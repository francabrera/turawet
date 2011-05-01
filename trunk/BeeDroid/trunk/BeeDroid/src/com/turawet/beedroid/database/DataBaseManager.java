/**
 * 
 */
package com.turawet.beedroid.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.turawet.beedroid.wsclient.beans.FormIdentificationBean;
import com.turawet.beedroid.wsclient.beans.FormInfoBean;
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
	public boolean saveForms(List<FormInfoBean> formsToSave) throws SQLException
	{
		SQLiteDatabase db = dbAccessor.getWritableDatabase();
		ContentValues values = new ContentValues(formsToSave.size());
		for(FormInfoBean formInfo: formsToSave)
		{
			
			Log.d("DATABASE_MANAGER", "Name    -> " + formInfo.getFormPreview().getName());
			Log.d("DATABASE_MANAGER", "Version -> " + formInfo.getFormPreview().getVersion());
			Log.d("DATABASE_MANAGER", "Xml     -> " + formInfo.getXml());
			
			values.put(Cte.DataBase.NAME, formInfo.getFormPreview().getName());
			values.put(Cte.DataBase.VERSION,formInfo.getFormPreview().getVersion());
			values.put(Cte.DataBase.XML, formInfo.getXml());
			db.insertOrThrow(Cte.DataBase.FORMS_INFO_TABLE, null, values);
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FormIdentificationBean> getSavedFormsIdentification()
	{
		List<FormIdentificationBean> listOfSavedFormsId = new ArrayList<FormIdentificationBean>();
		
		SQLiteDatabase sqliteDatabase = dbAccessor.getReadableDatabase();
		Cursor c = sqliteDatabase.query(Cte.DataBase.FORMS_INFO_TABLE, new String[]
		{ Cte.DataBase.NAME, Cte.DataBase.VERSION }, null, null, null, null, Cte.DataBase.NAME);
		if (c.moveToFirst())
		{
			int nameColumn = c.getColumnIndex(Cte.DataBase.NAME);
			int versionColumn = c.getColumnIndex(Cte.DataBase.VERSION);
			do
			{
				String name = c.getString(nameColumn);
				String version = c.getString(versionColumn);
				FormIdentificationBean formIdentificationBean = new FormIdentificationBean(name, version);
				listOfSavedFormsId.add(formIdentificationBean);
			}
			while (c.moveToNext());
		}
		return listOfSavedFormsId;
	}

	/**
	 * @param form
	 * @return
	 */
	public boolean existForm(FormIdentificationBean form)
	{
		SQLiteDatabase sqliteDatabase = dbAccessor.getReadableDatabase();
		String selection = Cte.DataBase.NAME + " = ? AND " + Cte.DataBase.VERSION + " = ?";
		Cursor c = sqliteDatabase.query(Cte.DataBase.FORMS_INFO_TABLE, 
													new String[] { Cte.DataBase.NAME }, selection, 
													new String[] { form.getName(), form.getVersion() }, null, null, null);
		return c.moveToFirst();
	}
}
