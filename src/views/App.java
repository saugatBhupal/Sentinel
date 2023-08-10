package views;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Context;

public class App extends JFrame {

	public Context context = new Context();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("#     _____            _   _            _ \n"
					+ "#    / ____|          | | (_)          | |\n"
					+ "#   | (___   ___ _ __ | |_ _ _ __   ___| |\n"
					+ "#    \\___ \\ / _ \\ '_ \\| __| | '_ \\ / _ \\ |\n"
					+ "#    ____) |  __/ | | | |_| | | | |  __/ |\n"
					+ "#   |_____/ \\___|_| |_|\\__|_|_| |_|\\___|_|\n\n"
					+ "#    Starting Application :  \n");
					App frame = new App();
					frame.setVisible(true);
					System.out.println("#    Application Running :  \n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1201, 841);
		getContentPane().setLayout(null);

		LoginPanel loginPanel = new LoginPanel(this);
		JPanel panel = loginPanel.getFrame();
		getContentPane().add(panel);//4447
	}

	public void addPanel(JPanel panel){
		Container container = getContentPane();
		container.removeAll();
		container.revalidate();
		container.repaint();
		container.add(panel);
	}

}
