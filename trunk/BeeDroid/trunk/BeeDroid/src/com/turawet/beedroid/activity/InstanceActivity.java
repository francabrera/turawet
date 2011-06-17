/**
 * 
 */
package com.turawet.beedroid.activity;

import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.adapter.SavedFormsEfficientAdapter;
import com.turawet.beedroid.constants.Cte.FormWsBean;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * InstanceActivity
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class InstanceActivity extends ListActivity
{

	/**
	 * 
	 */
	
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
		
		Intent intent = new Intent(InstanceActivity.this, FillNewInstanceActivity.class);
		intent.putExtra(FormWsBean.name, form.getName());
		intent.putExtra(FormWsBean.version, form.getVersion());
		startActivity(intent);
	}
	

}
