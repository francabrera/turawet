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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * @author nicopernas
 * 
 */
public class DownloadFormsActivity extends ListActivity
{
	private ListView	itemFormList;
	private boolean[]	checkedItemList;
	
	/**
	 *
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downloads_forms_view);
		itemFormList = (ListView) findViewById(android.R.id.list);
		
		WSClient ws = WSClient.getInstance();
		List<FormPreviewBean> listOfNewForms = ws.getAllFormPreview();
		
		checkedItemList = new boolean[listOfNewForms.size()];
		
		ListAdapter customAdapter = new DownloadFormsEfficientAdapter(this, listOfNewForms, checkedItemList);
		itemFormList.setAdapter(customAdapter);
	}
	
	/**
	 * 
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		ViewGroup row = (ViewGroup) v;
		CheckBox check = (CheckBox) row.findViewById(R.id.checkbox);
		check.toggle();
		checkedItemList[position] = !checkedItemList[position];
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
		for (int i = 0; i < checkedItemList.length; i++)
		{
			if (checkedItemList[i])
				Log.d("Seleccionado -> ", String.valueOf(i));
		}
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectNoneFormsToDownload()
	{
		for (int i = 0; i < checkedItemList.length; i++)
		{
			checkedItemList[i] = false;
			ViewGroup row = (ViewGroup) itemFormList.getChildAt(i);
			if (row != null)
				((CheckBox) row.findViewById(R.id.checkbox)).setChecked(false);
		}
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectAllFormsToDownload()
	{
		for (int i = 0; i < checkedItemList.length; i++)
		{
			checkedItemList[i] = true;
			ViewGroup row = (ViewGroup) itemFormList.getChildAt(i);
			if (row != null)
				((CheckBox) row.findViewById(R.id.checkbox)).setChecked(true);
		}		
		return true;
	}
	
}
