package mainPackage;

import guiPackage.GUI;

import java.util.ArrayList;

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
	private static ArrayList<Task> taskList = new ArrayList<Task>();
	
	static GUI gui = new GUI();

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
	

		
		
		taskList.add(new Task("Task 1",100, 400, 400));
		//taskList.add(new Task("Task 2", 200, 500, 500));
		taskList.add(new Task("Task 3", 150, 300, 300));
		taskList.add(new Task("Task 4", 200, 600, 600));
		
		scheduler = new Scheduler(taskList);

		for(Task task : taskList)
		{
			gui.addTaskToCB(task);			
		}
	}
	
	public static ArrayList<Task> getTaskList()
	{
		return taskList;
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


