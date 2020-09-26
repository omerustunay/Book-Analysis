package com.yazlab.bll;

import java.util.ArrayList;

import com.yazlab.dalabstract.IRepositoryBooks;
import com.yazlab.interfaces.Ibx_booksService;
import com.yazlab.models.bx_books;

public class bx_booksManager implements Ibx_booksService {

	IRepositoryBooks repository;

	public bx_booksManager(IRepositoryBooks repository) {
		super();
		this.repository = repository;
	}

	@Override
	public boolean Kaydet(bx_books entity) {
		return repository.Kaydet(entity);
	}

	@Override
	public boolean Sil(int id) {
		return repository.Sil(id);
	}


	@Override
	public ArrayList<bx_books> Listele() {

		return repository.Listele();
	}


	@Override
	public ArrayList<bx_books> PopulerListele() {
		return repository.PopulerListele();
	}

	@Override
	public bx_books kListele(bx_books ktp) {
		return repository.kListele(ktp);
	}

	@Override
	public ArrayList<bx_books> EnKitapListele() {
		return repository.EnKitapListele();

	}

	@Override
	public ArrayList<bx_books> Son5Listele() {
		return repository.Son5Listele();
	}

}
