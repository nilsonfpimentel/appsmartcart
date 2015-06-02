package com.smartcart.cliente.persistencia;

import com.smartcart.cliente.estruturas.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ClienteDAO {
	private SQLiteDatabase database;
	
	private final String TABLE_NAME = ClienteDBHelper.TABLE_NAME;
	
	private final String ID = ClienteDBHelper.KEY_ID;
	private final String FIRST_NAME = ClienteDBHelper.KEY_FST_NOME;
	private final String LAST_NAME = ClienteDBHelper.KEY_LST_NOME;
	private final String ACC_NAME = ClienteDBHelper.KEY_ACC_NOME;
	private final String ACC_SENHA = ClienteDBHelper.KEY_ACC_PSW;
	
	private String columns[] = { ClienteDBHelper.KEY_ID,
			ClienteDBHelper.KEY_FST_NOME, ClienteDBHelper.KEY_LST_NOME,
			ClienteDBHelper.KEY_ACC_NOME, ClienteDBHelper.KEY_ACC_PSW };
	
	private ClienteDBHelper clienteDBHelper;
	
	public ClienteDAO(Context context) {
		clienteDBHelper = new ClienteDBHelper(context);
	}
	
	public void open() throws SQLException {
		database = clienteDBHelper.getWritableDatabase();
	}
	
	public void close() {
		clienteDBHelper.close();
	}
	
	public void incluirCliente(Cliente cliente) {
		database = clienteDBHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(ID, cliente.getId());
		values.put(FIRST_NAME, cliente.getPrimeiroNome());
		values.put(LAST_NAME, cliente.getUltimoNome());
		values.put(ACC_NAME, cliente.getContaNome());
		values.put(ACC_SENHA, cliente.getContaSenha());
		
		database.insert(TABLE_NAME, null, values);
		database.close();
	}
	
	public Cliente recuperarCliente(int id) {
		database = clienteDBHelper.getReadableDatabase();
		
		Cursor cursor = database.query(TABLE_NAME, new String[] { ID,
	            FIRST_NAME, LAST_NAME, ACC_NAME, ACC_SENHA }, ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4));
		
		return cliente;
	}
}