package com.yazlab.dalconcreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.yazlab.dalabstract.IRepositoryBooksRatings;
import com.yazlab.models.bx_books_ratings;

public class RepositoryMysqlBooksRatings implements IRepositoryBooksRatings {

	@Override
	public boolean Kaydet(bx_books_ratings entity) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yazlab?user=root&password";
			Connection baglanti = DriverManager.getConnection(url);
			Statement stmp = baglanti.createStatement();
			String sql = "insert into bx_book_ratings " + "(" + " user_id," + " isbn," + " book_rating " + ")\r\n"
					+ " values" + " ('" + entity.getUser_id() + "'," + "'" + entity.getIsbn() + "'," + "'"
					+ entity.getBook_rating() + "" + "')";

			return stmp.executeUpdate(sql) > 0;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "hata:" + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean Karsilastirma(String isbn, String id) {
		try {

			String sql = "Select * from bx_book_ratings where user_id = ? and isbn = ?  ";
			Connection connection = new MysqlBaglanti().Baglanti();
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, isbn);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Zaten daha önce oyladýnýz.");
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "karsilastirma hatasý hata:" + e.getMessage());

		}
		return false;
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
