package com.author.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.author.config.CustomeUserService;
import com.author.config.JwtUtil;
import com.author.entity.Author;
import com.author.repository.AuthorRepository;
import com.author.requests.SigninRequest;
import com.author.requests.SignupRequest;
import com.author.response.SigninResponse;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController extends BaseExceptionHandler{
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	private CustomeUserService customeUserService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest signupRequest){
		
		if(authorRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body("User Already Exist !!!");
		}
		
		Author author=new Author();
		author.setEmail(signupRequest.getEmail());
		author.setUsername(signupRequest.getUsername());
		author.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		authorRepository.save(author);
		return ResponseEntity.ok("User Registerd Successfully !!!");
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody SigninRequest signinRequest){
	
		System.out.println("Inside signin !!!");
		if(!authorRepository.existsByUsername(signinRequest.getUsername())) {
			return ResponseEntity.badRequest().body("User Not Present !!!");
		}
		 this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		

		String jwt = jwtUtil.generateToken(customeUserService.loadUserByUsername(signinRequest.getUsername()));
		return ResponseEntity.ok(new SigninResponse(jwt));
	}

}
