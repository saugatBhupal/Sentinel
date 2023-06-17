package plugins.ImagePlugins.plugin;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageResizerPlugin {
    public static ImageIcon run(Image image, JLabel label) {

        int labelWidth = label.getWidth();
        int labelHeight = label.getHeight();
    
        if (labelWidth == 0 || labelHeight == 0) {
            labelWidth = 100;
            labelHeight = 100;
        }
    
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
    
        if (imageWidth <= labelWidth && imageHeight <= labelHeight) {
            return new ImageIcon(image);
        }
    
        double ratio = Math.min((double) labelWidth / (double) imageWidth, (double) labelHeight / (double) imageHeight);
        int newWidth = (int) (imageWidth * ratio);
        int newHeight = (int) (imageHeight * ratio);
    
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
    
        return new ImageIcon(scaledImage);
    }
}

    

