/**
 * 
 */
package com.turawet.beedroid.xml.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.turawet.beedroid.beans.InstanceBean;

/**
 * @class XmlToViewsParser
 * 
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @autor Romén Rodríguez Gil
 * 
 */
public class XmlToBeansParser
{
	/**
	 *
	 */
	private XmlToBeansParserHandler	handler;
	
	public XmlToBeansParser(String xml) throws SAXException, ParserConfigurationException, IOException
	{
		this(new ByteArrayInputStream(xml.getBytes("UTF-8")));
	}
	
	/**
	 * 
	 * @param in
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public XmlToBeansParser(InputStream in) throws SAXException, ParserConfigurationException, IOException
	{
		handler = new XmlToBeansParserHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader xmlReader = parser.getXMLReader();
		xmlReader.setContentHandler(handler);
		InputSource inputSource = new InputSource(in);
		xmlReader.parse(inputSource);
	}

	/**
	 * 
	 * @return
	 */
	public InstanceBean getInstance()
	{
		return handler.getInstance();
	}
	
	
	/**
	 * Método que mostrará todo el proceso de parsing por el LOG
	 * -------- BORRAR AL FINAL ------
	 */
//	public void showParse()
//	{
//		InstanceBean instance = handler.getInstance();
//		List<SectionBean> sections = instance.getSections();
//        for (SectionBean section : sections) {
//        	List<SectionChildBean> sectionChildren = section.getSectionChildren();
//        	/* Must differ if it is a group or a field */
//        	for (SectionChildBean sectionChild : sectionChildren) {
//                Log.d("Campo de la instancia: ", sectionChild.toString());
//                GenericInstanceFieldBean field = (GenericInstanceFieldBean)sectionChild;
//            	List<PropertyBean> properties = field.getFormField().getProperties();
//            	/* Must differ if it is a group or a field */
//            	for (PropertyBean property : properties) {
//                    Log.d("Propiedad del campo: ", property.toString());
//            	}
//        	}
//        }
//
//		
//	}
	
}
