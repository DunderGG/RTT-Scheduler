package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.MutableComboBoxModel;

import objectPackage.Task;
import mainPackage.Main;

/*
 * 	AUTHOR:	David Bennehag (David.Bennehag@Gmail.com)
 * 	
 * 
 * 	TODO:
 * 			We should provide a GUI where the user can enter the desired properties of the tasks, and somewhere to display the results.
 * 				Future work would be to actually draw the schedule to give a more clear view of the results.
 * 
 * 	FINISHED:
 * 			Menu has started taking form, as well as textual results and a drowdown-list for choosing a task
 */

public class GUI extends JFrame
{
	private static final long serialVersionUID = 6301935178809241968L;

	final Container contentPane = getContentPane();
	
	private JTextArea txtExecTime = new JTextArea(), txtPeriod = new JTextArea(), txtDeadline = new JTextArea(), txtLCM = new JTextArea(), txtCtrlPts = new JTextArea();
	private JComboBox<Task> CBTasks;
	//private ComboBoxRenderer CBRenderer;
	
	private JButton btnPDA, btnTextResults, btnDiagResults;
	
	private JMenuBar menuBar;
	private JMenu menu, subMenu;
	private JMenuItem menuItem;
	
	public GUI()
	{
		super("RTT Scheduler");
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
		menuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				new newTaskGUI();
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
		JPanel taskPropertiesPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints GBC;

		btnPDA = new JButton("PDA");
		btnPDA.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				new PdaGUI(Main.getTaskList());
			}
		});
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.fill = GridBagConstraints.BOTH;
		taskPropertiesPanel.add(btnPDA, GBC);
		
		btnTextResults = new JButton("Tex");
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.fill = GridBagConstraints.BOTH;
		taskPropertiesPanel.add(btnTextResults, GBC);
		
		btnDiagResults = new JButton("Dia");
		GBC = new GridBagConstraints();
		GBC.gridx = 2;
		GBC.gridy = 0;
		GBC.weightx = 1;
		GBC.fill = GridBagConstraints.BOTH;
		taskPropertiesPanel.add(btnDiagResults, GBC);
		
		JLabel lblTask = new JLabel("Select a task");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblTask, GBC);
		
		CBTasks = new JComboBox<Task>();
		CBTasks.setPreferredSize(new Dimension(100,20));
		CBTasks.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox<Object> cb = (JComboBox<Object>)e.getSource();
				Task task = (Task) cb.getSelectedItem();
				
				txtExecTime.setText(Integer.toString(task.getExecTime()));
				txtPeriod.setText(Integer.toString(task.getPeriod()));
				txtDeadline.setText(Integer.toString(task.getDeadline()));
			}
		});
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 2;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.NORTH;
		taskPropertiesPanel.add(CBTasks, GBC);
		
		JLabel lblWcet = new JLabel("WCET");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 3;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblWcet, GBC);
		txtExecTime.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 4;
		GBC.anchor = GridBagConstraints.NORTH;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtExecTime, GBC);
		
		JLabel lblPeriod = new JLabel("Period");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 5;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblPeriod, GBC);
		txtPeriod.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 6;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.NORTH;
		taskPropertiesPanel.add(txtPeriod, GBC);
		
		JLabel lblDeadline = new JLabel("Deadline");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 7;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.SOUTH;
		taskPropertiesPanel.add(lblDeadline, GBC);
		txtDeadline.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 8;
		GBC.weighty = 1;
		GBC.anchor = GridBagConstraints.NORTH;
		taskPropertiesPanel.add(txtDeadline, GBC);	
		
		contentPane.add(taskPropertiesPanel, BorderLayout.WEST);
		/*******************************/
		/*** END OF TASK INFORMATION ***/
		/*******************************/
		
		/* 
		 * A panel for displaying the results in text.
		 */
		JPanel resultPanel = new JPanel(new GridBagLayout());
		
		JLabel lblLCM = new JLabel("LCM");
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 0;
		GBC.weightx = 1;
		resultPanel.add(lblLCM, GBC);
		txtLCM.setEditable(false);
		GBC = new GridBagConstraints();
		GBC.gridx = 0;
		GBC.gridy = 1;
		GBC.weightx = 1;
		resultPanel.add(txtLCM, GBC);
		
		JLabel lblCtrlPts = new JLabel("Control Points");
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weightx = 1;
		resultPanel.add(lblCtrlPts, GBC);
		JScrollPane scroll = new JScrollPane(txtCtrlPts); 
		txtCtrlPts.setEditable(false);
		txtCtrlPts.setLineWrap(true);
		txtCtrlPts.setWrapStyleWord(true);
		txtCtrlPts.setRows(5);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.weightx = 1;
		resultPanel.add(scroll, GBC);
		
		contentPane.add(resultPanel, BorderLayout.CENTER);
		/***************************/
		/*** END OF TEXT RESULTS ***/
		/***************************/
		
		
		/*
		 * A panel for, in the future, drawing a visual representation of the results.
		 */
		JPanel diagramPanel = new JPanel();
		//contentPane.add(diagramPanel, BorderLayout.CENTER);
		/*** END OF GRAPHIC RESULTS ***/
		
		
		
		
		/*
		 * HANDLING THE WINDOW
		 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setSize((int)(tk.getScreenSize().getWidth()*0.25), (int)(tk.getScreenSize().getHeight()*0.65));
		
		/*
		 * Debugging information
		 */
		System.out.print("Component:\tcontentPane\ttaskPropertiesPanel\t\tdiagramPanel\n");
		System.out.print("Width: \t\t" + contentPane.getSize().width + "\t\t" + taskPropertiesPanel.getSize().width + "\t\t\t" + diagramPanel.getSize().width + "\n");
		System.out.print("Height: \t" + contentPane.getSize().height + "\t\t" + taskPropertiesPanel.getSize().height + "\t\t\t" + diagramPanel.getSize().height + "\n");
		
	}
	
	public void updateLCM(Long LCM)
	{
		txtLCM.setText(Long.toString(LCM));
	}
	
	public void updateCtrlPts(ArrayList<Integer> CtrlPts)
	{
		String text = "";
		
		int i = 0;
		for(int pt : CtrlPts)
		{
			text = text + pt;
			
			if(i+++1 < CtrlPts.size())
			{
				text = text + ", ";
			}
		}
		
		txtCtrlPts.setText(text);
	}

	public void addTaskToCB(Task newTask)
	{
		CBTasks.addItem(newTask);
	}	

}
