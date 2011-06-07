package com.turawet.beedroid.beans;

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
	public Integer groupId;
	private List<GroupElementBean> groupElements;
	


	@Override
	public String toString()
	{
		return "GroupID: "+groupId;
	}
	
	
	@Override
	public String toXml()
	{
		String temp = "<group><groupid>"+groupId+"</groupid>";
		Iterator<GroupElementBean> it=groupElements.iterator();
        while(it.hasNext()) {
        	temp+=(it.next()).toXml();
        }
        
        return temp+"</group>";
	}

}
