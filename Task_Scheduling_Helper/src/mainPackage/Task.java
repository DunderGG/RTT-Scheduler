package mainPackage;

public class Task
{
	private String name = "task";
	private int execTime, period, deadline;
	
	public Task()
	{
		this.setName("default");
		this.setExecTime(10);
		this.setPeriod(50);
		this.setDeadline(20);
	}
	public Task(String name)
	{
		this.setName(name);
		this.setExecTime(10);
		this.setPeriod(50);
		this.setDeadline(20);
	}
	public Task(String name, int execTime)
	{
		this.setName(name);
		this.setExecTime(execTime);
		this.setPeriod(50);
		this.setDeadline(20);
	}
	public Task(String name, int execTime, int period)
	{
		this.setName(name);
		this.setExecTime(execTime);
		this.setPeriod(period);
		this.setDeadline(20);
	}
	public Task(String name, int execTime, int period, int deadline)
	{
		this.setName(name);
		this.setExecTime(execTime);
		this.setPeriod(period);
		this.setDeadline(deadline);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
 	public int getExecTime()
	{
		return execTime;
	}
	public void setExecTime(int execTime)
	{
		this.execTime = execTime;
	}

	public int getPeriod()
	{
		return period;
	}
	public void setPeriod(int period)
	{
		this.period = period;
	}

	public int getDeadline()
	{
		return deadline;
	}
	public void setDeadline(int deadline)
	{
		this.deadline = deadline;
	}

	public String toString()
	{
		return name;
	}
}
