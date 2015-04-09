package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
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
		
		contentPane.setBackground(Color.BLACK);	
		
		/*
		 * Everything related to the task information
		 * 
		 * A panel for the desired task properties: execution times, periods and (optionally) deadlines.
		 */
		JPanel taskPropertiesPanel = new JPanel(new GridBagLayout());
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
		
		
		/*
		 * TEXTAREAS
		 */
		
		final JTextArea txtName = new JTextArea(1,10);
		txtName.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtName);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtName, GBC);
		
		final JTextArea txtWCET = new JTextArea(1,5);
		txtWCET.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtWCET);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtWCET, GBC);
		
		final JTextArea txtPeriod = new JTextArea(1,5);
		txtPeriod.addFocusListener(new selectAllFocusAdapter());
		TransferFocus.patch(txtPeriod);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 2;
		GBC.weighty = 1;
		taskPropertiesPanel.add(txtPeriod, GBC);
		
		final JTextArea txtDeadline = new JTextArea(1,5);
		txtDeadline.addFocusListener(new selectAllFocusAdapter());
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
				if(txtName.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "The task needs a name!");
				else if(txtWCET.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "The task needs a WCET!");
				else if(txtPeriod.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "The task needs a period!");
				else
				{
					if(txtDeadline.getText().equals(""))
					{
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
							Main.getScheduler().addNewTask(new Task(txtName.getText(), Integer.parseInt(txtWCET.getText()), Integer.parseInt(txtPeriod.getText()), Integer.parseInt(txtPeriod.getText())));
					
							Main.getScheduler().updateContPoints();
							Main.getScheduler().updateLCM();
							txtName.setText("");
						}
					}
					else
					{
						Main.getScheduler().addNewTask(new Task(txtName.getText(), Integer.parseInt(txtWCET.getText()), Integer.parseInt(txtPeriod.getText()), Integer.parseInt(txtDeadline.getText())));
						
						Main.getScheduler().updateContPoints();
						Main.getScheduler().updateLCM();
						txtName.setText("");
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
