package plugins.AudioPlugins.factory;

import plugins.AudioPlugins.AudioPlugins;

public class AudioPluginFactory implements AudioPlugins{

    @Override
    public void convert() {
        System.out.println("Convert");
    }
    
}
