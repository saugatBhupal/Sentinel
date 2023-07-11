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

    

