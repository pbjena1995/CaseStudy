package com.bookservice.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.Repository.BookRepository;
import com.bookservice.entity.Book;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@PostMapping()
	public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
		if (bookRepository.existsByBookname(book.getBookname())) {
			return ResponseEntity.badRequest().body(book.getBookname() + " Is Already Present !!!");
		}
		book.setStatus('Y');
		Date date = new Date();
		book.setPublish_date(date);
		bookRepository.save(book);
		return ResponseEntity.ok("Book Is Created Successfully:" + book.getBookname());
	}

	@PutMapping()
	public ResponseEntity<?> updateBook(@Valid @RequestBody Book book) {
		if (!bookRepository.existsByBookname(book.getBookname())) {
			return ResponseEntity.badRequest().body(book.getBookname() + "  Is Not Present !!!");
		}
		Book bookResult = bookRepository.findByBookname(book.getBookname());
		book.setId(bookResult.getId());
		book.setPublish_date(bookResult.getPublish_date());
		book.setStatus(bookResult.getStatus());
		bookRepository.save(book);
		return ResponseEntity.ok("Book Is Updated Successfully:" + book.getBookname());
	}

	@GetMapping()
	public ResponseEntity<?> getBook() {
		List<Book> findAll = bookRepository.findAll();
		return ResponseEntity.ok(findAll);
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getBookByName(@PathVariable("name") String bookName) {
		if (!bookRepository.existsByBookname(bookName)) {
			return ResponseEntity.badRequest().body(bookName + " Book Is Not Present !!!");
		}
		return ResponseEntity.ok(bookRepository.findByBookname(bookName));
	}

	@PutMapping("/{name}/{author}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable("name") String bookName,
			@PathVariable("author") String authorName, @PathVariable("status") Character status) {
		Book findByBookname = bookRepository.findByBookname(bookName);
		if (findByBookname == null) {
			return ResponseEntity.badRequest().body(bookName + "  Is Not Present !!!");
		}
		if (!findByBookname.getAuthor_name().equals(authorName)) {
			return ResponseEntity.badRequest().body("Author Is Not Authorised To Change The Status Of The Book");
		}
		findByBookname.setStatus(status);
		bookRepository.save(findByBookname);
		return ResponseEntity.ok("Status Is Updated Successfully !!!");
	}
}
