package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "book_no")
public class Book extends Item {

	private String author;
	private String isbn;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", isbn=" + isbn + ", getNo()=" + getNo() + ", getTitle()=" + getTitle()
				+ ", getCategory()=" + getCategory() + ", getStatus()=" + getStatus() + "]";
	}

}
