package main.java.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import main.java.main.Datei;
import main.java.main.DateiList;
import main.java.main.Funktion;
import main.java.main.XML_Editor;
import main.java.main.fileWriter;


import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;

import java.awt.FlowLayout;

import java.awt.Toolkit;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;


import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.JTabbedPane;
import javax.swing.JSpinner;

import javax.swing.JCheckBox;

public class GUI extends JFrame {


	
	/**
	 * 
	 */

	private JPanel contentPane;
	private JTable table;
	private JTextField tfd_pname;
	private JTextField tfd_pfad;
	private JTextField tfd_vname;
	private JTextField tfd_version;
	private String msg;
	private String prepfad;
	private JTextField tfd_prepfad;
	private Logger logger = LoggerFactory.getLogger(GUI.class);
	private JSpinner spinner = new JSpinner();
	private DateiList datalist = new DateiList();
	private JCheckBox CBX_pfadErzeugen = new JCheckBox("Pfad erzeugen");
	private JSpinner spin_del = new JSpinner();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI("");
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
	public GUI(String Msg) {
		setResizable(false);
		setTitle("XML-Updater");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 351);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		try {
			datalist.load();
		} catch (IOException e1) {
			logger.error("Fehler: ", e1);
		}

		table = new JTable(showList(datalist));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(600);
		table.getColumnModel().getColumn(3)
				.setHeaderRenderer(new HorizontalAlignmentHeaderRenderer(SwingConstants.LEFT));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		table.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Versionen", null, panel, null);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("Wahl");
		label_1.setBounds(116, 37, 39, 14);
		panel.add(label_1);

	
		spinner.setBounds(153, 34, 29, 20);
		spinner.setValue(-1);
		panel.add(spinner);

		JLabel label_2 = new JLabel("Version:");
		label_2.setBounds(186, 37, 52, 14);
		panel.add(label_2);

		tfd_version = new JTextField();
		tfd_version.setBounds(243, 34, 86, 20);
		tfd_version.setColumns(10);
		panel.add(tfd_version);

