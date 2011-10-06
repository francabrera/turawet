package com.turawet.beedroid.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.turawet.beedroid.dao.Instance;
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
import com.turawet.beedroid.parser.XmlToInstanceParser;

public class XmlToInstanceParserTest
{
	
	private static final String	formulariCompletoXmlFile	= "assets/formulario_for_test.xml";
	private FileInputStream			xmlFile;
	private XmlToInstanceParser	parser;
	private Instance					instance;
	
	@Before
	public void setUp() throws Exception
	{
		xmlFile = new FileInputStream(formulariCompletoXmlFile);
		parser = new XmlToInstanceParser();
		parser.parse(xmlFile);
		instance = parser.getInstance();
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void numberOfFieldsTest()
	{
		assertEquals(9, instance.getNumberOfFields());
	}
	
	@Test
	public void xmlFileNotNullTest()
	{
		assertNotNull(xmlFile);
	}
	
	@Test
	public void parserNotNullFieldTest() throws SAXException, ParserConfigurationException, IOException
	{
		assertNotNull(parser);
	}
	
	@Test
	public void instanceNotNullTest()
	{
		assertNotNull(instance);
	}
	
	@Test
	public void formIdFieldTest()
	{
		assertEquals("12345", instance.getFormId());
	}
	
	@Test
	public void geolocalizedTest()
	{
		assertEquals(true, instance.isGeolocalized());
	}
	
	@Test
	public void firstSectionFieldTest()
	{
		Section section = instance.getSectionAt(0);
		assertEquals("Primera sección", section.getName());
		assertEquals("111", section.getId());
	}
	
	@Test
	public void secondSectionFieldTest()
	{
		Section section = instance.getSectionAt(1);
		assertEquals("Segunda sección", section.getName());
		assertEquals("222", section.getId());
	}
	
	@Test
	public void firstTextFieldFieldTest()
	{
		TextField textField = (TextField) instance.getFieldAt(0);
		assertEquals("1111", textField.getId());
		assertEquals("Label del primer campo", textField.getLabel());
		assertEquals(true, textField.isRequired());
		
		Property prop = textField.getPropertyAt(0);
		assertEquals("max_length", prop.getName());
		assertEquals("10", prop.getValue());
		
		assertEquals(instance.getSectionAt(0), textField.getSection());
	}
	
	@Test
	public void secondTextFieldFieldTest()
	{
		TextField textField = (TextField) instance.getFieldAt(1);
		assertEquals("", textField.getId());
		assertEquals("Label del segundo campo", textField.getLabel());
		assertEquals(false, textField.isRequired());
		
		Property prop = textField.getPropertyAt(0);
		assertEquals("font", prop.getName());
		assertEquals("Arial", prop.getValue());
		
		assertEquals(instance.getSectionAt(0), textField.getSection());
	}
	
	@Test
	public void firstDateFieldFieldTest()
	{
		DateField dateField = (DateField) instance.getFieldAt(2);
		assertEquals("", dateField.getId());
		assertEquals("Fecha", dateField.getLabel());
		assertEquals(true, dateField.isRequired());
		
		Property prop = dateField.getPropertyAt(0);
		assertEquals("format", prop.getName());
		assertEquals("YYYY/MM/DD", prop.getValue());
		
		prop = dateField.getPropertyAt(1);
		assertEquals("calendar", prop.getName());
		assertEquals("MONTH_CALENDAR", prop.getValue());
		
		assertEquals(instance.getSectionAt(0), dateField.getSection());
	}
	
	@Test
	public void firstRadioFieldTest()
	{
		RadioField radioField = (RadioField) instance.getFieldAt(3);
		assertEquals("132", radioField.getId());
		assertEquals("Radio", radioField.getLabel());
		assertEquals(true, radioField.isRequired());
		
		Option option = radioField.getOptionAt(0);
		assertEquals("Masculino", option.getLabel());
		assertEquals("M", option.getValue());
		
		option = radioField.getOptionAt(1);
		assertEquals("Femenino", option.getLabel());
		assertEquals("F", option.getValue());
		
		assertEquals(instance.getSectionAt(0), radioField.getSection());
	}
	
	@Test
	public void firstComboFieldTest()
	{
		ComboField comboField = (ComboField) instance.getFieldAt(4);
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
		
		assertEquals(instance.getSectionAt(0), comboField.getSection());
	}
	
	@Test
	public void firstCheckBoxFieldTest()
	{
		CheckBoxField checkboxField = (CheckBoxField) instance.getFieldAt(5);
		assertEquals("14", checkboxField.getId());
		assertEquals("Check1", checkboxField.getLabel());
		assertEquals(true, checkboxField.isRequired());
		
		Property prop = checkboxField.getPropertyAt(0);
		assertEquals("widget", prop.getName());
		assertEquals("TRISTATE", prop.getValue());
		
		assertEquals(instance.getSectionAt(0), checkboxField.getSection());
		
	}
	
	@Test
	public void firstImageFieldTest()
	{
		ImageField imageField = (ImageField) instance.getFieldAt(6);
		assertEquals("", imageField.getId());
		assertEquals("Imagen", imageField.getLabel());
		assertEquals(true, imageField.isRequired());
		
		assertEquals(instance.getSectionAt(0), imageField.getSection());
	}
	
	@Test
	public void firstNumericFieldTest()
	{
		NumericField numericField = (NumericField) instance.getFieldAt(7);
		assertEquals("246", numericField.getId());
		assertEquals("Número de ventanas", numericField.getLabel());
		assertEquals(true, numericField.isRequired());
		
		assertEquals(instance.getSectionAt(0), numericField.getSection());
	}
	
	@Test
	public void textFieldSecondSectionTest()
	{
		TextField textField = (TextField) instance.getFieldAt(instance.getNumberOfFields() - 1);
		assertEquals("543", textField.getId());
		assertEquals("Número de ventanas", textField.getLabel());
		assertEquals(true, textField.isRequired());
		
		assertEquals(instance.getSectionAt(1), textField.getSection());
	}
	
//	@Test
//	public void numberOfFieldViewsTest()
//	{
//		FieldViewCollection fieldViews = instance.getFieldAsViews();
//		assertEquals(instance.getNumberOfFields(), fieldViews.getNumberOfFieldViews());
//	}
//	
//	@Test
//	public void allFieldViewsNotNullTest()
//	{
//		for (FieldView fieldView : instance.getFieldAsViews())
//			assertNotNull(fieldView);
//	}
//	
	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForTextFieldTest() throws IllegalValueForFieldException
	{
		TextField field = (TextField) instance.getFieldAt(0);
		field.setValue(new Integer(0));
	}
	
	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForDateFieldTest() throws IllegalValueForFieldException
	{
		DateField field = (DateField) instance.getFieldAt(2);
		field.setValue(new Integer(0));
	}
	
	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForRadioFieldTest() throws IllegalValueForFieldException
	{
		RadioField field = (RadioField) instance.getFieldAt(3);
		field.setValue(new Integer(0));
	}
	
	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForComboFieldTest() throws IllegalValueForFieldException
	{
		ComboField field = (ComboField) instance.getFieldAt(4);
		field.setValue(new Integer(0));
	}

	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForCheckboxFieldTest() throws IllegalValueForFieldException
	{
		CheckBoxField field = (CheckBoxField) instance.getFieldAt(5);
		field.setValue(new Integer(0));
	}

	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForImageFieldTest() throws IllegalValueForFieldException
	{
		ImageField field = (ImageField) instance.getFieldAt(6);
		field.setValue(new Integer(0));
	}
	

	@Test(expected = IllegalValueForFieldException.class)
	public void invalidValueClassForNumericFieldTest() throws IllegalValueForFieldException
	{
		NumericField field = (NumericField) instance.getFieldAt(7);
		field.setValue(false);
	}
	
	
	// <field type="CHECKBOX">
	// <id>14</id>
	// <label>Check1</label>
	// <required />
	// <properties>
	// <property>
	// <name>widget</name>
	// <value>TRISTATE</value>
	// </property>
	// </properties>
	// </field>
}
