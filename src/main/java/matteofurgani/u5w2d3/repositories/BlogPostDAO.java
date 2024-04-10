package matteofurgani.u5w2d3.repositories;

import matteofurgani.u5w2d3.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogPostDAO extends JpaRepository<Blogpost, Integer> {
    boolean existsByTitle(String title);
    Optional<Blogpost> findByTitle(String title);
}
