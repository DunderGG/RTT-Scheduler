package guiPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objectPackage.Task;
import mainPackage.Main;

public class LatexTableGUI extends JFrame
{
	private static final long serialVersionUID = -7498453520339548391L;
	final Container contentPane = getContentPane();
	
	String strBegin, strCenter, strBegin2, strTasks, strCaption, strEnd;
	JTextArea txtLatexTable;
	
	public LatexTableGUI(ArrayList<Task> taskList)
	{
		//Set the title
		super("Latex Table");
		//Set the position of the window to be of the same Y-position as, and just right of, the main window
		this.setLocation(Main.getGui().getX()+Main.getGui().getWidth(), Main.getGui().getY());
		contentPane.setBackground(Color.BLACK);	
		
		
		txtLatexTable = new JTextArea();
		contentPane.add(txtLatexTable, BorderLayout.CENTER);
		

		
		strBegin = "\\begin{table}[h!]\n";
		strCenter= "\t\\centering\n";
		
		strBegin2= "\t\\begin{tabular}{| c | c | c | c |}\n"
				+ "\t\\hline\n"
				+ "\t & $C_i$ & $D_i$ & $P_i$ \\\\\n";
		
		strTasks = "";
		for(Task task : taskList)
		{
			strTasks += "\t\\hline\n"
					+ "\t$" + task.getName() + "$ & " + task.getExecTime() + "& " + task.getDeadline() + "  & " + task.getPeriod() + " \\\\\n";
		}
		strTasks += "\t\\hline";
		
		strCaption = "\t\\caption{xxxxxxxxxx}\n";
		strEnd = "\\end{table}";
		
		
		txtLatexTable.setText(strBegin + strBegin2 + strTasks + strCaption + strEnd);
		
		JCheckBox chbCenter = new JCheckBox("Centered?");
		chbCenter.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Object source = e.getItemSelectable();
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					txtLatexTable.setText(strBegin + strCenter + strBegin2 + strTasks + strCaption + strEnd);
				}
				
				if(e.getStateChange() == ItemEvent.DESELECTED)
				{
					txtLatexTable.setText(strBegin + strBegin2 + strTasks + strCaption + strEnd);
				}
			}
		});
		contentPane.add(chbCenter, BorderLayout.PAGE_START);
		
		
		/*
		 * HANDLING THE WINDOW
		 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		setResizable(true);
		setVisible(true);
		setSize((int)(tk.getScreenSize().getWidth()*0.30), (int)(tk.getScreenSize().getHeight()*0.35));
	}
}
