package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	private ArrayList<Task> taskList;
	
	final Container contentPane = getContentPane();	
	
	private JTextArea txtExecTime, txtPeriod, txtDeadline;
	private JComboBox CBTasks;
	private ComboBoxRenderer CBRenderer;
	
	
	public GUI()
	{
		super("Task Scheduling Helper");
		contentPane.setBackground(Color.BLACK);	
		
		/*
		 * Everything related to the task information
		 */
		//A panel for the desired task properties: execution times, periods and (optionally) deadlines.
		JPanel taskOptionsPanel = new JPanel(new GridLayout(3,2));
		taskList = new ArrayList<Task>();
		taskList.add(new Task());
		CBTasks = new JComboBox(taskList.toArray());
		CBRenderer = new ComboBoxRenderer();
		CBRenderer.setPreferredSize(new Dimension(200,130));
		CBTasks.setRenderer(CBRenderer);
		CBTasks.setMaximumRowCount(3);
		taskOptionsPanel.add(CBTasks);
		
		txtExecTime = new JTextArea("Execution time: " + taskExecTime + "\n");
		
		taskOptionsPanel.add(txtExecTime);
		txtPeriod = new JTextArea("Period: " + taskPeriod + "\n");
		taskOptionsPanel.add(txtPeriod);
		txtDeadline = new JTextArea("Deadline: " + taskDeadline + "\n");
		taskOptionsPanel.add(txtDeadline);
		
		contentPane.add(taskOptionsPanel, BorderLayout.WEST);
		
		//A panel for displaying the results in text.
		JPanel resultPanel = new JPanel();
		contentPane.add(resultPanel, BorderLayout.CENTER);
		
		//A panel for, in the future, drawing a visual representation of the results.
		JPanel diagramPanel = new JPanel();
		contentPane.add(diagramPanel, BorderLayout.CENTER);
		
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
	
	

	
	

}
