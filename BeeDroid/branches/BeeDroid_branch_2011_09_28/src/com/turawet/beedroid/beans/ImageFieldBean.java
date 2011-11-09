/**
 * 
 */
package com.turawet.beedroid.beans;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import android.util.Base64;
import android.util.Xml;

import com.turawet.beedroid.constants.Cte.InstanceCte;
import com.turawet.beedroid.constants.Cte.XmlEnumTags;
import com.turawet.beedroid.constants.Cte.XmlTags;

/**
 * @class TextFieldBean: Represents a TextField
 * 
 * @param instanceFieldId
 *           : [Inherited] The id of the Field
 * @param value
 *           : the text
 * @param formField
 *           : The FormField of the current InstanceField
 * @version 1.0
 * 
 * @author Francisco José Cabrera Hernández
 * @author Nicolás Pernas Maradei
 * @author Romén Rodríguez Gil
 * 
 */
public class ImageFieldBean extends GenericInstanceFieldBean
{
	private String	imageBase64Encoded;
	private String	imageName;
	
	/**
	 * @param order
	 * @param formField
	 */
	public ImageFieldBean(int order, FormFieldBean formField)
	{
		super(order, formField);
		imageBase64Encoded = "";
		imageName = "";
	}
	
	/**
	 * @param value
	 */
	public void setValue(ByteArrayOutputStream value)
	{
		if (value != null)
		{
			imageBase64Encoded = Base64.encodeToString(value.toByteArray(), Base64.DEFAULT);
			imageName = String.valueOf(System.currentTimeMillis()) + InstanceCte.JPEG_EXTENSION;
		}
	}
	
	@Override
	public void toXml(Writer writer) throws IllegalArgumentException, IllegalStateException, IOException
	{
		serializer = Xml.newSerializer();
		serializer.setOutput(writer);
		serializer.startTag(XmlTags.namespace, XmlEnumTags.field.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.id.toString());
		serializer.endTag(XmlTags.namespace, XmlEnumTags.id.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.value.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.filename.toString());
		serializer.text(imageName);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.filename.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.binary.toString());
		serializer.text(imageBase64Encoded);
		serializer.endTag(XmlTags.namespace, XmlEnumTags.binary.toString());
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.value.toString());
		serializer.startTag(XmlTags.namespace, XmlEnumTags.order.toString());
		serializer.text(String.valueOf(order));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.order.toString());
		
		serializer.startTag(XmlTags.namespace, XmlEnumTags.formfieldid.toString());
		serializer.text(String.valueOf(getFormField().getId()));
		serializer.endTag(XmlTags.namespace, XmlEnumTags.formfieldid.toString());
		
		serializer.endTag(XmlTags.namespace, XmlEnumTags.field.toString());
		serializer.flush();
	}
}