		JButton button_2 = new JButton("Updaten");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					spinner.commitEdit();
				} catch (java.text.ParseException e2) {

				}
				String version = tfd_version.getText();
				int select = (Integer) spinner.getValue();

				if (select < 0 || select > datalist.getFileList().size()) {
					
					if (select ==-1) {
						msg = "Bitte wähle eine Nummer!";
					}else{
						msg = "Nummer existiert nicht!";
					}
					
					
				} else {
					if (!version.equals("")) {
						try {

							if (XML_Editor.update(datalist.getFileList().get(select), version)) {
								msg = "Version von Nummer " + select + " angepasst!";
							} else {
								msg = "ERROR: Version von Nummer " + select + " nicht angepasst!";
							}

							spinner.setValue(-1);
						} catch (Exception e2) {
							logger.error("Fehler: ", e2);
						}

						Funktion.showList(datalist);
						tfd_version.setText("");
					} else {
						msg = "Bitte gebe eine Version ein!";

					}
				}
				dispose();
				GUI frame = new GUI(msg);
				frame.setVisible(true);
				msg = "";

			}
		});
		button_2.setBounds(334, 33, 105, 23);
		panel.add(button_2);

		JButton button_3 = new JButton("Alle updaten");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String version = tfd_version.getText();
				if (!version.equals("")) {

					if (Funktion.updateAll(datalist, version)) {
						msg = "Alle Versionen Angepasst";
					} else {
						msg = "ERROR: Nicht alle Versionen wurden Angepasst!";
					}

				} else {
					msg = "Bitte gebe eine Version ein!";

				}
				tfd_version.setText("");
				dispose();

				GUI frame = new GUI(msg);
				frame.setVisible(true);
				msg = "";

			}
		});
		button_3.setBounds(449, 34, 130, 23);
		panel.add(button_3);

		JLabel lbl_Msg = new JLabel(Msg);
		lbl_Msg.setForeground(new Color(255, 0, 0));
		lbl_Msg.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Msg.setBounds(116, 11, 463, 14);
		panel.add(lbl_Msg);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Dateipfade", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));

		JLabel label = new JLabel("Wahl");
		panel_2.add(label);

		
		panel_2.add(spin_del);
		spin_del.setValue(-1);
		JButton button = new JButton("L\u00F6schen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					spin_del.commitEdit();
				} catch (java.text.ParseException e2) {

				}

				int zahl = (Integer) spin_del.getValue();
				if (zahl < 0 || zahl > datalist.getFileList().size()) {
					if (zahl ==-1) {
						msg = "Bitte wähle eine Nummer!";
					}else{
						msg = "Nummer existiert nicht!";
					}
				} else {

					String tmp = "Programm " + datalist.getFileList().get(zahl).getRname() + " wurde entfernt!";

					if (Funktion.remove(datalist, zahl)) {
						msg = tmp;
					} else {
						msg = "ERROR: Programm " + datalist.getFileList().get(zahl).getRname()
								+ " wurde nicht entfernt!";
					}
				}
				spin_del.setValue(-1);

				dispose();
				GUI frame = new GUI(msg);
				frame.setVisible(true);
				msg = "";
			}
		});
		panel_2.add(button);

		
		panel_2.add(CBX_pfadErzeugen);

		JButton button_1 = new JButton("Hinzuf\u00FCgen");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String vname = tfd_vname.getText();
				String pname = tfd_pname.getText();
				String pfad = tfd_pfad.getText();
				if (!vname.equals("")) {
					if (!vname.equals("")) {
					if (CBX_pfadErzeugen.isSelected()) {
						pfad = prepfad + "\\source\\" + pname + "\\" + vname + "-view\\public_html\\WEB-INF\\web.xml";
					}
				

					Datei data = new Datei(vname.toLowerCase(), pname.toLowerCase(), pfad);
					if (data.exists()) {
						if (Funktion.add(datalist, data)) {
							msg = "Programm " + pname + " hinzugefügt";
							logger.info(msg);
						} else {
							msg = "Programm " + pname + " nicht hinzugefügt!";
							logger.info(msg);

						}
					} else {
						msg = "Der Angegebene Dateipfad existiert nicht!";
						logger.error(msg + "\n" + pfad);
					}
					}else{
						msg="Bitte gebe ein Programmnamen und Versionennamen ein!";
					
					}
				}else{
					msg="Bitte gebe ein Programmnamen und Versionennamen ein!";
				}
					
		
				GUI frame = new GUI(msg);
				dispose();
				frame.setVisible(true);
				msg = "";
			}
		});
		panel_2.add(button_1);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));

		JLabel lblProgrammname = new JLabel("Programmname:");
		panel_3.add(lblProgrammname);

		tfd_pname = new JTextField();
		tfd_pname.setColumns(10);
		panel_3.add(tfd_pname);

		JLabel lblVersionname = new JLabel("Versionname:");
		panel_3.add(lblVersionname);

		tfd_vname = new JTextField();
		tfd_vname.setColumns(10);
		panel_3.add(tfd_vname);

		JLabel lblDateipfad = new JLabel("Dateipfad:");
		panel_3.add(lblDateipfad);

		tfd_pfad = new JTextField();
		tfd_pfad.setColumns(10);
		panel_3.add(tfd_pfad);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Einstellung", null, panel_4, null);
		panel_4.setLayout(null);

		tfd_prepfad = new JTextField();
		tfd_prepfad.setBounds(153, 29, 294, 20);
		panel_4.add(tfd_prepfad);
		tfd_prepfad.setColumns(10);
		try {
			prepfad = fileWriter.load();
		} catch (IOException e2) {
			logger.error("Fehler!", e2);
		}
		tfd_prepfad.setText(prepfad);
		JButton btnNewButton = new JButton("save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfd_prepfad.getText().equals("")) {
				
			
				try {
					fileWriter.save(tfd_prepfad.getText());
					msg = "Neuer Dateipfad gesetzt: " + tfd_prepfad.getText();
					logger.info(msg);
					
				} catch (IOException e1) {
					logger.error("Fehler: ", e1);
				}
				}else{
					msg= "Bitte gebe einen Dateipfad ein!";
					logger.info(msg);
				}
				dispose();
				GUI frame = new GUI(msg);
				frame.setVisible(true);
				msg = "";
			}
		});
		btnNewButton.setBounds(465, 28, 89, 23);
		panel_4.add(btnNewButton);

		JLabel lblDateipfad_1 = new JLabel("Dateipfad:");
		lblDateipfad_1.setBounds(56, 32, 74, 14);
		panel_4.add(lblDateipfad_1);

	}

	public DefaultTableModel showList(DateiList datalist) {
		try {
			datalist.load();
		} catch (IOException e) {
			logger.error("Fehler: ", e);
		}

		String[][] data = new String[][] { { "0", datalist.getFileList().get(0).getRname(),
				XML_Editor.showContent(datalist.getFileList().get(0)), datalist.getFileList().get(0).getPfad() },

		};

		// Die Column-Titles
		String[] title = new String[] { "Nummer", "Programmname", "Version", "Dateipfad" };
		DefaultTableModel model = new DefaultTableModel(data, title);

		for (int i = 1; i < datalist.getFileList().size(); i++) {
			model.addRow(new String[] { (i + ""), datalist.getFileList().get(i).getRname(),
					XML_Editor.showContent(datalist.getFileList().get(i)), datalist.getFileList().get(i).getPfad() });
			model.fireTableDataChanged();
		}

		return model;
	}
}
