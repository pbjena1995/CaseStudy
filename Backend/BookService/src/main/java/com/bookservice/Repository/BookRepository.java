package com.bookservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookservice.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}
