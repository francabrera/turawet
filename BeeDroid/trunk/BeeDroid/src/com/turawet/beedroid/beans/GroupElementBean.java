package com.turawet.beedroid.beans;

import java.util.Iterator;
import java.util.List;


/**
 * @class GroupElementBean: Represents a GroupElement
 * 
 * @param fields: Fields contained in this element of the group (one item of the list)
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class GroupElementBean extends BaseBean {

	private List<GenericInstanceFieldBean> fields;

	@Override
	public String toString()
	{
		return "GroupElement";
		/*
		if (fields.size() > 0)
			return "GroupElement: "+fields.first.formField.field_group_order;
		else
			return null;
		*/
	}
	
	@Override
	public String toXml()
	{
		String temp = "<element>";
		Iterator<GenericInstanceFieldBean> it=fields.iterator();
        while(it.hasNext()) {
        	temp +=(it.next()).toXml();
        }

        return temp+"</element>";
	}

}
