package final_Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Interface {

	Map<String, List<String>> map = Reviews.initMapFromFile("Reviews.txt");
	
	private JFrame frame = new JFrame(); 						//Main menu frame.
	private JPanel pane = new JPanel(); 						//Main menu pane.
	private DefaultTableModel model = new DefaultTableModel();	//Main menu table. 
	private JFrame newFrame = new JFrame(); 					//Pop up frame for finding ideal contractor.
	private JPanel newFramePane = new JPanel();
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
	
	//Initialize the contents of the main frame.
	private void initialize() throws IOException {

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		
        JButton loadData = new JButton("Load All");
        JButton find = new JButton("Find Contractor");
        JButton end = new JButton("Exit Program");
        end.addActionListener(new CloseListener());
        loadData.addActionListener(new LoadListener());
        find.addActionListener(new FindListener());
        
		pane.add(loadData);
		pane.add(find);
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
	        Contractor[] fromSample = TestRead.readSample();
	        for (int i = 0; i < fromSample.length; i++) {
	        	Contractor c = fromSample[i];
	        	model.addRow(new Object[] {c.getContractorName(),c.getLicenseNumber(),c.getState(),
	        			c.getCity(),c.getAddress(),c.getSpecialty(),c.avgReview(map)});
	        }
	    }
	}
	JComboBox cities = new JComboBox(InterfaceDatabase.readCities("AK"));
	//Find Button which loads the new frame for finding a contractor.
	private class FindListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	
	    	frame.setEnabled(false);
	    	
	    	JComboBox states = new JComboBox(InterfaceDatabase.readStates());
	    	
	    	states.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	newFramePane.remove(newFramePane.getComponentCount()-5);
	            	cities = new JComboBox(InterfaceDatabase.readCities(states.getSelectedItem().toString()));
	            	newFramePane.add(cities, newFramePane.getComponentCount()-4);
	            	newFrame.add(newFramePane);
	            	newFrame.setVisible(true);
	            }          
	         });
	    	
	    	JComboBox speciality = new JComboBox(InterfaceDatabase.readSpecialities());
	    	
	    	JLabel stateMsg = new JLabel("Please select your state:");
	    	JLabel cityMsg = new JLabel("Please select your city:");
	    	JLabel specialityMsg = new JLabel("Please select your work:");
	    	
	    	stateMsg.setAlignmentX(250);
	    	cityMsg.setAlignmentX(250);
	    	specialityMsg.setAlignmentX(250);
	    	
	    	JButton cancel = new JButton("Cancel");
	        cancel.addActionListener(new NewFrameCloseListener());
	        
	    	JButton find = new JButton("Search");
	    	find.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) { //results returns as null?
	            	cancel.doClick();
	            	Contractor[] allCons = TestRead.readSample();
	            	SortMe.sort(allCons);
	            	Contractor ideal = new Contractor(cities.getSelectedItem().toString().trim(), states.getSelectedItem().toString().trim(), speciality.getSelectedItem().toString().trim());
	            	System.out.println(cities.getSelectedItem().toString().trim());
	            	System.out.println(states.getSelectedItem().toString().trim());
	            	System.out.println(speciality.getSelectedItem().toString().trim());

	            	try {
	            		Contractor[] results = SortMe.search(allCons, ideal, "Reviews");
						for (int i = 0; i < results.length; i++) {
		    	        	Contractor c = results[i];
		    	        	System.out.println(c.getContractorName());
		    	        	model.addRow(new Object[] {c.getContractorName(),c.getLicenseNumber(),c.getState(),
		    	        			c.getCity(),c.getAddress(),c.getSpecialty(),c.avgReview(map)});
		    	        }
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            }          
	         });
	    	
	    	
	    	newFrame.setUndecorated(true);
	    	newFrame.setSize(500,300);
	    	newFrame.setLocationRelativeTo(null);
	    	newFrame.setAlwaysOnTop(true);
	        
	    	newFramePane.setLayout(new BoxLayout(newFramePane, BoxLayout.Y_AXIS));
	        newFramePane.add(stateMsg);
	        newFramePane.add(states);			
	        newFramePane.add(cityMsg);
	        newFramePane.add(cities);				
	        newFramePane.add(specialityMsg);
	        newFramePane.add(speciality);	
	        newFramePane.add(find);
	        newFramePane.add(cancel);
	        newFramePane.setBackground(Color.WHITE);
	        newFramePane.setBackground(Color.WHITE);
	        newFrame.add(newFramePane);
	        newFrame.pack();
	        newFrame.setVisible(true);
	        
	    }
	}
	
	//Action when new frame for finding contractor is closed.
	private class NewFrameCloseListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	frame.setEnabled(true);
	    	newFrame.setVisible(false);
	    	newFramePane.removeAll();
	    	newFrame.dispose();
	    }
	}
}
