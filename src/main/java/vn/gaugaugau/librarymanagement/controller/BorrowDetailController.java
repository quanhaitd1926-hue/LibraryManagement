package vn.gaugaugau.librarymanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.model.BorrowDetail;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.BorrowDetailService;

@RestController
@RequiredArgsConstructor
public class BorrowDetailController {

	private final BorrowDetailService borrowDetailService;

	@PostMapping("/borrow_details")
	public ResponseEntity<ApiResponse<BorrowDetail>> createBorrowDetai(@Valid @RequestBody BorrowDetail borrowDetail) {
		BorrowDetail newBorrowDetail = this.borrowDetailService.handleCreateBorrowDetail(borrowDetail);
		return ApiResponse.created(newBorrowDetail);
	}

	@GetMapping("/borrow_details")
	public ResponseEntity<ApiResponse<List<BorrowDetail>>> getAllBorrowDetail() {
		List<BorrowDetail> listBorrowDetails = this.borrowDetailService.findAllBorrowDetail();
		return ApiResponse.success(listBorrowDetails);
	}

	@GetMapping("/borrow_details/{id}")
	public ResponseEntity<ApiResponse<BorrowDetail>> getBorrowDetailById(@PathVariable Long id) {
		BorrowDetail borrowDetail = this.borrowDetailService.findBorrowDetailById(id);
		return ApiResponse.success(borrowDetail);
	}

	@PutMapping("/borrow_details/{id}")
	public ResponseEntity<ApiResponse<String>> updateBorrowDetail(@PathVariable Long id,
			@RequestBody BorrowDetail borrowDetail) {
		this.borrowDetailService.handleUpdateBorrowDetail(id, borrowDetail);
		return ApiResponse.success("update successful!");
	}
}
