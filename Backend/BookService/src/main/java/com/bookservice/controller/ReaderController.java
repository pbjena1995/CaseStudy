package com.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.Book;
import com.bookservice.entity.Reader;
import com.bookservice.service.ReaderService;

@CrossOrigin
@RestController
@RequestMapping("/reader")
public class ReaderController {

	@Autowired
	ReaderService readerService;

	@PostMapping("/buyBook")
	public ResponseEntity<?> buyBook(@RequestBody Reader reader) {
		if (readerService.purchaseBook(reader)) {
			return ResponseEntity.ok("Purchase is done Successfully !!!");
		}
		return ResponseEntity.badRequest().body("Purchase Failed !!!");
	}

	@GetMapping("/{email}")
	public ResponseEntity<?> getAllPurchasedBooksByEmail(@PathVariable String email) {
		Reader allPurchasedBooksByEmail = readerService.getAllPurchasedBooksByEmail(email);
		List<Book> bookdetails = readerService.getBookdetails(allPurchasedBooksByEmail);
		return ResponseEntity.ok(bookdetails);
	}

	@PostMapping("/refund/{email}/{bookId}")
	public ResponseEntity<?> requestForRefund(@PathVariable String email,@PathVariable int bookId) {
		boolean refundRequest = readerService.refundRequest(email,bookId);
		if (refundRequest) {
			return ResponseEntity.ok("Refund Succefully Done !!!");
		}
		return ResponseEntity.badRequest().body("Refund Failed !!!");
	}
}
