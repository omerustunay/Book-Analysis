package com.yazlab.interfaces;

import com.yazlab.models.bx_books_ratings;

public interface Ibx_books_ratingsService {

	boolean Kaydet(bx_books_ratings entity);
	boolean Karsilastirma(String isbn, String id);
	boolean Karsilastirma(String isbn);
	boolean Karsilastirma();
	
}
