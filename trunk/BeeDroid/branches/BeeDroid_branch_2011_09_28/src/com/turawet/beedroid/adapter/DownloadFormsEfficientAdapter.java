/**
 * 
 */
package com.turawet.beedroid.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.turawet.beedroid.R;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;

/**
 * @author nicopernas
 * 
 */
public class DownloadFormsEfficientAdapter extends BaseAdapter
{
	private LayoutInflater						mInflater;
	private List<FormIdentificationBean>	listOfForms;
	private boolean[]								checkedItemList;
	
	public DownloadFormsEfficientAdapter(Context context, List<FormIdentificationBean> listOfForms)
	{
		this.mInflater = LayoutInflater.from(context);
		this.listOfForms = listOfForms;
		this.checkedItemList = new boolean[listOfForms.size()];
	}
	
	/**
	 * The number of items in the list is determined by the number of speeches
	 * in our array.
	 * 
	 * @see android.widget.ListAdapter#getCount()
	 */
	public int getCount()
	{
		return listOfForms.size();
	}
	
	/**
	 * Since the data comes from an array, just returning the index is
	 * sufficent to get at the data. If we were using a more complex data
	 * structure, we would return whatever object represents one row in the
	 * list.
	 * 
	 * @see android.widget.ListAdapter#getItem(int)
	 */
	public Object getItem(int position)
	{
		return position;
	}
	
	/**
	 * Use the array index as a unique id.
	 * 
	 * @see android.widget.ListAdapter#getItemId(int)
	 */
	public long getItemId(int position)
	{
		return position;
	}
	
	/**
	 * Make a view to hold each row.
	 * 
	 * @see android.widget.ListAdapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		DownloadsFormItemList rowItem;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.downloads_forms_item_list, null);
			
			rowItem = new DownloadsFormItemList();
			rowItem.formName = (TextView) convertView.findViewById(R.id.download_form_name);
			rowItem.formVersion = (TextView) convertView.findViewById(R.id.download_form_version);
			rowItem.checked = (CheckBox) convertView.findViewById(R.id.check_form_to_download);
			convertView.setTag(rowItem);
		}
		else
		{
			rowItem = (DownloadsFormItemList) convertView.getTag();
		}
		
		FormIdentificationBean formId = listOfForms.get(position);
		rowItem.formName.setText(formId.getName());
		rowItem.formVersion.setText(formId.getVersion());
		rowItem.checked.setChecked(checkedItemList[position]);
		return convertView;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FormIdentificationBean> getSelectedFormsToDownload()
	{
		List<FormIdentificationBean> selectedFormsToDownload = new ArrayList<FormIdentificationBean>();
		for (int i = 0; i < checkedItemList.length; i++)
		{
			if (checkedItemList[i])
				selectedFormsToDownload.add(listOfForms.get(i));
		}
		return selectedFormsToDownload;
	}
	
	/**
	 * 
	 * @param position
	 */
	public void toggle(int position)
	{
		checkedItemList[position] = !checkedItemList[position];
		notifyDataSetChanged();
	}
	
	/**
	 * 
	 */
	public void checkAllItems()
	{
		setAllItemsValueTo(true);
	}
	
	/**
	 * 
	 */
	public void uncheckAllItems()
	{
		setAllItemsValueTo(false);
	}
	
	/**
	 * 
	 * @param value
	 */
	private void setAllItemsValueTo(boolean value)
	{
		for (int i = 0; i < checkedItemList.length; i++)
			checkedItemList[i] = value;
		notifyDataSetChanged();
	}
	
	private static class DownloadsFormItemList
	{
		/**
		 * @author nicopernas
		 */
		public TextView	formName;
		public TextView	formVersion;
		public CheckBox	checked;
	}
	
}
