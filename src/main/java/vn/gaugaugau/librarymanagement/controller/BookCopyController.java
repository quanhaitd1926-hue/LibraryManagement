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
import vn.gaugaugau.librarymanagement.model.BookCopy;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.BookCopyService;

@RestController
@RequiredArgsConstructor
public class BookCopyController {

	private final BookCopyService bookCopyService;

	@PostMapping("/book_copies")
	public ResponseEntity<ApiResponse<BookCopy>> createBookCopy(@Valid @RequestBody BookCopy bookCopy) {
		BookCopy newBookCopy = this.bookCopyService.handleCreateBookCopy(bookCopy);
		return ApiResponse.created(newBookCopy);
	}

	@GetMapping("/book_copies")
	public ResponseEntity<ApiResponse<List<BookCopy>>> getAllBookCopy() {
		List<BookCopy> listBookCopies = this.bookCopyService.findAllBookCopy();
		return ApiResponse.success(listBookCopies);
	}

	@GetMapping("/book_copies/{id}")
	public ResponseEntity<ApiResponse<BookCopy>> getBookCopyById(@PathVariable Long id) {
		BookCopy bookCopy = this.bookCopyService.findBookCopyById(id);
		return ApiResponse.success(bookCopy);
	}

	@PutMapping("/book_copies/{id}")
	public ResponseEntity<ApiResponse<String>> updateBookCopy(@PathVariable Long id, @RequestBody BookCopy bookCopy) {
		this.bookCopyService.handleUpdateBookCopy(id, bookCopy);
		return ApiResponse.success("update successful!");
	}
}
