/**
 * 
 */
package com.turawet.beedroid.activity;

import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.adapter.DownloadFormsEfficientAdapter;
import com.turawet.beedroid.adapter.beans.DownloadsFormItemList;
import com.turawet.beedroid.beans.FormPreviewBean;
import com.turawet.beedroid.wsclient.WSClient;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
		setContentView(R.layout.downloads_forms_view);
		ListView formList = (ListView) findViewById(android.R.id.list);
		
		WSClient ws = WSClient.getInstance();
		List<FormPreviewBean> listOfNewForms = ws.getAllFormPreview();

		formList.setAdapter(new DownloadFormsEfficientAdapter(this, listOfNewForms));
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
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.downloads_forms_menu, menu);
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.select_all:
				return selectAllFormsToDownload();
			case R.id.select_none:
				return selectNoneFormsToDownload();
				
			case R.id.download_selected:
				return downloadAllSelectedForms();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * @return
	 */
	private boolean downloadAllSelectedForms()
	{
		
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectNoneFormsToDownload()
	{
		return false;
	}
	
	/**
	 * @return
	 */
	private boolean selectAllFormsToDownload()
	{
		ListView formList = (ListView) findViewById(android.R.id.list);
		int numOfForms = formList.getCount();
		for (int position = 0; position < numOfForms; position++)
		{
			DownloadsFormItemList row = (DownloadsFormItemList) formList.getItemAtPosition(position);
			row.getCheck().setChecked(true);
		}
		return true;
	}
	
}
