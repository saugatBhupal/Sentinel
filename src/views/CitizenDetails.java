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

public class CitizenDetails extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private Font font;
    private Map<TextAttribute, Object> attributes;

    public CitizenDetails() {

        initialize();
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
        currentPageTitle.setText("Criminal history");
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

        RoundedLabel citizenPhoto = new RoundedLabel("", 20, Color.decode("#EEF7FE"), 45);
        citizenPhoto.setBounds(246, 180, 155, 165);
        citizenPhoto.setBackground(Color.decode("#FFFFFF"));
        panel.add(citizenPhoto);

        JLabel citizenTitle = new JLabel();
        citizenTitle.setText("Citizen.I.D - ");
        citizenTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        citizenTitle.setForeground(new Color(0, 0, 0, 105));
        citizenTitle.setBounds(250, 350, 141, 32);
        panel.add(citizenTitle);

        JLabel citizen = new JLabel();
        citizen.setText("20229821");
        citizen.setFont(new Font("Jost", Font.PLAIN, 15));
        citizen.setForeground(Color.decode("#415EB6"));
        citizen.setBounds(335, 350, 141, 32);
        panel.add(citizen);

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
        firstName.setText("John");
        firstName.setFont(new Font("Jost", Font.PLAIN, 15));
        firstName.setForeground(Color.decode("#415EB6"));
        firstName.setBounds(580, 180, 141, 32);
        panel.add(firstName);

        JLabel middleName = new JLabel();
        middleName.setText("me");
        middleName.setFont(new Font("Jost", Font.PLAIN, 15));
        middleName.setForeground(Color.decode("#415EB6"));
        middleName.setBounds(580, 216, 141, 32);
        panel.add(middleName);

        JLabel lastName = new JLabel();
        lastName.setText("Cena");
        lastName.setFont(new Font("Jost", Font.PLAIN, 15));
        lastName.setForeground(Color.decode("#415EB6"));
        lastName.setBounds(580, 252, 141, 32);
        panel.add(lastName);

        JLabel dob = new JLabel();
        dob.setText("1981-04-01");
        dob.setFont(new Font("Jost", Font.PLAIN, 15));
        dob.setForeground(Color.decode("#415EB6"));
        dob.setBounds(580, 288, 141, 32);
        panel.add(dob);

        JLabel address = new JLabel();
        address.setText("Address");
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

        JLabel chargesTitle = new JLabel();
        chargesTitle.setText("Charges");
        chargesTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        chargesTitle.setForeground(new Color(0, 0, 0, 105));
        chargesTitle.setBounds(766, 216, 141, 32);
        panel.add(chargesTitle);

        JLabel convictionsTitle = new JLabel();
        convictionsTitle.setText("Convictions");
        convictionsTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        convictionsTitle.setForeground(new Color(0, 0, 0, 105));
        convictionsTitle.setBounds(766, 252, 141, 32);
        panel.add(convictionsTitle);

        JLabel outConvictionsTitle = new JLabel();
        outConvictionsTitle.setText("<html>Outstanding<br>Convictions</html>");
        outConvictionsTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        outConvictionsTitle.setForeground(Color.decode("#8B8A8A"));
        outConvictionsTitle.setBounds(766, 288, 141, 52);
        panel.add(outConvictionsTitle);

        JLabel firs = new JLabel();
        firs.setText("12");
        firs.setFont(new Font("Jost", Font.PLAIN, 15));
        firs.setForeground(Color.decode("#415EB6"));
        firs.setBounds(862, 180, 141, 32);
        panel.add(firs);

        JLabel charges = new JLabel();
        charges.setText("10");
        charges.setFont(new Font("Jost", Font.PLAIN, 15));
        charges.setForeground(Color.decode("#415EB6"));
        charges.setBounds(862, 216, 141, 32);
        panel.add(charges);

        JLabel convictions = new JLabel();
        convictions.setText("1");
        convictions.setFont(new Font("Jost", Font.PLAIN, 15));
        convictions.setForeground(Color.decode("#415EB6"));
        convictions.setBounds(862, 252, 141, 32);
        panel.add(convictions);

        JLabel outConvictions = new JLabel();
        outConvictions.setText("1");
        outConvictions.setFont(new Font("Jost", Font.PLAIN, 15));
        outConvictions.setForeground(Color.decode("#415EB6"));
        outConvictions.setBounds(862, 298, 141, 32);
        panel.add(outConvictions);

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

        JLabel offenceTitle = new JLabel();
        offenceTitle.setText("Offense");
        offenceTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceTitle.setForeground(new Color(0, 0, 0, 115));
        offenceTitle.setBounds(482, 473, 141, 32);
        panel.add(offenceTitle);

        JLabel dispositionTitle = new JLabel();
        dispositionTitle.setText("Disposition");
        dispositionTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        dispositionTitle.setForeground(new Color(0, 0, 0, 115));
        dispositionTitle.setBounds(685, 473, 141, 32);
        panel.add(dispositionTitle);

        JLabel offenceLocationTitle = new JLabel();
        offenceLocationTitle.setText("Offense Location");
        offenceLocationTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceLocationTitle.setForeground(new Color(0, 0, 0, 115));
        offenceLocationTitle.setBounds(823, 473, 141, 32);
        panel.add(offenceLocationTitle);

        JLabel sentenceTitle = new JLabel();
        sentenceTitle.setText("Sentence");
        sentenceTitle.setFont(new Font("Jost", Font.PLAIN, 14));
        sentenceTitle.setForeground(new Color(0, 0, 0, 115));
        sentenceTitle.setBounds(976, 473, 141, 32);
        panel.add(sentenceTitle);

        JLabel tableMiddle = new JLabel("");
        tableMiddle.setBackground(new Color(0, 0, 0, 46));
        tableMiddle.setBounds(247, 504, 837, 1);
        tableMiddle.setOpaque(true);
        panel.add(tableMiddle);

        JLabel offenceDate = new JLabel();
        offenceDate.setText("11/02/23");
        offenceDate.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceDate.setForeground(Color.decode("#000000"));
        offenceDate.setBounds(250, 518, 141, 32);
        panel.add(offenceDate);

        JTextArea offence = new JTextArea();
        offence.setText("Manufacture and distribution of heroin near a school");
        offence.setBackground(Color.decode("#FFFFFF"));
        offence.setFont(new Font("Jost", Font.PLAIN, 14));
        offence.setForeground(Color.decode("#000000"));
        offence.setBounds(400, 518, 241, 45);
        offence.setBorder(null);
        offence.setEditable(false);
        offence.setLineWrap(true);
        offence.setWrapStyleWord(true);
        panel.add(offence);

        JTextArea disposition = new JTextArea();
        disposition.setText("Convicted as Minor");
        disposition.setBackground(Color.decode("#FFFFFF"));
        disposition.setFont(new Font("Jost", Font.PLAIN, 14));
        disposition.setForeground(Color.decode("#000000"));
        disposition.setBounds(670, 518, 125, 45);
        disposition.setBorder(null);
        disposition.setEditable(false);
        disposition.setLineWrap(true);
        disposition.setWrapStyleWord(true);
        panel.add(disposition);

        JTextArea offenceLocation = new JTextArea();
        offenceLocation.setText("Dilibazar,KTM");
        offenceLocation.setBackground(Color.decode("#FFFFFF"));
        offenceLocation.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceLocation.setForeground(Color.decode("#000000"));
        offenceLocation.setBounds(825, 518, 108, 45);
        offenceLocation.setBorder(null);
        offenceLocation.setEditable(false);
        offenceLocation.setLineWrap(true);
        offenceLocation.setWrapStyleWord(true);
        panel.add(offenceLocation);

        JTextArea sentence = new JTextArea();
        sentence.setText("Home Confinement, Probation");
        sentence.setBackground(Color.decode("#FFFFFF"));
        sentence.setFont(new Font("Jost", Font.PLAIN, 14));
        sentence.setForeground(Color.decode("#000000"));
        sentence.setBounds(965, 518, 178, 45);
        sentence.setBorder(null);
        sentence.setEditable(false);
        sentence.setLineWrap(true);
        sentence.setWrapStyleWord(true);
        panel.add(sentence);

        JLabel offenceDate2 = new JLabel();
        offenceDate2.setText("11/02/23");
        offenceDate2.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceDate2.setForeground(Color.decode("#000000"));
        offenceDate2.setBounds(250, 568, 141, 32);
        panel.add(offenceDate2);

        JTextArea offence2 = new JTextArea();
        offence2.setText("Manufacture and distribution of heroin near a school");
        offence2.setBackground(Color.decode("#FFFFFF"));
        offence2.setFont(new Font("Jost", Font.PLAIN, 14));
        offence2.setForeground(Color.decode("#000000"));
        offence2.setBounds(400, 568, 241, 45);
        offence2.setBorder(null);
        offence2.setEditable(false);
        offence2.setLineWrap(true);
        offence2.setWrapStyleWord(true);
        panel.add(offence2);

        JTextArea disposition2 = new JTextArea();
        disposition2.setText("Convicted as Minor");
        disposition2.setBackground(Color.decode("#FFFFFF"));
        disposition2.setFont(new Font("Jost", Font.PLAIN, 14));
        disposition2.setForeground(Color.decode("#000000"));
        disposition2.setBounds(670, 568, 125, 45);
        disposition2.setBorder(null);
        disposition2.setEditable(false);
        disposition2.setLineWrap(true);
        disposition2.setWrapStyleWord(true);
        panel.add(disposition2);

        JTextArea offenceLocation2 = new JTextArea();
        offenceLocation2.setText("Dilibazar,KTM");
        offenceLocation2.setBackground(Color.decode("#FFFFFF"));
        offenceLocation2.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceLocation2.setForeground(Color.decode("#000000"));
        offenceLocation2.setBounds(825, 568, 108, 45);
        offenceLocation2.setBorder(null);
        offenceLocation2.setEditable(false);
        offenceLocation2.setLineWrap(true);
        offenceLocation2.setWrapStyleWord(true);
        panel.add(offenceLocation2);

        JTextArea sentence2 = new JTextArea();
        sentence2.setText("Home Confinement, Probation");
        sentence2.setBackground(Color.decode("#FFFFFF"));
        sentence2.setFont(new Font("Jost", Font.PLAIN, 14));
        sentence2.setForeground(Color.decode("#000000"));
        sentence2.setBounds(965, 568, 178, 45);
        sentence2.setBorder(null);
        sentence2.setEditable(false);
        sentence2.setLineWrap(true);
        sentence2.setWrapStyleWord(true);
        panel.add(sentence2);

        JLabel offenceDate3 = new JLabel();
        offenceDate3.setText("11/02/23");
        offenceDate3.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceDate3.setForeground(Color.decode("#000000"));
        offenceDate3.setBounds(250, 618, 141, 32);
        panel.add(offenceDate3);

        JTextArea offence3 = new JTextArea();
        offence3.setText("Manufacture and distribution of heroin near a school");
        offence3.setBackground(Color.decode("#FFFFFF"));
        offence3.setFont(new Font("Jost", Font.PLAIN, 14));
        offence3.setForeground(Color.decode("#000000"));
        offence3.setBounds(400, 618, 241, 45);
        offence3.setBorder(null);
        offence3.setEditable(false);
        offence3.setLineWrap(true);
        offence3.setWrapStyleWord(true);
        panel.add(offence3);

        JTextArea disposition3 = new JTextArea();
        disposition3.setText("Convicted as Minor");
        disposition3.setBackground(Color.decode("#FFFFFF"));
        disposition3.setFont(new Font("Jost", Font.PLAIN, 14));
        disposition3.setForeground(Color.decode("#000000"));
        disposition3.setBounds(670, 618, 125, 45);
        disposition3.setBorder(null);
        disposition3.setEditable(false);
        disposition3.setLineWrap(true);
        disposition3.setWrapStyleWord(true);
        panel.add(disposition3);

        JTextArea offenceLocation3 = new JTextArea();
        offenceLocation3.setText("Dilibazar,KTM");
        offenceLocation3.setBackground(Color.decode("#FFFFFF"));
        offenceLocation3.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceLocation3.setForeground(Color.decode("#000000"));
        offenceLocation3.setBounds(825, 618, 108, 45);
        offenceLocation3.setBorder(null);
        offenceLocation3.setEditable(false);
        offenceLocation3.setLineWrap(true);
        offenceLocation3.setWrapStyleWord(true);
        panel.add(offenceLocation3);

        JTextArea sentence3 = new JTextArea();
        sentence3.setText("Home Confinement, Probation");
        sentence3.setBackground(Color.decode("#FFFFFF"));
        sentence3.setFont(new Font("Jost", Font.PLAIN, 14));
        sentence3.setForeground(Color.decode("#000000"));
        sentence3.setBounds(965, 618, 178, 45);
        sentence3.setBorder(null);
        sentence3.setEditable(false);
        sentence3.setLineWrap(true);
        sentence3.setWrapStyleWord(true);
        panel.add(sentence3);

        JLabel offenceDate4 = new JLabel();
        offenceDate4.setText("11/02/23");
        offenceDate4.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceDate4.setForeground(Color.decode("#000000"));
        offenceDate4.setBounds(250, 668, 141, 32);
        panel.add(offenceDate4);

        JTextArea offence4 = new JTextArea();
        offence4.setText("Manufacture and distribution of heroin near a school");
        offence4.setBackground(Color.decode("#FFFFFF"));
        offence4.setFont(new Font("Jost", Font.PLAIN, 14));
        offence4.setForeground(Color.decode("#000000"));
        offence4.setBounds(400, 668, 241, 45);
        offence4.setBorder(null);
        offence4.setEditable(false);
        offence4.setLineWrap(true);
        offence4.setWrapStyleWord(true);
        panel.add(offence4);

        JTextArea disposition4 = new JTextArea();
        disposition4.setText("Convicted as Minor");
        disposition4.setBackground(Color.decode("#FFFFFF"));
        disposition4.setFont(new Font("Jost", Font.PLAIN, 14));
        disposition4.setForeground(Color.decode("#000000"));
        disposition4.setBounds(670, 668, 125, 45);
        disposition4.setBorder(null);
        disposition4.setEditable(false);
        disposition4.setLineWrap(true);
        disposition4.setWrapStyleWord(true);
        panel.add(disposition4);

        JTextArea offenceLocation4 = new JTextArea();
        offenceLocation4.setText("Dilibazar,KTM");
        offenceLocation4.setBackground(Color.decode("#FFFFFF"));
        offenceLocation4.setFont(new Font("Jost", Font.PLAIN, 14));
        offenceLocation4.setForeground(Color.decode("#000000"));
        offenceLocation4.setBounds(825, 668, 108, 45);
        offenceLocation4.setBorder(null);
        offenceLocation4.setEditable(false);
        offenceLocation4.setLineWrap(true);
        offenceLocation4.setWrapStyleWord(true);
        panel.add(offenceLocation4);

        JTextArea sentence4 = new JTextArea();
        sentence4.setText("Home Confinement, Probation");
        sentence4.setBackground(Color.decode("#FFFFFF"));
        sentence4.setFont(new Font("Jost", Font.PLAIN, 14));
        sentence4.setForeground(Color.decode("#000000"));
        sentence4.setBounds(965, 668, 178, 45);
        sentence4.setBorder(null);
        sentence4.setEditable(false);
        sentence4.setLineWrap(true);
        sentence4.setWrapStyleWord(true);
        panel.add(sentence4);

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
        background.setBounds(0,0,1201,841);
        panel.add(background);
    }

    public JPanel getFrame() {
        return panel;
    }
}

