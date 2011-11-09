/**
 * 
 */
package com.turawet.beedroid.adapter;

import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.wsclient.beans.FormIdentification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class SavedFormsEfficientAdapter extends ArrayAdapter<FormIdentification>
{
	
	private LayoutInflater	mInflater;
	
	/**
	 * @param context
	 * @param resource
	 * @param textViewResourceId
	 * @param objects
	 */
	public SavedFormsEfficientAdapter(Context context, int resource, List<FormIdentification> objects)
	{
		super(context, resource, objects);
		mInflater = LayoutInflater.from(context);
	}
	
	/**
	 *
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		SavedFormItemList rowItem;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.saved_forms_item_list, null);
			
			rowItem = new SavedFormItemList();
			rowItem.formName = (TextView) convertView.findViewById(R.id.saved_form_name);
			rowItem.formVersion = (TextView) convertView.findViewById(R.id.saved_form_version);
			convertView.setTag(rowItem);
		}
		else
		{
			rowItem = (SavedFormItemList) convertView.getTag();
		}
		
		FormIdentification formId = getItem(position);
		rowItem.formName.setText(formId.getName());
		rowItem.formVersion.setText(formId.getVersion());
		return convertView;
	}
	
	public static class SavedFormItemList
	{
		/**
		 * @author nicopernas
		 */
		public TextView	formName;
		public TextView	formVersion;
	}
	
}
