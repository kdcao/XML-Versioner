package main.java.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class Dateipfade extends JFrame {



	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dateipfade frame = new Dateipfade();
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
	public Dateipfade() {
	
		setBounds(100, 100, 234, 272);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setBackground(new Color(105, 105, 105));
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		panel_1.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Programmname:");
		lblNewLabel_2.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(0, 13, 98, 14);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(107, 10, 76, 20);
		textField_1.setFont(new Font("Courier New", Font.PLAIN, 12));
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Versionname:");
		lblNewLabel_3.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 44, 87, 14);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(107, 41, 76, 20);
		textField_2.setFont(new Font("Courier New", Font.PLAIN, 12));
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDateipfad = new JLabel("Dateipfad:");
		lblDateipfad.setFont(new Font("Courier New", Font.PLAIN, 12));
		lblDateipfad.setBounds(25, 76, 73, 14);
		lblDateipfad.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDateipfad);
		
		textField = new JTextField();
		textField.setBounds(107, 73, 76, 20);
		textField.setFont(new Font("Courier New", Font.PLAIN, 12));
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnHinzufgen.setFont(new Font("Courier New", Font.PLAIN, 12));
		btnHinzufgen.setBackground(new Color(169, 169, 169));
		btnHinzufgen.setBounds(34, 118, 129, 23);
		panel.add(btnHinzufgen);
		
		JLabel lbl_info = new JLabel("");
		lbl_info.setBounds(24, 100, 159, 14);
		panel.add(lbl_info);
		
		JLabel lblDateipfadHinzufgen = new JLabel("Dateipfad hinzuf\u00FCgen");
		lblDateipfadHinzufgen.setBackground(new Color(105, 105, 105));
		lblDateipfadHinzufgen.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblDateipfadHinzufgen.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblDateipfadHinzufgen, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel(" Dateipfadnr.:");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 11));
		panel_2.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Courier New", Font.PLAIN, 11));
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnLschen = new JButton("L\u00F6schen");
		btnLschen.setBackground(new Color(169, 169, 169));
		btnLschen.setFont(new Font("Courier New", Font.PLAIN, 11));
		panel_2.add(btnLschen);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		JButton btnNewButton = new JButton("New button");
		panel_3.add(btnNewButton);
	}
}
