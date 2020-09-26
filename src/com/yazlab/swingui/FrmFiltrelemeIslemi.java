package com.yazlab.swingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewController;

import com.yazlab.bll.bx_booksManager;
import com.yazlab.bll.bx_books_ratingsManager;
import com.yazlab.dalconcreate.BuildDB;
import com.yazlab.dalconcreate.RepositoryMysqlBooks;
import com.yazlab.dalconcreate.RepositoryMysqlBooksRatings;
import com.yazlab.interfaces.Ibx_booksService;
import com.yazlab.interfaces.Ibx_books_ratingsService;
import com.yazlab.models.bx_books;
import com.yazlab.models.bx_books_ratings;

public class FrmFiltrelemeIslemi extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Ibx_booksService booksService = new bx_booksManager(new RepositoryMysqlBooks());
	Ibx_books_ratingsService ratingsService = new bx_books_ratingsManager(new RepositoryMysqlBooksRatings());
	URL url;
	private String filePath;
	ListSelectionModel model;
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	int userYedekId;
	public void setUserYedekId(int userYedekId) {
		this.userYedekId = userYedekId;
	}

	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFiltrelemeIslemi frame = new FrmFiltrelemeIslemi();
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
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public FrmFiltrelemeIslemi() throws NumberFormatException, IOException {
//		userYedekId = Integer.parseInt(new FrmLoginPaneli().userIDtxtOku());
 
		setTitle("Filtreleme");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/document.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 10));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(150, 10));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

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
		
		JButton btnFotoA = new JButton("Foto a\u00E7");
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
						JLabel label = new JLabel(new ImageIcon(image));
						frame.getContentPane().add(label, BorderLayout.CENTER);
						frame.setBounds(100, 100, 250, 200);
						frame.setExtendedState(MAXIMIZED_BOTH);
						frame.pack();
						frame.setVisible(true);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Url hatasý Hata:" + e.getMessage());
					}
				}
			}
		});
		btnFotoA.setBounds(239, 93, 118, 49);
		panel_1.add(btnFotoA);

//		System.out.println( "filtreleme"+userYedekId);
//		table_1 = new JTable();
//		panel.add(table_1, BorderLayout.NORTH);
		model = table.getSelectionModel();
		JButton btnOpenAPdf = new JButton("Open a PDF File");
		btnOpenAPdf.setIcon(new ImageIcon(this.getClass().getResource("/pdf2.png")));
		btnOpenAPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (table.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Kitap seçilmedi!!");
						return;
					}

					int donenDeger = JOptionPane.showConfirmDialog(null,
							"secili kitabý okumak istediðinizden emin misiniz?", "Kitap Seçimi",
							JOptionPane.YES_NO_OPTION);
					if (donenDeger != 0)
						return;
					if (!model.isSelectionEmpty()) {

						int selectedRow = model.getMinSelectionIndex();
						String str = liste.get(table.getSelectedRow()).getBook_title();

						KitapOku(str);

//						if(selectedRow==0 || selectedRow==1 ||selectedRow==2) {
//							KitapOku();
//						}
//						else {
//							JOptionPane.showMessageDialog(null,"Pdf sistemde yüklü deðil!!","UYARI", JOptionPane.WARNING_MESSAGE);
//						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hata:" + e2.getMessage());
				}

			}
		});
		btnOpenAPdf.setBounds(239, 22, 160, 49);
		panel_1.add(btnOpenAPdf);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(27, 93, 180, 49);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

