package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.field.view.FieldView;

public class RadioField extends OptionField
{
	
	@Override
	public void setValue(Object value)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object getValue()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.RADIO;
	}

	@Override
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
