package com.turawet.beedroid.activity.test;

import android.content.ContentResolver;
import android.test.AndroidTestCase;

import com.turawet.beedroid.dao.Section;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.field.CheckBoxField;
import com.turawet.beedroid.field.ComboField;
import com.turawet.beedroid.field.DateField;
import com.turawet.beedroid.field.ImageField;
import com.turawet.beedroid.field.NumericField;
import com.turawet.beedroid.field.RadioField;
import com.turawet.beedroid.field.TextField;
import com.turawet.beedroid.field.misc.Option;
import com.turawet.beedroid.field.misc.Property;
import com.turawet.beedroid.test.utils.InstanceUtils;

public class XmlToInstanceParserTest extends AndroidTestCase
{
	private InstanceUtils	instanceUtils;
	
	public void setUp() throws Exception
	{
		instanceUtils = new InstanceUtils(getContext());
	}
	
	public void tearDown() throws Exception
	{
	}
	
	public void testNumberOfFields()
	{
		assertEquals(9, instanceUtils.getInstance().getNumberOfFields());
	}
	
	public void testInstanceNotNull()
	{
		assertNotNull(instanceUtils.getInstance());
	}
	
	public void testFormIdField()
	{
		assertEquals("12345", instanceUtils.getInstance().getFormId());
	}
	
	public void testGeolocalized()
	{
		assertEquals(true, instanceUtils.getInstance().isGeolocalized());
	}
	
	public void testFirstSectionField()
	{
		Section section = instanceUtils.getInstance().getSectionAt(0);
		assertEquals("Primera sección", section.getName());
		assertEquals("111", section.getId());
	}
	
	public void testSecondSectionField()
	{
		Section section = instanceUtils.getInstance().getSectionAt(1);
		assertEquals("Segunda sección", section.getName());
		assertEquals("222", section.getId());
	}
	
