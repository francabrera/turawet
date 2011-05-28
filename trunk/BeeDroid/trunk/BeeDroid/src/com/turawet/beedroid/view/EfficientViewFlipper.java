/**
 * 
 */
package com.turawet.beedroid.view;

import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.parser.FieldContainer;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * @author nicopernas
 * 
 */
public class EfficientViewFlipper extends ViewFlipper
{
	static String[]					items			=
															{ "11111", "222222", "333333", "444444", "555555", "6666666" };
	
	private List<FieldContainer>	fields;
	private int							index;
	
	private static final int		START			= 0;
	private static final int		MIDDLE		= 1;
	private static final int		END			= 2;
	private static final int		BUFF_SIZE	= 3;
	
	/**
	 * 
	 * @param context
	 * @param attributes
	 */
	public EfficientViewFlipper(Context context, AttributeSet attributes)
	{
		super(context, attributes);
		fields = new ArrayList<FieldContainer>();
		index = -1;
		addView(getView(START), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		addView(getView(MIDDLE), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		addView(getView(END), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
	}
	
	private View getView(int position)
	{
		TextView text = new TextView(this.getContext());
		text.setText(items[position]);
		text.setTextColor(Color.BLACK);
		text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		text.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.GRAY);
		return text;
	}
	
	@Override
	public void showNext()
	{
		super.showNext();
		
		
		index++;
		
		if (index > MIDDLE)
		{
			
		}
	}
	
	@Override
	public void showPrevious()
	{
		super.showPrevious();
		Log.d("", "Esto tendría que tardaar en salir");
	}
	
	/**
	 * @param fields
	 */
	public void setFields(List<FieldContainer> fields)
	{
		this.fields = fields;
		this.index = 0;
	}
}
