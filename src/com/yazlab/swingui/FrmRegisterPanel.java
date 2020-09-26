package com.yazlab.swingui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.yazlab.dalconcreate.BuildDB;
import java.awt.Toolkit;

public class FrmRegisterPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtYourAge;
	private JTextField txtYourLocation;
	    Connection connection = null;
	    ResultSet rs = null;
	    ResultSet rs2 = null;
	    private JPasswordField txtPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegisterPanel frame = new FrmRegisterPanel();
					frame.setLocationRelativeTo(null);//Tam ortada baslar. Ekrani ortalar
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmRegisterPanel() {
		setTitle("Register Page");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/UserAdd.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 1;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		txtUserName = new JTextField();
		GridBagConstraints gbc_txtUserName = new GridBagConstraints();
		gbc_txtUserName.insets = new Insets(0, 0, 5, 0);
		gbc_txtUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUserName.gridx = 4;
		gbc_txtUserName.gridy = 1;
		contentPane.add(txtUserName, gbc_txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 2;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 4;
		gbc_txtPassword.gridy = 2;
		contentPane.add(txtPassword, gbc_txtPassword);
		
		JLabel lblYourAge = new JLabel("Your Age");
		GridBagConstraints gbc_lblYourAge = new GridBagConstraints();
		gbc_lblYourAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblYourAge.gridx = 2;
		gbc_lblYourAge.gridy = 3;
		contentPane.add(lblYourAge, gbc_lblYourAge);
		
		txtYourAge = new JTextField();
		GridBagConstraints gbc_txtYourAge = new GridBagConstraints();
		gbc_txtYourAge.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourAge.gridx = 4;
		gbc_txtYourAge.gridy = 3;
		contentPane.add(txtYourAge, gbc_txtYourAge);
		txtYourAge.setColumns(10);
		
		JLabel lblYourLocation = new JLabel("Your Location");
		GridBagConstraints gbc_lblYourLocation = new GridBagConstraints();
		gbc_lblYourLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblYourLocation.gridx = 2;
		gbc_lblYourLocation.gridy = 4;
		contentPane.add(lblYourLocation, gbc_lblYourLocation);
		
		txtYourLocation = new JTextField();
		GridBagConstraints gbc_txtYourLocation = new GridBagConstraints();
		gbc_txtYourLocation.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourLocation.gridx = 4;
		gbc_txtYourLocation.gridy = 4;
		contentPane.add(txtYourLocation, gbc_txtYourLocation);
		txtYourLocation.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setIcon(new ImageIcon(this.getClass().getResource("/kullaniciEkle.png")));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUserName.getText().equals("") || txtPassword.getText().equals("") || txtYourAge.getText().equals("") || txtYourLocation.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in the information completely.");
                } else if(existingUserName()){
                    JOptionPane.showMessageDialog(null, "Username is already in use");
                }else {
                    String sql = "INSERT INTO bx_users2( kullanici_adi, location, age, password) VALUES(?,?,?,?) ; ";
                    try {
                        connection = new BuildDB().databaseConnection(connection);

                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, txtUserName.getText());
                        preparedStatement.setString(4, txtPassword.getText());
                        preparedStatement.setString(3, txtYourAge.getText());
                        preparedStatement.setString(2, txtYourLocation.getText());
                        System.out.println(sql);
                        preparedStatement.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null,"You are now registered");

                        System.out.println("Succesful.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
   
			}
		});
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegister.gridx = 4;
		gbc_btnRegister.gridy = 5;
		contentPane.add(btnRegister, gbc_btnRegister);
		
		JLabel lblDoYouAlready = new JLabel("Do you already have an account?");
		GridBagConstraints gbc_lblDoYouAlready = new GridBagConstraints();
		gbc_lblDoYouAlready.insets = new Insets(0, 0, 0, 5);
		gbc_lblDoYouAlready.gridx = 2;
		gbc_lblDoYouAlready.gridy = 6;
		contentPane.add(lblDoYouAlready, gbc_lblDoYouAlready);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setIcon(new ImageIcon(this.getClass().getResource("/okey.png")));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmLoginPaneli logineGit=new FrmLoginPaneli();
				logineGit.main(new String[0]);
				dispose();
			}
		});
		GridBagConstraints gbc_btnSignIn = new GridBagConstraints();
		gbc_btnSignIn.gridx = 4;
		gbc_btnSignIn.gridy = 6;
		contentPane.add(btnSignIn, gbc_btnSignIn);
	}
	 private boolean existingUserName() {
	        String sql2 = "SELECT  * from bx_admin , bx_users2 where kullanici_adi = ? or userName = ? ";

	        try {
	            connection = new BuildDB().databaseConnection(connection);
	            PreparedStatement ps2 = null;
	            ps2 = connection.prepareStatement(sql2);

	            ps2.setString(1, txtUserName.getText());
	            ps2.setString(2,txtPassword.getText());
	            rs = ps2.executeQuery();
	            if (rs.next()) {
	                return true;
	            }

	        } catch (SQLException ex1) {
	            ex1.printStackTrace();
	        }
	        return false;
	    }
}
