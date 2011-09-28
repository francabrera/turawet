/**
 * @title FillNewInstanceActivity
 * @authors Francisco Jose Cabrera Hernandez,
 *          Nicolas Pernas Maradei,
 *          Romen Rodriguez Gil
 * 
 */
package com.turawet.beedroid.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import com.turawet.beedroid.R;
import com.turawet.beedroid.beans.InstanceBean;
import com.turawet.beedroid.constants.Cte.FormWsBean;
import com.turawet.beedroid.constants.Cte.InstanceBeanCte;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.parser.XmlToBeansParser;
import com.turawet.beedroid.view.BeanViewFlipper;
import com.turawet.beedroid.view.FieldView;
import com.turawet.beedroid.view.support.InstanceBeanManager;
import com.turawet.beedroid.wsclient.WSClient;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FillNewInstanceActivity extends Activity
{
	private InstanceBeanManager			instanceManager;
	private List<Pair<String, Integer>>	sectionsList;
	private BeanViewFlipper					flipper;
	
	/**
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		
		try
		{
			initialize();
			List<FieldView> fields = instanceManager.getAllSectionsAndFieldsViews(sectionsList);
			for (FieldView field : fields)
				flipper.addView(field);
			setContentView(flipper);
			// FieldView fieldView =
			// instanceManager.getNewTextFieldView((TextFieldBean)instance.getSections().get(0).getSectionChildren().get(0),
			// 0);
			// LinearLayout linear = new LinearLayout(this);
			// linear.addView(fieldView);
			//
			// HorizontalScrollView horizontal = new HorizontalScrollView(this);
			// horizontal.addView(linear);
			// setContentView(horizontal);
			
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
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * 
	 */
	private void initialize() throws SAXException, ParserConfigurationException, IOException
	{
		instanceManager = new InstanceBeanManager(this, getEmptyInstanceFromXmlDefinitionForm());
		flipper = new BeanViewFlipper(this);
		sectionsList = new ArrayList<Pair<String, Integer>>();
	}
	
	/**
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private InstanceBean getEmptyInstanceFromXmlDefinitionForm() throws SAXException, ParserConfigurationException, IOException
	{
		// InputStream xml = getAssets().open("formulario_breve_v1.xml");
		XmlToBeansParser parser = new XmlToBeansParser(getXmlFormDefinition());
		return parser.getInstance();
	}
	
	/**
	 * @return
	 */
	private String getXmlFormDefinition()
	{
		FormIdentificationBean form = createFormFromParameters();
		DataBaseManager db = DataBaseManager.getInstance(this);
		return db.getFormInfo(form).getXml();
	}
	
	/**
	 * @return
	 */
	private FormIdentificationBean createFormFromParameters()
	{
		Bundle parameters = getIntent().getExtras();
		String name = parameters.getString(FormWsBean.name);
		String version = parameters.getString(FormWsBean.version);
		return new FormIdentificationBean(name, version);
	}
	
	@Override
	public void onConfigurationChanged(Configuration conf)
	{
		super.onConfigurationChanged(conf);
		flipper.adjustPageWidth();
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
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == Activity.RESULT_OK)
		{
			Bundle extras = data.getExtras();
			Bitmap bitmap = (Bitmap) extras.get(InstanceBeanCte.data);
			if (requestCode == InstanceBeanCte.GALLERY_IMAGE)
				instanceManager.addImageToGallery(bitmap, flipper);
			else if (requestCode == InstanceBeanCte.SINGLE_IMAGE)
				instanceManager.addImage(bitmap, flipper);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		item.setEnabled(false);
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.save_n_send_instance:
				return saveAndSendInstance();
			case R.id.list_of_sections_of_instance:
				return showListOfSections();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * @return
	 */
	private boolean showListOfSections()
	{
		final Dialog sectionsDialog = new Dialog(this);
		sectionsDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		sectionsDialog.setTitle("Selecciona una sección");
		
		ListView listOfSectionsView = new ListView(this);
		
		List<String> sectionsNames = new ArrayList<String>();
		for (Pair<String, Integer> pair : sectionsList)
			sectionsNames.add(pair.first);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sectionsNames);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		listOfSectionsView.setAdapter(adapter);
		
		listOfSectionsView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id)
			{
				int page = sectionsList.get(position).second.intValue();
				flipper.smoothScrollToPage(page);
				sectionsDialog.dismiss();
			}
		});
		
		sectionsDialog.setContentView(listOfSectionsView);
		sectionsDialog.show();
		
		return true;
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
			// Log.d("", instanceXml);
			WSClient ws = WSClient.getInstance();
			boolean result = ws.uploadNewInstance(instanceXml);
			/*
			 * if (result)
			 * {
			 * AlertMaker.showMessage(new AlertDialog.Builder(this), R.string.ok,
			 * R.string.saved_sended_ok).show();
			 * }
			 * else
			 * {
			 * AlertMaker.showErrorMessage(new AlertDialog.Builder(this),
			 * R.string.saved_sended_not_ok).show();
			 * }
			 */
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
		finally
		{
			finish();
		}
		
		return true;
	}
}
