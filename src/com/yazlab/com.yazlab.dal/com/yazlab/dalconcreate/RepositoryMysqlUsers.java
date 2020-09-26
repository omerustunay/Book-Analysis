package com.yazlab.dalconcreate;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.yazlab.dalabstract.IRepositoryUsers;
import com.yazlab.models.bx_users;

public class RepositoryMysqlUsers implements IRepositoryUsers {

	Connection baglanti;

	public RepositoryMysqlUsers() {
		this.baglanti = new MysqlBaglanti().Baglanti();
	}

	@Override
	public boolean Kaydet(bx_users entity) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yazlab?user=root&password";
			Connection baglanti = DriverManager.getConnection(url);
			Statement stmp = baglanti.createStatement();
			String sql = "insert into bx_users2 "
					+ "("
					+ "location,"
					+ " age,"
					+ " kullanici_adi"
					+ ")\r\n"
					+ " values"
					+ " ('"+entity.getLocation()+"',"
					+ "'"+entity.getAge()+"',"
					+ "'"+entity.getKullanici_adi()+""
					+ "')";
			
			
			return stmp.executeUpdate(sql)>0;

		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "hata:" + e.getMessage());
			return false;
		}


	}

	@Override
	public boolean Sil(int id) {
		try {
			String sql = "delete from bx_users2 where user_id=" + id + "";
			Statement stmp = baglanti.createStatement();
			return stmp.executeUpdate(sql) > 0;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "sql de hata var");
			return false;
		}
	}

	@Override
	public ArrayList<bx_users> Listele() {
		ArrayList<bx_users> kullaniciListesi = new ArrayList<>();
		try {

			String sql = "Select * from bx_users2";
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()) {

				bx_users f = new bx_users();
				f.setLocation(rs.getString(2));
				f.setAge(rs.getString(3));
				f.setUser_id(Integer.parseInt(rs.getString(1)));
				f.setKullanici_adi(rs.getString(4));

				kullaniciListesi.add(f);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return kullaniciListesi;
	}


}
