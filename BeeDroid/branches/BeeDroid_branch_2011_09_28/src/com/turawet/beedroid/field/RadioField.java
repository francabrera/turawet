package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.field.view.RadioFieldView;

public class RadioField extends OptionField
{
	
	private Integer	choice;
	
	@Override
	public Object getValue()
	{
		return getOptionAt(choice);
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.RADIO;
	}
	
	@Override
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException
	{
		view = (RadioFieldView) obtainAViewForThisField(context);
		view.setSectionTitle(getSection().getName());
		view.setFieldLabel(getLabel());
		((RadioFieldView) view).addOptions(getOptions());
		return view.performView();
	}
	
	@Override
	public void readValue() throws IllegalValueForFieldException
	{
		Object value = view.getValue();
		if (value instanceof String)
			choice = (Integer) value;
		else
			throw new IllegalValueForFieldException("RadioField value must be a Integer, got " + value.getClass().getName());
	}
	
}
