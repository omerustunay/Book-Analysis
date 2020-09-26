package com.yazlab.bll;

import java.util.ArrayList;

import com.yazlab.dalabstract.IRepositoryUsers;
import com.yazlab.interfaces.Ibx_usersService;
import com.yazlab.models.bx_users;

public class bx_usersManager implements Ibx_usersService{

	
	IRepositoryUsers repository;
	
	public bx_usersManager(IRepositoryUsers repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public boolean Kaydet(bx_users entity) {
		// TODO Auto-generated method stub
		return repository.Kaydet(entity);
	}

	@Override
	public boolean Sil(int id) {
		return repository.Sil(id);
	}


	@Override
	public ArrayList<bx_users> Listele() {
		return repository.Listele();
	}


}
