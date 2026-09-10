package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceAlreadyExistsException;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.Category;
import vn.gaugaugau.librarymanagement.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public Category handleCreateCategory(Category category) {
		boolean checkExistsByName = this.categoryRepository.existsByName(category.getName());
		if (checkExistsByName) {
			throw new ResourceAlreadyExistsException("Name already exists.");
		} else {
			return this.categoryRepository.save(category);
		}
	}

	public List<Category> findAllCategory() {
		List<Category> listCategories = this.categoryRepository.findAll();
		return listCategories;
	}

	public Category findCategoryById(Long id) {
		Optional<Category> categoryOpt = this.categoryRepository.findById(id);
		if (!categoryOpt.isPresent()) {
			throw new ResourceNotFoundException("id not found!");
		} else {
			return categoryOpt.get();
		}
	}
}
