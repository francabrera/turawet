package com.turawet.beedroid.field.view;

import com.turawet.beedroid.R;

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
		textInput = (EditText) viewInflater.inflate(R.layout.edit_text, this);
		textInput.setHint("texto...");
		addView(textInput);
	}
	
	@Override
	protected void generateView()
	{
		addSectionTitle();
		addFieldLabel();
		addTextInput();
	}
	
}
