package mainPackage;

import guiPackage.GUI;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import objectPackage.Scheduler;
import objectPackage.Task;

/*
 * 	AUTHOR:	David Bennehag (David.Bennehag@Gmail.com)
 * 	
 * 
 * 
 * 	TODO:
 * 		Create a number of tasks. Each task gets an execution time (C), a period (T) and (optionally) a deadline (D).
 * 			We then make a schedule with the selected scheduling algorithm (RMS, EDF...)
 * 
 * 	FINISHED:
 * 
 */

public class Main
{	
	static GUI gui;
	static Scheduler scheduler;
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

		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				gui = new GUI();	
			}
		});
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				scheduler = new Scheduler();
			}
		});

	}
	
	public static GUI getGui()
	{
		return gui;
	}
	public static Scheduler getScheduler()
	{
		return scheduler;
	}

}


