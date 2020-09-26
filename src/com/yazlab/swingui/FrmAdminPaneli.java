package com.yazlab.swingui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.yazlab.bll.bx_adminManager;
import com.yazlab.bll.bx_booksManager;
import com.yazlab.bll.bx_usersManager;
import com.yazlab.dalconcreate.BuildDB;
import com.yazlab.dalconcreate.RepositoryMysqlAdmin;
import com.yazlab.dalconcreate.RepositoryMysqlBooks;
import com.yazlab.dalconcreate.RepositoryMysqlUsers;
import com.yazlab.interfaces.Ibx_adminService;
import com.yazlab.interfaces.Ibx_booksService;
import com.yazlab.interfaces.Ibx_usersService;
import com.yazlab.models.bx_admin;
import com.yazlab.models.bx_books;
import com.yazlab.models.bx_users;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

public class FrmAdminPaneli extends JFrame {

	/**
	 * 
	 */
	Ibx_usersService usersService = new bx_usersManager(new RepositoryMysqlUsers());
	Ibx_booksService booksService = new bx_booksManager(new RepositoryMysqlBooks());
	Ibx_adminService adminService = new bx_adminManager(new RepositoryMysqlAdmin());

	private JPanel contentPane;
	private JTable DataListeler;
	private JTextField txtIsbn;
	private JTextField txtBookTitle;
	private JTextField txtBookAuthor;
	private JTextField txtYearOfPublication;
	private JTextField txtPublisher;
	private JTextField txtImageUrlS;
	private JTextField txtImageUrlM;
	private JTextField txtImageUrlL;
	private JTable table;
	private JTextField usernameInput;
	private JTextField ageInput;
	private JPasswordField passwordInput;
	private JTextField locationInput;
	Connection connection = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	private JTable table_1;
	private JTextField txtBul;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAdminPaneli frame = new FrmAdminPaneli();
					frame.setLocationRelativeTo(null);// Tam ortada baslar. Ekrani ortalar
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
	public FrmAdminPaneli() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/admn.png")));
		setTitle("Admin Formu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel AdminIslemleri = new JPanel();
		AdminIslemleri.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		AdminIslemleri.setToolTipText("");
		tabbedPane.addTab("Admin \u0130\u015Flemleri", null, AdminIslemleri, null);
		AdminIslemleri.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(230, 10));
		AdminIslemleri.add(panel_2, BorderLayout.WEST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 63, 0, 99, 40, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 55, 29, 66, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel_8 = new JLabel("username");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 1;
		panel_2.add(lblNewLabel_8, gbc_lblNewLabel_8);

		usernameInput = new JTextField();
		GridBagConstraints gbc_usernameInput = new GridBagConstraints();
		gbc_usernameInput.gridwidth = 2;
		gbc_usernameInput.insets = new Insets(0, 0, 5, 0);
		gbc_usernameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameInput.gridx = 2;
		gbc_usernameInput.gridy = 1;
		panel_2.add(usernameInput, gbc_usernameInput);
		usernameInput.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("password");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.gridwidth = 2;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 2;
		panel_2.add(lblNewLabel_9, gbc_lblNewLabel_9);

		passwordInput = new JPasswordField();
		GridBagConstraints gbc_passwordInput = new GridBagConstraints();
		gbc_passwordInput.gridwidth = 2;
		gbc_passwordInput.insets = new Insets(0, 0, 5, 0);
		gbc_passwordInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordInput.gridx = 2;
		gbc_passwordInput.gridy = 2;
		panel_2.add(passwordInput, gbc_passwordInput);

		JLabel lblAge = new JLabel("age");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.gridwidth = 2;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 0;
		gbc_lblAge.gridy = 3;
		panel_2.add(lblAge, gbc_lblAge);

		ageInput = new JTextField();
		GridBagConstraints gbc_ageInput = new GridBagConstraints();
		gbc_ageInput.gridwidth = 2;
		gbc_ageInput.insets = new Insets(0, 0, 5, 0);
		gbc_ageInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageInput.gridx = 2;
		gbc_ageInput.gridy = 3;
		panel_2.add(ageInput, gbc_ageInput);
		ageInput.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("location");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.gridwidth = 2;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 4;
		panel_2.add(lblNewLabel_10, gbc_lblNewLabel_10);

		locationInput = new JTextField();
		GridBagConstraints gbc_locationInput = new GridBagConstraints();
		gbc_locationInput.gridwidth = 2;
		gbc_locationInput.insets = new Insets(0, 0, 5, 0);
		gbc_locationInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationInput.gridx = 2;
		gbc_locationInput.gridy = 4;
		panel_2.add(locationInput, gbc_locationInput);
		locationInput.setColumns(10);

		JButton btnEkle_2 = new JButton("Ekle");
		btnEkle_2.setIcon(new ImageIcon(this.getClass().getResource("/docplus.png")));
		btnEkle_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					bx_admin ekle = new bx_admin();
					ekle.setUserName(usernameInput.getText());
					ekle.setPassword(passwordInput.getText());
					ekle.setAge(ageInput.getText());
					ekle.setLocation(locationInput.getText());

					if (usernameInput.getText().equals("") || passwordInput.getText().equals("")
							|| ageInput.getText().equals("") || locationInput.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Boþ býrakmayýnýz!");
					}

					else if (Tekrar1()) {
						JOptionPane.showMessageDialog(null, "kullanýcý adý kullanýlmakta!!");
						AdminListele();
					}

					else {
						boolean sonuc = adminService.Kaydet(ekle);

						if (sonuc) {
							JOptionPane.showMessageDialog(null, "kayýt baþarýlý");
							usernameInput.setText("");
							passwordInput.setText("");
							ageInput.setText("");
							locationInput.setText("");

						}
						AdminListele();

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "kayýt basarýsýz. HATA:" + e2.getMessage());
				}
			}

		});

		GridBagConstraints gbc_btnEkle_2 = new GridBagConstraints();
		gbc_btnEkle_2.gridwidth = 2;
		gbc_btnEkle_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEkle_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnEkle_2.gridx = 2;
		gbc_btnEkle_2.gridy = 6;
		panel_2.add(btnEkle_2, gbc_btnEkle_2);

		JButton btnListele_2 = new JButton("Listele");
		btnListele_2.setIcon(new ImageIcon(this.getClass().getResource("/listele.png")));
		btnListele_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdminListele();
			}
		});
		GridBagConstraints gbc_btnListele_2 = new GridBagConstraints();
		gbc_btnListele_2.gridwidth = 2;
		gbc_btnListele_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListele_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnListele_2.gridx = 2;
		gbc_btnListele_2.gridy = 7;
		panel_2.add(btnListele_2, gbc_btnListele_2);

		JButton btnSil_2 = new JButton("Sil");
		btnSil_2.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		btnSil_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (table_1.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "kullanýcý seçilmedi!!");
						return;

					}
					int donenDeger = JOptionPane.showConfirmDialog(null, "secili kaydý silmek emin misiniz?");
					if (donenDeger != 0)
						return;

					int id = Integer.parseInt(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
					if (adminService.Sil(id)) {
						AdminListele();
						JOptionPane.showMessageDialog(null, "kayit silindi");
					} else {
						JOptionPane.showMessageDialog(null, "kayit silinemedi");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "buradayým. Hata:" + e2.getMessage());
				}

			}

		});
		GridBagConstraints gbc_btnSil_2 = new GridBagConstraints();
		gbc_btnSil_2.gridwidth = 2;
		gbc_btnSil_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSil_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnSil_2.gridx = 2;
		gbc_btnSil_2.gridy = 8;
		panel_2.add(btnSil_2, gbc_btnSil_2);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.gridwidth = 8;
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 12;
		panel_2.add(panel_8, gbc_panel_8);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setBounds(0, 0, 115, 66);
		panel_8.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/ustLogin.png")));
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setBounds(114, 0, 116, 66);
		panel_8.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(this.getClass().getResource("/logout.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			dispose();
			
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
				FrmLoginPaneli loginGit=new FrmLoginPaneli();
				loginGit.setLocationRelativeTo(null);
				loginGit.main(new String[0]);
				dispose();
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(355, 10));
		AdminIslemleri.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 128, 36, 0, 102, 0 };
		gbl_panel_3.rowHeights = new int[] { 357, 0, 17, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 4;
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_3.add(scrollPane_1, gbc_scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JPanel KitapIslemleri = new JPanel();
		tabbedPane.addTab("Kitap \u0130\u015Flemleri", null, KitapIslemleri, null);
		KitapIslemleri.setLayout(new BorderLayout(0, 0));

		JPanel FrmKitap = new JPanel();
		KitapIslemleri.add(FrmKitap, BorderLayout.CENTER);
		FrmKitap.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 10));
		FrmKitap.add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 114, 228, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblIsbn = new JLabel("isbn");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 1;
		panel.add(lblIsbn, gbc_lblIsbn);

		txtIsbn = new JTextField();
		GridBagConstraints gbc_txtIsbn = new GridBagConstraints();
		gbc_txtIsbn.insets = new Insets(0, 0, 5, 0);
		gbc_txtIsbn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIsbn.gridx = 1;
		gbc_txtIsbn.gridy = 1;
		panel.add(txtIsbn, gbc_txtIsbn);
		txtIsbn.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Book Title");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtBookTitle = new JTextField();
		GridBagConstraints gbc_txtBookTitle = new GridBagConstraints();
		gbc_txtBookTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtBookTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBookTitle.gridx = 1;
		gbc_txtBookTitle.gridy = 2;
		panel.add(txtBookTitle, gbc_txtBookTitle);
		txtBookTitle.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Book Author");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		txtBookAuthor = new JTextField();
		GridBagConstraints gbc_txtBookAuthor = new GridBagConstraints();
		gbc_txtBookAuthor.insets = new Insets(0, 0, 5, 0);
		gbc_txtBookAuthor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBookAuthor.gridx = 1;
		gbc_txtBookAuthor.gridy = 3;
		panel.add(txtBookAuthor, gbc_txtBookAuthor);
		txtBookAuthor.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Year of Publication");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 4;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		txtYearOfPublication = new JTextField();
		GridBagConstraints gbc_txtYearOfPublication = new GridBagConstraints();
		gbc_txtYearOfPublication.insets = new Insets(0, 0, 5, 0);
		gbc_txtYearOfPublication.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYearOfPublication.gridx = 1;
		gbc_txtYearOfPublication.gridy = 4;
		panel.add(txtYearOfPublication, gbc_txtYearOfPublication);
		txtYearOfPublication.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Publisher");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 5;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		txtPublisher = new JTextField();
		GridBagConstraints gbc_txtPublisher = new GridBagConstraints();
		gbc_txtPublisher.insets = new Insets(0, 0, 5, 0);
		gbc_txtPublisher.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPublisher.gridx = 1;
		gbc_txtPublisher.gridy = 5;
		panel.add(txtPublisher, gbc_txtPublisher);
		txtPublisher.setColumns(10);

		JLabel lblImageUrlS = new JLabel("\u0130mage Url S");
		GridBagConstraints gbc_lblImageUrlS = new GridBagConstraints();
		gbc_lblImageUrlS.insets = new Insets(0, 0, 5, 5);
		gbc_lblImageUrlS.gridx = 0;
		gbc_lblImageUrlS.gridy = 6;
		panel.add(lblImageUrlS, gbc_lblImageUrlS);

		txtImageUrlS = new JTextField();
		GridBagConstraints gbc_txtImageUrlS = new GridBagConstraints();
		gbc_txtImageUrlS.insets = new Insets(0, 0, 5, 0);
		gbc_txtImageUrlS.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtImageUrlS.gridx = 1;
		gbc_txtImageUrlS.gridy = 6;
		panel.add(txtImageUrlS, gbc_txtImageUrlS);
		txtImageUrlS.setColumns(10);

		JLabel lblImageUrlM = new JLabel("\u0130mage Url M");
		GridBagConstraints gbc_lblImageUrlM = new GridBagConstraints();
		gbc_lblImageUrlM.insets = new Insets(0, 0, 5, 5);
		gbc_lblImageUrlM.gridx = 0;
		gbc_lblImageUrlM.gridy = 7;
		panel.add(lblImageUrlM, gbc_lblImageUrlM);

		txtImageUrlM = new JTextField();
		GridBagConstraints gbc_txtImageUrlM = new GridBagConstraints();
		gbc_txtImageUrlM.insets = new Insets(0, 0, 5, 0);
		gbc_txtImageUrlM.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtImageUrlM.gridx = 1;
		gbc_txtImageUrlM.gridy = 7;
		panel.add(txtImageUrlM, gbc_txtImageUrlM);
		txtImageUrlM.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("\u0130mage Url L");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);

		txtImageUrlL = new JTextField();
		GridBagConstraints gbc_txtImageUrlL = new GridBagConstraints();
		gbc_txtImageUrlL.insets = new Insets(0, 0, 5, 0);
		gbc_txtImageUrlL.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtImageUrlL.gridx = 1;
		gbc_txtImageUrlL.gridy = 8;
		panel.add(txtImageUrlL, gbc_txtImageUrlL);
		txtImageUrlL.setColumns(10);

		JButton btnEkle_1 = new JButton("Ekle");
		btnEkle_1.setIcon(new ImageIcon(this.getClass().getResource("/docplus.png")));
		btnEkle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bx_books ekle = new bx_books();
					ekle.setIsbn(txtIsbn.getText());
					ekle.setBook_title(txtBookTitle.getText());
					ekle.setBook_author(txtBookAuthor.getText());
					ekle.setYear_of_publication(txtYearOfPublication.getText());
					ekle.setPublisher(txtPublisher.getText());
					ekle.setImage_url_s(txtImageUrlS.getText());
					ekle.setImage_url_m(txtImageUrlM.getText());
					ekle.setImage_url_l(txtImageUrlL.getText());
					if (txtIsbn.getText().equals("") || txtBookAuthor.getText().equals("")
							|| txtBookTitle.getText().equals("") || txtPublisher.getText().equals("")
							|| txtYearOfPublication.getText().equals("") || txtImageUrlS.getText().equals("")
							|| txtImageUrlM.getText().equals("") || txtImageUrlL.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "Boþ býrakmayýnýz!");
					}

					else if (Tekrar()) {
						JOptionPane.showMessageDialog(null, "ayný kitap veritabanýnda bulunmakta.");
						KitapListele();
					}

					else {
						boolean sonuc = booksService.Kaydet(ekle);

						if (sonuc) {
							JOptionPane.showMessageDialog(null, "kayýt baþarýlý");
							txtIsbn.setText("");
							txtBookTitle.setText("");
							txtBookAuthor.setText("");
							txtYearOfPublication.setText("");
							txtPublisher.setText("");
							txtImageUrlS.setText("");
							txtImageUrlM.setText("");
							txtImageUrlL.setText("");
						}
						KitapListele();

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "kayýt basarýsýz. HATA:" + e2.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnEkle_1 = new GridBagConstraints();
		gbc_btnEkle_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEkle_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnEkle_1.gridx = 1;
		gbc_btnEkle_1.gridy = 9;
		panel.add(btnEkle_1, gbc_btnEkle_1);

		JButton btnListele_1 = new JButton("Listele");
		btnListele_1.setIcon(new ImageIcon(this.getClass().getResource("/listele.png")));
		btnListele_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				KitapListele();
			}
		});
		GridBagConstraints gbc_btnListele_1 = new GridBagConstraints();
		gbc_btnListele_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListele_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnListele_1.gridx = 1;
		gbc_btnListele_1.gridy = 10;
		panel.add(btnListele_1, gbc_btnListele_1);

		JButton btnSil_1 = new JButton("Sil");
		btnSil_1.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		btnSil_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (table.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "kitap seçilmedi!!");
						return;

					}
					int donenDeger = JOptionPane.showConfirmDialog(null, "secili kaydý silmek emin misiniz?");
					if (donenDeger != 0)
						return;

					int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					if (booksService.Sil(id)) {
						KitapListele();
						JOptionPane.showMessageDialog(null, "kayit silindi");
					} else {
						JOptionPane.showMessageDialog(null, "kayit silinemedi");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hata:" + e2.getMessage());
				}

			}
		});
		GridBagConstraints gbc_btnSil_1 = new GridBagConstraints();
		gbc_btnSil_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnSil_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSil_1.gridx = 1;
		gbc_btnSil_1.gridy = 11;
		panel.add(btnSil_1, gbc_btnSil_1);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 14;
		panel.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Login Page");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/ustLogin.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmLoginPaneli loginGit=new FrmLoginPaneli();
				loginGit.setLocationRelativeTo(null);
				loginGit.main(new String[0]);
				dispose();
			}
		});
		panel_5.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.setIcon(new ImageIcon(this.getClass().getResource("/logout.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_5.add(btnNewButton_3);

		JPanel panel_1 = new JPanel();
		FrmKitap.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(10, 30));
		panel_1.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		txtBul = new JTextField();
		panel_7.add(txtBul);
		txtBul.setColumns(10);
		
		JButton btnBul = new JButton("  bul");
		btnBul.setIcon(new ImageIcon(this.getClass().getResource("/bul.png")));
		panel_7.add(btnBul);
		btnBul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					bul();
		
			}
		});

		JPanel KullaniciIslemleri = new JPanel();
		tabbedPane.addTab("Kullan\u0131c\u0131 \u0130\u015Flemleri", null, KullaniciIslemleri, null);
		KullaniciIslemleri.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel FrmKullanici = new JPanel();
		KullaniciIslemleri.add(FrmKullanici);
		FrmKullanici.setLayout(new BorderLayout(0, 0));

		JPanel islem = new JPanel();
		islem.setPreferredSize(new Dimension(250, 10));
		FrmKullanici.add(islem, BorderLayout.WEST);
		GridBagLayout gbl_islem = new GridBagLayout();
		gbl_islem.columnWidths = new int[] { 61, 0, 120, 0, 0 };
		gbl_islem.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 61, 31, 0, 0, 0, 0 };
		gbl_islem.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_islem.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		islem.setLayout(gbl_islem);

		JButton btnListele = new JButton("Listele");
		btnListele.setIcon(new ImageIcon(this.getClass().getResource("/listele.png")));
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				KullaniciListele();
			}
		});
		GridBagConstraints gbc_btnListele = new GridBagConstraints();
		gbc_btnListele.insets = new Insets(0, 0, 5, 5);
		gbc_btnListele.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnListele.gridx = 2;
		gbc_btnListele.gridy = 6;
		islem.add(btnListele, gbc_btnListele);

		JButton btnSil = new JButton("Sil");
		btnSil.setIcon(new ImageIcon(this.getClass().getResource("/delete.png")));
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (DataListeler.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "kullanýcý seçilmedi!!");
						return;

					}
					int donenDeger = JOptionPane.showConfirmDialog(null, "secili kaydý silmek emin misiniz?");
					if (donenDeger != 0)
						return;

					int id = Integer.parseInt(DataListeler.getValueAt(DataListeler.getSelectedRow(), 0).toString());
					if (usersService.Sil(id)) {
						KullaniciListele();
						JOptionPane.showMessageDialog(null, "kayit silindi");
					} else {
						JOptionPane.showMessageDialog(null, "kayit silinemedi");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hata:" + e2.getMessage());
				}

			}
		});
		GridBagConstraints gbc_btnSil = new GridBagConstraints();
		gbc_btnSil.insets = new Insets(0, 0, 5, 5);
		gbc_btnSil.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSil.gridx = 2;
		gbc_btnSil.gridy = 7;
		islem.add(btnSil, gbc_btnSil);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridheight = 3;
		gbc_panel_4.gridwidth = 6;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 10;
		islem.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JButton btnNewButton_5 = new JButton("Login");
		panel_4.add(btnNewButton_5);
		btnNewButton_5.setIcon(new ImageIcon(this.getClass().getResource("/ustLogin.png")));
		
		JButton btnNewButton_4 = new JButton("Logout");
		panel_4.add(btnNewButton_4);
		btnNewButton_4.setIcon(new ImageIcon(this.getClass().getResource("/logout.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				dispose();
			
			}
		});
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmLoginPaneli loginGit=new FrmLoginPaneli();
				loginGit.setLocationRelativeTo(null);
				loginGit.main(new String[0]);
				dispose();
			}
		});

		JPanel DataListele = new JPanel();
		FrmKullanici.add(DataListele, BorderLayout.CENTER);
		DataListele.setLayout(new BorderLayout(0, 0));

		JScrollPane Data = new JScrollPane();
		DataListele.add(Data, BorderLayout.CENTER);

		DataListeler = new JTable();

		Data.setViewportView(DataListeler);
	}

	private void KullaniciListele() {

		ArrayList<bx_users> liste = usersService.Listele();
		int listDegeri = liste.size();
		Object[][] data = new Object[listDegeri][];

		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[] { liste.get(i).getUser_id(), liste.get(i).getKullanici_adi(),
					liste.get(i).getLocation(), liste.get(i).getAge(), };
		}

		DataListeler
				.setModel(new DefaultTableModel(data, new String[] { "user_id", "kullanici_adi", "location", "age", }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});

	}

	private void KitapListele() {

		ArrayList<bx_books> liste = booksService.Listele();
		int listDegeri = liste.size();
		Object[][] data = new Object[listDegeri][];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[] { liste.get(i).getIsbn(), liste.get(i).getBook_title(),
					liste.get(i).getBook_author(), liste.get(i).getYear_of_publication(), liste.get(i).getPublisher(),
					liste.get(i).getImage_url_s(), liste.get(i).getImage_url_m(), liste.get(i).getImage_url_l(), };
		}

		table.setModel(new DefaultTableModel(data, new String[] { "isbn", "book title", "book author",
				"year of publication", "publisher", "s", "m", "l", }) {

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		

	}

	private void AdminListele() {

		ArrayList<bx_admin> liste = adminService.Listele();
		int listDegeri = liste.size();
		Object[][] data = new Object[listDegeri][];

		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[] { liste.get(i).getAdminID(), liste.get(i).getUserName(), liste.get(i).getAge(),
					liste.get(i).getLocation(), };
		}

		table_1.setModel(new DefaultTableModel(data, new String[] { "id", "username", "age", "location", }) {

			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	}

	private boolean Tekrar() {

		String sql2 = "SELECT  * from bx_books where book_title = ? or year_of_publication = ? ";

		try {
			connection = new BuildDB().databaseConnection(connection);
			PreparedStatement ps2 = null;
			ps2 = connection.prepareStatement(sql2);

			ps2.setString(1, txtBookTitle.getText());
			ps2.setString(2, txtYearOfPublication.getText());
			rs = ps2.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException ex1) {
			ex1.printStackTrace();
		}
		return false;

	}

	private boolean Tekrar1() {

		String sql2 = "SELECT  * from bx_admin where userName = ?  ";

		try {
			connection = new BuildDB().databaseConnection(connection);
			PreparedStatement ps2 = null;
			ps2 = connection.prepareStatement(sql2);

			ps2.setString(1, usernameInput.getText());
			rs = ps2.executeQuery();
			
			if (rs.next()) {
				return true;
			}

		} catch (SQLException ex1) {
			ex1.printStackTrace();
		}
		return false;
	}
	
	private boolean bul() {
		String sql="select * from bx_books where book_title=? ";
		try {
			
			connection = new BuildDB().databaseConnection(connection);
			PreparedStatement ps2 = null;
			ps2 = connection.prepareStatement(sql);

			ps2.setString(1, txtBul.getText());
			rs = ps2.executeQuery();
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hata:"+e.getMessage());
		}
		
		return false;
	}
	
}
