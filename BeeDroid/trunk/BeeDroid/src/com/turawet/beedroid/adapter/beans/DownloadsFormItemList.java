/**
 * 
 */
package com.turawet.beedroid.adapter.beans;

import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

/**
 * @author nicopernas
 * 
 */
public class DownloadsFormItemList implements Checkable
{
	/**
	 * 
	 * @author nicopernas
	 * 
	 */
	private TextView	formName;
	private TextView	formVersion;
	
	public DownloadsFormItemList(TextView formName, TextView formVersion, CheckBox check)
	{
		this.formName = formName;
		this.formVersion = formVersion;
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
	
	/* (non-Javadoc)
	 * @see android.widget.Checkable#isChecked()
	 */
	@Override
	public boolean isChecked()
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.widget.Checkable#setChecked(boolean)
	 */
	@Override
	public void setChecked(boolean arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.widget.Checkable#toggle()
	 */
	@Override
	public void toggle()
	{
		// TODO Auto-generated method stub
		
	}
	
}
