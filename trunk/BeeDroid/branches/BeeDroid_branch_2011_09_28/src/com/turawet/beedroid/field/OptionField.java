package com.turawet.beedroid.field;

import com.turawet.beedroid.field.misc.Option;
import com.turawet.beedroid.field.misc.Options;

public abstract class OptionField extends Field
{
	
	protected Options	options;
	
	public OptionField()
	{
		this.options = new Options();
	}
	
	public boolean addOption(Option option)
	{
		return options.addOption(option);
	}
	
	public Option getOptionAt(int position)
	{
		return options.getOptionAt(position);
	}
	
	public Options getOptions()
	{
		return options;
	}
}
