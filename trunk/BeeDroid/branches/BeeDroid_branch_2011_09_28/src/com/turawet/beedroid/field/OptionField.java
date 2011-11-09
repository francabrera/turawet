package com.turawet.beedroid.field;

import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.field.misc.Option;

public abstract class OptionField extends Field
{
	
	protected List<Option>	options;
	
	public OptionField()
	{
		this.options = new ArrayList<Option>();
	}
	
	public boolean addOption(Option option)
	{
		return options.add(option);
	}
	
	public Option getOptionAt(int position)
	{
		return options.get(position);
	}
	
	public List<Option> getOptions()
	{
		return options;
	}
	
	public int numberOfOptions()
	{
		return options.size();
	}
}
