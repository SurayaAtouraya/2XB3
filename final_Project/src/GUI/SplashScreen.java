package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UnsupportedLookAndFeelException;

public class SplashScreen {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {
						SplashScreen window = new SplashScreen();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		});
	}

	public SplashScreen() throws InterruptedException {
		initialize();
	}

	private void initialize() throws InterruptedException {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.add(new JLabel(new ImageIcon("logo.png")));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Thread t1 = new Thread(new Runnable() {
		    public void run()
		    {
		         try {
					Thread.sleep(5000);
					MainMenu.main(null);
					frame.dispose();
				} catch (InterruptedException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
		         
		    }});  
		    t1.start();
	}

}
