package com.authorservice.authrequest;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninRequest {
	@NotBlank(message = "Email Cannot Be Blank")
	private String email;
	@NotBlank(message = "Password Cannot Be Blank")
	private String password;
}
