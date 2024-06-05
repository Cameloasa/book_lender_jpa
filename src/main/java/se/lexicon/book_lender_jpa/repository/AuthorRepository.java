package se.lexicon.book_lender_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.book_lender_jpa.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


    //Find authors by their first name.
    List<Author> findByFirstName(String firstName);

    //Find authors by their last name
    List<Author> findByLastName(String lastName);


    //Find authors by their first name or last name containing a keyword
    List<Author> findByFirstNameOrLastNameContaining(String firstName, String lastName);

    //Find authors by a book's ID
    @Query("SELECT a FROM Author a JOIN a.writtenBooks b WHERE b.id = :bookId")
    List<Author> findByBookId(@Param("bookId") Integer bookId);

    //Update an author's name by their ID
    @Modifying
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName WHERE a.id = :id")
    void updateAuthorNameById(@Param("id") Integer id, @Param("firstName") String firstName, @Param("lastName") String lastName);

    //Delete an author by their ID
    @Modifying
    @Query("DELETE FROM Author a WHERE a.id = :id")
    void deleteByAuthorId(@Param("id") Integer id);
}
