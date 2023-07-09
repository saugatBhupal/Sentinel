package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import controller.CaseController;
import controller.CitizenController;
import controller.FirController;
import controller.PoliceController;
import controller.controllerImpl.CaseControllerImpl;
import controller.controllerImpl.CitizenControllerImpl;
import controller.controllerImpl.FirControllerImpl;
import controller.controllerImpl.PoliceControllerImpl;
import model.Citizen;
import model.Fir;
import model.Log;
import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.ui.event.Focus;
import utils.ui.event.Hover;
import utils.ui.graphic.RoundedLabel;
import utils.ui.graphic.RoundedBorderLabel;
import views.widget.DateTimeWidget;

import model.Case;

public class AssignedCasesPanel extends JFrame {
    private ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);
    private static FirController firController;
    private static CaseController caseController;
    private static PoliceController policeController;
    private static CitizenController citizenController;
    private JFrame frame;
    private JPanel panel;
    private final List<Case> cases;
    private final App app;
    private Font font;
    private Map<TextAttribute, Object> attributes;
    public AssignedCasesPanel(App app, List<Case> cases) {
        this.app = app;
        this.firController = new FirControllerImpl(app);
        this.caseController = new CaseControllerImpl(app);
        this.policeController = new PoliceControllerImpl(panel, app);
        this.citizenController = new CitizenControllerImpl(app);
        this.cases = cases;
        initialize();
    }


    private static JPanel createPanel(Case cs) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(759, 117));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(null);
        panel.setLayout(null);

        Fir fir = firController.getFirByID(cs.getFirID());
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
