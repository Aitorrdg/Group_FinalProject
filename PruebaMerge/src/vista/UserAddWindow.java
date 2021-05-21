package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import modelo.Boss;
import modelo.InterfaceAdministrator;
import modelo.InterfaceBoss;
import modelo.User;
import resources.TextPrompt;

public class UserAddWindow extends JDialog implements ActionListener, FocusListener, PropertyChangeListener {

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
	private InterfaceAdministrator data;
	private InterfaceBoss dataBoss;
	private JRadioButton rdbtnWorker;
	private JRadioButton rdbtnBoss;
	private JDateChooser dateChooser;
	private JButton btnClean;
	private DefaultListModel<String> listModel;
	private TextPrompt placeholder;
	private JLabel lblJoiningDate;
	private User u;
	private JButton btnCalSen;

	public UserAddWindow(InterfaceAdministrator data, InterfaceBoss dataBoss, boolean b) {
		setModal(b);
		this.data = data;
		this.dataBoss = dataBoss;
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
		rdbtnBoss.addFocusListener(this);

		rdbtnWorker = new JRadioButton("Worker");
		buttonGroup.add(rdbtnWorker);
		rdbtnWorker.setFont(new Font("Arial", Font.ITALIC, 15));
		rdbtnWorker.setBounds(203, 28, 79, 21);
		borderPanel.add(rdbtnWorker);
		rdbtnWorker.addFocusListener(this);

		textFieldPassword = new JTextField();
		textFieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPassword.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldPassword.addFocusListener(this);
		placeholder = new TextPrompt("Password", textFieldPassword);
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(53, 215, 245, 27);
		borderPanel.add(textFieldPassword);

		lblJoiningDate = new JLabel("Joining Date");
		lblJoiningDate.setFont(new Font("Arial", Font.BOLD, 15));
		lblJoiningDate.setBounds(344, 125, 97, 18);
		borderPanel.add(lblJoiningDate);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addPropertyChangeListener(this);
		dateChooser.setBounds(344, 153, 245, 27);
		borderPanel.add(dateChooser);
		dateChooser.setLocale(getLocale());
		dateChooser.getCalendarButton().addFocusListener(this);


		textFieldSeniority = new JTextField();
		textFieldSeniority.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSeniority.setFont(new Font("Arial", Font.ITALIC, 15));
		textFieldSeniority.setColumns(10);
		textFieldSeniority.setBounds(344, 190, 245, 27);
		borderPanel.add(textFieldSeniority);

		placeholder = new TextPrompt("Seniority", textFieldSeniority);
		placeholder.changeAlpha(0.75f);
		placeholder.changeStyle(Font.ITALIC);

		btnAddUser = new JButton("Add User");
		btnAddUser.setFont(new Font("Arial", Font.BOLD, 15));
		btnAddUser.setBounds(33, 268, 110, 27);
		btnAddUser.setBackground(Color.WHITE);
		btnAddUser.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnAddUser);
		btnAddUser.addActionListener(this);

		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Arial", Font.BOLD, 15));
		btnModify.setBounds(153, 268, 110, 27);
		btnModify.setBackground(Color.WHITE);
		btnModify.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnModify);
		btnModify.addActionListener(this);

		btnDeleteUser = new JButton("Delete");
		btnDeleteUser.setFont(new Font("Arial", Font.BOLD, 15));
		btnDeleteUser.setBounds(273, 267, 110, 27);
		btnDeleteUser.setBackground(Color.WHITE);
		btnDeleteUser.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnDeleteUser);
		btnDeleteUser.addActionListener(this);

		btnClose = new JButton("Close");
		btnClose.setFont(new Font("Arial", Font.ITALIC, 15));
		btnClose.setBounds(513, 268, 110, 27);
		btnClose.setBackground(Color.WHITE);
		btnClose.setBorder(new LineBorder(new Color(109, 158, 235)));
		borderPanel.add(btnClose);
		btnClose.addActionListener(this);

		listModel = new DefaultListModel<String>();
		listModel.addElement("Worker Managing");
		listModel.addElement("Stock Managing");
		listModel.addElement("Training Manager");
		listModel.addElement("Technichal Supervisor");
		listModel.addElement("Worker Safety Manager");
		listModel.addElement("Zone Manager");
		listModel.addElement("PR Manager");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(344, 26, 245, 88);
		borderPanel.add(scrollPane);

		list = new JList<String>(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnClean = new JButton("Clean");
		btnClean.addActionListener(this);
		btnClean.setFont(new Font("Arial", Font.ITALIC, 15));
		btnClean.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnClean.setBackground(Color.WHITE);
		btnClean.setBounds(393, 268, 110, 27);
		borderPanel.add(btnClean);

		btnModify.setEnabled(false);
		btnDeleteUser.setEnabled(false);
		btnClean.setEnabled(false);

		textFieldPassword.setEditable(false);
		textFieldSeniority.setEditable(false);

		btnCalSen = new JButton("click");
		btnCalSen.addActionListener(this);
		btnCalSen.setToolTipText("Click to get Seniority");
		btnCalSen.setBackground(Color.WHITE);
		btnCalSen.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnCalSen.setFont(new Font("Arial", Font.ITALIC, 10));
		btnCalSen.setBounds(531, 221, 58, 21);
		borderPanel.add(btnCalSen);
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
			try {
				addNewUser();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(btnModify)) {
			try {
				modifyUser();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(btnDeleteUser)) {
			try {
				deleteUser(u);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(btnCalSen)) {
			
				
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String fecha = sdf.format(dateChooser.getDate());
					int sen;
					sen = dataBoss.calculateSeniority(LocalDateTime.parse(fecha, formatter));
					String seni = String.valueOf(sen);
					textFieldSeniority.setText(seni);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "Please select a date");
				}
				
				
		}
	}

	private void deleteUser(User u) {
		// TODO Auto-generated method stub

		int dialogButton = JOptionPane.YES_NO_OPTION;
		JOptionPane.showConfirmDialog(null, "Are you sure ?", "WARNING", dialogButton);
		if (dialogButton == JOptionPane.YES_OPTION) {
			try {
				data.deleteUser(u);
				JOptionPane.showMessageDialog(this, "User has been deleted successfully", "Deleted",
						JOptionPane.OK_OPTION);
				cleanTheForm();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e);
			}

			if (dialogButton == JOptionPane.NO_OPTION) {
				remove(dialogButton);
			}
		}

	}

	private void modifyUser() {
		// TODO Auto-generated method stub

		if (rdbtnBoss.isSelected()) {
			rdbtnWorker.setEnabled(false);
			u = setBoss(u);
		} else if (rdbtnWorker.isSelected()) {
			rdbtnBoss.setEnabled(false);
			u = setWorker(u);
		}
		try {
			data.modifyUser(u);
			JOptionPane.showMessageDialog(this, "Modiffied correctly");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e);
		}

	}

	private void addNewUser() {

		if (buttonGroup.isSelected(null)) {
			JOptionPane.showMessageDialog(this, "Select type of user");
		} else if (rdbtnBoss.isSelected()) {
			if (textFieldID.getText().isEmpty() || textFieldName.getText().isEmpty()
					|| textFieldSurname.getText().isEmpty() || list.isSelectionEmpty()
					|| dateChooser.getDateFormatString().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please confirm that you have not missed any camp");
			} else {
				u = setBoss(u);
				try {if (u.getPassword() == null) {
					JOptionPane.showMessageDialog(this, "Name/surname is too short");
				} else {
					insertUser(u);
					JOptionPane.showMessageDialog(this, "User added correctly");
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e);
				}

			}
		} else if (rdbtnWorker.isSelected()) {
			if (textFieldID.getText().isEmpty() || textFieldName.getText().isEmpty()
					|| textFieldSurname.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please confirm that you have not missed any camp");
			} else {

				u = setWorker(u);
				try {
					if (u.getPassword() == null) {
						JOptionPane.showMessageDialog(this, "Name/surname is too short");
					} else {
						insertUser(u);
						JOptionPane.showMessageDialog(this, "User added correctly");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e);
				}
			}

		}

	}

	private User setWorker(User u) {
		u = new User();
		u.setType('W');
		String password;
		u.setId(textFieldID.getText());
		u.setName(textFieldName.getText());
		u.setSurname(textFieldSurname.getText());
		password = generatePassword(textFieldName.getText(), textFieldSurname.getText());
		u.setPassword(password);
		return u;
	}

	private User setBoss(User u) {
		u = new Boss();
		u.setType('B');
		String password;
		u.setId(textFieldID.getText());
		u.setName(textFieldName.getText());
		u.setSurname(textFieldSurname.getText());
		password = generatePassword(textFieldName.getText(), textFieldSurname.getText());
		u.setPassword(password);
		((Boss) u).setSpeciality(list.getSelectedValue());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String fecha = sdf.format(dateChooser.getDate());
		((Boss) u).setJoiningDate(LocalDateTime.parse(fecha, formatter));
		int sen;
		try {
			sen = dataBoss.calculateSeniority(LocalDateTime.parse(fecha, formatter));
			String seni = String.valueOf(sen);
			textFieldSeniority.setText(seni);
			((Boss) u).setSeniority(sen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e);
		}

		return u;
	}

	private void insertUser(User u) {
		// TODO Auto-generated method stub
		try {
			data.addUser(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (u != null) {
			JOptionPane.showMessageDialog(this, "CODE: " + u.getId() + "\nName: " + u.getName() + "\nSurname: "
					+ u.getSurname() + "\nPassword: " + u.getPassword());
		} else {
			JOptionPane.showMessageDialog(this, "There was an error while adding the user");

		}
	}

	private String generatePassword(String name, String surname) {
		String password = null;
		if (name.length() < 3 || surname.length() < 3) {
			password = null;
		} else {
			password = name.substring(0, 3) + surname.substring(0, 3) + "*1234";
		}

		return password;
	}

	private void cleanTheForm() {
		// TODO Auto-generated method stub
		textFieldID.setText("");
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
		if (e.getSource().equals(textFieldID)) {
			btnClean.setEnabled(true);

		}
		if (e.getSource().equals(rdbtnWorker)) {
			fieldWorker();
		}
		if (e.getSource().equals(rdbtnBoss)) {
			list.setEnabled(true);
			dateChooser.setEnabled(true);
			textFieldSeniority.setEnabled(true);
		}
		if (e.getSource().equals(textFieldPassword)) {
			if (textFieldName.getText().isBlank() || textFieldSurname.getText().isBlank()) {
				textFieldPassword.transferFocus();	
				JOptionPane.showMessageDialog(this, "Please fill the Name/Surname fields first");
			
			}else if(textFieldName.getText().length() <3 || textFieldSurname.getText().length()<3) {
				textFieldPassword.transferFocus();	
				JOptionPane.showMessageDialog(this, "Name/Surname too short");
						
			}
			else {
				String s = generatePassword(textFieldName.getText(), textFieldSurname.getText());
				textFieldPassword.setText(s);
			}

		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(textFieldID)) {
			int indexList = 0;
			Pattern pat = Pattern.compile("[a-zA-Z0-9]{1}[0-9]{7}[A-Z a-z]");
		    Matcher mat = pat.matcher(textFieldID.getText());
		       if(!mat.matches()){
		    	   JOptionPane.showMessageDialog(this, "The introduced Id is incorrect, please "

		                   + "introduce a valid one.");
		    	   textFieldID.setText("");
		       }   
			try {
				u = data.searchUser(textFieldID.getText());
				if (u != null) {
					JButton jbt_modify = new JButton("Modify");
					JButton jbt_consult = new JButton("Consult");
					JButton jbt_skip = new JButton("Skip");
					jbt_skip.addActionListener(this);
					Object[] options = { jbt_modify, jbt_consult, jbt_skip };
					int a = 1, b = 2;
					Icon c1 = null;
					int o = JOptionPane.showOptionDialog(this,
							"User already exist, you can consult and modify his/her details", "Choose your option", a,
							b, c1, null, options);

					if (o == 2) {
						this.dispose();
					} else if (o == 1) {
						cleanTheForm();
					}
					if (o == 0) {
						btnModify.setEnabled(true);
						btnDeleteUser.setEnabled(true);
						btnAddUser.setEnabled(false);
						textFieldName.setText(u.getName());
						textFieldSurname.setText(u.getSurname());
						if (u.getType() == 'B') {
							rdbtnBoss.setSelected(true);
							dateChooser.setDate(new Date(Timestamp.valueOf(((Boss) u).getJoiningDate()).getTime()));
							for (int i = 0; i < listModel.size(); i++) {
								if (listModel.get(i).equalsIgnoreCase(((Boss) u).getSpeciality())) {
									indexList = i;
								}
							}
							list.setSelectedIndex(indexList);
							String s = Integer.toString(((Boss) u).getSeniority());
							textFieldSeniority.setText(s);
							textFieldPassword.setText("**********");

						} else {
							textFieldPassword.setText("**********");
							rdbtnWorker.setSelected(true);
							fieldWorker();
						}

					}

				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e);
			}
		}

	}

	private void fieldWorker() {
		// TODO Auto-generated method stub

		list.setEnabled(false);
		dateChooser.setEnabled(false);
		textFieldSeniority.setEnabled(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

		if (evt.getSource().equals(dateChooser.getCalendarButton().getPropertyChangeListeners())) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String fecha = sdf.format(dateChooser.getDate());
				int sen;
				sen = dataBoss.calculateSeniority(LocalDateTime.parse(fecha, formatter));
				String seni = String.valueOf(sen);
				textFieldSeniority.setText(seni);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e);
			}
		}

	}
}