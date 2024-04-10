package matteofurgani.u5w2d3.repositories;

import matteofurgani.u5w2d3.entities.Author;
import matteofurgani.u5w2d3.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostDAO extends JpaRepository<Blogpost, Integer> {
    List<Blogpost> findByAuthor(Author author);
}

