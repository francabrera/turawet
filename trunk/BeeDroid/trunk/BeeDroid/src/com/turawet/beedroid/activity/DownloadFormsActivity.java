/**
 * 
 */
package com.turawet.beedroid.activity;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.turawet.beedroid.R;
import com.turawet.beedroid.adapter.DownloadFormsEfficientAdapter;
import com.turawet.beedroid.wsclient.beans.FormPreviewBean;
import com.turawet.beedroid.wsclient.WSClient;

import android.app.ListActivity;
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
			// Hacemos la llamada al WS y comprobamos los resultados obtenidos
			List<FormPreviewBean> listOfAvaliableForms = ws.getAllFormPreview();
			if (listOfAvaliableForms.isEmpty())
				gotAnyFormToDownload = false;
			setListAdapter(new DownloadFormsEfficientAdapter(this, listOfAvaliableForms));
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
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.downloads_forms_menu, menu);
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
	 * @return
	 */
	private boolean downloadAllSelectedForms()
	{
		List<FormPreviewBean> selectedForms = ((DownloadFormsEfficientAdapter) getListAdapter()).getSelectedFormsToDownload();
		if (selectedForms.isEmpty())
		{
			// TODO Mandar un mensaje diciendo que no se ha seleccionado ningún
			// formulario
			return false; // ¿?¿?¿?
		}
		else
		{
			
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
