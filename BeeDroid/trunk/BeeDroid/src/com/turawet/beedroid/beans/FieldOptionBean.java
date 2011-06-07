package com.turawet.beedroid.beans;

/**
 * @class FieldOptionBean: Represents a FieldOptionBean
 * 
 * @param id: The id of the option
 * @param label: The label of the option
 * @param value: The value of the option described by the label
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class FieldOptionBean extends BaseBean {
	
	public Integer id;
	public String label;
	public Integer value;
	
	
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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
