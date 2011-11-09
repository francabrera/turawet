/**
 * 
 */
package com.turawet.beedroid.wsclient.beans;

/**
 * @author nicopernas
 * 
 */
public class FormInfoBean
{
	/**
	 *
	 */
	private FormIdentification	formId;
	private String					xml;
	
	public FormInfoBean(FormIdentification formId, String xml)
	{
		this.formId = formId;
		this.xml = xml;
	}
	
	/**
	 * @return the formId
	 */
	public FormIdentification getFormId()
	{
		return formId;
	}
	
	/**
	 * @param formId
	 *           the formId to set
	 */
	public void setFormId(FormIdentification formId)
	{
		this.formId = formId;
	}
	
	/**
	 * @return the xml
	 */
	public String getXml()
	{
		return xml;
	}
	
	/**
	 * @param xml
	 *           the xml to set
	 */
	public void setXml(String xml)
	{
		this.xml = xml;
	}
}
