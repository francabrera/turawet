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

import com.turawet.beedroid.wsclient.beans.FormIdentification;
import com.turawet.beedroid.wsclient.beans.FormIdentification;
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
		for (FormInfoBean formInfo : formsToSave)
		{
			values.put(Cte.DataBase.NAME, formInfo.getFormId().getName());
			values.put(Cte.DataBase.VERSION, formInfo.getFormId().getVersion());
			values.put(Cte.DataBase.XML, formInfo.getXml());
			db.insertOrThrow(Cte.DataBase.FORMS_INFO_TABLE, null, values);
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FormIdentification> getSavedFormsIdentification()
	{
		List<FormIdentification> listOfSavedFormsId = new ArrayList<FormIdentification>();
		
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
				FormIdentification FormIdentification = new FormIdentification(name, version);
				listOfSavedFormsId.add(FormIdentification);
			}
			while (c.moveToNext());
		}
		c.close();
		return listOfSavedFormsId;
	}
	
	/**
	 * @param form
	 * @return
	 */
	public FormInfoBean getFormInfo(FormIdentification form)
	{
		SQLiteDatabase sqliteDatabase = dbAccessor.getReadableDatabase();
		String selection = Cte.DataBase.NAME + " = ? AND " + Cte.DataBase.VERSION + " = ?";
		
		Cursor c = sqliteDatabase.query(Cte.DataBase.FORMS_INFO_TABLE, new String[]
		{ Cte.DataBase.XML }, selection, new String[]
		{ form.getName(), form.getVersion() }, null, null, null);
		
		String xml = null;
		if (c.moveToFirst())
		{
			int xmlColumn = c.getColumnIndex(Cte.DataBase.XML);
			xml = c.getString(xmlColumn);
		}
		c.close();
		return new FormInfoBean(form, xml);
	}
}
