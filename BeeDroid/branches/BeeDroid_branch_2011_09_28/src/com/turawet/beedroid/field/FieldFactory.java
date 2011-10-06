package com.turawet.beedroid.field;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.exception.IllegalFieldTypeException;

public interface FieldFactory
{
	public Field makeField(FieldType type) throws IllegalFieldTypeException;
}
