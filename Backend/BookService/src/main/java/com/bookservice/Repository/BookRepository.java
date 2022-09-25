package com.bookservice.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookservice.entity.Book;
import com.bookservice.entity.BookCategory;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	Boolean existsByBookname(String bookName);
	
	Boolean existsByBooknameAndAuthorname(String bookName,String author);

	Book findByBookname(String bookName);

	List<Book> findByCategory(BookCategory category);

	List<Book> findByAuthorname(String author);

	List<Book> findByPrice(float price);

	List<Book> findByPublisher(String publisher);
}
