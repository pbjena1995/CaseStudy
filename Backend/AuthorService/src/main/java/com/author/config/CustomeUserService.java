package com.author.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.author.entity.Author;
import com.author.repository.AuthorRepository;

@Component
public class CustomeUserService implements UserDetailsService{

	@Autowired
	AuthorRepository authorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Author author = authorRepository.findByUsername(username);
		
		if(author == null) {
			throw new UsernameNotFoundException("User Not Present");
		}
		return new User(author.getUsername(),author.getPassword(),new ArrayList<>());
	}

}
