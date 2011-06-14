/**
 * 
 */
package com.turawet.beedroid.constants;

/**
 * Cte: Clase que contiene las constantes del proyecto
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public final class Cte
{
	/**
	 *
	 */
	
	public static enum FieldType
	{
		TEXT, DATE
	};
	
	public static enum XmlEnumTags
	{
		instance, meta, id, formid, author, user, creationdate, modificationdate, editable, fields, field, group, groupid, element, value, order, sections, section, formfieldid, day, month, year
	};
	
	public static final class XmlTags
	{
		public static final String	namespace	= "";
	};
	
	public static final class FormWsBean
	{
		public static final String	name		= "name";
		public static final String	version	= "version";
		public static final String	xml		= "xml";
	}
	
	public static final class WSClient
	{
		public static final String	REPOSITORY_URL						= "193.145.110.236";
		public static final String	NAMESPACE							= "http://" + REPOSITORY_URL + "/ws_server/";
		public static final String	URL_TO_WSDL							= "http://" + REPOSITORY_URL + "/ws_server/service.wsdl";
		public static final String	GET_ALL_FORMS_PREVIEW			= "get_all_forms_preview";
		public static final String	GET_XMLFORM_BY_NAME_VERSION	= "get_xmlform_by_name_version";
		public static final String	UPLOAD_NEW_INSTANCE				= "upload_new_instance";
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
