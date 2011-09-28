/**
 * 
 */
package com.turawet.beedroid.database;

import com.turawet.beedroid.constants.Cte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author nicopernas
 * 
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper
{
	/**
	 * @param context
	 */
	public DataBaseOpenHelper(Context context)
	{
		super(context, Cte.DataBase.DATABASE_NAME, null, Cte.DataBase.DATABASE_VERSION);
	}
	
	/**
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		StringBuffer sql = new StringBuffer();
		
		sql.append("CREATE TABLE ").
		append(Cte.DataBase.FORMS_INFO_TABLE).
		append(" (").
		append(Cte.DataBase.NAME).
		append(" TEXT, ").
		append(Cte.DataBase.VERSION).
		append(" TEXT, ").
		append(Cte.DataBase.XML).
		append(" TEXT);");
		
		db.execSQL(sql.toString());
	}
	
	/**
	 * 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		
	}
}
