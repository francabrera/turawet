/**
 * 
 */
package com.turawet.beedroid.activities;

import android.app.ListActivity;
import android.os.Bundle;

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
		//setContentView(R.layout.forms);
		setListAdapter(null);
	}
	
}
