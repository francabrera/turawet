package com.turawet.beedroid;

import com.turawet.beedroid.R;
import com.turawet.beedroid.activity.FormsActivity;
import com.turawet.beedroid.activity.InstanceActivity;
import com.turawet.beedroid.activity.OptionsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BeeDroid extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
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
