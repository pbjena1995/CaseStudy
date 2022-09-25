package com.bookservice.service;

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

import com.bookservice.Repository.ReaderRepository;
import com.bookservice.entity.Payment;
import com.bookservice.entity.Reader;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

	@Mock
	ReaderRepository readerRepository;
	@InjectMocks
	ReaderService readerService;

	@Test
	public void getAllPurchasedBooksByEmailTest() {
		Reader reader = getReader();
		when(readerRepository.findByEmail(Mockito.anyString())).thenReturn(reader);
		assertTrue(readerService.getAllPurchasedBooksByEmail(reader.getEmail()) instanceof Reader);
		Mockito.verify(readerRepository, atLeastOnce()).findByEmail(Mockito.anyString());
	}
	

	public static Reader getReader() {
		Reader reader = new Reader();

		reader.setId(1);
		reader.setEmail("abc@gmail.com");
		reader.setName("Priyabrat Jena");
		Payment payment = new Payment();
		payment.setId(2);
		payment.setBook_id(1);
		payment.setPayment_status("Y");
		payment.setRefund_status("N");
		payment.setReader_book_fk(1);
		List<Payment> list = new ArrayList();
		list.add(payment);
		reader.setPayment(list);
		return reader;
	}

}
