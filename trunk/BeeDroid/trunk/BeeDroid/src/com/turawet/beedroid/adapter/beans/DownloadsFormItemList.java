/**
 * 
 */
package com.turawet.beedroid.adapter.beans;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class DownloadsFormItemList
{
	/**
	 * 
	 * @author nicopernas
	 * 
	 */
	private TextView	formName;
	private TextView	formVersion;
	private CheckBox	check;
	
	public DownloadsFormItemList(TextView formName, TextView formVersion, CheckBox check)
	{
		this.formName = formName;
		this.formVersion = formVersion;
		this.check = check;
	}
	
	/**
	 * @return the formName
	 */
	public TextView getFormName()
	{
		return formName;
	}
	
	/**
	 * @param formName
	 *           the formName to set
	 */
	public void setFormName(TextView formName)
	{
		this.formName = formName;
	}
	
	/**
	 * @return the formVersion
	 */
	public TextView getFormVersion()
	{
		return formVersion;
	}
	
	/**
	 * @param formVersion
	 *           the formVersion to set
	 */
	public void setFormVersion(TextView formVersion)
	{
		this.formVersion = formVersion;
	}
	
	/**
	 * @return the check
	 */
	public CheckBox getCheck()
	{
		return check;
	}
	
	/**
	 * @param check
	 *           the check to set
	 */
	public void setCheck(CheckBox check)
	{
		this.check = check;
	}
	
}
