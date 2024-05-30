package se.lexicon.book_lender_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.book_lender_jpa.entity.AppUser;
import se.lexicon.book_lender_jpa.entity.Details;
import se.lexicon.book_lender_jpa.repository.AppUserRepository;
import se.lexicon.book_lender_jpa.repository.DetailsRepository;

import java.time.LocalDate;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    final
    AppUserRepository appUserRepository;

    final
    DetailsRepository detailsRepository;

    @Autowired
    public MyCommandLineRunner(AppUserRepository appUserRepository, DetailsRepository detailsRepository) {
        this.appUserRepository = appUserRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Details details = new Details("user@test", "user", LocalDate.of(2000,1,1));
        AppUser appUser = new AppUser("username","1234",details);

        detailsRepository.save(details);
        appUserRepository.save(appUser);


    }
}
