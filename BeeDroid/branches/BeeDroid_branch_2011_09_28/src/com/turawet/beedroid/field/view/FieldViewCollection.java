package com.turawet.beedroid.field.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FieldViewCollection implements Iterable<FieldView>
{
	
	private List<FieldView>	fieldViews;
	
	public FieldViewCollection()
	{
		fieldViews = new ArrayList<FieldView>();
	}
	
	@Override
	public Iterator<FieldView> iterator()
	{
		return fieldViews.iterator();
	}
	
	public boolean addView(FieldView fieldAsView)
	{
		return fieldViews.add(fieldAsView);
	}

	public int getNumberOfFieldViews()
	{
		return fieldViews.size();
	}
	
}
