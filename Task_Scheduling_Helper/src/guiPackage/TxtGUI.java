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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author dunderklumpen
 *
 */
public class TxtGUI extends JFrame
{
	private static final long serialVersionUID = -5810332732081923974L;

	final Container contentPane = getContentPane();
	
	JTextArea txtLCM = new JTextArea(), txtCtrlPts = new JTextArea();
	
	public TxtGUI()
	{
		super("Numerical Results");
		contentPane.setBackground(Color.BLACK);
		
		/* 
		 * A panel for displaying the results in text.
		 */
		JPanel resultPanel = new JPanel(new GridBagLayout());
		GridBagConstraints GBC;
		
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
		txtCtrlPts.setEditable(false);
		txtCtrlPts.setLineWrap(true);
		txtCtrlPts.setWrapStyleWord(true);
		txtCtrlPts.setRows(5);
		GBC = new GridBagConstraints();
		GBC.gridx = 1;
		GBC.gridy = 1;
		GBC.weightx = 1;
		resultPanel.add(txtCtrlPts, GBC);
		
		contentPane.add(resultPanel, BorderLayout.CENTER);
		/***************************/
		/*** END OF TEXT RESULTS ***/
		/***************************/
		
		
		/*
		 * HANDLING THE WINDOW
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setVisible(true);
		setSize(800,600);
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
}