	public void testFirstTextFieldField()
	{
		TextField textField = (TextField) instanceUtils.getInstance().getFieldAt(0);
		assertEquals("1111", textField.getId());
		assertEquals("Label del primer campo", textField.getLabel());
		assertEquals(true, textField.isRequired());
		
		Property prop = textField.getPropertyAt(0);
		assertEquals("max_length", prop.getName());
		assertEquals("10", prop.getValue());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), textField.getSection());
	}
	
	public void testSecondTextFieldField()
	{
		TextField textField = (TextField) instanceUtils.getInstance().getFieldAt(1);
		assertEquals("", textField.getId());
		assertEquals("Label del segundo campo", textField.getLabel());
		assertEquals(false, textField.isRequired());
		
		Property prop = textField.getPropertyAt(0);
		assertEquals("font", prop.getName());
		assertEquals("Arial", prop.getValue());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), textField.getSection());
	}
	
	public void testFirstDateFieldField()
	{
		DateField dateField = (DateField) instanceUtils.getInstance().getFieldAt(2);
		assertEquals("", dateField.getId());
		assertEquals("Fecha", dateField.getLabel());
		assertEquals(true, dateField.isRequired());
		
		Property prop = dateField.getPropertyAt(0);
		assertEquals("format", prop.getName());
		assertEquals("YYYY/MM/DD", prop.getValue());
		
		prop = dateField.getPropertyAt(1);
		assertEquals("calendar", prop.getName());
		assertEquals("MONTH_CALENDAR", prop.getValue());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), dateField.getSection());
	}
	
	public void testFirstRadioField()
	{
		RadioField radioField = (RadioField) instanceUtils.getInstance().getFieldAt(3);
		assertEquals("132", radioField.getId());
		assertEquals("Radio", radioField.getLabel());
		assertEquals(true, radioField.isRequired());
		
		Option option = radioField.getOptionAt(0);
		assertEquals("Masculino", option.getLabel());
		assertEquals("M", option.getValue());
		
		option = radioField.getOptionAt(1);
		assertEquals("Femenino", option.getLabel());
		assertEquals("F", option.getValue());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), radioField.getSection());
	}
	
	public void testFirstComboField()
	{
		ComboField comboField = (ComboField) instanceUtils.getInstance().getFieldAt(4);
		assertEquals("4321", comboField.getId());
		assertEquals("Combo1", comboField.getLabel());
		assertEquals(false, comboField.isRequired());
		
		Option option = comboField.getOptionAt(0);
		assertEquals("Opción 1", option.getLabel());
		assertEquals("O1", option.getValue());
		
		option = comboField.getOptionAt(1);
		assertEquals("Opción 2", option.getLabel());
		assertEquals("O2", option.getValue());
		
		Property prop = comboField.getPropertyAt(0);
		assertEquals("size", prop.getName());
		assertEquals("30", prop.getValue());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), comboField.getSection());
	}
	
	public void testFirstCheckBoxField()
	{
		CheckBoxField checkboxField = (CheckBoxField) instanceUtils.getInstance().getFieldAt(5);
		assertEquals("14", checkboxField.getId());
		assertEquals("Check1", checkboxField.getLabel());
		assertEquals(true, checkboxField.isRequired());
		
		Property prop = checkboxField.getPropertyAt(0);
		assertEquals("widget", prop.getName());
		assertEquals("TRISTATE", prop.getValue());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), checkboxField.getSection());
		
	}
	
	public void testFirstImageField()
	{
		ImageField imageField = (ImageField) instanceUtils.getInstance().getFieldAt(6);
		assertEquals("", imageField.getId());
		assertEquals("Imagen", imageField.getLabel());
		assertEquals(true, imageField.isRequired());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), imageField.getSection());
	}
	
	public void testNumericField()
	{
		NumericField numericField = (NumericField) instanceUtils.getInstance().getFieldAt(7);
		assertEquals("246", numericField.getId());
		assertEquals("Número de ventanas", numericField.getLabel());
		assertEquals(true, numericField.isRequired());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(0), numericField.getSection());
	}
	
	public void testTextFieldSecondSection()
	{
		TextField textField = (TextField) instanceUtils.getInstance().getFieldAt(instanceUtils.getInstance().getNumberOfFields() - 1);
		assertEquals("543", textField.getId());
		assertEquals("Número de ventanas", textField.getLabel());
		assertEquals(true, textField.isRequired());
		
		assertEquals(instanceUtils.getInstance().getSectionAt(1), textField.getSection());
	}
	
	public void testInvalidValueClassForTextField()
	{
		TextField field = (TextField) instanceUtils.getInstance().getFieldAt(0);
		try
		{
			field.setValue(new Integer(0));
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
	
	public void testInvalidValueClassForDateField()
	{
		DateField field = (DateField) instanceUtils.getInstance().getFieldAt(2);
		try
		{
			field.setValue(new Integer(0));
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
	
	public void testIinvalidValueClassForRadioField()
	{
		RadioField field = (RadioField) instanceUtils.getInstance().getFieldAt(3);
		try
		{
			field.setValue(new Integer(0));
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
	
	public void testInvalidValueClassForComboField()
	{
		ComboField field = (ComboField) instanceUtils.getInstance().getFieldAt(4);
		try
		{
			field.setValue(new Integer(0));
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
	
	public void testInvalidValueClassForCheckboxField()
	{
		CheckBoxField field = (CheckBoxField) instanceUtils.getInstance().getFieldAt(5);
		try
		{
			field.setValue(new Integer(0));
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
	
	public void testInvalidValueClassForImageField()
	{
		ImageField field = (ImageField) instanceUtils.getInstance().getFieldAt(6);
		try
		{
			field.setValue(new Integer(0));
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
	
	public void testInvalidValueClassForNumericField()
	{
		NumericField field = (NumericField) instanceUtils.getInstance().getFieldAt(7);
		try
		{
			field.setValue(false);
			fail("IllegalValueForFieldException exception expected!!");
		}
		catch (IllegalValueForFieldException e)
		{
		}
	}
}
