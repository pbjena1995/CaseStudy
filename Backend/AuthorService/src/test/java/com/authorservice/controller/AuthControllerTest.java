package com.authorservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.authorservice.authrequest.SigninRequest;
import com.authorservice.entity.Author;
import com.authorservice.repository.AuthorRepository;
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
	@Mock
	AuthorRepository authRepository;
	@InjectMocks
	AuthController authController;
	
	@Test
	public void signUpTest() {
		Author author =getAuthor();
		when(authRepository.existsByName(Mockito.anyString())).thenReturn(false);
		when(authRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
		when(authRepository.save(Mockito.any(Author.class))).thenReturn(author);
		assertEquals("User Registered Successfully !!!", authController.signUp(author).getBody());
		Mockito.verify(authRepository,atLeastOnce()).existsByName(Mockito.anyString());
		Mockito.verify(authRepository,atLeastOnce()).existsByEmail(Mockito.anyString());
		Mockito.verify(authRepository,atLeastOnce()).save(Mockito.any(Author.class));
	}
	@Test
	public void signUpTest_FailForName() {
		Author author =getAuthor();
		when(authRepository.existsByName(Mockito.anyString())).thenReturn(true);
		assertEquals("Username Already Exist !!!", authController.signUp(author).getBody());
		Mockito.verify(authRepository,atLeastOnce()).existsByName(Mockito.anyString());
	}
	@Test
	public void signUpTest_FailForEmail() {
		Author author =getAuthor();
		when(authRepository.existsByName(Mockito.anyString())).thenReturn(false);
		when(authRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
		assertEquals("User Email Already Exist !!!", authController.signUp(author).getBody());
		Mockito.verify(authRepository,atLeastOnce()).existsByName(Mockito.anyString());
		Mockito.verify(authRepository,atLeastOnce()).existsByEmail(Mockito.anyString());
	}
	@Test
	public void signinTest() {
		Author author =getAuthor();
		SigninRequest signinRequest=new SigninRequest();
		signinRequest.setEmail("abc@gmail.com");
		signinRequest.setPassword("jena123");
		when(authRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(author);
		assertEquals("User Login succefully !!!", authController.signIn(signinRequest).getBody());
		Mockito.verify(authRepository,atLeastOnce()).findByEmailAndPassword(Mockito.anyString(),Mockito.anyString());
	}
	@Test
	public void signinTestFail() {
		SigninRequest signinRequest=new SigninRequest();
		signinRequest.setEmail("abc@gmail.com");
		signinRequest.setPassword("jena123");
		when(authRepository.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		assertEquals("Invalid Credential !!!", authController.signIn(signinRequest).getBody());
		Mockito.verify(authRepository,atLeastOnce()).findByEmailAndPassword(Mockito.anyString(),Mockito.anyString());
	}
	public static Author getAuthor() {
		Author author=new Author();
		author.setId(1L);
		author.setEmail("abc@gmail.com");
		author.setName("jena");
		author.setPassword("jena123");
		return author;
	}
}
