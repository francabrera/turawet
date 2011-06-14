/**
 * 
 */
package com.turawet.beedroid.view.support;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.turawet.beedroid.beans.DateFieldBean;
import com.turawet.beedroid.beans.FieldOptionBean;
import com.turawet.beedroid.beans.GenericInstanceFieldBean;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.beans.RadioFieldBean;
import com.turawet.beedroid.beans.TextFieldBean;
import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.view.FieldView;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * @author nicopernas
 * 
 */
public class InstanceBeanManager
{
	/**
	 *
	 */
	InstanceBean		instance;
	private Context	context;
	private int			sectionIndex;
	
	public InstanceBeanManager(Context context, InstanceBean instance)
	{
		this.instance = instance;
		this.context = context;
	}
	
	public String instanceToXml() throws IllegalArgumentException, IllegalStateException, IOException
	{
		Writer writer = new StringWriter();
		instance.toXml(writer);
		return writer.toString();
	}
	
	/**
	 * 
	 */
	/*
	 * public final void incIndex()
	 * {
	 * if (fieldIndex < numOfFields - 1)
	 * {
	 * fieldIndex++;
	 * }
	 * else
	 * {
	 * if (sectionIndex < numOfSections - 1)
	 * {
	 * sectionIndex++;
	 * fieldIndex = 0;
	 * numOfFields =
	 * instance.getSections().get(sectionIndex).getSectionChildren().size();
	 * }
	 * }
	 * }
	 */
	/**
	 * 
	 */
	/*
	 * public final void decIndex()
	 * {
	 * if (fieldIndex > 0)
	 * {
	 * fieldIndex--;
	 * }
	 * else
	 * {
	 * if (sectionIndex > 0)
	 * {
	 * sectionIndex--;
	 * numOfFields =
	 * instance.getSections().get(sectionIndex).getSectionChildren().size();
	 * fieldIndex = numOfFields - 1;
	 * }
	 * }
	 * }
	 */
	/**
	 * @param section
	 * @param field
	 * @return
	 */
	private final FieldView getNewFieldView(int sectionInd, int fieldInd)
	{
		GenericInstanceFieldBean fieldBean = (GenericInstanceFieldBean) instance.getSections().get(sectionInd).getSectionChildren().get(fieldInd);
		
		FieldType fieldType = fieldBean.getFormField().getType();
		FieldView fieldView = null;
		switch (fieldType)
		{
			case TEXT:
				fieldView = getNewTextFieldView((TextFieldBean) fieldBean);
				break;
			case DATE:
				fieldView = getNewDateFieldView((DateFieldBean) fieldBean);
				break;
			case RADIO:
				fieldView = getNewRadioFieldView((RadioFieldBean) fieldBean);
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
	private FieldView getNewRadioFieldView(RadioFieldBean radioField)
	{
		RadioGroup radioGroup = new RadioGroup(context);
		
		List<FieldOptionBean> options = radioField.getFormField().getFieldOptions();
		int id = 0;
		for(FieldOptionBean option : options)
		{
			RadioButton radio = new RadioButton(context);
			radio.setText(option.getLabel());
			radio.setId(id++);
			radioGroup.addView(radio);
		}
		
		String sectionTitle = instance.getSections().get(sectionIndex).getName();
		String fieldLabel = radioField.getFormField().getLabel();
		FieldType type = radioField.getFormField().getType();
		
		FieldView fieldView = getNewFieldView(type);
		fieldView.setSectionTitle(sectionTitle);
		fieldView.setField(radioGroup, fieldLabel);
		return fieldView;
	}

	/**
	 * @param fieldBean
	 * @return
	 */
	private FieldView getNewDateFieldView(DateFieldBean dateField)
	{
		DatePicker datePicker = new DatePicker(context);
		
		// text.setText(textField.getValue());
		datePicker.setForegroundGravity(Gravity.TOP);
		datePicker.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		String sectionTitle = instance.getSections().get(sectionIndex).getName();
		String fieldLabel = dateField.getFormField().getLabel();
		FieldType type = dateField.getFormField().getType();
		
		FieldView fieldView = getNewFieldView(type);
		fieldView.setSectionTitle(sectionTitle);
		fieldView.setField(datePicker, fieldLabel);
		return fieldView;
	}
	
	/**
	 * @param fieldBean
	 * @return
	 */
	private FieldView getNewTextFieldView(TextFieldBean textField)
	{
		
		EditText text = new EditText(context);
		
		text.setText(textField.getText());
		text.setText("Texto por defecto...");
		text.setGravity(Gravity.TOP);
		text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F));
		
		String sectionTitle = instance.getSections().get(sectionIndex).getName();
		String fieldLabel = textField.getFormField().getLabel();
		FieldType type = textField.getFormField().getType();
		
		FieldView fieldView = getNewFieldView(type);
		fieldView.setSectionTitle(sectionTitle);
		fieldView.setField(text, fieldLabel);
		return fieldView;
	}
	
	/**
	 * @return
	 */
	private final FieldView getNewFieldView(FieldType fieldType)
	{
		FieldView fieldView = new FieldView(context, fieldType);
		fieldView.setGravity(Gravity.CENTER_HORIZONTAL);
		fieldView.setOrientation(LinearLayout.VERTICAL);
		fieldView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 0.0F));
		return fieldView;
	}
	
	/**
	 * @return
	 */
	public List<FieldView> getAllInstanceViews()
	{
		List<FieldView> views = new ArrayList<FieldView>();
		int numberOfSections = instance.getSections().size();
		for (int section = 0; section < numberOfSections; section++)
		{
			int numberOfFields = instance.getSections().get(section).getSectionChildren().size();
			for (int field = 0; field < numberOfFields; field++)
			{
				views.add(getNewFieldView(section, field));
			}
		}
		return views;
	}
	
	/**
	 * @param value
	 * @param mCurrentPage
	 * 
	 */
	private void saveDataToPage(int section, int field, Object value)
	{
		GenericInstanceFieldBean instanceBean = (GenericInstanceFieldBean) instance.getSections().get(section).getSectionChildren().get(field);
		FieldType type = instanceBean.getFormField().getType();
		switch (type)
		{
			case TEXT:
				((TextFieldBean) instanceBean).setText((String) value);
				break;
			case DATE:
				((DateFieldBean) instanceBean).setDate((Date) value);
				break;
			case RADIO:
				((RadioFieldBean) instanceBean).setValue(((Integer)value).intValue());
				break;
			default:
				break;
		}
	}
	
	/**
	 * @param childAt
	 */
	public void readFieldValues(View fieldsViews)
	{
		int numOfSections = instance.getSections().size();
		int i = 0;
		for (int section = 0; section < numOfSections; section++)
		{
			int numOfFields = instance.getSections().get(section).getSectionChildren().size();
			for (int field = 0; field < numOfFields; field++)
			{
				FieldView fieldView = (FieldView) ((LinearLayout) fieldsViews).getChildAt(i++);
				Object value = fieldView.getFieldValue();
				saveDataToPage(section, field, value);
			}
		}
	}
}
