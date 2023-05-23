package com.book.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
	
	@Id
	@GeneratedValue(generator = "book_seq")
	@SequenceGenerator(name="book_seq",initialValue = 11111,allocationSize = 1)
	private int bookid;
	private String title;
	private String author;
	private int catid;
	private int pubid;
	private int copies;
	private int price;
	
	

}
