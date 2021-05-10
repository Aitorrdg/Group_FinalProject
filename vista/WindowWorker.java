package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import modelo.BDImplementationWorker;
import modelo.Service;
import modelo.Worker;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTable;

public class WindowWorker extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JButton btnInformation;
	private Worker wo;
	private JButton btnMyServices;
	private JButton btnBack;
	private JLabel lblImg;
	private BDImplementationWorker data;
	private JScrollPane scrollPane;
	private JTableHeader tableHeader;
	private JTable table;
	private JPanel borderPanel;

	public WindowWorker(Worker wo, BDImplementationWorker data) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowWorker.class.getResource("/img/Cleaning Service.png")));
		this.data = data;
		this.wo = wo;
		this.wo = wo;
		setBounds(100, 100, 717, 524);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(109, 158, 235));

		borderPanel = new JPanel();
		borderPanel.setBackground(new Color(207, 226, 243));
		borderPanel.setBounds(20, 25, 654, 435);
		contentPanel.add(borderPanel);
		borderPanel.setLayout(null);

		btnInformation = new JButton("My information");
		btnInformation.setFont(new Font("Arial", Font.BOLD, 20));
		btnInformation.setBackground(Color.white);
		btnInformation.setBounds(20, 207, 253, 83);
		btnInformation.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnInformation);
		btnInformation.addActionListener(this);

		lblNombre = new JLabel("New label");
		lblNombre.setFont(new Font("Arial", Font.BOLD, 20));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(192, 46, 282, 53);
		borderPanel.add(lblNombre);

		lblNombre.setText("Worker: " + wo.getName());

		btnMyServices = new JButton("My services");
		btnMyServices.setFont(new Font("Arial", Font.BOLD, 20));
		btnMyServices.setBackground(Color.WHITE);
		btnMyServices.setBounds(371, 207, 253, 83);
		btnMyServices.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnMyServices);
		btnMyServices.addActionListener(this);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(474, 381, 150, 44);
		btnBack.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnBack);
		btnBack.addActionListener(this);

		/*lblImg = new JLabel("");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setIcon(new ImageIcon("C:\\Users\\Usuario\\Downloads\\Cleaning Service.png"));
		lblImg.setBounds(20, 20, 103, 103);
		borderPanel.add(lblImg);*/

		btnBack.setVisible(false);

		if (wo == null) {
			JOptionPane.showMessageDialog(this, "The worker doesn´t exist", "ERROR", EXIT_ON_CLOSE);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnInformation)) {
			Worker se = new Worker();
			try {
				se = wo;

			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(this,
					"Your information" + " \n " + se.getId() + " \n " + se.getPassword() + " \n " + se.getName()
							+ " \n " + se.getSurname() + " \n " + se.getBossId() + " \n " + se.getSalary(),
					"Worker information: " + se.getName(), JOptionPane.INFORMATION_MESSAGE);

		}

		if (e.getSource().equals(btnMyServices)) {
			btnBack.setVisible(true);
			btnInformation.setVisible(false);
			btnMyServices.setVisible(false);
			ArrayList<Service> servicios = null;
			try {
				servicios = data.listServices(wo.getId());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String matrizTabla[][] = new String[servicios.size()][6];
			for (int i = 0; i < servicios.size(); i++) {

				matrizTabla[i][0] = servicios.get(i).getCodeService();
				matrizTabla[i][1] = servicios.get(i).getDescription();
				matrizTabla[i][2] = servicios.get(i).getWorkerId();
				matrizTabla[i][3] = servicios.get(i).getDate_time_end().toString();
				matrizTabla[i][4] = servicios.get(i).getDate_time_start().toString();
				matrizTabla[0][5] = String.valueOf(servicios.get(i).getPrice()) + "\u20ac";

			}

			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 209, 783, 121);
			borderPanel.add(scrollPane);

			String titulos[] = { "Service Code", "Description", "Worker ID", "Date of end", "Date of start", "Price" };
			table = new JTable(matrizTabla, titulos);
			table.setVisible(true);
			table.setSelectionBackground(new Color(109, 158, 235));
			table.setSelectionForeground(Color.WHITE);
			table.setRowMargin(0);
			table.setRowHeight(25);
			table.setShowVerticalLines(false);
			table.setFont(new Font("Tahoma", Font.PLAIN, 12));
			scrollPane.setViewportView(table);

			tableHeader = table.getTableHeader();
			tableHeader.setBackground(new Color(109, 158, 235));
			tableHeader.setForeground(Color.WHITE);
			tableHeader.setFont(new Font("Tahoma", Font.BOLD, 15));
			tableHeader.setBorder(null);
			tableHeader.setEnabled(false);

		}
		if (e.getSource().equals(btnBack)) {
			btnInformation.setVisible(true);
			btnMyServices.setVisible(true);
			table.setVisible(false);
			tableHeader.setVisible(false);
			scrollPane.setVisible(false);
			btnBack.setVisible(false);

		}
	}
}
