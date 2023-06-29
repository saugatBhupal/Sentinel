package views;

import java.awt.Color;
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
    private final CitizenController citizenController;
    private final FirController firController;
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private JFrame frame;
    private JPanel panel;
    private Font font;
    private Map<TextAttribute, Object> attributes;

    public ListFirPanel() {
        this.citizenController = new CitizenControllerImpl();
        this.firController = new FirControllerImpl();
        initialize();
    }

    private static JPanel createPanel(Fir fir){

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(759, 117));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(null);
        panel.setLayout(null);

        JLabel idTitle = new JLabel();
        idTitle.setText("Record ID");
        idTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        idTitle.setForeground(Color.decode("#AC4040"));
        idTitle.setBounds(59, 23, 60, 20);
        panel.add(idTitle);

        JLabel recordNameTitle = new JLabel();
        recordNameTitle.setText("Record Name");
        recordNameTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        recordNameTitle.setForeground(Color.decode("#AC4040"));
        recordNameTitle.setBounds(59, 23, 141, 32);
        panel.add(recordNameTitle);

        JLabel recordDateTitle = new JLabel();
        recordDateTitle.setText("Record Date");
        recordDateTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        recordDateTitle.setForeground(Color.decode("#AC4040"));
        recordDateTitle.setBounds(239, 305, 141, 32);
        panel.add(recordDateTitle);

        JLabel id = new JLabel();
        id.setText("9876678");
        id.setFont(new Font("Jost", Font.PLAIN, 15));
        id.setForeground(Color.decode("#CC8686"));
        id.setBounds(360, 252, 141, 32);
        panel.add(id);

        JLabel recordName = new JLabel();
        recordName.setText("Auto Theft");
        recordName.setFont(new Font("Jost", Font.PLAIN, 15));
        recordName.setForeground(Color.decode("#CC8686"));
        recordName.setBounds(360, 278, 141, 32);
        panel.add(recordName);

        JLabel recordDate = new JLabel();
        recordDate.setText("12-09-2022");
        recordDate.setFont(new Font("Jost", Font.PLAIN, 15));
        recordDate.setForeground(Color.decode("#CC8686"));
        recordDate.setBounds(360, 305, 141, 32);
        panel.add(recordDate);

        JLabel filedByTitle = new JLabel();
        filedByTitle.setText("Filed By");
        filedByTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        filedByTitle.setForeground(Color.decode("#AC4040"));
        filedByTitle.setBounds(569, 252, 141, 32);
        panel.add(filedByTitle);

        JLabel broughtByTitle = new JLabel();
        broughtByTitle.setText("Brought By");
        broughtByTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        broughtByTitle.setForeground(Color.decode("#AC4040"));
        broughtByTitle.setBounds(569, 278, 141, 32);
        panel.add(broughtByTitle);

        JLabel broughtAgainstTitle = new JLabel();
        broughtAgainstTitle.setText("Brought Against");
        broughtAgainstTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        broughtAgainstTitle.setForeground(Color.decode("#AC4040"));
        broughtAgainstTitle.setBounds(569, 305, 141, 32);
        panel.add(broughtAgainstTitle);

        JLabel filedBy = new JLabel();
        filedBy.setText("Insp. Ram Dangol");
        filedBy.setFont(new Font("Jost", Font.PLAIN, 15));
        filedBy.setForeground(Color.decode("#CC8686"));
        filedBy.setBounds(690, 252, 141, 32);
        panel.add(filedBy);

        JLabel broughtBy = new JLabel();
        broughtBy.setText("Hari Prasad");
        broughtBy.setFont(new Font("Jost", Font.PLAIN, 15));
        broughtBy.setForeground(Color.decode("#CC8686"));
        broughtBy.setBounds(690, 278, 141, 32);
        panel.add(broughtBy);

        JLabel broughtAgainst = new JLabel();
        broughtAgainst.setText("Bhuwan Rawat");
        broughtAgainst.setFont(new Font("Jost", Font.PLAIN, 15));
        broughtAgainst.setForeground(Color.decode("#CC8686"));
        broughtAgainst.setBounds(690, 305, 141, 32);
        panel.add(broughtAgainst);

        JLabel pending = new JLabel();
        pending.setText("Pending");
        pending.setFont(new Font("Jost", Font.BOLD, 15));
        pending.setForeground(Color.decode("#AC4040"));
        pending.setBounds(940, 258, 141, 32);
        panel.add(pending);

        RoundedLabel details = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        details.setBounds(930, 300, 80, 28);
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

        return(panel);


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
    }
}
