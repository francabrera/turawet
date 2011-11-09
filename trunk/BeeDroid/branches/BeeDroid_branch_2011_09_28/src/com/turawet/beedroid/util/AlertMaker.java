/**
 * 
 */
package com.turawet.beedroid.util;

import com.turawet.beedroid.R;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * @author nicopernas
 *
 */
public class AlertMaker
{
	/**
	 * 
	 * @param builder
	 * @param message
	 * @return
	 */
	public static AlertDialog showAlertMessage(AlertDialog.Builder builder, int message)
	{
		return showMessage(builder, R.string.warning, message);
	}

	/**
	 * 
	 * @param builder
	 * @param message
	 * @return
	 */
	public static AlertDialog showErrorMessage(AlertDialog.Builder builder, int message)
	{
		return showMessage(builder, R.string.error, message);
	}

	/**
	 * 
	 * @param builder
	 * @param message
	 * @return
	 */
	public static AlertDialog showMessage(AlertDialog.Builder builder, int title, int message)
	{
		builder.setMessage(message)
		.setTitle(title)
		.setCancelable(false)
      .setNeutralButton(R.string.accept, new DialogInterface.OnClickListener() 
      {
          public void onClick(DialogInterface dialog, int id) {}
      });
		return builder.create();
	}

}
