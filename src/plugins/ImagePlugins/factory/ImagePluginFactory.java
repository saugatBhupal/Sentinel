package plugins.ImagePlugins.factory;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import plugins.ImagePlugins.ImagePlugins;
import plugins.ImagePlugins.plugin.ImageCropCirclePlugin;
import plugins.ImagePlugins.plugin.ImageResizerPlugin;

public class ImagePluginFactory implements ImagePlugins {

    @Override
    public void cropCircle(String url, String uID) {
       ImageCropCirclePlugin.run(url,uID);
    }

    @Override
    public void scale() {
       
    }

    @Override
    public ImageIcon resize(Image image, JLabel label) {
        return ImageResizerPlugin.run(image, label);
    }

}
