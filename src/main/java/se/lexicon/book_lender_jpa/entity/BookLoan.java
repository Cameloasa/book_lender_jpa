package se.lexicon.book_lender_jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "borrower_id")
    private AppUser borrowers;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book books;


    public BookLoan(LocalDate loanDate, LocalDate dueDate, AppUser borrowers, Book books) {
        this.loanDate = LocalDate.now();
        this.dueDate = dueDate;
        this.borrowers = borrowers;
        this.books = books;
    }
}
