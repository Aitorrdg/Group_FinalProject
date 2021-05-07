package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.InterfaceBoss;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CrudWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel borderPane;
	private JTextField textCode;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textSalary;
	private JButton btnConfirm;
	
	public CrudWindow(InterfaceBoss datosBoss) {
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 501, 336);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		JPanel backPanel = new JPanel();
		backPanel.setBounds(41, 48, 403, 201);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);
		
		textCode = new JTextField();
		textCode.setText("CODE\r\n");
		textCode.setHorizontalAlignment(SwingConstants.CENTER);
		textCode.setForeground(Color.LIGHT_GRAY);
		textCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textCode.setBounds(10, 21, 143, 31);
		textCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textCode);
		textCode.setColumns(10);
		
		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setText("NAME");
		textName.setForeground(Color.LIGHT_GRAY);
		textName.setFont(new Font("Arial", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(10, 63, 143, 31);
		textName.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textName);
		
		textSurname = new JTextField();
		textSurname.setForeground(Color.LIGHT_GRAY);
		textSurname.setFont(new Font("Arial", Font.PLAIN, 14));
		textSurname.setText("SURNAME");
		textSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textSurname.setColumns(10);
		textSurname.setBounds(10, 105, 143, 31);
		textSurname.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textSurname);
		
		textSalary = new JTextField();
		textSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textSalary.setText("SALARY");
		textSalary.setForeground(Color.LIGHT_GRAY);
		textSalary.setFont(new Font("Arial", Font.PLAIN, 14));
		textSalary.setColumns(10);
		textSalary.setBounds(10, 147, 143, 31);
		textSalary.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textSalary);
		
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirm.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setBounds(250, 147, 143, 31);
		backPanel.add(btnConfirm);

	}

}
