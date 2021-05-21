package vista;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import modelo.InterfaceBoss;
import modelo.Service;
import modelo.User;
import modelo.Worker;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JLabel;

public class CrudWindow extends JDialog implements ActionListener, FocusListener {

	private JPanel borderPane;
	private JTextField textCode;
	private JTextField textName;
	private JTextField textSurname;
	private JFormattedTextField textSalary;
	private JButton btnModifyWorker;
	private InterfaceBoss datosBoss;
	private String idBoss;
	private Worker worker;
	private JButton btnAddWorker;
	private JButton btnDeleteWorker;
	private JTextField textCodeService;
	private JTextField textWorkerCode;
	private JFormattedTextField textPrice;
	private JTextField textDescription;
	private JButton btnModifyService;
	private JSpinner spinnerDateStart;
	private JSpinner spinnerDateEnd;
	private JLabel lblDateEnd;
	private JButton btnDeleteService;
	private Service service;
	private JButton btnAddService;
	private NumberFormat decimalFormat=new DecimalFormat("####.##");

	public CrudWindow(InterfaceBoss datosBoss, String id, String name, String surname, String bossId) {
		idBoss = bossId;
		this.datosBoss = datosBoss;
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
		textCode.setText(id);
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
		textName.setEditable(false);
		textName.setText(name);
		backPanel.add(textName);

		textSurname = new JTextField();
		textSurname.setToolTipText("INTRODUCE WORKER SURNAME\r\n");
		textSurname.setForeground(Color.BLACK);
		textSurname.setFont(new Font("Arial", Font.PLAIN, 14));
		textSurname.setHorizontalAlignment(SwingConstants.CENTER);
		textSurname.setColumns(10);
		textSurname.setBounds(10, 105, 143, 31);
		textSurname.setBorder(new LineBorder(new Color(207, 226, 243)));
		textSurname.setEditable(false);
		textSurname.setText(surname);
		backPanel.add(textSurname);
		
		textSalary=new JFormattedTextField(decimalFormat);
		textSalary.setToolTipText("INTRODUCE WORKER SALARY,ONLY NUMBERS ACCEPTED\r\n");
		textSalary.setHorizontalAlignment(SwingConstants.CENTER);
		textSalary.setText("0000.00");
		textSalary.setForeground(Color.LIGHT_GRAY);
		textSalary.setFont(new Font("Arial", Font.PLAIN, 14));
		textSalary.setColumns(10);
		textSalary.setBounds(10, 147, 143, 31);
		textSalary.setBorder(new LineBorder(new Color(207, 226, 243)));
		textSalary.addFocusListener(this);
		backPanel.add(textSalary);

		btnAddWorker = new JButton("ADD");
		btnAddWorker.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddWorker.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnAddWorker.setBackground(Color.WHITE);
		btnAddWorker.setBounds(250, 147, 143, 31);
		btnAddWorker.addActionListener(this);
		backPanel.add(btnAddWorker);
	}

