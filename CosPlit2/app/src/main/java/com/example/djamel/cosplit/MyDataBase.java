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
    //Table House
    public static final String TABLE_House = "House";
    public static final String COLUMN_House_ID = "houseid";
    public static final String COLUMN_House_NAME = "housename";
    public static final String COLUMN_House_Code = "housecode";
    //Table Register
    public static final String TABLE_Register = "Register";
    public static final String COLUMN_Register_ID = "Registerid";
    public static final String COLUMN_Register_First_NAME = "RegisterFirstname";
    public static final String COLUMN_Register_Last_Name = "RegisterLastName";
    public static final String COLUMN_Register_Email = "RegisterEmail";
    public static final String COLUMN_Register_Password = "RegisterPassword";
    public static final String COLUMN_FK_House = "IDHouseFK";


    //CREATION DES TABLE DANS LA BASE MyDataBase
    //Table House
    private static final String SQL_CREATE_ENTRIES_House = "CREATE TABLE " + TABLE_House+ " (" +COLUMN_House_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_House_NAME + " TEXT, " + COLUMN_House_Code + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES_House = "DROP TABLE IF EXISTS " + TABLE_House;

    //Table Register
    private static final String SQL_CREATE_ENTRIES_Register = "CREATE TABLE " + TABLE_Register+ " (" +COLUMN_Register_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," +
            " " + COLUMN_Register_First_NAME + " TEXT, " + COLUMN_Register_Last_Name + " TEXT," +COLUMN_Register_Email +
            " TEXT, " + COLUMN_Register_Password  + " Text , "+ COLUMN_FK_House + " INTEGER NOT NULL)";
    private static final String SQL_DELETE_ENTRIES_Register = "DROP TABLE IF EXISTS " + TABLE_Register;

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_House);
        db.execSQL(SQL_CREATE_ENTRIES_Register);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_House);
        db.execSQL(SQL_DELETE_ENTRIES_Register);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    //insertion de donnée dans la base table house
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

    //insertion de donnée dans la base table house
    public Boolean insertdataRegister( String nom,String prenom,String email, String password,int idhouse)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COLUMN_Register_First_NAME,nom);
        contentvalues.put(COLUMN_Register_Last_Name,prenom);
        contentvalues.put(COLUMN_Register_Email,email);
        contentvalues.put(COLUMN_Register_Password,password);
        contentvalues.put(COLUMN_FK_House,idhouse);
        long result = db.insert(TABLE_Register,null,contentvalues);
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

    //fonction pour afficher le house id
    public int getlastid(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultset = db.rawQuery("Select * from "+TABLE_House,null);
        resultset.moveToLast();
        int idhouse = resultset.getInt(0);
        return idhouse;

    }

}
