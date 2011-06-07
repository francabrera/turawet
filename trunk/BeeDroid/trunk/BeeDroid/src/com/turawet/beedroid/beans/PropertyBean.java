package com.turawet.beedroid.beans;

/**
 * @class PropertyBean: Represents a PropertyBean
 * 
 * @param id: The id of the option
 * @param name: The name of the option
 * @param value: The value of the option described by the name
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class PropertyBean extends BaseBean {

	public Integer id;
	public String name;
	public Integer value;

		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String toXml() {
		return null;
	}

}
