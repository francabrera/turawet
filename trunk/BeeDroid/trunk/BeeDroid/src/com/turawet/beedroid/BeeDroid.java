package com.turawet.beedroid;

import com.turawet.beedroid.R;
import com.turawet.beedroid.activity.ContinueInstanceActivity;
import com.turawet.beedroid.activity.FormsActivity;
import com.turawet.beedroid.activity.InstanceActivity;
import com.turawet.beedroid.activity.OptionsActivity;
import com.turawet.beedroid.adapter.MainGridAdapter;
import com.turawet.beedroid.constants.Cte.MainIcon.Id;
import com.turawet.beedroid.constants.Cte.MainIcon.Name;
import com.turawet.beedroid.util.MainIcon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
public class BeeDroid extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	
	private MainGridAdapter	mainGridAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mainGridAdapter = new MainGridAdapter(this);
		
		addMainIconsToAdapter();
		
		GridView mainGrid = (GridView) findViewById(R.id.main_grid);
		
		mainGrid.setAdapter(mainGridAdapter);
		mainGrid.setOnItemClickListener(itemClickListener);
	}
	
	/**
	 * @param mainGridAdapter
	 */
	private void addMainIconsToAdapter()
	{
		if (mainGridAdapter != null)
		{
			mainGridAdapter.addIcon(new MainIcon(Id.FORMULARIO, Name.FORMULARIO, R.drawable.formulario_icon));
			mainGridAdapter.addIcon(new MainIcon(Id.COMPLETAR_INSTANCIA, Name.COMPLETAR_INSTANCIA, R.drawable.formulario_icon));
			mainGridAdapter.addIcon(new MainIcon(Id.CREAR_INTSANCIA, Name.CREAR_INTSANCIA, R.drawable.formulario_icon));
			mainGridAdapter.addIcon(new MainIcon(Id.OPCIONES, Name.OPCIONES, R.drawable.formulario_icon));
		}
	}
	
	private OnItemClickListener	itemClickListener	= new OnItemClickListener()
	{
		public void onItemClick(AdapterView<?> parent, View v, int position, long id)
		{
			MainIcon mainIcon = mainGridAdapter.getIcon(position);
			switch(mainIcon.getId())
			{
				case Id.FORMULARIO:
					loadFormsActivity();
					break;
				case Id.COMPLETAR_INSTANCIA:
					loadContinueInstanceActivity();
					break;
				case Id.CREAR_INTSANCIA:
					loadInstanceActivity();
					break;
				case Id.OPCIONES:
					loadOptionsActivity();
					break;
				default:
					break;
			}
		}
	};

	/**
	 * 
	 * @return
	 */
	private boolean loadFormsActivity()
	{
		Intent myIntent = new Intent(BeeDroid.this, FormsActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean loadContinueInstanceActivity()
	{
		Intent myIntent = new Intent(BeeDroid.this, ContinueInstanceActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean loadInstanceActivity()
	{
		Intent myIntent = new Intent(BeeDroid.this, InstanceActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean loadOptionsActivity()
	{
		Intent myIntent = new Intent(BeeDroid.this, OptionsActivity.class);
		startActivity(myIntent);
		return true;
	}
}
