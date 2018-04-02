package GUI;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import ADT.Contractor;
import Algorithms.Search;
import Algorithms.Sort;
import Read.DataReader;
import Read.DataReaderForInterface;
import Read.Reviews;

public class MainMenu {
	
//	ButtonListeners.ConfirmPopUpWindowClose confirmPopUpWindowClose = buttonListeners.new ConfirmPopUpWindowClose();
//    test.addActionListener(confirmPopUpWindowClose);
	ButtonListeners buttonListeners = new ButtonListeners();
	private static final int DO_NOTHING_ON_CLOSE = 0;
	static JFrame frame = new JFrame(); 						//Main menu frame.
	private JPanel pane = new JPanel(); 						//Main menu pane.
	private DefaultTableModel model = new DefaultTableModel();	//Main menu table. 
	static JFrame newFrame = new JFrame(); 					//Pop up frame for finding ideal contractor.
	static JPanel newFramePane = new JPanel();
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	static {
	    try {
	    	map = Reviews.initMapFromFile("Reviews");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } // end try-catch
	  }
	
	//Launch the application.
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public MainMenu() throws IOException {
		initialize();
	}
	
	//Initialize the contents of the main frame.
	private void initialize() throws IOException {
		
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		newFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JButton distance = new JButton("Distance");			//Setup buttons and their listeners
        JButton find = new JButton("Find Contractor");
        JButton end = new JButton("Exit Program");
        JButton test = new JButton("test");//GIOEJGOSEHGUOWHUF
        end.addActionListener(new CloseListener());
        find.addActionListener(new FindListener());
        distance.setEnabled(false);
        pane.add(distance);									//Adding to content pane
		pane.add(find);
		pane.add(end);
		pane.add(test);
        
		JTable contractorTable = new JTable(model); 
		
		model.addColumn("Contractor Name"); 
		model.addColumn("License Number"); 
		model.addColumn("State");
		model.addColumn("City");
		model.addColumn("Address"); 
		model.addColumn("Phone Number"); 
		model.addColumn("Speciality"); 
		model.addColumn("Rating");
	
		contractorTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		contractorTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		contractorTable.getColumnModel().getColumn(2).setPreferredWidth(0);
		contractorTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		contractorTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		contractorTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		contractorTable.getColumnModel().getColumn(6).setPreferredWidth(200);
		contractorTable.getColumnModel().getColumn(7).setPreferredWidth(0);
		
		JScrollPane scrollPane = new JScrollPane(contractorTable);
		scrollPane.setPreferredSize(new Dimension(1300, 650));
		pane.add(scrollPane);
		
		frame.add(pane);
		frame.setVisible(true);
		//Using the default close on the main frame
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure to close this window?", "Closing Confirmation", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	try {
						Reviews.writeMapToFile(map, "Reviews.txt");
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						e.printStackTrace();
					}
		            System.exit(0);
		        }
		    }
		});
	}
	
	
	//Close Button on the main frame
	private class CloseListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (JOptionPane.showConfirmDialog(frame, 
	            "Are you sure to close this window?", "Closing Confirmation", 
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	        	try {
					Reviews.writeMapToFile(map, "Reviews.txt");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
	            System.exit(0);
	        }
	    }
	}

	JComboBox cities = new JComboBox(DataReaderForInterface.readCities("AK"));
	
	//Find Button which loads the new frame for finding a contractor.
	private class FindListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	
	    	frame.setEnabled(false);
	    	
	    	JComboBox states = new JComboBox(DataReaderForInterface.readStates());
	    	
	    	states.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	newFramePane.remove(newFramePane.getComponentCount()-5);
	            	cities = new JComboBox(DataReaderForInterface.readCities(states.getSelectedItem().toString()));
	            	newFramePane.add(cities, newFramePane.getComponentCount()-4);
	            	newFrame.add(newFramePane);
	            	newFrame.setVisible(true);
	            }          
	         });
	    	
	    	JComboBox speciality = new JComboBox(DataReaderForInterface.readSpecialities());
	    	
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
	            public void actionPerformed(ActionEvent e) { 
	            	cancel.doClick();
	            	Contractor[] allCons = DataReader.readContractors();
	            	Sort.sort(allCons);
	            	Contractor ideal = new Contractor(cities.getSelectedItem().toString().trim(), states.getSelectedItem().toString().trim(), speciality.getSelectedItem().toString().trim());
	            	System.out.println(cities.getSelectedItem().toString().trim());
	            	System.out.println(states.getSelectedItem().toString().trim());
	            	System.out.println(speciality.getSelectedItem().toString().trim());

	            	try {
	            		Contractor[] results = Search.search(allCons, ideal, "Reviews");
	            		model.setRowCount(0);
						for (int i = 0; i < results.length; i++) {
		    	        	Contractor c = results[i];
		    	        	System.out.println(c.getContractorName());
		    	        	model.addRow(new Object[] {c.getContractorName(),c.getLicenseNumber(),c.getState(),
		    	        			c.getCity(),c.getAddress(),c.getNumber(), c.getSpecialty(),c.avgReview(map)});
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
	        newFramePane.setBackground(Color.LIGHT_GRAY);
	        newFrame.add(newFramePane);
	        newFrame.pack();
	        newFrame.setVisible(true);
	        
	      //Default close on pop up frame
			newFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        if (JOptionPane.showConfirmDialog(frame, 
			            "Are you sure to close this window?", "Closing Confirmation", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			        	try {
							Reviews.writeMapToFile(map, "Reviews.txt");
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							e.printStackTrace();
						}
			            cancel.doClick();
			        }
			    }
			});
	        
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

