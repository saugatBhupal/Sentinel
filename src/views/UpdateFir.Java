package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.FirController;
import controller.controllerImpl.FirControllerImpl;
import model.Fir;
import model.Police;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import views.widget.DateTimeWidget;

public class UpdateFir extends JFrame {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private final FirController firController;
    private final App app;
    private JFrame frame;
    private JPanel panel;
    private final Fir fir;

    public UpdateFir(App app, Fir fir) {
        this.app = app;
        this.fir = fir;
        this.firController = new FirControllerImpl(app);
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
        currentPageTitle.setText("Update F.I.R");
        currentPageTitle.setFont(new Font("Jost", Font.PLAIN, 20));
        currentPageTitle.setForeground(Color.decode("#1A75D5"));
        currentPageTitle.setBounds(310, 61, 151, 32);
        panel.add(currentPageTitle);

        DateTimeWidget.addWidget(panel);

        JLabel complaintByLabel = new JLabel();
        complaintByLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        complaintByLabel.setBounds(285, 145, 134, 31);
        complaintByLabel.setText("Complaint By");
        panel.add(complaintByLabel);

        JTextField complaintByField = new JTextField();
        complaintByField.setFont(new Font("Jost", Font.PLAIN, 18));
        complaintByField.setBackground(Color.decode("#ECECEC"));
        complaintByField.setBorder(null);
        complaintByField.setForeground(Color.decode("#6D6767"));
        complaintByField.setText(String.valueOf(fir.getFiledBy()));
        // complaintByField.addFocusListener(Focus.setPlaceholder(complaintByField,
        // String.valueOf(fir.getFiledBy())));
        complaintByField.setBounds(292, 186, 300, 27);
        panel.add(complaintByField);

        JLabel complaintByLine = new JLabel();
        complaintByLine.setBounds(285, 218, 290, 1);
        complaintByLine.setBackground(Color.decode("#000000"));
        complaintByLine.setOpaque(true);
        panel.add(complaintByLine);

        JLabel againstLabel = new JLabel();
        againstLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        againstLabel.setBounds(750, 145, 264, 31);
        againstLabel.setText("Complaint Against (Optional)");
        panel.add(againstLabel);

        JTextField againstField = new JTextField();
        againstField.setFont(new Font("Jost", Font.PLAIN, 18));
        againstField.setBackground(Color.decode("#ECECEC"));
        againstField.setBorder(null);
        againstField.setForeground(Color.decode("#6D6767"));
        againstField.addFocusListener(Focus.setPlaceholder(againstField, "Citizen I.D / Name"));
        againstField.setBounds(758, 186, 300, 27);
        againstField.setText(String.valueOf(fir.getFiledAgainst()));
        panel.add(againstField);

        JLabel againstLine = new JLabel();
        againstLine.setBounds(750, 218, 290, 1);
        againstLine.setBackground(Color.decode("#000000"));
        againstLine.setOpaque(true);
        panel.add(againstLine);

        JLabel incidentLabel = new JLabel();
        incidentLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        incidentLabel.setBounds(285, 264, 264, 31);
        incidentLabel.setText("Incident Description");
        panel.add(incidentLabel);

        JTextArea incidentField = new JTextArea();
        incidentField.setFont(new Font("Jost", Font.PLAIN, 18));
        incidentField.setBackground(Color.decode("#F5F4F4"));
        incidentField.setBorder(null);
        incidentField.setForeground(Color.decode("#6D6767"));
        incidentField.setBounds(295, 324, 700, 130);
        incidentField.setLineWrap(true);
        incidentField.setText(String.valueOf(fir.getDescription()));
        panel.add(incidentField);

        JLabel dateTimeLabel = new JLabel();
        dateTimeLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        dateTimeLabel.setBounds(285, 465, 264, 31);
        dateTimeLabel.setText("Incident Date & Time");
        panel.add(dateTimeLabel);

        Date filedDate = fir.getFiledDate();
        LocalDate localFiledDate = filedDate.toLocalDate();

        Time filedTime = fir.getFiledTime();
        LocalTime localFiledTime = filedTime.toLocalTime();

        JTextField dateField = new JTextField();
        dateField.setFont(new Font("Jost", Font.PLAIN, 18));
        dateField.setBackground(Color.decode("#ECECEC"));
        dateField.setBorder(null);
        dateField.setForeground(Color.decode("#6D6767"));
        dateField.addFocusListener(Focus.setPlaceholder(dateField, "dd"));
        dateField.setBounds(288, 508, 22, 30);
        dateField.setText(String.valueOf(localFiledDate.getDayOfMonth()));
        panel.add(dateField);

        JTextField monthField = new JTextField();
        monthField.setFont(new Font("Jost", Font.PLAIN, 18));
        monthField.setBackground(Color.decode("#ECECEC"));
        monthField.setBorder(null);
        monthField.setForeground(Color.decode("#6D6767"));
        monthField.addFocusListener(Focus.setPlaceholder(monthField, "mm"));
        monthField.setBounds(344, 508, 30, 30);
        monthField.setText(String.valueOf(localFiledDate.getMonthValue()));
        panel.add(monthField);

        JTextField yearField = new JTextField();
        yearField.setFont(new Font("Jost", Font.PLAIN, 18));
        yearField.setBackground(Color.decode("#ECECEC"));
        yearField.setBorder(null);
        yearField.setForeground(Color.decode("#6D6767"));
        yearField.addFocusListener(Focus.setPlaceholder(yearField, "yyyy"));
        yearField.setBounds(413, 506, 45, 30);
        yearField.setText(String.valueOf(localFiledDate.getYear()));
        panel.add(yearField);

        JTextField hourField = new JTextField();
        hourField.setFont(new Font("Jost", Font.PLAIN, 18));
        hourField.setBackground(Color.decode("#ECECEC"));
        hourField.setBorder(null);
        hourField.setForeground(Color.decode("#6D6767"));
        hourField.addFocusListener(Focus.setPlaceholder(hourField, "hh"));
        hourField.setBounds(509, 506, 26, 30);
        hourField.setText(String.valueOf(localFiledTime.getHour()));
        panel.add(hourField);

        JTextField minuteField = new JTextField();
        minuteField.setFont(new Font("Jost", Font.PLAIN, 18));
        minuteField.setBackground(Color.decode("#ECECEC"));
        minuteField.setBorder(null);
        minuteField.setForeground(Color.decode("#6D6767"));
        minuteField.addFocusListener(Focus.setPlaceholder(minuteField, "mm"));
        minuteField.setBounds(567, 506, 30, 30);
        minuteField.setText(String.valueOf(localFiledTime.getMinute()));
        panel.add(minuteField);

        JTextField secondsField = new JTextField();
        secondsField.setFont(new Font("Jost", Font.PLAIN, 18));
        secondsField.setBackground(Color.decode("#ECECEC"));
        secondsField.setBorder(null);
        secondsField.setForeground(Color.decode("#6D6767"));
        secondsField.addFocusListener(Focus.setPlaceholder(secondsField, "ss"));
        secondsField.setBounds(632, 506, 26, 30);
        secondsField.setText(String.valueOf(localFiledTime.getSecond()));
        panel.add(secondsField);

        JLabel evidenceLabel = new JLabel();
        evidenceLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        evidenceLabel.setBounds(285, 555, 264, 31);
        evidenceLabel.setText("Evidence (Optional)");
        panel.add(evidenceLabel);

        JLabel addEvidence = new JLabel("+ Upload Files");
        addEvidence.setFont(new Font("Jost", Font.PLAIN, 16));
        addEvidence.setBackground(Color.decode("#1A75D5"));
        addEvidence.setOpaque(true);
        addEvidence.setHorizontalAlignment(SwingConstants.CENTER);
        addEvidence.setForeground(new Color(255, 255, 255, 240));
        addEvidence.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addEvidence.setBounds(285, 595, 184, 40);
        addEvidence.addMouseListener(Hover.newColor(addEvidence, "#1A75D5", "#165EAA"));
        panel.add(addEvidence);

        JLabel witnessLabel = new JLabel();
        witnessLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        witnessLabel.setBounds(750, 465, 264, 31);
        witnessLabel.setText("Witness (Optional)");
        panel.add(witnessLabel);

        JTextField witnessField = new JTextField();
        witnessField.setFont(new Font("Jost", Font.PLAIN, 18));
        witnessField.setBackground(Color.decode("#ECECEC"));
        witnessField.setBorder(null);
        witnessField.setForeground(Color.decode("#6D6767"));
        if (fir.getWitness() != null) {
            witnessField.setText(String.valueOf(fir.getWitness()));
        }
        else{
            witnessField.addFocusListener(Focus.setPlaceholder(witnessField, "Citizen I.D / Name"));
        }
        witnessField.setBounds(750, 505, 300, 27);
        panel.add(witnessField);

        JLabel witnessLine = new JLabel();
        witnessLine.setBounds(750, 535, 290, 1);
        witnessLine.setBackground(Color.decode("#000000"));
        witnessLine.setOpaque(true);
        panel.add(witnessLine);

        JLabel categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Jost", Font.PLAIN, 19));
        categoryLabel.setBounds(750, 555, 264, 31);
        categoryLabel.setText("Category");
        panel.add(categoryLabel);

        String categories[] = { "Theft/Burglary", "Assault", "Fraud/Forgery",
                "Cybercrime", "Domestic Violence" };
        JComboBox<String> category = new JComboBox<>(categories);
        category.setSelectedItem(fir.getCategory());
        category.setBounds(746, 590, 200, 30);
        category.setFont(new Font("Jost", Font.PLAIN, 16));
        category.setForeground(Color.decode("#6D6767"));
        category.setBackground(Color.decode("#ffffff"));
        category.setFocusable(false);
        panel.add(category);

        JLabel updateFir = new JLabel("Update F.I.R");
        updateFir.setFont(new Font("Jost", Font.PLAIN, 16));
        updateFir.setBackground(Color.decode("#1A75D5"));
        updateFir.setOpaque(true);
        updateFir.setHorizontalAlignment(SwingConstants.CENTER);
        updateFir.setForeground(new Color(255, 255, 255, 240));
        updateFir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateFir.setBounds(585, 670, 184, 40);
        updateFir.addMouseListener(Hover.newColor(updateFir, "#1A75D5", "#165EAA"));
        updateFir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fir.setFiledBy(Long.valueOf(complaintByField.getText()));
                fir.setFiledAgainst(Long.parseLong(againstField.getText()));
                fir.setFiledDate(
                        Date.valueOf(yearField.getText() + "-" + monthField.getText() + "-" + dateField.getText()));
                fir.setFiledTime(
                        Time.valueOf(hourField.getText() + ":" + minuteField.getText() + ":" + secondsField.getText()));
                fir.setDescription(incidentField.getText());
                fir.setCategory(category.getSelectedItem().toString());
                fir.setEvidence(null);
                fir.setRegisteredBy(app.context.getPolice().getPoliceID());
                String witnessText = witnessField.getText();
                long witnessValue = (!witnessText.isEmpty()) ? Long.parseLong(witnessText) : null;
                fir.setWitness(witnessValue);
                firController.updateFir(fir);
            }
        });
        panel.add(updateFir);

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

        JLabel backgroundFir = new JLabel();
        backgroundFir.setOpaque(true);
        backgroundFir.setBounds(108, 20, 1093, 841);
        backgroundFir.setIcon(new ImageIcon("resources/artboards/create-F.I.R-background.png"));
        panel.add(backgroundFir);
    }

    public JPanel getFrame() {
        return panel;
    }
}