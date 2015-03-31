package guiPackage;

import java.awt.Component;
import java.awt.KeyboardFocusManager;

/**
 * Some components treat tabulator (TAB key) in their own way.
 * Sometimes the tabulator is supposed to simply transfer the focus
 * to the next focusable component.
 * <br/>
 * Here s how to use this class to override the "component's default"
 * behavior:
 * <pre>
 * JTextArea  area  = new JTextArea(..);
 * <b>TransferFocus.patch( area );</b>
 * </pre>
 * This should do the trick. 
 * This time the KeyStrokes are used.
 * More elegant solution than TabTransfersFocus().
 * 
 * @author kaimu
 * @since 2006-05-14
 * @version 1.0
 */

public class TransferFocus
{
	public static void patch(Component c)
	{
		c.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		c.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
	}
}
