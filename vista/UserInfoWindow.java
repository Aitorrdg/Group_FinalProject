package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import modelo.InterfaceAdministrator;
import javax.swing.table.DefaultTableModel;

public class UserInfoWindow extends JDialog {
	private static final long serialVersionUID = 1L;
	private InterfaceAdministrator data;
	private JTable tableUser;
	public UserInfoWindow(MainAdmin adminWindow, boolean b, InterfaceAdministrator data) {
		setModal(b);
		this.data=data;
		getContentPane().setBackground(new Color(109, 158, 235));
		setBounds(100, 100, 688, 464);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(207, 226, 243));
		panel.setBounds(24, 22, 624, 380);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(466, 30, 98, 22);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(lblAdmin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 62, 519, 243);
		panel.add(scrollPane);
		
		tableUser = new JTable();
		tableUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tableUser);
	}
}
