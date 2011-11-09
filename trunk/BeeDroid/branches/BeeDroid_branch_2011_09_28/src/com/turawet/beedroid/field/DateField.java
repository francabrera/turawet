package com.turawet.beedroid.field;

import java.util.Date;

import android.content.Context;
import android.text.format.DateFormat;

import com.turawet.beedroid.constants.Cte;
import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.view.DateFieldView;
import com.turawet.beedroid.field.view.FieldView;

public class DateField extends Field
{
	
	private Date	date;
	
	@Override
	public String getValue()
	{
		// TODO cambiar el Cte.Field.DATE_FORMAT por el formato definido en la
		// propieddad obtenida del formulario
		return DateFormat.format(Cte.Field.DATE_FORMAT, date).toString();
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.DATE;
	}
	
	@Override
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException
	{
		view = (DateFieldView) obtainAViewForThisField(context);
		view.setSectionTitle(getSection().getName());
		view.setFieldLabel(getLabel());
		return view.performView();
	}
	
	@Override
	public void readValueFromView() throws IllegalValueForFieldException
	{
		Object value = view.getValue();
		if (value instanceof Date)
			date = (Date) value;
		else
			throw new IllegalValueForFieldException("DateField value must be a java.util.Date, got " + value.getClass().getName());
	}
	
}
