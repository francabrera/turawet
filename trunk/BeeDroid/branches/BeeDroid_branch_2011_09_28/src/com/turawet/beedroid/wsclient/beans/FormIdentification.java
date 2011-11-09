/**
 * 
 */
package com.turawet.beedroid.wsclient.beans;

/**
 * @author nicopernas
 * 
 */
public class FormIdentification
{
	/**
	 *
	 */
	private String	name;
	private String	version;
	
	/**
	 * 
	 */
	public FormIdentification(String name, String version)
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
	public boolean equals(Object object)
	{
		boolean result = false;
		if (object instanceof FormIdentification)
		{
			FormIdentification form = (FormIdentification) object;
			result = form.getName().equals(name) && form.getVersion().equals(version);
		}
		return result;
	}
}
