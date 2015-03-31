package objectPackage;

public class Task
{
	private static final String DEFAULTNAME = "task";
	private static final int DEFAULTWCET = 100, DEFAULTPERIOD = 100, DEFAULTDEADLINE = 100;
	
	private String name = DEFAULTNAME;
	private int execTime, period, deadline;
	
	public Task()
	{
		this.setName(DEFAULTNAME);
		this.setExecTime(DEFAULTWCET);
		this.setPeriod(DEFAULTPERIOD);
		this.setDeadline(DEFAULTDEADLINE);
	}
	public Task(String name)
	{
		this.setName(name);
		this.setExecTime(DEFAULTWCET);
		this.setPeriod(DEFAULTPERIOD);
		this.setDeadline(DEFAULTDEADLINE);
	}
	public Task(String name, int execTime)
	{
		this.setName(name);
		this.setExecTime(execTime);
		this.setPeriod(DEFAULTPERIOD);
		this.setDeadline(DEFAULTDEADLINE);
	}
	public Task(String name, int execTime, int period)
	{
		this.setName(name);
		this.setExecTime(execTime);
		this.setPeriod(period);
		this.setDeadline(DEFAULTDEADLINE);
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
