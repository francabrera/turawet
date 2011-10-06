package com.turawet.beedroid.dao;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.turawet.beedroid.exception.IllegalFieldTypeException;
import com.turawet.beedroid.field.Field;
import com.turawet.beedroid.field.GeolocalizationField;
import com.turawet.beedroid.field.view.FieldViewCollection;
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
	
	public FieldViewCollection getFieldAsViews(Context context) throws IllegalFieldTypeException
	{
		FieldViewCollection fieldsAsViews = new FieldViewCollection();
		for (Field field : fields)
			fieldsAsViews.addView(field.getFieldAsView(context));
		return fieldsAsViews;
	}
}
