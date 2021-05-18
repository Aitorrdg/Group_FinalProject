package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Boss;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.TextPrompt;
import modelo.User;
import modelo.Worker;
import javax.swing.JCheckBox;

public class LoginWindow extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel backPane;
	private JPasswordField passwordField;
	private JLabel lblSelectType;
	private JComboBox<String> comboBoxUserType;
	// private JLabel lblPassIcon;
	// private JLabel lblUserIcon;
	private JButton btnClose;
	private JButton btnLogin;
	private InterfaceAdministrator data;
	private InterfaceBoss dataBoss;
	private JTextField textFieldUser;
	private TextPrompt placeholder;

	public LoginWindow(InterfaceAdministrator data,InterfaceBoss dataBoss) {
		this.data = data;
		this.dataBoss=dataBoss;
		setTitle("Login");
		setResizable(false);
		
		
		
		
		setForeground(new Color(238, 130, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 524);
		backPane = new JPanel();
		backPane.setBackground(new Color(109, 158, 235));
		backPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backPane);
		backPane.setLayout(null);
		JPanel borderPanel = new JPanel();
		borderPanel.setBounds(25, 28, 654, 435);
		borderPanel.setBackground(new Color(207, 226, 243));
		backPane.add(borderPanel);
		borderPanel.setLayout(null);

		lblSelectType = new JLabel("Please select your user type:");
		lblSelectType.setBounds(68, 78, 522, 45);
		lblSelectType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectType.setFont(new Font("Arial", Font.BOLD, 30));
		borderPanel.add(lblSelectType);

		passwordField = new JPasswordField();
		passwordField.setBounds(116, 268, 394, 31);
		passwordField.setToolTipText("Insert your password");
		passwordField.setFont(new Font("Arial", Font.BOLD, 20));
		borderPanel.add(passwordField);
		passwordField.addActionListener(this);
		placeholder = new TextPrompt("Password", passwordField);

		comboBoxUserType = new JComboBox<>();
		comboBoxUserType.setBounds(116, 154, 394, 31);
		comboBoxUserType.setFont(new Font("Arial", Font.BOLD, 20));
		borderPanel.add(comboBoxUserType);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(116, 340, 141, 38);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
		borderPanel.add(btnLogin);
		btnLogin.addActionListener(this);

		btnClose = new JButton("Close");
		btnClose.setBounds(369, 340, 141, 38);
		btnClose.setFont(new Font("Arial", Font.BOLD, 16));
		borderPanel.add(btnClose);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(116, 212, 393, 31);
		textFieldUser.setToolTipText("Insert your username");
		textFieldUser.setFont(new Font("Arial", Font.BOLD, 20));
		borderPanel.add(textFieldUser);
		textFieldUser.setColumns(10);
		placeholder = new TextPrompt("Username", textFieldUser);
		placeholder.changeAlpha(0.75f);
		placeholder.setFont(new Font("Arial", Font.ITALIC, 10));
		
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char) 0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 10));
		chckbxShowPassword.setBounds(387, 305, 122, 21);
		borderPanel.add(chckbxShowPassword);
		
		
		
		
		
		textFieldUser.addKeyListener((KeyListener) new KeyAdapter() {
			@SuppressWarnings("deprecation")
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (textFieldUser.getText().length() > 0 && passwordField.getText().length() > 0)
					btnLogin.setEnabled(true);
				else
					btnLogin.setEnabled(false);
			}
		});

		btnClose.addActionListener(this);

		comboBoxUserType.addItem("Admin");
		comboBoxUserType.addItem("Boss");
		comboBoxUserType.addItem("Worker");
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource().equals(btnLogin)) {
			login(data, a);
		}
		if (a.getSource().equals(btnClose)) {
			this.dispose();
		}

	}

	@SuppressWarnings("deprecation")
	private void login(InterfaceAdministrator data, ActionEvent a) {

		if ((comboBoxUserType.getSelectedItem().toString().equalsIgnoreCase("Admin"))) {
			if (passwordField.getText().equals("1") && textFieldUser.getText().equalsIgnoreCase("1")) {
				MainAdmin aw = new MainAdmin(this, true, data,dataBoss);
				aw.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
			}
		}
		if (comboBoxUserType.getSelectedItem().toString().equalsIgnoreCase("Boss")) {
			String nId = textFieldUser.getText();
			String nPassword = passwordField.getText();
			User u = new Boss();
			try {
				u = data.searchUser(nId);
			
				if (u.getType() == 'B' && u.getId().equalsIgnoreCase(nId) && u.getPassword().equals(nPassword)) {
				 MainBoss mb = new MainBoss((Boss) u, dataBoss);
				 mb.setVisible(true);
				 this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(backPane, e);
			}
		}
		if(comboBoxUserType.getSelectedItem().toString().equalsIgnoreCase("Worker")) {
			String nId = textFieldUser.getText();
			String nPassword = passwordField.getText();
			User w = new Worker();
			try {
				w = data.searchUser(nId);
				if (w.getType() == 'W' && w.getId().equalsIgnoreCase(nId) && w.getPassword().equals(nPassword)) {
					//MainWorker mw = new MainWorker(this, true)
				} else {
					JOptionPane.showMessageDialog(this, "Incorrect Username or Password");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(backPane, e);
			}
		}
	}
}
