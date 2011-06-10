package com.turawet.beedroid.beans;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @class FormFieldBean: Represents a FormField
 * 
 * @param id: The id of the FormField
 * @param label: The label of the FormField
 * @param section_order: The order of the FormField within the section
 * @param field_group_order: The order of the FormField within the group
 * @param type: The type of the element "FormField"
 * @param required: Whether this field must be filled or not
 * @param fieldOptions: Options list
 * @param properties: Properties list
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class FormFieldBean extends BaseBean {

	/**
	 * @uml.property  name="label"
	 */
	private String label;
	/**
	 * @uml.property  name="section_order"
	 */
	private int section_order;
	/**
	 * @uml.property  name="field_group_order"
	 */
	private int field_group_order;
	/**
	 * @uml.property  name="type"
	 */
	private String type;
	/**
	 * @uml.property  name="required"
	 */
	private boolean required;
	/**
	 * @uml.property  name="fieldOptions"
	 */
	private List<FieldOptionBean> fieldOptions;
	/**
	 * @uml.property  name="properties"
	 */
	private List<PropertyBean> properties;


	/*Constructor*/
	public FormFieldBean() {
		super();
		properties = new ArrayList<PropertyBean>();
	}	

	/* Getter & Setters */
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getSection_order() {
		return section_order;
	}

	public void setSection_order(int sectionOrder) {
		section_order = sectionOrder;
	}

	public int getField_group_order() {
		return field_group_order;
	}

	public void setField_group_order(int fieldGroupOrder) {
		field_group_order = fieldGroupOrder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public List<FieldOptionBean> getFieldOptions() {
		return fieldOptions;
	}

	public void setFieldOptions(List<FieldOptionBean> fieldOptions) {
		this.fieldOptions = fieldOptions;
	}

	public List<PropertyBean> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyBean> properties) {
		this.properties = properties;
	}
	
	
	/* Other mutators */
	public void addProperty(PropertyBean property) {
		this.properties.add(property);
	}
	
	
	/* Other methods */

	@Override
	public String toString() {
		return label;
	}

	@Override
	public void toXml(Writer writer) {

	}
	

}
