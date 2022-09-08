package com.bookservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "bookname") })
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Bookname Cannot be Blank")
	private String bookname;
	private String image;
	@NotBlank(message = "Book Title Cannot be Blank")
	private String title;
	@Enumerated(EnumType.STRING)
	private BookCategory category;
	@Column(nullable = false)
	private float price;
	@NotBlank(message = "Author Name Cannot Be Blank")
	private String author_name;
	@NotBlank(message = "Book Publisher Cannot Be Blank")
	private String publisher;
	private Date publish_date;
	@NotBlank(message = "Book Content Cannot Be Blank")
	private String contents;
	// status 'Y' means active & 'N' means not active
	private Character status;

}
