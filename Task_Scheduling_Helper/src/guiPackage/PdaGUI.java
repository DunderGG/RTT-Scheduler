/*
 * 		AUTHOR:		David Bennehag (David.Bennehag@Gmail.com)
 * 		VERSION: 	1.0
 * 
 * 		COURSE: xxx (MPCSN, Chalmers University of Technology)
 *		
 *
 *		TODO  
 *
 * 
 * 		FINISHED
 * 
 */
package guiPackage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mainPackage.Main;
import objectPackage.Task;

/**
 * @author dunderklumpen
 *
 */
public class PdaGUI extends JFrame
{
	private static final long serialVersionUID = -7617134054414256156L;
	
	final Container contentPane = getContentPane();
	
	private ArrayList<Integer> controlPoints;
	private ArrayList<double[]> pdaResults;
	
	public PdaGUI(ArrayList<Task> taskList)
	{
		//Set the title
		super("PDA Results");
		//Set the position of the window to be of the same Y-position as, and just right of, the main window
		this.setLocation(Main.getGui().getX()+Main.getGui().getWidth(), Main.getGui().getY());
		
		contentPane.setBackground(Color.BLACK);	
		
		controlPoints = Main.getScheduler().getControlPoints();
		
		pdaResults = Main.getScheduler().calcPDA(taskList);
		
		
		//Each task needs a column, the control points need one and the result needs one
		int nrOfCols = taskList.size() + 2;
		String[] columnNames = new String[nrOfCols];
		columnNames[0] = "L";
		columnNames[nrOfCols-1] = "Result";
		int i = 1;
		for(Task task : taskList)
			columnNames[i++] = task.toString();
		
		Object[][] data = new Object[controlPoints.size()][];
		
		int rowIndex = 0;
		for(int cp : controlPoints)
		{
			Object[] row = new Object[nrOfCols];
			row[0] = cp;
			
			
			int j = 1;
			for(double[] result : pdaResults)
			{
				row[j] = result[rowIndex];
				j++;
			}
	
			double sum = 0;
			for(int x = 1; x < nrOfCols-1; x++)
			{
				sum += (double)row[x];
			}
			row[nrOfCols-1] = sum;
			
			System.out.println("row[" + 0 + "] = " + row[0]);
			for(int x = 1; x < nrOfCols-1; x++)
			{
				System.out.println("row[" + x + "] = " + row[x]);
			}
			System.out.println("row[" + (nrOfCols-1) + "] = " + row[nrOfCols-1]);
			
			data[rowIndex++] = row;
		}
		
		
		JTable table = new JTable(data, columnNames);
		TblRenderer tblRenderer = new TblRenderer();
		table.setDefaultRenderer(Object.class, tblRenderer);
		
		JScrollPane sp = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		contentPane.add(sp);
		
		
		
		/*
		 * HANDLING THE WINDOW
		 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		setResizable(true);
		setVisible(true);
		setSize((int)(tk.getScreenSize().getWidth()*0.35), (int)(tk.getScreenSize().getHeight()*0.65));
	}
	

}
