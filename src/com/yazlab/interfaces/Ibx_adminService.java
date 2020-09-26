package com.yazlab.interfaces;

import java.util.ArrayList;

import com.yazlab.models.bx_admin;

public interface Ibx_adminService {

	boolean Kaydet(bx_admin entity);
	boolean Sil(int id);
	ArrayList<bx_admin> Listele();
}
