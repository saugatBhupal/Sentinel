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

		JTextField search = new JTextField();
		search.setBorder(null);
		search.addMouseListener(Hover.focusable(search));
		search.addFocusListener(Focus.setPlaceholder(search, "Search existing F.I.Rs"));
		search.setFont(new Font("Jost", Font.PLAIN, 14));
		search.setForeground(new Color(61, 63, 64, 180));
		search.setBounds(510, 166, 365, 30);
		panel.add(search);

		JLabel allFIRsIcon = new JLabel();
		allFIRsIcon.setBounds(351, 301, 53, 53);
		allFIRsIcon.setOpaque(true);
		allFIRsIcon.setIcon(
				imagePlugins.resize(new ImageIcon("resources/artboards/all-FIRs.png").getImage(), allFIRsIcon));
		panel.add(allFIRsIcon);

		JLabel allFIRsLine = new JLabel();
		allFIRsLine.setBounds(438, 290, 1, 100);
		allFIRsLine.setBackground(Color.decode("#1A75D5"));
		allFIRsLine.setOpaque(true);
		panel.add(allFIRsLine);

        JLabel viewAllFIRs = new JLabel();
		viewAllFIRs.setCursor(new Cursor(Cursor.HAND_CURSOR));
		viewAllFIRs.setFont(new Font("Jost", Font.PLAIN, 12));
		viewAllFIRs.setForeground(new Color(22, 91, 191,180));
		viewAllFIRs.setBounds(464, 358, 142, 55);
		viewAllFIRs.setText("View more");
		panel.add(viewAllFIRs);

        JLabel viewAllFIRsOverlay = new JLabel();
        viewAllFIRsOverlay.setBounds(464, 358, 142, 55);
        viewAllFIRsOverlay.addMouseListener(Hover.animate(viewAllFIRs, 464, 504));
        panel.add(viewAllFIRsOverlay);

		JLabel allFIRsText = new JLabel();
		allFIRsText.setFont(new Font("Jost", Font.PLAIN, 14));
		allFIRsText.setForeground(Color.decode("#002349"));
		allFIRsText.setBounds(351, 358, 142, 55);
		allFIRsText.setText("All F.I.Rs");
		panel.add(allFIRsText);

		JTextArea allFIRsDescription = new JTextArea();
		allFIRsDescription.setBounds(464, 301, 150, 78);
		allFIRsDescription.setText(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		allFIRsDescription.setForeground(Color.decode("#002349"));
		allFIRsDescription.setEditable(false);
		allFIRsDescription.setLineWrap(true);
		allFIRsDescription.setWrapStyleWord(true);
		font = allFIRsText.getFont();
		allFIRsDescription.setFont(font);
		panel.add(allFIRsDescription);


		JLabel newFIRsIcon = new JLabel();
		newFIRsIcon.setBounds(774, 301, 53, 53);
		newFIRsIcon.setOpaque(true);
		newFIRsIcon.setIcon(
				imagePlugins.resize(new ImageIcon("resources/artboards/new-FIR.png").getImage(), newFIRsIcon));
		panel.add(newFIRsIcon);

		JLabel newFIRsLine = new JLabel();
		newFIRsLine.setBounds(862, 290, 1, 100);
		newFIRsLine.setBackground(Color.decode("#1A75D5"));
		newFIRsLine.setOpaque(true);
		panel.add(newFIRsLine);

        JLabel viewNewFIRs = new JLabel();
		viewNewFIRs.setCursor(new Cursor(Cursor.HAND_CURSOR));
		viewNewFIRs.setFont(new Font("Jost", Font.PLAIN, 12));
		viewNewFIRs.setForeground(new Color(22, 91, 191,180));
		viewNewFIRs.setBounds(887, 358, 142, 55);
		viewNewFIRs.setText("View more");
		panel.add(viewNewFIRs);

		JLabel newFIRsText = new JLabel();
		newFIRsText.setFont(new Font("Jost", Font.PLAIN, 14));
		newFIRsText.setForeground(Color.decode("#002349"));
		newFIRsText.setBounds(774, 358, 142, 55);
		newFIRsText.setText("New F.I.R");
		panel.add(newFIRsText);

		JTextArea newFIRsDescription = new JTextArea();
		newFIRsDescription.setBounds(887, 301, 150, 78);
		newFIRsDescription.setText(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		newFIRsDescription.setForeground(Color.decode("#002349"));
		newFIRsDescription.setEditable(false);
		newFIRsDescription.setLineWrap(true);
		newFIRsDescription.setWrapStyleWord(true);
		font = allFIRsText.getFont();
		newFIRsDescription.setFont(font);
		panel.add(newFIRsDescription);
