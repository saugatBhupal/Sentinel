package views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Document;
import views.widget.DateTimeWidget;

public class ResetPassword {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private JFrame frame;
    private JPanel panel;

    public ResetPassword() {
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
        parentPageTitle.setBounds(239, 61, 141, 32);
        panel.add(parentPageTitle);

        JLabel separator = new JLabel();
        separator.setIcon(new ImageIcon("resources/artboards/left-arrow-1.png"));
        separator.setBounds(385, 70, 16, 16);
        panel.add(separator);

        JLabel currentPageTitle = new JLabel();
        currentPageTitle.setText("Register Officer");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(412, 61, 151, 32);
        panel.add(currentPageTitle);

        DateTimeWidget.addWidget(panel);

        JLabel dashboardIcon = new JLabel();
        dashboardIcon.setBounds(36, 125, 35, 35);
        dashboardIcon.setIcon(new ImageIcon("resources/artboards/dash-icon-default.png"));
        panel.add(dashboardIcon);
        JLabel recordsIcon = new JLabel();
        recordsIcon.setBounds(39, 285, 35, 35);
        recordsIcon.setIcon(imagePlugins
                .resize(new ImageIcon("resources/artboards/records-icon-default.png").getImage(), recordsIcon));
        panel.add(recordsIcon);

        JLabel officersIcon = new JLabel();
        officersIcon.setBounds(44, 349, 35, 25);
        officersIcon.setIcon(imagePlugins
                .resize(new ImageIcon("resources/artboards/police-icon-selected.png").getImage(), officersIcon));
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

        JLabel policeIdLabel = new JLabel();
        policeIdLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        policeIdLabel.setBounds(509, 236, 134, 31);
        policeIdLabel.setText("Police I.D");
        panel.add(policeIdLabel);

        JTextField policeIdField = new JTextField();
        policeIdField.setFont(new Font("Jost", Font.PLAIN, 18));
        policeIdField.setBackground(Color.decode("#FFFFFF"));
        policeIdField.setBorder(null);
        policeIdField.setForeground(Color.decode("#6D6767"));
        policeIdField.addFocusListener(Focus.setPlaceholder(policeIdField, "Police I.D"));
        policeIdField.setBounds(516, 269, 300, 23);
        panel.add(policeIdField);

        JLabel policeIdLine = new JLabel();
        policeIdLine.setBounds(509, 299, 270, 1);
        policeIdLine.setBackground(Color.decode("#000000"));
        policeIdLine.setOpaque(true);
        panel.add(policeIdLine);
        JLabel newPasswordLabel = new JLabel();
        newPasswordLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        newPasswordLabel.setBounds(509, 333, 134, 31);
        newPasswordLabel.setText("New Password");
        panel.add(newPasswordLabel);

		JPasswordField newPasswordField = new JPasswordField();
		newPasswordField.setFont(new Font("Jost", Font.PLAIN, 18));
		newPasswordField.setBorder(null);
		newPasswordField.setForeground(Color.decode("#002349"));
		newPasswordField.getDocument().addDocumentListener(Document.hidePassword(newPasswordField));
		newPasswordField.addFocusListener(Focus.setPlaceholder(newPasswordField, "Enter Password...."));
		newPasswordField.setBounds(516, 365, 300, 27);
		panel.add(newPasswordField);

        JLabel newPasswordLine = new JLabel();
        newPasswordLine.setBounds(509, 396, 270, 1);
        newPasswordLine.setBackground(Color.decode("#000000"));
        newPasswordLine.setOpaque(true);
        panel.add(newPasswordLine);

        JLabel confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        confirmPasswordLabel.setBounds(509, 430, 134, 31);
        confirmPasswordLabel.setText("Confirm Password");
        panel.add(confirmPasswordLabel);

		JPasswordField confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("Jost", Font.PLAIN, 18));
		confirmPasswordField.setBorder(null);
		confirmPasswordField.setForeground(Color.decode("#002349"));
		confirmPasswordField.getDocument().addDocumentListener(Document.hidePassword(confirmPasswordField));
		confirmPasswordField.addFocusListener(Focus.setPlaceholder(confirmPasswordField, "Enter Password...."));
		confirmPasswordField.setBounds(516, 465, 300, 27);
		panel.add(confirmPasswordField);

        JLabel confirmPasswordLine = new JLabel();
        confirmPasswordLine.setBounds(509, 493, 270, 1);
        confirmPasswordLine.setBackground(Color.decode("#000000"));
        confirmPasswordLine.setOpaque(true);
        panel.add(confirmPasswordLine);

        JLabel privateKeyLabel = new JLabel();
        privateKeyLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        privateKeyLabel.setBounds(509, 527, 134, 31);
        privateKeyLabel.setText("Private");
        panel.add(privateKeyLabel);

        JTextField privateKeyField = new JTextField();
        privateKeyField.setFont(new Font("Jost", Font.PLAIN, 18));
        privateKeyField.setBackground(Color.decode("#FFFFFF"));
        privateKeyField.setBorder(null);
        privateKeyField.setForeground(Color.decode("#6D6767"));
        privateKeyField.addFocusListener(Focus.setPlaceholder(privateKeyField, "Private Key"));
        privateKeyField.setBounds(516, 562, 300, 23);
        panel.add(privateKeyField);

        JLabel privateKeyLine = new JLabel();
        privateKeyLine.setBounds(509, 590, 270, 1);
        privateKeyLine.setBackground(Color.decode("#000000"));
        privateKeyLine.setOpaque(true);
        panel.add(privateKeyLine);

        JLabel backgroundLogin = new JLabel();
        backgroundLogin.setOpaque(true);
        backgroundLogin.setBounds(108, 0, 1093, 841);
        backgroundLogin.setIcon(new ImageIcon("resources/artboards/reset-password-background.png"));
        panel.add(backgroundLogin);

    }

    public JPanel getFrame() {
        return panel;
    }
}

       