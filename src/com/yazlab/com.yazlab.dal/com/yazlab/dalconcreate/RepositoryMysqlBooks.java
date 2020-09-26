package com.yazlab.dalconcreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.yazlab.dalabstract.IRepositoryBooks;
import com.yazlab.models.bx_books;

public class RepositoryMysqlBooks implements IRepositoryBooks {

	Connection baglanti;

	public RepositoryMysqlBooks() {
		this.baglanti = new MysqlBaglanti().Baglanti();
	}

	@Override
	public boolean Kaydet(bx_books entity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yazlab?user=root&password";
			Connection baglanti = DriverManager.getConnection(url);
			Statement stmp = baglanti.createStatement();
			String sql = "insert into bx_books "
					+ "("
					+ "isbn,"
					+ " book_title,"
					+ " book_author,"
					+ "year_of_publication,"
					+ "publisher,"
					+ "image_url_s,"
					+ "image_url_m,"
					+ "image_url_l"
					+ ")\r\n"
					+ " values"
					+ " ('"+entity.getIsbn()+"',"
					+ "'"+entity.getBook_title()+"',"
					+ "'"+entity.getBook_author()+"',"
					+ "'"+entity.getYear_of_publication()+"',"
					+ "'"+entity.getPublisher()+"',"
					+ "'"+entity.getImage_url_s()+"',"
					+ "'"+entity.getImage_url_m()+"',"
					+ "'"+entity.getImage_url_l()+""				
					+ "')";
			
			return stmp.executeUpdate(sql)>0;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "hata:" + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean Sil(int id) {
		try {
			String sql = "delete from bx_books where isbn=" + id + "";
			Statement stmp = baglanti.createStatement();
			return stmp.executeUpdate(sql) > 0;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "sql de hata var");
			return false;
		}
	}


	@Override
	public ArrayList<bx_books> Listele() {
		ArrayList<bx_books> KitapListesi = new ArrayList<>();
		try {

			String sql = "Select * from bx_books";
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()) {

				bx_books f = new bx_books();
				f.setIsbn(rs.getString(1));
				f.setBook_title(rs.getString(2));
				f.setBook_author(rs.getString(3));
				f.setYear_of_publication(rs.getString(4));
				f.setPublisher(rs.getString(5));
				f.setImage_url_s(rs.getString(6));
				f.setImage_url_m(rs.getString(7));
				f.setImage_url_l(rs.getString(8));
				KitapListesi.add(f);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return KitapListesi;
	}


	@Override
	public ArrayList<bx_books> PopulerListele() {
		
		
		ArrayList<bx_books> PopulerListe = new ArrayList<>();
		try {

			String sql = "select isbn "
					+ "from bx_book_ratings "
					+ "GROUP BY isbn having count(isbn) "
					+ "order by count(isbn) "
					+ "DESC limit 11;";
			
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()) {

				bx_books f = new bx_books();
				f.setIsbn(rs.getString(1));
				String a = f.getIsbn();
				if(!a.equals(""))
				PopulerListe.add(f);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return PopulerListe;
		
		
	}

	@Override
	public bx_books kListele(bx_books ktp) {
		bx_books f = new bx_books();
		bx_books a = new bx_books();
		
		try {

			String sql = "Select * from bx_books where isbn = "+  " ('"+ktp.getIsbn()+"')";
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()  ) {
				a.setIsbn(rs.getString(1));
				if( !( a.getIsbn() == null )  && !( a.getIsbn().equals("") )  ) {
				f.setIsbn(a.getIsbn());
				f.setBook_title(rs.getString(2));
				f.setBook_author(rs.getString(3));
				f.setYear_of_publication(rs.getString(4));
				f.setPublisher(rs.getString(5));
				f.setImage_url_s(rs.getString(6));
				f.setImage_url_m(rs.getString(7));
				f.setImage_url_l(rs.getString(8));
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return f;
	}

	@Override
	public ArrayList<bx_books> EnKitapListele() {
		ArrayList<bx_books> EnKitapListe = new ArrayList<>();
		try {
			String sql = "select isbn "
					+ "from bx_book_ratings "
					+ "GROUP BY isbn having Avg(book_rating) "
					+ "order by Avg(book_rating) "
					+ "DESC limit 18;";
			
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()) {

				bx_books f = new bx_books();
				f.setIsbn(rs.getString(1));
				if(!f.getIsbn().equals("") && !(f.getIsbn()  == null) )

				EnKitapListe.add(f);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return EnKitapListe;
	}

	@Override
	public ArrayList<bx_books> Son5Listele() {
		ArrayList<bx_books> Son5Liste = new ArrayList<>();
		try {
			String sql = "select * from bx_books order by book_id desc limit 5;";
			
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()) {

				bx_books f = new bx_books();
				f.setIsbn(rs.getString(1));
				if(!f.getIsbn().equals("") && !(f.getIsbn()  == null) )
				Son5Liste.add(f);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return Son5Liste;
	}
	
}
