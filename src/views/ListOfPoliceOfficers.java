package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CitizenController;
import controller.PoliceController;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import model.Citizen;
import model.Police;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;

public class PoliceList extends JFrame {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private final PoliceController policeController;
    private JFrame frame;
    private JPanel panel;

    public PoliceList() {
        this.policeController = new PoliceControllerImpl(panel);
        initialize();
    }
    private static JPanel createPanel(Police police) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(860, 117));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(null);
        panel.setLayout(null);

        RoundedLabel details = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        details.setBounds(618, 85, 80, 20);
        details.setBackground(Color.decode("#EEF7FE"));
        details.setText("Details");
        details.setFont(new Font("Jost", Font.PLAIN, 13));
        details.setForeground(Color.decode("#647DC4"));
        details.setHorizontalAlignment(SwingConstants.CENTER);
        details.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 12));
        details.addMouseListener(Hover.animateOutline(details, "#EEF7FE", "#647DC4", "#26449E", "#EEF7FE"));
        details.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(details);
        
        JLabel citizenNoTitle = new JLabel();
        citizenNoTitle.setText("Police ID.");
        citizenNoTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenNoTitle.setForeground(Color.decode("#415EB6"));
        citizenNoTitle.setBounds(36, 20, 141, 32);
        panel.add(citizenNoTitle);

        JLabel firstNameTitle = new JLabel();
        firstNameTitle.setText("First Name.");
        firstNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        firstNameTitle.setForeground(Color.decode("#415EB6"));
        firstNameTitle.setBounds(36, 48, 141, 32);
        panel.add(firstNameTitle);

        JLabel lastNameTitle = new JLabel();
        lastNameTitle.setText("Last Name.");
        lastNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        lastNameTitle.setForeground(Color.decode("#415EB6"));
        lastNameTitle.setBounds(36, 76, 141, 32);
        panel.add(lastNameTitle);

        JLabel policeID = new JLabel();
        policeID.setText(police.getPoliceID().toString());
        policeID.setHorizontalAlignment(SwingConstants.LEFT);
        policeID.setFont(new Font("Jost", Font.PLAIN, 15));
        policeID.setForeground(Color.decode("#93AEF8"));
        policeID.setBounds(142, 20, 141, 32);
        panel.add(policeID);

        JLabel firstName = new JLabel();
        firstName.setText(police.getCitizen().getFirstName());
        firstName.setFont(new Font("Jost", Font.PLAIN, 15));
        firstName.setForeground(Color.decode("#93AEF8"));
        firstName.setBounds(122, 48, 141, 32);
        panel.add(firstName);

        JLabel lastName = new JLabel();
        lastName.setText(police.getCitizen().getLastName());
        lastName.setFont(new Font("Jost", Font.PLAIN, 15));
        lastName.setForeground(Color.decode("#93AEF8"));
        lastName.setBounds(122, 76, 141, 32);
        panel.add(lastName);

        JLabel DOBTitle = new JLabel();
        DOBTitle.setText("D.O.B");
        DOBTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        DOBTitle.setForeground(Color.decode("#415EB6"));
        DOBTitle.setBounds(257, 20, 141, 32);
        panel.add(DOBTitle);

        JLabel genderTitle = new JLabel();
        genderTitle.setText("Gender");
        genderTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        genderTitle.setForeground(Color.decode("#415EB6"));
        genderTitle.setBounds(257, 48, 141, 32);
        panel.add(genderTitle);

        JLabel contactNoTitle = new JLabel();
        contactNoTitle.setText("Contact No.");
        contactNoTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        contactNoTitle.setForeground(Color.decode("#415EB6"));
        contactNoTitle.setBounds(257, 76, 141, 32);
        panel.add(contactNoTitle);

        