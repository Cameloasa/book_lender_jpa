package se.lexicon.book_lender_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "regDate")


@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100 )
    @Setter private String userName;

    @Column(nullable = false, length = 100 )
    @Setter private String password;

    @Column
    private LocalDate regDate;

    @OneToOne
    @JoinColumn(name = "detail_id")
    @Setter private Details userDetails;


    @OneToMany(mappedBy = "borrower",cascade = CascadeType.ALL)
    private Set<BookLoan>  bookLoans = new HashSet<>();




    public AppUser(String userName, String password, Details details) {
        this.userName = userName;
        this.password = password;
        this.userDetails = details;
        this.regDate = LocalDate.now();

    }

    public AppUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public AppUser(String userName, Details userDetails) {
        this.userName = userName;
        this.userDetails = userDetails;
        this.regDate = LocalDate.now();
    }

    public void addBookLoan(BookLoan bookLoan) {
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
    }
}
