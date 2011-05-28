/**
 * 
 */
package com.turawet.beedroid.activity;

import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;
import com.turawet.beedroid.adapter.SavedFormsEfficientAdapter;
import com.turawet.beedroid.constants.Cte;
import com.turawet.beedroid.database.DataBaseManager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/**
 * @author nicopernas
 * 
 */
public class FormsActivity extends ListActivity
{
	/**
	 * @param savedInstanceState
	 *           Instancia salvada en caso que la actividad
	 *           inicie luego de un reposo
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forms);
		setListAdapter(new SavedFormsEfficientAdapter(this, android.R.layout.simple_list_item_2, new ArrayList<FormIdentificationBean>()));
	}
	
	/**
	 * 
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		DataBaseManager db = DataBaseManager.getInstance(this);
		List<FormIdentificationBean> savedForms = db.getSavedFormsIdentification();
		
		SavedFormsEfficientAdapter adapter = (SavedFormsEfficientAdapter) getListAdapter();
		adapter.clear();
		for (FormIdentificationBean savedForm : savedForms)
			adapter.add(savedForm);
	}
	
	/**
	 * 
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);
		FormIdentificationBean form = (FormIdentificationBean) l.getItemAtPosition(position);
		
		Intent intent = new Intent(FormsActivity.this, FillNewInstanceActivity.class);
		intent.putExtra(Cte.FormIdentificationBean.name, form.getName());
		intent.putExtra(Cte.FormIdentificationBean.version, form.getVersion());
		startActivity(intent);
	}
	
	/**
	 * @param menu
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.forms_menu, menu);
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
			case R.id.download_forms:
				return downloadForms();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean downloadForms()
	{
		Intent myIntent = new Intent(FormsActivity.this, DownloadFormsActivity.class);
		startActivity(myIntent);
		return true;
	}
}
