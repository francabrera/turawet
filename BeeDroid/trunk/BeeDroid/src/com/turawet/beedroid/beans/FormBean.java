package com.turawet.beedroid.beans;

import java.io.Writer;

/**
 * @class FormBean: Represents a form
 * 
 * @param id
 *           : The id of the form
 * @param name
 *           : The name of the form
 * @param version
 *           : The version of the form
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
/**
 * @author nicopernas
 *
 */
public class FormBean extends BaseBean
{
	
	/**
	 * @uml.property name="name"
	 */
	private String		name;
	
	/**
	 * @uml.property name="version"
	 */
	private int			version;
	
	/**
	 * @uml.property name="geolocalizated"
	 */
	private boolean	geolocalized;

	public FormBean()
	{
		super();
		name = "";
		version = -1;
		geolocalized = false;
	}

	/**
	 * @return the geolocalizated
	 */
	public boolean isGeolocalized()
	{
		return geolocalized;
	}
	
	/**
	 * @param geolocalized
	 *           the geolocalized to set
	 */
	public void setGeolocalized(boolean geolocalized)
	{
		this.geolocalized = geolocalized;
	}
	
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public int getVersion()
	{
		return version;
	}
	
	/**
	 * @param version
	 */
	public void setVersion(int version)
	{
		this.version = version;
	}
	
	@Override
	public void toXml(Writer writer)
	{
	}
}
