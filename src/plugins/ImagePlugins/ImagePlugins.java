package plugins.ImagePlugins;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public interface ImagePlugins{
    ImageIcon resize(Image image, JLabel label);
    void cropCircle(String url, String uID);
    void scale();
}