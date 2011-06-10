/**
 * 
 */
package com.turawet.beedroid.view.support;

import com.turawet.beedroid.beans.InstanceBean;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class BeanToViewGenerator
{
	/**
	 *
	 */
	private String[]				items		=
													{ "0", "1", "2", "3", "4", "5" };
	
	private static final int	CHILDS	= 6;
	private LinearLayout			list[];
	private int						index;
	private boolean				increased;
	private boolean				decreased;
	
	InstanceBean					instance;
	private Context				context;
	
	private int						sectionIndex;
	private int						fieldIndex;
	
	public BeanToViewGenerator(Context context, InstanceBean instance)
	{
		this.instance = instance;
		this.context = context;
		index = 0;
		sectionIndex = 0;
		fieldIndex = 0;
		increased = false;
		decreased = false;
		list = new LinearLayout[CHILDS];
		
		for (int i = 0; i < CHILDS; i++)
			list[i] = getLayout();
		
		for (int i = 0; i < CHILDS; i++)
		{
			list[i].addView(getView(i));
		}
		
	}
	
	/**
	 * @return
	 */
	public final boolean updateBeforeDecrease()
	{
		boolean out = false;
		if (!decreased)
		{
			index--;
			out = true;
		}
		else
			increased = true;
		return out;
	}
	
	/**
	 * @return
	 */
	public final boolean updateBeforeIncrease()
	{
		boolean out = false;
		if (!increased)
		{
			index++;
			out = true;
		}
		else
			decreased = true;
		return out;
	}
	
	/**
	 * @return
	 */
	public final boolean canIncreaseIndex()
	{
		return index < CHILDS - 1;
	}
	
	/**
	 * @return
	 */
	public final boolean canDecreaseIndex()
	{
		return index > 0;
	}
	
	/**
	 * @return
	 */
	private final int getNextIndex()
	{
		if (index <= CHILDS - 1)
			return index + 1;
		return index;
	}
	
	/**
	 * @return
	 */
	private final int getPreviousIndex()
	{
		if (index >= 0)
			return index - 1;
		return index;
	}
	
	/**
	 * 
	 */
	public final void incIndex()
	{
		if (index < CHILDS - 1)
			index++;
	}
	
	/**
	 * 
	 */
	public final void decIndex()
	{
		if (index > 0)
			index--;
	}
	
	/**
	 * @param ind
	 * @return
	 */
	private final boolean validIndex(int ind)
	{
		return ind >= 0 && ind < CHILDS;
	}
	
	/**
	 * @return
	 */
	public final View getNextView()
	{
		final int ind = getNextIndex();
		if (validIndex(ind))
			return list[ind];
		else
			return null;
	}
	
	/**
	 * @return
	 */
	public final View getPrevView()
	{
		final int ind = getPreviousIndex();
		if (validIndex(ind))
			return list[ind];
		else
			return null;
	}
	
	/**
	 * @return
	 */
	public final View getCurrentView()
	{
		return list[index];
	}
	
	/**
	 * @return
	 */
	private final LinearLayout getLayout()
	{
		LinearLayout layout = new LinearLayout(context);
		layout.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		layout.setBackgroundColor(Color.WHITE);
		
		return layout;
		
	}
	
	/**
	 * @param position
	 * @return
	 */
	private final View getView(int position)
	{
		TextView text = new TextView(context);
		text.setText(items[position]);
		text.setTextColor(Color.BLACK);
		// text.setHeight(height);
		// text.setWidth(width);
		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		text.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.GRAY);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		return text;
	}
	
}
