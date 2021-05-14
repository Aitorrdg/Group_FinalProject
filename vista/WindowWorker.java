package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTable;

public class WindowWorker extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JButton btnInformation;
	private Worker wo;
	private JButton btnInCompleServices;
	private JButton btnBack;
	private JLabel lblImg;
	private BDImplementationWorker data;
	private JScrollPane scrollpaneServices;
	private JScrollPane scrollPaneIncompletedServices;
	private JTableHeader tableHeaderServices;
	private JTableHeader tableHeaderIncompletedServices;
	private JTable tableServices;
	private JTable tableIncompleteServices;
	private JPanel borderPanel;

	public WindowWorker(Worker wo, BDImplementationWorker data) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowWorker.class.getResource("/img/Cleaning Service.png")));
		this.data = data;
		this.wo = wo;
		setBounds(100, 100, 870, 633);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(109, 158, 235));

		borderPanel = new JPanel();
		borderPanel.setBackground(new Color(207, 226, 243));
		borderPanel.setBounds(20, 25, 811, 543);
		contentPanel.add(borderPanel);
		borderPanel.setLayout(null);

		btnInformation = new JButton("My information");
		btnInformation.setToolTipText("Clic here to look at your information");
		btnInformation.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnInformation.setBackground(new Color(109, 158, 235));
		btnInformation.setBounds(582, 46, 200, 53);
		borderPanel.add(btnInformation);
		btnInformation.addActionListener(this);

		lblNombre = new JLabel("New label");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(192, 46, 410, 53);
		borderPanel.add(lblNombre);

		lblNombre.setText("Worker: " + wo.getName());

		btnInCompleServices = new JButton("Incompleted Services");
		btnInCompleServices.setToolTipText("Clic here to look at your incomplete services");
		btnInCompleServices.setFont(new Font("Arial", Font.BOLD, 20));
		btnInCompleServices.setBackground(new Color(109, 158, 235));
		btnInCompleServices.setBounds(186, 441, 416, 59);
		borderPanel.add(btnInCompleServices);
		btnInCompleServices.addActionListener(this);

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnBack.setBackground(new Color(109, 158, 235));
		btnBack.setBounds(582, 437, 200, 66);
		borderPanel.add(btnBack);
		btnBack.addActionListener(this);

		lblImg = new JLabel("");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setIcon(new ImageIcon("C:\\Users\\Usuario\\Downloads\\Cleaning Service.png"));
		lblImg.setBounds(20, 20, 103, 103);
		borderPanel.add(lblImg);

		btnBack.setVisible(false);

		if (wo == null) {
			JOptionPane.showMessageDialog(this, "The worker doesn´t exist", "ERROR", DISPOSE_ON_CLOSE);
		} else {
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
				matrizTabla[i][1] = servicios.get(i).getDescrip();
				matrizTabla[i][2] = servicios.get(i).getWorkerId();
				matrizTabla[i][3] = servicios.get(i).getDate_time_end().toString();
				matrizTabla[i][4] = servicios.get(i).getDate_time_start().toString();
				matrizTabla[i][5] = String.valueOf(servicios.get(i).getPrice()) + "\u20ac";

			}

			scrollpaneServices = new JScrollPane();
			scrollpaneServices.setBounds(20, 209, 783, 125);
			borderPanel.add(scrollpaneServices);
			String titulos[] = { "Service Code", "Description", "Worker ID", "Date of end", "Date of start", "Price" };
			tableServices = new JTable(matrizTabla, titulos);
			tableServices.setVisible(true);
			tableServices.setSelectionBackground(new Color(109, 158, 235));
			tableServices.setSelectionForeground(Color.WHITE);
			tableServices.setRowMargin(0);
			tableServices.setRowHeight(25);
			tableServices.setShowVerticalLines(false);
			tableServices.setFont(new Font("Arial", Font.PLAIN, 12));
			scrollpaneServices.setViewportView(tableServices);

			tableHeaderServices = tableServices.getTableHeader();
			tableHeaderServices.setBackground(new Color(109, 158, 235));
			tableHeaderServices.setForeground(Color.WHITE);
			tableHeaderServices.setFont(new Font("Arial", Font.BOLD, 15));
			tableHeaderServices.setBorder(null);
			tableHeaderServices.setEnabled(false);

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
			JOptionPane.showMessageDialog(this, "Your information: " + " \n " + " Worker code: " + se.getId() + " \n "
					+ " Password: " + se.getPassword() + " \n " + " Name:" + se.getName() + " \n " + " Surname:"
					+ se.getSurname() + " \n " + " Boss code " + se.getBossId() + " \n " + " Salary: " + se.getSalary(),
					se.getName() + " information: ", JOptionPane.INFORMATION_MESSAGE);

		}

		if (e.getSource().equals(btnBack)) {
			btnInCompleServices.setVisible(true);
			if (tableIncompleteServices != null) {
				tableIncompleteServices.setVisible(false);
				tableHeaderIncompletedServices.setVisible(false);
				scrollPaneIncompletedServices.setVisible(false);
			}
			tableServices.setVisible(true);
			tableHeaderServices.setVisible(true);
			scrollpaneServices.setVisible(true);
			btnBack.setVisible(false);

		}
		if (e.getSource().equals(btnInCompleServices)) {
			tableServices.setVisible(false);
			tableHeaderServices.setVisible(false);
			scrollpaneServices.setVisible(false);
			btnInCompleServices.setVisible(false);
			ArrayList<Service> servicios2 = null;
			try {
				servicios2 = data.listUnCompletedServices(wo.getId());
				if (servicios2.size() == 0) {
					JOptionPane.showMessageDialog(this, "The are no incompleted services for this worker", "ERROR",
							DISPOSE_ON_CLOSE);
					btnBack.setVisible(false);
					tableServices.setVisible(true);
					tableHeaderServices.setVisible(true);
					scrollpaneServices.setVisible(true);
					btnBack.setVisible(false);
					btnInCompleServices.setVisible(true);

				} else {
					btnBack.setVisible(true);

					String matrizTabla2[][] = new String[servicios2.size()][6];
					for (int i = 0; i < servicios2.size(); i++) {

						matrizTabla2[i][0] = servicios2.get(i).getCodeService();
						matrizTabla2[i][1] = servicios2.get(i).getDescrip();
						matrizTabla2[i][2] = servicios2.get(i).getWorkerId();
						matrizTabla2[i][3] = servicios2.get(i).getDate_time_end().toString();
						matrizTabla2[i][4] = servicios2.get(i).getDate_time_start().toString();
						matrizTabla2[i][5] = String.valueOf(servicios2.get(i).getPrice()) + "\u20ac";

					}

					scrollPaneIncompletedServices = new JScrollPane();
					scrollPaneIncompletedServices.setBounds(20, 209, 783, 125);
					borderPanel.add(scrollPaneIncompletedServices);

					String titulos[] = { "Service Code", "Description", "Worker ID", "Date of end", "Date of start",
							"Price" };
					tableIncompleteServices = new JTable(matrizTabla2, titulos);
					tableIncompleteServices.setSelectionBackground(new Color(109, 158, 235));
					tableIncompleteServices.setSelectionForeground(Color.WHITE);
					tableIncompleteServices.setRowMargin(0);
					tableIncompleteServices.setRowHeight(25);
					tableIncompleteServices.setShowVerticalLines(false);
					tableIncompleteServices.setFont(new Font("Arial", Font.PLAIN, 12));
					scrollPaneIncompletedServices.setViewportView(tableIncompleteServices);

					tableHeaderIncompletedServices = tableIncompleteServices.getTableHeader();
					tableHeaderIncompletedServices.setBackground(new Color(109, 158, 235));
					tableHeaderIncompletedServices.setForeground(Color.WHITE);
					tableHeaderIncompletedServices.setFont(new Font("Arial", Font.BOLD, 15));
					tableHeaderIncompletedServices.setBorder(null);
					tableHeaderIncompletedServices.setEnabled(false);

					tableIncompleteServices.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int fila = tableIncompleteServices.rowAtPoint(e.getPoint());
							int columna = 0;
							if ((fila > -1) && (columna > -1))
								try {
									int confirmado = JOptionPane.showConfirmDialog(contentPanel,
											"Are you sure you want to modify this service ? ");
									if (JOptionPane.OK_OPTION == confirmado) {
										data.markFinishedServices(
												tableIncompleteServices.getValueAt(fila, columna).toString());
										JOptionPane.showMessageDialog(contentPanel, "Successful modification");
									} else {
										JOptionPane.showMessageDialog(contentPanel, "No modification is made");
									}
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					});
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}
