package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class newTaskGUI extends JFrame
{
	private static final long serialVersionUID = 559401973816977636L;
	final Container contentPane = getContentPane();
	
	public newTaskGUI()
	{
		super("Add a new task");
		contentPane.setBackground(Color.BLACK);	
		
		/*
		 * Everything related to the task information
		 * 
		 * A panel for the desired task properties: execution times, periods and (optionally) deadlines.
		 */
		JPanel taskPropertiesPanel = new JPanel(new GridBagLayout());
		GridBagConstraints GBC;
		
		JLabel lblName = new JLabel("Name");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblName, GBC);
		
		JLabel lblWCET = new JLabel("WCET");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblWCET, GBC);
		
		JLabel lblPeriod = new JLabel("Period");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 2;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblPeriod, GBC);
		
		JLabel lblDeadline = new JLabel("Deadline");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 3;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblDeadline, GBC);
		
		
		
		
		JTextArea txtName = new JTextArea();
		TransferFocus.patch(txtName);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(txtName, GBC);
		
		JTextArea txtWCET = new JTextArea();
		TransferFocus.patch(txtWCET);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(txtWCET, GBC);
		
		JTextArea txtPeriod = new JTextArea();
		TransferFocus.patch(txtPeriod);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 2;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(txtPeriod, GBC);
		
		JTextArea txtDeadline = new JTextArea();
		TransferFocus.patch(txtDeadline);
		txtDeadline.setRows(1);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 3;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(txtDeadline,GBC);
		
		JButton btnAdd = new JButton("Add task");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 4;
		GBC.weighty = 1;

		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(btnAdd, GBC);
		
		
		contentPane.add(taskPropertiesPanel);
		
		
		
		/*
		 * HANDLING THE WINDOW
		 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		setResizable(false);
		setVisible(true);
		setSize((int)(tk.getScreenSize().getWidth()*0.25), (int)(tk.getScreenSize().getHeight()*0.30));	
	}
	
	
	
	
}
