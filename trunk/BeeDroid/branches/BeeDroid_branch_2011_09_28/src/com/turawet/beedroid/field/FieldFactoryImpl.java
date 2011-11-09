package com.turawet.beedroid.field;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;

public class FieldFactoryImpl implements FieldFactory
{
	public Field makeField(FieldType type) throws IllegalFieldTypeException
	{
		switch (type)
		{
			case TEXT:
				return new TextField();
			case DATE:
				return new DateField();
			case RADIO:
				return new RadioField();
			case COMBO:
				return new ComboField();
			case CHECKBOX:
				return new CheckBoxField();
			case NUMERIC:
				return new NumericField();
			case IMAGE:
				return new ImageField();
			case GEO:
				return new GeolocalizationField();
			default:
				throw new IllegalFieldTypeException(type + " isn't a valid field type");
		}
	}
}
