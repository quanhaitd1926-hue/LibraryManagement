package vn.gaugaugau.librarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.gaugaugau.librarymanagement.exception.ResourceNotFoundException;
import vn.gaugaugau.librarymanagement.model.Book;
import vn.gaugaugau.librarymanagement.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public Book handleCreateBook(Book book) {
		return this.bookRepository.save(book);
	}

	public List<Book> findAllBook() {
		List<Book> listBooks = this.bookRepository.findAll();
		return listBooks;
	}

	public Book findBookById(Long id) {
		return this.bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found!"));
	}
}
