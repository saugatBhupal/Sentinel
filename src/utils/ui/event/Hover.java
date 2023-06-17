package utils.ui.event;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Hover {
	
    public static MouseAdapter newColor(JLabel component, String oldColor, String newColor){
        return new MouseAdapter() {

            @Override
			public void mouseEntered(MouseEvent e){
				component.setBackground(Color.decode("#013B7A"));
			}

			@Override
			public void mouseExited(MouseEvent e){
				component.setBackground(Color.decode("#165EAA"));
			}
        };
    }

	public static MouseAdapter focusable(JTextField component){
        return new MouseAdapter() {

            @Override
			public void mouseEntered(MouseEvent e){
				component.setFocusable(true);
			}

			@Override
			public void mouseExited(MouseEvent e){
				component.setFocusable(false);
			}
        };
    }

	public static MouseAdapter animate(JLabel component, Integer startX, Integer endX){
			Timer timer = new Timer();
			return new MouseAdapter() {

            @Override
			public void mouseEntered(MouseEvent e){
				TimerTask tt = new TimerTask() {
				Integer x = startX;
				@Override
				public void run(){
					if(x <= endX){
						component.setBounds(x, component.getBounds().y, component.getWidth(), component.getHeight());
						x+=6;
					}
				}
				};
				timer.schedule(tt, 1, 20);
				
			}

			@Override
			public void mouseExited(MouseEvent e){
				component.setBounds(startX, component.getBounds().y, component.getWidth(), component.getHeight());
			}
        };
	}

}
