package com.turawet.beedroid.field;

import java.io.IOException;

import android.content.Context;

import com.turawet.beedroid.constants.Cte.FieldType;
import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.field.view.RadioFieldView;
import com.turawet.beedroid.xml.XmlConverter;

public class RadioField extends OptionField
{
	
	private Integer	choice;
	
	@Override
	public String getValue()
	{
		return getOptionAt(choice).getValue();
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
		((RadioFieldView) view).setOptions(getOptions());
		return view.performView();
	}
	
	@Override
	public void readValueFromView() throws IllegalValueForFieldException
	{
		Object value = view.getValue();
		if (value instanceof String)
			choice = (Integer) value;
		else
			throw new IllegalValueForFieldException("RadioField value must be a Integer, got " + value.getClass().getName());
	}
	
	@Override
	public void convertToXml(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.startElement(XmlEnumTags.field);
		addInstanceFieldId(converter);
		addSectionId(converter);
		addOrder(converter);
		addFormFieldId(converter);
		addOption(converter);
		converter.endElement(XmlEnumTags.field);
	}
	
	final private void addOption(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.addElement(XmlEnumTags.value, getValue());
	}
}