	/**
	 * @param idBoss 
	 * @wbp.parser.constructor
	 */
	public CrudWindow(InterfaceBoss datosBoss, Worker w, String idBoss) {
		this.datosBoss = datosBoss;
		this.idBoss=idBoss;
		worker = w;
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

		textSalary=new JFormattedTextField(decimalFormat);
		textSalary.setToolTipText("INTRODUCE WORKER SALARY,ONLY NUMBERS ACCEPTED\r\n");
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

	public CrudWindow(Worker w, InterfaceBoss datosBoss,String idBoss) {
		this.datosBoss = datosBoss;
		this.idBoss=idBoss;
		worker = w;
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
		
		textSalary=new JFormattedTextField(decimalFormat);
		textSalary.setToolTipText("INTRODUCE WORKER SALARY,ONLY NUMBERS ACCEPTED\r\n");
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
		this.datosBoss = datosBoss;
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
		
		textPrice=new JFormattedTextField(decimalFormat);
		textPrice.setToolTipText("SERVICE PRICE,ONLY NUMBERS ACCEPTED\r\n");
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
		JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(spinnerDateStart, "dd/MM/yyyy HH:mm");
		spinnerDateStart.setEditor(timeEditorStart);
		spinnerDateStart.setValue(new Date(Timestamp.valueOf(service.getDate_time_start()).getTime()));
		backPanel.add(spinnerDateStart);

		spinnerDateEnd = new JSpinner(new SpinnerDateModel());
		spinnerDateEnd.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateEnd.setBounds(250, 111, 143, 20);
		JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(spinnerDateEnd, "dd/MM/yyyy HH:mm");
		spinnerDateEnd.setEditor(timeEditorEnd);
		spinnerDateEnd.setValue(new Date(Timestamp.valueOf(service.getDate_time_end()).getTime()));
		backPanel.add(spinnerDateEnd);

		lblDateEnd = new JLabel("SERVICE  TIME END:");
		lblDateEnd.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateEnd.setBounds(250, 86, 156, 14);
		backPanel.add(lblDateEnd);
	}

	public CrudWindow(InterfaceBoss datosBoss) {
		this.datosBoss = datosBoss;
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
		textCodeService.setForeground(Color.LIGHT_GRAY);
		textCodeService.setFont(new Font("Arial", Font.PLAIN, 14));
		textCodeService.setBounds(10, 21, 143, 31);
		textCodeService.setBorder(new LineBorder(new Color(207, 226, 243)));
		textCodeService.setEditable(false);
		textCodeService.setText("PREDEFINED");
		backPanel.add(textCodeService);
		textCodeService.setColumns(10);

		textWorkerCode = new JTextField();
		textWorkerCode.setToolTipText("ASSIGNED WORKER ID");
		textWorkerCode.setHorizontalAlignment(SwingConstants.CENTER);
		textWorkerCode.setForeground(Color.LIGHT_GRAY);
		textWorkerCode.setFont(new Font("Arial", Font.PLAIN, 14));
		textWorkerCode.setColumns(10);
		textWorkerCode.setEditable(true);
		textWorkerCode.setBounds(10, 63, 143, 31);
		textWorkerCode.setBorder(new LineBorder(new Color(207, 226, 243)));
		textWorkerCode.setText("WORKER ID");
		textWorkerCode.addFocusListener(this);
		backPanel.add(textWorkerCode);

		textDescription = new JTextField();
		textDescription.setToolTipText("SERVICE DESCRIPTION\r\n");
		textDescription.setForeground(Color.LIGHT_GRAY);
		textDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		textDescription.setText("DESCRIPTION");
		textDescription.setHorizontalAlignment(SwingConstants.CENTER);
		textDescription.setColumns(10);
		textDescription.setEditable(true);
		textDescription.setBounds(10, 105, 143, 31);
		textDescription.setBorder(new LineBorder(new Color(207, 226, 243)));
		textDescription.addFocusListener(this);
		backPanel.add(textDescription);
			
		textPrice = new JFormattedTextField(decimalFormat);
		textPrice.setToolTipText("SERVICE PRICE,ONLY NUMBERS ACCEPTED\r\n");
		textPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textPrice.setText("0000.00");
		textPrice.setForeground(Color.LIGHT_GRAY);
		textPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		textPrice.setColumns(10);
		textPrice.setEditable(true);
		textPrice.setBounds(10, 147, 143, 31);
		textPrice.setBorder(new LineBorder(new Color(207, 226, 243)));
		textPrice.addFocusListener(this);
		backPanel.add(textPrice);

		btnAddService = new JButton("ADD");
		btnAddService.setFont(new Font("Arial", Font.BOLD, 14));
		btnAddService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnAddService.setBackground(Color.WHITE);
		btnAddService.setBounds(250, 147, 143, 31);
		btnAddService.addActionListener(this);
		backPanel.add(btnAddService);

		JLabel lblDateStart = new JLabel("DATE TIME START:");
		lblDateStart.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateStart.setBounds(251, 26, 125, 14);
		backPanel.add(lblDateStart);

		spinnerDateStart = new JSpinner(new SpinnerDateModel());
		spinnerDateStart.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateStart.setBounds(250, 47, 143, 20);
		JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(spinnerDateStart, "dd/MM/yyyy HH:mm");
		spinnerDateStart.setEditor(timeEditorStart);
		spinnerDateStart.setValue(new Date(Timestamp.valueOf(LocalDateTime.now()).getTime()));
		spinnerDateStart.setEnabled(true);
		backPanel.add(spinnerDateStart);

		spinnerDateEnd = new JSpinner(new SpinnerDateModel());
		spinnerDateEnd.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateEnd.setBounds(250, 111, 143, 20);
		JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(spinnerDateEnd, "dd/MM/yyyy HH:mm");
		spinnerDateEnd.setEditor(timeEditorEnd);
		spinnerDateEnd.setValue(new Date(Timestamp.valueOf(LocalDateTime.now()).getTime()));
		spinnerDateEnd.setEnabled(true);
		backPanel.add(spinnerDateEnd);

		lblDateEnd = new JLabel("SERVICE  TIME END:");
		lblDateEnd.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateEnd.setBounds(250, 86, 156, 14);
		backPanel.add(lblDateEnd);
	}

	public CrudWindow(Service service, InterfaceBoss datosBoss) {
		this.datosBoss = datosBoss;
		this.service = service;
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
		textWorkerCode.setEditable(false);
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
		textDescription.setEditable(false);
		textDescription.setBounds(10, 105, 143, 31);
		textDescription.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textDescription);

		textPrice=new JFormattedTextField(decimalFormat);
		textPrice.setToolTipText("SERVICE PRICE,ONLY NUMBERS ACCEPTED\r\n");
		textPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textPrice.setText(String.valueOf(service.getPrice()));
		textPrice.setForeground(Color.BLACK);
		textPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		textPrice.setColumns(10);
		textPrice.setEditable(false);
		textPrice.setBounds(10, 147, 143, 31);
		textPrice.setBorder(new LineBorder(new Color(207, 226, 243)));
		backPanel.add(textPrice);

		btnDeleteService = new JButton("DELETE");
		btnDeleteService.setFont(new Font("Arial", Font.BOLD, 14));
		btnDeleteService.setBorder(new LineBorder(new Color(109, 158, 235)));
		btnDeleteService.setBackground(Color.WHITE);
		btnDeleteService.setBounds(250, 147, 143, 31);
		btnDeleteService.addActionListener(this);
		backPanel.add(btnDeleteService);

		JLabel lblDateStart = new JLabel("DATE TIME START:");
		lblDateStart.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateStart.setBounds(251, 26, 125, 14);
		backPanel.add(lblDateStart);

		spinnerDateStart = new JSpinner(new SpinnerDateModel());
		spinnerDateStart.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateStart.setBounds(250, 47, 143, 20);
		JSpinner.DateEditor timeEditorStart = new JSpinner.DateEditor(spinnerDateStart, "dd/MM/yyyy HH:mm");
		spinnerDateStart.setEditor(timeEditorStart);
		spinnerDateStart.setValue(new Date(Timestamp.valueOf(service.getDate_time_start()).getTime()));
		spinnerDateStart.setEnabled(false);
		backPanel.add(spinnerDateStart);

		spinnerDateEnd = new JSpinner(new SpinnerDateModel());
		spinnerDateEnd.setFont(new Font("Arial", Font.BOLD, 11));
		spinnerDateEnd.setBounds(250, 111, 143, 20);
		JSpinner.DateEditor timeEditorEnd = new JSpinner.DateEditor(spinnerDateEnd, "dd/MM/yyyy HH:mm");
		spinnerDateEnd.setEditor(timeEditorEnd);
		spinnerDateEnd.setValue(new Date(Timestamp.valueOf(service.getDate_time_end()).getTime()));
		spinnerDateEnd.setEnabled(false);
		backPanel.add(spinnerDateEnd);

		lblDateEnd = new JLabel("SERVICE  TIME END:");
		lblDateEnd.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblDateEnd.setBounds(250, 86, 156, 14);
		backPanel.add(lblDateEnd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAddWorker)) {
			if (!textCode.getText().isEmpty() && !textName.getText().isEmpty() && !textSurname.getText().isEmpty()
					&& !textSalary.getText().isEmpty())
				addWorker();
			else
				JOptionPane.showMessageDialog(this, "PLEASE INSERT ALL THE INFORMATION");
		}
		if (e.getSource().equals(btnModifyWorker)) {
			if (!textCode.getText().isEmpty() && !textName.getText().isEmpty() && !textSurname.getText().isEmpty()
					&& !textSalary.getText().isEmpty())
				modifyWorker();
			else
				JOptionPane.showMessageDialog(this, "PLEASE INSERT ALL THE INFORMATION");
		}
		if (e.getSource().equals(btnDeleteWorker)) {
			if (!textCode.getText().isEmpty() && !textName.getText().isEmpty() && !textSurname.getText().isEmpty()
					&& !textSalary.getText().isEmpty())
				deleteWorker();
			else
				JOptionPane.showMessageDialog(this, "PLEASE INSERT ALL THE INFORMATION");
		}
		if (e.getSource().equals(btnModifyService)) {
			if (!textCodeService.getText().isEmpty() && !textDescription.getText().isEmpty()
					&& !textPrice.getText().isEmpty() && !textWorkerCode.getText().isEmpty())
				modifyService();
			else
				JOptionPane.showMessageDialog(this, "PLEASE INSERT ALL THE INFORMATION");
		}
		if (e.getSource().equals(btnDeleteService)) {
			if (!textCodeService.getText().isEmpty() && !textDescription.getText().isEmpty()
					&& !textPrice.getText().isEmpty() && !textWorkerCode.getText().isEmpty())
				deleteSevice();
			else
				JOptionPane.showMessageDialog(this, "PLEASE INSERT ALL THE INFORMATION");
		}
		if (e.getSource().equals(btnAddService)) {
			if (!textCodeService.getText().isEmpty() && !textDescription.getText().isEmpty()
					&& !textPrice.getText().isEmpty() && !textWorkerCode.getText().isEmpty())
				addSevice();
			else
				JOptionPane.showMessageDialog(this, "PLEASE INSERT ALL THE INFORMATION");
		}
	}

	private void addSevice() {
		try {
			Service service = new Service();
			service.setWorkerId(textWorkerCode.getText());
			service.setPrice(Double.parseDouble(textPrice.getText()));
			service.setDescription(textDescription.getText());
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String spinnerStart = formatter.format(spinnerDateStart.getValue());
			String spinnerEnd = formatter.format(spinnerDateEnd.getValue());
			service.setDate_time_start(LocalDateTime.parse(spinnerStart, formato));
			service.setDate_time_end(LocalDateTime.parse(spinnerEnd, formato));
			datosBoss.addService(service);
			JOptionPane.showMessageDialog(this,
					"SERVICE ADDED CORRECTLY, THE CODE OF THE SERVICE IS:" + service.getCodeService());
			this.dispose();
			Set<Service>services=datosBoss.listServices();
			AddEditDeleteWindow aedw=new AddEditDeleteWindow(datosBoss,services);
			aedw.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	private void deleteSevice() {
		int reply = JOptionPane.showConfirmDialog(this, "ARE YOU SURE THAT YOU WANT TO DELETE THIS SERVICE?",
				"CONFIRMATION WINDOW", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			try {
				datosBoss.deleteService(service.getCodeService());
				JOptionPane.showMessageDialog(this, "SERVICE DELETED CORRECTLY");
				Set<Service>services=datosBoss.listServices();
				AddEditDeleteWindow aedw=new AddEditDeleteWindow(datosBoss,services);
				aedw.setVisible(true);
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		} else {
			this.dispose();
		}
	}

	private void modifyService() {

		int reply = JOptionPane.showConfirmDialog(this, "ARE YOU SURE THAT YOU WANT TO MODIFY THIS SERVICE?",
				"CONFIRMATION WINDOW", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			try {
				Service service = new Service();
				service.setCodeService(textCodeService.getText());
				service.setWorkerId(textWorkerCode.getText());
				service.setPrice(Double.parseDouble(textPrice.getText()));
				service.setDescription(textDescription.getText());
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				String spinnerStart = formatter.format(spinnerDateStart.getValue());
				String spinnerEnd = formatter.format(spinnerDateEnd.getValue());
				service.setDate_time_start(LocalDateTime.parse(spinnerStart, formato));
				service.setDate_time_end(LocalDateTime.parse(spinnerEnd, formato));
				datosBoss.modifyService(service);
				JOptionPane.showMessageDialog(this, "SERVICE MODIFIED CORRECTLY");
				Set<Service>services=datosBoss.listServices();
				AddEditDeleteWindow aedw=new AddEditDeleteWindow(datosBoss,services);
				aedw.setVisible(true);
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		} else {
			this.dispose();
		}
	}

	private void deleteWorker(){
		int reply = JOptionPane.showConfirmDialog(this, "ARE YOU SURE THAT YOU WANT TO DELETE THIS WORKER?",
				"CONFIRMATION WINDOW", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			try {
				datosBoss.deleteWorker(worker.getId());
				JOptionPane.showMessageDialog(this, "WORKER DELETED CORRECTLY");
				Set<Worker>workers=datosBoss.listWorkers();
				User u=new User();
				u.setId(idBoss);
				AddEditDeleteWindow aedw=new AddEditDeleteWindow(datosBoss,workers,u);
				aedw.setVisible(true);
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		} else {
			this.dispose();
		}
	}

	private void modifyWorker() {
		worker.setName(textName.getText());
		worker.setSurname(textSurname.getText());
		worker.setSalary(Double.parseDouble(textSalary.getText()));
		worker.setBossId(idBoss);
		worker.setType('W');
		int reply = JOptionPane.showConfirmDialog(this, "ARE YOU SURE THAT YOU WANT TO MODIFY THIS WORKER?",
				"CONFIRMATION WINDOW", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			try {
				datosBoss.modifyWorker(worker);
				JOptionPane.showMessageDialog(this, "WORKER MODIFIED CORRECTLY");
				Set<Worker>workers=datosBoss.listWorkers();
				User u=new User();
				u.setId(idBoss);
				AddEditDeleteWindow aedw=new AddEditDeleteWindow(datosBoss,workers,u);
				aedw.setVisible(true);
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e);
			}
		} else {
			this.dispose();
		}
	}

	private void addWorker() {
		Worker worker = new Worker();
		worker.setId(textCode.getText());
		worker.setName(textName.getText());
		worker.setSurname(textSurname.getText());
		worker.setSalary(Double.parseDouble(textSalary.getText()));
		worker.setBossId(idBoss);
		worker.setType('W');
		this.worker=worker;
		try {
			datosBoss.addWorker(worker);
			JOptionPane.showMessageDialog(this, "WORKER ADDED CORRECTLY");
			Set<Worker>workers=datosBoss.listWorkers();
			User u=new User();
			u.setId(idBoss);
			AddEditDeleteWindow aedw=new AddEditDeleteWindow(datosBoss,workers,u);
			aedw.setVisible(true);
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(textSalary) && textSalary.getText().equalsIgnoreCase("0000.00")) {
			textSalary.setText("");
			textSalary.setForeground(Color.BLACK);
		}
		if (e.getSource().equals(textPrice) && textPrice.getText().equalsIgnoreCase("0000.00")) {
			textPrice.setText("");
			textPrice.setForeground(Color.BLACK);
		}
		if (e.getSource().equals(textDescription) && textDescription.getText().equalsIgnoreCase("DESCRIPTION")) {
			textDescription.setText("");
			textDescription.setForeground(Color.BLACK);
		}
		if (e.getSource().equals(textWorkerCode) && textWorkerCode.getText().equalsIgnoreCase("WORKER ID")) {
			textWorkerCode.setText("");
			textWorkerCode.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textCode)) {
			try {
				Worker w = datosBoss.searchWorker(textCode.getText());
				if (w != null) {
					JOptionPane.showMessageDialog(this, "ERROR WORKER ALREADY EXISTS");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "ERROR SEARCHING WORKER");
			}
		}

	}
}
