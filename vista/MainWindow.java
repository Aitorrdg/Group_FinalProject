package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class MainWindow extends JFrame {

	private JPanel backPane;

	
	public MainWindow() {
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
	}
}
