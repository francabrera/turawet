/**
 * 
 */
package com.turawet.beedroid.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.turawet.beedroid.beans.FormPreviewBean;
import com.turawet.beedroid.constants.Cte;

/**
 * @author nicopernas
 * 
 */
public class DataBaseAccesor
{
	/**
	 *
	 */
	
	private static DataBaseAccesor	dataBaseAccesor	= null;
	private DataBaseOpenHelper			dbAccesor			= null;
	
	/**
	 * Cons
	 */
	private DataBaseAccesor(Context context)
	{
		dbAccesor = new DataBaseOpenHelper(context);
	}
	
	/**
	 * 
	 */
	private synchronized static void createInstance(Context context)
	{
		if (dataBaseAccesor == null)
			dataBaseAccesor = new DataBaseAccesor(context);
	}
	
	/**
	 * 
	 * @return
	 */
	public static DataBaseAccesor getInstance(Context context)
	{
		if (dataBaseAccesor == null)
			createInstance(context);
		return dataBaseAccesor;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FormPreviewBean> getSavedFormsPreview()
	{
		List<FormPreviewBean> listFormPreview = new ArrayList<FormPreviewBean>();
		
		SQLiteDatabase sqliteDatabase = dbAccesor.getReadableDatabase();
		Cursor c = sqliteDatabase.query(Cte.DataBase.FORMS_PREVIEW, new String[]
		{ Cte.DataBase.NAME, Cte.DataBase.VERSION }, null, null, null, null, Cte.DataBase.NAME);
		if (c.moveToFirst())
		{
			int nameColumn = c.getColumnIndex(Cte.DataBase.NAME);
			int versionColumn = c.getColumnIndex(Cte.DataBase.VERSION);
			do
			{
				String name = c.getString(nameColumn);
				String version = c.getString(versionColumn);
				FormPreviewBean formPreviewBean = new FormPreviewBean(name, version);
				listFormPreview.add(formPreviewBean);
			}
			while (c.moveToNext());
		}
		return listFormPreview;
	}
}
