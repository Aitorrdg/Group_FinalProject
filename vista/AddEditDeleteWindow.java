package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import modelo.Boss;
import modelo.InterfaceBoss;
import modelo.Service;
import modelo.Worker;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddEditDeleteWindow extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel borderPane;
	private JButton btnAddWorker;
	private JButton btnModifyWorker;
	private JButton btnDeleteWorker;
	private InterfaceBoss datosBoss;
	private String columnasWorker[] = { "Code", "Name", "Surname", "Salary", "Boss" };
	private String columnasService[] = { "Code", "Worker", "Descript", "Price", "Start", "End", "Finished" };
	private JPanel backPanel;
	private JButton btnModifyService;
	private JButton btnDeleteService;
	private JButton btnAddService;
	private JTable tableService;
	private JTable tableWorkers;
	private Worker worker;
	private Service service;

	public AddEditDeleteWindow(InterfaceBoss datosBoss, Set<Worker> workers) {
		this.datosBoss = datosBoss;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 664, 524);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		backPanel = new JPanel();
		backPanel.setBounds(25, 28, 609, 435);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);

		btnModifyWorker = new JButton("MODIFY");
		btnModifyWorker.setFont(new Font("Arial", Font.BOLD, 14));
		btnModifyWorker.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnModifyWorker.setBackground(Color.WHITE);
		btnModifyWorker.setBounds(231, 321, 143, 39);
		btnModifyWorker.setEnabled(false);
		btnModifyWorker.addActionListener(this);
		backPanel.add(btnModifyWorker);

		btnDeleteWorker = new JButton("DELETE");
		btnDeleteWorker.setFont(new Font("Arial", Font.BOLD, 14));
		btnDeleteWorker.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnDeleteWorker.setBackground(Color.WHITE);
		btnDeleteWorker.setBounds(441, 321, 143, 39);
		btnDeleteWorker.setEnabled(false);
		btnDeleteWorker.addActionListener(this);
		backPanel.add(btnDeleteWorker);

		btnAddWorker = new JButton("ADD");
		btnAddWorker.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddWorker.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnAddWorker.setBackground(Color.WHITE);
		btnAddWorker.setBounds(10, 321, 143, 39);
		btnAddWorker.addActionListener(this);
		backPanel.add(btnAddWorker);

		tableCreationWorker(workers);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public AddEditDeleteWindow(Set<Service> services, InterfaceBoss datosBoss) {
		this.datosBoss = datosBoss;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 857, 524);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		backPanel = new JPanel();
		backPanel.setBounds(25, 28, 801, 435);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);

		btnModifyService = new JButton("MODIFY");
		btnModifyService.setFont(new Font("Arial", Font.BOLD, 14));
		btnModifyService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnModifyService.setBackground(Color.WHITE);
		btnModifyService.setBounds(318, 321, 143, 39);
		btnModifyService.setEnabled(false);
		btnModifyService.addActionListener(this);
		backPanel.add(btnModifyService);

		btnDeleteService = new JButton("DELETE");
		btnDeleteService.setFont(new Font("Arial", Font.BOLD, 14));
		btnDeleteService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnDeleteService.setBackground(Color.WHITE);
		btnDeleteService.setBounds(618, 321, 143, 39);
		btnDeleteService.setEnabled(false);
		btnDeleteService.addActionListener(this);
		backPanel.add(btnDeleteService);

		btnAddService = new JButton("ADD");
		btnAddService.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnAddService.setBackground(Color.WHITE);
		btnAddService.setBounds(10, 321, 143, 39);
		btnAddService.addActionListener(this);
		backPanel.add(btnAddService);

		tableCreationService(services);
	}

	private void tableCreationService(Set<Service> services) {
		ArrayList<Service> listServices = new ArrayList<>();
		String information[][] = null;
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(10, 90, 780, 190);
		backPanel.add(scrollTable);
		information = new String[services.size()][7];
		for (int i = 0; i < information.length; i++) {
			for (Service s : services) {
				listServices.add(s);
			}
			information[i][0] = listServices.get(i).getCodeService();
			information[i][1] = listServices.get(i).getWorkerId();
			information[i][2] = listServices.get(i).getDescription();
			information[i][3] = String.valueOf(listServices.get(i).getPrice());
			information[i][4] = "" + listServices.get(i).getDate_time_start();
			information[i][5] = "" + listServices.get(i).getDate_time_end();
			information[i][6] = "" + listServices.get(i).isFinished();
		}
		tableService = new JTable(information, columnasService);
		tableService.setEnabled(true);
		resizeColummWidth(tableService);
		tableService.setSelectionBackground(new Color(109, 158, 235));
		tableService.setSelectionForeground(Color.WHITE);
		tableService.setRowMargin(0);
		tableService.setRowHeight(35);
		tableService.setShowVerticalLines(false);
		tableService.addMouseListener(this);
		tableService.setFont(new Font("Arial", Font.PLAIN, 12));

		JTableHeader tableHeaderService = tableService.getTableHeader();
		tableHeaderService.setBackground(new Color(109, 158, 235));
		tableHeaderService.setForeground(Color.WHITE);
		tableHeaderService.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeaderService.setBorder(null);
		tableHeaderService.setEnabled(false);
		tableService.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollTable.setViewportView(tableService);
	}

	private void resizeColummWidth(JTable tableService) {
		tableService.getColumnModel().getColumn(0).setPreferredWidth(130);
		tableService.getColumnModel().getColumn(1).setPreferredWidth(130);
		tableService.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableService.getColumnModel().getColumn(3).setPreferredWidth(130);
		tableService.getColumnModel().getColumn(4).setPreferredWidth(130);
		tableService.getColumnModel().getColumn(5).setPreferredWidth(130);
		tableService.getColumnModel().getColumn(6).setPreferredWidth(130);
	}

	private void tableCreationWorker(Set<Worker> workers) {
		ArrayList<Worker> listWorkers = new ArrayList<>();
		String information[][] = null;
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(105, 95, 380, 190);
		backPanel.add(scrollTable);
		information = new String[workers.size()][5];
		for (int i = 0; i < information.length; i++) {
			for (Worker w : workers) {
				listWorkers.add(w);
			}
			information[i][0] = "  " + listWorkers.get(i).getId();
			information[i][1] = "     " + listWorkers.get(i).getName();
			information[i][2] = "     " + listWorkers.get(i).getSurname();
			information[i][3] = "     " + String.valueOf(listWorkers.get(i).getSalary());
			information[i][4] = "  " + listWorkers.get(i).getBossId();
		}
		tableWorkers = new JTable(information, columnasWorker);
		tableWorkers.setEnabled(true);
		tableWorkers.setSelectionBackground(new Color(109, 158, 235));
		tableWorkers.setSelectionForeground(Color.WHITE);
		tableWorkers.setRowMargin(0);
		tableWorkers.setRowHeight(55);
		tableWorkers.setShowVerticalLines(false);
		tableWorkers.addMouseListener(this);
		tableWorkers.setFont(new Font("Arial", Font.PLAIN, 12));

		JTableHeader tableHeaderWorker = tableWorkers.getTableHeader();
		tableHeaderWorker.setBackground(new Color(109, 158, 235));
		tableHeaderWorker.setForeground(Color.WHITE);
		tableHeaderWorker.setFont(new Font("Arial", Font.BOLD, 15));
		tableHeaderWorker.setBorder(null);
		tableHeaderWorker.setEnabled(false);
		tableWorkers.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollTable.setViewportView(tableWorkers);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddWorker)) {
			if (worker.getSalary() == 0) {
				CrudWindow crW = new CrudWindow(datosBoss, worker.getId(), worker.getName(), worker.getPassword(),
						worker.getSurname(), worker.getBossId());
				this.dispose();
				crW.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "This worker has already all the information");
			}
		}
		if (e.getSource().equals(btnModifyWorker)) {
			CrudWindow crW = new CrudWindow(datosBoss, worker);
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnDeleteWorker)) {
			CrudWindow crW = new CrudWindow(worker, datosBoss);
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnAddService)) {
			if (worker.getSalary() == 0) {
				CrudWindow crW = new CrudWindow(datosBoss);
				this.dispose();
				crW.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "This worker has already all the information");
			}
		}
		if (e.getSource().equals(btnModifyService)) {
			CrudWindow crW = new CrudWindow(datosBoss, service);
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnDeleteService)) {
			CrudWindow crW = new CrudWindow(service, datosBoss);
			this.dispose();
			crW.setVisible(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tableWorkers)) {
			int fila = tableWorkers.rowAtPoint(e.getPoint());
			int columna = 0;
			if ((fila > -1) && (columna > -1))
				try {
					String code = tableWorkers.getValueAt(fila, columna).toString();
					worker = datosBoss.searchWorker(code);
					btnAddWorker.setEnabled(false);
					btnDeleteWorker.setEnabled(true);
					btnModifyWorker.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "ERROR WHILE SEARCHING WORKER");
				}
		}
		if (e.getSource().equals(tableService)) {
			int fila = tableService.rowAtPoint(e.getPoint());
			int columna = 0;
			if ((fila > -1) && (columna > -1))
				try {
					String code = tableService.getValueAt(fila, columna).toString();
					service = datosBoss.searchService(code);
					btnAddService.setEnabled(false);
					btnDeleteService.setEnabled(true);
					btnModifyService.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "ERROR WHILE SEARCHING SERVICE");
				}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
