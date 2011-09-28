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
		TEXT, DATE, RADIO, GEO, IMAGE, NUMERIC
	};
	
	public static enum XmlEnumTags
	{
		instance, meta, id, formid, author, user, creationdate, modificationdate, editable, fields, field, group, groupid, element, value, order, sections, section, formfieldid, day, month, year, property, name, version, type, label, required, option, geolocalized, filename, binary, geolocalization, longitude, latitude
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
		public static final String	REPOSITORY_URL						= "ec2-50-18-94-108.us-west-1.compute.amazonaws.com";
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
	
	public static final class InstanceBeanCte
	{
		public static final int		SINGLE_IMAGE	= 0x00;
		public static final int		GALLERY_IMAGE	= 0x01;
		public static final String	data				= "data";
		public static final int		JPEG_QUALITY	= 80;
		public static final String	JPEG_EXTENSION	= ".jpg";
	}
	
	public static final class Parser
	{
		public static final String	FORM		= "form";
		public static final String	SECTION	= "section";
		public static final String	TEXT		= "text";
		public static final String	DATE		= "date";
		public static final String	NAME		= "name";
	}
	
	public static final class MainIcon
	{
		public static final class Id
		{
			public static final int	FORMULARIO				= 0x00;
			public static final int	COMPLETAR_INSTANCIA	= 0x01;
			public static final int	CREAR_INTSANCIA		= 0x02;
			public static final int	OPCIONES					= 0x03;
			
		}
		
		public static final class Name
		{
			public static final String	FORMULARIO				= "Formularios";
			public static final String	COMPLETAR_INSTANCIA	= "Completar instancia";
			public static final String	CREAR_INTSANCIA		= "Crear instancia";
			public static final String	OPCIONES					= "Opciones";
			
		}
	}
}
