package com.turawet.beedroid.field.misc;

import java.util.ArrayList;
import java.util.List;

public class Properties
{
	private List<Property>	properties;
	
	public Properties()
	{
		properties = new ArrayList<Property>();
	}
	
	public boolean addProperty(Property property)
	{
		return properties.add(property);
	}
	
	public Property getPropertyAt(int position)
	{
		return properties.get(position);
	}
}
