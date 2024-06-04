package se.lexicon.book_lender_jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.book_lender_jpa.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContaining(String title);

    List<Book> findByMaxLoanDaysLessThan(Integer maxLoanDays);

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.returned = false")
    List<Book> findBooksCurrentlyOnLoan();


    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.dueDate < :currentDate AND bl.returned = false")
    List<Book> findOverdueBooks(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT b FROM Book b JOIN b.bookLoans bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
    List<Book> findBooksLoanedBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}