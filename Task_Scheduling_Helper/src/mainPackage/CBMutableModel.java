package mainPackage;

import java.util.Vector;

import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

public class CBMutableModel implements MutableComboBoxModel<Object>
{
	Vector<Object> objects = new Vector<Object>();
	Object item;

	@Override
	public Object getSelectedItem()
	{
		// TODO Auto-generated method stub
		return item;
	}

	@Override
	public void setSelectedItem(Object anItem)
	{
		// TODO Auto-generated method stub
		item = anItem;
	}

	@Override
	public void addListDataListener(ListDataListener l)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize()
	{
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeElement(Object obj)
	{
		// TODO Auto-generated method stub
		objects.removeElement(obj);
	}

	@Override
	public void removeElementAt(int index)
	{
		// TODO Auto-generated method stub
		objects.removeElementAt(index);
	}

	@Override
	public void addElement(Object item)
	{
		// TODO Auto-generated method stub
		objects.addElement(item);
	}

	@Override
	public void insertElementAt(Object item, int index)
	{
		// TODO Auto-generated method stub
		objects.insertElementAt(item, index);
	}

	@Override
	public Object getElementAt(int index)
	{
		// TODO Auto-generated method stub
		return objects.get(index);
	}

}
