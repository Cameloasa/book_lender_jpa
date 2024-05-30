package se.lexicon.book_lender_jpa.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.book_lender_jpa.entity.AppUser;

import java.util.Optional;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    @Test
    @Transactional
    public void testSaveAndFindById() {
        //1.Arrange
        AppUser appUser = new AppUser("user", "1234");
        //2.Act
        AppUser savedAppUser = appUserRepository.save(appUser);
        //3.Assert

        Assertions.assertNotNull(savedAppUser);
        Assertions.assertNotNull(savedAppUser.getId());

        Optional<AppUser> foundAppUser = appUserRepository.findById(savedAppUser.getId());
        Assertions.assertTrue(foundAppUser.isPresent());

    }
}
