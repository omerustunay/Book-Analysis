package com.yazlab.bll;

import java.util.ArrayList;

import com.yazlab.dalabstract.IRepositoryAdmin;
import com.yazlab.interfaces.Ibx_adminService;
import com.yazlab.models.bx_admin;

public class bx_adminManager implements Ibx_adminService{

	IRepositoryAdmin repository;
	
	public bx_adminManager(IRepositoryAdmin repository) {
		super();
		this.repository = repository;
	}

	@Override
	public boolean Kaydet(bx_admin entity) {
		return repository.Kaydet(entity);
	}

	@Override
	public boolean Sil(int id) {
		return repository.Sil(id);
	}

	
	@Override
	public ArrayList<bx_admin> Listele() {
		return repository.Listele();
	}


}
