package vn.gaugaugau.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.gaugaugau.librarymanagement.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	boolean existsByName(String name);
}