//		JSpinner spinnerOyla = new JSpinner();
//		panel_2.add(spinnerOyla);
//		spinnerOyla.setModel(new SpinnerNumberModel(0, 0, 10, 1));

		JButton btnOyla = new JButton("Oyla");
		panel_2.add(btnOyla);
		btnOyla.setIcon(new ImageIcon(this.getClass().getResource("/oyla.png")));

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(27, 22, 180, 60);
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "En Pop\u00FCler Kitaplar(10)",
				"En \u0130yi Kitaplar(10)", "Son Eklenen Kitaplar(5)" }));

		JButton btnListele = new JButton("Listele     ");
		panel_3.add(btnListele);
		btnListele.setIcon(new ImageIcon(this.getClass().getResource("/filter.png")));

		JButton btnLogOut = new JButton("Logout");
		btnLogOut.setIcon(new ImageIcon(this.getClass().getResource("/logout.png")));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnLogOut.setBounds(27, 178, 133, 37);
		panel_1.add(btnLogOut);
		
		JButton btnOneri = new JButton("\u00D6nerilen Kitaplar");
		btnOneri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				FrmOneri frmOneriGit;
				try {
					frmOneriGit = new FrmOneri();
					frmOneriGit.setLocationRelativeTo(null);// Tam ortada baslar. Ekrani ortalar
					frmOneriGit.userID=userYedekId;
					frmOneriGit.setVisible(true);
					System.out.println("filtreleme : "+userYedekId);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				FrmOneri frmOneriGit = new FrmOneri();
//				frmOneriGit.main(new String[0]);// register paneline git.
//				frmOneriGit.setLocationRelativeTo(null);
//				System.out.println( "filtreleme"+userYedekId);
//				frmOneriGit.userID=userYedekId;
////				dispose();// login ekranýný kapat
			}
		});
		btnOneri.setBounds(254, 153, 172, 55);
		panel_1.add(btnOneri);
		
		
		btnListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_1.remove(btnFotoA);
