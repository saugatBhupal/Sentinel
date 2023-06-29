package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Map;

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

import org.apache.batik.gvt.text.GVTAttributedCharacterIterator.TextAttribute;

import controller.CitizenController;
import controller.controllerImpl.CitizenControllerImpl;
import model.Citizen;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;

public class CitizenList extends JFrame {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private final CitizenController citizenController;
    private JFrame frame;
    private JPanel panel;

    public CitizenList() {
        this.citizenController = new CitizenControllerImpl();
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
        separator.setBounds(375, 70, 16, 16);
        panel.add(separator);

        JLabel currentPageTitle = new JLabel();
        currentPageTitle.setText("List of citizens");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(400, 61, 151, 32);
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

        JTextField search = new JTextField();
        search.setBorder(null);
        search.addMouseListener(Hover.focusable(search));
        search.addFocusListener(Focus.setPlaceholder(search, "Search for existing F.I.Rs"));
        search.setFont(new Font("Jost", Font.PLAIN, 14));
        search.setForeground(new Color(61, 63, 64, 180));
        search.setBounds(414, 164, 365, 30);
        panel.add(search);

        RoundedLabel searchButton = new RoundedLabel("", 12, Color.decode("#1A75D5"), 9);
        searchButton.setBounds(810, 160, 100, 35);
        searchButton.setBackground(Color.decode("#1A75D5"));
        searchButton.setText("Search");
        searchButton.setFont(new Font("Jost", Font.PLAIN, 14));
        searchButton.setForeground(Color.decode("#FFFFFF"));
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 12));
        panel.add(searchButton);

        JLabel citizenNoTitle = new JLabel();
        citizenNoTitle.setText("Citizenship No.");
        citizenNoTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenNoTitle.setForeground(Color.decode("#415EB6"));
        citizenNoTitle.setBounds(239, 252, 141, 32);
        panel.add(citizenNoTitle);

        JLabel firstNameTitle = new JLabel();
        firstNameTitle.setText("First Name.");
        firstNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        firstNameTitle.setForeground(Color.decode("#415EB6"));
        firstNameTitle.setBounds(239, 278, 141, 32);
        panel.add(firstNameTitle);

        JLabel lastNameTitle = new JLabel();
        lastNameTitle.setText("Last Name.");
        lastNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        lastNameTitle.setForeground(Color.decode("#415EB6"));
        lastNameTitle.setBounds(239, 305, 141, 32);
        panel.add(lastNameTitle);

        JLabel citizenshipNo = new JLabel();
        citizenshipNo.setText("20098987");
        citizenshipNo.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenshipNo.setForeground(Color.decode("#93AEF8"));
        citizenshipNo.setBounds(350, 252, 141, 32);
        panel.add(citizenshipNo);

        JLabel firstName = new JLabel();
        firstName.setText("Ram");
        firstName.setFont(new Font("Jost", Font.PLAIN, 15));
        firstName.setForeground(Color.decode("#93AEF8"));
        firstName.setBounds(350, 278, 141, 32);
        panel.add(firstName);

        JLabel lastName = new JLabel();
        lastName.setText("Chaudary");
        lastName.setFont(new Font("Jost", Font.PLAIN, 15));
        lastName.setForeground(Color.decode("#93AEF8"));
        lastName.setBounds(350, 305, 141, 32);
        panel.add(lastName);

        JLabel DOBTitle = new JLabel();
        DOBTitle.setText("D.O.B");
        DOBTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        DOBTitle.setForeground(Color.decode("#415EB6"));
        DOBTitle.setBounds(480, 252, 141, 32);
        panel.add(DOBTitle);

        JLabel genderTitle = new JLabel();
        genderTitle.setText("Gender");
        genderTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        genderTitle.setForeground(Color.decode("#415EB6"));
        genderTitle.setBounds(480, 278, 141, 32);
        panel.add(genderTitle);

        JLabel contactNoTitle = new JLabel();
        contactNoTitle.setText("Contact No.");
        contactNoTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        contactNoTitle.setForeground(Color.decode("#415EB6"));
        contactNoTitle.setBounds(480, 305, 141, 32);
        panel.add(contactNoTitle);

        JLabel dob = new JLabel();
        dob.setText("2023-04-13");
        dob.setFont(new Font("Jost", Font.PLAIN, 15));
        dob.setForeground(Color.decode("#93AEF8"));
        dob.setBounds(570, 252, 141, 32);
        panel.add(dob);

        JLabel gender = new JLabel();
        gender.setText("M");
        gender.setFont(new Font("Jost", Font.PLAIN, 15));
        gender.setForeground(Color.decode("#93AEF8"));
        gender.setBounds(570, 278, 141, 32);
        panel.add(gender);

        JLabel contactNo = new JLabel();
        contactNo.setText("9808763521");
        contactNo.setFont(new Font("Jost", Font.PLAIN, 15));
        contactNo.setForeground(Color.decode("#93AEF8"));
        contactNo.setBounds(570, 305, 141, 32);
        panel.add(contactNo);

        JLabel tempAddressTitle = new JLabel();
        tempAddressTitle.setText("Temporary Address");
        tempAddressTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        tempAddressTitle.setForeground(Color.decode("#415EB6"));
        tempAddressTitle.setBounds(710, 252, 141, 32);
        panel.add(tempAddressTitle);

        JLabel addressTitle = new JLabel();
        addressTitle.setText("Permanent Address");
        addressTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        addressTitle.setForeground(Color.decode("#415EB6"));
        addressTitle.setBounds(710, 278, 141, 32);
        panel.add(addressTitle);

        JLabel recordsTitle = new JLabel();
        recordsTitle.setText("Total Records");
        recordsTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        recordsTitle.setForeground(Color.decode("#415EB6"));
        recordsTitle.setBounds(710, 305, 141, 32);
        panel.add(recordsTitle);

        JLabel tempAddress = new JLabel();
        tempAddress.setText("Kathmandu, ward-3, Nepal");
        tempAddress.setFont(new Font("Jost", Font.PLAIN, 15));
        tempAddress.setForeground(Color.decode("#93AEF8"));
        tempAddress.setBounds(850, 252, 281, 32);
        panel.add(tempAddress);

        JLabel address = new JLabel();
        address.setText("Pokhara, ward-12, Nepal");
        address.setFont(new Font("Jost", Font.PLAIN, 15));
        address.setForeground(Color.decode("#93AEF8"));
        address.setBounds(850, 278, 281, 32);
        panel.add(address);

        JLabel records = new JLabel();
        records.setText("(12)");
        records.setFont(new Font("Jost", Font.PLAIN, 15));
        records.setForeground(Color.decode("#93AEF8"));
        records.setBounds(800, 305, 281, 32);
        panel.add(records);

        RoundedLabel details = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        details.setBounds(850, 314, 80, 20);
        details.setBackground(Color.decode("#EEF7FE"));
        details.setText("Details");
        details.setFont(new Font("Jost", Font.PLAIN, 13));
        details.setForeground(Color.decode("#647DC4"));
        details.setHorizontalAlignment(SwingConstants.CENTER);
        details.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 12));
        panel.add(details);

        RoundedLabel citizenBackground = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        citizenBackground.setBounds(210, 235, 860, 117);
        citizenBackground.setBackground(Color.decode("#EEF7FE"));
        panel.add(citizenBackground);

        JLabel backgroundLogin = new JLabel();
        backgroundLogin.setOpaque(true);
        backgroundLogin.setBounds(110, 0, 1093, 841);
        backgroundLogin.setIcon(new ImageIcon("resources/artboards/list-citizen-background.png"));
        panel.add(backgroundLogin);

    }

    public JPanel getFrame() {
        return panel;
    }
}