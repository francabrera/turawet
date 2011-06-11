/**
 * 
 */
package com.turawet.beedroid.view.support;

import com.turawet.beedroid.beans.DateFieldBean;
import com.turawet.beedroid.beans.GenericInstanceFieldBean;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.beans.TextFieldBean;
import com.turawet.beedroid.constants.Cte.FieldType;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
	private boolean	increased;
	private boolean	decreased;
	InstanceBean		instance;
	private Context	context;
	private int			sectionIndex;
	private int			fieldIndex;
	private int			numOfSections;
	private int			numOfFields;
	
	public BeanToViewGenerator(Context context, InstanceBean instance)
	{
		this.instance = instance;
		this.context = context;
		sectionIndex = 0;
		fieldIndex = 0;
		
		numOfSections = this.instance.getSections().size();
		numOfFields = this.instance.getSections().get(sectionIndex).getSectionChildren().size();
		
		increased = false;
		decreased = false;
		
	}
	
	/**
	 * @return
	 */
	public final boolean updateBeforeDecrease()
	{
		boolean out = false;
		if (!decreased)
		{
			decIndex();
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
			incIndex();
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
		return sectionIndex < numOfSections - 1 || fieldIndex < numOfFields - 1;
	}
	
	/**
	 * @return
	 */
	public final boolean canDecreaseIndex()
	{
		return sectionIndex > 0 || fieldIndex > 0;
	}
	
	/**
	 * 
	 */
	public final void incIndex()
	{
		if (fieldIndex < numOfFields - 1)
		{
			fieldIndex++;
		}
		else
		{
			if (sectionIndex < numOfSections - 1)
			{
				sectionIndex++;
				fieldIndex = 0;
				numOfFields = instance.getSections().get(sectionIndex).getSectionChildren().size();
			}
		}
	}
	
	/**
	 * 
	 */
	public final void decIndex()
	{
		if (fieldIndex > 0)
		{
			fieldIndex--;
		}
		else
		{
			if (sectionIndex > 0)
			{
				sectionIndex--;
				numOfFields = instance.getSections().get(sectionIndex).getSectionChildren().size();
				fieldIndex = numOfFields - 1;
			}
		}
	}
	
	/**
	 * @return
	 */
	public final View getCurrentView()
	{
		return getNewFieldView(sectionIndex, fieldIndex);
	}
	
	/**
	 * @return
	 */
	public final View getNextView()
	{
		int field = fieldIndex;
		int section = sectionIndex;
		// Estoy enel último campo, el siguiente será null
		if (section == numOfSections - 1 && field == numOfFields - 1)
		{
			return null;
		}
		else
		{
			if (field == numOfFields - 1)
			{
				field = 0;
				section++;
			}
			else
			{
				field++;
			}
		}
		return getNewFieldView(section, field);
	}
	
	/**
	 * @return
	 */
	public final View getPrevView()
	{
		int field = fieldIndex;
		int section = sectionIndex;
		// Estoy enel último campo, el siguiente será null
		if (section == 0 && field == 0)
		{
			return null;
		}
		else
		{
			if (field == 0)
			{
				section--;
				field = instance.getSections().get(section).getSectionChildren().size() - 1;
			}
			else
			{
				field--;
			}
		}
		return getNewFieldView(section, field);
	}
	
	/**
	 * @param section
	 * @param field
	 * @return
	 */
	private final View getNewFieldView(int sectionInd, int fieldInd)
	{
		GenericInstanceFieldBean fieldBean = (GenericInstanceFieldBean) instance.getSections().get(sectionInd).getSectionChildren().get(fieldInd);
		
		FieldType fieldType = fieldBean.getFormField().getType();
		View fieldView = null;
		switch (fieldType)
		{
			case TEXT:
				fieldView = getNewTextFieldView((TextFieldBean) fieldBean);
				break;
			case DATE:
				fieldView = getNewDateFieldView((DateFieldBean) fieldBean);
				break;
			default:
				break;
		}
		
		return fieldView;
	}
	
	/**
	 * @param fieldBean
	 * @return
	 */
	private View getNewDateFieldView(DateFieldBean dateField)
	{
		EditText text = new EditText(context);
		
		// text.setText(textField.getValue());
		text.setText("Texto por defecto...");
		text.setGravity(Gravity.TOP);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		LinearLayout layout = getLayout();
		layout.addView(getLabel(dateField.getFormField().getLabel()));
		layout.addView(text);
		return layout;
	}
	/*	DatePicker datePicker = new DatePicker(context);
		
		// text.setText(textField.getValue());
		datePicker.setForegroundGravity(Gravity.TOP);
		datePicker.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		LinearLayout layout = getLayout();
		layout.addView(getLabel(dateField.getFormField().getLabel()));
		layout.addView(datePicker);
		return layout;*/
	/**
	 * @param fieldBean
	 * @return
	 */
	private View getNewTextFieldView(TextFieldBean textField)
	{
		
	EditText text = new EditText(context);
		
		// text.setText(textField.getValue());
		text.setText("Texto por defecto...");
		text.setGravity(Gravity.TOP);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		LinearLayout layout = getLayout();
		layout.addView(getLabel(textField.getFormField().getLabel()));
		layout.addView(text);
		return layout;
	}
	
	/**
	 * @param title
	 * @return
	 */
	private final TextView getLabel(String title)
	{
		TextView text = new TextView(context);
		text.setText(title.toString());
		text.setTextColor(Color.WHITE);
		text.setGravity(Gravity.TOP);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		return text;
	}
	
	private final LinearLayout getLayout()
	{
		LinearLayout layout = new LinearLayout(context);
		layout.setGravity(Gravity.CENTER_HORIZONTAL);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		return layout;
	}
}
