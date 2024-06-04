package se.lexicon.book_lender_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.book_lender_jpa.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {

    // Find book loans by borrower's ID
    List<BookLoan> findByBorrowerId(Integer borrowerId);

    // Find book loans by book ID
    List<BookLoan> findByBookId(Integer bookId);

    // Find book loans that are not yet returned
    List<BookLoan> findByReturnedFalse();

    // Find overdue book loans (due date is past and not returned)
    @Query("SELECT bl FROM BookLoan bl WHERE bl.dueDate < :currentDate AND bl.returned = false")
    List<BookLoan> findOverdueBookLoans(@Param("currentDate") LocalDate currentDate);

    // Find book loans between specified dates
    @Query("SELECT bl FROM BookLoan bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
    List<BookLoan> findBookLoansBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
