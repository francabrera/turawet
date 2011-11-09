/**
 * 
 */
package com.turawet.beedroid.util;

/**
 * @author nicopernas
 * 
 */
public class MainIcon
{
	private int			id;
	private String		name;
	private int			resourceId;
	
	public MainIcon(int id, String name, int resourceId)
	{
		this.id = id;
		this.name = name;
		this.resourceId = resourceId;
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
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId)
	{
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceId
	 */
	public int getResourceId()
	{
		return resourceId;
	}
	
}
