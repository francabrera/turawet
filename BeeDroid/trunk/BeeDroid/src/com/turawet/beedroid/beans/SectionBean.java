package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

import android.util.Xml;

/**
 * @class SectionBean: Represents a section
 * 
 * @param id
 *           : The id of the form
 * @param name
 *           : The name of the form
 * @param order
 *           : The order of the section within the form
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class SectionBean extends BaseBean
{
	
	/**
	 * @uml.property name="name"
	 */
	private String							name;
	/**
	 * @uml.property name="order"
	 */
	private int								order;
	/**
	 * @uml.property name="sectionChildren"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType=
	 *                     "com.turawet.beedroid.beans.SectionChildBean"
	 */
	private List<SectionChildBean>	sectionChildren;
	
	/* Constructor */
	public SectionBean()
	{
		super();
		sectionChildren = new ArrayList<SectionChildBean>();
	}
	
	/* Getters y setters */
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getOrder()
	{
		return order;
	}
	
	public void setOrder(int order)
	{
		this.order = order;
	}
	
	public List<SectionChildBean> getSectionChildren()
	{
		return sectionChildren;
	}
	
	public void setSectionChildren(List<SectionChildBean> sectionChildren)
	{
		this.sectionChildren = sectionChildren;
	}
	
	/* Other mutators */
	public void addChild(SectionChildBean child)
	{
		this.sectionChildren.add(child);
	}
	
	/* Other methods */
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.section.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.id.toString());
		serializer.endTag(XmlTags.namespace, XmlEnumTags.id.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.fields.toString());
		serializer.flush();
		
		for (SectionChildBean section : sectionChildren)
		{
			section.toXml(writer);
		}
		serializer.endTag(XmlTags.namespace, XmlEnumTags.fields.toString());

		serializer.endTag(XmlTags.namespace, XmlEnumTags.section.toString());
		serializer.flush();
	}
	
}
