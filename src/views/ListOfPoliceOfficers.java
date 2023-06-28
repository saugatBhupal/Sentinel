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

        JLabel dob = new JLabel();
        dob.setText(police.getCitizen().getDOB().toString());
        dob.setFont(new Font("Jost", Font.PLAIN, 15));
        dob.setForeground(Color.decode("#93AEF8"));
        dob.setBounds(343, 20, 141, 32);
        panel.add(dob);

        JLabel gender = new JLabel();
        gender.setText("M");
        gender.setFont(new Font("Jost", Font.PLAIN, 15));
        gender.setForeground(Color.decode("#93AEF8"));
        gender.setBounds(343, 48, 141, 32);
        panel.add(gender);

        JLabel contactNo = new JLabel();
        contactNo.setText(police.getCitizen().getContact());
        contactNo.setFont(new Font("Jost", Font.PLAIN, 15));
        contactNo.setForeground(Color.decode("#93AEF8"));
        contactNo.setBounds(343, 76, 141, 32);
        panel.add(contactNo);

        JLabel tempAddressTitle = new JLabel();
        tempAddressTitle.setText("Temporary Address");
        tempAddressTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        tempAddressTitle.setForeground(Color.decode("#415EB6"));
        tempAddressTitle.setBounds(476, 20, 141, 32);
        panel.add(tempAddressTitle);

        JLabel addressTitle = new JLabel();
        addressTitle.setText("Permanent Address");
        addressTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        addressTitle.setForeground(Color.decode("#415EB6"));
        addressTitle.setBounds(476, 48, 141, 32);
        panel.add(addressTitle);

        JLabel recordsTitle = new JLabel();
        recordsTitle.setText("Total Records");
        recordsTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        recordsTitle.setForeground(Color.decode("#415EB6"));
        recordsTitle.setBounds(476, 76, 141, 32);
        panel.add(recordsTitle);

        JLabel tempAddress = new JLabel();
        tempAddress.setText(police.getCitizen().getTemporaryAddress());
        tempAddress.setFont(new Font("Jost", Font.PLAIN, 15));
        tempAddress.setForeground(Color.decode("#93AEF8"));
        tempAddress.setBounds(615, 20, 281, 32);
        panel.add(tempAddress);

        JLabel address = new JLabel();
        address.setText(police.getCitizen().getPermanentAddress());
        address.setFont(new Font("Jost", Font.PLAIN, 15));
        address.setForeground(Color.decode("#93AEF8"));
        address.setBounds(615, 48, 281, 32);
        panel.add(address);

        JLabel records = new JLabel();
        records.setText("(12)");
        records.setFont(new Font("Jost", Font.PLAIN, 15));
        records.setForeground(Color.decode("#93AEF8"));
        records.setBounds(575, 76, 281, 32);
        panel.add(records);

        RoundedLabel citizenBackground = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        citizenBackground.setBounds(0, 0, 860, 117);
        citizenBackground.setBackground(Color.decode("#EEF7FE"));
        panel.add(citizenBackground);

        return panel;
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

        
    

        