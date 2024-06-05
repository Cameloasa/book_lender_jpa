package se.lexicon.book_lender_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.book_lender_jpa.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findByFirstName(String firstName);
}
