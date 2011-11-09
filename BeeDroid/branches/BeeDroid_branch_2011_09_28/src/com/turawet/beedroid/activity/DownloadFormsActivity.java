/**
 * 
 */
package com.turawet.beedroid.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.turawet.beedroid.R;
import com.turawet.beedroid.adapter.DownloadFormsEfficientAdapter;
import com.turawet.beedroid.database.DataBaseManager;
import com.turawet.beedroid.util.AlertMaker;
import com.turawet.beedroid.wsclient.beans.FormIdentification;
import com.turawet.beedroid.wsclient.beans.FormInfoBean;
import com.turawet.beedroid.wsclient.WSClient;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class DownloadFormsActivity extends ListActivity
{
	private boolean	gotAnyFormToDownload	= true;
	
	/**
	 *
	 */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downloads_forms_layout);
		
		WSClient ws = WSClient.getInstance();
		
		try
		{
			// Hacemos la llamada al WS y eliminamos aquellos formularios que ya
			// estén
			// guardadaos en el teléfono, para que no se puedan volver a descargar.
			List<FormIdentification> avaliablesFormsToDownload = ws.getAllFormPreview();
			DataBaseManager db = DataBaseManager.getInstance(this);
			List<FormIdentification> savedForms = db.getSavedFormsIdentification();
			avaliablesFormsToDownload.removeAll(savedForms);
			if (avaliablesFormsToDownload.isEmpty())
				gotAnyFormToDownload = false;
			setListAdapter(new DownloadFormsEfficientAdapter(this, avaliablesFormsToDownload));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			gotAnyFormToDownload = false;
			TextView msgToDisplay = (TextView) findViewById(android.R.id.empty);
			String cantConnect = getString(R.string.cant_connect);
			msgToDisplay.setText(cantConnect);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			gotAnyFormToDownload = false;
			TextView msgToDisplay = (TextView) findViewById(android.R.id.empty);
			String gralError = getString(R.string.general_error);
			msgToDisplay.setText(gralError);
		}
		
	}
	
	/**
	 * 
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		((DownloadFormsEfficientAdapter) getListAdapter()).toggle(position);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		if (gotAnyFormToDownload)
		{
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.downloads_forms_menu, menu);
		}
		return gotAnyFormToDownload;
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
			case R.id.select_all:
				return selectAllFormsToDownload();
			case R.id.select_none:
				return selectNoneFormsToDownload();
			case R.id.download_selected:
				return downloadAllSelectedForms();
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean downloadAllSelectedForms()
	{
		// Obtenemos los formularios seleccionados para descargarse
		List<FormIdentification> selectedForms = ((DownloadFormsEfficientAdapter) getListAdapter()).getSelectedFormsToDownload();
		
		// Comprobamos que la lista no sea vacía
		if (selectedForms.isEmpty())
		{
			AlertMaker.showAlertMessage(new AlertDialog.Builder(this), R.string.no_forms_selected).show();
		}
		else
		{
			List<FormInfoBean> formsToSave = new ArrayList<FormInfoBean>();
			WSClient ws = WSClient.getInstance();
			boolean errors = false;
			// Descargamos todos los formularios y los almacenamos
			for (FormIdentification formId : selectedForms)
			{
				try
				{
					FormInfoBean formInfoBean = ws.getFormByNameVersion(formId);
					formsToSave.add(formInfoBean);
				}
				catch (Exception e)
				{
					AlertMaker.showErrorMessage(new AlertDialog.Builder(this), R.string.error_downloading_form).show();
					errors = true;
					e.printStackTrace();
				}
			}
			if (!errors)
			{
				// Guardamos los formularios en el dispositivo
				DataBaseManager dbManager = DataBaseManager.getInstance(this);
				try
				{
					dbManager.saveForms(formsToSave);
				}
				catch (SQLException s)
				{
					AlertMaker.showErrorMessage(new AlertDialog.Builder(this), R.string.error_saving_form).show();
				}
			}
			// Cerramos la activity
			finish();
		}
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectNoneFormsToDownload()
	{
		((DownloadFormsEfficientAdapter) getListAdapter()).uncheckAllItems();
		return true;
	}
	
	/**
	 * @return
	 */
	private boolean selectAllFormsToDownload()
	{
		((DownloadFormsEfficientAdapter) getListAdapter()).checkAllItems();
		return true;
	}
	
}
