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
package objectPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * @author dunderklumpen
 *
 */
public class TableExporter
{
	public TableExporter()
	{
		
	}
	
	public static void exportTable(JTable table, File file) throws IOException
	{
		TableModel tm = table.getModel();
		FileWriter fw = new FileWriter(file);
		
		for(int i = 0; i < tm.getColumnCount(); i++)
		{
			fw.write(tm.getColumnName(i) + "\t");
		}
		fw.write("\n");
		
		for(int i = 0; i < tm.getRowCount(); i++)
		{
			for(int j = 0; j < tm.getColumnCount(); j++)
			{
				fw.write(tm.getValueAt(i, j).toString() + "\t");
			}
			fw.write("\n");
		}
		
		fw.close();
		
		System.out.println("Writing table to file: " + file);
	}
}
