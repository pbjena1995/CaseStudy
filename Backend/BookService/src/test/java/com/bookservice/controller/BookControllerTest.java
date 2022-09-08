package com.bookservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookservice.Repository.BookRepository;
import com.bookservice.entity.Book;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@Mock
	BookRepository bookRepository;
	@InjectMocks
	BookController bookController;

	@Test
	public void createBookTest() {
		Book book = getBook();
		when(bookRepository.existsByBookname(Mockito.anyString())).thenReturn(false);
		when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		assertEquals("Book Is Created Successfully:" + book.getBookname(), bookController.createBook(book).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).existsByBookname(Mockito.anyString());
		Mockito.verify(bookRepository, atLeastOnce()).save(Mockito.any(Book.class));
	}

	@Test
	public void createBookTestFail() {
		Book book = getBook();
		when(bookRepository.existsByBookname(Mockito.anyString())).thenReturn(true);
		assertEquals(book.getBookname() + " Is Already Present !!!", bookController.createBook(book).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).existsByBookname(Mockito.anyString());
	}

	@Test
	public void updateBookTest() {
		Book book = getBook();
		when(bookRepository.existsByBookname(Mockito.anyString())).thenReturn(true);
		when(bookRepository.findByBookname(Mockito.anyString())).thenReturn(book);
		when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		assertEquals("Book Is Updated Successfully:" + book.getBookname(), bookController.updateBook(book).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).existsByBookname(Mockito.anyString());
		Mockito.verify(bookRepository, atLeastOnce()).findByBookname(Mockito.anyString());
		Mockito.verify(bookRepository, atLeastOnce()).save(Mockito.any(Book.class));
	}

	@Test
	public void updateBookTestFail() {
		Book book = getBook();
		when(bookRepository.existsByBookname(Mockito.anyString())).thenReturn(false);
		assertEquals(book.getBookname() + "  Is Not Present !!!", bookController.updateBook(book).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).existsByBookname(Mockito.anyString());
	}

	@Test
	public void getBookTest() {
		Book book = getBook();
		List<Book> list = new ArrayList<Book>();
		list.add(book);
		when(bookRepository.findAll()).thenReturn(list);
		assertTrue(bookController.getBook().getBody() instanceof List);
		Mockito.verify(bookRepository, atLeastOnce()).findAll();
	}

	@Test
	public void getBookByNameTest() {
		Book book = getBook();
		when(bookRepository.existsByBookname(Mockito.anyString())).thenReturn(true);
		when(bookRepository.findByBookname(Mockito.anyString())).thenReturn(book);
		assertTrue(bookController.getBookByName(Mockito.anyString()).getBody() instanceof Book);
		Mockito.verify(bookRepository, atLeastOnce()).existsByBookname(Mockito.anyString());
		Mockito.verify(bookRepository, atLeastOnce()).findByBookname(Mockito.anyString());

	}

	@Test
	public void getBookByNameTestFail() {
		Book book = getBook();
		when(bookRepository.existsByBookname(Mockito.anyString())).thenReturn(false);
		assertEquals(book.getBookname() + " Book Is Not Present !!!",
				bookController.getBookByName(book.getBookname()).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).existsByBookname(Mockito.anyString());
	}

	@Test
	public void updateStatus() {
		Book book = getBook();
		when(bookRepository.findByBookname(Mockito.anyString())).thenReturn(book);
		when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		assertEquals("Status Is Updated Successfully !!!",
				bookController.updateStatus(book.getBookname(), book.getAuthor_name(), book.getStatus()).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).findByBookname(Mockito.anyString());
		Mockito.verify(bookRepository, atLeastOnce()).save(Mockito.any(Book.class));
	}

	@Test
	public void updateStatusBookNotPresentFail() {
		Book book = getBook();
		when(bookRepository.findByBookname(Mockito.anyString())).thenReturn(null);
		assertEquals(book.getBookname() + "  Is Not Present !!!",
				bookController.updateStatus(book.getBookname(), book.getAuthor_name(), book.getStatus()).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).findByBookname(Mockito.anyString());
	}

	@Test
	public void updateStatusAuthorNotAuthorisedFail() {
		Book book = getBook();
		when(bookRepository.findByBookname(Mockito.anyString())).thenReturn(book);
		assertEquals("Author Is Not Authorised To Change The Status Of The Book",
				bookController.updateStatus(book.getBookname(), "wick", book.getStatus()).getBody());
		Mockito.verify(bookRepository, atLeastOnce()).findByBookname(Mockito.anyString());
	}

	public static Book getBook() {
		Book book = new Book();	
		book.setId(1);
		book.setBookname("Java");
		book.setImage("D/Image");
		book.setTitle("Java Book");
		//book.setCategory(BookCategory.COMIC);
		book.setPrice(1000.0f);
		book.setAuthor_name("John");
		book.setPublisher("abc");
		book.setPublish_date(new Date());
		book.setContents("This is java book");
		book.setStatus('Y');
		return book;
	}
}
