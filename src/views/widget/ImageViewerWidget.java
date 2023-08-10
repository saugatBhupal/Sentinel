package views.widget;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.ImagePlugins.factory.ImagePluginFactory;
import plugins.PluginFactory.PluginFactory;

public class ImageViewerWidget extends JFrame{
    private final String imageBasePath = "resources/assets/documents/";
    private final ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);

    public ImageViewerWidget(String filename){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
        setLocationRelativeTo(null);
		getContentPane().setLayout(null);
        setVisible(true);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(imageBasePath + filename + ".png"));
        imageLabel.setBounds(0, 0, 600, 600);
        getContentPane().add(imageLabel);
    }
}
