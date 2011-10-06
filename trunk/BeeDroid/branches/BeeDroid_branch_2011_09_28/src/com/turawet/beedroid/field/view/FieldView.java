package com.turawet.beedroid.field.view;

import com.turawet.beedroid.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class FieldView extends LinearLayout
{
	protected TextView			sectionTitle;
	protected TextView			fieldLabel;
	protected LayoutInflater	viewInflater;
	
	public FieldView(Context context)
	{
		super(context);
		viewInflater = LayoutInflater.from(context);
	}
	
	protected void setSectionTitle(String title)
	{
		sectionTitle = (TextView) viewInflater.inflate(R.layout.section_title, this);
		sectionTitle.setText(title);
	}
	
	protected void setFieldLabel(String label)
	{
		fieldLabel = (TextView) viewInflater.inflate(R.layout.field_label, this);
		fieldLabel.setText(label);
	}
	
	protected void addFieldLabel()
	{
		addView(fieldLabel);
	}

	protected void addSectionTitle()
	{
		addView(sectionTitle);
	}
	
	protected abstract void generateView();
}
