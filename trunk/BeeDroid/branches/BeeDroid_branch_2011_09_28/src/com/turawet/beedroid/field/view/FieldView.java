package com.turawet.beedroid.field.view;

import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;

import android.content.Context;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
		setViewParameters();
	}
	
	private void setViewParameters()
	{
		setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0.0F));
		setGravity(Gravity.FILL);
		setOrientation(VERTICAL);
		// setBackgroundColor(R.color.white);
	}
	
	public void setSectionTitle(String title)
	{
		sectionTitle = (TextView) viewInflater.inflate(R.layout.section_title, null);
		sectionTitle.setText(title);
		sectionTitle.setPaintFlags(sectionTitle.getPaintFlags() | Paint.ANTI_ALIAS_FLAG);
	}
	
	public void setFieldLabel(String label)
	{
		fieldLabel = (TextView) viewInflater.inflate(R.layout.field_label, null);
		fieldLabel.setText(label);
		fieldLabel.setPaintFlags(fieldLabel.getPaintFlags() | Paint.ANTI_ALIAS_FLAG);
	}
	
	protected void addFieldLabel() throws NullFieldLabelException
	{
		if (fieldLabel == null)
			throw new NullFieldLabelException("The field label can't be null");
		addView(fieldLabel);
	}
	
	protected void addSectionTitle() throws NullSectionTitleExcpetion
	{
		if (sectionTitle == null)
			throw new NullSectionTitleExcpetion("The section title can't be null");
		addView(sectionTitle);
	}
	
	public abstract FieldView performView() throws NullSectionTitleExcpetion, NullFieldLabelException;
	
	public abstract Object getValue();
}
