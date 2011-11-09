package com.turawet.beedroid.field;

import java.io.IOException;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.xml.XmlConverter;

public class CheckBoxField extends Field
{
	
	@Override
	public String getValue()
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
	public void readValueFromView() throws IllegalValueForFieldException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void convertToXml(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		// TODO Auto-generated method stub
		
	}
}
