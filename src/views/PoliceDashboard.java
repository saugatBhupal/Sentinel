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