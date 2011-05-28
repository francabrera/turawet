/**
 * 
 */
package com.turawet.beedroid.constants;

/**
 * @author nicopernas
 * 
 */
public final class Cte
{
	/**
	 *
	 */
	
	public static final class FormIdentificationBean
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
		public static final String	XML									= "xml";
		public static final int		TIMEOUT								= 10000;
	}
	
	public static final class Tags
	{
		public static final String	REPOSITORY_URL	= "REPOSITORY_URL";
		
	}
	
	public static final class DataBase
	{
		public static final int		DATABASE_VERSION	= 1;
		public static final String	DATABASE_NAME		= "BeeDroidDataBase";
		public static final String	FORMS_INFO_TABLE	= "FORMS_INFO_TABLE";
		public static final String	NAME					= "NAME";
		public static final String	VERSION				= "VERSION";
		public static final String	XML					= "XML";
	}
	
	public static final class Parser
	{
		public static final String	FORM		= "form";
		public static final String	SECTION	= "section";
		public static final String	TEXT		= "text";
		public static final String	DATE		= "date";
		public static final String	NAME		= "name";
	}
}
