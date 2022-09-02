package com.bookservice.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookservice.entity.Book;
import com.bookservice.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class BookController extends ExceptionHandler{
	
	
	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/authorId/{authorId}")
	public ResponseEntity<?> createBook(@Valid @PathVariable("authorId") int author_id, @RequestBody Book book) {
		
		book.setAuthor_id(author_id); 
		book.setStatus('Y');
		book.setCreate_date(new java.util.Date());
		book.setImage("");
		book.setPublish_date(new java.util.Date());
		System.out.println(book);
		bookService.saveBook(book);	
		ResponseEntity responseEntity = new ResponseEntity(book.getId() , HttpStatus.CREATED);
		return responseEntity;
		
	}
	
	
	@PutMapping("/authorId/{authorId}/books/{bookId}")
	public ResponseEntity<?> updateBook(@Valid @PathVariable("authorId") int author_id,@PathVariable("bookId") int bookId, @RequestBody Book book) {
		book.setAuthor_id(author_id);
		book.setId(bookId);
		bookService.saveBook(book);	
		ResponseEntity responseEntity = new ResponseEntity(book.getId() , HttpStatus.CREATED);
		return responseEntity;
	}
	
	

}
