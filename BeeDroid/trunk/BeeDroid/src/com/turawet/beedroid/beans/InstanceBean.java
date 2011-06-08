package com.turawet.beedroid.beans;

import java.util.Iterator;
import java.util.List;

/**
 * @class InstanceBean: Represents a instance
 * 
 * @param id: The id of the instance
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class InstanceBean extends BaseBean {

	/* Form meta */
	/**
	 * @uml.property  name="form"
	 * @uml.associationEnd  
	 */
	public FormBean form;
	/* Insance meta */
	/**
	 * @uml.property  name="authoruser"
	 */
	public String authoruser;
	/**
	 * @uml.property  name="creationDate"
	 */
	public String creationDate;
	/**
	 * @uml.property  name="modificationDate"
	 */
	public String modificationDate;
	/**
	 * @uml.property  name="editable"
	 */
	public boolean editable;
	/* Children */
	/**
	 * @uml.property  name="sections"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.turawet.beedroid.beans.SectionBean"
	 */
	private List<SectionBean> sections;

	
	/* Getters y setters */
	
	
	/* Other methods */

	@Override
	public String toString()
	{
		return "Instance: "+id+" Form: "+form.name;
	}
	
	@Override
	public String toXml()
	{
		String tempSections = "<instance>" +
				"<meta><id/><formid>"+form.getId()+"</formid>" +
				"<author><user>"+authoruser+"</user></author>" +
				"<creationdate>"+creationDate+"</creationdate>" +
				"<modificationdate>"+modificationDate+"</modificationdate>" +
				"<editable>"+editable+"</editable>" +
				"</meta>";
		/* Sections */
		Iterator<SectionBean> it=sections.iterator();
        while(it.hasNext()) {
          tempSections+=(it.next()).toXml();
        }
		
		return tempSections+"</instance>";
	}

}
