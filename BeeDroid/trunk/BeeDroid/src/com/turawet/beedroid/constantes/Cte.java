/**
 * 
 */
package com.turawet.beedroid.constantes;

/**
 * @author nicopernas
 * 
 */
public final class Cte
{
	/**
	 *
	 */
	
	public static final class formPreviewBean
	{
		public static final String	name		= "name";
		public static final String	version	= "version";
		
	}
	
	public static final class WSClient
	{
		public static final String	REPOSITORY_URL						= "192.168.0.10";
		public static final String	NAMESPACE							= "http://" + REPOSITORY_URL + "/ws_server/";
		public static final String	URL_TO_WSDL							= "http://" + REPOSITORY_URL + "/ws_server/service.wsdl";
		public static final String	GET_ALL_FORMS_PREVIEW			= "get_all_forms_preview";
		public static final String	GET_XMLFORM_BY_NAME_VERSION	= "get_xmlform_by_name_version";
	}
	
	public static final class Tags
	{
		public static final String	REPOSITORY_URL	= "REPOSITORY_URL";
		
	}
}
