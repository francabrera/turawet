/**
 * 
 */
package com.turawet.beedroid.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.turawet.beedroid.wsclient.beans.FormPreviewBean;
import com.turawet.beedroid.constants.Cte;

/**
 * @author nicopernas
 * 
 */
public class DataBaseManager
{
	/**
	 *
	 */
	
	private static DataBaseManager	dataBaseManager	= null;
	private DataBaseOpenHelper			dbAccessor			= null;
	
	/**
	 * Cons
	 */
	private DataBaseManager(Context context)
	{
		dbAccessor = new DataBaseOpenHelper(context);
	}
	
	/**
	 * 
	 */
	private synchronized static void createInstance(Context context)
	{
		if (dataBaseManager == null)
			dataBaseManager = new DataBaseManager(context);
	}
	
	/**
	 * 
	 * @return
	 */
	public static DataBaseManager getInstance(Context context)
	{
		if (dataBaseManager == null)
			createInstance(context);
		return dataBaseManager;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FormPreviewBean> getSavedFormsPreview()
	{
		List<FormPreviewBean> listFormPreview = new ArrayList<FormPreviewBean>();
		
		SQLiteDatabase sqliteDatabase = dbAccessor.getReadableDatabase();
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
