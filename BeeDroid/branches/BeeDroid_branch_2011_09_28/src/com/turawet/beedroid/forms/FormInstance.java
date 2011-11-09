package com.turawet.beedroid.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateFormat;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.Field;
import com.turawet.beedroid.field.GeolocalizationField;
import com.turawet.beedroid.field.view.FieldViewCollection;
import com.turawet.beedroid.view.support.SectionBookmarksCollection;
import com.turawet.beedroid.xml.XmlConverter;
import com.turawet.beedroid.xml.XmlConvertible;

public class FormInstance implements XmlConvertible
{
	private String						formId;
	private String						instanceId;
	private Date						creationDate;
	private Date						modificationDate;
	private boolean					completed;
	private GeolocalizationField	geolocalization;
	private List<Field>				fields;
	private List<Section>			sections;
	private String						author;
	private Context					context;
	private String						dateFormat;
	private boolean					editable;
	
	public FormInstance()
	{
		creationDate = new Date(0);
		modificationDate = new Date(0);
		completed = false;
		fields = new ArrayList<Field>();
		sections = new ArrayList<Section>();
		author = "-- add author --";
		dateFormat = "--- mm:hh dd/MM/yy ---";
		editable = false;
		instanceId = "";
	}
	
	public String getInstanceId()
	{
		return instanceId;
	}
	
	public void setInstanceId(String instanceId)
	{
		this.instanceId = instanceId;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getFormId()
	{
		return formId;
	}
	
	public void setFormId(String formId)
	{
		this.formId = formId;
	}
	
	public String getCreationDate()
	{
		return DateFormat.format(dateFormat, creationDate).toString();
	}
	
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	public String getModificationDate()
	{
		return DateFormat.format(dateFormat, modificationDate).toString();
	}
	
	public void setModificationDate(Date modificationDate)
	{
		this.modificationDate = modificationDate;
	}
	
	public boolean isCompleted()
	{
		return completed;
	}
	
	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}
	
	public Field getFieldAt(int position)
	{
		return fields.get(position);
	}
	
	public void addSection(Section section)
	{
		sections.add(section);
	}
	
	public Section getSectionAt(int position)
	{
		return sections.get(position);
	}
	
	public void addField(Field field)
	{
		fields.add(field);
	}
	
	public void makeInstanceGeolocalized()
	{
		geolocalization = new GeolocalizationField();
	}
	
	public GeolocalizationField getGeolocalizationField()
	{
		return geolocalization;
	}
	
	public boolean isEditable()
	{
		return editable;
	}
	
	public void setEditable(boolean isEditable)
	{
		editable = isEditable;
	}
	
	public boolean isGeolocalized()
	{
		return geolocalization != null;
	}
	
	public int getNumberOfFields()
	{
		return fields.size();
	}
	
	public void setContext(Context context)
	{
		this.context = context;
	}
	
	public SectionBookmarksCollection getSectionBookmarks()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public FieldViewCollection getFieldAsViews() throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException
	{
		FieldViewCollection fieldsAsViews = new FieldViewCollection();
		for (Field field : fields)
			fieldsAsViews.addView(field.getFieldAsView(context));
		
		addGeolocaliztionFieldIfRequired(fieldsAsViews);
		return fieldsAsViews;
	}
	
	private void addGeolocaliztionFieldIfRequired(FieldViewCollection fieldsViews) throws IllegalFieldTypeException, NullSectionTitleExcpetion, NullFieldLabelException
	{
		if (isGeolocalized())
			fieldsViews.addView(geolocalization.getFieldAsView(context));
	}
	
	public void makeFieldsReadValuesFromViews() throws IllegalValueForFieldException
	{
		for (Field field : fields)
			field.readValueFromView();
		getGeolocalizationField().readValueFromView();
	}
	
	@Override
	public void convertToXml(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.startElement(XmlEnumTags.instance);
		converter.addElement(XmlEnumTags.id, getInstanceId());
		addMetadata(converter);
		addFields(converter);
		converter.endElement(XmlEnumTags.instance);
	}
	
	final private void addFields(XmlConverter converter) throws IOException, IllegalArgumentException, IllegalStateException
	{
		for (Field field : fields)
			field.convertToXml(converter);
	}
	
	final private void addMetadata(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.startElement(XmlEnumTags.meta);
		converter.addElement(XmlEnumTags.formid, getFormId());
		addAuthor(converter);
		converter.addElement(XmlEnumTags.creationdate, getCreationDate());
		converter.addElement(XmlEnumTags.modificationdate, getModificationDate());
		converter.addElement(XmlEnumTags.editable, String.valueOf(isEditable()));
		addGeolocalization(converter);
		converter.endElement(XmlEnumTags.meta);
	}
	
	final private void addAuthor(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		converter.startElement(XmlEnumTags.author);
		converter.addElement(XmlEnumTags.user, getAuthor());
		converter.endElement(XmlEnumTags.author);
	}
	
	final private void addGeolocalization(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException
	{
		getGeolocalizationField().convertToXml(converter);
	}
	
}
