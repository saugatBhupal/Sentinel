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
}
}