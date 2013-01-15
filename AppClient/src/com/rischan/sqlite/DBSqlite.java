package com.rischan.sqlite;



import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSqlite extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "logpesan";

	// pesan table name
	private static final String TABLE_PESAN = "pesan";

	// pesan Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_PESAN = "pesan";


	public DBSqlite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PESAN_TABLE = "CREATE TABLE " + TABLE_PESAN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_PESAN + " TEXT" + ")";
		db.execSQL(CREATE_PESAN_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESAN);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void addPesan(String pesan) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PESAN, pesan); // Contact Name

		// Inserting Row
		db.insert(TABLE_PESAN, null, values);
		db.close(); // Closing database connection
	}


}
