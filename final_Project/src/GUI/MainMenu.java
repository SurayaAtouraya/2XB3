package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Read.DataReaderForInterface;
import Read.Reviews;

import GUI.ButtonListeners;

public class MainMenu {
	
	public JTable contractorTable = new JTable(model); 
	static JComboBox<String> cities = new JComboBox<>(DataReaderForInterface.readCities("AK"));
	static ButtonListeners buttonListeners = new ButtonListeners();
	static MouseListeners mouseListeners = new MouseListeners();
	static ButtonListeners.CloseWindow CloseWindow = buttonListeners.new CloseWindow();
	static ButtonListeners.CloseFrame CloseFrame = buttonListeners.new CloseFrame();
	static ButtonListeners.FindButton FindButton = buttonListeners.new FindButton();
	static MouseListeners.MouseJTable mousePressed= mouseListeners.new MouseJTable();
	private static final int DO_NOTHING_ON_CLOSE = 0;
	static JFrame frame = new JFrame(); 						//Main menu frame.
	private JPanel pane = new JPanel(); 						//Main menu pane.
	static DefaultTableModel model = new DefaultTableModel();	//Main menu table. 
	static JFrame newFrame = new JFrame(); 						//Pop up frame for finding ideal contractor.
	static JPanel newFramePane = new JPanel();
	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	static { try { map = Reviews.initMapFromFile("Reviews");} 
	catch (Exception e) { e.printStackTrace(); }}
	
	//Launch the application.
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
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
	
		frame.addWindowListener(CloseWindow);
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		newFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
        JButton find = new JButton("Find Contractor");
        JButton end = new JButton("Exit Program");
        end.addActionListener(CloseFrame);
        find.addActionListener(FindButton);
		pane.add(find);
		pane.add(end);
        
		model.addColumn("Contractor Name"); 
		model.addColumn("License Number"); 
		model.addColumn("State");
		model.addColumn("City");
		model.addColumn("Address"); 
		model.addColumn("Phone Number"); 
		model.addColumn("Speciality"); 
		model.addColumn("Rating");
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i <= 7; i++) {contractorTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );}
		contractorTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		contractorTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		contractorTable.getColumnModel().getColumn(2).setPreferredWidth(0);
		contractorTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		contractorTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		contractorTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		contractorTable.getColumnModel().getColumn(6).setPreferredWidth(200);
		contractorTable.getColumnModel().getColumn(7).setPreferredWidth(0);
		contractorTable.getTableHeader().setReorderingAllowed(false);
		contractorTable.getTableHeader().setResizingAllowed(false);
		contractorTable.isCellEditable(0, 0);
		JScrollPane scrollPane = new JScrollPane(contractorTable);
		scrollPane.setPreferredSize(new Dimension(1300, 650));
		pane.add(scrollPane);
		
		frame.add(pane);
		frame.setVisible(true);
		
	}
}

