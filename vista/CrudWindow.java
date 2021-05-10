package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.Boss;
import modelo.InterfaceBoss;
import modelo.Worker;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CrudWindow extends JDialog implements ActionListener,FocusListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel borderPane;
	private JTextField textCode;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textSalary;
	private JButton btnConfirm;
	private InterfaceBoss datosBoss;
	private String idBoss;
	

	public CrudWindow(InterfaceBoss datosBoss,String id, String name, String password, String surname,
			String bossId) {
		idBoss=bossId;
		this.datosBoss=datosBoss;
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
		textCode.setToolTipText("INTRODUCE WORKER ID\r\n");
		//textCode.setText("CODE\r\n");
		textCode.setHorizontalAlignment(SwingConstants.CENTER);
		textCode.setForeground(Color.BLACK);
		textCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textCode.setBounds(10, 21, 143, 31);
		textCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		//textCode.addFocusListener(this);
		textCode.setEditable(false);
		textCode.setText(id);
		backPanel.add(textCode);
		textCode.setColumns(10);
		
		textName = new JTextField();
		textName.setToolTipText("INTRODUCE WORKER NAME");
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		//textName.setText("NAME");
		textName.setForeground(Color.BLACK);
		textName.setFont(new Font("Arial", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(10, 63, 143, 31);
		textName.setBorder(new LineBorder(new Color(207, 226, 243)));
		//textName.addFocusListener(this);
		textName.setEditable(false);
		textName.setText(name);
		backPanel.add(textName);
		
		textSurname = new JTextField();
		textSurname.setToolTipText("INTRODUCE WORKER SURNAME\r\n");
		textSurname.setForeground(Color.BLACK);
		textSurname.setFont(new Font("Arial", Font.PLAIN, 14));
		//textSurname.setText("SURNAME");
		textSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textSurname.setColumns(10);
		textSurname.setBounds(10, 105, 143, 31);
		textSurname.setBorder(new LineBorder(new Color(207, 226, 243)));
		//textSurname.addFocusListener(this);
		textSurname.setEditable(false);
		textSurname.setText(surname);
		backPanel.add(textSurname);
		
		textSalary = new JTextField();
		textSalary.setToolTipText("INTRODUCE WORKER SALARY\r\n");
		textSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textSalary.setText("SALARY");
		textSalary.setForeground(Color.LIGHT_GRAY);
		textSalary.setFont(new Font("Arial", Font.PLAIN, 14));
		textSalary.setColumns(10);
		textSalary.setBounds(10, 147, 143, 31);
		textSalary.setBorder(new LineBorder(new Color(207, 226, 243)));
		textSalary.addFocusListener(this);
		backPanel.add(textSalary);
		
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirm.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setBounds(250, 147, 143, 31);
		btnConfirm.addActionListener(this);
		backPanel.add(btnConfirm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnConfirm)&&!textCode.getText().isEmpty()
				&&!textName.getText().isEmpty()&&!textSurname.getText().isEmpty()&&!textSalary.getText().isEmpty()) {
			addWorker();
		}else
			JOptionPane.showMessageDialog(this,"PLEASE INSERT ALL THE INFORMATION");
		
	}

	private void addWorker() {
		Worker worker=new Worker();
		worker.setId(textCode.getText());
		worker.setName(textName.getText());
		worker.setPassword(textSurname.getText());
		worker.setSalary(Double.parseDouble(textSalary.getText()));
		worker.setBossId(idBoss);
		worker.setType('W');
		try {
			datosBoss.addWorker(worker);
			JOptionPane.showMessageDialog(this,"WORKER ADDED CORRECTLY");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(textSalary)) {
			textSalary.setText("");
			textSalary.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(textCode)) {
			try {
				Worker w=datosBoss.searchWorker(textCode.getText());
				if(w!=null) {
					JOptionPane.showMessageDialog(this,"ERROR WORKER ALREADY EXISTS");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this,"ERROR SEARCHING WORKER");
			}
		}
		
	}

}
