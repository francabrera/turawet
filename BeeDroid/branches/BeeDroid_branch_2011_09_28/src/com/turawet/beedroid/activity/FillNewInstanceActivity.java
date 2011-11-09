/**
 * @title FillNewInstanceActivity
 * @authors Francisco Jose Cabrera Hernandez,
 *          Nicolas Pernas Maradei,
 *          Romen Rodriguez Gil
 * 
 */
package com.turawet.beedroid.activity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.android.maps.MapActivity;
import com.turawet.beedroid.R;
import com.turawet.beedroid.constants.Cte.FormWsBean;
import com.turawet.beedroid.constants.Cte.XmlTags;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.forms.FormInstance;
import com.turawet.beedroid.view.FieldViewFlipper;
import com.turawet.beedroid.view.support.SectionBookmarksCollection;
import com.turawet.beedroid.wsclient.WSClient;
import com.turawet.beedroid.wsclient.beans.FormIdentification;
import com.turawet.beedroid.xml.XmlConverter;
import com.turawet.beedroid.xml.parser.XmlToInstanceParser;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FillNewInstanceActivity extends MapActivity
{
	private FormInstance				instance;
	private FieldViewFlipper	flipper;
	
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
		}
		catch (Exception e)
		{
			// TODO Mostrar errores al usuario
			e.printStackTrace();
		}
	}
	
	private void initialize() throws Exception
	{
		instance = getInstanceFromXmlDefinitionForm();
		instance.setContext(this);
		flipper = new FieldViewFlipper(this);
		flipper.setFieldViewsToShow(instance.getFieldAsViews());
		setContentView(flipper);
	}
	
	/**
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	private FormInstance getInstanceFromXmlDefinitionForm() throws SAXException, ParserConfigurationException, IOException
	{
		XmlToInstanceParser parser = new XmlToInstanceParser();
		parser.parse(getXmlFormDefinitionAsInputStream());
		return parser.getInstance();
	}
	
	private InputStream getXmlFormDefinitionAsInputStream() throws IOException
	{
		return getAssets().open("formulario_for_test.xml");
		
		// String xml = getXmlFormDefinitionFromDB();
		// return new ByteArrayInputStream(xml.getBytes());
	}
	
	/**
	 * @return
	 */
	private String getXmlFormDefinitionFromDB()
	{
		FormIdentification form = createFormFromParameters();
		DataBaseManager db = DataBaseManager.getInstance(this);
		return db.getFormInfo(form).getXml();
	}
	
	/**
	 * @return
	 */
	private FormIdentification createFormFromParameters()
	{
		Bundle parameters = getIntent().getExtras();
		String name = parameters.getString(FormWsBean.name);
		String version = parameters.getString(FormWsBean.version);
		return new FormIdentification(name, version);
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
	
	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent data)
	// {
	// if (resultCode == Activity.RESULT_OK)
	// {
	// Bundle extras = data.getExtras();
	// Bitmap bitmap = (Bitmap) extras.get(InstanceBeanCte.data);
	// if (requestCode == InstanceBeanCte.GALLERY_IMAGE)
	// instanceManager.addImageToGallery(bitmap, flipper);
	// else if (requestCode == InstanceBeanCte.SINGLE_IMAGE)
	// instanceManager.addImage(bitmap, flipper);
	// }
	// }
	//
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
		
		final SectionBookmarksCollection sectionBookmarksCollection = instance.getSectionBookmarks();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sectionBookmarksCollection.sectionsNames());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		listOfSectionsView.setAdapter(adapter);
		
		listOfSectionsView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id)
			{
				int page = sectionBookmarksCollection.getSectionPageAt(position);
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
	 * @throws IOException
	 */
	private boolean saveAndSendInstance()
	{
		try
		{
			XmlConverter converter = new XmlConverter(XmlTags.namespace.toString());
			instance.makeFieldsReadValuesFromViews();
			instance.convertToXml(converter);
			String instanceXml = converter.endDocumentAndGetTranslation();
//			String instanceXml = instance.translateToXml();
			Log.d("", instanceXml);
			// WSClient ws = WSClient.getInstance();
			// boolean result = ws.uploadNewInstance(instanceXml);
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
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			finish();
		}
		
		return true;
	}
	
	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}
}
