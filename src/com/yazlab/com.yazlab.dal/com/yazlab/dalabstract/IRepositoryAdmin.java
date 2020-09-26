package com.yazlab.dalabstract;

import java.util.ArrayList;
import com.yazlab.models.bx_admin;

public interface IRepositoryAdmin {

	boolean Kaydet(bx_admin entity);
	boolean Sil(int id);
	ArrayList<bx_admin> Listele();
}
