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
	public FormBean getForm() {
		return form;
	}

	public void setForm(FormBean form) {
		this.form = form;
	}

	public String getAuthoruser() {
		return authoruser;
	}

	public void setAuthoruser(String authoruser) {
		this.authoruser = authoruser;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<SectionBean> getSections() {
		return sections;
	}

	public void setSections(List<SectionBean> sections) {
		this.sections = sections;
	}
	
	/* Other mutators */
	public void addSection(SectionBean section) {
		this.sections.add(section);
	}
	
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
