package com.yazlab.models;

public class bx_books {


	
	
	private String isbn;
	private String book_title;
	private String book_author;
	private String year_of_publication;
	private String publisher;
	private String image_url_s;
	private String image_url_m;
	private String image_url_l;

	
	public bx_books() {
		super();
	}


	public bx_books(String isbn, String book_title, String book_author, String year_of_publication, String publisher,
			String image_url_s, String image_url_m, String image_url_l) {
		super();
		this.isbn = isbn;
		this.book_title = book_title;
		this.book_author = book_author;
		this.year_of_publication = year_of_publication;
		this.publisher = publisher;
		this.image_url_s = image_url_s;
		this.image_url_m = image_url_m;
		this.image_url_l = image_url_l;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getBook_title() {
		return book_title;
	}


	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}


	public String getBook_author() {
		return book_author;
	}


	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}


	public String getYear_of_publication() {
		return year_of_publication;
	}


	public void setYear_of_publication(String year_of_publication) {
		this.year_of_publication = year_of_publication;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getImage_url_s() {
		return image_url_s;
	}


	public void setImage_url_s(String image_url_s) {
		this.image_url_s = image_url_s;
	}


	public String getImage_url_m() {
		return image_url_m;
	}


	public void setImage_url_m(String image_url_m) {
		this.image_url_m = image_url_m;
	}


	public String getImage_url_l() {
		return image_url_l;
	}


	public void setImage_url_l(String image_url_l) {
		this.image_url_l = image_url_l;
	}
	
	
}
