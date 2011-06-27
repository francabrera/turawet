/**
 * 
 */
package com.turawet.beedroid.view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.constants.Cte.InstanceBeanCte;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
		addView(this.field, FIELD);
	}
	
	/**
	 * @return
	 */
	public View getField()
	{
		return this.field;
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
			case NUMERIC:
				Editable numeric = ((EditText) field).getText();
				try
				{
					value = new Integer(numeric.toString());
				}
				catch (Exception e)
				{
					value = new Integer(-1);
				}
				break;
			case DATE:
				DatePicker datePicker = (DatePicker) field;
				int day = datePicker.getDayOfMonth();
				int month = datePicker.getMonth();
				int year = datePicker.getYear();
				value = new Date(year, month, day);
				break;
			case RADIO:
				Integer radioButtonId = ((RadioGroup) field).getCheckedRadioButtonId();
				value = radioButtonId;
				break;
			case IMAGE:
				ImageView image = (ImageView) field;
				BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
				Bitmap bitmap = drawable.getBitmap();
				if (bitmap != null)
				{
					OutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(CompressFormat.JPEG, InstanceBeanCte.JPEG_QUALITY, stream);
					value = stream;
				}
				break;
			case GEO:
				value = ((TextView) field).getText();
				break;
			default:
				break;
		}
		return value;
	}
}
