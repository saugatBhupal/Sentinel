package plugins.PluginFactory;

import plugins.MediaFormat;
import plugins.AudioPlugins.AudioPlugins;
import plugins.AudioPlugins.factory.AudioPluginFactory;
import plugins.ImagePlugins.ImagePlugins;
import plugins.ImagePlugins.factory.ImagePluginFactory;

public class PluginFactory {

    @SuppressWarnings("unchecked")
    public static <any> any createPlugin(MediaFormat.ofType plugin){
        if (plugin.equals(MediaFormat.ofType.IMAGE)) {
            ImagePlugins imagePlugins = new ImagePluginFactory();
            return (any)(ImagePlugins) imagePlugins;
        } 
        else{
            AudioPlugins audioPlugins = new AudioPluginFactory();
            return (any) (AudioPlugins) audioPlugins;
        }
    }
   
}
