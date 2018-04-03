package GUI;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SplashScreen {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {
						@SuppressWarnings("unused")
						SplashScreen window = new SplashScreen();
						UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
					} catch (InterruptedException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
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
					Thread.sleep(1000);
					MainMenu.main(null);
					frame.dispose();
				} catch (InterruptedException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | IOException e) {
					e.printStackTrace();
				}
		         
		    }});  
		    t1.start();
	}

}
