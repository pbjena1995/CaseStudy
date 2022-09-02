package com.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.Repository.BookRepository;
import com.bookservice.entity.Book;


@Service
public class BookService {

	
	
	@Autowired
	private BookRepository bookRepo;
	
	public void saveBook(Book book) {
		//System.out.println("Book Data--"+book);
		bookRepo.save(book);
		
	}

}
