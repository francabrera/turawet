package com.turawet.beedroid.beans;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;



/**
 * @class FieldGroupInstanceBean: Represents a FieldGroupInstanceBean
 * 
 * @param groupId: The id of the group
 * @param groupElements: Element of the group (items of the list)
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FieldGroupInstanceBean extends SectionChildBean {
	
	// Should we create a groupInstance and a Group (one for description and other for instantiation) 
	/**
	 * @uml.property  name="groupId"
	 */
	public int groupId;
	/**
	 * @uml.property  name="groupElements"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.turawet.beedroid.beans.GroupElementBean"
	 */
	private List<GroupElementBean> groupElements;
	


	@Override
	public String toString()
	{
		return "GroupID: "+groupId;
	}
	
	
	@Override
	public void toXml(Writer writer)
	{
		String temp = "<group><groupid>"+groupId+"</groupid>";
		Iterator<GroupElementBean> it=groupElements.iterator();
        while(it.hasNext()) {
        	(it.next()).toXml(writer);
        }
        
        temp+="</group>";
	}

}