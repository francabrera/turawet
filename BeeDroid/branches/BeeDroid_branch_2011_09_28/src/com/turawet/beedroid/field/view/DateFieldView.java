package com.turawet.beedroid.field.view;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.turawet.beedroid.R;
import com.turawet.beedroid.exception.NullFieldLabelException;
import com.turawet.beedroid.exception.NullSectionTitleExcpetion;

import android.content.Context;
import android.widget.DatePicker;

public class DateFieldView extends FieldView
{
	
	private DatePicker	datePicker;
	
	public DateFieldView(Context context)
	{
		super(context);
	}
	
	@Override
	public DateFieldView performView() throws NullFieldLabelException, NullSectionTitleExcpetion
	{
		addSectionTitle();
		addFieldLabel();
		addDatePicker();
		return this;
	}
	
	private void addDatePicker()
	{
		datePicker = (DatePicker) viewInflater.inflate(R.layout.date_picker, null);
		addView(datePicker);
	}
	
	@Override
	public Object getValue()
	{
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		return calendar.getTime();
	}
}
