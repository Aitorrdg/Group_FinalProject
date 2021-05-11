package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Boss;
import modelo.InterfaceBoss;
import modelo.Worker;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditDeleteWindow extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel borderPane;
	private JButton btnAdd;
	private JButton btnModify;
	private JButton btnDelete;
	private InterfaceBoss datosBoss;
	private Boss boss;

	public AddEditDeleteWindow(InterfaceBoss datosBoss, Set<Worker> workers,Boss b) {
		this.datosBoss=datosBoss;
		boss=b;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
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
		
		btnModify = new JButton("MODIFY");
		btnModify.setFont(new Font("Arial", Font.BOLD, 14));
		btnModify.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnModify.setBackground(Color.WHITE);
		btnModify.setBounds(254, 321, 143, 39);
		btnModify.setEnabled(false);
		btnModify.addActionListener(this);
		backPanel.add(btnModify);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(460, 321, 143, 39);
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(this);
		backPanel.add(btnDelete);
		
		btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
		btnAdd.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBounds(45, 321, 143, 39);
		btnAdd.addActionListener(this);
		backPanel.add(btnAdd);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Worker w=new Worker();
		if(e.getSource().equals(btnAdd)) {
			if(w.getSalary()==0) {
				CrudWindow crW=new CrudWindow(datosBoss,w.getId(),w.getName(),w.getPassword(),w.getSurname(),w.getBossId());
				this.dispose();
				crW.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(this,"This worker has already all the information");
			}
		}
		if(e.getSource().equals(btnModify)) {
			CrudWindow crW=new CrudWindow(datosBoss,w);
			this.dispose();
			crW.setVisible(true);
		}
		if(e.getSource().equals(btnDelete)) {
			CrudWindow crW=new CrudWindow(w,datosBoss);
		}
	}

}
