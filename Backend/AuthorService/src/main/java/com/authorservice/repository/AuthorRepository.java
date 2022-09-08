package com.authorservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorservice.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	Optional<Author> findByName(String name);

	Boolean existsByName(String name);

	Boolean existsByEmail(String email);

	Author findByEmailAndPassword(String name, String password);
}
