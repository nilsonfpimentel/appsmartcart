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
	
	/**
	 * Instância do banco de dados.
	 */
	private SQLiteDatabase sqliteDatabase;
	
	/**
	 * SqliteOpenHelper
	 */
	private ClienteDBHelper clienteDBHelper;
	
	private String colunas[] = { ClienteDBHelper.KEY_ID,
			ClienteDBHelper.KEY_FST_NOME, ClienteDBHelper.KEY_LST_NOME,
			ClienteDBHelper.KEY_EMAIL, ClienteDBHelper.KEY_SENHA };
	
	/**
	 * 
	 * @param context - Contexto da activity que requisita acesso ao banco de dados.
	 */
	public ClienteDAO(Context context) {
		clienteDBHelper = new ClienteDBHelper(context);
	}
	
	/**
	 * Abre conexão para escrita com o banco de dados.
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		sqliteDatabase = clienteDBHelper.getWritableDatabase();
	}
	
	/**
	 * Abre conexão para leitura com o banco de dados.
	 * 
	 * @throws SQLException
	 */
	public void openReadable() throws SQLException {
		sqliteDatabase = clienteDBHelper.getReadableDatabase();
	}
	
	/**
	 * Fecha a conexão com o banco de dados.
	 */
	public void close() {
		clienteDBHelper.close();
	}
	
	/**
	 * Recupera o conteúdo armazenado no objeto cursor e o retorna como uma instância da classe
	 * cliente.
	 * 
	 * @param cursor - Cursor apontando para alguma linha do banco de dados.
	 * @return - Cliente referenciado pelo cursor como instância da classe "Cliente".
	 */
	private Cliente recuperarClienteEmCursor(Cursor cursor) {
		long id = Long.parseLong(cursor.getString(0));
		String primeiroNome = cursor.getString(1);
		String ultimoNome = cursor.getString(2);
		String email = cursor.getString(3);
		String senha = cursor.getString(4);
		
		Cliente cliente = new Cliente(id, primeiroNome, ultimoNome, email,
				senha);
		
		return cliente;
	}
	
	/**
	 * Insere os atributos de cliente como pares de chave-valor em uma instância de
	 * ContentValues.
	 * 
	 * @param cliente - Cliente cujos atributos devem ser armazenados em um ContentValue.
	 * @return - Instância de ContentValues com as chave-valor inseridas.
	 */
	private ContentValues inserirEmContentValues(Cliente cliente) {
		ContentValues values = new ContentValues();
		
		values.put(ClienteDBHelper.KEY_FST_NOME, cliente.getPrimeiroNome());
		values.put(ClienteDBHelper.KEY_LST_NOME, cliente.getUltimoNome());
		values.put(ClienteDBHelper.KEY_EMAIL, cliente.getEmail());
		values.put(ClienteDBHelper.KEY_SENHA, cliente.getSenha());
		
		return values;
	}
	
	/**
	 * Verifica no banco de dados se existe algum cliente cadastrado com o email fornecido e
	 * retorna como resposta um booleano.
	 * @param email
	 * @return - boolean referente a se o email existe no banco de dados.
	 */
	public boolean verificarEmail(String email) {
		this.openReadable();
		
		String selecao = ClienteDBHelper.KEY_EMAIL + " = ?";
		String selecaoArgs[] = new String[] { email };
		
		Cursor cursor = sqliteDatabase.query(ClienteDBHelper.TABLE_NAME, colunas,
				selecao, selecaoArgs, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			this.close();
			return true;
		}
		
		this.close();
		return false;
	}
	
	/**
	 * Consulta no banco de dados por clientes contendo o email e senha passados como argumento
	 * e o retorna como uma instância da classe CLiente caso exista no banco de dados.
	 * 
	 * @param email - E-mail do cliente a ser consultado.
	 * @param senha - Senha do cliente a ser consultado.
	 * @return - Cliente do banco de dados com dado email e senha.
	 */
	public Cliente verificarDadosLogin(String email, String senha) {
		this.open();
		
		String selecaoArgs[] = new String[] {email, senha};
		
		String selecao = ClienteDBHelper.KEY_EMAIL + " = ? AND "
		+ ClienteDBHelper.KEY_SENHA + " = ?";
		
		Cursor cursor = sqliteDatabase.query(ClienteDBHelper.TABLE_NAME, colunas,
				selecao, selecaoArgs, null, null, null, null);
		
		if(cursor.moveToFirst()) {
			Cliente clienteRecuperado = this.recuperarClienteEmCursor(cursor);
			this.close();
			return clienteRecuperado;
		}
		
		return null;
	}
	
	/**
	 * Insere no banco de dados o cliente passado como argumento.
	 * 
	 * @param cliente - Cliente a ser inserido.
	 */
	public void incluirCliente(Cliente cliente) {
		if (this.verificarEmail(cliente.getEmail()) == false) {
			this.open();
			
			ContentValues values = this.inserirEmContentValues(cliente);
			sqliteDatabase.insert(ClienteDBHelper.TABLE_NAME, null, values);
			
			this.close();
		}
	}
	
	/**
	 * Recupera um cliente do banco de dados a partir do seu ID.
	 * 
	 * @param id - ID do cliente armazenado no banco.
	 * @return - Instância da classe Cliente referente à linha na tabela com o referido ID.
	 */
	public Cliente recuperarCliente(long id) {
		this.openReadable();
		
		Cursor cursor = sqliteDatabase.query(ClienteDBHelper.TABLE_NAME, colunas,
				ClienteDBHelper.KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			Cliente cliente = this.recuperarClienteEmCursor(cursor);
			this.close();
			return cliente;
		}
		
		return null;
	}
	
	/**
	 * Recupera do banco todas as linhas armazenadas e as retorna como uma lista de instâncias
	 * da classe Cliente.
	 * 
	 * @return - ArrayList de clientes armazenados no banco de dados.
	 */
	public List<Cliente> recuperarTodosClientes() {
		this.openReadable();
		
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		String selectQuery = "SELECT * FROM " + ClienteDBHelper.TABLE_NAME;
		Cursor cursor = sqliteDatabase.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()) {
			do{
				Cliente cliente = this.recuperarClienteEmCursor(cursor);
				listaClientes.add(cliente);
			} while (cursor.moveToNext());
		}
		
		return listaClientes;
	}
	
	/**
	 * Atualiza no banco o cliente passado como argumento, caso exista.
	 * 
	 * @param cliente - Cliente cujos atributos devem ser atualizados.
	 * @return - 
	 */
	public int atualizarCliente(Cliente cliente) {
		this.open();
		
		ContentValues values = this.inserirEmContentValues(cliente);
		int codigoAtualizacao = sqliteDatabase.update(ClienteDBHelper.TABLE_NAME, values,
				ClienteDBHelper.KEY_ID + " = ?",
				new String[] { String.valueOf(cliente.getId()) });
		
		this.close();
		return codigoAtualizacao;
	}
	
	/**
	 * Remove do banco de dados o cliente fornecido como argumento, caso exista.
	 * 
	 * @param cliente - Cliente a ser removido do banco.
	 */
	public void removerCliente(Cliente cliente) {
		this.open();
		
		sqliteDatabase.delete(ClienteDBHelper.TABLE_NAME,
				ClienteDBHelper.KEY_ID + " = ?",
				new String[] { String.valueOf(cliente.getId()) });
		
		sqliteDatabase.close();
	}
	
	/**
	 * Recupera do banco de dados o número total de clientes armazenados.
	 * 
	 * @return - Número de linhas na tabela do banco de dados.
	 */
	public int recuperarNumeroDeClientes() {
		this.openReadable();
		
		String contagemQuery = "SELECT * FROM " + ClienteDBHelper.TABLE_NAME;
		Cursor cursor = sqliteDatabase.rawQuery(contagemQuery, null);
		
		return cursor.getCount();
	}
}