package com.yazlab.dalconcreate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.TableModel;

public class BuildDB {
    public Connection databaseConnection(Connection connection ) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yazlab?useSSL=false", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hata Mysql'e baðlanýlamadý");
        }
        return connection;
    }
    public void closeConnection(Connection connection, Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Hata!");
                e.printStackTrace();
            }
        }

        if(connection!= null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Hata!");
            }
        }
    }
	public static TableModel resultSetToTableModel(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
