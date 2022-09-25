package com.author.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
	
	@NotBlank
	@Size(max=20)
	private String username;
	@NotBlank
	@Size(max=30)
	private String email;
	@NotBlank
	@Size(max=10)
	private String password;
}
