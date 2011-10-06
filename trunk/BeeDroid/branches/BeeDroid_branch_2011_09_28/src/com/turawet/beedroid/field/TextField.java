package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.field.view.TextFieldView;

public class TextField extends Field
{
	private String				text;
	private TextFieldView	textView;
	
	@Override
	public void setValue(Object text) throws IllegalValueForFieldException
	{
		if (text instanceof String)
			this.text = (String) text;
		else
			throw new IllegalValueForFieldException(text.getClass().getName() + " isn't valid for " + getClass().getName() + " value. A java.lang.String value was expected.");
	}
	
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
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException
	{
		textView = (TextFieldView) obtainAViewForThisField(context);
		return null;
	}
	
}
