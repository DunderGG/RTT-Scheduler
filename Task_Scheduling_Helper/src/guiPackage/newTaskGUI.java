package guiPackage;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		
		contentPane.setBackground(Color.LIGHT_GRAY);	
		
		/*
		 * Everything related to the task information
		 * 
		 * A panel for the desired task properties: execution times, periods and (optionally) deadlines.
		 */
		JPanel taskPropertiesPanel = new JPanel(new GridBagLayout());
		taskPropertiesPanel.setBackground(contentPane.getBackground());
		GridBagConstraints GBC;
		
		
		/*
		 * LABELS
		 */
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
		
		final int[] i = new int[1];
		i[0] = 1;
		/*
		 * TEXTAREAS
		 */
		final JTextArea txtName = new JTextArea(1,10);
		txtName.setText("T_" + i[0]++);
		txtName.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtName);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtName, GBC);
		
		//Used in the JFormattedTextField
		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setGroupingUsed(false);
		intFormat.setParseIntegerOnly(true);
		
		//Use JFormattedTextFields with an Integer NumberFormat, so that we only accept Integers as input
		final JFormattedTextField txtWCET = new JFormattedTextField(intFormat);
		txtWCET.setText("100");
		txtWCET.setColumns(5);
		txtWCET.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtWCET);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtWCET, GBC);
		
		final JFormattedTextField txtPeriod = new JFormattedTextField(intFormat);
		txtPeriod.setText("500");
		txtPeriod.setColumns(5);
		txtPeriod.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtPeriod);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 2;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtPeriod, GBC);
		
		final JFormattedTextField txtDeadline = new JFormattedTextField(intFormat);
		txtDeadline.setText("500");
		txtDeadline.setColumns(5);
		txtDeadline.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtDeadline);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 3;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtDeadline,GBC);
		
		JButton btnAdd = new JButton("Add task");
		btnAdd.setBackground(contentPane.getBackground());
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Retrieve the most recent list of tasks
				ArrayList<Task> taskList = Main.getScheduler().getTaskList();
				
				//Make sure the user doesn't try adding tasks without a name, WCET or period
				if(txtName.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "The task needs a name!");
				else if(txtWCET.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "The task needs a WCET!");
				else if(txtPeriod.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "The task needs a period!");
				else
				{
					/*
					 * Don't allow two tasks with the same name
					 */
					for(Task task : taskList)
					{
						if(task.getName().equals(txtName.getText()))
						{
							JOptionPane.showMessageDialog(contentPane, "Duplicate names not allowed");
							return;
						}
					}
					
					
					if(txtDeadline.getText().equals(""))
					{
						//Show a dialog when the user hasn't specified a deadline
						Object[] options = {"YES", "NO"}; 
						int ans = JOptionPane.showOptionDialog(
							contentPane, 
							"If you don't specify a deadline, it is automatically set to be equal to its period\n",
							"Use default deadline?",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[0]);
					
						if(ans == JOptionPane.YES_OPTION)
						{
							//Create a new Task with the specified values
							Task newTask = new Task(txtName.getText(), (int)txtWCET.getValue(), (int)txtPeriod.getValue(), (int)txtPeriod.getValue());
							
							//Add the new task to the scheduler. Will cause it to update the control points, the LCM and add it to the list.
							Main.getScheduler().addNewTask(newTask);

							//Add the new task to the JComboBox inside the main GUI
							Main.getGui().addTaskToCB(newTask);
							
							//Clear the name field and give it the focus
							txtName.setText("T_" + i[0]++);
							txtName.requestFocusInWindow();
						}
					}
					else
					{
						//Create a new Task with the specified values
						Task newTask = new Task(txtName.getText(), Integer.parseInt(txtWCET.getText()), Integer.parseInt(txtPeriod.getText()), Integer.parseInt(txtDeadline.getText()));
						
						//Add the new task to the scheduler. Will cause it to update the control points, the LCM and add it to the list.
						Main.getScheduler().addNewTask(newTask);

						//Add the new task to the JComboBox inside the main GUI
						Main.getGui().addTaskToCB(newTask);
						
						//Clear the name field and give it the focus
						txtName.setText("T_" + i[0]++);
						txtName.requestFocusInWindow();
					}
				}
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
