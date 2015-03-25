package mainPackage;

import javax.swing.UIManager;

/*
 * 	AUTHOR:	David Bennehag (David.Bennehag@Gmail.com)
 * 	
 * 
 * 
 * 	TODO:
 * 		Create a number of tasks. Each task gets an execution time (C), a period (T) and (optionally) a deadline (D).
 * 			We then make a schedule with the selected scheduling algorithm (RMS, EDF...)
 * 
 */

public class Main
{

	public static void main(String[] args)
	{
		//Set the look and feel of the program to be the same as system default
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}

		new GUI();
	}

}
