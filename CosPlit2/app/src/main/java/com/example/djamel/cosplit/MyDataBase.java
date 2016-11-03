package com.example.djamel.cosplit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by djamel on 03/11/2016.
 */

public class MyDataBase extends SQLiteOpenHelper {

    //creation de base de données
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyDataBase.db";

    public static final String TABLE_House = "House";

    public static final String COLUMN_House_ID = "houseid";
    public static final String COLUMN_House_NAME = "housename";
    public static final String COLUMN_House_Code = "housecode";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_House+ " (" +COLUMN_House_ID + " INTEGER PRIMARY KEY, " + COLUMN_House_NAME + " TEXT, " + COLUMN_House_Code + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_House;
    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    //insertion de donnée dans la base
    public Boolean insertdata( String nom,int code)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COLUMN_House_NAME,nom);
        contentvalues.put(COLUMN_House_Code,code);

        long result = db.insert(TABLE_House,null,contentvalues);
        if (result == -1)

            return false;
        else
            return true;
    }

    //affichage de donnée
    public Cursor Afficher()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //cursor class random read write access
        Cursor res =  db.rawQuery("select * from "+TABLE_House,null);
        return  res;

    }
}
