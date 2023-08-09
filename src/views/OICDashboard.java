package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.batik.gvt.text.GVTAttributedCharacterIterator.TextAttribute;

import controller.CaseController;
import controller.CitizenController;
import controller.FirController;
import controller.PoliceController;
import controller.controllerImpl.CaseControllerImpl;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.FirControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import model.Citizen;
import model.Police;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.dateTime.DateTimeUtil;
import utils.generator.PasswordGenerator;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import views.widget.DateTimeWidget;

public class OICDashboard extends JFrame {
        private final CitizenController citizenController;
        private final PoliceController policeController;
        private final FirController firController;
        private final CaseController caseController;
        private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
        private JFrame frame;
        private JPanel panel;
        private Font font;
        private Map<TextAttribute, Object> attributes;

        public OICDashboard(App app) {
                this.citizenController = new CitizenControllerImpl(app);
                this.policeController = new PoliceControllerImpl(panel, app);
                this.firController = new FirControllerImpl(app);
                this.caseController = new CaseControllerImpl(app);
                initialize();
        }

        public void initialize() {

                frame = new JFrame();
                frame.setBounds(0, 0, 1201, 841);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().setLayout(null);

                panel = new JPanel();
                panel.setBackground(new Color(254, 254, 254, 1));
                panel.setBounds(0, 0, 1201, 841);
                panel.setLayout(null);
                frame.getContentPane().add(panel);

                JLabel logo = new JLabel();
                logo.setBounds(25, 35, 58, 57);
                logo.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/Sentinel-logo-2.png").getImage(),
                                logo));
                panel.add(logo);

                JLabel parentPageTitle = new JLabel();
                parentPageTitle.setText("F.I.R");
                parentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
                parentPageTitle.setForeground(Color.decode("#002349"));
                parentPageTitle.setBounds(239, 61, 141, 32);
                panel.add(parentPageTitle);

                DateTimeWidget.addWidget(panel);

                JLabel dashboardIcon = new JLabel();
                dashboardIcon.setBounds(36, 125, 35, 35);
                dashboardIcon.setIcon(new ImageIcon("resources/artboards/dash-icon-default.png"));
                panel.add(dashboardIcon);

                JLabel recordsIcon = new JLabel();
                recordsIcon.setBounds(39, 285, 35, 35);
                recordsIcon.setIcon(imagePlugins
                                .resize(new ImageIcon("resources/artboards/records-icon-default.png").getImage(),
                                                recordsIcon));
                panel.add(recordsIcon);

                JLabel officersIcon = new JLabel();
                officersIcon.setBounds(44, 349, 35, 25);
                officersIcon.setIcon(imagePlugins
                                .resize(new ImageIcon("resources/artboards/police-icon-selected.png").getImage(),
                                                officersIcon));
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

                JLabel FIRsIcon = new JLabel();
                FIRsIcon.setBounds(278, 264, 53, 53);
                FIRsIcon.setOpaque(true);
                FIRsIcon.setIcon(
                                imagePlugins.resize(new ImageIcon("resources/artboards/all-FIRs.png").getImage(),
                                                FIRsIcon));
                panel.add(FIRsIcon);

                JLabel FIRsLine = new JLabel();
                FIRsLine.setBounds(370, 254, 1, 100);
                FIRsLine.setBackground(Color.decode("#1A75D5"));
                FIRsLine.setOpaque(true);
                panel.add(FIRsLine);

                JLabel FIRsText = new JLabel();
                FIRsText.setFont(new Font("Jost", Font.PLAIN, 14));
                FIRsText.setForeground(Color.decode("#002349"));
                FIRsText.setBounds(294, 311, 142, 55);
                FIRsText.setText("F.I.R");
                panel.add(FIRsText);

                JTextArea FIRsDescription = new JTextArea();
                FIRsDescription.setBounds(394, 264, 150, 78);
                FIRsDescription.setText(
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend.");
                FIRsDescription.setForeground(Color.decode("#002349"));
                FIRsDescription.setEditable(false);
                FIRsDescription.setLineWrap(true);
                FIRsDescription.setWrapStyleWord(true);
                font = FIRsText.getFont();
                FIRsDescription.setFont(font);
                FIRsDescription.setCursor(new Cursor(Cursor.HAND_CURSOR));
                FIRsDescription.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                firController.getAllFir();
                        }
                });
                panel.add(FIRsDescription);

                JLabel policeIcon = new JLabel();
                policeIcon.setBounds(765, 264, 53, 53);
                policeIcon.setOpaque(true);
                policeIcon.setIcon(
                                imagePlugins.resize(new ImageIcon("resources/artboards/new-FIR.png").getImage(),
                                                policeIcon));
                panel.add(policeIcon);

                JLabel policeLine = new JLabel();
                policeLine.setBounds(855, 254, 1, 100);
                policeLine.setBackground(Color.decode("#1A75D5"));
                policeLine.setOpaque(true);
                panel.add(policeLine);

                JLabel policeText = new JLabel();
                policeText.setFont(new Font("Jost", Font.PLAIN, 14));
                policeText.setForeground(Color.decode("#002349"));
                policeText.setBounds(750, 311, 142, 55);
                policeText.setText("Police Officers");
                panel.add(policeText);

                JTextArea policeDescription = new JTextArea();
                policeDescription.setBounds(880, 264, 150, 78);
                policeDescription.setText(
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend.");
                policeDescription.setForeground(Color.decode("#002349"));
                policeDescription.setEditable(false);
                policeDescription.setLineWrap(true);
                policeDescription.setWrapStyleWord(true);
                font = policeText.getFont();
                policeDescription.setFont(font);
                policeDescription.setCursor(new Cursor(HAND_CURSOR));
                policeDescription.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                policeController.getAllPolice();
                        }
                });
                panel.add(policeDescription);

                JLabel citizenIcon = new JLabel();
                citizenIcon.setBounds(278, 526, 53, 53);
                citizenIcon.setOpaque(true);
                citizenIcon.setIcon(
                                imagePlugins.resize(new ImageIcon("resources/artboards/citizens.png").getImage(),
                                                citizenIcon));
                panel.add(citizenIcon);

                JLabel citizensLine = new JLabel();
                citizensLine.setBounds(370, 514, 1, 100);
                citizensLine.setBackground(Color.decode("#1A75D5"));
                citizensLine.setOpaque(true);
                panel.add(citizensLine);

                JLabel citizenText = new JLabel();
                citizenText.setFont(new Font("Jost", Font.PLAIN, 14));
                citizenText.setForeground(Color.decode("#002349"));
                citizenText.setBounds(278, 573, 142, 55);
                citizenText.setText("Citizens");
                panel.add(citizenText);

                JTextArea citizenDescription = new JTextArea();
                citizenDescription.setBounds(394, 524, 150, 78);
                citizenDescription.setText(
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend.");
                citizenDescription.setForeground(Color.decode("#002349"));
                citizenDescription.setEditable(false);
                citizenDescription.setLineWrap(true);
                citizenDescription.setWrapStyleWord(true);
                font = FIRsText.getFont();
                citizenDescription.setFont(font);
                citizenDescription.setCursor(new Cursor(Cursor.HAND_CURSOR));
                citizenDescription.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                citizenController.getAllCitizens();
                        }
                });
                panel.add(citizenDescription);

                JLabel offendersIcon = new JLabel();
                offendersIcon.setBounds(765, 526, 53, 53);
                offendersIcon.setOpaque(true);
                offendersIcon.setIcon(
                                imagePlugins.resize(new ImageIcon("resources/artboards/offender.png").getImage(),
                                                officersIcon));
                panel.add(offendersIcon);

                JLabel offendersLine = new JLabel();
                offendersLine.setBounds(855, 514, 1, 100);
                offendersLine.setBackground(Color.decode("#1A75D5"));
                offendersLine.setOpaque(true);
                panel.add(offendersLine);

                JLabel offendersText = new JLabel();
                offendersText.setFont(new Font("Jost", Font.PLAIN, 14));
                offendersText.setForeground(Color.decode("#002349"));
                offendersText.setBounds(760, 573, 142, 55);
                offendersText.setText("Offenders");
                panel.add(offendersText);

                JTextArea offenderDescription = new JTextArea();
                offenderDescription.setBounds(880, 524, 150, 78);
                offenderDescription.setText(
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend.");
                offenderDescription.setForeground(Color.decode("#002349"));
                offenderDescription.setEditable(false);
                offenderDescription.setLineWrap(true);
                offenderDescription.setWrapStyleWord(true);
                font = policeText.getFont();
                offenderDescription.setFont(font);
                offenderDescription.setCursor(new Cursor(Cursor.HAND_CURSOR));
                offenderDescription.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                //caseController.getAllCases();
                                citizenController.getAllCriminals();
                        }
                });
                panel.add(offenderDescription);

                JLabel backgroundLogin = new JLabel();
                backgroundLogin.setOpaque(true);
                backgroundLogin.setBounds(26, 0, 1293, 841);
                backgroundLogin.setIcon(new ImageIcon("resources/artboards/dashboard-cheif-background.png"));
                panel.add(backgroundLogin);
        }

        public JPanel getFrame() {
                return panel;
        }
}