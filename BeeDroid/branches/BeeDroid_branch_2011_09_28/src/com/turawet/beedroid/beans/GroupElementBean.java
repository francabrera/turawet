package com.turawet.beedroid.beans;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import android.util.Xml;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

/**
 * @class GroupElementBean: Represents a GroupElement
 * 
 * @param fields
 *           : Fields contained in this element of the group (one item of the
 *           list)
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class GroupElementBean extends BaseBean
{
	
	/**
	 * @uml.property name="fields"
	 * @uml.associationEnd multiplicity="(0 -1)"
	 *                     elementType=
	 *                     "com.turawet.beedroid.beans.GenericInstanceFieldBean"
	 */
	private List<GenericInstanceFieldBean>	fields;
	
	public GroupElementBean()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		return "GroupElement";
		/*
		 * if (fields.size() > 0)
		 * return "GroupElement: "+fields.first.formField.field_group_order;
		 * else
		 * return null;
		 */
	}
	
	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.element.toString());
		
		for (GenericInstanceFieldBean instanceField : fields)
		{
			instanceField.toXml(writer);
		}
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.element.toString());
	}
	
}
