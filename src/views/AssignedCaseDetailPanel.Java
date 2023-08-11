package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Map;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import model.Police;
import model.Verdict;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.dateTime.DateTimeUtil;
import utils.generator.PasswordGenerator;
import utils.media.mediaUtil;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;
import views.widget.ImageViewerWidget;

public class AssignedCaseDetailPanel extends JFrame {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private final CitizenController citizenController;
    private final PoliceController policeController;
    private final FirController firController;
    private final CaseController caseController;
    private final VerdictController verdictController;
    private JFrame frame;
    private JPanel panel;
    private Font font;
    private String file;
    private Case cs;
    private final App app;
    private final List<Log> logs;
    private Map<TextAttribute, Object> attributes;

    public AssignedCaseDetailPanel(App app, Case cs, List<Log> logs) {
        this.cs = cs;
        this.app = app;
        this.logs = logs;
        this.citizenController = new CitizenControllerImpl(app);
        this.policeController = new PoliceControllerImpl(panel, app);
        this.firController = new FirControllerImpl(app);
        this.caseController = new CaseControllerImpl(app);
        this.verdictController = new VerdictControllerImpl(app);
        initialize();
    }

    private static JPanel createPanel(Log log) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 68));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(null);
        panel.setLayout(null);

        JTextArea logs = new JTextArea();
        logs.setText(log.getMessage());
        logs.setOpaque(false);
        logs.setFont(new Font("Jost", Font.PLAIN, 14));
        logs.setForeground(Color.decode("#165BBF"));
        logs.setBounds(15, 12, 590, 48);
        logs.setBorder(null);
        logs.setEditable(false);
        logs.setLineWrap(true);
        logs.setWrapStyleWord(true);
        panel.add(logs);

        RoundedLabel media = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
        media.setBounds(647, 13, 52, 17);
        media.setBackground(Color.decode("#E6EDF8"));
        media.setText("Media");
        media.setFont(new Font("Jost", Font.PLAIN, 10));
        media.setForeground(Color.decode("#647DC4"));
        media.setHorizontalAlignment(SwingConstants.CENTER);
        media.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
        media.setCursor(new Cursor(Cursor.HAND_CURSOR));
        media.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (log.getMedia() != null) {
                    ImageViewerWidget widget = new ImageViewerWidget(log.getMedia());
                    widget.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(panel, "No files uploaded");
                }
            }
        });

        panel.add(media);

        JLabel logsDate = new JLabel();
        logsDate.setText(log.getLogDate().toString());
        logsDate.setFont(new Font("Jost", Font.PLAIN, 10));
        logsDate.setForeground(Color.decode("#737373"));
        logsDate.setBounds(647, 36, 60, 32);
        panel.add(logsDate);

        JLabel logsTime = new JLabel();
        logsTime.setText(log.getLogTime().toString());
        logsTime.setFont(new Font("Jost", Font.PLAIN, 10));
        logsTime.setForeground(Color.decode("#737373"));
        logsTime.setBounds(647, 24, 42, 32);
        panel.add(logsTime);

        RoundedLabel logsPanelBackground = new RoundedLabel("", 10, Color.decode("#F2F4F7"), 12);
        logsPanelBackground.setBounds(0, 0, 716, 62);
        logsPanelBackground.setBackground(Color.decode("#E6EDF8"));
        panel.add(logsPanelBackground);

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
        parentPageTitle.setText("Case Details");
        parentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        parentPageTitle.setForeground(Color.decode("#002349"));
        parentPageTitle.setBounds(239, 61, 141, 32);
        panel.add(parentPageTitle);

        JLabel separator = new JLabel();
        separator.setIcon(new ImageIcon("resources/artboards/left-arrow-1.png"));
        separator.setBounds(285, 70, 16, 16);
        panel.add(separator);

        JLabel currentPageTitle = new JLabel();
        currentPageTitle.setText("View Case");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(310, 61, 151, 32);
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

        Fir fir = firController.getFirByID(cs.getFirID());

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

        JLabel logsTitle = new JLabel();
        logsTitle.setText("Case Logs");
        logsTitle.setFont(new Font("Jost", Font.PLAIN, 15));
        logsTitle.setForeground(Color.decode("#000000"));
        logsTitle.setBounds(282, 380, 182, 32);
        panel.add(logsTitle);

        RoundedLabel logsBackground = new RoundedLabel("", 20, Color.decode("#F2F4F7"), 12);
        logsBackground.setBounds(278, 417, 740, 180);
        logsBackground.setBackground(new Color(25, 95, 198, 8));
        panel.add(logsBackground);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(278, 417, 740, 180);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.decode("#F2F4F7"));
        panel.add(scrollPane);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        containerPanel.setBackground(Color.decode("#FFFFFF"));

        if (logs != null) {
            Collections.reverse(logs);
            for (Log log : logs) {
                JPanel panel = createPanel(log);
                containerPanel.add(panel);
                containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }
        scrollPane.setViewportView(containerPanel);

        if (cs.getFir().getStatus().equals(2)) {
            RoundedLabel viewDocument = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
            viewDocument.setBounds(282, 645, 116, 26);
            viewDocument.setBackground(Color.decode("#FFFFFF"));
            viewDocument.setText("+ View Document");
            viewDocument.setFont(new Font("Jost", Font.PLAIN, 11));
            viewDocument.setForeground(Color.decode("#647DC4"));
            viewDocument.setHorizontalAlignment(SwingConstants.CENTER);
            viewDocument.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
            viewDocument.setCursor(new Cursor(Cursor.HAND_CURSOR));
            viewDocument.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Verdict v = verdictController.getVerdict(cs);
                    if (v != null) {
                        ImageViewerWidget widget = new ImageViewerWidget((v.getVerdict()));
                        widget.setVisible(true);
                    }
                }
            });
            panel.add(viewDocument);
        } else {

            if (app.context.getRole().equals("oic")) {
                JLabel verdict = new JLabel();
                verdict.setText("Court Verdict");
                verdict.setFont(new Font("Jost", Font.PLAIN, 15));
                verdict.setForeground(Color.decode("#000000"));
                verdict.setBounds(282, 610, 182, 32);
                panel.add(verdict);

                RoundedLabel uploadDocument = new RoundedLabel("", 20, Color.decode("#FEEEEE"), 9);
                uploadDocument.setBounds(282, 645, 116, 26);
                uploadDocument.setBackground(Color.decode("#FFFFFF"));
                uploadDocument.setText("+ Upload Document");
                uploadDocument.setFont(new Font("Jost", Font.PLAIN, 11));
                uploadDocument.setForeground(Color.decode("#647DC4"));
                uploadDocument.setHorizontalAlignment(SwingConstants.CENTER);
                uploadDocument.setBorder(new RoundedBorderLabel(Color.decode("#26449E"), 1, 6));
                uploadDocument.setCursor(new Cursor(Cursor.HAND_CURSOR));
                uploadDocument.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png");
                        fileChooser.setFileFilter(filter);
                        int action = fileChooser.showSaveDialog(null);
                        if (action == fileChooser.APPROVE_OPTION) {
                            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                            file = mediaUtil.saveImage(filePath);
                            if (file != null) {
                                JOptionPane.showMessageDialog(app, "Successfully saved file");
                            } else {
                                JOptionPane.showMessageDialog(app, "Error saving file");
                            }
                        }
                    }
                });
                panel.add(uploadDocument);

                RoundedLabel submitCase = new RoundedLabel("", 10, Color.decode("#FEEEEE"), 9);
                submitCase.setBounds(550, 706, 140, 35);
                submitCase.setBackground(Color.decode("#1A75D5"));
                submitCase.setText("Update Case");
                submitCase.setFont(new Font("Jost", Font.PLAIN, 14));
                submitCase.setForeground(Color.decode("#FFFFFF"));
                submitCase.setHorizontalAlignment(SwingConstants.CENTER);
                submitCase.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 10));
                submitCase.setCursor(new Cursor(Cursor.HAND_CURSOR));
                submitCase.addMouseListener(Hover.newColor(submitCase, "#1A75D5", "#165EAA"));
                submitCase.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Verdict verdict = new Verdict();
                        verdict.setVerdict(file);
                        verdict.setVerdictDate(Date.valueOf(LocalDate.now()));
                        verdict.setCaseID(cs.getCaseID());
                        verdictController.saveVerdict(verdict);
                    }
                });
                panel.add(submitCase);
            } else {
                JTextArea newLog = new JTextArea();
                newLog.setBackground(Color.decode("#FAFAFA"));
                newLog.setFont(new Font("Jost", Font.PLAIN, 14));
                newLog.setForeground(Color.decode("#165BBF"));
                newLog.setBounds(306, 626, 558, 38);
                newLog.setBorder(null);
                newLog.setEditable(true);
                newLog.addFocusListener(Focus.setPlaceholderArea(newLog, "Enter new case log"));
                newLog.setLineWrap(true);
                newLog.setWrapStyleWord(true);
                panel.add(newLog);

                RoundedLabel newLogsBackground = new RoundedLabel("", 10, Color.decode("#F2F4F7"), 12);
                newLogsBackground.setBounds(286, 620, 600, 54);
                newLogsBackground.setBackground(Color.decode("#FAFAFA"));
                newLogsBackground.setBorder(new RoundedBorderLabel(Color.decode("#F2F4F7"), 1, 12));
                panel.add(newLogsBackground);

                RoundedLabel addEvidence = new RoundedLabel("", 10, Color.decode("#FEEEEE"), 9);
                addEvidence.setBounds(896, 634, 98, 28);
                addEvidence.setBackground(Color.decode("#1A75D5"));
                addEvidence.setText("+ Evidence");
                addEvidence.setFont(new Font("Jost", Font.PLAIN, 14));
                addEvidence.setForeground(Color.decode("#FFFFFF"));
                addEvidence.setHorizontalAlignment(SwingConstants.CENTER);
                addEvidence.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 10));
                addEvidence.setCursor(new Cursor(Cursor.HAND_CURSOR));
                addEvidence.addMouseListener(Hover.newColor(addEvidence, "#1A75D5", "#165EAA"));
                addEvidence.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png");
                        fileChooser.setFileFilter(filter);
                        int action = fileChooser.showSaveDialog(null);
                        if (action == fileChooser.APPROVE_OPTION) {
                            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                            file = mediaUtil.saveImage(filePath);
                            if (file != null) {
                                JOptionPane.showMessageDialog(app, "Successfully saved file");
                            } else {
                                JOptionPane.showMessageDialog(app, "Error saving file");
                            }
                        }
                    }
                });
                panel.add(addEvidence);

                RoundedLabel updateLogs = new RoundedLabel("", 10, Color.decode("#FEEEEE"), 9);
                updateLogs.setBounds(550, 706, 140, 35);
                updateLogs.setBackground(Color.decode("#1A75D5"));
                updateLogs.setText("Update Case");
                updateLogs.setFont(new Font("Jost", Font.PLAIN, 14));
                updateLogs.setForeground(Color.decode("#FFFFFF"));
                updateLogs.setHorizontalAlignment(SwingConstants.CENTER);
                updateLogs.setBorder(new RoundedBorderLabel(Color.decode("#1A75D5"), 1, 10));
                updateLogs.setCursor(new Cursor(Cursor.HAND_CURSOR));
                updateLogs.addMouseListener(Hover.newColor(updateLogs, "#1A75D5", "#165EAA"));
                updateLogs.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String logText = newLog.getText().trim();
                        if (!logText.isEmpty() && !Focus.hasPlaceholder(newLog, "Enter new case log")) {
                            Log log = new Log();
                            log.setMessage(newLog.getText());
                            log.setLogDate(Date.valueOf(LocalDate.now()));
                            log.setLogTime(Time.valueOf(LocalTime.now()));
                            log.setMedia(file);
                            log.setCaseID(cs.getCaseID());
                            JPanel panel = createPanel(new Log(newLog.getText(), Date.valueOf(DateTimeUtil.getDate()),
                                    Time.valueOf(DateTimeUtil.getFullTime()), Long.parseLong("1234")));
                            containerPanel.add(panel, 0);
                            containerPanel.add(Box.createRigidArea(new Dimension(0, 0)));
                            newLog.setText("");
                            scrollPane.revalidate();
                            scrollPane.repaint();
                            caseController.registerLog(log);
                        } else {
                            JOptionPane.showMessageDialog(panel, "Cannot upload empty case log");
                        }
                    }
                });
                panel.add(updateLogs);
            }
        }
            JLabel backgroundLogin = new JLabel();
            backgroundLogin.setOpaque(true);
            backgroundLogin.setBounds(25, -25, 1393, 841);
            backgroundLogin.setIcon(new ImageIcon("resources/artboards/assigned-case-background.png"));
            panel.add(backgroundLogin);
        }
    

    public JPanel getFrame() {
        return panel;
    }
}