package guiPackage;

import java.util.Vector;

import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

import objectPackage.Task;

public class CBMutableModel implements MutableComboBoxModel<Task>
{
	Vector<Task> tasks = new Vector<Task>();
	Object item;

	@Override
	public Object getSelectedItem()
	{
		return item;
	}

	@Override
	public void setSelectedItem(Object anItem)
	{
		item = anItem;
	}

	@Override
	public void addListDataListener(ListDataListener l)
	{
	}

	@Override
	public int getSize()
	{
		return tasks.size();
	}

	public void removeElement(Task obj)
	{
		tasks.removeElement(obj);
	}

	@Override
	public void removeElementAt(int index)
	{
		tasks.removeElementAt(index);
	}

	@Override
	public void addElement(Task task)
	{
		tasks.add(task);
	}

	@Override
	public void insertElementAt(Task obj, int index)
	{
		tasks.insertElementAt(obj, index);
	}

	@Override
	public Task getElementAt(int index)
	{
		return tasks.get(index);
	}

	@Override
	public void removeListDataListener(ListDataListener l)
	{
	}

	@Override
	public void removeElement(Object obj)
	{
		tasks.remove(obj);
	}

}
