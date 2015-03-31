package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import objectPackage.Task;
import mainPackage.Main;

public class newTaskGUI extends JFrame
{
	private static final long serialVersionUID = 559401973816977636L;
	final Container contentPane = getContentPane();
	
	public newTaskGUI()
	{
		super("Add a new task");
		//Set the position of the window to be of the same Y-position as, and just right of, the main window
		this.setLocation(Main.getGui().getX()+Main.getGui().getWidth(), Main.getGui().getY());
		
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
		GBC.fill = GridBagConstraints.BOTH;
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weighty = 1;
		taskPropertiesPanel.add(lblName, GBC);
		
		JLabel lblWCET = new JLabel("WCET");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.weighty = 1;
		taskPropertiesPanel.add(lblWCET, GBC);
		
		JLabel lblPeriod = new JLabel("Period");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 2;
		GBC.weighty = 1;
		taskPropertiesPanel.add(lblPeriod, GBC);
		
		JLabel lblDeadline = new JLabel("Deadline");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 3;
		GBC.weighty = 1;
		taskPropertiesPanel.add(lblDeadline, GBC);
		
		
		
		
		final JTextArea txtName = new JTextArea(1,10);
		TransferFocus.patch(txtName);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtName, GBC);
		
		final JTextArea txtWCET = new JTextArea(1,5);
		TransferFocus.patch(txtWCET);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtWCET, GBC);
		
		final JTextArea txtPeriod = new JTextArea(1,5);
		TransferFocus.patch(txtPeriod);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 2;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtPeriod, GBC);
		
		final JTextArea txtDeadline = new JTextArea(1,5);
		TransferFocus.patch(txtDeadline);
		txtDeadline.setRows(1);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 3;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtDeadline,GBC);
		
		JButton btnAdd = new JButton("Add task");
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.getScheduler().addNewTask(new Task(txtName.getText(), Integer.parseInt(txtWCET.getText()), Integer.parseInt(txtPeriod.getText()), Integer.parseInt(txtDeadline.getText())));
			}
		});
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 4;
		GBC.weighty = 1;
		GBC.gridwidth = 2;
		GBC.fill = GridBagConstraints.HORIZONTAL;
		taskPropertiesPanel.add(btnAdd, GBC);
		
		
		contentPane.add(taskPropertiesPanel);
		
		
		
		/*
		 * HANDLING THE WINDOW
		 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		setResizable(false);
		setVisible(true);
		setSize((int)(tk.getScreenSize().getWidth()*0.20), (int)(tk.getScreenSize().getHeight()*0.20));	
	}
	
	
	
	
}
