package vn.gaugaugau.librarymanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.model.Borrow;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.BorrowService;

@RestController
@RequiredArgsConstructor
public class BorrowController {

	private final BorrowService borrowService;

	@PostMapping("/borrows")
	public ResponseEntity<ApiResponse<Borrow>> createBorrow(@RequestBody Borrow borrow) {
		Borrow newBorrow = this.borrowService.handleCreateBorrow(borrow);
		return ApiResponse.created(newBorrow);
	}

	@GetMapping("/borrows")
	public ResponseEntity<ApiResponse<List<Borrow>>> getAllBorrow() {
		List<Borrow> listBorrows = this.borrowService.findAllBorrow();
		return ApiResponse.success(listBorrows);
	}

	@GetMapping("/borrows/{id}")
	public ResponseEntity<ApiResponse<Borrow>> getBorrowById(@PathVariable Long id) {
		Borrow borrow = this.borrowService.findBorrowById(id);
		return ApiResponse.success(borrow);
	}

	@PutMapping("/borrows/{id}")
	public ResponseEntity<ApiResponse<String>> updateBorrow(@PathVariable Long id, @RequestBody Borrow borrow) {
		this.borrowService.handleUpdateBorrow(id, borrow);
		return ApiResponse.success("update successful!");
	}
}
