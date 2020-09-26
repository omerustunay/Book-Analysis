package com.yazlab.bll;

import com.yazlab.dalabstract.IRepositoryBooksRatings;
import com.yazlab.interfaces.Ibx_books_ratingsService;
import com.yazlab.models.bx_books_ratings;

public class bx_books_ratingsManager implements Ibx_books_ratingsService {

	IRepositoryBooksRatings repository;

	public bx_books_ratingsManager(IRepositoryBooksRatings repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public boolean Kaydet(bx_books_ratings entity) {
		// TODO Auto-generated method stub
		return repository.Kaydet(entity);
	}

	
	@Override
	public boolean Karsilastirma(String isbn, String id) {
		// TODO Auto-generated method stub
		return repository.Karsilastirma(isbn,id);
	}

	@Override
	public boolean Karsilastirma(String isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Karsilastirma() {
		// TODO Auto-generated method stub
		return false;
	}
}
