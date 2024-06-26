package se.lexicon.book_lender_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer maxLoanDays;

   @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BookLoan> bookLoans = new HashSet<>();


   @ManyToMany(mappedBy = "writtenBooks", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Author> authors = new HashSet<>();

    public Book(String isbn, String title, Integer maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;

    }

    public Book(String title,String author) {
        this.title = title;
        this.authors = getAuthors();
    }

    public void addAuthor(Author author) {

        authors.add(author);
        author.getWrittenBooks().add(this);
    }

    public void removeAuthor(Author author) {

        authors.remove(author);
        author.getWrittenBooks().remove(this);
    }


}
