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
import vn.gaugaugau.librarymanagement.model.Category;
import vn.gaugaugau.librarymanagement.response.ApiResponse;
import vn.gaugaugau.librarymanagement.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping("/categories")
	public ResponseEntity<ApiResponse<Category>> createRole(@Valid @RequestBody Category category) {
		Category newCategory = this.categoryService.handleCreateCategory(category);
		return ApiResponse.created(newCategory);
	}

	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<Category>>> getAllCategory() {
		List<Category> listCategory = this.categoryService.findAllCategory();
		return ApiResponse.success(listCategory);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id) {
		Category category = this.categoryService.findCategoryById(id);
		return ApiResponse.success(category);
	}
}
