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