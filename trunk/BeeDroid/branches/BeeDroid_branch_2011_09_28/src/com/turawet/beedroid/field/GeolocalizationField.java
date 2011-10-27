package com.turawet.beedroid.field;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.misc.Position;
import com.turawet.beedroid.field.view.FieldView;

public class GeolocalizationField extends Field
{
	
	private Position	geoPosition;
	
	@Override
	public Object getValue()
	{
		return geoPosition;
	}
	
	@Override
	public FieldType getType()
	{
		return FieldType.GEO;
	}
	
	@Override
	public FieldView getFieldAsView(Context context) throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException
	{
		view = obtainAViewForThisField(context);
		return view.performView();
	}
	
	@Override
	public void readValue() throws IllegalValueForFieldException
	{
		Object value = view.getValue();
		if (value instanceof Position)
			geoPosition = (Position) value;
		else
			throw new IllegalValueForFieldException("GeolocalizationField value must be a GeoPosition, got " + value.getClass().getName());
	}
}
