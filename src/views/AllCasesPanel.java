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

    

