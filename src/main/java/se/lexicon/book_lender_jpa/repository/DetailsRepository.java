package se.lexicon.book_lender_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.book_lender_jpa.entity.Details;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Integer> {

    Optional<Details> findByEmail(String email);

    List<Details> findByNameContaining(String nameContaining);

    List<Details> findByNameContainingIgnoreCase(String name);


}
