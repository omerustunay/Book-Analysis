package com.yazlab.interfaces;

import java.util.ArrayList;

import com.yazlab.models.bx_users;

public interface Ibx_usersService {

	boolean Kaydet(bx_users entity);
	boolean Sil(int id);
	ArrayList<bx_users> Listele();
}
