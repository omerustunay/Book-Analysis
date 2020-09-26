package com.yazlab.swingui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.yazlab.dalconcreate.BuildDB;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class FrmLoginPaneli extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUserName;
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	int id;
//	private GridPane gridPane;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			FrmLoginPaneli dialog = new FrmLoginPaneli();
			dialog.setLocationRelativeTo(null);// Tam ortada baslar. Ekrani ortalar
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmLoginPaneli() {
		setTitle("Login Page");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/ustLogin.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblKullancAd = new JLabel("Username");
			lblKullancAd.setBounds(166, 93, 59, 14);
			contentPanel.add(lblKullancAd);
		}
		{
			txtUserName = new JTextField();
			txtUserName.setBounds(254, 90, 86, 20);
			contentPanel.add(txtUserName);
			txtUserName.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(166, 124, 59, 14);
			contentPanel.add(lblPassword);
		}
		{
			JButton btnSign = new JButton("Sign      ");
			btnSign.setIcon(new ImageIcon(this.getClass().getResource("/okey.png")));
			btnSign.setBounds(116, 206, 126, 44);
			btnSign.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mysqlOpr();
				}
			});
			contentPanel.add(btnSign);
		}
		{
			JButton btnRegister = new JButton("Register");
			btnRegister.setIcon(new ImageIcon(this.getClass().getResource("/kullaniciEkle.png")));
			btnRegister.setBounds(254, 206, 126, 40);
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					FrmRegisterPanel frmRegisterGit = new FrmRegisterPanel();
					frmRegisterGit.main(new String[0]);// register paneline git.
					frmRegisterGit.setLocationRelativeTo(null);
					dispose();// login ekranýný kapat

				}
			});
			contentPanel.add(btnRegister);
		}

		txtPassword = new JPasswordField();
		txtPassword.setBounds(254, 121, 86, 20);
		contentPanel.add(txtPassword);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(this.getClass().getResource("/login.png")));
		label.setBounds(28, 60, 118, 127);
		contentPanel.add(label);
	}

	private int mysqlOpr() {
		int kontrol = 0;
		String sql = "SELECT  adminID from bx_admin" + " WHERE userName = ? AND password = ?";
		try {
			connection = new BuildDB().databaseConnection(connection);
			ps = connection.prepareStatement(sql);
			ps.setString(1, txtUserName.getText());
			ps.setString(2, txtPassword.getText());
			rs = ps.executeQuery();
			if (rs.next()) {

				FrmAdminPaneli admineGit = new FrmAdminPaneli();
				admineGit.setLocationRelativeTo(null);
				dispose();
				admineGit.setVisible(true);// admin paneline git.
				dispose();// login ekranýný kapat

//                System.out.println("Login Succesful for admin");
				JOptionPane.showMessageDialog(null, "Login Succesful for admin");
//                id =rs.getInt(1);
//                System.out.println("Admin Online (Admin No: " + id + ")");
				
				return 0;

			} else {
//                System.out.println("Login Fail for Admin");
//                JOptionPane.showMessageDialog(null, "Login Fail for Admin");
			}

		} catch (Exception sqlEx) {
//            System.out.println(sql);
//            System.out.println("Error : WelcomePage/SingInButton hata:"+sqlEx.getMessage());
			JOptionPane.showMessageDialog(null, "Error : WelcomePage/SingInButton hata:" + sqlEx.getMessage());
			sqlEx.printStackTrace();
		}
		sql = "SELECT  user_id from bx_users2" + " WHERE kullanici_adi = ? AND password = ?";
		try {
			connection = new BuildDB().databaseConnection(connection);
			ps = connection.prepareStatement(sql);
			ps.setString(1, txtUserName.getText());
			ps.setString(2, txtPassword.getText());
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				sql = "Select count(user_id) From bx_book_ratings where user_id = ?";
				connection = new BuildDB().databaseConnection(connection);
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next())
					kontrol = rs.getInt(1);

				if (kontrol < 10) {
					FrmKitapOylamaIslemi frmChooseBooks = new FrmKitapOylamaIslemi();
					frmChooseBooks.setLocationRelativeTo(null);
					frmChooseBooks.setVisible(true);
//					userIDTxtAt(id);
					frmChooseBooks.userID=id;
					System.out.println("logindeyim "+id);
					
					dispose();
				} else {
					FrmFiltrelemeIslemi frmFotoGoster = new FrmFiltrelemeIslemi();
					frmFotoGoster.setVisible(true);
					frmFotoGoster.setLocationRelativeTo(null);
					setVisible(false);
//					userIDTxtAt(id);
					frmFotoGoster.userYedekId=id;
					System.out.println("logindeyim "+id);


				}

//                    JOptionPane.showMessageDialog(null, "Login Succesful for User");
			} else {
				JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
			}

		} catch (Exception sqlEx2) {
//                System.out.println(sql);
			JOptionPane.showMessageDialog(null, "Error : WelcomePage/SingInButton Hata:" + sqlEx2.getMessage());
			sqlEx2.printStackTrace();
		}
		return 0;
	}
	
//	private void userIDTxtAt(int id) throws IOException {
//		 String str = id+"";
//		 	
//	        File file = new File("dosya.txt");
//	        if (!file.exists()) {
//	            file.createNewFile();
//	        }
//
//	        FileWriter fileWriter = new FileWriter(file, false);
//	        BufferedWriter bWriter = new BufferedWriter(fileWriter);
//	        bWriter.write(str);
//	        bWriter.close();
//	}
//	public String userIDtxtOku() throws IOException {
//
//        File file = new File("dosya.txt");
//        BufferedReader reader = null;
//        reader = new BufferedReader(new FileReader(file));
//        String satir = reader.readLine();
//        
//            return satir;
//	}
}
