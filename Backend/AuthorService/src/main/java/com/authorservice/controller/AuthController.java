package com.authorservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authorservice.authrequest.SigninRequest;
import com.authorservice.entity.Author;
import com.authorservice.repository.AuthorRepository;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthController extends BaseExceptionHandler{
	@Autowired
	AuthorRepository authorRepository;

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody Author author) {
		if (authorRepository.existsByName(author.getName())) {
			return ResponseEntity.badRequest().body("Username Already Exist !!!");
		}
		if (authorRepository.existsByEmail(author.getEmail())) {
			return ResponseEntity.badRequest().body("User Email Already Exist !!!");
		}
		authorRepository.save(author);
		return ResponseEntity.ok("User Registered Successfully !!!");
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody SigninRequest signinRequest) {
		Author findByNameAndPassword = authorRepository.findByEmailAndPassword(signinRequest.getEmail(),
				signinRequest.getPassword());
		if (findByNameAndPassword == null) {
			return ResponseEntity.badRequest().body("Invalid Credential !!!");
		}
		return ResponseEntity.ok("User Login succefully !!!");
	}
}
