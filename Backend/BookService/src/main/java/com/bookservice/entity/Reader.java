package com.bookservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Reader {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Reader Name Cannot be Blank")
	private String name;
	@NotBlank(message = "Reader Email Cannot be Blank")
	private String email;
	@OneToMany(targetEntity = Payment.class, cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "reader_book_fk", referencedColumnName = "id",nullable=false)
	private List<Payment> payment;
}
