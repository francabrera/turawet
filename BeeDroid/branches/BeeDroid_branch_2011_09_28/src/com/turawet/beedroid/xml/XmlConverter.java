package com.turawet.beedroid.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

public class XmlConverter
{
	private XmlSerializer	serializer;
	private Writer				writer;
	private String				namespace;
	
	public XmlConverter(String namespace) throws IllegalArgumentException, IllegalStateException, IOException
	{
		this.namespace = namespace;
		writer = new StringWriter();
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.setPrefix(XmlTags.prefix, this.namespace);
		startDocument();
	}
	
	final private void startDocument() throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer.startDocument(XmlTags.utf8, null);
	}
	
	public String endDocumentAndGetTranslation() throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer.endDocument();
		serializer.flush();
		return writer.toString();
	}
	
	final public void addElement(XmlEnumTags tagName, String value) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer.startTag(namespace, tagName.toString());
		serializer.text(value);
		serializer.endTag(namespace, tagName.toString());
	}
	
	final public void startElement(XmlEnumTags tagName) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer.startTag(namespace, tagName.toString());
	}
	
	final public void endElement(XmlEnumTags tagName) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer.endTag(namespace, tagName.toString());
	}
	
}
