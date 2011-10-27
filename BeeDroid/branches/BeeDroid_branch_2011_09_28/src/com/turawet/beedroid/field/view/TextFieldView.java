package com.turawet.beedroid.field.view;

import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;

import android.content.Context;
import android.widget.EditText;

public class TextFieldView extends FieldView
{
	
	private EditText	textInput;
	
	public TextFieldView(Context context)
	{
		super(context);
	}
	
	private void addTextInput()
	{
		textInput = (EditText) viewInflater.inflate(R.layout.edit_text, null);
		textInput.setHint("texto...");
		addView(textInput);
	}
	
	@Override
	public TextFieldView performView() throws NullSectionTitleExcpetion, NullFieldLabelException
	{
		addSectionTitle();
		addFieldLabel();
		addTextInput();
		return this;
	}

	@Override
	public Object getValue()
	{
		return textInput.getText().toString();
	}
	
}
