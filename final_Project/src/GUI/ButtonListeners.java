package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Read.Reviews;

//Class that includes all button action listeners used by the GUI
public class ButtonListeners implements ActionListener {

	
	public class CloseMainWindow implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (JOptionPane.showConfirmDialog(MainMenu.frame, 
		            "Are you sure to close this window?", "Closing Confirmation", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						Reviews.writeMapToFile(MainMenu.map, "Reviews.txt");
					} catch (FileNotFoundException | UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
		            System.exit(0);
		        }
	    }
	}
	
	public class ConfirmPopUpWindowClose implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("TEST");
	    }
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
