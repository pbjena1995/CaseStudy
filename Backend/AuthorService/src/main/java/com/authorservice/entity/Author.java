package com.authorservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "email") })
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Author Name CanNot Be Blank!!!")
	@Size(max = 100)
	private String name;
	@NotBlank(message = "Email CanNot Be Blank!!!")
	@NotNull
	@Size(max = 20)
	private String email;
	@NotBlank(message = "Password CanNot Be Blank!!!")
	@Size(max = 10)
	private String password;
}
