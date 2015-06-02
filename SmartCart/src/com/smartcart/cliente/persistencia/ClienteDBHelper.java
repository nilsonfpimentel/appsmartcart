package com.smartcart.cliente.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClienteDBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Clientes.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "clientes";
	
	public static final String KEY_ID = "id";
	public static final String KEY_FST_NOME = "primeiro_nome";
	public static final String KEY_LST_NOME = "ultimo_nome";
	public static final String KEY_ACC_NOME = "cont_nome";
	public static final String KEY_ACC_PSW = "conta_senha";
	
	public ClienteDBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String CREATE_STATEMENT = "CREATE TABLE "
				+ TABLE_NAME + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_FST_NOME + " TEXT " + KEY_LST_NOME + " TEXT "
				+ KEY_ACC_NOME + " TEXT " + KEY_ACC_PSW + " TEXT);";
		
		db.execSQL(CREATE_STATEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}
