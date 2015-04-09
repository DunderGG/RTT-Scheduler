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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 * @author dunderklumpen
 *
 *	ONLY TO BE USED ON TEXTUAL COMPONENTS
 *
 */
public class selectAllFocusAdapter extends FocusAdapter
{
	public void focusGained(FocusEvent e)
	{
		final JTextComponent comp = (JTextComponent) e.getSource();
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				comp.selectAll();
			}
		});
		
	}

}
