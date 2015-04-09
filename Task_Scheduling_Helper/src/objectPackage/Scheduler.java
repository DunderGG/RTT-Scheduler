package objectPackage;

import guiPackage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import mainPackage.*;

/*
 * 	AUTHOR:	David Bennehag (David.Bennehag@Gmail.com)
 * 	
 * 
 * 
 * 	TODO:
 * 			A class that can be used for taking scheduling decisions, based on a specified policy.
 * 
 * 
 * 	FINISHED:
 * 			Can successfully find the LCM of the tasksets periods, as well as all the control points needed for PDA.
 */

public class Scheduler
{
	public static enum policies { EDF, RM, DM }
	
	private policies policy;
	private long tasksetLCM;
	private TreeSet<Integer> controlPoints = new TreeSet<Integer>();
	private ArrayList<Task> taskList = new ArrayList<Task>();

	public Scheduler()
	{	
		GUI gui = Main.getGui();
	}
	public Scheduler(ArrayList<Task> taskList)
	{
		this.taskList = taskList;
		GUI gui = Main.getGui();
		
		tasksetLCM = calcLCM(taskList);
		gui.updateLCM(tasksetLCM);
		System.out.println("Taskset LCM: " + tasksetLCM);
		
		controlPoints = calcContPoints(taskList);
		gui.updateCtrlPts(controlPoints);
	}
	public Scheduler(ArrayList<Task> taskList, policies policy)
	{
		this.taskList = taskList;
		this.policy = policy;
		
		GUI gui = Main.getGui();
		
		tasksetLCM = calcLCM(taskList);
		gui.updateLCM(tasksetLCM);
		System.out.println("Taskset LCM: " + tasksetLCM);
		
		controlPoints = calcContPoints(taskList);
		gui.updateCtrlPts(controlPoints);

	}
	
	public double calcPDA(Task task, int cp)
	{
		//Calculated iteratively with (floor{(L - D)/T} + 1) * C for each control point.
		double result = 0;
		int temp = cp - task.getDeadline();
		if(temp < 0)
			result = 0;
		else
			result = ((Math.floor((cp - task.getDeadline()) / task.getPeriod()) + 1) * task.getExecTime());
		return result;
	}
	
	/**
	 * Function used for calculating all the checkpoints L, for a given task set,
	 * needed for a Processor Demand Analysis.
	 * 
	 * L = kT + D, where k = 0,1,2,3....
	 */
	private TreeSet<Integer> calcContPoints(ArrayList<Task> taskList)
	{
		//Use TreeSet instead of ArrayList when we don't allow duplicates, and want ordering guaranteed
		TreeSet<Integer> resultTree = new TreeSet<Integer>();
		
		int L = 0;
		for(Task task : taskList)
		{
			for(int k = 0; L+task.getPeriod() <= tasksetLCM; k++)
			{
				L = (k*task.getPeriod()) + task.getDeadline();
				
				resultTree.add(L);
			}
			L = 0;
		}
		return resultTree;
	}
	/*
	 * Safe to call from public
	 */
	public void updateContPoints()
	{
		controlPoints = calcContPoints(taskList);
		Main.getGui().updateCtrlPts(controlPoints);
	}
	/*
	 * Safe to call from public
	 */
	public void updateLCM()
	{
		tasksetLCM = calcLCM(taskList);
		Main.getGui().updateLCM(tasksetLCM);
	}

	public long calcLCM(ArrayList<Task> tasks)
	{
		long[] periods = new long[tasks.size()];
		for(int i = 0; i< tasks.size(); i++)
		{
			periods[i] = tasks.get(i).getPeriod();
		}
		return calcLCM(periods);
	}
	
	private static long calcGCD(long a, long b)
	{
		while(b > 0)
		{
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	private static long calcLCM(long[] input)
	{
		long result = input[0];
		for(int i = 1; i<input.length; i++)
		{
			result = calcLCM(result, input[i]);
		}
		return result;
	}
	
	public void addNewTask(Task task)
	{
		taskList.add(task);
		
		tasksetLCM = calcLCM(taskList);
		controlPoints = calcContPoints(taskList);
		
		Main.getGui().addTaskToCB(task);
	}
	
	public policies getPolicy() {return policy;}
	public void setPolicy(policies policy) {this.policy = policy;}
	public ArrayList<Task> getTaskList() { return taskList; }
	public long getTasksetLCM()	{ return tasksetLCM; }
	public TreeSet<Integer> getControlPoints() { return controlPoints;	}
	private static long calcLCM(long a, long b)	{ return a * (b / calcGCD(a, b)); }
}
