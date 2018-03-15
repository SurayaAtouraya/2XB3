package final_Project;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Interface {

	Map<String, List<String>> map = Reviews.initMapFromFile("Reviews.txt");
	
	private JFrame frame = new JFrame();
	private JPanel pane = new JPanel();
	private DefaultTableModel model = new DefaultTableModel(); 

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public Interface() throws IOException {
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() throws IOException {

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		
        JButton loadData = new JButton("Load All");
        JButton test2 = new JButton("Test2");
        JButton end = new JButton("Exit Program");
        end.addActionListener(new CloseListener());
        loadData.addActionListener(new LoadListener());
		pane.add(loadData);
		pane.add(test2);
		pane.add(end);
        
		JTable contractorTable = new JTable(model); 
		model.addColumn("Contractor Name"); 
		model.addColumn("License Number"); 
		model.addColumn("State");
		model.addColumn("City");
		model.addColumn("Address"); 
		model.addColumn("Speciality"); 
		model.addColumn("Rating");  
		JScrollPane scrollPane = new JScrollPane(contractorTable);
		scrollPane.setPreferredSize(new Dimension(1300, 650));
		pane.add(scrollPane);
		
		frame.add(pane);
		frame.setVisible(true);
	}
	
	//Close Button
	private class CloseListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	try {
				Reviews.writeMapToFile(map, "Reviews.txt");
			} catch (FileNotFoundException | UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
	        System.exit(0);
	    }
	}
	//Load All button
	private class LoadListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        List<Contractor> fromSample = TestRead.readSample();
	        for (int i = 0; i < fromSample.size(); i++) {
	        	Contractor c = fromSample.get(i);
	        	model.addRow(new Object[] {c.getContractorName(),c.getLicenseNumber(),c.getState(),
	        			c.getCity(),c.getAddress(),c.getSpecialty(),c.avgReview(map)});
	        }
	    }
	}
}
