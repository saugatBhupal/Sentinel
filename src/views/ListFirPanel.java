package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import controller.FirController;
import controller.PoliceController;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.FirControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import model.Citizen;
import model.Fir;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;

public class ListFirPanel extends JFrame {
    private static CitizenController citizenController;
    private static PoliceController policeController;
    private static FirController firController;
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private JFrame frame;
    private JPanel panel;
    private Font font;
    private List<Fir> firs;
    private Map<TextAttribute, Object> attributes;

    public ListFirPanel(List<Fir> firs, App app) {
        this.citizenController = new CitizenControllerImpl(app);
        this.policeController = new PoliceControllerImpl(panel, app);
        this.firController = new FirControllerImpl(app);
        this.firs = firs;
        initialize();
    }

    private static JPanel createPanel(Fir fir) {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(759, 117));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(null);
        panel.setLayout(null);

        int statusValue = fir.getStatus();
        Color titleColor = (statusValue == 0) ? Color.decode("#AC4040")
                : (statusValue == 1) ? Color.decode("#415EB6") : Color.decode("#23B0B0");

        Color detailColor = (statusValue == 0) ? Color.decode("#CC8686")
                : (statusValue == 1) ? Color.decode("#93AEF8") : Color.decode("#75D0D0");

        Color backgroundColor = (statusValue == 0) ? Color.decode("#FEEEEE")
                : (statusValue == 1) ? Color.decode("#EEF7FE") : Color.decode("#F0FFFF");

        JLabel idTitle = new JLabel();
        idTitle.setText("Record ID");
        idTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        idTitle.setForeground(titleColor);
        idTitle.setBounds(59, 23, 120, 20);
        panel.add(idTitle);

        JLabel recordNameTitle = new JLabel();
        recordNameTitle.setText("Record Name");
        recordNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        recordNameTitle.setForeground(titleColor);
        recordNameTitle.setBounds(59, 48, 160, 22);
        panel.add(recordNameTitle);

        JLabel recordDateTitle = new JLabel();
        recordDateTitle.setText("Record Date");
        recordDateTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        recordDateTitle.setForeground(titleColor);
        recordDateTitle.setBounds(59, 73, 160, 22);
        panel.add(recordDateTitle);

        JLabel id = new JLabel();
        id.setText(String.valueOf(fir.getFirID()));
        id.setFont(new Font("Jost", Font.PLAIN, 15));
        id.setForeground(detailColor);
        id.setBounds(161, 23, 100, 20);
        panel.add(id);

        JLabel recordName = new JLabel();
        recordName.setText(fir.getCategory());
        recordName.setFont(new Font("Jost", Font.PLAIN, 15));
        recordName.setForeground(detailColor);
        recordName.setBounds(161, 44, 181, 32);
        panel.add(recordName);

        JLabel recordDate = new JLabel();
        recordDate.setText(String.valueOf(fir.getFiledDate()));
        recordDate.setFont(new Font("Jost", Font.PLAIN, 15));
        recordDate.setForeground(detailColor);
        recordDate.setBounds(161, 70, 141, 32);
        panel.add(recordDate);

        JLabel fregisteredByTitle = new JLabel();
        fregisteredByTitle.setText("Registered By");
        fregisteredByTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        fregisteredByTitle.setForeground(titleColor);
        fregisteredByTitle.setBounds(339, 20, 141, 32);
        panel.add(fregisteredByTitle);

        JLabel filedByTitle = new JLabel();
        filedByTitle.setText("Filed By");
        filedByTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        filedByTitle.setForeground(titleColor);
        filedByTitle.setBounds(339, 44, 141, 32);
        panel.add(filedByTitle);

        JLabel filedAgainstTitle = new JLabel();
        filedAgainstTitle.setText("Filed Against");
        filedAgainstTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        filedAgainstTitle.setForeground(titleColor);
        filedAgainstTitle.setBounds(339, 70, 141, 32);
        panel.add(filedAgainstTitle);

        JLabel registeredBy = new JLabel();
        registeredBy.setText(policeController.search(fir.getRegisteredBy()).getCitizen().getFullName());
        registeredBy.setFont(new Font("Jost", Font.PLAIN, 15));
        registeredBy.setForeground(detailColor);
        registeredBy.setBounds(457, 20, 241, 32);
        panel.add(registeredBy);

