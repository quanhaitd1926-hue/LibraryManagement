package vn.gaugaugau.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.gaugaugau.librarymanagement.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
