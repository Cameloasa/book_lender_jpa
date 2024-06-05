package se.lexicon.book_lender_jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired  AuthorRepository authorRepository;
}
