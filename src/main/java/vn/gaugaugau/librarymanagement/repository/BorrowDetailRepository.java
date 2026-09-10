package vn.gaugaugau.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.gaugaugau.librarymanagement.model.BorrowDetail;

@Repository
public interface BorrowDetailRepository extends JpaRepository<BorrowDetail, Long> {

}
