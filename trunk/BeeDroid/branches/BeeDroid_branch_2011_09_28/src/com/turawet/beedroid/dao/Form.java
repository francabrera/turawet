package com.turawet.beedroid.dao;

import java.util.Date;

public class Form
{
	private String		id;
	private String		version;
	private String		name;
	private String		author;
	private boolean	geolocalized;
	private Date		creationDate;
	
	public Form()
	{
		id = "";
		version = "";
		name = "";
		author = "";
		geolocalized = false;
		creationDate = new Date(0);
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getVersion()
	{
		return version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public boolean isGeolocalized()
	{
		return geolocalized;
	}
	
	public void setGeolocalized(boolean geolocalized)
	{
		this.geolocalized = geolocalized;
	}
	
	public Date getCreationDate()
	{
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	// @Override
	// public void toXml(Writer writer)
	// {
	// }
}
