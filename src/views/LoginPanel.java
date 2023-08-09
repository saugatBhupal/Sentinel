package views;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.PoliceController;
import controller.controllerImpl.PoliceControllerImpl;
import model.Case;
import model.Police;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Document;
import utils.ui.event.Focus;
import utils.ui.event.Hover;

import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;

public class LoginPanel extends JFrame {

	private final PoliceController policeController;
	private JFrame frame;
	private JPanel panel;
	private Font font;
	private Map<TextAttribute, Object> attributes;
	private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);

	public LoginPanel(App app) {
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

		JLabel login = new JLabel();
		login.setFont(new Font("Jost", Font.PLAIN, 24));
		login.setForeground(Color.decode("#165BBF"));
		login.setBounds(768, 134, 246, 38);
		login.setText("Welcome Back !");
		panel.add(login);

		JLabel loginSub = new JLabel();
		loginSub.setText("Please Enter Your Credentials To Continue");
		loginSub.setFont(new Font("Jost", Font.PLAIN, 18));
		loginSub.setForeground(new Color(0, 35, 73, 150));
		loginSub.setBounds(682, 174, 332, 26);
		panel.add(loginSub);

		JTextField citizenshipField = new JTextField();
		citizenshipField.setFont(new Font("Jost", Font.PLAIN, 18));
		citizenshipField.setBorder(null);
		citizenshipField.setForeground(Color.decode("#002349"));
		citizenshipField.setText("Enter I.D Here ....");
		citizenshipField.addFocusListener(Focus.setPlaceholder(citizenshipField, "Enter I.D Here ...."));
		citizenshipField.setBounds(705, 339, 300, 27);
		panel.add(citizenshipField);

		JLabel citizenshipLabel = new JLabel();
		citizenshipLabel.setFont(new Font("Jost", Font.PLAIN, 18));
		citizenshipLabel.setForeground(Color.decode("#000000"));
		citizenshipLabel.setBounds(697, 299, 134, 31);
		citizenshipLabel.setText("Citizenship I.D");
		panel.add(citizenshipLabel);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Jost", Font.PLAIN, 18));
		passwordField.setBorder(null);
		passwordField.setForeground(Color.decode("#002349"));
		passwordField.setText("Enter Password....");
		passwordField.getDocument().addDocumentListener(Document.hidePassword(passwordField));
		passwordField.addFocusListener(Focus.setPlaceholder(passwordField, "Enter Password...."));
		passwordField.setBounds(705, 451, 300, 27);
		panel.add(passwordField);

		JLabel forgotPassword = new JLabel("Forgot Password ?");
		forgotPassword.setFont(new Font("Jost", Font.PLAIN, 12));
		forgotPassword.setForeground(Color.decode("#165BBF"));
		forgotPassword.setBounds(915, 405, 100, 31);
		forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(forgotPassword);

		JLabel candidateLine = new JLabel();
		candidateLine.setBounds(697, 375, 319, 1);
		candidateLine.setBackground(Color.decode("#000000"));
		candidateLine.setOpaque(true);
		panel.add(candidateLine);

		JLabel passwordLine = new JLabel();
		passwordLine.setBounds(697, 483, 319, 1);
		passwordLine.setBackground(Color.decode("#000000"));
		passwordLine.setOpaque(true);
		panel.add(passwordLine);

		JLabel passwordLabel = new JLabel();
		passwordLabel.setFont(new Font("Jost", Font.PLAIN, 18));
		passwordLabel.setForeground(Color.decode("#000000"));
		passwordLabel.setBounds(697, 405, 134, 31);
		passwordLabel.setText("Password");
		panel.add(passwordLabel);

		JLabel loginButton = new JLabel("Login");
		loginButton.setOpaque(true);
		loginButton.setFont(new Font("Jost", Font.PLAIN, 18));
		loginButton.setForeground(Color.decode("#ffffff"));
		loginButton.setBackground(Color.decode("#165EAA"));
		loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		loginButton.setBounds(697, 554, 319, 42);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addMouseListener(Hover.newColor(loginButton,"#165EAA" , "#013B7A"));
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				policeController.authenticate(new Police(Long.parseLong(citizenshipField.getText()), new String(passwordField.getPassword())));
			}
		});
		panel.add(loginButton);

		JLabel clickHere = new JLabel();
		clickHere.setText("Click Here");
		clickHere.setCursor(new Cursor(Cursor.HAND_CURSOR));
		clickHere.setFont(new Font("Jost", Font.PLAIN, 16));
		clickHere.setForeground(new Color(22, 91, 191,180));
		clickHere.setBounds(892, 690, 229, 23);
		panel.add(clickHere);
		
		JLabel loginSupport = new JLabel();
		loginSupport.setText("Need help logging in?");
		loginSupport.setFont(new Font("Jost", Font.PLAIN, 16));
		loginSupport.setForeground(Color.decode("#002349"));
		loginSupport.setBounds(733, 690, 229, 23);
		panel.add(loginSupport);

		

		JLabel logo = new JLabel();
		logo.setBounds(29, 30, 85, 85);
		logo.setIcon(new ImageIcon("resources/artboards/sentinel-logo.png"));
		panel.add(logo); 

		JLabel logoText = new JLabel();
		logoText.setFont(new Font("Jost", Font.PLAIN, 26));
		logoText.setForeground(Color.decode("#FFFFFF"));
		logoText.setBounds(125, 30, 106, 46);
		logoText.setText("Sentinel");
		panel.add(logoText);

		JLabel logoSubText = new JLabel();
		logoSubText.setFont(new Font("Jost", Font.PLAIN, 16));
		logoSubText.setForeground(Color.decode("#FFFFFF"));
		logoSubText.setBounds(125, 76, 237, 27);
		logoSubText.setText("Criminal records management");
		panel.add(logoSubText);

		JLabel sideMenuImage = new JLabel();
		sideMenuImage.setBounds(0, 166, 460, 383);
		sideMenuImage.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/login-image.png").getImage(), sideMenuImage));
		sideMenuImage.setOpaque(false);
		panel.add(sideMenuImage);

		JLabel loginTitle = new JLabel();
		loginTitle.setBounds(123, 596, 205, 32);
		loginTitle.setText("Login To Your Account");
		loginTitle.setForeground(Color.decode("#BDCEDF"));
		font = loginTitle.getFont();
		attributes = new HashMap<>();
		attributes.put(TextAttribute.FAMILY, "Jost");
		attributes.put(TextAttribute.SIZE, 20);
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WIDTH_CONDENSED);
		font = font.deriveFont(attributes);
		loginTitle.setFont(font);
		panel.add(loginTitle);

		JTextArea loginDescription = new JTextArea();
		loginDescription.setBounds(53, 652, 382, 78);
		loginDescription.setText(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend lorem justo, nec dignissim leo fermentum non. Nunc rutrum fermentum.");
		loginDescription.setForeground(Color.decode("#E9DDDD"));
		loginDescription.setBackground(Color.decode("#002349"));
		loginDescription.setEditable(false);
		loginDescription.setLineWrap(true);
		loginDescription.setWrapStyleWord(true);
		font = loginTitle.getFont();
		attributes = new HashMap<>();
		attributes.put(TextAttribute.FAMILY, "Jost");
		attributes.put(TextAttribute.SIZE, 14);
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WIDTH_CONDENSED);
		font = font.deriveFont(attributes);
		loginDescription.setFont(font);
		panel.add(loginDescription);

		JLabel line = new JLabel();
		line.setBounds(25, 745, 418, 1);
		line.setBackground(Color.decode("#F2F2F2"));
		line.setOpaque(true);
		panel.add(line);

		JLabel version = new JLabel("v 1.0");
		version.setFont(new Font("Jost", Font.PLAIN, 16));
		version.setForeground(new Color(255, 255, 255, 65));
		version.setBounds(25, 770, 44, 23);
		panel.add(version);

		JLabel FAQ = new JLabel("F.A.Q");
		FAQ.setFont(new Font("Jost", Font.PLAIN, 16));
		FAQ.setForeground(Color.decode("#DCDCDC"));
		FAQ.setBounds(374, 770, 44, 23);
		panel.add(FAQ);

		JLabel faqIcon = new JLabel("?");
		faqIcon.setFont(new Font("Jost", Font.PLAIN, 22));
		faqIcon.setForeground(Color.decode("#FFFFFF"));
		faqIcon.setBounds(425, 764, 13, 32);
		panel.add(faqIcon);

		JLabel right = new JLabel();
		right.setBounds(480, -6, 734, 841);
		right.setOpaque(true);
		right.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/login-right.png").getImage(), right));
		panel.add(right);

		JLabel sideMenuBar = new JLabel("");
		sideMenuBar.setBounds(0, 0, 476, 841);
		sideMenuBar.setBackground(Color.decode("#002349"));
		sideMenuBar.setOpaque(true);
		panel.add(sideMenuBar);

		JLabel backgroundLogin = new JLabel();
		backgroundLogin.setOpaque(true);
		backgroundLogin.setBounds(476, 0, 734, 841);
		backgroundLogin.setBackground(Color.decode("#FFFFFF"));
		panel.add(backgroundLogin);
		
	}

	public JPanel getFrame() {
		return panel;
	}
}
