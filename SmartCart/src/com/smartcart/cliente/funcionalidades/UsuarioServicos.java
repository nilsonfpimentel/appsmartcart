package com.smartcart.cliente.funcionalidades;

import java.util.List;

import android.util.Log;

import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.persistencia.ClienteDAO;

/**
 * @author PLACEHOLDER
 *
 */
public abstract class UsuarioServicos {
	
	/**
	 * 
	 */
	private static ClienteDAO clienteDao;
	
	/**
	 * 
	 */
	private static Cliente clienteLogado;
	
	/**
	 * @param cliente
	 */
	public static void setClienteLogado(Cliente cliente) { clienteLogado = cliente; }
	
	/**
	 * @return
	 */
	public static Cliente getClienteLogado() { return clienteLogado; }
	
	/**
	 * @param dao
	 */
	public static void setDao(ClienteDAO dao) {
		clienteDao = dao;
	}
	
	/**
	 * 
	 */
	public static void openDao() {
		clienteDao.open();
	}
	
	/**
	 * 
	 */
	public static void closeDAO() {
		clienteDao.close();
	}
	
	/**
	 * @param email
	 * @param senha
	 * @return
	 */
	public static Cliente logarConta(String email, String senha) {
		Cliente clienteTemp = clienteDao.verificarDadosLogin(email, senha);
		
		if (clienteTemp != null) {
			setClienteLogado(clienteTemp);
		}
		
		return clienteTemp;
	}
	
	/**
	 * 
	 * @param email
	 * @param senha
	 * @return
	 */
	public static boolean login(String email, String senha) {
		Cliente clienteTemp = clienteDao.verificarDadosLogin(email, senha);
		
		if (clienteTemp != null) {
			setClienteLogado(clienteTemp);
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	public static void logout() {
		setClienteLogado(null);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String recuperarPrimeiroNome() {
		return clienteLogado.getPrimeiroNome();
	}
	
	/**
	 * @param dao
	 * @param email
	 * @return
	 */
	public static boolean verificarDisponibilidadeEmail(ClienteDAO dao, String email) {
		return dao.verificarEmail(email); 
	}
	
	/**
	 * @param email
	 * @return
	 */
	public static boolean checaEmailCadastrado(String email) {
		return clienteDao.verificarEmail(email); 
	}
	
	/**
	 * @param cliente
	 */
	public static void cadastrarCliente(Cliente cliente) {
		clienteDao.incluirCliente(cliente);
	}
	
	/**
	 * 
	 * @param cliente
	 */
	private static void insereAtributosAusentes(Cliente cliente) {
		
		if (cliente.getPrimeiroNome().length() <= 0) {
			cliente.setPrimeiroNome(clienteLogado.getPrimeiroNome());
			Log.d("UsrServ: insereAtt", "primeiroNome >> Ausente");
		}
		
		if (cliente.getUltimoNome().length() <= 0) {
			cliente.setUltimoNome(clienteLogado.getUltimoNome());
			Log.d("UsrServ: insereAtt", "sobreNome >> Ausente");
		}
		
		if (cliente.getEmail().length() <= 0) {
			cliente.setEmail(clienteLogado.getEmail());
			Log.d("UsrServ: insereAtt", "email >> Ausente");
		}
		
		if (cliente.getSenha().length() <= 0) {
			cliente.setSenha(clienteLogado.getSenha());
			Log.d("UsrServ: insereAtt", "senha >> Ausente");
		}
	}
	
	/**
	 * 
	 * @param cliente
	 * @return
	 */
	public static boolean atualizarDados(Cliente cliente) {
		insereAtributosAusentes(cliente);
		
		if (verificaValidadeCampos(cliente) == 0) {
			clienteDao.atualizarCliente(cliente);
			setClienteLogado(cliente);
			
			Log.d("UsrServ: cliente:", "priNome: " + cliente.getPrimeiroNome());
			Log.d("UsrServ: cliente:", "ultNome: " + cliente.getUltimoNome());
			Log.d("UsrServ: cliente:", "email: " + cliente.getEmail());
			Log.d("UsrServ: cliente:", "senha: " + cliente.getSenha());

			return true;
		}
		return false;
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	public static boolean verificaCamposVazios(Cliente cliente) {
		if (cliente.getPrimeiroNome().length() <= 0) { return true; }
		if (cliente.getUltimoNome().length() <= 0) { return true; }
		if (cliente.getEmail().length() <= 0) { return true; }
		if (cliente.getSenha().length() <= 0) { return true; }
		
		return false;
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	public static int verificaValidadeCampos(Cliente cliente) {
		int validade = 0;
		
		if(verificaCamposVazios(cliente)) { return -1; }
		
		return validade;
	}
	
	public static void imprimeTodos() {
		Log.d("Reading: ", "Reading all contacts.."); 
        List<Cliente> contacts = clienteDao.recuperarTodosClientes();       
         
        for (Cliente cn : contacts) {
            String log = "fN: "+cn.getPrimeiroNome() + " ,lN: " + cn.getUltimoNome() + " ,E: " + cn.getEmail() + ", S: " + cn.getSenha();
            // Writing Contacts to log
            Log.d("CL: ", log);
        }
	}
}
