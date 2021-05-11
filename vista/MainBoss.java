package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.BDImplementationAdmin;
import modelo.Boss;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.InterfaceWorker;
import modelo.User;
import modelo.Worker;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;

public class MainBoss extends JFrame implements ActionListener {

	private JPanel borderPane;
	private JTextField bossName;
	private JButton btnManageWorkers;
	private JButton btnManageServices;
	private Set<Worker> workers;
	private InterfaceBoss datosBoss;
	private Boss boss;

	public MainBoss(Boss b, InterfaceAdministrator datosUser, InterfaceBoss datosBoss) {
		this.datosBoss = datosBoss;
		boss=b;
		try {
			workers = datosBoss.listWorkers();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this,"ERROR WHILE LISTING THE WORKERS");
		}
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 524);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		JPanel backPanel = new JPanel();
		backPanel.setBounds(25, 28, 654, 435);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);

		JLabel lblBoss = new JLabel("BOSS");
		lblBoss.setFont(new Font("Arial", Font.BOLD, 21));
		lblBoss.setBounds(290, 11, 88, 39);
		backPanel.add(lblBoss);

		btnManageWorkers = new JButton("MANAGE WORKERS");
		btnManageWorkers.setFont(new Font("Arial", Font.BOLD, 16));
		btnManageWorkers.setForeground(Color.BLACK);
		btnManageWorkers.setBackground(Color.WHITE);
		btnManageWorkers.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnManageWorkers.setBounds(27, 191, 262, 67);
		btnManageWorkers.addActionListener(this);
		backPanel.add(btnManageWorkers);

		btnManageServices = new JButton("MANAGE SERVICES\r\n");
		btnManageServices.setBackground(Color.WHITE);
		btnManageServices.setFont(new Font("Arial", Font.BOLD, 16));
		btnManageServices.setBounds(365, 191, 262, 67);
		btnManageServices.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnManageServices.addActionListener(this);
		backPanel.add(btnManageServices);

		JButton btnExit = new JButton("EXIT\r\n");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBackground(Color.WHITE);
		btnExit.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnExit.setFont(new Font("Arial", Font.BOLD, 14));
		btnExit.setBounds(484, 374, 143, 39);
		backPanel.add(btnExit);

		bossName = new JTextField();
		bossName.setEditable(false);
		bossName.setHorizontalAlignment(SwingConstants.CENTER);
		bossName.setFont(new Font("Arial", Font.PLAIN, 17));
		bossName.setBounds(227, 61, 191, 31);
		bossName.setBorder(new LineBorder(new Color(207, 226, 243)));
		bossName.setBackground(new Color(207, 226, 243));
		bossName.setText(b.getName() + " " + b.getSurname());
		backPanel.add(bossName);
		bossName.setColumns(10);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnManageWorkers)) {
			AddEditDeleteWindow aedw = new AddEditDeleteWindow(datosBoss, workers);
			this.dispose();
			aedw.setVisible(true);
		}
	}

}
