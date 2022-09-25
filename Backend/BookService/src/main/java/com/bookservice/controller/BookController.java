package com.bookservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.bookservice.entity.BookCategory;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController extends BaseExceptionHandler{
	@Autowired
	private BookRepository bookRepository;

	@PostMapping()
	public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
		if (bookRepository.existsByBooknameAndAuthorname(book.getBookname(),book.getAuthorname())) {
			return ResponseEntity.badRequest().body("Book Is Already Present !!!");
		}
		book.setStatus("ACTIVE");
		Date date = new Date();
		book.setPublish_date(date);
		book.setImage("D:/Image");
		bookRepository.save(book);
		return ResponseEntity.ok("Book Is Created Successfully:" + book.getBookname());
	}

	@PutMapping()
	public ResponseEntity<?> updateBook(@Valid @RequestBody Book book) {
		Book bookResult = bookRepository.findById(book.getId()).get();
		book.setId(bookResult.getId());
		book.setPublish_date(bookResult.getPublish_date());
		book.setImage(bookResult.getImage());
		book.setContents(bookResult.getContents());
		book.setStatus(bookResult.getStatus());
		bookRepository.save(book);
		return ResponseEntity.ok("Book Is Updated Successfully:" + book.getBookname());
	}

	@GetMapping()
	public ResponseEntity<?> getBook() {
		List<Book> findAll = bookRepository.findAll();
		return ResponseEntity.ok(findAll);
	}
	
	@GetMapping("/activebook")
	public ResponseEntity<?> getActiveBook() {
		List<Book> findAll = bookRepository.findAll();
		List<Book> list=new ArrayList<>();
		for(Book book:findAll) {
			if(book.getStatus().equals("ACTIVE")) {
				list.add(book);
			}
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getBookByName(@PathVariable("name") String bookName) {
		if (!bookRepository.existsByBookname(bookName)) {
			return ResponseEntity.badRequest().body(bookName + " Book Is Not Present !!!");
		}
		return ResponseEntity.ok(bookRepository.findByBookname(bookName));
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<?> updateStatus(@PathVariable("bookId") int bookId) {
		 Book findById = bookRepository.findById(bookId).get();
		if (findById == null) {
			return ResponseEntity.badRequest().body("Book Is Not Present !!!");
		}
		String status = findById.getStatus();
		System.out.println("Book Status-"+status);
		String updateStatus="";
		if(status.equalsIgnoreCase("ACTIVE")) {
			updateStatus="INACTIVE";
		}
		else {
			updateStatus="ACTIVE";
		}
		System.out.println("Book Update Status-"+updateStatus);
		findById.setId(bookId);
		findById.setStatus(updateStatus);
		bookRepository.save(findById);
		return ResponseEntity.ok("Status Is Updated Successfully !!!");
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<?> getBookByCategory(@PathVariable("category") BookCategory category){
		System.out.println("Inside getBookByCategory !!!");
		List<Book> findByCategory = bookRepository.findByCategory(category);
		if(findByCategory == null) {
			return ResponseEntity.badRequest().body("No Results Found !!!");
		}
		return ResponseEntity.ok(findByCategory);
	}
	@GetMapping("author/{author}")
	public ResponseEntity<?> getBookByAuthor(@PathVariable("author") String author){
		System.out.println("Inside getBookByAuthor !!!");
		List<Book> findByAuthor = bookRepository.findByAuthorname(author);
		if(findByAuthor == null) {
			return ResponseEntity.badRequest().body("No Results Found !!!");
		}
		return ResponseEntity.ok(findByAuthor);
	}
	@GetMapping("price/{price}")
	public ResponseEntity<?> getBookByPrice(@PathVariable("price") float price){
		List<Book> findByPrice = bookRepository.findByPrice(price);
		if(findByPrice == null) {
			return ResponseEntity.badRequest().body("No Results Found !!!");
		}
		return ResponseEntity.ok(findByPrice);
	}
	@GetMapping("publisher/{publisher}")
	public ResponseEntity<?> getBookByPublisher(@PathVariable("publisher") String publisher){
		List<Book> findByPublisher = bookRepository.findByPublisher(publisher);
		if(findByPublisher == null) {
			return ResponseEntity.badRequest().body("No Results Found !!!");
		}
		return ResponseEntity.ok(findByPublisher);
	}
}
