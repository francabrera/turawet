package com.turawet.beedroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button b = (Button) findViewById(R.id.btn1);
		b.setOnClickListener(mCorkyListener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	private OnClickListener	mCorkyListener	= new OnClickListener()
														{
															private boolean	clicked		= false;
															private int			numOfClicks	= 0;
															
															@Override
															public void onClick(View v)
															{
																
																Button b = (Button) v;
																if (!this.clicked)
																{
																	this.clicked = true;
																	b.setText("Me han clickado!");
																	this.numOfClicks++;
																}
																else
																{
																	b.setText("Me han clickado " + this.numOfClicks + " veces!");
																	this.numOfClicks++;
																}
															}
															
														};
	
}
