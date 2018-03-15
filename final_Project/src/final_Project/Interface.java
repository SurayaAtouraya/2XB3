package final_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Interface {

	private JFrame frame;

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public Interface() {
		initialize();
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultTableModel model = new DefaultTableModel(); 
		JTable contractorTable = new JTable(model); 
		model.addColumn("Contractor Name"); 
		model.addColumn("Rating"); 
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		model.addRow(new Object[]{"Bill", "4.2"});
		
		JScrollPane scrollPane = new JScrollPane(contractorTable);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setVisible(true);
	}

}
