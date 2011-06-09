package com.turawet.beedroid.beans;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;

/**
 * @class GenericInstanceFieldBean: Represents a Generic instance field
 * 
 * @param order: Every instance field has an order (remember dinamic fields)
 * @param formField: Every instance field has a descriptive form field.
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public abstract class GenericInstanceFieldBean extends SectionChildBean {

	/**
	 * @uml.property  name="order"
	 */
	public int order;
	/**
	 * @uml.property  name="formField"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	public FormFieldBean formField;
	
    /* Constructor */		
	public GenericInstanceFieldBean(int order, FormFieldBean formField) {
		super();
		this.order = order;
		this.formField = formField;
	}
	
	/* Getters && Setters */
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public FormFieldBean getFormField() {
		return formField;
	}

	public void setFormField(FormFieldBean formField) {
		this.formField = formField;
	}

	/* Other methods */
	@Override
	public void toXml(Writer writer) {
	}	
}
