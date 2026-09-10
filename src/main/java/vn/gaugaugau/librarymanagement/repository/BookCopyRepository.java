package vn.gaugaugau.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.gaugaugau.librarymanagement.model.BookCopy;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

}
