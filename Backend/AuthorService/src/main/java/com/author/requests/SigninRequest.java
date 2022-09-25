package com.author.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class SigninRequest {

	@NotBlank
	@Size(max=20)
	private String username;
	@NotBlank
	@Size(max=10)
	private String password;
}
