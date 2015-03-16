package com.example.flashkey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.example.flashkey.DatabaseConstants.TABLE_NAME;
import static com.example.flashkey.DatabaseConstants.KEY_NAME;
import static com.example.flashkey.DatabaseConstants.PASSWORD;
import static com.example.flashkey.DatabaseConstants.KEY_TYPE;
public class DatabaseHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "event.db";
    private final static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	
        final String INIT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                                  _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  
                                  KEY_NAME + " TEXT, " +
                                  PASSWORD + " TEXT, " +
                                  KEY_TYPE + " TEXT);"; 
                                  
    	
        db.execSQL(INIT_TABLE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
	
	
}

