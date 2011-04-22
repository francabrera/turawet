/**
 * 
 */
package com.turawet.beedroid.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.turawet.beedroid.R;
import com.turawet.beedroid.beans.FormPreviewBean;
import com.turawet.beedroid.constantes.Cte;
import com.turawet.beedroid.wsclient.WSClient;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * @author nicopernas
 * 
 */
public class FormsActivity extends ListActivity
{
	/**
	 * @param savedInstanceState
	 * 	Instancia salvada en caso que la actividad 
	 * 	inicie luego de un reposo
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forms);
		
		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		
		WSClient ws = WSClient.getInstance();
		List<FormPreviewBean> forms = ws.getAllFormPreview();
		int numOfFormPreviews = forms.size();
		for (int i = 0; i < numOfFormPreviews; i++)
		{
			FormPreviewBean formPreviewBean = forms.get(i);
			String name = formPreviewBean.getName();
			String version = formPreviewBean.getVersion();
			
			Map<String, String> group = new HashMap<String, String>();
			group.put(Cte.formPreviewBean.name, name);
			group.put(Cte.formPreviewBean.version, version);
			
			groupData.add(group);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, groupData, android.R.layout.simple_list_item_2, new String[]
		{ Cte.formPreviewBean.name, Cte.formPreviewBean.version }, new int[]
		{ android.R.id.text1, android.R.id.text2 });
		
		setListAdapter(adapter);
	}
	
	/**
	 * 
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO Auto-generated method stub
		// super.onListItemClick(l, v, position, id);
		Map<String, String> map = (HashMap<String, String>) l.getItemAtPosition(position);
		Toast.makeText(this, map.get("name"), Toast.LENGTH_LONG).show();
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
