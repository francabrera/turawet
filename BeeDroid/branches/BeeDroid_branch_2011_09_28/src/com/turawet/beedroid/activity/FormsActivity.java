/**
 * 
 */
package com.turawet.beedroid.activity;

import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.wsclient.beans.FormIdentification;
import com.turawet.beedroid.adapter.SavedFormsEfficientAdapter;
import com.turawet.beedroid.constants.Cte.FormWsBean;
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
 * FormsActivity
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
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
		setListAdapter(new SavedFormsEfficientAdapter(this, android.R.layout.simple_list_item_2, new ArrayList<FormIdentification>()));
	}
	
	/**
	 * 
	 */
	@Override
	protected void onStart()
	{
		super.onStart();
		DataBaseManager db = DataBaseManager.getInstance(this);
		List<FormIdentification> savedForms = db.getSavedFormsIdentification();
		
		SavedFormsEfficientAdapter adapter = (SavedFormsEfficientAdapter) getListAdapter();
		adapter.clear();
		for (FormIdentification savedForm : savedForms)
			adapter.add(savedForm);
	}
	
	/**
	 * 
	 */
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id)
//	{
//		super.onListItemClick(l, v, position, id);
//		FormIdentification form = (FormIdentification) l.getItemAtPosition(position);
//		
//		Intent intent = new Intent(FormsActivity.this, FillNewInstanceActivity.class);
//		intent.putExtra(FormWsBean.name, form.getName());
//		intent.putExtra(FormWsBean.version, form.getVersion());
//		startActivity(intent);
//	}
	
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
