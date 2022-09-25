package com.bookservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookservice.Repository.BookRepository;
import com.bookservice.Repository.ReaderRepository;
import com.bookservice.entity.Book;
import com.bookservice.entity.Payment;
import com.bookservice.entity.Reader;

@Service
public class ReaderService{
	
	@Autowired 
	private ReaderRepository readerRepository;	
	
	@Autowired
	private BookRepository bookRepository;
	
	public boolean purchaseBook(Reader reader) {
		Payment payment=new Payment();
		payment.setBook_id(reader.getPayment().get(0).getBook_id());
		payment.setPayment_status("Y");
		payment.setRefund_status("N");
		List<Payment> list=new ArrayList();
		
		if(!readerRepository.existsByEmail(reader.getEmail())) {
			list.add(payment);
			reader.setPayment(list);
			readerRepository.save(reader);
			return true;
		}
		Reader findByEmail = readerRepository.findByEmail(reader.getEmail());
		int readerId=findByEmail.getId();
		reader.setId(readerId);
//		List<Payment> payment = reader.getPayment();
//		payment.get(0).setReader_book_fk(readerId);
		payment.setReader_book_fk(readerId);
		list.add(payment);
		reader.setPayment(list);
		readerRepository.save(reader);
		return true;	
	}
	public Reader getAllPurchasedBooksByEmail(String email) {
		 Reader findByEmail = readerRepository.findByEmail(email);	
		 List<Payment> list= new ArrayList();
		// System.out.println("Root Cause--"+findByEmail.getEmail());
		 for(Payment payment:findByEmail.getPayment()) {
				if(payment.getRefund_status().equals("N") && payment.getPayment_status().equals("Y")) {
					System.out.println("Inside refund");
					list.add(payment);
				}
		}
		 
		findByEmail.setPayment(list);
		return findByEmail;
	}
	public List<Book> getBookdetails(Reader allPurchasedBooksByEmail) {
		List<Book> list=new ArrayList();
		for(Payment payment:allPurchasedBooksByEmail.getPayment()) {
			if(payment.getPayment_status().equals("Y") && payment.getRefund_status().equals("N")) {
				int bookId=payment.getBook_id();
				Book findById = bookRepository.findById(bookId).get();
				list.add(findById);
			}
			
		}
		return list;
	}

	public boolean refundRequest(String email,int bookId) {
		Reader findByEmail = readerRepository.findByEmail(email);
		
		for(Payment payment:findByEmail.getPayment()) {
			if(payment.getBook_id() == bookId) {
				readerRepository.updateRefundStatus(payment.getId(), "Y");
				break;
			}
		}
//		int updateRefundStatus = readerRepository.updateRefundStatus(paymentId, RefundStatus.DONE);
//		System.out.println("Refund status"+updateRefundStatus);
		return true;
	}
	
}
