package views;

import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.batik.gvt.text.GVTAttributedCharacterIterator.TextAttribute;

import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;

public class AllCasesPanel extends JFrame {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private JFrame frame;
    private JPanel panel;
    private Font font;
    private Map<TextAttribute, Object> attributes;

    public AllCasesPanel() {
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
        parentPageTitle.setText("Cases");
        parentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        parentPageTitle.setForeground(Color.decode("#002349"));
        parentPageTitle.setBounds(239, 61, 141, 32);
        panel.add(parentPageTitle);

        JLabel separator = new JLabel();
        separator.setIcon(new ImageIcon("resources/artboards/left-arrow-1.png"));
        separator.setBounds(295, 70, 16, 16);
        panel.add(separator);

        JLabel currentPageTitle = new JLabel();
        currentPageTitle.setText("All Cases");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(320, 61, 151, 32);
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

        JLabel complaintByTitle = new JLabel();
        complaintByTitle.setText("Complaint By");
        complaintByTitle.setFont(new Font("Jost", Font.PLAIN, 16));
        complaintByTitle.setForeground(Color.decode("#415EB6"));
        complaintByTitle.setBounds(285, 138, 102, 32);
        panel.add(complaintByTitle);

        JLabel complaintBy = new JLabel();
        complaintBy.setText(citizenController.search(fir.getFiledBy()).getFullName());
        complaintBy.setFont(new Font("Jost", Font.PLAIN, 15));
        complaintBy.setForeground(Color.decode("#000000"));
        complaintBy.setBounds(285, 167, 202, 32);
        panel.add(complaintBy);

        JLabel complaintByAddress = new JLabel();
        complaintByAddress.setText(citizenController.search(fir.getFiledBy()).getTemporaryAddress());
        complaintByAddress.setFont(new Font("Jost", Font.PLAIN, 14));
        complaintByAddress.setForeground(Color.decode("#737373"));
        complaintByAddress.setBounds(285, 190, 292, 32);
        panel.add(complaintByAddress);

        JLabel complaintByContact = new JLabel();
        complaintByContact.setText(citizenController.search(fir.getFiledBy()).getContact());
        complaintByContact.setFont(new Font("Jost", Font.PLAIN, 14));
        complaintByContact.setForeground(Color.decode("#737373"));
        complaintByContact.setBounds(285, 214, 252, 32);
        panel.add(complaintByContact);

        RoundedLabel complaintBydetails = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        complaintBydetails.setBounds(443, 149, 56, 18);
        complaintBydetails.setBackground(Color.decode("#FFFFFF"));
        complaintBydetails.setText("Details");
        complaintBydetails.setFont(new Font("Jost", Font.PLAIN, 10));
        complaintBydetails.setForeground(Color.decode("#647DC4"));
        complaintBydetails.setHorizontalAlignment(SwingConstants.CENTER);
        complaintBydetails.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
        complaintBydetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        complaintBydetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                citizenController.getDetail(citizenController.search(fir.getFiledBy()));
            }
        });
        panel.add(complaintBydetails);

        JLabel complaintAgainst = new JLabel();
        complaintAgainst.setText(citizenController.search(fir.getFiledAgainst()).getFullName());
        complaintAgainst.setFont(new Font("Jost", Font.PLAIN, 15));
        complaintAgainst.setForeground(Color.decode("#000000"));
        complaintAgainst.setBounds(285, 290, 202, 32);
        panel.add(complaintAgainst);

        JLabel complaintAgainstAddress = new JLabel();
        complaintAgainstAddress.setText(citizenController.search(fir.getFiledAgainst()).getTemporaryAddress());
        complaintAgainstAddress.setFont(new Font("Jost", Font.PLAIN, 14));
        complaintAgainstAddress.setForeground(Color.decode("#737373"));
        complaintAgainstAddress.setBounds(285, 311, 282, 32);
        panel.add(complaintAgainstAddress);

        JLabel complaintAgainstContact = new JLabel();
        complaintAgainstContact.setText(citizenController.search(fir.getFiledAgainst()).getContact());
        complaintAgainstContact.setFont(new Font("Jost", Font.PLAIN, 14));
        complaintAgainstContact.setForeground(Color.decode("#737373"));
        complaintAgainstContact.setBounds(285, 336, 252, 32);
        panel.add(complaintAgainstContact);

        RoundedLabel complaintBydetails = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        complaintBydetails.setBounds(443, 149, 56, 18);
        complaintBydetails.setBackground(Color.decode("#FFFFFF"));
        complaintBydetails.setText("Details");
        complaintBydetails.setFont(new Font("Jost", Font.PLAIN, 10));
        complaintBydetails.setForeground(Color.decode("#647DC4"));
        complaintBydetails.setHorizontalAlignment(SwingConstants.CENTER);
        complaintBydetails.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
        complaintBydetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        complaintBydetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                citizenController.getDetail(citizenController.search(fir.getFiledBy()));
            }
        });
        panel.add(complaintBydetails);

        JLabel complaintAgainstTitle = new JLabel();
        complaintAgainstTitle.setText("Complaint Against");
        complaintAgainstTitle.setFont(new Font("Jost", Font.PLAIN, 16));
        complaintAgainstTitle.setForeground(Color.decode("#415EB6"));
        complaintAgainstTitle.setBounds(285, 263, 182, 32);
        panel.add(complaintAgainstTitle);

        JLabel complaintAgainst = new JLabel();
        complaintAgainst.setText(citizenController.search(fir.getFiledAgainst()).getFullName());
        complaintAgainst.setFont(new Font("Jost", Font.PLAIN, 15));
        complaintAgainst.setForeground(Color.decode("#000000"));
        complaintAgainst.setBounds(285, 290, 202, 32);
        panel.add(complaintAgainst);

        JLabel complaintAgainstAddress = new JLabel();
        complaintAgainstAddress.setText(citizenController.search(fir.getFiledAgainst()).getTemporaryAddress());
        complaintAgainstAddress.setFont(new Font("Jost", Font.PLAIN, 14));
        complaintAgainstAddress.setForeground(Color.decode("#737373"));
        complaintAgainstAddress.setBounds(285, 311, 282, 32);
        panel.add(complaintAgainstAddress);

        JLabel complaintAgainstContact = new JLabel();
        complaintAgainstContact.setText(citizenController.search(fir.getFiledAgainst()).getContact());
        complaintAgainstContact.setFont(new Font("Jost", Font.PLAIN, 14));
        complaintAgainstContact.setForeground(Color.decode("#737373"));
        complaintAgainstContact.setBounds(285, 336, 252, 32);
        panel.add(complaintAgainstContact);

        RoundedLabel complaintAgainstDetails = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        complaintAgainstDetails.setBounds(443, 271, 56, 18);
        complaintAgainstDetails.setBackground(Color.decode("#FFFFFF"));
        complaintAgainstDetails.setText("Details");
        complaintAgainstDetails.setFont(new Font("Jost", Font.PLAIN, 10));
        complaintAgainstDetails.setForeground(Color.decode("#647DC4"));
        complaintAgainstDetails.setHorizontalAlignment(SwingConstants.CENTER);
        complaintAgainstDetails.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
        complaintAgainstDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        complaintAgainstDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                citizenController.getDetail(citizenController.search(fir.getFiledAgainst()));
            }
        });
        panel.add(complaintAgainstDetails);

        JLabel witnessTitle = new JLabel();
        witnessTitle.setText("Witness");
        witnessTitle.setFont(new Font("Jost", Font.PLAIN, 16));
        witnessTitle.setForeground(Color.decode("#415EB6"));
        witnessTitle.setBounds(781, 138, 102, 32);
        panel.add(witnessTitle);
        if (fir.getWitness() != 0) {
            JLabel witnessName = new JLabel();
            witnessName.setText(citizenController.search(fir.getWitness()).getFullName());
            witnessName.setFont(new Font("Jost", Font.PLAIN, 15));
            witnessName.setForeground(Color.decode("#000000"));
            witnessName.setBounds(781, 167, 202, 32);
            panel.add(witnessName);

            JLabel witnessAddress = new JLabel();
            witnessAddress.setText(citizenController.search(fir.getWitness()).getTemporaryAddress());
            witnessAddress.setFont(new Font("Jost", Font.PLAIN, 14));
            witnessAddress.setForeground(Color.decode("#737373"));
            witnessAddress.setBounds(781, 190, 282, 32);
            panel.add(witnessAddress);

            JLabel witnessContact = new JLabel();
            witnessContact.setText(citizenController.search(fir.getWitness()).getContact());
            witnessContact.setFont(new Font("Jost", Font.PLAIN, 14));
            witnessContact.setForeground(Color.decode("#737373"));
            witnessContact.setBounds(781, 214, 252, 32);
            panel.add(witnessContact);

            RoundedLabel witnessDetails = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
            witnessDetails.setBounds(947, 149, 56, 18);
            witnessDetails.setBackground(Color.decode("#FFFFFF"));
            witnessDetails.setText("Details");
            witnessDetails.setFont(new Font("Jost", Font.PLAIN, 10));
            witnessDetails.setForeground(Color.decode("#647DC4"));
            witnessDetails.setHorizontalAlignment(SwingConstants.CENTER);
            witnessDetails.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
            witnessDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
            witnessDetails.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    citizenController.getDetail(citizenController.search(fir.getWitness()));
                }
            });
            panel.add(witnessDetails);
        } else {
            JLabel witnessName = new JLabel();
            witnessName.setText("No witness");
            witnessName.setFont(new Font("Jost", Font.PLAIN, 15));
            witnessName.setForeground(Color.decode("#737373"));
            witnessName.setBounds(781, 167, 102, 32);
            panel.add(witnessName);
        }

        JLabel registeredTitle = new JLabel();
        registeredTitle.setText("Registered By");
        registeredTitle.setFont(new Font("Jost", Font.PLAIN, 16));
        registeredTitle.setForeground(Color.decode("#415EB6"));
        registeredTitle.setBounds(781, 263, 182, 32);
        panel.add(registeredTitle);

        JLabel registeredName = new JLabel();
        registeredName.setText(policeController.search(fir.getRegisteredBy()).getCitizen().getFullName());
        registeredName.setFont(new Font("Jost", Font.PLAIN, 15));
        registeredName.setForeground(Color.decode("#000000"));
        registeredName.setBounds(781, 290, 202, 32);
        panel.add(registeredName);

        JLabel registeredContact = new JLabel();
        registeredContact.setText(policeController.search(fir.getRegisteredBy()).getCitizen().getContact());
        registeredContact.setFont(new Font("Jost", Font.PLAIN, 14));
        registeredContact.setForeground(Color.decode("#737373"));
        registeredContact.setBounds(781, 315, 252, 32);
        panel.add(registeredContact);

        RoundedLabel registeredDetails = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        registeredDetails.setBounds(947, 271, 56, 18);
        registeredDetails.setBackground(Color.decode("#FFFFFF"));
        registeredDetails.setText("Details");
        registeredDetails.setFont(new Font("Jost", Font.PLAIN, 10));
        registeredDetails.setForeground(Color.decode("#647DC4"));
        registeredDetails.setHorizontalAlignment(SwingConstants.CENTER);
        registeredDetails.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
        registeredDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registeredDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                citizenController.getDetail(policeController.search(fir.getRegisteredBy()).getCitizen());
            }
        });
        panel.add(registeredDetails);

        JLabel incidentTitle = new JLabel();
        incidentTitle.setText("Incident Description");
        incidentTitle.setFont(new Font("Jost", Font.PLAIN, 16));
        incidentTitle.setForeground(Color.decode("#000000"));
        incidentTitle.setBounds(282, 377, 182, 32);
        panel.add(incidentTitle);

        JTextArea incidentField = new JTextArea();
        incidentField.setText(fir.getDescription());
        incidentField.setFont(new Font("Jost", Font.PLAIN, 18));
        incidentField.setBackground(Color.decode("#FFFFFF"));
        incidentField.setBorder(null);
        incidentField.setForeground(Color.decode("#6D6767"));
        incidentField.setBounds(282, 429, 740, 95);
        incidentField.setEditable(false);
        panel.add(incidentField);

        JLabel incidentDateTitle = new JLabel();
        incidentDateTitle.setText("Incident Date & Time");
        incidentDateTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        incidentDateTitle.setForeground(Color.decode("#000000"));
        incidentDateTitle.setBounds(282, 557, 182, 32);
        panel.add(incidentDateTitle);

        JLabel incidentDate = new JLabel();
        incidentDate.setText(String.valueOf(fir.getFiledDate()));
        incidentDate.setFont(new Font("Jost", Font.PLAIN, 14));
        incidentDate.setForeground(Color.decode("#737373"));
        incidentDate.setBounds(282, 582, 252, 32);
        panel.add(incidentDate);

        JLabel incidentTime = new JLabel();
        incidentTime.setText(String.valueOf(fir.getFiledTime()));
        incidentTime.setFont(new Font("Jost", Font.PLAIN, 14));
        incidentTime.setForeground(Color.decode("#737373"));
        incidentTime.setBounds(372, 582, 252, 32);
        panel.add(incidentTime);

        JLabel registeredDateTitle = new JLabel();
        registeredDateTitle.setText("Registered Date & Time");
        registeredDateTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        registeredDateTitle.setForeground(Color.decode("#000000"));
        registeredDateTitle.setBounds(791, 557, 182, 32);
        panel.add(registeredDateTitle);

        JLabel registeredDate = new JLabel();
        registeredDate.setText(String.valueOf(fir.getRegisteredDate()));
        registeredDate.setFont(new Font("Jost", Font.PLAIN, 14));
        registeredDate.setForeground(Color.decode("#737373"));
        registeredDate.setBounds(792, 582, 252, 32);
        panel.add(registeredDate);

        JLabel registeredTime = new JLabel();
        registeredTime.setText(String.valueOf(fir.getRegisteredTime()));
        registeredTime.setFont(new Font("Jost", Font.PLAIN, 14));
        registeredTime.setForeground(Color.decode("#737373"));
        registeredTime.setBounds(892, 582, 252, 32);
        panel.add(registeredTime);

        RoundedLabel evidence = new RoundedLabel("", 10, Color.decode("#FEEEEE"), 9);
        evidence.setBounds(282, 622, 140, 35);
        evidence.setBackground(Color.decode("#1A75D5"));
        evidence.setText("View Evidences");
        evidence.setFont(new Font("Jost", Font.PLAIN, 14));
        evidence.setForeground(Color.decode("#FFFFFF"));
        evidence.setHorizontalAlignment(SwingConstants.CENTER);
        evidence.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 10));
        evidence.setCursor(new Cursor(Cursor.HAND_CURSOR));
        evidence.addMouseListener(Hover.newColor(evidence, "#1A75D5", "165EAA"));
        panel.add(evidence);

        if (app.context.getRole().equals("oic")) {
            JLabel assignTitle = new JLabel();
            assignTitle.setText("Assign F.I.R");
            assignTitle.setFont(new Font("Jost", Font.PLAIN, 15));
            assignTitle.setForeground(Color.decode("#000000"));
            assignTitle.setBounds(795, 608, 182, 32);
            panel.add(assignTitle);

            JTextField assignField = new JTextField();
            assignField.setFont(new Font("Jost", Font.PLAIN, 14));
            assignField.setBackground(Color.decode("#FAFAFA"));
            assignField.setBorder(null);
            assignField.setForeground(Color.decode("#6D6767"));
            assignField.addFocusListener(Focus.setPlaceholder(assignField, "Police I.D / Name"));
            assignField.setBounds(795, 638, 150, 27);
            panel.add(assignField);

            JLabel againstLine = new JLabel();
            againstLine.setBounds(795, 666, 190, 1);
            againstLine.setBackground(Color.decode("#000000"));
            againstLine.setOpaque(true);
            panel.add(againstLine);

            RoundedLabel register = new RoundedLabel("", 10, Color.decode("#FEEEEE"), 9);
            register.setBounds(550, 690, 140, 35);
            register.setBackground(Color.decode("#1A75D5"));
            register.setText("Register");
            register.setFont(new Font("Jost", Font.PLAIN, 14));
            register.setForeground(Color.decode("#FFFFFF"));
            register.setHorizontalAlignment(SwingConstants.CENTER);
            register.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 10));
            register.setCursor(new Cursor(Cursor.HAND_CURSOR));
            register.addMouseListener(Hover.newColor(register, "#1A75D5", "#165EAA"));
            register.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    Case cs = new Case();
                    cs.setRegisteredDate(Date.valueOf(LocalDate.now()));
                    cs.setRegisteredTime(Time.valueOf(LocalTime.now()));
                    cs.setFirID(fir.getFirID());
                    cs.setAssignedTo(Long.valueOf(assignField.getText()));
                    caseController.registerCase(cs);
                }
            });
            panel.add(register);
        } else {
            RoundedLabel update = new RoundedLabel("", 10, Color.decode("#FEEEEE"), 9);
            update.setBounds(550, 680, 140, 35);
            update.setBackground(Color.decode("#1A75D5"));
            update.setText("Update F.I.R");
            update.setFont(new Font("Jost", Font.PLAIN, 14));
            update.setForeground(Color.decode("#FFFFFF"));
            update.setHorizontalAlignment(SwingConstants.CENTER);
            update.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 10));
            update.setCursor(new Cursor(Cursor.HAND_CURSOR));
            update.addMouseListener(Hover.newColor(update, "#1A75D5", "#165EAA"));
            update.addMouseListener(new MouseAdapter() {
                @Override 
                public void mouseClicked(MouseEvent e){
                    firController.getUpdate(fir);
                }
            });
            panel.add(update);
        }

        JLabel backgroundLogin = new JLabel();
        backgroundLogin.setOpaque(true);
        backgroundLogin.setBounds(25, -25, 1393, 841);
        backgroundLogin.setIcon(new ImageIcon("resources/artboards/view-fir-background.png"));
        panel.add(backgroundLogin);
    }

    public JPanel getFrame() {
        return panel;
    }
}