        JLabel filedBy = new JLabel();
        filedBy.setText(citizenController.search(fir.getFiledBy()).getFullName());
        filedBy.setFont(new Font("Jost", Font.PLAIN, 15));
        filedBy.setForeground(detailColor);
        filedBy.setBounds(457, 44, 241, 32);
        panel.add(filedBy);

        JLabel filedAgainst = new JLabel();
        filedAgainst.setText(citizenController.search(fir.getFiledAgainst()).getFullName());
        filedAgainst.setFont(new Font("Jost", Font.PLAIN, 15));
        filedAgainst.setForeground(detailColor);
        filedAgainst.setBounds(457, 70, 241, 32);
        panel.add(filedAgainst);

        JLabel status = new JLabel();
        String statusText = (statusValue == 0) ? "Pending" : (statusValue == 1) ? "Ongoing" : "Resolved";
        status.setText(statusText);
        status.setText(statusText);
        status.setFont(new Font("Jost", Font.BOLD, 15));
        status.setForeground(titleColor);
        status.setBounds(673, 29, 141, 32);
        panel.add(status);

        RoundedLabel details = new RoundedLabel("", 20, backgroundColor, 9);
        details.setBounds(670, 69, 65, 18);
        details.setBackground(backgroundColor);
        details.setText("Details");
        details.setFont(new Font("Jost", Font.PLAIN, 14));
        details.setForeground(Color.decode("#677BC1"));
        details.setHorizontalAlignment(SwingConstants.CENTER);
        details.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 12));
        details.setCursor(new Cursor(Cursor.HAND_CURSOR));
        details.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                firController.getDetail(fir);
            }
        });
        panel.add(details);

        RoundedLabel pendingBackground = new RoundedLabel("", 20, backgroundColor, 9);
        pendingBackground.setBounds(0, 0, 820, 117);
        pendingBackground.setBackground(backgroundColor);
        panel.add(pendingBackground);

        return (panel);
        
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

        JLabel separator = new JLabel();
        separator.setIcon(new ImageIcon("resources/artboards/left-arrow-1.png"));
        separator.setBounds(285, 70, 16, 16);
        panel.add(separator);

        JLabel currentPageTitle = new JLabel();
        currentPageTitle.setText("All F.I.Rs");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(310, 61, 151, 32);
        panel.add(currentPageTitle);

        DateTimeWidget.addWidget(panel);

        JLabel dashboardIcon = new JLabel();
        dashboardIcon.setBounds(36, 125, 35, 35);
        dashboardIcon.setIcon(new ImageIcon("resources/artboards/dash-icon-default.png"));
        dashboardIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dashboardIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        policeController.getDashboard();
                }
        });
        panel.add(dashboardIcon);

        JLabel recordsIcon = new JLabel();
        recordsIcon.setBounds(39, 285, 35, 35);
        recordsIcon.setIcon(imagePlugins
                        .resize(new ImageIcon("resources/artboards/records-icon-default.png").getImage(),
                                        recordsIcon));
        recordsIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recordsIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        firController.getAllFir();
                }
        });
        panel.add(recordsIcon);

        JLabel officersIcon = new JLabel();
        officersIcon.setBounds(44, 349, 35, 25);
        officersIcon.setIcon(imagePlugins
                        .resize(new ImageIcon("resources/artboards/police-icon-selected.png").getImage(),
                                        officersIcon));
        officersIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        officersIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        policeController.getAllPolice();
                }
        });
        panel.add(officersIcon);

        JLabel citizensIcon = new JLabel();
        citizensIcon.setBounds(39, 413, 35, 35);
        citizensIcon.setIcon(new ImageIcon("resources/artboards/citizen-icon-default.png"));
        citizensIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        citizensIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        citizenController.getAllCitizens();
                }
        });
        panel.add(citizensIcon);

        JLabel convictsIcon = new JLabel();
        convictsIcon.setBounds(39, 477, 35, 35);
        convictsIcon.setIcon(new ImageIcon("resources/artboards/convict-icon-default.png"));
        convictsIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        convictsIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                        citizenController.getAllCriminals();
                }
        });
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
        search.setBounds(494, 164, 365, 30);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    firController.fullTextSearch(search.getText());
                }
            }
        });
        
        panel.add(search);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(232, 242, 888, 599);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.decode("#000000"));
        panel.add(scrollPane);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        containerPanel.setBackground(Color.decode("#FFFFFF"));

        for (Fir fir : firs) {
            JPanel panel = createPanel(fir);
            containerPanel.add(panel);
            containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        scrollPane.setViewportView(containerPanel);

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
