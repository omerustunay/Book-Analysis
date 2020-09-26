package com.yazlab.swingui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.yazlab.bll.bx_booksManager;
import com.yazlab.dalconcreate.BuildDB;
import com.yazlab.dalconcreate.RepositoryMysqlBooks;
import com.yazlab.interfaces.Ibx_booksService;
import com.yazlab.models.NodeOneri;
import com.yazlab.models.bx_admin;
import com.yazlab.models.bx_books;
import com.yazlab.models.bx_books_ratings;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmOneri extends JFrame {

	ArrayList<Integer> deneme;
	ArrayList<bx_books> liste = new ArrayList<>();
	Connection connection = null;
	ResultSet rs = null;
	Statement stmp = null;
	int userID;

	public void setUserID(int userID) {
		this.userID = userID;
	}

	private JPanel contentPane;
	private JTable table;
	double d1Uzunluk;
	NodeOneri onerilecekUser = new NodeOneri();
	ArrayList<NodeOneri> oylayanlar = new ArrayList<NodeOneri>();
	ArrayList<String> bizimKtplr = new ArrayList<String>();
	ArrayList<bx_books_ratings> oylar = new ArrayList<>();
	Ibx_booksService booksService = new bx_booksManager(new RepositoryMysqlBooks());
	Object[][] data;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmOneri frame = new FrmOneri();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public FrmOneri() throws NumberFormatException, IOException {
//		userID = Integer.parseInt(new FrmLoginPaneli().userIDtxtOku());
//		System.out.println("onerideyim" + userID);
		

		setTitle("\u00D6neri Formu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 702, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(table);

		

		JButton btnNewButton = new JButton("listele");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
				oylananKitaplariAl();
				d1Belirle();
				bizimKitaplar();
				bizimGibiler();
				dDegerleriniAtama();
				d1UzunlukHesapla();
				System.out.println(" d1 uzunlugu " + d1UzunlukHesapla());
				dot();
				bubbleSort();
		
				ArrayList<bx_books> liste = cosListele();
				int listDegeri = liste.size();
				data = new Object[listDegeri][];

				for (int i = 0; i < data.length; i++) {
					data[i] = new Object[] { liste.get(i).getIsbn(), liste.get(i).getBook_title(),
							liste.get(i).getBook_author(), };
				}
				table.setModel(new DefaultTableModel(data, new String[] { "isbn", "book title", "book author", }) {

					boolean[] columnEditables = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				
				
				ListSelectionModel model = table.getSelectionModel();
				
				model.addListSelectionListener(new ListSelectionListener() {
		
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
		
						if (!model.isSelectionEmpty()) {
		
							int selectedRow = model.getMinSelectionIndex();
							JOptionPane.showMessageDialog(null, (selectedRow + 1) + ". kitabýn resmini açtýnýz.");
							String gelenImage = liste.get(table.getSelectedRow()).getImage_url_l();
							try {
								URL url = new URL(gelenImage);
								Image image = ImageIO.read(url);
		
								JFrame frame = new JFrame();
								JLabel label = new JLabel(new ImageIcon(image));
								frame.getContentPane().add(label, BorderLayout.CENTER);
								frame.setBounds(100, 100, 250, 200);
								frame.setLocationRelativeTo(null);// Tam ortada baslar. Ekrani ortalar
								frame.pack();
								frame.setVisible(true);
		
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Foto tarafýnda Hata: " + e.getMessage());
							}
						}
					}
				});
			
			}
		});
		contentPane.add(btnNewButton);

	
		
		


	}

	public ArrayList<bx_books_ratings> oylananKitaplariAl() {

		String sql = "SELECT * FROM bx_book_ratings \r\n"
				+ "WHERE   isbn IN (SELECT isbn  FROM bx_book_ratings where user_id = ?) and user_id<>?;";
		try {
			System.out.println("Oylanan bütün kitaplar");
			connection = (Connection) new BuildDB().databaseConnection(connection);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userID + "");
			ps.setString(2, userID + "");
			rs = ps.executeQuery();
			while (rs.next()) {
				bx_books_ratings f = new bx_books_ratings();
				f.setUser_id(rs.getString(1));
				f.setIsbn(rs.getString(2));
				f.setBook_rating(rs.getString(3));
				oylar.add(f);
				System.out.println(f.getUser_id() + " " + f.getIsbn() + " " + f.getBook_rating());
			}
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		}
		return oylar;
	}

	public void d1Belirle() {
		onerilecekUser.setUserID(userID + "");
		ArrayList<Integer> a = new ArrayList<Integer>();
		int i = 0;
		String sql = "SELECT * FROM bx_book_ratings " + "where user_id = ?;";
		try {
			connection = (Connection) new BuildDB().databaseConnection(connection);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userID + "");
			rs = ps.executeQuery();
			System.out.println("d1den gelenler");
			while (rs.next()) {
				a.add(Integer.parseInt(rs.getString(3)));
				System.out.println(a.get(i) + " " + (i + 1));
				i++;
			}
			onerilecekUser.setD(a);
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	public ArrayList<String> bizimKitaplar() {
//		ArrayList<String> bizimKtplr = new ArrayList<String>();
		System.out.println("Bizim oyladýðýmýz kitaplarýn idleri");
		String sql = "SELECT distinct isbn FROM bx_book_ratings " + "where user_id = ?;";
		try {
			connection = (Connection) new BuildDB().databaseConnection(connection);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userID + "");
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				bizimKtplr.add(rs.getString(1));
				System.out.println(bizimKtplr.get(i));
				i++;
			}
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		}

		return bizimKtplr;
	}

	public ArrayList<NodeOneri> bizimGibiler() {
		ArrayList<String> bizimGibiler = new ArrayList<String>();
		System.out.println("Bizimle ayný kitaplardan herhangi birini seçmiþ olanlar");
		String sql = "SELECT DISTINCT user_id FROM bx_book_ratings\r\n"
				+ "WHERE   isbn IN (SELECT isbn  FROM bx_book_ratings where user_id = ?) and user_id<> ?;";
		try {
			connection = (Connection) new BuildDB().databaseConnection(connection);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userID + "");
			ps.setString(2, userID + "");
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				NodeOneri n = new NodeOneri();
				n.setUserID(rs.getString(1));
				oylayanlar.add(n);
				System.out.println(n.getUserID());
				i++;
			}
		} catch (Exception sqlEx) {
			sqlEx.printStackTrace();
		}
		return oylayanlar;
	}

	public void dDegerleriniAtama() {
		System.out.println("D degerlerini atama sýrayla");
		int sayac = 0;
		for (int i = 0; i < oylayanlar.size(); i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			for (int s = 0; s < bizimKtplr.size(); s++)
				a.add(-1);
			for (int j = 0; j < oylar.size(); j++) {
				for (int k = 0; k < bizimKtplr.size(); k++) {
					if (oylayanlar.get(i).getUserID().equals(oylar.get(j).getUser_id())
							&& bizimKtplr.get(k).equals(oylar.get(j).getIsbn())) {
						// a.add(Integer.parseInt(oylar.get(j).getBook_rating()));
						a.set(k, Integer.parseInt(oylar.get(j).getBook_rating()));
						sayac++;
					}
				}

			}
			oylayanlar.get(i).setD(a);
		}

		System.out.println(sayac);

		for (int y = 0; y < oylayanlar.size(); y++) {
			for (int z = 0; z < bizimKtplr.size(); z++)
				System.out.print(oylayanlar.get(y).getD().get(z) + " ");
			System.out.println();
		}

	}

	public double d1UzunlukHesapla() {
		for (int j = 0; j < bizimKtplr.size(); j++) {
			d1Uzunluk += ((onerilecekUser.getD().get(j)) * onerilecekUser.getD().get(j));

		}
		d1Uzunluk = Math.sqrt(d1Uzunluk);
		return d1Uzunluk;
	}

	public void dot() {

		double dot = 0;// pay --> dot
		double payda;
		double d2Uzunluk = 0;

//		-->dot pay 
//		-->d1Uzunluk*d2Uzunluk

		// pay ve payda hesabý

		for (int i = 0; i < oylayanlar.size(); i++) {
			for (int j = 0; j < bizimKtplr.size(); j++) {
				if (oylayanlar.get(i).getD().get(j) != -1) {

					// Pay hesabý
					dot += (onerilecekUser.getD().get(j)) * (oylayanlar.get(i).getD().get(j));
					d2Uzunluk += ((oylayanlar.get(i).getD().get(j)) * (oylayanlar.get(i).getD().get(j)));

				}
			}
			System.out.println("dot: 	" + dot);
			d2Uzunluk = Math.sqrt(d2Uzunluk);
			System.out.println("d2 uzunlugu 	" + d2Uzunluk);

//			0/0 belirsizlik matematik hesabý iþlemini hesaplatmadýk.

			if (d1Uzunluk != 0 && d2Uzunluk != 0) {

				oylayanlar.get(i).setCosDegeri(dot / (d1Uzunluk * d2Uzunluk));
				System.out.println(
						"id :" + oylayanlar.get(i).getUserID() + "cos degeri  : " + oylayanlar.get(i).getCosDegeri());

			}
			dot = 0;
			d2Uzunluk = 0;
		}

	}

	public void bubbleSort() {
		NodeOneri temp = new NodeOneri();
		NodeOneri temp2 = new NodeOneri();

		int i, j;

		for (i = 1; i < oylayanlar.size(); i++) {
			for (j = 0; j < oylayanlar.size() - i; j++) {
				if (oylayanlar.get(j).getCosDegeri() < oylayanlar.get(j + 1).getCosDegeri()) {
					temp = oylayanlar.get(j);
					temp2 = oylayanlar.get(j + 1);
					oylayanlar.set(j, temp2);
					oylayanlar.set(j + 1, temp);

				}
			}
		}

		System.out.println("COS SIRALI DEGERLERÝ");
		for (i = 1; i < oylayanlar.size(); i++) {
			System.out.println(oylayanlar.get(i).getCosDegeri());
		}

	}

	private ArrayList<bx_books> cosListele() {
		try {
			// System.out.println("user idmiz " + userID);
			for (int i = 0; i < 10; i++) {
				String sql = "SELECT * FROM bx_books \r\n"
						+ "WHERE   isbn not IN (SELECT isbn  FROM bx_book_ratings where user_id = ?)"
						+ " and isbn in (select isbn from bx_book_ratings where user_id = ?)  ;";

				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, userID + "");
				ps.setString(2, oylayanlar.get(i).getUserID());
				rs = ps.executeQuery();

				while (rs.next()) {

					bx_books f = new bx_books();
					f.setIsbn(rs.getString(1));
					f.setBook_title(rs.getString(2));
					f.setBook_author(rs.getString(3));
					f.setImage_url_l(rs.getString(8));
					System.out.println(oylayanlar.get(i).getUserID() + " ---- " + f.getBook_author());
					liste.add(f);

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "listeleme hatasý hata:" + e.getMessage());
		}

		return liste;
	}

}
