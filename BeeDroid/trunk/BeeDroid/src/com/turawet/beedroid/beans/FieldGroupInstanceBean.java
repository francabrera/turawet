package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

import android.util.Xml;

/**
 * @class FieldGroupInstanceBean: Represents a FieldGroupInstanceBean
 * 
 * @param groupId
 *           : The id of the group
 * @param groupElements
 *           : Element of the group (items of the list)
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FieldGroupInstanceBean extends SectionChildBean
{
	
	// Should we create a groupInstance and a Group (one for description and
	// other for instantiation)
	/**
	 * @uml.property name="groupId"
	 */
	private int								groupId;
	/**
	 * @uml.property name="groupElements"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType="com.turawet.beedroid.beans.GroupElementBean"
	 */
	private List<GroupElementBean>	groupElements;
	
	public FieldGroupInstanceBean()
	{
		super();
		groupId = -1;
	}
	
	@Override
	public String toString()
	{
		return "GroupID: " + groupId;
	}
	
	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.group.toString())
		.startTag(XmlTags.namespace, XmlEnumTags.groupid.toString())
		.text(String.valueOf(groupId))
		.endTag(XmlTags.namespace, XmlEnumTags.groupid.toString());
		
		for (GroupElementBean group : groupElements)
		{
			group.toXml(writer);
		}
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.group.toString());
	}
	
}
