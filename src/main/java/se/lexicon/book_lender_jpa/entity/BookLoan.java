package se.lexicon.book_lender_jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(nullable = false)
    private LocalDate loanDate = LocalDate.now();

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column
    boolean returned;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private AppUser borrower;

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    private Book book;


    public BookLoan( AppUser borrower, Book book) {
        this.loanDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(book.getMaxLoanDays());
        this.borrower = borrower;
        this.book = book;

    }

    public void addBookLoan(BookLoan bookLoan) {
       book.getBookLoans().add(bookLoan);
       borrower.getBookLoans().add(bookLoan);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        book.getBookLoans().remove(bookLoan);
        borrower.getBookLoans().remove(bookLoan);
    }




}
