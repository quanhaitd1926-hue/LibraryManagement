package vn.gaugaugau.librarymanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.model.Book;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping("/books")
	public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody Book book) {
		Book newBook = this.bookService.handleCreateBook(book);
		return ApiResponse.created(newBook);
	}

	@GetMapping("/books")
	public ResponseEntity<ApiResponse<List<Book>>> getALlBook() {
		List<Book> listBooks = this.bookService.findAllBook();
		return ApiResponse.success(listBooks);
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) {
		Book book = this.bookService.findBookById(id);
		return ApiResponse.success(book);
	}
}
