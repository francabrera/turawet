package com.turawet.beedroid;

import com.turawet.beedroid.R;
import com.turawet.beedroid.activity.ContinueInstanceActivity;
import com.turawet.beedroid.activity.FormsActivity;
import com.turawet.beedroid.activity.InstanceActivity;
import com.turawet.beedroid.activity.OptionsActivity;
//import com.turawet.beedroid.adapter.MainInitialGridAdapter;

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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.GridView;

/**
 * BeeDroid
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class BeeDroid extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);

		LinearLayout child1 = (LinearLayout) findViewById(R.id.widget36);
		child1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				
				Log.d("","ID: " + view.getId());
				loadForms();

			}
		});
		LinearLayout child2 = (LinearLayout) findViewById(R.id.widget37);
		child2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				
				Log.d("","ID: " + view.getId());
				loadToCompleteInstance();
				

			}
		});
		LinearLayout child3 = (LinearLayout) findViewById(R.id.widget38);
		child3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				
				Log.d("","ID: " + view.getId());
				loadForNewInstance();
				

			}
		});
		LinearLayout child4 = (LinearLayout) findViewById(R.id.widget39);
		child4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				
				Log.d("","ID: " + view.getId());
				loadOptions();

			}
		});

	}

	// gridview.setOnItemClickListener(new OnItemClickListener()
	// {
	//			
	// @Override
	// public void onItemClick(AdapterView<?> grid, View imageButton, int
	// position, long rowId)
	// {
	// // TODO Auto-generated method stub
	//				
	// Log.d("", "Position = " + position);
	// Log.d("", "RowID = " + rowId);
	// switch (position)
	// {
	// case 0:
	// /* See forms */
	// loadForms();
	// break;
	// case 1:
	// /* Continue instance */
	// loadToCompleteInstance();
	// break;
	// case 2:
	// /* New instance */
	// loadForNewInstance();
	// break;
	// case 3:
	// /* Options */
	// loadOptions();
	// break;
	// default:
	// break;
	// }
	// }
	// });
	// }

	/**
	 * 
	 */
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
	 * inflater = getMenuInflater(); inflater.inflate(R.menu.main_menu, menu);
	 * return true; }
	 */
	/**
	 * 
	 */
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item)
	// {
	// // Handle item selection
	// switch (item.getItemId())
	// {
	// case R.id.forms:
	// return loadForms();
	// case R.id.instance:
	// return loadToCompleteInstance();
	// case R.id.options:
	// return loadOptions();
	// default:
	// return super.onOptionsItemSelected(item);
	// }
	// }

	/**
	 * 
	 * @return
	 */
	private boolean loadForms() {
		Intent myIntent = new Intent(BeeDroid.this, FormsActivity.class);
		startActivity(myIntent);
		return true;
	}

	/**
	 * 
	 * @return
	 */
	private boolean loadToCompleteInstance() {
		Intent myIntent = new Intent(BeeDroid.this,
				ContinueInstanceActivity.class);
		startActivity(myIntent);
		return true;
	}

	/**
	 * 
	 * @return
	 */
	private boolean loadForNewInstance() {
		Intent myIntent = new Intent(BeeDroid.this, InstanceActivity.class);
		startActivity(myIntent);
		return true;
	}

	/**
	 * 
	 * @return
	 */
	private boolean loadOptions() {
		Intent myIntent = new Intent(BeeDroid.this, OptionsActivity.class);
		startActivity(myIntent);
		return true;
	}
}
