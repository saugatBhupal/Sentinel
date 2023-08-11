package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.batik.gvt.text.GVTAttributedCharacterIterator.TextAttribute;

import controller.CaseController;
import controller.CitizenController;
import controller.FirController;
import controller.PoliceController;
import controller.VerdictController;
import controller.controllerImpl.CaseControllerImpl;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.FirControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import controller.controllerImpl.VerdictControllerImpl;
import model.Case;
import model.Citizen;
import model.Fir;
import model.Log;
import model.Verdict;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;
import views.widget.ImageViewerWidget;

public class CitizenDetails extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private final App app;
    private Citizen citizen;
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private static FirController firController;
    private static CaseController caseController;
    private static CitizenController citizenController;
    private static VerdictController verdictController;
    private static PoliceController policeController;
    private Font font;
    private Map<TextAttribute, Object> attributes;

    public CitizenDetails(App app, Citizen citizen) {
        this.app = app;
        this.citizen = citizen;
        this.firController = new FirControllerImpl(app);
        this.citizenController = new CitizenControllerImpl(app);
        this.caseController = new CaseControllerImpl(app);
        this.verdictController = new VerdictControllerImpl(app);
        this.policeController = new PoliceControllerImpl(panel, app);
        initialize();
    }

    private static JPanel createPanel(Fir fir, Long citizenID) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(837, 37));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(null);
        panel.setLayout(null);

        String typeText;
        String statusText;
        int statusValue = fir.getStatus();
        if (statusValue == 0) {
            typeText = "F.I.R";
            statusText = "Pending";
        } else if (statusValue == 1) {
            typeText = "Case";
            statusText = "Ongoing";

        } else {
            typeText = "Verdict";
            statusText = "Resolved";
      }

        String roleText = (fir.getFiledBy().equals(citizenID) ? "Victim"
                : fir.getFiledAgainst().equals(citizenID) ? "Perpetrator"
                        : fir.getWitness().equals(citizenID) ? "witness" : "");

        JLabel offenceDate = new JLabel();
        offenceDate.setText(fir.getFiledDate().toString());
        offenceDate.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceDate.setForeground(Color.decode("#000000"));
        offenceDate.setBounds(0, 0, 109, 20);
        panel.add(offenceDate);

        JTextArea type = new JTextArea();
        type.setText(typeText);
        type.setBackground(Color.decode("#FFFFFF"));
        type.setFont(new Font("Jost", Font.PLAIN, 14));
        type.setForeground(Color.decode("#000000"));
        type.setBounds(222, 0, 46, 20);
        type.setBorder(null);
        type.setEditable(false);
        type.setLineWrap(true);
        type.setWrapStyleWord(true);
        panel.add(type);

        JTextArea role = new JTextArea();
        role.setText(roleText);
        role.setBackground(Color.decode("#FFFFFF"));
        role.setFont(new Font("Jost", Font.PLAIN, 14));
        role.setForeground(Color.decode("#000000"));
        role.setBounds(368, 0, 77, 20);
        role.setBorder(null);
        role.setEditable(false);
        role.setLineWrap(true);
        role.setWrapStyleWord(true);
        panel.add(role);

        JTextArea status = new JTextArea();
        status.setText(statusText);
        status.setBackground(Color.decode("#FFFFFF"));
        status.setFont(new Font("Jost", Font.PLAIN, 14));
        status.setForeground(Color.decode("#000000"));
        status.setBounds(525, 0, 83, 20);
        status.setBorder(null);
        status.setEditable(false);
        status.setLineWrap(true);
        status.setWrapStyleWord(true);
        panel.add(status);

        RoundedLabel details = new RoundedLabel("", 12, Color.decode("#FEEEEE"), 9);
        details.setBounds(660, 0, 80, 20);
        details.setBackground(Color.decode("#EEF7FE"));
        details.setText("Details");
        details.setFont(new Font("Jost", Font.PLAIN, 13));
        details.setForeground(Color.decode("#647DC4"));
        details.setHorizontalAlignment(SwingConstants.CENTER);
        details.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 12));
        details.addMouseListener(Hover.animateOutline(details, "#EEF7FE", "#647DC4", "#26449E", "#EEF7FE"));
        details.setCursor(new Cursor(Cursor.HAND_CURSOR));
        details.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Case cs = caseController.findByFirID(fir.getFirID());
                if (statusValue == 2) {
                    Verdict verdict = verdictController.getVerdict(cs);
                    if (verdict != null) {
                        ImageViewerWidget widget = new ImageViewerWidget((verdict.getVerdict()));
                        widget.setVisible(true);
                    }
                }
                if (statusValue == 1) {
                    List<Log> logs = caseController.getAllLogs(cs.getCaseID());
                    caseController.getDetail(cs, logs);
                }
                if (statusValue == 0) {
                    firController.getDetail(fir);
                }
            }
        });
        panel.add(details);

        return panel;
    }

    public void initialize() {

        frame = new JFrame();
        frame.setBounds(0, 0, 1201, 841);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 1));
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
        currentPageTitle.setText("Citizen details");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(400, 61, 151, 32);
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

        RoundedLabel citizenPhoto = new RoundedLabel("", 20, Color.decode("#EEF7FE"), 45);
        citizenPhoto.setIcon(imagePlugins.resize(new ImageIcon("resources/assets/profiles/"+citizen.getCitizenshipNo()+".png").getImage(), citizenPhoto));
        citizenPhoto.setBounds(246, 180, 180, 165);
        citizenPhoto.setBackground(Color.decode("#FFFFFF"));
        panel.add(citizenPhoto);

        JLabel citizenTitle = new JLabel();
        citizenTitle.setText("Citizen.I.D - ");
        citizenTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenTitle.setForeground(new Color(0, 0, 0, 105));
        citizenTitle.setBounds(250, 350, 141, 32);
        panel.add(citizenTitle);

        JLabel citizenID = new JLabel();
        citizenID.setText(String.valueOf(citizen.getCitizenshipNo()));
        citizenID.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenID.setForeground(Color.decode("#415EB6"));
        citizenID.setBounds(335, 350, 141, 32);
        panel.add(citizenID);

        JLabel firstNameTitle = new JLabel();
        firstNameTitle.setText("First Name");
        firstNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        firstNameTitle.setForeground(new Color(0, 0, 0, 105));
        firstNameTitle.setBounds(472, 180, 141, 32);
        panel.add(firstNameTitle);

        JLabel middleNameTitle = new JLabel();
        middleNameTitle.setText("Middle Name");
        middleNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        middleNameTitle.setForeground(new Color(0, 0, 0, 105));
        middleNameTitle.setBounds(472, 216, 141, 32);
        panel.add(middleNameTitle);

        JLabel lastNameTitle = new JLabel();
        lastNameTitle.setText("Last Name");
        lastNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        lastNameTitle.setForeground(new Color(0, 0, 0, 105));
        lastNameTitle.setBounds(472, 252, 141, 32);
        panel.add(lastNameTitle);

        JLabel dobTitle = new JLabel();
        dobTitle.setText("D.O.B");
        dobTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        dobTitle.setForeground(new Color(0, 0, 0, 105));
        dobTitle.setBounds(472, 288, 141, 32);
        panel.add(dobTitle);

        JLabel addressTitle = new JLabel();
        addressTitle.setText("Address");
        addressTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        addressTitle.setForeground(new Color(0, 0, 0, 105));
        addressTitle.setBounds(472, 323, 141, 32);
        panel.add(addressTitle);

        JLabel firstName = new JLabel();
        firstName.setText(citizen.getFirstName());
        firstName.setFont(new Font("Jost", Font.PLAIN, 15));
        firstName.setForeground(Color.decode("#415EB6"));
        firstName.setBounds(580, 180, 141, 32);
        panel.add(firstName);

        JLabel middleName = new JLabel();
        if (citizen.getMiddleName() != null) {
            middleName.setText(citizen.getMiddleName());
        } else {
            middleName.setText("-");
        }
        middleName.setFont(new Font("Jost", Font.PLAIN, 15));
        middleName.setForeground(Color.decode("#415EB6"));
        middleName.setBounds(580, 216, 141, 32);
        panel.add(middleName);

        JLabel lastName = new JLabel();
        lastName.setText(citizen.getLastName());
        lastName.setFont(new Font("Jost", Font.PLAIN, 15));
        lastName.setForeground(Color.decode("#415EB6"));
        lastName.setBounds(580, 252, 141, 32);
        panel.add(lastName);

        JLabel dob = new JLabel();
        dob.setText(String.valueOf(citizen.getDOB()));
        dob.setFont(new Font("Jost", Font.PLAIN, 15));
        dob.setForeground(Color.decode("#415EB6"));
        dob.setBounds(580, 288, 141, 32);
        panel.add(dob);

        JLabel address = new JLabel();
        address.setText(citizen.getTemporaryAddress());
        address.setFont(new Font("Jost", Font.PLAIN, 15));
        address.setForeground(Color.decode("#415EB6"));
        address.setBounds(580, 323, 141, 32);
        panel.add(address);

        JLabel firTitle = new JLabel();
        firTitle.setText("F.I.Rs");
        firTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        firTitle.setForeground(new Color(0, 0, 0, 105));
        firTitle.setBounds(766, 180, 141, 32);
        panel.add(firTitle);

        JLabel pendingTitle = new JLabel();
        pendingTitle.setText("Pending F.I.Rs");
        pendingTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        pendingTitle.setForeground(new Color(0, 0, 0, 105));
        pendingTitle.setBounds(766, 216, 161, 32);
        panel.add(pendingTitle);

        JLabel ongoingTitle = new JLabel();
        ongoingTitle.setText("Ongoing Cases");
        ongoingTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        ongoingTitle.setForeground(new Color(0, 0, 0, 105));
        ongoingTitle.setBounds(766, 252, 141, 32);
        panel.add(ongoingTitle);

        JLabel convictionsTitle = new JLabel();
        convictionsTitle.setText("Convictions");
        convictionsTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        convictionsTitle.setForeground(Color.decode("#8B8A8A"));
        convictionsTitle.setBounds(766, 288, 141, 52);
        panel.add(convictionsTitle);

        JLabel firs = new JLabel();
        firs.setText(String.valueOf(firController.filterByStatus("*", citizen.getCitizenshipNo()).size()));
        firs.setFont(new Font("Jost", Font.PLAIN, 15));
        firs.setForeground(Color.decode("#415EB6"));
        firs.setBounds(868, 180, 141, 32);
        panel.add(firs);

        JLabel pending = new JLabel();
        pending.setText(String.valueOf(firController.filterByStatus(0, citizen.getCitizenshipNo()).size()));
        pending.setFont(new Font("Jost", Font.PLAIN, 15));
        pending.setForeground(Color.decode("#415EB6"));
        pending.setBounds(868, 216, 141, 32);
        panel.add(pending);

        JLabel ongoing = new JLabel();
        ongoing.setText(String.valueOf(firController.filterByStatus(1, citizen.getCitizenshipNo()).size()));
        ongoing.setFont(new Font("Jost", Font.PLAIN, 15));
        ongoing.setForeground(Color.decode("#415EB6"));
        ongoing.setBounds(868, 252, 141, 32);
        panel.add(ongoing);

        JLabel convictions = new JLabel();
        convictions.setText(String.valueOf(firController.filterByStatus(2, citizen.getCitizenshipNo()).size()));
        convictions.setFont(new Font("Jost", Font.PLAIN, 15));
        convictions.setForeground(Color.decode("#415EB6"));
        convictions.setBounds(868, 298, 141, 32);
        panel.add(convictions);

        JLabel recordDetails = new JLabel();
        recordDetails.setText("Record Details");
        recordDetails.setFont(new Font("Jost", Font.PLAIN, 14));
        recordDetails.setForeground(Color.decode("#000000"));
        recordDetails.setBounds(247, 429, 141, 32);
        panel.add(recordDetails);

        JLabel tableTop = new JLabel("");
        tableTop.setBackground(new Color(0, 0, 0, 46));
        tableTop.setBounds(247, 470, 837, 1);
        tableTop.setOpaque(true);
        panel.add(tableTop);

        JLabel offenceDateTitle = new JLabel();
        offenceDateTitle.setText("Offense Details");
        offenceDateTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceDateTitle.setForeground(new Color(0, 0, 0, 115));
        offenceDateTitle.setBounds(250, 473, 141, 32);
        panel.add(offenceDateTitle);

        JLabel typeTitle = new JLabel();
        typeTitle.setText("Type");
        typeTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        typeTitle.setForeground(new Color(0, 0, 0, 115));
        typeTitle.setBounds(482, 473, 141, 32);
        panel.add(typeTitle);

        JLabel roleTitle = new JLabel();
        roleTitle.setText("Role");
        roleTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        roleTitle.setForeground(new Color(0, 0, 0, 115));
        roleTitle.setBounds(635, 473, 141, 32);
        panel.add(roleTitle);

        JLabel statusTitle = new JLabel();
        statusTitle.setText("Status");
        statusTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        statusTitle.setForeground(new Color(0, 0, 0, 115));
        statusTitle.setBounds(793, 473, 141, 32);
        panel.add(statusTitle);

        JLabel detailsTitle = new JLabel();
        detailsTitle.setText("Details");
        detailsTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        detailsTitle.setForeground(new Color(0, 0, 0, 115));
        detailsTitle.setBounds(936, 473, 141, 32);
        panel.add(detailsTitle);

        JLabel tableMiddle = new JLabel("");
        tableMiddle.setBackground(new Color(0, 0, 0, 46));
        tableMiddle.setBounds(247, 504, 837, 1);
        tableMiddle.setOpaque(true);
        panel.add(tableMiddle);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(251, 514, 872, 173);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.decode("#FFFF"));
        panel.add(scrollPane);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        containerPanel.setBackground(Color.decode("#FFFFFF"));

        for (Fir fir : firController.filterByStatus("*", citizen.getCitizenshipNo())) {
            JPanel panel = createPanel(fir, citizen.getCitizenshipNo());
            containerPanel.add(panel);
            containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        scrollPane.setViewportView(containerPanel);

        JLabel tableBottom = new JLabel("");
        tableBottom.setBackground(new Color(0, 0, 0, 46));
        tableBottom.setBounds(247, 720, 837, 1);
        tableBottom.setOpaque(true);
        panel.add(tableBottom);

        JLabel endLeft = new JLabel("");
        endLeft.setBackground(new Color(0, 0, 0, 46));
        endLeft.setBounds(472, 750, 129, 1);
        endLeft.setOpaque(true);
        panel.add(endLeft);

        JLabel endRight = new JLabel("");
        endRight.setBackground(new Color(0, 0, 0, 46));
        endRight.setBounds(738, 750, 129, 1);
        endRight.setOpaque(true);
        panel.add(endRight);

        JLabel endTitle = new JLabel();
        endTitle.setText("END OF REPORT");
        endTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        endTitle.setForeground(Color.decode("#000000"));
        endTitle.setBounds(620, 736, 141, 32);
        panel.add(endTitle);

        JLabel background = new JLabel();
        background.setOpaque(true);
        background.setBackground(Color.decode("#FFFFFF"));
        background.setBounds(0, 0, 1201, 841);
        panel.add(background);

    }

    public JPanel getFrame() {
        return panel;
    }
}
