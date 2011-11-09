package com.turawet.beedroid.field;

import android.content.Context;
import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.view.FieldView;

public class TextField extends Field
{
	private String	text;
	
	@Override
	public String getValue()
	{
		return text;
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.TEXT;
	}
	
	@Override
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException
	{
		view = obtainAViewForThisField(context);
		view.setSectionTitle(getSection().getName());
		view.setFieldLabel(getLabel());
		return view.performView();
	}
	
	@Override
	public void readValueFromView() throws IllegalValueForFieldException
	{
		Object value = view.getValue();
		if (value instanceof String)
			text = (String) value;
		else
			throw new IllegalValueForFieldException("TextField value must be a String, got " + value.getClass().getName());
	}
}
