/**
 * 
 */
package com.turawet.beedroid.activities;

import com.turawet.beedroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * @author nicopernas
 * 
 */
public class InstanceActivity extends Activity
{
	/**
	 *
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instance);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.forms:
				return loadForms();
			case R.id.options:
				return loadOptions();
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private boolean loadForms()
	{
		Intent myIntent = new Intent(InstanceActivity.this, FormsActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	private boolean loadOptions()
	{
		Intent myIntent = new Intent(InstanceActivity.this, OptionsActivity.class);
		startActivity(myIntent);
		return true;
	}

	
}
