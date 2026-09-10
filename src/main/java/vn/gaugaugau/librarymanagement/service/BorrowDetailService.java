package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.BorrowDetail;
import vn.gaugaugau.librarymanagement.repository.BorrowDetailRepository;

@Service
@RequiredArgsConstructor
public class BorrowDetailService {

	private final BorrowDetailRepository borrowDetailRepository;

	public BorrowDetail handleCreateBorrowDetail(BorrowDetail borrowDetail) {
		return this.borrowDetailRepository.save(borrowDetail);
	}

	public List<BorrowDetail> findAllBorrowDetail() {
		return this.borrowDetailRepository.findAll();
	}

	public BorrowDetail findBorrowDetailById(Long id) {
		return this.borrowDetailRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id not found!"));
	}

	public void handleUpdateBorrowDetail(Long id, BorrowDetail borrowDetail) {
		Optional<BorrowDetail> borrowDetailOpt = this.borrowDetailRepository.findById(id);
		if (!borrowDetailOpt.isPresent()) {
			throw new ResourceNotFoundException("id not found!");
		} else {
			BorrowDetail borrowDetailInDB = borrowDetailOpt.get();
			borrowDetailInDB.setStatus(borrowDetail.getStatus());
			this.borrowDetailRepository.save(borrowDetailInDB);
		}
	}
}
