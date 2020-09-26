package com.yazlab.swingui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.yazlab.bll.bx_booksManager;
import com.yazlab.bll.bx_books_ratingsManager;
import com.yazlab.dalconcreate.BuildDB;
import com.yazlab.dalconcreate.RepositoryMysqlBooks;
import com.yazlab.dalconcreate.RepositoryMysqlBooksRatings;
import com.yazlab.interfaces.Ibx_booksService;
import com.yazlab.interfaces.Ibx_books_ratingsService;
import com.yazlab.models.bx_admin;
import com.yazlab.models.bx_books;
import com.yazlab.models.bx_books_ratings;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class FrmKitapOylamaIslemi extends JFrame {

	int userID;
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	private JPanel contentPane;
	private JTable table;
	Ibx_booksService booksService = new bx_booksManager(new RepositoryMysqlBooks());
	Ibx_books_ratingsService ratingsService = new bx_books_ratingsManager(new RepositoryMysqlBooksRatings());
	ListSelectionModel model;
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private JTextField txtBul;
	URL url;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmKitapOylamaIslemi frame = new FrmKitapOylamaIslemi();
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
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public FrmKitapOylamaIslemi() throws NumberFormatException, IOException {
//		userID = Integer.parseInt(new FrmLoginPaneli().userIDtxtOku());

//		System.out.println("oylamadayým " +userID );
		setTitle("Kitap Oylama Page");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/oylamaYapma.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 30));
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		txtBul = new JTextField();
		panel_3.add(txtBul);
		txtBul.setColumns(10);

		JButton btnBul = new JButton("  Bul        ");
		btnBul.setIcon(new ImageIcon(this.getClass().getResource("/bul.png")));
		btnBul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Bul();
			}
		});
		panel_3.add(btnBul);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		ArrayList<bx_books> liste = booksService.Listele();
		int listDegeri = liste.size();
		Object[][] data = new Object[listDegeri][];

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

		model = table.getSelectionModel();
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(49, 100, 106, 57);
		panel_2.add(spinner);
		spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));

		JButton btnOyla = new JButton("Oyla");
		btnOyla.setBounds(156, 100, 106, 57);
		btnOyla.setIcon(new ImageIcon(this.getClass().getResource("/oyla.png")));
		panel_2.add(btnOyla);
		
		JButton btnFotoA = new JButton("Foto A\u00E7");
		btnFotoA.setIcon(new ImageIcon(this.getClass().getResource("/Photos.png")));
		btnFotoA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				model = table.getSelectionModel();
				if (!model.isSelectionEmpty()) {

					int selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(null, (selectedRow + 1) + ". kitabýn resmini açtýnýz.");
					String gelenImage = liste.get(table.getSelectedRow()).getImage_url_l();
					try {
						url = new URL(gelenImage);
						Image image = ImageIO.read(url);

						JFrame frame = new JFrame();
//						label = new JLabel(new ImageIcon(image));
						JLabel label=new JLabel(new ImageIcon(image));
//						label.setBounds(401, 50, 118, 142);
//						panel_1.add(label);
						frame.getContentPane().add(label, BorderLayout.CENTER);
						frame.setBounds(100, 100, 250, 200);
						frame.setExtendedState(MAXIMIZED_BOTH);
						frame.pack();
						frame.setVisible(true);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Url hatasý Hata:"+e.getMessage());
					}
				}
			
			}
		});
		btnFotoA.setBounds(86, 26, 176, 33);
		panel_2.add(btnFotoA);
		btnOyla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					bx_books_ratings ekle = new bx_books_ratings();
					ekle.setUser_id((userID) + "");

					int selectedRow = model.getMinSelectionIndex();
					String str = liste.get(table.getSelectedRow()).getIsbn();
					ekle.setIsbn(str);

					ekle.setBook_rating(spinner.getValue() + "");
					if (ratingsService.Karsilastirma(str, (userID + ""))) {
//						JOptionPane.showMessageDialog(null,"Zaten daha önce oyladýnýz.");
						return;
					}
					boolean sonuc = ratingsService.Kaydet(ekle);
					int kontrol = 0;
					String sql = "Select count(user_id) From bx_book_ratings where user_id = ?";
					connection = new BuildDB().databaseConnection(connection);
					ps = connection.prepareStatement(sql);
					ps.setInt(1, userID);
					rs = ps.executeQuery();
					if (rs.next())
						kontrol = rs.getInt(1);
					if (kontrol >= 10) {
						
						
						JOptionPane.showMessageDialog(null, "Tebrikler oyladýnýz . Ana sayfaya geçmek ister misiniz?"
								+ "");
						int gelenDeger=JOptionPane.showConfirmDialog(null, "tekrar oylama yapmak ister misiniz?","bilgilendirme",JOptionPane.YES_NO_OPTION);
						
						if(gelenDeger!=0) {
							FrmFiltrelemeIslemi frmFotoGoster = new FrmFiltrelemeIslemi();
							frmFotoGoster.userYedekId=userID;
							dispose();
							frmFotoGoster.setUserYedekId(userID);
							frmFotoGoster.setLocationRelativeTo(null);
							frmFotoGoster.setVisible(true);
							System.out.println("oylamadayým: "+userID);
						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "oylama basarýsýz. HATA:" + e2.getMessage());
				}

			}
		});

	}

	private void Bul() {
		String sql = "select * from bx_books where book_title=? ";
		try {

			connection = new BuildDB().databaseConnection(connection);
			PreparedStatement ps2 = null;
			ps2 = connection.prepareStatement(sql);

			ps2.setString(1, txtBul.getText());
			rs = ps2.executeQuery();
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "bul fonkisyonunda Hata:" + e.getMessage());
		}
	}
}
