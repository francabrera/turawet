package com.turawet.beedroid.field.view;

import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;
import com.turawet.beedroid.field.misc.Option;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioFieldView extends FieldView
{
	
	// TODO esto tiene que devolver un ScrollView para que los radio no se queden
	// fuera (si son muchos)
	// podría heredar de una clase que implement ScrollView, así como los
	// checkbox... o bien usarlo acá adentro como un atributto
	
	private RadioGroup	radioGroup;
	private List<Option>	options;
	
	public RadioFieldView(Context context)
	{
		super(context);
	}
	
	@Override
	public RadioFieldView performView() throws NullSectionTitleExcpetion, NullFieldLabelException
	{
		addSectionTitle();
		addFieldLabel();
		addRadioGroup();
		return this;
	}
	
	private void addRadioGroup()
	{
		radioGroup = (RadioGroup) inflateViewById(R.layout.radio_group);
		addOptionsAsRadioButtons();
		addView(radioGroup);
	}
	
	private void addOptionsAsRadioButtons()
	{
		for (Option option : options)
		{
			RadioButton radio = (RadioButton) inflateViewById(R.layout.radio_button);
			radio.setText(option.getLabel());
			radio.setId(option.getId());
			radioGroup.addView(radio);
		}
	}
	
	@Override
	public Object getValue()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setOptions(List<Option> options)
	{
		this.options = options;
	}
}
