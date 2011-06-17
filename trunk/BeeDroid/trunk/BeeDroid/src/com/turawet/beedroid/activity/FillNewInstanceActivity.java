/**
 * @title FillNewInstanceActivity
 * @authors Francisco Jose Cabrera Hernandez,
 *          Nicolas Pernas Maradei,
 *          Romen Rodriguez Gil
 * 
 */
package com.turawet.beedroid.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import com.turawet.beedroid.R;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.constants.Cte.FormWsBean;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.parser.XmlToBeansParser;
import com.turawet.beedroid.util.AlertMaker;
import com.turawet.beedroid.view.BeanViewFlipper;
import com.turawet.beedroid.view.FieldView;
import com.turawet.beedroid.view.support.InstanceBeanManager;
import com.turawet.beedroid.wsclient.WSClient;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class FillNewInstanceActivity extends Activity
{
	private InstanceBeanManager instanceManager;

	BeanViewFlipper flipper;
	/**
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		
		try
		{
			Bundle parameters = getIntent().getExtras();
			String name = parameters.getString(FormWsBean.name);
			String version = parameters.getString(FormWsBean.version);
			FormIdentificationBean form = new FormIdentificationBean(name, version);
			DataBaseManager db = DataBaseManager.getInstance(this);
			/* This line loads the selected form */
			//String xml = db.getFormInfo(form).getXml();
			/* This line always load the asset form */
			InputStream xml = getAssets().open("formulario_breve_v1.xml");
			
			XmlToBeansParser parser;
			parser = new XmlToBeansParser(xml);
			InstanceBean instance = parser.getInstance();
			instanceManager = new InstanceBeanManager(this, instance);
			
			flipper = new BeanViewFlipper(this);
			List<FieldView> views = instanceManager.getAllInstanceViews();
			for (FieldView view : views)
				flipper.addView(view);
			setContentView(flipper);
		}
		catch (SAXException e1)
		{
			// TODO Mostrar errores al usuario
			e1.printStackTrace();
		}
		catch (ParserConfigurationException e1)
		{
			// TODO Mostrar errores al usuario
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			// TODO Mostrar errores al usuario
			e1.printStackTrace();
		}
	}
	
	/**
	 */
	@Override
	public void onBackPressed()
	{
		// TODO Esto se ejecutará cuando se presione volver. Habría que preguntar
		// que se va a hacer con la nueva instancia (guardar, descartar) si es que
		// no esta guardada
		
		Log.d(getClass().getName(), "BACK presionada...");
		super.onBackPressed();
	}
	
	/**
	 * @param menu
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.instance_menu, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.save_n_send_instance:
				return saveAndSendInstance();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * @return
	 */
	private boolean saveAndSendInstance()
	{
		
		try
		{
			instanceManager.readFieldValues(flipper.getChildAt(0));
			String instanceXml = instanceManager.instanceToXml();
			Log.d("",instanceXml);
			WSClient ws = WSClient.getInstance();
			boolean result = ws.uploadNewInstance(instanceXml);
			if(result)
			{
				AlertMaker.showMessage(new AlertDialog.Builder(this), R.string.ok, R.string.saved_sended_ok);
			}
			else
			{
				AlertMaker.showErrorMessage(new AlertDialog.Builder(this), R.string.saved_sended_not_ok);
			}
		}
		catch (IllegalArgumentException e)
		{
			// TODO Error del toXml
			e.printStackTrace();
		}
		catch (IllegalStateException e)
		{
			// TODO Error del toXml
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Error del toXml
			e.printStackTrace();
		}
		catch (XmlPullParserException e)
		{
			// TODO Error del WS
			e.printStackTrace();
		}
		finish();
		return true;
	}
}
