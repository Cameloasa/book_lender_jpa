package se.lexicon.book_lender_jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.book_lender_jpa.entity.AppUser;
import se.lexicon.book_lender_jpa.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;

    @Test

    public void testSaveAndFindById() {
        //1.Arrange
        AppUser appUser = new AppUser("joe_doe", "1234");
        //2.Act
        AppUser savedAppUser = appUserRepository.save(appUser);
        //3.Assert

        Assertions.assertNotNull(savedAppUser);
        Assertions.assertNotNull(savedAppUser.getId());

        Optional<AppUser> foundAppUser = appUserRepository.findById(savedAppUser.getId());
        Assertions.assertTrue(foundAppUser.isPresent());

    }

    @Test

    public void testFindByUserName() {
        // Arrange
        Details userDetails = new Details("Jon Doe", "jondoe@test.com", LocalDate.of(2000,1,1));
        Details savedDetails = detailsRepository.save(userDetails);

        // Create an app user
        AppUser appUser = new AppUser("joe_doe", "1234", savedDetails);
        AppUser savedAppUser = appUserRepository.save(appUser);

        // Act - Test finding by username
        Optional<AppUser> foundUser = appUserRepository.findByUserName("joe_doe");

        // Assert
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("joe_doe", foundUser.get().getUserName());
    }

    @Test
    public void findByUserDetailsEmailIgnoreCase(){

        // Arrange
        Details userDetails = new Details("Bob Johnson", "bob@test.com", LocalDate.of(2002,2,2));
        Details savedDetails = detailsRepository.save(userDetails);

        AppUser appUser = new AppUser("bob_johnson", "1234", savedDetails);
        appUserRepository.save(appUser);

        // Act
        Optional<AppUser> foundUser = appUserRepository.findByUserDetails_EmailIgnoreCase("BOB@TEST.COM");

        // Assert
        Assertions.assertTrue(foundUser.isPresent());
        //Assertions.assertEquals("bob_johnson", foundUser.get().getUserName());
    }

    @Test
    public void testFindByRegDateBetween() {
        // Arrange
        Details userDetails = new Details("Jane Doe", "janedoe@test.com",LocalDate.of(2003,3,3));
        Details savedDetails = detailsRepository.save(userDetails);

        AppUser appUser = new AppUser("jane_doe", "1234", savedDetails);
        appUserRepository.save(appUser);

        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);

        // Act
        List<AppUser> foundUsers = appUserRepository.findByRegDateBetween(startDate, endDate);

        // Assert
        Assertions.assertFalse(foundUsers.isEmpty());
        Assertions.assertEquals(1, foundUsers.size());
        Assertions.assertEquals("jane_doe", foundUsers.get(0).getUserName());
    }

    @Test
    public void testFindByUserDetailsId() {
        // Arrange
        Details userDetails = new Details("Alice Smith", "alice@test.com", LocalDate.of(2004,4,4));
        Details savedDetails = detailsRepository.save(userDetails);

        AppUser appUser = new AppUser("alice_smith", "1234", savedDetails);
        appUserRepository.save(appUser);

        // Act
        Optional<AppUser> foundUser = appUserRepository.findByUserDetails_Id(savedDetails.getId());

        // Assert
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals("alice_smith", foundUser.get().getUserName());
    }



}





