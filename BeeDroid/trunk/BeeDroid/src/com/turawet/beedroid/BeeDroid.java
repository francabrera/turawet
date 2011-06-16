package com.turawet.beedroid;

import com.turawet.beedroid.R;
import com.turawet.beedroid.activity.FormsActivity;
import com.turawet.beedroid.activity.InstanceActivity;
import com.turawet.beedroid.activity.OptionsActivity;
import com.turawet.beedroid.adapter.MainInitialGridAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class BeeDroid extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		GridView mainGrid = (GridView) getLayoutInflater().inflate(R.layout.main, null);
		
		// (GridView) findViewById(R.id.mainGrid);
		int columnWidth = getWindowManager().getDefaultDisplay().getWidth();
		mainGrid.setColumnWidth(columnWidth / 2);
		setContentView(mainGrid);
		
		GridView gridview = (GridView) findViewById(R.id.mainGrid);
		gridview.setAdapter(new MainInitialGridAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> grid, View imageButton, int position, long rowId)
			{
				// TODO Auto-generated method stub
				
				Log.d("", "Position = " + position);
				Log.d("", "RowID = " + rowId);
				switch (position)
				{
					case 0:
						loadForms();
						break;
					case 1:
						loadInstance();
						break;
					case 2:
						loadOptions();
						break;
					default:
						break;
				}
			}
		});
	}
	
	/**
	 * 
	 */
	/*
	 * @Override
	 * public boolean onCreateOptionsMenu(Menu menu)
	 * {
	 * MenuInflater inflater = getMenuInflater();
	 * inflater.inflate(R.menu.main_menu, menu);
	 * return true;
	 * }
	 */
	/**
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.forms:
				return loadForms();
			case R.id.instance:
				return loadInstance();
			case R.id.options:
				return loadOptions();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean loadForms()
	{
		Intent myIntent = new Intent(BeeDroid.this, FormsActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean loadInstance()
	{
		Intent myIntent = new Intent(BeeDroid.this, InstanceActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean loadOptions()
	{
		Intent myIntent = new Intent(BeeDroid.this, OptionsActivity.class);
		startActivity(myIntent);
		return true;
	}
}
