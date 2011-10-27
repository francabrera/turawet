package com.turawet.beedroid.dao;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Xml;

import com.turawet.beedroid.beans.SectionBean;
import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;
import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.exception.IllegalValueForFieldException;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.Field;
import com.turawet.beedroid.field.GeolocalizationField;
import com.turawet.beedroid.field.view.FieldView;
import com.turawet.beedroid.field.view.FieldViewCollection;
import com.turawet.beedroid.field.view.GeolocalizationFieldView;
import com.turawet.beedroid.view.support.SectionBookmarksCollection;

public class Instance
{
	private String						formId;
	private Date						creationDate;
	private Date						modificationDate;
	private boolean					completed;
	private GeolocalizationField	geolocalization;
	private List<Field>				fields;
	private List<Section>			sections;
	private XmlSerializer			serializer;
	private Context					context;
	
	public Instance()
	{
		creationDate = new Date(0);
		modificationDate = new Date(0);
		completed = false;
		fields = new ArrayList<Field>();
		sections = new ArrayList<Section>();
	}
	
	public String getFormId()
	{
		return formId;
	}
	
	public void setFormId(String formId)
	{
		this.formId = formId;
	}
	
	public Date getCreationDate()
	{
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	public Date getModificationDate()
	{
		return modificationDate;
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
	
	public boolean isGeolocalized()
	{
		return geolocalization != null;
	}
	
	public int getNumberOfFields()
	{
		return fields.size();
	}
	
	public void toXml(Writer writer)
	{
		// TODO Auto-generated method stub
		
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
		{
			GeolocalizationField geo = new GeolocalizationField();
			fields.add(geo);
			fieldsViews.addView(geo.getFieldAsView(context));
		}
	}
	
	public String getFieldValuesAsXml() throws IllegalValueForFieldException, IllegalArgumentException, IllegalStateException, IOException
	{
		// TODO Auto-generated method stub
		// leer valores de cada campo
		// mandar a los campos a traducirse
		// devolver xml
		
		readAllFieldValues();
		
		return translateInstanceAsXml();
	}
	
	private String translateInstanceAsXml() throws IllegalArgumentException, IllegalStateException, IOException
	{
		// // TODO Auto-generated method stub
		// serializer = Xml.newSerializer();
		// Writer writer = new StringWriter();
		// serializer.setOutput(writer);
		// serializer.startDocument("UTF-8", null);
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.instance.toString());
		// serializer.startTag(XmlTags.namespace, XmlEnumTags.id.toString());
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.id.toString());
		// serializer.startTag(XmlTags.namespace, XmlEnumTags.meta.toString());
		// serializer.startTag(XmlTags.namespace, XmlEnumTags.formid.toString());
		// serializer.text(formId);
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.formid.toString());
		// serializer.startTag(XmlTags.namespace, XmlEnumTags.author.toString());
		// serializer.startTag(XmlTags.namespace, XmlEnumTags.user.toString());
		// serializer.text("--- añadir el nombre del usuario que esta usando la aplicación ---");
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.user.toString());
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.author.toString());
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.creationdate.toString());
		// serializer.text(DateFormat.format("--- mm:hh dd/MM/yy ---",
		// creationDate).toString());
		// serializer.endTag(XmlTags.namespace,
		// XmlEnumTags.creationdate.toString());
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.modificationdate.toString());
		// serializer.text(DateFormat.format("--- mm:hh dd/MM/yy ---",
		// modificationDate).toString());
		// serializer.endTag(XmlTags.namespace,
		// XmlEnumTags.modificationdate.toString());
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.editable.toString());
		// serializer.text("--- " + String.valueOf(false) + " ---");
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.editable.toString());
		//
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.geolocalization.toString());
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.latitude.toString());
		//
		// serializer.text("--- latitude ---");
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.latitude.toString());
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.longitude.toString());
		//
		// serializer.text("--- longitud ---");
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.longitude.toString());
		//
		// serializer.endTag(XmlTags.namespace,
		// XmlEnumTags.geolocalization.toString());
		//
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.meta.toString());
		// serializer.startTag(XmlTags.namespace,
		// XmlEnumTags.sections.toString());
		//
		// serializer.flush();
		//
		// /* Sections */
		// for (Section section : sections)
		// section.toXml(writer);
		//
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.sections.toString());
		// serializer.endTag(XmlTags.namespace, XmlEnumTags.instance.toString());
		// serializer.endDocument();
		return "<xml></xml>";
	}
	
	private void readAllFieldValues() throws IllegalValueForFieldException
	{
		// TODO Auto-generated method stub
		for (Field field : fields)
			field.readValue();
	}
	
	public void setContext(Context context)
	{
		this.context = context;
	}
}
