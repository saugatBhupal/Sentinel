package views.widget;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import plugins.MediaFormat;
import plugins.ImagePlugins.ImagePlugins;
import plugins.PluginFactory.PluginFactory;
import utils.dateTime.DateTimeUtil;

class UpdateTime extends Thread{
	private JLabel time;

	public UpdateTime(JLabel time){
		this.time = time;
	}
	@Override
	public void run(){
		while(true){
			time.setText(DateTimeUtil.getTime());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}

public class DateTimeWidget{
    private static ImagePlugins imagePlugins = PluginFactory.createPlugin(MediaFormat.ofType.IMAGE);

    public static void addWidget(JPanel component){

        JLabel clockIcon = new JLabel();
		clockIcon.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/clock-icon.png").getImage(), clockIcon));
		clockIcon.setBounds(964, 49, 24,24 );
		component.add(clockIcon);

		JLabel time = new JLabel(DateTimeUtil.getTime());
		time.setFont(new Font("Jost", Font.PLAIN, 14));
		time.setForeground(Color.decode("#59687E"));
		time.setBounds(996, 50, 75, 20);
		component.add(time);

		JLabel calendarIcon = new JLabel();
		calendarIcon.setIcon(imagePlugins.resize(new ImageIcon("resources/artboards/calendar-icon.png").getImage(), calendarIcon));
		calendarIcon.setBounds(964, 76, 24,24);
		component.add(calendarIcon);

		JLabel date = new JLabel(DateTimeUtil.getDate().toString());
		date.setFont(new Font("Jost", Font.PLAIN, 14));
		date.setForeground(Color.decode("#59687E"));
        date.setHorizontalAlignment(SwingConstants.LEFT);
		date.setBounds(996, 78, 88, 20);
		component.add(date);

		UpdateTime updateTime = new UpdateTime(time);
		updateTime.start();

    }
}
     


