package com.yazlab.dalabstract;

import java.util.ArrayList;
import com.yazlab.models.bx_books;

public interface IRepositoryBooks {

	boolean Kaydet(bx_books entity);
	boolean Sil(int id);
	ArrayList<bx_books> Listele();
	ArrayList<bx_books> PopulerListele();
	bx_books kListele(bx_books ktp);
	ArrayList<bx_books> EnKitapListele();
	ArrayList<bx_books> Son5Listele();

}
