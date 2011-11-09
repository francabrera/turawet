package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.view.FieldView;

public class NumericField extends Field
{
	private Integer	number;
	
	@Override
	public String getValue()
	{
		return number.toString();
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.NUMERIC;
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
		if (value instanceof Integer)
			number = (Integer) value;
		else
			throw new IllegalValueForFieldException("NumericField value must be a Integer, got " + value.getClass().getName());
	}
		
}
