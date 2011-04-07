/**
 * 
 */
package com.turawet.beedroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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
	 *
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forms);
		
		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		
		for (int i = 1; i < 19; i++)
		{
			Map<String, String> group = new HashMap<String, String>();
			group.put("title", "FORMULARIO " + i);
			group.put("desc", "Versión " + i + ".0");
			groupData.add(group);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, groupData, android.R.layout.simple_list_item_2, new String[]
		{ "title", "desc" }, new int[]
		{ android.R.id.text1, android.R.id.text2 });
		
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		// TODO Auto-generated method stub
		// super.onListItemClick(l, v, position, id);
		Map<String,String> map = (HashMap<String,String>) l.getItemAtPosition(position);
		Toast.makeText(this, map.get("title"), Toast.LENGTH_LONG).show();
	}
	
}
