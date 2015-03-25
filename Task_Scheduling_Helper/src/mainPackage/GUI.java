package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.MutableComboBoxModel;

/*
 * 	AUTHOR:	David Bennehag (David.Bennehag@Gmail.com)
 * 	
 * 
 * 	TODO:
 * 		We should provide a GUI where the user can enter the desired properties of the tasks, and somewhere to display the results.
 * 			Future work would be to actually draw the schedule to give a more clear view of the results.
 * 
 */

public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private int taskExecTime = 0, taskPeriod = 0, taskDeadline = 0;
	
	final Container contentPane = getContentPane();	
	
	private JTextArea txtExecTime, txtPeriod, txtDeadline;
	private JComboBox<Object> CBTasks;
	//private ComboBoxRenderer CBRenderer;
	
	private JMenuBar menuBar;
	private JMenu menu, subMenu;
	private JMenuItem menuItem;
	
	public GUI()
	{
		super("Task Scheduling Helper");
		contentPane.setBackground(Color.BLACK);	
		
		/*
		 * A menu for options and stuff
		 */
		menuBar = new JMenuBar();
		
		//Build the first menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		subMenu = new JMenu("New");
		menu.add(subMenu);
		menuItem = new JMenuItem("Task");
		subMenu.add(menuItem);
		
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(menu);
		menuItem = new JMenuItem("Scheduler");
		menu.add(menuItem);
		

		this.setJMenuBar(menuBar);
		/***************************/
		/*** END OF OPTIONS MENU ***/
		/***************************/
		
		/*
		 * Everything related to the task information
		 * 
		 * A panel for the desired task properties: execution times, periods and (optionally) deadlines.
		 */
		JPanel taskOptionsPanel = new JPanel(new GridBagLayout());
		
		txtExecTime = new JTextArea();
		txtPeriod = new JTextArea();
		txtDeadline = new JTextArea();
		
		GridBagConstraints GBC;

		CBTasks = new JComboBox<Object>(new CBMutableModel());
		CBTasks.setPreferredSize(new Dimension(100,20));
		CBTasks.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox<Object> cb = (JComboBox<Object>)e.getSource();
				Task task = (Task) cb.getSelectedItem();
				
				txtExecTime.setText("WCET: " + task.getExecTime());
				txtPeriod.setText("Period: " + task.getPeriod());
				txtDeadline.setText("Deadline: " + task.getDeadline());
			}
		});
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 0.5;
		taskOptionsPanel.add(CBTasks, GBC);
		
		
		txtExecTime.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weightx = 1;
		taskOptionsPanel.add(txtExecTime, GBC);
		
		
		txtPeriod.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 2;
		GBC.gridy = 0;
		GBC.weightx = 1;
		taskOptionsPanel.add(txtPeriod, GBC);
		
		
		txtDeadline.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 3;
		GBC.gridy = 0;
		GBC.weightx = 1;
		taskOptionsPanel.add(txtDeadline, GBC);	
		
		contentPane.add(taskOptionsPanel, BorderLayout.PAGE_END);
		/*******************************/
		/*** END OF TASK INFORMATION ***/
		/*******************************/
		/* 
		 * A panel for displaying the results in text.
		 */
		JPanel resultPanel = new JPanel();
		contentPane.add(resultPanel, BorderLayout.CENTER);
		/***************************/
		/*** END OF TEXT RESULTS ***/
		/***************************/
		
		/*
		 * A panel for, in the future, drawing a visual representation of the results.
		 */
		JPanel diagramPanel = new JPanel();
		contentPane.add(diagramPanel, BorderLayout.CENTER);
		/*** END OF GRAPHIC RESULTS ***/
		
		/*
		 * HANDLING THE WINDOW
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setVisible(true);
		setSize(800,600);
		
		/*
		 * Debugging information
		 */
		System.out.print("Component:\tcontentPane\ttaskOptionsPanel\tresultPanel\t\tdiagramPanel\n");
		System.out.print("Width: \t\t" + contentPane.getSize().width + "\t\t" + taskOptionsPanel.getSize().width + "\t\t\t" + resultPanel.getSize().width + "\t\t\t" + diagramPanel.getSize().width + "\n");
		System.out.print("Height: \t" + contentPane.getSize().height + "\t\t" + taskOptionsPanel.getSize().height + "\t\t\t" + resultPanel.getSize().height + "\t\t\t" + diagramPanel.getSize().height + "\n");
		
	}


	public void addTaskToCB(Task newTask)
	{
		CBTasks.addItem(newTask);
	}
	
	
	
	
	

}