//				panel_1.revalidate();
//				panel_1.repaint();
				table.setModel(new DefaultTableModel(data, new String[] { "isbn", "book title", "book author", }) {

					boolean[] columnEditables = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				DefaultTableModel dm = (DefaultTableModel) table.getModel();
				dm.getDataVector().removeAllElements();
				table.repaint();

				int degerCombo = comboBox.getSelectedIndex();

				if (degerCombo == 0) {
					// en popüler kitaplar
					JOptionPane.showMessageDialog(null, "en popüler kitaplar");

					PopulerListele();

				} else if (degerCombo == 1) {
//					En iyi kitaplar
					JOptionPane.showMessageDialog(null, "en iyi kitaplar");

					EnKitapListele();

				} else {
//				Son Eklenen Kitaplar
					JOptionPane.showMessageDialog(null, "son eklenen kitaplar");

					Son5Listele();
				}
			}
		});
		btnOyla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FrmKitapOylamaIslemi kitapOyla;
				try {
					kitapOyla = new FrmKitapOylamaIslemi();
					kitapOyla.setLocationRelativeTo(null);// Tam ortada baslar. Ekrani ortalar
					kitapOyla.userID=userYedekId;
					System.out.println("filtreleme : "+userYedekId);
					dispose();
					kitapOyla.setVisible(true);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				try {
//
//					bx_books_ratings ekle = new bx_books_ratings();
//					ekle.setUser_id((userYedekId) + "");
//
//					int selectedRow = model.getMinSelectionIndex();
//					String str = liste.get(table.getSelectedRow()).getIsbn();
//					ekle.setIsbn(str);
//
//					ekle.setBook_rating(spinnerOyla.getValue() + "");
//					if (ratingsService.Karsilastirma(str, (userYedekId + ""))) {
//						JOptionPane.showMessageDialog(null,"Zaten daha önce oyladýnýz.");
//						return;
//					}
//					boolean sonuc = ratingsService.Kaydet(ekle);
//					int kontrol = 0;
//					String sql = "Select count(user_id) From bx_book_ratings where user_id = ?";
//					connection = new BuildDB().databaseConnection(connection);
//					ps = connection.prepareStatement(sql);
//					ps.setInt(1, userYedekId);
//					rs = ps.executeQuery();
//					if (rs.next())
//						kontrol = rs.getInt(1);
////					if (kontrol >= 10) {
////						FrmFotoGoster frmFotoGoster = new FrmFotoGoster();
////						frmFotoGoster.setLocationRelativeTo(null);
////						dispose();
////						frmFotoGoster.setVisible(true);
////					}
//
//				} catch (Exception e2) {
//					JOptionPane.showMessageDialog(null, "kayýt basarýsýz. HATA:" + e2.getMessage());
//				}
			}
		});

	}

	private void KitapOku(String str) {

		filePath = "C:\\Users\\DELL\\Desktop\\" + str + ".pdf";
		SwingController controller = new SwingController();

		SwingViewBuilder factory = new SwingViewBuilder(controller);

		JPanel viewerComponentPanel = factory.buildViewerPanel();

		controller.getDocumentViewController().setAnnotationCallback(
				new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));

		JFrame applicationFrame = new JFrame();
		applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			applicationFrame.getContentPane().add(viewerComponentPanel);
		applicationFrame.getContentPane().add(viewerComponentPanel);

		controller.openDocument(filePath);

		applicationFrame.pack();
		applicationFrame.setVisible(true);

	}

	private void PopulerListele() {

		ArrayList<bx_books> liste = booksService.PopulerListele();
		ArrayList<bx_books> liste1 = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			bx_books bx_b1 = new bx_books();
			bx_b1 = booksService.kListele(liste.get(i));
			if(!(bx_b1.getIsbn() == null ))	
			liste1.add(bx_b1);
			System.out.println(liste1);
		}
		int listDegeri = liste1.size();
		System.out.println(listDegeri);
		Object[][] data = new Object[listDegeri][];

		for (int i = 0; i < 10; i++) {
			data[i] = new Object[] { liste1.get(i).getIsbn(), liste1.get(i).getBook_title(),
					liste1.get(i).getBook_author(), };
		
	}

		

		table.setModel(new DefaultTableModel(data, new String[] { "isbn", "book title", "book author", }) {

			boolean[] columnEditables = new boolean[] { false, false, false, };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				if (!model.isSelectionEmpty()) {

					int selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(null, (selectedRow + 1) + ". kitabýn resmini açtýnýz.");
					String gelenImage = liste1.get(table.getSelectedRow()).getImage_url_l();
					try {
						url = new URL(gelenImage);
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

	private void EnKitapListele() {

		ArrayList<bx_books> liste = booksService.EnKitapListele();
		ArrayList<bx_books> liste1 = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			bx_books bx_b1 = new bx_books();
			bx_b1 = booksService.kListele(liste.get(i));
			if(!(bx_b1.getIsbn() == null ))
			liste1.add(bx_b1);
		}
		int listDegeri = liste1.size();
		Object[][] data = new Object[listDegeri][];

	

		for (int i = 0; i < 10; i++) {
			data[i] = new Object[] { liste1.get(i).getIsbn(), liste1.get(i).getBook_title(),
					liste1.get(i).getBook_author(), };
		
	}

		table.setModel(new DefaultTableModel(data, new String[] { "isbn", "book title", "book author", }) {

			boolean[] columnEditables = new boolean[] { false, false, false, };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				if (!model.isSelectionEmpty()) {

					int selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(null, (selectedRow + 1) + ". kitabýn resmini açtýnýz.");
					String gelenImage = liste1.get(table.getSelectedRow()).getImage_url_l();
					try {
						url = new URL(gelenImage);
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

	private void Son5Listele() {

		ArrayList<bx_books> liste = booksService.Son5Listele();
		ArrayList<bx_books> liste1 = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			bx_books bx_b1 = new bx_books();
			bx_b1 = booksService.kListele(liste.get(i));
			liste1.add(bx_b1);
		}
		int listDegeri = liste1.size();
		Object[][] data = new Object[listDegeri][];

		for (int i = 0; i < 5; i++) {
			data[i] = new Object[] { liste1.get(i).getIsbn(), liste1.get(i).getBook_title(),
					liste1.get(i).getBook_author(), };
		
	}
//		int j = 0;
//
//		for (int i = 0; i < 5; i++) {
//
//			if (liste1.get(i).getIsbn() != null && j < 5) {
//				data[j] = new Object[] { liste1.get(i).getIsbn(), liste1.get(i).getBook_title(),
//						liste1.get(i).getBook_author(), };
//				j++;
//			}
//		}
		table.setModel(new DefaultTableModel(data, new String[] { "isbn", "book title", "book author", }) {

			boolean[] columnEditables = new boolean[] { false, false, false, };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				if (!model.isSelectionEmpty()) {

					int selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(null, (selectedRow + 1) + ". kitabýn resmini açtýnýz.");
					String gelenImage = liste1.get(table.getSelectedRow()).getImage_url_l();
					try {
						url = new URL(gelenImage);
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
}
