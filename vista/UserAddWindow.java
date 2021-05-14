package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.Timestamp;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import modelo.Boss;
import modelo.InterfaceAdministrator;
import modelo.TextPrompt;
import modelo.User;
import java.awt.event.FocusAdapter;

public class UserAddWindow extends JDialog implements ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel backPane;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldSeniority;
	private JButton btnAddUser;
	private JButton btnModify;
	private JButton btnDeleteUser;
	private JButton btnClose;
	private JList<String> list;
	private JScrollPane scrollPane;
	private User user;
	private InterfaceAdministrator data;
	private JRadioButton rdbtnWorker;
	private JRadioButton rdbtnBoss;
	private JDateChooser dateChooser;
	private JButton btnClean;
	private DefaultListModel<String> listModel;
	private TextPrompt placeholder;
	private TextPrompt placeholder_1;

	public UserAddWindow(InterfaceAdministrator data, boolean b) {
		setModal(b);
		this.data = data;

		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 717, 409);
		backPane = new JPanel();
		backPane.setBackground(new Color(109, 158, 235));
		backPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backPane);
		backPane.setLayout(null);
		JPanel borderPanel = new JPanel();
		borderPanel.setBounds(25, 28, 654, 319);
		borderPanel.setBackground(new Color(207, 226, 243));
		backPane.add(borderPanel);
		borderPanel.setLayout(null);

		textFieldID = new JTextField();
		textFieldID.addFocusListener(this);
		textFieldID.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldID.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldID.setBounds(53, 77, 245, 27);
		borderPanel.add(textFieldID);
		textFieldID.setColumns(10);
		placeholder = new TextPrompt("Code", textFieldID);

		textFieldName = new JTextField();
		textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldName.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldName.setColumns(10);
		textFieldName.setBounds(53, 125, 245, 27);
		borderPanel.add(textFieldName);
		placeholder = new TextPrompt("Name", textFieldName);

		textFieldSurname = new JTextField();
		textFieldSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSurname.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(53, 170, 245, 27);
		borderPanel.add(textFieldSurname);
		placeholder = new TextPrompt("Surname", textFieldSurname);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.BOLD, 15));
		lblType.setBounds(53, 29, 52, 18);
		borderPanel.add(lblType);

		rdbtnBoss = new JRadioButton("Boss");
		buttonGroup.add(rdbtnBoss);
		rdbtnBoss.setFont(new Font("Arial", Font.ITALIC, 15));
		rdbtnBoss.setBounds(108, 28, 79, 21);
		borderPanel.add(rdbtnBoss);

		rdbtnWorker = new JRadioButton("Worker");
		buttonGroup.add(rdbtnWorker);
		rdbtnWorker.setFont(new Font("Arial", Font.ITALIC, 15));
		rdbtnWorker.setBounds(203, 28, 79, 21);
		borderPanel.add(rdbtnWorker);

		textFieldPassword = new JTextField();
		textFieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPassword.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldPassword.addFocusListener(this);
		placeholder_1 = new TextPrompt("Password", textFieldPassword);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(53, 215, 245, 27);
		borderPanel.add(textFieldPassword);

		JLabel lblJoiningDate = new JLabel("Joining Date");
		lblJoiningDate.setFont(new Font("Arial", Font.BOLD, 15));
		lblJoiningDate.setBounds(346, 142, 97, 18);
		borderPanel.add(lblJoiningDate);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(344, 170, 245, 27);
		borderPanel.add(dateChooser);
		dateChooser.setLocale(getLocale());

		textFieldSeniority = new JTextField();
		textFieldSeniority.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSeniority.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldSeniority.setColumns(10);
		textFieldSeniority.setBounds(344, 215, 245, 27);
		borderPanel.add(textFieldSeniority);

		placeholder = new TextPrompt("Seniority", textFieldSeniority);
		placeholder.changeAlpha(0.75f);
		placeholder.changeStyle(Font.ITALIC);

		btnAddUser = new JButton("Add User");
		btnAddUser.setFont(new Font("Arial", Font.BOLD, 15));
		btnAddUser.setBounds(33, 268, 110, 27);
		borderPanel.add(btnAddUser);
		btnAddUser.addActionListener(this);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Arial", Font.BOLD, 15));
		btnModify.setBounds(153, 268, 110, 27);
		borderPanel.add(btnModify);
		btnModify.addActionListener(this);

		btnDeleteUser = new JButton("Delete");
		btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeleteUser.setBounds(273, 267, 110, 27);
		borderPanel.add(btnDeleteUser);
		btnDeleteUser.addActionListener(this);

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Arial", Font.ITALIC, 15));
		btnClose.setBounds(513, 268, 110, 27);
		borderPanel.add(btnClose);
		btnClose.addActionListener(this);

		listModel = new DefaultListModel<String>();
		listModel.addElement("Barrer");
		listModel.addElement("A");
		listModel.addElement("NADAnnnnnn");
		listModel.addElement("B");
		listModel.addElement("B");
		listModel.addElement("B");
		listModel.addElement("B");
		listModel.addElement("B");
		listModel.addElement("B");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(344, 26, 245, 88);
		borderPanel.add(scrollPane);

		list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnClean = new JButton("Clean");
		btnClean.addActionListener(this);
		btnClean.setFont(new Font("Arial", Font.ITALIC, 15));
		btnClean.setBounds(393, 268, 110, 27);
		borderPanel.add(btnClean);

		btnModify.setEnabled(false);
		btnDeleteUser.setEnabled(false);
		btnClean.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnClose)) {
			this.dispose();
		}
		if (e.getSource().equals(btnClean)) {
			cleanTheForm();
		}
		if (e.getSource().equals(btnAddUser)) {
			addNewUser();
		}

	}

	private void addNewUser() {
		// TODO Auto-generated method stub
		User u = null;
		String password;
		if (rdbtnBoss.isSelected()) {
			
			if(textFieldID.getText().isBlank() || textFieldName.getText().isBlank() || textFieldSurname.getText().isBlank() || list.isSelectionEmpty() || dateChooser.getDateEditor().toString().isBlank()) {
				JOptionPane.showConfirmDialog(this, "Please confirm that you have not missed any camp");
			}
			else {
				u.setId(textFieldID.getText());
				u.setName(textFieldName.getText());
				u.setSurname(textFieldSurname.getText());
				password=generatePassword(textFieldName.getText(),textFieldSurname.getText());
				System.out.println(password);
				u.setPassword(password);
				textFieldPassword.setText(u.getPassword().toString());
				 
			}
		}
	}

	private String generatePassword(String name, String surname) {
		String password;
		
		password=name.substring(0, 3)+surname.substring(0, 3)+"*1234";
		return password;
	}

	private void cleanTheForm() {
		// TODO Auto-generated method stub
		textFieldID.setText("");
		textFieldName.setText("");
		textFieldPassword.setText("");
		textFieldSeniority.setText("");
		textFieldSurname.setText("");
		buttonGroup.clearSelection();
		list.clearSelection();
		((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
		btnAddUser.setEnabled(true);
		textFieldSeniority.setEnabled(true);
		dateChooser.setEnabled(true);
		list.setEnabled(true);
		btnModify.setEnabled(false);
		btnDeleteUser.setEnabled(false);

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(textFieldID)) {
			btnClean.setEnabled(true);
		}
		if(e.getSource().equals(textFieldPassword)) {
			String password=generatePassword(textFieldName.getText(), textFieldSurname.getText());
			textFieldPassword.setText(password);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		int indexList = 0;

		if (e.getSource().equals(textFieldID)) {
			try {
				user = data.searchUser(textFieldID.getText());
				if (user != null) {
					JButton jbt_modify = new JButton("Modify");
					JButton jbt_consult = new JButton("Consult");
					JButton jbt_skip = new JButton("Skip");
					jbt_skip.addActionListener(this);
					Object[] options = { jbt_modify, jbt_consult, jbt_skip };
					int a = 1, b = 2;
					Icon c1 = null;
					// int o =JOptionPane.showOptionDialog(jbt_skip, e, getTitle(), indexList,
					// indexList, null, options, e)
					int o = JOptionPane.showOptionDialog(this,
							"User already exist, you can consult and modify his/her details", "Choose your option", a,
							b, c1, null, options);

					if (o == 2) {
						this.dispose();
					}
					if (o == 1) {
						cleanTheForm();
					}
					if (o == 0) {
						btnAddUser.setEnabled(false);
						textFieldName.setText(user.getName());
						textFieldSurname.setText(user.getSurname());
						if (user.getType() == 'B') {
							rdbtnBoss.setSelected(true);
							dateChooser.setDate(new Date(Timestamp.valueOf(((Boss) user).getJoiningDate()).getTime()));
							for (int i = 0; i < listModel.size(); i++) {
								if (listModel.get(i).contentEquals(((Boss) user).getSpeciality())) {
									indexList = i;
								}
							}
							list.setSelectedIndex(indexList);
							String s = Integer.toString(((Boss) user).getSeniority());
							textFieldSeniority.setText(s);
							textFieldPassword.setText("**********");

						} else {
							textFieldPassword.setText("**********");
							rdbtnWorker.setSelected(true);
							fieldWorker();
						}
						btnModify.setEnabled(true);
						btnDeleteUser.setEnabled(true);
					}

				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void fieldWorker() {
		// TODO Auto-generated method stub

		list.setEnabled(false);
		dateChooser.setEnabled(false);
		textFieldSeniority.setEnabled(false);
	}
}