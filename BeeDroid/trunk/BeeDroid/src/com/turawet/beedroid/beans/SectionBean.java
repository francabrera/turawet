package com.turawet.beedroid.beans;

import java.util.Iterator;
import java.util.List;

/**
 * @class SectionBean: Represents a section
 * 
 * @param id: The id of the form
 * @param name: The name of the form
 * @param order: The order of the section within the form
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class SectionBean extends BaseBean {

	/**
	 * @uml.property  name="name"
	 */
	public String name;
	/**
	 * @uml.property  name="order"
	 */
	public int order;
	/**
	 * @uml.property  name="sectionChildren"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="com.turawet.beedroid.beans.SectionChildBean"
	 */
	private List<SectionChildBean> sectionChildren;
	
	
	/* Getters y setters */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<SectionChildBean> getSectionChildren() {
		return sectionChildren;
	}

	public void setSectionChildren(List<SectionChildBean> sectionChildren) {
		this.sectionChildren = sectionChildren;
	}
	
	/* Other mutators */
	public void addChild(SectionChildBean child) {
		this.sectionChildren.add(child);
	}
	
	/* Other methods */
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public String toXml()
	{
		String tempSections = "<fields>";
		Iterator<SectionChildBean> it=sectionChildren.iterator();
        while(it.hasNext()) {
        	tempSections+=(it.next()).toXml();
        }

        return tempSections+"</fields>";
	}
	
}
