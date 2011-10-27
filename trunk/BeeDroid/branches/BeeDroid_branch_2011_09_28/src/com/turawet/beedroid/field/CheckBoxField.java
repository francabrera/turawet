package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.field.view.FieldView;

public class CheckBoxField extends Field
{
	
	@Override
	public Object getValue()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.CHECKBOX;
	}
	
	@Override
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readValue() throws IllegalValueForFieldException
	{
		// TODO Auto-generated method stub
		
	}
}
