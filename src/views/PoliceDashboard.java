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
