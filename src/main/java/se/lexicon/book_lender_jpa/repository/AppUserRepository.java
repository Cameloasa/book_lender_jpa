package se.lexicon.book_lender_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.book_lender_jpa.entity.AppUser;
import se.lexicon.book_lender_jpa.entity.Details;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {


     Optional<AppUser> findByUserName(String username);
     List<AppUser> findByRegDateBetween(LocalDate start, LocalDate end);
     Optional<AppUser> findByUserDetails_Id(Integer id);
     AppUser findByUserDetails_EmailIgnoreCase(String email);
}
