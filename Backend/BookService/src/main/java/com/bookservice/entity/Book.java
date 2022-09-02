package com.bookservice.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
public class Book {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false, unique=true)
	private int id;
	
	@Lob
	@Column(nullable=false, length=Integer.MAX_VALUE)
	private String image;
	
	@NotBlank(message="Book Title Cannot be Blank")
	@Size(min=5 , max=50 , message="Book Title Must Be Between 5 to 50 Character")
	@Column(nullable=false)
	private String title;
	
	@NotBlank(message="Book Category Cannot Blank")
	@Size(max=30 , message="Book Category Must 30 Character")
	@Column(nullable=false)
	private String category;
	
	@Column(nullable=false, precision=6, scale=2)
	private float price;
	
	
	@Column(nullable=false)
	private int author_id;
	
	@NotBlank(message="Book Publisher Cannot Blank")
	@Size(max=100 , message="Book Publisher Must 100 Character")
	@Column(nullable=false)
	private String publisher;
	
	private Date publish_date;
	
	
	@Column(nullable=false)
	private String contents;
	
	private Character status;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date create_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", category=" + category + ", price=" + price + ", author_id="
				+ author_id + ", publisher=" + publisher + ", publish_date=" + publish_date + ", contents=" + contents
				+ ", status=" + status + ", create_date=" + create_date + "]";
	}

}
