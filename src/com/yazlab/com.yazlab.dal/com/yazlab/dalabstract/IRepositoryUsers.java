package com.yazlab.dalabstract;

import java.util.ArrayList;
import com.yazlab.models.bx_users;

public interface IRepositoryUsers {

	boolean Kaydet(bx_users entity);
	boolean Sil(int id);
	ArrayList<bx_users> Listele();
}
