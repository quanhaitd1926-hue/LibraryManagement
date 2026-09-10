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
import vn.gaugaugau.librarymanagement.model.Author;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;

	@PostMapping("/authors")
	public ResponseEntity<ApiResponse<Author>> createAuthor(@Valid @RequestBody Author author) {
		Author newAuthor = this.authorService.handleCreateAuthor(author);
		return ApiResponse.created(newAuthor);
	}

	@GetMapping("/authors")
	public ResponseEntity<ApiResponse<List<Author>>> getAllAuthor() {
		List<Author> listAuthors = this.authorService.findAllAuthor();
		return ApiResponse.success(listAuthors);
	}

	@GetMapping("/authors/{id}")
	public ResponseEntity<ApiResponse<Author>> getAuthorById(@PathVariable Long id) {
		Author author = this.authorService.findAuthorById(id);
		return ApiResponse.success(author);
	}
}
