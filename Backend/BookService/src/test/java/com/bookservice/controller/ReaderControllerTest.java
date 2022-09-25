package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.entity.Payment;
import com.bookservice.entity.Reader;
import com.bookservice.service.ReaderService;


@ExtendWith(MockitoExtension.class)
class ReaderControllerTest {

	@Mock
	ReaderService readerService;
	@InjectMocks
	ReaderController readerController;
	
	@Test
	public void buyBookTest() {
		Reader reader=getReader();
		when(readerService.purchaseBook(Mockito.any(Reader.class))).thenReturn(true);
		assertEquals("Purchase is done Successfully !!!", readerController.buyBook(reader).getBody());
		Mockito.verify(readerService, atLeastOnce()).purchaseBook(Mockito.any(Reader.class));
	}
	
	@Test
	public void buyBookTestFail() {
		Reader reader=getReader();
		when(readerService.purchaseBook(Mockito.any(Reader.class))).thenReturn(false);
		assertEquals("Purchase Failed !!!", readerController.buyBook(reader).getBody());
		Mockito.verify(readerService, atLeastOnce()).purchaseBook(Mockito.any(Reader.class));
	}
	
	@Test
	public void getAllPurchasedBooksByEmailTest() {
		Reader reader=getReader();
		when(readerService.getAllPurchasedBooksByEmail(Mockito.anyString())).thenReturn(reader);
		assertTrue(readerController.getAllPurchasedBooksByEmail(Mockito.anyString()).getBody() instanceof Reader);
		Mockito.verify(readerService, atLeastOnce()).getAllPurchasedBooksByEmail(Mockito.anyString());
	}
	
	
	public static Reader getReader() {
		Reader reader=new Reader();
		
		reader.setId(1);
		reader.setEmail("abc@gmail.com");
		reader.setName("Priyabrat Jena");
		Payment payment=new Payment();
		payment.setId(2);
		payment.setBook_id(1);
		payment.setPayment_status("Y");
		payment.setRefund_status("N");
		payment.setReader_book_fk(1);
		List<Payment> list=new ArrayList();
		list.add(payment);
		reader.setPayment(list);
		return reader;
	}
	
}
