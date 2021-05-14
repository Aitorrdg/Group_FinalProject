package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

import modelo.Boss;
import modelo.InterfaceBoss;
import modelo.Service;
import modelo.Worker;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class CrudWindow extends JDialog implements ActionListener,FocusListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel borderPane;
	private JTextField textCode;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textSalary;
	private JButton btnModifyWorker;
	private InterfaceBoss datosBoss;
	private String idBoss;
	private Worker worker;
	private JButton btnConfirm;
	private JButton btnDeleteWorker;
	private JTextField textCodeService;
	private JTextField textWorkerCode;
	private JTextField textPrice;
	private JTextField textDescription;
	private JButton btnModifyService;
	private JSpinner spinnerDateStart;
	private JSpinner spinnerDateEnd;
	private JLabel lblDateEnd;

	public CrudWindow(InterfaceBoss datosBoss,String id, String name, String password, String surname,
			String bossId) {
		idBoss=bossId;
		this.datosBoss=datosBoss;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 501, 336);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		JPanel backPanel = new JPanel();
		backPanel.setBounds(41, 48, 403, 201);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);
		
		textCode = new JTextField();
		textCode.setToolTipText("INTRODUCE WORKER ID\r\n");
		//textCode.setText("CODE\r\n");
		textCode.setHorizontalAlignment(SwingConstants.CENTER);
		textCode.setForeground(Color.BLACK);
		textCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textCode.setBounds(10, 21, 143, 31);
		textCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		//textCode.addFocusListener(this);
		textCode.setEditable(false);
		textCode.setText(id);
		backPanel.add(textCode);
		textCode.setColumns(10);
		
		textName = new JTextField();
		textName.setToolTipText("INTRODUCE WORKER NAME");
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		//textName.setText("NAME");
		textName.setForeground(Color.BLACK);
		textName.setFont(new Font("Arial", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(10, 63, 143, 31);
		textName.setBorder(new LineBorder(new Color(207, 226, 243)));
		//textName.addFocusListener(this);
		textName.setEditable(false);
		textName.setText(name);
		backPanel.add(textName);
		
		textSurname = new JTextField();
		textSurname.setToolTipText("INTRODUCE WORKER SURNAME\r\n");
		textSurname.setForeground(Color.BLACK);
		textSurname.setFont(new Font("Arial", Font.PLAIN, 14));
		//textSurname.setText("SURNAME");
		textSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textSurname.setColumns(10);
		textSurname.setBounds(10, 105, 143, 31);
		textSurname.setBorder(new LineBorder(new Color(207, 226, 243)));
		//textSurname.addFocusListener(this);
		textSurname.setEditable(false);
		textSurname.setText(surname);
		backPanel.add(textSurname);
		
		textSalary = new JTextField();
		textSalary.setToolTipText("INTRODUCE WORKER SALARY\r\n");
		textSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textSalary.setText("SALARY");
		textSalary.setForeground(Color.LIGHT_GRAY);
		textSalary.setFont(new Font("Arial", Font.PLAIN, 14));
		textSalary.setColumns(10);
		textSalary.setBounds(10, 147, 143, 31);
		textSalary.setBorder(new LineBorder(new Color(207, 226, 243)));
		textSalary.addFocusListener(this);
		backPanel.add(textSalary);
		
		btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 14));
		btnConfirm.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setBounds(250, 147, 143, 31);
		btnConfirm.addActionListener(this);
		backPanel.add(btnModifyWorker);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public CrudWindow(InterfaceBoss datosBoss, Worker w) {
		this.datosBoss=datosBoss;
		worker=w;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 501, 336);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		JPanel backPanel = new JPanel();
		backPanel.setBounds(41, 48, 403, 201);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);
		
		textCode = new JTextField();
		textCode.setToolTipText("INTRODUCE WORKER ID\r\n");
		textCode.setHorizontalAlignment(SwingConstants.CENTER);
		textCode.setForeground(Color.BLACK);
		textCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textCode.setBounds(10, 21, 143, 31);
		textCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		textCode.setEditable(false);
		textCode.setText(worker.getId());
		backPanel.add(textCode);
		textCode.setColumns(10);
		
		textName = new JTextField();
		textName.setToolTipText("INTRODUCE WORKER NAME");
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setForeground(Color.BLACK);
		textName.setFont(new Font("Arial", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(10, 63, 143, 31);
		textName.setBorder(new LineBorder(new Color(207, 226, 243)));
		textName.setText(worker.getName());
		backPanel.add(textName);
		
		textSurname = new JTextField();
		textSurname.setToolTipText("INTRODUCE WORKER SURNAME\r\n");
		textSurname.setForeground(Color.BLACK);
		textSurname.setFont(new Font("Arial", Font.PLAIN, 14));
		textSurname.setText(worker.getSurname());
		textSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textSurname.setColumns(10);
		textSurname.setBounds(10, 105, 143, 31);
		textSurname.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textSurname);
		
		textSalary = new JTextField();
		textSalary.setToolTipText("INTRODUCE WORKER SALARY\r\n");
		textSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textSalary.setText(String.valueOf(worker.getSalary()));
		textSalary.setForeground(Color.BLACK);
		textSalary.setFont(new Font("Arial", Font.PLAIN, 14));
		textSalary.setColumns(10);
		textSalary.setBounds(10, 147, 143, 31);
		textSalary.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textSalary);
		
		btnModifyWorker = new JButton("MODIFY");
		btnModifyWorker.setFont(new Font("Arial", Font.BOLD, 14));
		btnModifyWorker.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnModifyWorker.setBackground(Color.WHITE);
		btnModifyWorker.setBounds(250, 147, 143, 31);
		btnModifyWorker.addActionListener(this);
		backPanel.add(btnModifyWorker);
		
		
	}

	public CrudWindow(Worker w, InterfaceBoss datosBoss) {
		this.datosBoss=datosBoss;
		worker=w;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 501, 336);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		JPanel backPanel = new JPanel();
		backPanel.setBounds(41, 48, 403, 201);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);
		
		textCode = new JTextField();
		textCode.setToolTipText("INTRODUCE WORKER ID\r\n");
		textCode.setHorizontalAlignment(SwingConstants.CENTER);
		textCode.setForeground(Color.LIGHT_GRAY);
		textCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textCode.setBounds(10, 21, 143, 31);
		textCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		textCode.setEditable(false);
		textCode.setText(worker.getId());
		backPanel.add(textCode);
		textCode.setColumns(10);
		
		textName = new JTextField();
		textName.setToolTipText("INTRODUCE WORKER NAME");
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textName.setForeground(Color.LIGHT_GRAY);
		textName.setFont(new Font("Arial", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(10, 63, 143, 31);
		textName.setEditable(false);
		textName.setBorder(new LineBorder(new Color(207, 226, 243)));
		textName.setText(worker.getName());
		backPanel.add(textName);
		
		textSurname = new JTextField();
		textSurname.setToolTipText("INTRODUCE WORKER SURNAME\r\n");
		textSurname.setForeground(Color.LIGHT_GRAY);
		textSurname.setFont(new Font("Arial", Font.PLAIN, 14));
		textSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textSurname.setColumns(10);
		textSurname.setBounds(10, 105, 143, 31);
		textSurname.setBorder(new LineBorder(new Color(207, 226, 243)));
		textSurname.setEditable(false);
		textSurname.setText(worker.getSurname());
		backPanel.add(textSurname);
		
		textSalary = new JTextField();
		textSalary.setToolTipText("INTRODUCE WORKER SALARY\r\n");
		textSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textSalary.setText(String.valueOf(worker.getSalary()));
		textSalary.setForeground(Color.LIGHT_GRAY);
		textSalary.setFont(new Font("Arial", Font.PLAIN, 14));
		textSalary.setColumns(10);
		textSalary.setEditable(false);
		textSalary.setBounds(10, 147, 143, 31);
		textSalary.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textSalary);
		
		btnDeleteWorker = new JButton("DELETE");
		btnDeleteWorker.setFont(new Font("Arial", Font.BOLD, 14));
		btnDeleteWorker.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnDeleteWorker.setBackground(Color.WHITE);
		btnDeleteWorker.setBounds(250, 147, 143, 31);
		btnDeleteWorker.addActionListener(this);
		backPanel.add(btnDeleteWorker);
	}

	public CrudWindow(InterfaceBoss datosBoss, Service service) {
		this.datosBoss=datosBoss;
		setModal(true);
		setResizable(false);
		setForeground(new Color(238, 130, 238));
		setBounds(100, 100, 501, 336);
		borderPane = new JPanel();
		borderPane.setBackground(new Color(109, 158, 235));
		borderPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(borderPane);
		borderPane.setLayout(null);
		JPanel backPanel = new JPanel();
		backPanel.setBounds(41, 48, 403, 201);
		backPanel.setBackground(new Color(207, 226, 243));
		borderPane.add(backPanel);
		backPanel.setLayout(null);
		
		textCodeService = new JTextField();
		textCodeService.setToolTipText("SERVICE CODE\r\n");
		textCodeService.setHorizontalAlignment(SwingConstants.CENTER);
		textCodeService.setForeground(Color.BLACK);
		textCodeService.setFont(new Font("Arial", Font.PLAIN, 14));
		textCodeService.setBounds(10, 21, 143, 31);
		textCodeService.setBorder(new LineBorder(new Color(207, 226, 243)));
		textCodeService.setEditable(false);
		textCodeService.setText(service.getCodeService());
		backPanel.add(textCodeService);
		textCodeService.setColumns(10);
		
		textWorkerCode = new JTextField();
		textWorkerCode.setToolTipText("ASSIGNED WORKER ID");
		textWorkerCode.setHorizontalAlignment(SwingConstants.CENTER);
		textWorkerCode.setForeground(Color.BLACK);
		textWorkerCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textWorkerCode.setColumns(10);
		textWorkerCode.setBounds(10, 63, 143, 31);
		textWorkerCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		textWorkerCode.setText(service.getWorkerId());
		backPanel.add(textWorkerCode);
		
		textDescription = new JTextField();
		textDescription.setToolTipText("SERVICE DESCRIPTION\r\n");
		textDescription.setForeground(Color.BLACK);
		textDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		textDescription.setText(service.getDescription());
		textDescription.setHorizontalAlignment(SwingConstants.CENTER);
		textDescription.setColumns(10);
		textDescription.setBounds(10, 105, 143, 31);
		textDescription.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textDescription);
		
		textPrice = new JTextField();
		textPrice.setToolTipText("SERVICE PRICE\r\n");
		textPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textPrice.setText(String.valueOf(service.getPrice()));
		textPrice.setForeground(Color.BLACK);
		textPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		textPrice.setColumns(10);
		textPrice.setBounds(10, 147, 143, 31);
		textPrice.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textPrice);
		
		btnModifyService = new JButton("MODIFY");
		btnModifyService.setFont(new Font("Arial", Font.BOLD, 14));
		btnModifyService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnModifyService.setBackground(Color.WHITE);
		btnModifyService.setBounds(250, 147, 143, 31);
		btnModifyService.addActionListener(this);
		backPanel.add(btnModifyService);
		
		JLabel lblDateStart = new JLabel("DATE TIME START:");
		lblDateStart.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateStart.setBounds(251, 26, 125, 14);
		backPanel.add(lblDateStart);
		
		spinnerDateStart = new JSpinner(new SpinnerDateModel());
		spinnerDateStart.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateStart.setBounds(250, 47, 143, 20);
		JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(spinnerDateStart, "HH:mm:ss dd/MM/yyyy");
		spinnerDateStart.setEditor(timeEditorStart);
		spinnerDateStart.setValue(new Date(Timestamp.valueOf(service.getDate_time_start()).getTime()));
		backPanel.add(spinnerDateStart);
		
		spinnerDateEnd = new JSpinner(new SpinnerDateModel());
		spinnerDateEnd.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateEnd.setBounds(250, 111, 143, 20);
		JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(spinnerDateEnd, "HH:mm:ss dd/MM/yyyy");
		spinnerDateEnd.setEditor(timeEditorEnd);
		spinnerDateEnd.setValue(new Date(Timestamp.valueOf(service.getDate_time_end()).getTime()));
		backPanel.add(spinnerDateEnd);
		
		lblDateEnd = new JLabel("SERVICE  TIME END:");
		lblDateEnd.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateEnd.setBounds(250, 86, 156, 14);
		backPanel.add(lblDateEnd);
	}

	public CrudWindow(InterfaceBoss datosBoss2) {
		// TODO Auto-generated constructor stub
	}

	public CrudWindow(Service service, InterfaceBoss datosBoss2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnConfirm)) {
			if(!textCode.getText().isEmpty()
				&&!textName.getText().isEmpty()&&!textSurname.getText().isEmpty()&&!textSalary.getText().isEmpty())
					addWorker();
			else 
				JOptionPane.showMessageDialog(this,"PLEASE INSERT ALL THE INFORMATION");
		}	
		if(e.getSource().equals(btnModifyWorker)) {
			if(!textCode.getText().isEmpty()
					&&!textName.getText().isEmpty()&&!textSurname.getText().isEmpty()&&!textSalary.getText().isEmpty())
						modifyService();
			else 
				JOptionPane.showMessageDialog(this,"PLEASE INSERT ALL THE INFORMATION");
		}
		if(e.getSource().equals(btnDeleteWorker)) {
			if(!textCode.getText().isEmpty()
					&&!textName.getText().isEmpty()&&!textSurname.getText().isEmpty()&&!textSalary.getText().isEmpty())
						deleteWorker();
			else 
				JOptionPane.showMessageDialog(this,"PLEASE INSERT ALL THE INFORMATION");
		}
		if(e.getSource().equals(btnModifyService)) {
			if(!textCodeService.getText().isEmpty()
					&&!textDescription.getText().isEmpty()&&!textPrice.getText().isEmpty()&&!textWorkerCode.getText().isEmpty())
						modifyWorker();
			else 
				JOptionPane.showMessageDialog(this,"PLEASE INSERT ALL THE INFORMATION");
		}
	}

	private void modifyService() {
		
		int reply=JOptionPane.showConfirmDialog(this,"ARE YOU SURE THAT YOU WANT TO MODIFY THIS SERVICE?","CONFIRMATION WINDOW",JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION) {
			try {
				Service service=new Service();
				service.setCodeService(textCodeService.getText());
				service.setWorkerId(textWorkerCode.getText());
				service.setPrice(Double.parseDouble(textPrice.getText()));
				service.setDescription(textDescription.getText());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
				service.setDate_time_start(LocalDateTime.parse(spinnerDateStart.getValue().toString(),formatter));
				service.setDate_time_end(LocalDateTime.parse(spinnerDateEnd.getValue().toString(),formatter));
				datosBoss.modifyService(service);
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		}else {
			this.dispose();
		}
	}

	private void deleteWorker() {
		int reply=JOptionPane.showConfirmDialog(this,"ARE YOU SURE THAT YOU WANT TO DELETE THIS WORKER?","CONFIRMATION WINDOW",JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION) {
			try {
				datosBoss.deleteWorker(worker.getId());
				JOptionPane.showMessageDialog(this,"WORKER DELETED CORRECTLY");
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		}else {
			this.dispose();
		}
	}

	private void modifyWorker() {
		worker.setName(textName.getText());
		worker.setSurname(textSurname.getText());
		worker.setSalary(Double.parseDouble(textSalary.getText()));
		int reply=JOptionPane.showConfirmDialog(this,"ARE YOU SURE THAT YOU WANT TO MODIFY THIS WORKER?","CONFIRMATION WINDOW",JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION) {
			try {
				datosBoss.modifyWorker(worker);
				JOptionPane.showMessageDialog(this,"WORKER MODIFIED CORRECTLY");
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		}else {
			this.dispose();
		}
	}

	private void addWorker() {
		Worker worker=new Worker();
		worker.setId(textCode.getText());
		worker.setName(textName.getText());
		worker.setPassword(textSurname.getText());
		worker.setSalary(Double.parseDouble(textSalary.getText()));
		worker.setBossId(idBoss);
		worker.setType('W');
		try {
			datosBoss.addWorker(worker);
			JOptionPane.showMessageDialog(this,"WORKER ADDED CORRECTLY");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(textSalary)) {
			textSalary.setText("");
			textSalary.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(textCode)) {
			try {
				Worker w=datosBoss.searchWorker(textCode.getText());
				if(w!=null) {
					JOptionPane.showMessageDialog(this,"ERROR WORKER ALREADY EXISTS");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this,"ERROR SEARCHING WORKER");
			}
		}
		
	}
}
