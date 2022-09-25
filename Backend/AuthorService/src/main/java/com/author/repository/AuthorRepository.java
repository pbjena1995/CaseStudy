package com.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.author.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>{

	Author findByEmail(String email);
	Author findByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsername(String email);
	boolean findByEmailAndPassword(String email,String password);
}
