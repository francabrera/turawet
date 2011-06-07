package com.turawet.beedroid.beans;

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
 * @autor Romén Rodríguez Gil
 * 
 */
public class FormFieldBean extends SectionChildBean {

	public Integer id;
	public String label;
	public Integer section_order;
	public Integer field_group_order;
	public String type;
	public Boolean required;
	private List<FieldOptionBean> fieldOptions;
	private List<PropertyBean> properties;


	/* Getter & Setters */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getSection_order() {
		return section_order;
	}

	public void setSection_order(Integer sectionOrder) {
		section_order = sectionOrder;
	}

	public Integer getField_group_order() {
		return field_group_order;
	}

	public void setField_group_order(Integer fieldGroupOrder) {
		field_group_order = fieldGroupOrder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
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

	@Override
	public String toString() {
		return label;
	}
	
	@Override
	public String toXml() {

        return null;
	}
	

}
