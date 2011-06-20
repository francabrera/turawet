package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.turawet.beedroid.constants.Cte;

import android.util.Xml;
import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

/**
 * @class InstanceBean: Represents a instance
 * 
 * @param id
 *           : The id of the instance
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class InstanceBean extends BaseBean
{
	
	/* Form meta */
	/**
	 * @uml.property name="form"
	 * @uml.associationEnd
	 */
	private FormBean				form;
	/* Insance meta */
	/**
	 * @uml.property name="authoruser"
	 */
	private String					authoruser;
	/**
	 * @uml.property name="creationDate"
	 */
	private String					creationDate;
	/**
	 * @uml.property name="modificationDate"
	 */
	private String					modificationDate;
	/**
	 * @uml.property name="editable"
	 */
	private boolean				editable;
	/* Children */
	/**
	 * @uml.property name="sections"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType="com.turawet.beedroid.beans.SectionBean"
	 */
	private List<SectionBean>	sections;
	
	private String					latitude;
	private String					longitud;
	
	/* Constructor */
	public InstanceBean()
	{
		super();
		sections = new ArrayList<SectionBean>();
		authoruser = "";
		Date date = new Date();
		SimpleDateFormat simpleDateformat=new SimpleDateFormat("yyyy-MM-dd");
		modificationDate = simpleDateformat.format(date);
		if (creationDate == null || creationDate.equals("")) {
			creationDate = modificationDate;
		}
		latitude = "";
		longitud = "";
		editable = true;
	}
	
	/* Getters y setters */
	public FormBean getForm()
	{
		return form;
	}
	
	public void setForm(FormBean form)
	{
		this.form = form;
	}
	
	public String getAuthoruser()
	{
		return authoruser;
	}
	
	public void setAuthoruser(String authoruser)
	{
		this.authoruser = authoruser;
	}
	
	public String getCreationDate()
	{
		return creationDate;
	}
	
	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}
	
	public String getModificationDate()
	{
		return modificationDate;
	}
	
	public void setModificationDate(String modificationDate)
	{
		this.modificationDate = modificationDate;
	}
	
	public boolean isEditable()
	{
		return editable;
	}
	
	public void setEditable(boolean editable)
	{
		this.editable = editable;
	}
	
	public List<SectionBean> getSections()
	{
		return sections;
	}
	
	public void setSections(List<SectionBean> sections)
	{
		this.sections = sections;
	}
	
	/* Other mutators */
	public void addSection(SectionBean section)
	{
		this.sections.add(section);
	}
	
	/**
	 * @return the latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}
	
	/**
	 * @param latitude
	 *           the latitude to set
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	/**
	 * @return the longitud
	 */
	public String getLongitud()
	{
		return longitud;
	}
	
	/**
	 * @param longitud
	 *           the longitud to set
	 */
	public void setLongitud(String longitud)
	{
		this.longitud = longitud;
	}
	
	/* Other methods */

	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startDocument("UTF-8", null);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.instance.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.id.toString());
		serializer.endTag(XmlTags.namespace, XmlEnumTags.id.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.meta.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.formid.toString());
		serializer.text(String.valueOf(form.getId()));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.formid.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.author.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.user.toString());
		serializer.text(authoruser);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.user.toString());
		serializer.endTag(XmlTags.namespace, XmlEnumTags.author.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.creationdate.toString());
		serializer.text(creationDate);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.creationdate.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.modificationdate.toString());
		serializer.text(modificationDate);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.modificationdate.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.editable.toString());
		serializer.text(String.valueOf(editable));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.editable.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.geolocalization.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.latitude.toString());
		
		serializer.text(latitude);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.latitude.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.longitude.toString());
		
		serializer.text(longitud);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.longitude.toString());
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.geolocalization.toString());
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.meta.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.sections.toString());
		
		serializer.flush();
		/* Sections */
		for (SectionBean section : sections)
			section.toXml(writer);
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.sections.toString());
		serializer.endTag(XmlTags.namespace, XmlEnumTags.instance.toString());
		serializer.endDocument();
	}
	
}
