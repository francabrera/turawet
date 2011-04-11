/**
 * 
 */
package com.turawet.beedroid.activity;

import com.turawet.beedroid.R;
import com.turawet.beedroid.adapter.DownloadFormsEfficientAdapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * @author nicopernas
 * 
 */
public class DownloadFormsActivity extends ListActivity
{
	/**
	 *
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new DownloadFormsEfficientAdapter(this));
	}
	
	/**
	 * 
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		CheckBox checkbox = (CheckBox) v.findViewById(R.id.checkbox);
		boolean checked = checkbox.isChecked();
		checkbox.setChecked(!checked);
	}
	
	/**
	 * 
	 * @author nicopernas
	 *
	 */
	
}
