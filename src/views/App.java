package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame {


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

		LoginPanel loginPanel = new LoginPanel();
		JPanel panel = loginPanel.getFrame();
		//RegisterPanel registerPanel = new RegisterPanel();
		//JPanel panel = registerPanel.getFrame();
		getContentPane().add(panel);//4447
	}

}
