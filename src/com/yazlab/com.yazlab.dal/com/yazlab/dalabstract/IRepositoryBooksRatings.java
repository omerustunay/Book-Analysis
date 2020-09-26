package com.yazlab.dalabstract;


import com.yazlab.models.bx_books_ratings;

public interface IRepositoryBooksRatings {
	
	boolean Kaydet(bx_books_ratings entity);
	boolean Karsilastirma(String isbn, String id);
	boolean Karsilastirma(String isbn);
	boolean Karsilastirma();
	
}
