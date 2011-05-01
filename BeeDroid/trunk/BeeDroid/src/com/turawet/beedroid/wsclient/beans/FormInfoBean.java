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
	private FormIdentificationBean	formPreview;
	private String				xml;
	
	public FormInfoBean(FormIdentificationBean formPreview, String xml)
	{
		this.formPreview = formPreview;
		this.xml = xml;
	}
	
	/**
	 * @return the formPreview
	 */
	public FormIdentificationBean getFormPreview()
	{
		return formPreview;
	}
	
	/**
	 * @param formPreview
	 *           the formPreview to set
	 */
	public void setFormPreview(FormIdentificationBean formPreview)
	{
		this.formPreview = formPreview;
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
