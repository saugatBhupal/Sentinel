package utils.ui.event;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Hover {
	
    public static MouseAdapter newColor(JLabel component, String oldColor, String newColor){
        return new MouseAdapter() {

            @Override
			public void mouseEntered(MouseEvent e){
				component.setBackground(Color.decode(newColor));
			}

			@Override
			public void mouseExited(MouseEvent e){
				component.setBackground(Color.decode(oldColor));
			}
        };
    }
    public static MouseAdapter newColor(JFileChooser component, String oldColor, String newColor){
        return new MouseAdapter() {

            @Override
			public void mouseEntered(MouseEvent e){
				component.setBackground(Color.decode(newColor));
			}

			@Override
			public void mouseExited(MouseEvent e){
				component.setBackground(Color.decode(oldColor));
			}
        };
    }
	public static MouseAdapter animateOutline(JLabel component, String oldBg, String oldFg, String newBg, String newFg){
			return new MouseAdapter() {

            @Override
			public void mouseEntered(MouseEvent e){
				component.setBackground(Color.decode(newBg));
				component.setForeground(Color.decode(newFg));
			}

			@Override
			public void mouseExited(MouseEvent e){
				component.setBackground(Color.decode(oldBg));
				component.setForeground(Color.decode(oldFg));
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
