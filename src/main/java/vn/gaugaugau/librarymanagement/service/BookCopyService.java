package vn.gaugaugau.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.BookCopy;
import vn.gaugaugau.librarymanagement.repository.BookCopyRepository;

@Service
@RequiredArgsConstructor
public class BookCopyService {

	private final BookCopyRepository bookCopyRepository;

	public BookCopy handleCreateBookCopy(BookCopy bookCopy) {
		return this.bookCopyRepository.save(bookCopy);
	}

	public List<BookCopy> findAllBookCopy() {
		return this.bookCopyRepository.findAll();
	}

	public BookCopy findBookCopyById(Long id) {
		return this.bookCopyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found!"));
	}

	public void handleUpdateBookCopy(Long id, BookCopy bookCopy) {
		Optional<BookCopy> bookCopyOpt = this.bookCopyRepository.findById(id);
		if (!bookCopyOpt.isPresent()) {
			throw new ResourceNotFoundException("id not found!");
		} else {
			BookCopy bookCopyInDB = bookCopyOpt.get();
			bookCopyInDB.setCode(bookCopy.getCode());
			bookCopyInDB.setStatus(bookCopy.getStatus());
			this.bookCopyRepository.save(bookCopyInDB);
		}
	}
}
