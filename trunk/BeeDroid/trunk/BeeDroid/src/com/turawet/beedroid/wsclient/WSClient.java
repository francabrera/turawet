/**
 * 
 */
package com.turawet.beedroid.wsclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.turawet.beedroid.wsclient.beans.FormPreviewBean;
import com.turawet.beedroid.constants.Cte;

/**
 * @author nicopernas
 * 
 */
public class WSClient
{
	private static WSClient	wsClient	= null;
	private HttpTransportSE	transportSE;
	
	/**
	 * Cons
	 */
	private WSClient()
	{
		transportSE = new HttpTransportSE(Cte.WSClient.URL_TO_WSDL);
	}
	
	/**
	 * 
	 */
	private synchronized static void createInstance()
	{
		if (wsClient == null)
			wsClient = new WSClient();
	}
	
	/**
	 * 
	 * @return
	 */
	public static WSClient getInstance()
	{
		if (wsClient == null)
			createInstance();
		return wsClient;
	}
	
	public String getXmlformByNameVersion()
	{
		SoapObject request = new SoapObject(Cte.WSClient.NAMESPACE, Cte.WSClient.GET_XMLFORM_BY_NAME_VERSION);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		String xmlForm = null;
		
		try
		{
			transportSE.call(Cte.WSClient.GET_XMLFORM_BY_NAME_VERSION, envelope);
			
			SoapObject response = (SoapObject) envelope.getResponse();
			
			int i = 0;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		
		return xmlForm;
	}
	
	/**
	 * @author nicopernas
	 * 
	 * @param
	 * @return
	 *         Lista con los nombres y versiones de los formularios
	 *         disponibles para que el usuario se descargue.
	 * 
	 */
	public List<FormPreviewBean> getAllFormPreview()
	{
		SoapObject request = new SoapObject(Cte.WSClient.NAMESPACE, Cte.WSClient.GET_ALL_FORMS_PREVIEW);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		// Lista de previas de formularios que vamos a devolver
		List<FormPreviewBean> allFormPreview = new ArrayList<FormPreviewBean>();
		
		// Hacemos la llamada al método remoto
		try
		{
			transportSE.call(Cte.WSClient.GET_ALL_FORMS_PREVIEW, envelope);
			
			// Obtenemos la respuesta del sobre SOAP
			SoapObject response = (SoapObject) envelope.getResponse();
			
			int countOfFormPreview = response.getPropertyCount();
			
			// Pasamos el resultado a objetos FormPreviewBean, los metemos
			// en la lista para devolver.
			for (int i = 0; i < countOfFormPreview; i++)
			{
				SoapObject formPreview = (SoapObject) response.getProperty(i);
				String formName = formPreview.getProperty("name").toString();
				String formVersion = formPreview.getProperty("version").toString();
				allFormPreview.add(new FormPreviewBean(formName, formVersion));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		return allFormPreview;
	}
}
