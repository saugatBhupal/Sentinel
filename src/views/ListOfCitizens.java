package views;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;

public class ListOfCitizens extends JFrame {
    private final CitizenController citizenController;
    private final PoliceController policeController;
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private JFrame frame;
    private JPanel panel;
    private Font font;
    private Map<TextAttribute, Object> attributes;

    public ListOfCitizens() {
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
        parentPageTitle.setText("Police Officers");
        parentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        parentPageTitle.setForeground(Color.decode("#002349"));
        parentPageTitle.setBounds(251,73,141,32);
        panel.add(parentPageTitle);

        JLabel separator = new JLabel();
        separator.setIcon(new ImageIcon("resources/artboards/left-arrow-1.png"));
        separator.setBounds(409,80,16,16);
        panel.add(separator);

        JLabel currentPageTitle = new JLabel();
        currentPageTitle.setText("List of citizens");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(424, 74, 151, 31);
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
        search.addFocusListener(Focus.setPlaceholder(search, "Enter Citizenship I.D or Name"));
        search.setFont(new Font("Jost", Font.PLAIN, 14));
        search.setForeground(new Color(61, 63, 64, 180));
        search.setBounds(409,153,191,22);
        panel.add(search);

        JLabel citizenIdTitle = new JLabel();
        citizenIdTitle.setText("Citizenship No.");
        citizenIdTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenIdTitle.setForeground(Color.decode("#AC4040"));
        citizenIdTitle.setBounds(265,265,92,20);
        panel.add(citizenIdTitle);

        JLabel FirstNameTitle = new JLabel();
        FirstNameTitle.setText("First Name");
        FirstNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        FirstNameTitle.setForeground(Color.decode("#AC4040"));
        FirstNameTitle.setBounds(265,293,68,20);
        panel.add(FirstNameTitle);

        JLabel LastNameTitle = new JLabel();
        LastNameTitle.setText("Last Name");
        LastNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        LastNameTitle.setForeground(Color.decode("#AC4040"));
        LastNameTitle.setBounds(265,321,66,20);
        panel.add(LastNameTitle);

        JLabel citizenid = new JLabel();
        citizenid.setText("20098987");
        citizenid.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenid.setForeground(Color.decode("#CC8686"));
        citizenid.setBounds(371,265,64,20);
        panel.add(citizenid);

        JLabel FirstName = new JLabel();
        FirstName.setText("Ram");
        FirstName.setFont(new Font("Jost", Font.PLAIN, 15));
        FirstName.setForeground(Color.decode("#CC8686"));
        FirstName.setBounds(351,293,26,20);
        panel.add(FirstName);

        JLabel LastName = new JLabel();
        LastName.setText("Chaudhary");
        LastName.setFont(new Font("Jost", Font.PLAIN, 15));
        LastName.setForeground(Color.decode("#CC8686"));
        LastName.setBounds(351,321,65,20);
        panel.add(LastName);

        JLabel DateOfBirthTitle = new JLabel();
        DateOfBirthTitle.setText("D.O.B");
        DateOfBirthTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        DateOfBirthTitle.setForeground(Color.decode("#AC4040"));
        DateOfBirthTitle.setBounds(486,265,36,20);
        panel.add(DateOfBirthTitle);

        JLabel GenderTitle = new JLabel();
        GenderTitle.setText("Gender");
        GenderTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        GenderTitle.setForeground(Color.decode("#AC4040"));
        GenderTitle.setBounds(486,293,46,20);
        panel.add(GenderTitle);

        JLabel ContactNoTitle = new JLabel();
        ContactNoTitle.setText("Contact No.");
        ContactNoTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        ContactNoTitle.setForeground(Color.decode("#AC4040"));
        ContactNoTitle.setBounds(486,321,71,20);
        panel.add(ContactNoTitle);

        JLabel DateOfBirth = new JLabel();
        DateOfBirth.setText("2023-04-13");
        DateOfBirth.setFont(new Font("Jost", Font.PLAIN, 15));
        DateOfBirth.setForeground(Color.decode("#CC8686"));
        DateOfBirth.setBounds(572,265,69,20);
        panel.add(DateOfBirth);

        JLabel Gender = new JLabel();
        Gender.setText("M");
        Gender.setFont(new Font("Jost", Font.PLAIN, 15));
        Gender.setForeground(Color.decode("#CC8686"));
        Gender.setBounds(572,293,12,20);
        panel.add(Gender);

        JLabel ContactNo = new JLabel();
        ContactNo.setText("9808763521");
        ContactNo.setFont(new Font("Jost", Font.PLAIN, 15));
        ContactNo.setForeground(Color.decode("#CC8686"));
        ContactNo.setBounds(572,321,77,20);
        panel.add(ContactNo);
        
        JLabel TemporaryAddressTitle = new JLabel();
        TemporaryAddressTitle.setText("Contact No.");
        TemporaryAddressTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        TemporaryAddressTitle.setForeground(Color.decode("#AC4040"));
        TemporaryAddressTitle.setBounds(486,321,71,20);
        panel.add(TemporaryAddressTitle);


        JLabel TemporaryAddress = new JLabel();
        TemporaryAddress.setText("Kathmandu, ward-3, Nepal");
        TemporaryAddress.setFont(new Font("Jost", Font.BOLD, 15));
        TemporaryAddress.setForeground(Color.decode("#AC4040"));
        TemporaryAddress.setBounds(834,265,159,20);
        panel.add(TemporaryAddress);

        JLabel permanentAddressTitle = new JLabel();
        permanentAddressTitle.setText("Permanent Address");
        permanentAddressTitle.setFont(new Font("Jost", Font.BOLD, 15));
        permanentAddressTitle.setForeground(Color.decode("#AC4040"));
        permanentAddressTitle.setBounds(705,293,115,20);
        panel.add(permanentAddressTitle);

        JLabel permanentAddress = new JLabel();
        permanentAddress.setText("Pokhara, ward-12, Nepal");
        permanentAddress.setFont(new Font("Jost", Font.BOLD, 15));
        permanentAddress.setForeground(Color.decode("#AC4040"));
        permanentAddress.setBounds(834,293,46,20);
        panel.add(permanentAddress);

        RoundedLabel details = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        details.setBounds(848,322,61,18);
        details.setBackground(Color.decode("#FEEEEE"));
        details.setText("Details");
        details.setFont(new Font("Jost", Font.PLAIN, 14));
        details.setForeground(Color.decode("#677BC1"));
        details.setHorizontalAlignment(SwingConstants.CENTER);
        details.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 12));
        panel.add(details);

        RoundedLabel pendingBackground = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        pendingBackground.setBounds(210, 235, 860, 117);
        pendingBackground.setBackground(Color.decode("#FEEEEE"));
        panel.add(pendingBackground);

        RoundedLabel resolvedBackground = new RoundedLabel("", 20, Color.decode("#F0FFFF"), 9);
        resolvedBackground.setBounds(210, 385, 860, 117);
        resolvedBackground.setBackground(Color.decode("#F0FFFF"));
        panel.add(resolvedBackground);

        RoundedLabel ongoingBackground = new RoundedLabel("", 20, Color.decode("#EEF7FE"), 9);
        ongoingBackground.setBounds(210, 538, 860, 117);
        ongoingBackground.setBackground(Color.decode("#EEF7FE"));
        panel.add(ongoingBackground);

        JLabel backgroundLogin = new JLabel();
        backgroundLogin.setOpaque(true);
        backgroundLogin.setBounds(108, 0, 1093, 841);
        backgroundLogin.setIcon(new ImageIcon("resources/artboards/list-fir-background.png"));
        panel.add(backgroundLogin);
    }

    public JPanel getFrame() {
        return panel;
    }
}


