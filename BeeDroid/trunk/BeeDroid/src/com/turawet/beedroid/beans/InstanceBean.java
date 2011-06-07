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
	public FormBean form;
	/* Insance meta */
	public Integer id;
	public String authoruser;
	public String creationDate;
	public String modificationDate;
	public Boolean editable;
	/* Children */
	private List<SectionBean> sections;

	
	/* Getters y setters */
	
	public FormBean getForm() {
		return form;
	}

	public void setForm(FormBean form) {
		this.form = form;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public List<SectionBean> getSections() {
		return sections;
	}

	public void setSections(List<SectionBean> sections) {
		this.sections = sections;
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
