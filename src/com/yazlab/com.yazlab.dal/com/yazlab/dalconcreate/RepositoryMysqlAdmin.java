package com.yazlab.dalconcreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.yazlab.dalabstract.IRepositoryAdmin;
import com.yazlab.models.bx_admin;

public class RepositoryMysqlAdmin implements IRepositoryAdmin{

	Connection baglanti;

	public RepositoryMysqlAdmin() {
		this.baglanti = new MysqlBaglanti().Baglanti();
	}
	@Override
	public boolean Kaydet(bx_admin entity) {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/yazlab?user=root&password";
		Connection baglanti = DriverManager.getConnection(url);
		Statement stmp = baglanti.createStatement();
		String sql = "insert into bx_admin "
				+ "("
				+ " userName,"
				+ " password,"
				+ " age,"
				+ " location"
				+ ")\r\n"
				+ " values"
				+ " ('"+entity.getUserName()+"',"
				+ "'"+entity.getPassword()+"',"
				+ "'"+entity.getAge()+"',"
				+ "'"+entity.getLocation()+""				
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
			String sql = "delete from bx_admin where adminID=" + id + "";
			Statement stmp = baglanti.createStatement();
			return stmp.executeUpdate(sql) > 0;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "sql de hata var");
			return false;
		}
	}


	@Override
	public ArrayList<bx_admin> Listele() {
		ArrayList<bx_admin> adminListesi = new ArrayList<>();
		try {

			String sql = "Select * from bx_admin";
			Statement stmp = baglanti.createStatement();
			ResultSet rs = stmp.executeQuery(sql);

			while (rs.next()) {

				bx_admin f = new bx_admin();
				f.setAdminID(Integer.parseInt(rs.getString(1)));
				f.setUserName(rs.getString(2));
				f.setLocation(rs.getString(5));
				f.setAge(rs.getString(4));
				
				adminListesi.add(f);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}
		return adminListesi;
	}


}
