package com.turawet.beedroid.field.misc;

import java.util.ArrayList;
import java.util.List;

public class Options
{
	private List<Option>	options;
	
	public Options()
	{
		options = new ArrayList<Option>();
	}
	
	public boolean addOption(Option option)
	{
		return options.add(option);
	}
	
	public Option getOptionAt(int position)
	{
		return options.get(position);
	}
}
