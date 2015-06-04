package com.smartcart.cliente.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.smartcart.cliente.estruturas.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ClienteDAO {
	private SQLiteDatabase database;
	private ClienteDBHelper clienteDBHelper;
	
	private String colunas[] = { ClienteDBHelper.KEY_ID,
			ClienteDBHelper.KEY_FST_NOME, ClienteDBHelper.KEY_LST_NOME,
			ClienteDBHelper.KEY_ACC_NOME, ClienteDBHelper.KEY_ACC_PSW };
	
	public ClienteDAO(Context context) {
		clienteDBHelper = new ClienteDBHelper(context);
	}
	
	
	public void open() throws SQLException {
		database = clienteDBHelper.getWritableDatabase();
	}
	
	public void openReadable() throws SQLException {
		database = clienteDBHelper.getReadableDatabase();
	}
	
	public void close() {
		clienteDBHelper.close();
	}
	
	private Cliente recuperarClienteEmCursor(Cursor cursor) {
		long id = Long.parseLong(cursor.getString(0));
		String primeiroNome = cursor.getString(1);
		String ultimoNome = cursor.getString(2);
		String contaNome = cursor.getString(3);
		String contaSenha = cursor.getString(4);
		
		Cliente cliente = new Cliente(id, primeiroNome, ultimoNome, contaNome,
				contaSenha);
		
		return cliente;
	}
	
	private ContentValues inserirEmContentValues(Cliente cliente) {
		ContentValues values = new ContentValues();
		
		values.put(ClienteDBHelper.KEY_FST_NOME, cliente.getPrimeiroNome());
		values.put(ClienteDBHelper.KEY_LST_NOME, cliente.getUltimoNome());
		values.put(ClienteDBHelper.KEY_ACC_NOME, cliente.getContaNome());
		values.put(ClienteDBHelper.KEY_ACC_PSW, cliente.getContaSenha());
		
		return values;
	}
	
	public boolean verificarNomeDeConta(String nomeConta) {
		this.openReadable();
		
		String selecao = ClienteDBHelper.KEY_ACC_NOME + " = ?";
		String selecaoArgs[] = new String[] { nomeConta };
		
		Cursor cursor = database.query(ClienteDBHelper.TABLE_NAME, colunas,
				selecao, selecaoArgs, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			return true;
		}
		
		return false;
	}
	
	public Cliente verificarDadosLogin(Cliente clienteConsulta) {
		this.open();
		
		String contaNome = clienteConsulta.getContaNome();
		String contaSenha = clienteConsulta.getContaSenha();
		
		String selecaoArgs[] = new String[] {contaNome, contaSenha};
		
		String selecao = ClienteDBHelper.KEY_ACC_NOME + " = ? AND "
				+ClienteDBHelper.KEY_ACC_PSW + " = ?";
		
		Cursor cursor = database.query(ClienteDBHelper.TABLE_NAME, colunas,
				selecao, selecaoArgs, null, null, null, null);
		
		if(cursor.moveToFirst()) {
			return this.recuperarClienteEmCursor(cursor);
		}
		
		return null;
	}
	
	public void incluirCliente(Cliente cliente) {
		this.open();
		
		ContentValues values = this.inserirEmContentValues(cliente);
		database.insert(ClienteDBHelper.TABLE_NAME, null, values);
		database.close();
	}
	
	public Cliente recuperarCliente(long id) {
		this.openReadable();
		
		Cursor cursor = database.query(ClienteDBHelper.TABLE_NAME, colunas,
				ClienteDBHelper.KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			Cliente cliente = this.recuperarClienteEmCursor(cursor);
			return cliente;
		}
		
		return null;
	}
	
	public List<Cliente> recuperarTodosClientes() {
		this.openReadable();
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		String selectQuery = "SELECT * FROM " + ClienteDBHelper.TABLE_NAME;
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do{
				Cliente cliente = this.recuperarClienteEmCursor(cursor);
				listaClientes.add(cliente);
			} while (cursor.moveToNext());
		}
		
		return listaClientes;
	}
	
	public int atualizarCliente(Cliente cliente) {
		this.open();
		
		ContentValues values = this.inserirEmContentValues(cliente);
		
		return database.update(ClienteDBHelper.TABLE_NAME, values,
				ClienteDBHelper.KEY_ID + " = ?",
				new String[] { String.valueOf(cliente.getId()) });
	}
	
	public void removerCliente(Cliente cliente) {
		this.open();
		
		database.delete(ClienteDBHelper.TABLE_NAME,
				ClienteDBHelper.KEY_ID + " = ?",
				new String[] { String.valueOf(cliente.getId()) });
		
		database.close();
	}
	
	public int recuperarNumeroDeClientes() {
		this.openReadable();
		String contagemQuery = "SELECT * FROM " + ClienteDBHelper.TABLE_NAME;
		Cursor cursor = database.rawQuery(contagemQuery, null);
		return cursor.getCount();
	}
}