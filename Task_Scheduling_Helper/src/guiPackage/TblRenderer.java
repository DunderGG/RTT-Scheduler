package guiPackage;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TblRenderer extends DefaultTableCellRenderer
{
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
	 { 
	     Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

	     int ctrlPt = (int)table.getValueAt(row, 0);
	     int nrOfTasks = table.getColumnCount()-2;
	     double sum = 0;
	     
	     for(int i = 1; i <= nrOfTasks; i++)
	     {
	    	 sum += (double)table.getValueAt(row, i);
	     }
	      
	     if(!table.isRowSelected(row))
	     {
		     if(column == table.getColumnCount()-1 && sum > ctrlPt)
		         c.setBackground(Color.RED); 
		     else
		    	 c.setBackground(table.getBackground());
	     }
	     return c; 
	 } 
}
