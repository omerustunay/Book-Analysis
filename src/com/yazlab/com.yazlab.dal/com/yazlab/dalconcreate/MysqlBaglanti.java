package com.yazlab.dalconcreate;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlBaglanti {
	public Connection Baglanti() {
		
		Connection baglanti=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yazlab?user=root&password";
			baglanti=DriverManager.getConnection(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baglanti;
	}
}
