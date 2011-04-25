/**
 * 
 */
package com.turawet.beedroid.activity;

import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.adapter.DownloadFormsEfficientAdapter;
import com.turawet.beedroid.wsclient.beans.FormPreviewBean;
import com.turawet.beedroid.wsclient.WSClient;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * @author nicopernas
 * 
 */
public class DownloadFormsActivity extends ListActivity
{
	private List<FormPreviewBean>	listOfAvailablesForms;
	
	/**
	 *
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downloads_forms_layout);
		
		WSClient ws = WSClient.getInstance();
		listOfAvailablesForms = ws.getAllFormPreview();
		
		setListAdapter(new DownloadFormsEfficientAdapter(this, listOfAvailablesForms));
	}
	
	/**
	 * 
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		((DownloadFormsEfficientAdapter) getListAdapter()).toggle(position);
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
		Log.d("", "Entramos en downloadAllSelectedForms()");
		final ListView listView = getListView();
		SparseBooleanArray array = listView.getCheckedItemPositions();
		for (int i = 0; i < array.size(); i++)
		{
			Log.d("Seleccionado -> ", "Elemento " + i);
		}
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectNoneFormsToDownload()
	{
		((DownloadFormsEfficientAdapter) getListAdapter()).uncheckAllItems();
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectAllFormsToDownload()
	{
		((DownloadFormsEfficientAdapter) getListAdapter()).checkAllItems();
		return true;
	}
	
}
