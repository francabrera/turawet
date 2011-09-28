/**
 * 
 */
package com.turawet.beedroid.wsclient.beans;

/**
 * @author nicopernas
 * 
 */
public class FormIdentificationBean
{
	/**
	 *
	 */
	private String	name;
	private String	version;
	
	/**
	 * 
	 */
	public FormIdentificationBean(String name, String version)
	{
		this.name = name;
		this.version = version;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name
	 *           the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion()
	{
		return version;
	}
	
	/**
	 * @param version
	 *           the version to set
	 */
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	@Override
	public String toString()
	{
		return "'" + name + "' : '" + version + "'";
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object o)
	{
		boolean result = false;
		if (o instanceof FormIdentificationBean)
		{
			result = ((FormIdentificationBean) o).getName().equals(name) && ((FormIdentificationBean) o).getVersion().equals(version);
		}
		return result;
	}
}
