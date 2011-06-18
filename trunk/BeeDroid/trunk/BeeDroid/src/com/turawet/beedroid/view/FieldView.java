/**
 * 
 */
package com.turawet.beedroid.view;

import java.util.Date;

import com.turawet.beedroid.constants.Cte.FieldType;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.RadioGroup;

/**
 * @author nicopernas
 * 
 */
public class FieldView extends LinearLayout
{
	
	private static final int	SECTION	= 0;
	private static final int	LABEL		= 1;
	private static final int	FIELD		= 2;
	private FieldType				fieldType;
	
	private TextView				sectionTitle;
	private TextView				label;
	private View					field;
	
	/**
	 * @param context
	 */
	public FieldView(Context context)
	{
		super(context);
	}

	public FieldView(Context context, FieldType fieldType)
	{
		super(context);
		this.fieldType = fieldType;
	}
	
	public FieldView(Context context, AttributeSet attr)
	{
		super(context, attr);
	}
	
	/**
	 *
	 */
	public void setSectionTitle(String title)
	{
		sectionTitle = getTextView(title);
		addView(sectionTitle, SECTION);
	}
	
	/**
	 * @param field
	 * @param label
	 */
	public void setField(View field, String label)
	{
		this.field = field;
		this.label = getTextView(label);
		addView(this.label, LABEL);
		addView(field, FIELD);
	}
	
	/**
	 * @param label
	 * @return
	 */
	private final TextView getTextView(String label)
	{
		TextView text = new TextView(getContext());
		text.setText(label.toString());
		text.setGravity(Gravity.TOP);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		return text;
	}
	
	/**
	 * 
	 */
	public void setFieldValue()
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return
	 * 
	 */
	public Object getFieldValue()
	{
		Object value = null;
		switch (fieldType)
		{
			case TEXT:
				Editable text = ((EditText) field).getText();
				value = text.toString();
				break;
			case DATE:
				int day = ((DatePicker) field).getDayOfMonth();
				int month = ((DatePicker) field).getMonth();
				int year = ((DatePicker) field).getYear();
				value = new Date(year, month, day);
				break;
			case RADIO:
				Integer radioButtonId = ((RadioGroup) field).getCheckedRadioButtonId();
				value = radioButtonId;
				break;
			default:
				break;
		}
		return value;
	}
}
