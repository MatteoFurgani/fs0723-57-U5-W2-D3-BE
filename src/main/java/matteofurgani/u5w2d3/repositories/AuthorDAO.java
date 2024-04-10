package matteofurgani.u5w2d3.repositories;

import matteofurgani.u5w2d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
    boolean existsByEmail(String email);
    Optional<Author> findByEmail(String email);
}
