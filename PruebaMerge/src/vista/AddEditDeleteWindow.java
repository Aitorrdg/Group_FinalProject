package vista;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import modelo.BDImplementationAdmin;
import modelo.BDImplementationBoss;
import modelo.BDImplementationWorker;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.InterfaceWorker;
import modelo.Service;
import modelo.User;
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
	private JPanel borderPaneService;
	private JPanel backPanelService;
	private JButton btnAddWorker;
	private JButton btnModifyWorker;
	private JButton btnDeleteWorker;
	private InterfaceBoss dataBoss;
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
	private User user;
	private JButton btnCloseSession;
	private JButton btnBack;
	
	public AddEditDeleteWindow(InterfaceBoss dataBoss, Set<Worker> workers, User user) {
		this.dataBoss = dataBoss;
		this.user=user;
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
		backPanel.addMouseListener(this);
		borderPane.add(backPanel);
		borderPane.addMouseListener(this);
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
		btnAddWorker.setEnabled(false);
		backPanel.add(btnAddWorker);
		
		btnCloseSession = new JButton("Close Session");
		btnCloseSession.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 10));
		btnCloseSession.setBounds(460, 20, 128, 29);
		btnCloseSession.setBackground(Color.WHITE);
		btnCloseSession.setFont(new Font("Arial", Font.BOLD, 10));
		btnCloseSession.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnCloseSession.addActionListener(this);
		backPanel.add(btnCloseSession);
		
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnBack.setBounds(449, 370, 153, 29);
		btnBack.addActionListener(this);
		backPanel.add(btnBack);

		tableCreationWorker(workers);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public AddEditDeleteWindow(Set<Service> services, InterfaceBoss dataBoss) {
		this.dataBoss = dataBoss;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 857, 524);
		borderPaneService = new JPanel();
		borderPaneService.setBackground(new Color(109, 158, 235));
		borderPaneService.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPaneService);
		borderPaneService.setLayout(null);
		backPanelService = new JPanel();
		backPanelService.setBounds(25, 28, 801, 435);
		backPanelService.setBackground(new Color(207, 226, 243));
		backPanelService.addMouseListener(this);
		borderPaneService.add(backPanelService);
		borderPaneService.addMouseListener(this);
		backPanelService.setLayout(null);

		btnModifyService = new JButton("MODIFY");
		btnModifyService.setFont(new Font("Arial", Font.BOLD, 14));
		btnModifyService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnModifyService.setBackground(Color.WHITE);
		btnModifyService.setBounds(318, 321, 143, 39);
		btnModifyService.setEnabled(false);
		btnModifyService.addActionListener(this);
		backPanelService.add(btnModifyService);

		btnDeleteService = new JButton("DELETE");
		btnDeleteService.setFont(new Font("Arial", Font.BOLD, 14));
		btnDeleteService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnDeleteService.setBackground(Color.WHITE);
		btnDeleteService.setBounds(618, 321, 143, 39);
		btnDeleteService.setEnabled(false);
		btnDeleteService.addActionListener(this);
		backPanelService.add(btnDeleteService);

		btnAddService = new JButton("ADD");
		btnAddService.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnAddService.setBackground(Color.WHITE);
		btnAddService.setBounds(10, 321, 143, 39);
		btnAddService.addActionListener(this);
		backPanelService.add(btnAddService);
		
		btnCloseSession = new JButton("Close Session");
		btnCloseSession.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 10));
		btnCloseSession.setBounds(662, 21, 128, 29);
		btnCloseSession.setBackground(Color.WHITE);
		btnCloseSession.setFont(new Font("Arial", Font.BOLD, 10));
		btnCloseSession.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnCloseSession.addActionListener(this);
		backPanelService.add(btnCloseSession);
		
		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnBack.setBounds(684, 395, 106, 29);
		btnBack.addActionListener(this);
		backPanelService.add(btnBack);

		tableCreationService(services);
	}

	

	private void tableCreationService(Set<Service> services) {
		ArrayList<Service> listServices = new ArrayList<>();
		String information[][] = null;
		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBounds(10, 90, 780, 190);
		backPanelService.add(scrollTable);
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
		tableService = new JTable(information, columnasService) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
			// HACER DOBLE CLICK************************************
			// Para ello sobreescribimos el metodo que ya tiene la clase
			// JTable.isCellEditable
			public boolean isCellEditable(int row, int column) {
				for (int i = 0; i < tableService.getRowCount(); i++) {
					if (row == i) {
						return false;
					}
				}
				return true;
			}
		};
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
		scrollTable.setBounds(20, 109, 580, 185);
		backPanel.add(scrollTable);
		information = new String[workers.size()][5];
		for (int i = 0; i < information.length; i++) {
			for (Worker w : workers) {
				listWorkers.add(w);
			}
			information[i][0] = listWorkers.get(i).getId();
			information[i][1] = "     " + listWorkers.get(i).getName();
			information[i][2] = "     " + listWorkers.get(i).getSurname();
			information[i][3] = "     " + String.valueOf(listWorkers.get(i).getSalary());
			information[i][4] = "  " + listWorkers.get(i).getBossId();
		}
		tableWorkers = new JTable(information, columnasWorker) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			// ***********************METODO PARA HACER QUE LA TABLA NO SEA EDITABLE, Y ASI
			// HACER DOBLE CLICK************************************
			// Para ello sobreescribimos el metodo que ya tiene la clase
			// JTable.isCellEditable
			public boolean isCellEditable(int row, int column) {
				for (int i = 0; i < tableWorkers.getRowCount(); i++) {
					if (row == i) {
						return false;
					}
				}
				return true;
			}
		};
		tableWorkers.setEnabled(true);
		resizeColumns(tableWorkers);
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

	private void resizeColumns(JTable tableWorkers) {
			tableWorkers.getColumnModel().getColumn(0).setPreferredWidth(130);
			tableWorkers.getColumnModel().getColumn(1).setPreferredWidth(130);
			tableWorkers.getColumnModel().getColumn(2).setPreferredWidth(130);
			tableWorkers.getColumnModel().getColumn(3).setPreferredWidth(130);
			tableWorkers.getColumnModel().getColumn(4).setPreferredWidth(130);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddWorker)) {

				CrudWindow crW = new CrudWindow(dataBoss, worker.getId(), worker.getName(),worker.getSurname(),user.getId());
				this.dispose();
				crW.setVisible(true);
		}
		if (e.getSource().equals(btnModifyWorker)) {
			CrudWindow crW = new CrudWindow(dataBoss,worker,user.getId());
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnDeleteWorker)) {
			CrudWindow crW = new CrudWindow(worker,dataBoss,user.getId());
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnAddService)) {
			CrudWindow crW = new CrudWindow(dataBoss);
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnModifyService)) {
			CrudWindow crW = new CrudWindow(dataBoss, service);
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnDeleteService)) {
			CrudWindow crW = new CrudWindow(service, dataBoss);
			this.dispose();
			crW.setVisible(true);
		}
		if (e.getSource().equals(btnCloseSession)) {
			this.dispose();
			InterfaceWorker dataWorker=new BDImplementationWorker();
			InterfaceAdministrator dataAdmin= new BDImplementationAdmin();
			LoginWindow ventanaPrincipal = new LoginWindow(dataAdmin,dataBoss,dataWorker);
			ventanaPrincipal.setVisible(true);
		}
		if(e.getSource().equals(btnBack)) {
			MainBoss mB= new MainBoss(user, dataBoss);
			this.dispose();
			mB.setVisible(true);
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
					worker = dataBoss.searchWorker(code);
					
					btnAddWorker.setEnabled(true);
					btnModifyWorker.setEnabled(true);
					btnDeleteWorker.setEnabled(true);
					
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
					service = dataBoss.searchService(code);
					btnAddService.setEnabled(true);
					btnDeleteService.setEnabled(true);
					btnModifyService.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "ERROR WHILE SEARCHING SERVICE");
				}
		}
		if(e.getSource().equals(backPanel)||e.getSource().equals(borderPane)) {
			btnAddWorker.setEnabled(false);
			btnDeleteWorker.setEnabled(false);
			btnModifyWorker.setEnabled(false);
		}
		if(e.getSource().equals(backPanelService)||e.getSource().equals(borderPaneService)) {
			btnAddService.setEnabled(true);
			btnDeleteService.setEnabled(false);
			btnModifyService.setEnabled(false);
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

