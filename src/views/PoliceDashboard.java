package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.batik.gvt.text.GVTAttributedCharacterIterator.TextAttribute;

import controller.CitizenController;
import controller.PoliceController;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import views.widget.DateTimeWidget;

public class PoliceDashboard extends JFrame{
	private final CitizenController citizenController;
	private final PoliceController policeController;
	private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
	private JFrame frame;
	private JPanel panel;
	private Font font;
	private Map<TextAttribute, Object> attributes;

	public PoliceDashboard() {
		this.citizenController = new CitizenControllerImpl();
		this.policeController = new PoliceControllerImpl(panel);
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
		parentPageTitle.setText("F.I.R");
		parentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
		parentPageTitle.setForeground(Color.decode("#002349"));
		parentPageTitle.setBounds(239, 61, 141, 32);
		panel.add(parentPageTitle);

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
