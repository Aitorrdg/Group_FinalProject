package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.InterfaceWorker;
import modelo.JTableUtilities;
import modelo.User;

public class MainAdmin extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private JButton btnCloseSession;
	private JButton btnUserAdd;
	private JButton btnUserInfo;
	private JScrollPane scrollPane;

	private static final long serialVersionUID = 1L;

	private InterfaceAdministrator data;
	private InterfaceBoss dataBoss;
	private InterfaceWorker dataWorker;
	private JTable table;

	public MainAdmin(LoginWindow mainWindow, boolean b, InterfaceAdministrator data, InterfaceBoss dataBoss,InterfaceWorker dataWorker) {
		setResizable(false);
		setModal(b);
		this.data = data;
		this.dataBoss=dataBoss;
		this.dataWorker=dataWorker;
		
		
		getContentPane().setBackground(new Color(109, 158, 235));
		setBounds(100, 100, 966, 675);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(207, 226, 243));
		panel.setBounds(24, 22, 914, 602);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(10, 25, 98, 22);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(lblAdmin);

		JLabel label = new JLabel("");
		label.setBounds(285, 15, 0, 0);
		panel.add(label);

		btnUserInfo = new JButton("User Information");
		btnUserInfo.setFont(new Font("Arial", Font.BOLD, 15));
		btnUserInfo.setBounds(23, 521, 168, 47);
		panel.add(btnUserInfo);
		btnUserInfo.addActionListener(this);

		btnUserAdd = new JButton("Manage");
		btnUserAdd.setFont(new Font("Arial", Font.BOLD, 15));
		btnUserAdd.setBounds(724, 521, 168, 47);
		panel.add(btnUserAdd);
		btnUserAdd.addActionListener(this);

		btnCloseSession = new JButton("Close Session");
		btnCloseSession.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 10));
		btnCloseSession.setBounds(764, 15, 128, 29);
		btnCloseSession.addActionListener(this);
		panel.add(btnCloseSession);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 77, 827, 434);
		panel.add(scrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 882, 10);
		panel.add(separator);
		

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		if (a.getSource().equals(btnUserInfo)) {
			showUserInfo();
//			UserInfoWindow uif = new UserInfoWindow(this, true, data);
//			uif.setVisible(true);
		}
		if (a.getSource().equals(btnUserAdd)) {
			UserAddWindow UAW = new UserAddWindow(data,dataBoss, true);
			UAW.setVisible(true);
		}
		if (a.getSource().equals(btnCloseSession)) {
			this.dispose();
			LoginWindow ventanaPrincipal = new LoginWindow(data,dataBoss,dataWorker);
			ventanaPrincipal.setVisible(true);
		}
	}

	private void showUserInfo() {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<>();
		try {
			users = data.listUser();
			if (users.size() > 0) {
				String rowTable[][] = new String[users.size()][4];
				for (int i = 0; i < users.size(); i++) {
					rowTable[i][0] =  users.get(i).getId();
					rowTable[i][1] = users.get(i).getName() ;
					rowTable[i][2] =   users.get(i).getSurname()  ;
					rowTable[i][3] =  Character.toString(users.get(i).getType()) ;

				}
				String titles[] = { "ID", "Name", "Surname", "Type" };
				table = new JTable(rowTable, titles);
				table.setSelectionBackground(new Color(0, 230, 168));
				table.setSelectionForeground(Color.WHITE);
				table.setRowMargin(0);
				table.setRowHeight(25);
				table.setShowVerticalLines(false);
				
				JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
				
				table.setFont(new Font("Arial", Font.BOLD, 12));
				scrollPane.setViewportView(table);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
