package objectPackage;

import guiPackage.GUI;

import java.util.ArrayList;
import java.util.Collections;

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
	private ArrayList<Integer> controlPoints = new ArrayList<Integer>();
	
	public long getTasksetLCM()
	{
		return tasksetLCM;
	}
	public ArrayList<Integer> getControlPoints()
	{
		return controlPoints;
	}
	
	/**
	 * @param taskList
	 */
	public Scheduler(ArrayList<Task> taskList)
	{
		GUI gui = Main.getGui();
		
		tasksetLCM = calcLCM(taskList);
		gui.updateLCM(tasksetLCM);
		System.out.println("Taskset LCM: " + tasksetLCM);
		
		controlPoints = calcContPoints(taskList);
		gui.updateCtrlPts(controlPoints);
		
		calcPDA(taskList);
	}
	/**
	 * @param taskList
	 * @param string
	 */
	public Scheduler(ArrayList<Task> taskList, policies policy)
	{
		GUI gui = Main.getGui();
		
		tasksetLCM = calcLCM(taskList);
		gui.updateLCM(tasksetLCM);
		System.out.println("Taskset LCM: " + tasksetLCM);
		
		controlPoints = calcContPoints(taskList);
		gui.updateCtrlPts(controlPoints);
		
		calcPDA(taskList);
		
		this.policy = policy;
		
	}

	/**
	 * @param taskList
	 */
	private ArrayList<double[]> calcPDA(ArrayList<Task> taskList)
	{
		//Calculated iteratively with (floor{(L - D)/T} + 1) * C for each control point.
		ArrayList<double[]> result = new ArrayList<double[]>();
		double[] calcs = null;
		
		for(Task task : taskList)
		{
			int i = 0;
			for(int cp : controlPoints)
			{
				calcs = new double[controlPoints.size()];
				calcs[i++] = ((Math.floor((cp - task.getDeadline()) / task.getPeriod()) + 1) * task.getExecTime());
			}
			result.add(calcs);			
		}
		return result;
	}
	
	
	/**
	 * Function used for calculating all the checkpoints L, for a given task set,
	 * needed for a Processor Demand Analysis.
	 * 
	 * L = kT + D, where k = 0,1,2,3....
	 * 
	 * @param taskList
	 * @return
	 */
	private ArrayList<Integer> calcContPoints(ArrayList<Task> taskList)
	{
		//The resulting list of control points, L.
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		int L = 0;
		
		for(Task task : taskList)
		{
			for(int i = 0; L+task.getPeriod() <= tasksetLCM; i++)
			{
				L = (i*task.getPeriod()) + task.getDeadline();
				
				//We don't want any duplicates.
				if(!result.contains(L))
					result.add(L);
			}
		}
		
		//Make sure the control points are ordered before returning it.
		Collections.sort(result);

		return result;
	}
	public policies getPolicy() {return policy;}
	public void setPolicy(policies policy) {this.policy = policy;}

	public long calcLCM(ArrayList<Task> tasks)
	{
		long[] periods = new long[tasks.size()];
		for(int i = 0; i< tasks.size(); i++)
		{
			periods[i] = tasks.get(i).getPeriod();
		}
		
		return calcLCM(periods);
	}
	
	/**
	 * @param a
	 * @param b
	 * @return
	 */
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
	
	/**
	 * @param input
	 * @return
	 */
	private static long calcGCD(long[] input)
	{
		long result = input[0];
		
		for(int i = 1; i<input.length; i++)
		{
			result = calcGCD(result, input[i]);
		}
		
		return result;
	}
	
	/**
	 * @param input
	 * @return
	 */
	private static long calcLCM(long[] input)
	{
		long result = input[0];
		
		for(int i = 1; i<input.length; i++)
		{
			result = calcLCM(result, input[i]);
		}
		
		return result;
	}

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	private static long calcLCM(long a, long b)
	{
		return a * (b / calcGCD(a, b));
	}
}
