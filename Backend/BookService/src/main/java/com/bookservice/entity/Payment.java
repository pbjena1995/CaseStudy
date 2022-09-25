package com.bookservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int book_id;
	//status Y menas payment done , N means payment not done
	private String payment_status;
	//status Y menas refund done , N means refund not requested
	private String refund_status;
	@Column(insertable=false, updatable=false)
	private int reader_book_fk;
}
