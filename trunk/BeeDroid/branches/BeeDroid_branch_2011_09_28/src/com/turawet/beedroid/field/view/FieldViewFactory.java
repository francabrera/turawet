package com.turawet.beedroid.field.view;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;

public class FieldViewFactory
{
	public static FieldView makeFormFieldView(Context context, FieldType type) throws IllegalFieldTypeException
	{
		switch (type)
		{
			case TEXT:
				return new TextFieldView(context);
			case DATE:
				return new DateFieldView(context);
			case RADIO:
				return new RadioFieldView(context);
			case NUMERIC:
				return new NumericFieldView(context);
			case IMAGE:
				return new ImageFieldView(context);
			case GEO:
				return new GeolocalizationFieldView(context);
			default:
				throw new IllegalFieldTypeException(type.toString() + " isn't a valid field type");
		}
	}
}
