package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.Author;
import vn.gaugaugau.librarymanagement.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {

	private final AuthorRepository authorRepository;

	public Author handleCreateAuthor(Author author) {
		String[] listDetailsName = author.getName().trim().split("\\s+");
		String formatedName = "";
		for (int i = 0; i < listDetailsName.length; i++) {
			formatedName += Character.toUpperCase(listDetailsName[i].charAt(0));
			for (int j = 1; j < listDetailsName[i].length(); j++) {
				formatedName += Character.toLowerCase(listDetailsName[i].charAt(j));
			}
			formatedName += " ";
		}
		author.setName(formatedName.trim());
		return this.authorRepository.save(author);
	}

	public List<Author> findAllAuthor() {
		List<Author> listAuthors = this.authorRepository.findAll();
		return listAuthors;
	}

	public Author findAuthorById(Long id) {
		Optional<Author> categoryOpt = this.authorRepository.findById(id);
		if (!categoryOpt.isPresent()) {
			throw new ResourceNotFoundException("id not found!");
		} else {
			return categoryOpt.get();
		}
	}
}
