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

import android.util.Base64;
import android.util.Log;

import com.turawet.beedroid.wsclient.beans.FormInfoBean;
import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;
import com.turawet.beedroid.constants.Cte;
import com.turawet.beedroid.constants.Cte.FormWsBean;

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
		transportSE = new HttpTransportSE(Cte.WSClient.URL_TO_WSDL, Cte.WSClient.TIMEOUT);
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
	
	/**
	 * 
	 * @param formToDownload
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public FormInfoBean getFormByNameVersion(FormIdentificationBean formToDownload) throws IOException, XmlPullParserException
	{
		SoapObject request = new SoapObject(Cte.WSClient.NAMESPACE, Cte.WSClient.GET_XMLFORM_BY_NAME_VERSION);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		String encodedName = Base64.encodeToString(formToDownload.getName().getBytes(), Base64.URL_SAFE);
		
		// Añadimos los parámetros a la llamada
		request.addProperty(FormWsBean.name, encodedName);
		request.addProperty(FormWsBean.version, formToDownload.getVersion());
		
		envelope.setOutputSoapObject(request);
		
		transportSE.call(Cte.WSClient.GET_XMLFORM_BY_NAME_VERSION, envelope);
		
		String xmlForm = envelope.getResponse().toString();
		
		return new FormInfoBean(formToDownload, xmlForm);
	}
	
	/**
	 * @author nicopernas
	 * 
	 * @param
	 * @return
	 *         Lista con los nombres y versiones de los formularios
	 *         disponibles para que el usuario se descargue.
	 * @throws XmlPullParserException
	 * @throws IOException
	 * 
	 */
	public List<FormIdentificationBean> getAllFormPreview() throws IOException, XmlPullParserException
	{
		SoapObject request = new SoapObject(Cte.WSClient.NAMESPACE, Cte.WSClient.GET_ALL_FORMS_PREVIEW);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		
		// Lista de previas de formularios que vamos a devolver
		List<FormIdentificationBean> allFormPreview = new ArrayList<FormIdentificationBean>();
		
		// Hacemos la llamada al método remoto
		transportSE.call(Cte.WSClient.GET_ALL_FORMS_PREVIEW, envelope);
		
		// Obtenemos la respuesta del sobre SOAP
		SoapObject response = (SoapObject) envelope.getResponse();
		
		int countOfFormPreview = response.getPropertyCount();
		
		// Pasamos el resultado a objetos FormPreviewBean, los metemos
		// en la lista para devolver.
		for (int i = 0; i < countOfFormPreview; i++)
		{
			SoapObject formId = (SoapObject) response.getProperty(i);
			String formName = formId.getProperty(FormWsBean.name).toString();
			String formVersion = formId.getProperty(FormWsBean.version).toString();
			allFormPreview.add(new FormIdentificationBean(formName, formVersion));
		}
		
		return allFormPreview;
	}
	
	public boolean uploadNewInstance(String instanceXml) throws IOException, XmlPullParserException
	{
		SoapObject request = new SoapObject(Cte.WSClient.NAMESPACE, Cte.WSClient.UPLOAD_NEW_INSTANCE);
		
		String encodedXml = Base64.encodeToString(instanceXml.getBytes(), Base64.URL_SAFE);
		
		request.addProperty(FormWsBean.xml, encodedXml);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);

		transportSE.debug = true;
		// Hacemos la llamada al método remoto
		transportSE.call(Cte.WSClient.UPLOAD_NEW_INSTANCE, envelope);

		// Obtenemos la respuesta del sobre SOAP
		Object response = envelope.getResponse();
		
		return Boolean.valueOf(response.toString());
	}
	
}
