package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import modelo.InterfaceAdministrator;


public class MainAdmin extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private JButton btnCloseSession;
	private JButton btnUserAdd;
	private JButton btnUserInfo;
	
	private static final long serialVersionUID = 1L;
	
	private InterfaceAdministrator data;

	public MainAdmin(LoginWindow mainWindow, boolean b, InterfaceAdministrator data) {
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
		lblAdmin.setBounds(254, 26, 98, 22);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Arial", Font.BOLD, 21));
		panel.add(lblAdmin);
		
		JLabel label = new JLabel("");
		label.setBounds(285, 15, 0, 0);
		panel.add(label);
		
		btnUserInfo = new JButton("User Information");
		btnUserInfo.setFont(new Font("Arial", Font.BOLD, 15));
		btnUserInfo.setBounds(79, 148, 168, 47);
		btnUserInfo.setBackground(Color.white);
		btnUserInfo.setBorder(new LineBorder(new Color(109, 158, 235)));
		panel.add(btnUserInfo);
		btnUserInfo.addActionListener(this);
		
		btnUserAdd = new JButton("Add User");
		btnUserAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnUserAdd.setBounds(362, 148, 168, 47);
		btnUserAdd.setBackground(Color.white);
		btnUserAdd.setBorder(new LineBorder(new Color(109, 158, 235)));
		panel.add(btnUserAdd);
		btnUserAdd.addActionListener(this);
		
		btnCloseSession = new JButton("Close Session");
		btnCloseSession.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 10));
		btnCloseSession.setBounds(425, 302, 128, 29);
		btnCloseSession.setBackground(Color.WHITE);
		btnCloseSession.setBorder(new LineBorder(new Color(109, 158, 235)));
		panel.add(btnCloseSession);
		btnCloseSession.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(btnUserInfo)) {
			UserInfoWindow uif = new UserInfoWindow(this, true, data);
			uif.setVisible(true);
		}
		if(a.getSource().equals(btnUserAdd)) {
			
		}
		if(a.getSource().equals(btnCloseSession)) {
			this.dispose();
			LoginWindow ventanaPrincipal = new LoginWindow(data);
			ventanaPrincipal.setVisible(true);
		}
	}
}
