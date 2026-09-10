package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.Borrow;
import vn.gaugaugau.librarymanagement.repository.BorrowRepository;

@Service
@RequiredArgsConstructor
public class BorrowService {

	private final BorrowRepository borrowRepository;

	public Borrow handleCreateBorrow(Borrow borrow) {
		return this.borrowRepository.save(borrow);
	}

	public List<Borrow> findAllBorrow() {
		return this.borrowRepository.findAll();
	}

	public Borrow findBorrowById(Long id) {
		return this.borrowRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found!"));
	}

	public void handleUpdateBorrow(Long id, Borrow borrow) {
		Optional<Borrow> borrowOpt = this.borrowRepository.findById(id);
		if (!borrowOpt.isPresent()) {
			throw new ResourceNotFoundException("id not found!");
		} else {
			Borrow borrowInDB = borrowOpt.get();
			borrowInDB.setBorrowDate(borrow.getBorrowDate());
			borrowInDB.setDueDate(borrow.getDueDate());
			borrowInDB.setReturnDate(borrow.getReturnDate());
			borrowInDB.setStatus(borrow.getStatus());
			this.borrowRepository.save(borrowInDB);
		}
	}
}
