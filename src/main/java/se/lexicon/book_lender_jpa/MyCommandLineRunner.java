package se.lexicon.book_lender_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.lexicon.book_lender_jpa.entity.AppUser;
import se.lexicon.book_lender_jpa.entity.Book;
import se.lexicon.book_lender_jpa.entity.BookLoan;
import se.lexicon.book_lender_jpa.entity.Details;
import se.lexicon.book_lender_jpa.repository.AppUserRepository;
import se.lexicon.book_lender_jpa.repository.BookLoanRepository;
import se.lexicon.book_lender_jpa.repository.BookRepository;
import se.lexicon.book_lender_jpa.repository.DetailsRepository;

import java.time.LocalDate;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    DetailsRepository detailsRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookLoanRepository loanRepository;

    @Override
    public void run(String... args) throws Exception {


        Book book = new Book("1234Test","Test Book",30);

        Details details = new Details("user@test", "user", LocalDate.of(2000,1,1));

        AppUser appUser = new AppUser("username","1234",details);

        BookLoan bookLoan = new BookLoan(appUser,book);

        detailsRepository.save(details);
        appUserRepository.save(appUser);
        bookRepository.save(book);
        loanRepository.save(bookLoan);


    }
}
