package se.lexicon.book_lender_jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.book_lender_jpa.entity.Author;
import se.lexicon.book_lender_jpa.entity.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired  AuthorRepository authorRepository;

    @Autowired BookRepository bookRepository;

    @Test
    public void testFindByFirstName() {
        Author author = new Author("firstName", "lastName");
        authorRepository.save(author);
        List<Author> optionalAuthor = authorRepository.findByFirstName("firstName");
        Assertions.assertEquals(optionalAuthor.size(), 1);
    }
    @Test
    public void testFindByLastName() {
        Author author = new Author("firstName", "lastName");
        authorRepository.save(author);
        List<Author> optionalAuthor = authorRepository.findByLastName("lastName");
        Assertions.assertEquals(optionalAuthor.size(), 1);
    }
    @Test
    public void testFindByFirstNameOrLastNameContaining() {
        Author author = new Author("firstName", "lastName");
        authorRepository.save(author);
        List<Author> authors = authorRepository.findByFirstNameOrLastNameContaining("firstName", "lastName");
        Assertions.assertEquals(authors.size(), 1);
    }

    @Test
    public void testFindByBookId(){

        Book book = new Book("The fall of civilization","Paul Cooper");
        bookRepository.save(book);
        Author author = new Author("Paul", "Cooper");
        author.getWrittenBooks().add(book);
        authorRepository.save(author);
        // Fetch authors by the book ID
        List<Author> authors = authorRepository.findByBook_Id(book.getId());

        //Todo
        Assertions.assertEquals(authors.size(), 1);





    }
}
