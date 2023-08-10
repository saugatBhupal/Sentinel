package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CitizenController;
import controller.PoliceController;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import model.Citizen;
import model.Police;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.dateTime.DateTimeUtil;
import utils.generator.PasswordGenerator;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import views.widget.DateTimeWidget;

public class RegisterPanel {
	private final CitizenController citizenController;
	private final PoliceController policeController;
	private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private JFrame frame;
    private JPanel panel;

    public RegisterPanel(App app) {
		this.citizenController = new CitizenControllerImpl(app);
		this.policeController = new PoliceControllerImpl(panel, app);
		initialize();
	}

	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1201, 841);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(248, 248, 250, 1));
		panel.setBounds(0, 0, 1201, 841);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		JLabel logo = new JLabel();
		logo.setBounds(25, 35, 58, 57);
		logo.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/Sentinel-logo-2.png").getImage(), logo));
		panel.add(logo);

		JLabel parentPageTitle = new JLabel();
		parentPageTitle.setText("Police Officers");
		parentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
		parentPageTitle.setForeground(Color.decode("#002349"));
		parentPageTitle.setBounds(239,61,141,32);
		panel.add(parentPageTitle);

		JLabel separator = new JLabel();
		separator.setIcon(new ImageIcon("resources/artboards/left-arrow-1.png"));
		separator.setBounds(385, 70, 16, 16);
		panel.add(separator);

		JLabel currentPageTitle = new JLabel();
		currentPageTitle.setText("Register Officer");
		currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
		currentPageTitle.setForeground(Color.decode("#1A75D5"));
		currentPageTitle.setBounds(412,61,151,32);
		panel.add(currentPageTitle);

		DateTimeWidget.addWidget(panel);

		JLabel searchIcon = new JLabel();
		searchIcon.setIcon(new ImageIcon("resources/artboards/search-icon.png"));
		searchIcon.setBounds(432, 204, 20, 20);
		panel.add(searchIcon);

		JTextField search = new JTextField(); 
		search.setBorder(null);
		search.addMouseListener(Hover.focusable(search));
		search.addFocusListener(Focus.setPlaceholder(search, "Enter Citizenship I.D ...."));
		search.setFont(new Font("Jost", Font.PLAIN, 14));
		search.setForeground(new Color(61, 63, 64, 180));
		search.setBounds(471, 203, 200, 23);
		panel.add(search);

		JLabel firstName = new JLabel("First Name");
		firstName.setFont(new Font("Jost", Font.PLAIN, 16));
		firstName.setForeground(Color.decode("#6D6767"));
		firstName.setBounds(323,311,74,23);
		panel.add(firstName);

		JLabel firstNameLine = new JLabel();
		firstNameLine.setBounds(311,339,190,1);
		firstNameLine.setBackground(Color.decode("#000000"));
		firstNameLine.setOpaque(true);
		panel.add(firstNameLine);

		JLabel middleName = new JLabel("Middle Name");
		middleName.setFont(new Font("Jost", Font.PLAIN, 16));
		middleName.setForeground(Color.decode("#6D6767"));
		middleName.setBounds(577,311,93,23);
		panel.add(middleName);

		JLabel middleNameLine = new JLabel();
		middleNameLine.setBounds(565,339,190,1);
		middleNameLine.setBackground(Color.decode("#000000"));
		middleNameLine.setOpaque(true);
		panel.add(middleNameLine);

		JLabel lastName = new JLabel("Last Name");
		lastName.setFont(new Font("Jost", Font.PLAIN, 16));
		lastName.setForeground(Color.decode("#6D6767"));
		lastName.setBounds(834,311,74,23);
		panel.add(lastName);

		JLabel lastNameLine = new JLabel();
		lastNameLine.setBounds(822,339,190,1);
		lastNameLine.setBackground(Color.decode("#000000"));
		lastNameLine.setOpaque(true);
		panel.add(lastNameLine);

		JLabel DOBLabel = new JLabel("D.O.B");
		DOBLabel.setFont(new Font("Jost", Font.PLAIN, 16));
		DOBLabel.setForeground(Color.decode("#000000"));
		DOBLabel.setBounds(320,400,80,23);
		panel.add(DOBLabel);

		JLabel DOB = new JLabel("DD  MM  YYYY");
		DOB.setFont(new Font("Jost", Font.PLAIN, 16));
		DOB.setForeground(Color.decode("#6D6767"));
		DOB.setHorizontalAlignment(SwingConstants.LEFT);
		DOB.setBounds(390,400,120,23);
		panel.add(DOB);

		JLabel AgeLabel = new JLabel("Age");
		AgeLabel.setFont(new Font("Jost", Font.PLAIN, 16));
		AgeLabel.setForeground(Color.decode("#000000"));
		AgeLabel.setBounds(622,400,30,23);
		panel.add(AgeLabel);

		JLabel Age = new JLabel("NA");
		Age.setFont(new Font("Jost", Font.PLAIN, 16));
		Age.setForeground(Color.decode("#6D6767"));
		Age.setHorizontalAlignment(SwingConstants.LEFT);
		Age.setBounds(676,400,30,23);
		panel.add(Age);

		JLabel MobileLabel = new JLabel("Mobile");
		MobileLabel.setFont(new Font("Jost", Font.PLAIN, 16));
		MobileLabel.setForeground(Color.decode("#000000"));
		MobileLabel.setBounds(837,400,47,23);
		panel.add(MobileLabel);

		JLabel Mobile = new JLabel("9800000000");
		Mobile.setFont(new Font("Jost", Font.PLAIN, 16));
		Mobile.setForeground(Color.decode("#6D6767"));
		Mobile.setHorizontalAlignment(SwingConstants.LEFT);
		Mobile.setBounds(907,400,100,23);
		panel.add(Mobile);

		JLabel permanentAdress = new JLabel("Permanent Name");
		permanentAdress.setFont(new Font("Jost", Font.PLAIN, 16));
		permanentAdress.setForeground(Color.decode("#6D6767"));
		permanentAdress.setBounds(323,479,132,23);
		panel.add(permanentAdress);

		JLabel permanentAddressLine = new JLabel();
		permanentAddressLine.setBounds(311,507,190,1);
		permanentAddressLine.setBackground(Color.decode("#000000"));
		permanentAddressLine.setOpaque(true);
		panel.add(permanentAddressLine);

		JLabel temporaryAddress = new JLabel("Temporary Address");
		temporaryAddress.setFont(new Font("Jost", Font.PLAIN, 16));
		temporaryAddress.setForeground(Color.decode("#6D6767"));
		temporaryAddress.setBounds(577,479,130,23);
		panel.add(temporaryAddress);

		JLabel temporaryAddressLine = new JLabel();
		temporaryAddressLine.setBounds(565,507,190,1);
		temporaryAddressLine.setBackground(Color.decode("#000000"));
		temporaryAddressLine.setOpaque(true);
		panel.add(temporaryAddressLine);

		/*JLabel department = new JLabel("Department");
		department.setFont(new Font("Jost", Font.PLAIN, 16));
		department.setForeground(Color.decode("#6D6767"));
		department.setBounds(834,479,80,23);
		panel.add(department);*/
		
		String ranks[] = { "Inspector", "Sub Inspector", "Head Constable", "Constable"};
		JComboBox<String> rank = new JComboBox<>(ranks);
		rank.setSelectedItem("Inspector");
		rank.setBounds(820,490,200,23);
		rank.setFont(new Font("Jost", Font.PLAIN, 16));
		rank.setForeground(Color.decode("#6D6767"));
		rank.setBackground(Color.decode("#ffffff"));
		rank.setFocusable(false);
		panel.add(rank);

		JLabel password = new JLabel("Password");
		password.setFont(new Font("Jost", Font.PLAIN, 16));
		password.setForeground(Color.decode("#6D6767"));
		password.setBounds(419,578,80,23);
		panel.add(password);

		JLabel passwordLine = new JLabel();
		passwordLine.setBounds(408,606,190,1);
		passwordLine.setBackground(Color.decode("#000000"));
		passwordLine.setOpaque(true);
		panel.add(passwordLine);

		JLabel privateKey = new JLabel("Private Key");
		privateKey.setFont(new Font("Jost", Font.PLAIN, 16));
		privateKey.setForeground(Color.decode("#6D6767"));
		privateKey.setBounds(714,578,80,23);
		panel.add(privateKey);

		JLabel privateKeyLine = new JLabel();
		privateKeyLine.setBounds(702,606,190,1);
		privateKeyLine.setBackground(Color.decode("#000000"));
		privateKeyLine.setOpaque(true);
		panel.add(privateKeyLine);

		JLabel errorField = new JLabel("* No citizen of this I.D exists");
		errorField.setVisible(false);
		errorField.setFont(new Font("Jost", Font.PLAIN, 12));
		errorField.setForeground(Color.decode("#CE0037"));
		errorField.setBounds(424,254,176,20);
		panel.add(errorField);


		JLabel autofill = new JLabel("Autofill");
		autofill.setFont(new Font("Jost", Font.PLAIN, 16));
		autofill.setBackground(Color.decode("#1A75D5"));
		autofill.setOpaque(true);
		autofill.setHorizontalAlignment(SwingConstants.CENTER);
		autofill.setForeground(new Color(255, 255, 255, 240));
		autofill.setCursor(new Cursor(Cursor.HAND_CURSOR));
		autofill.setBounds(786,195,116,40);
		autofill.addMouseListener(Hover.newColor(autofill, "#1A75D5", "#165EAA"));
		autofill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(! search.getText().isEmpty()){
					Citizen citizen = citizenController.search(Long.parseLong(search.getText()));
					if(citizen != null){
						firstName.setText(citizen.getFirstName());
						middleName.setText(citizen.getMiddleName());
						lastName.setText(citizen.getLastName());
						Mobile.setText(citizen.getContact());
						DOB.setText(citizen.getDOB().toString());
						permanentAdress.setText(citizen.getPermanentAddress());
						temporaryAddress.setText(citizen.getTemporaryAddress());
						password.setText(PasswordGenerator.generate());
						privateKey.setText(PasswordGenerator.generate());
						Age.setText(DateTimeUtil.elapsedPresent(citizen.getDOB().toLocalDate()).toString());
						errorField.setVisible(false);
					}
					else{
						firstName.setText("NA");
						middleName.setText("NA");
						lastName.setText("NA");
						Mobile.setText("NA");
						DOB.setText("NA");
						permanentAdress.setText("NA");
						temporaryAddress.setText("NA");
						password.setText("NA");
						privateKey.setText("NA");
						Age.setText("NA");
						errorField.setVisible(true);
					}
				}
			}
		});
		panel.add(autofill);

		JLabel register = new JLabel("Register");
		register.setFont(new Font("Jost", Font.PLAIN, 16));
		register.setBackground(Color.decode("#1A75D5"));
		register.setOpaque(true);
		register.setHorizontalAlignment(SwingConstants.CENTER);
		register.setForeground(new Color(255, 255, 255, 240));
		register.setCursor(new Cursor(Cursor.HAND_CURSOR));
		register.setBounds(568,671,184,40);
		register.addMouseListener(Hover.newColor(register, "#1A75D5", "#165EAA"));
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				Police police = new Police(null, password.getText(), "KTM" , (String) rank.getSelectedItem(), Long.parseLong(search.getText()), null);
				policeController.register(police);
			}
		});
		panel.add(register);

		JLabel dashboardIcon = new JLabel();
		dashboardIcon.setBounds(36, 125, 35, 35);
		dashboardIcon.setIcon(new ImageIcon("resources/artboards/dash-icon-default.png"));
		panel.add(dashboardIcon);

		JLabel recordsIcon = new JLabel();
		recordsIcon.setBounds(39, 285, 35, 35);
		recordsIcon.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/records-icon-default.png").getImage(), recordsIcon));
		panel.add(recordsIcon);

		JLabel officersIcon = new JLabel();
		officersIcon.setBounds(44, 349, 35, 25);
		officersIcon.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/police-icon-selected.png").getImage(), officersIcon));
		panel.add(officersIcon);

		JLabel citizensIcon = new JLabel();
		citizensIcon.setBounds(39, 413, 35, 35);
		citizensIcon.setIcon(new ImageIcon("resources/artboards/citizen-icon-default.png"));
		panel.add(citizensIcon);

		JLabel convictsIcon = new JLabel();
		convictsIcon.setBounds(39, 477, 35, 35);
		convictsIcon.setIcon(new ImageIcon("resources/artboards/convict-icon-default.png"));
		panel.add(convictsIcon);

		JLabel sideMenuBar = new JLabel();
		sideMenuBar.setBounds(0, 0, 108, 841);
		sideMenuBar.setBackground(Color.decode("#002349"));
		sideMenuBar.setOpaque(true);
		panel.add(sideMenuBar);

		JLabel backgroundLogin = new JLabel();
		backgroundLogin.setOpaque(true);
		backgroundLogin.setBounds(108, 0, 1093, 841);
		backgroundLogin.setIcon(new ImageIcon("resources/artboards/register-background.png"));
		panel.add(backgroundLogin);

	}

	public JPanel getFrame() {
		return panel;
	}
}
