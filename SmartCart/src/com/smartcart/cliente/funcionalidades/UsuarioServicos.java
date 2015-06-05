package com.smartcart.cliente.funcionalidades;

import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.persistencia.ClienteDAO;

public abstract class UsuarioServicos {
	private static ClienteDAO clienteDao;
	
	private static Cliente clienteLogado;
	
	public static void setClienteLogado(Cliente cliente) { clienteLogado = cliente; }
	public static Cliente getClienteLogado() { return clienteLogado; }
	
	public static void setDao(ClienteDAO dao) {
		clienteDao = dao;
	}
	
	public static void openDao() {
		clienteDao.open();
	}
	public static void closeDAO() {
		clienteDao.close();
	}
	
	public static Cliente logarConta(ClienteDAO dao, String email,
			String senha) {
		
		Cliente clienteTemp = dao.verificarDadosLogin(email, senha);
		return clienteTemp;
	}
	
	public static Cliente logarConta(String email, String senha) {
		Cliente clienteTemp = clienteDao.verificarDadosLogin(email, senha);
		
		if (clienteTemp != null) { setClienteLogado(clienteTemp); }
		return clienteTemp;
	}
	
	public static boolean verificarDisponibilidadeEmail(ClienteDAO dao, String email) {
		return dao.verificarEmail(email); 
	}
	
	public static boolean checaEmailCadastrado(String email) {
		return clienteDao.verificarEmail(email); 
	}
	
	public static void cadastrarCliente(Cliente cliente) {
		clienteDao.incluirCliente(cliente);
	}
	
	public static boolean verificaCamposVazios(Cliente cliente) {
		if (cliente.getPrimeiroNome().length() <= 0) { return true; }
		if (cliente.getUltimoNome().length() <= 0) { return true; }
		if (cliente.getEmail().length() <= 0) { return true; }
		if (cliente.getSenha().length() <= 0) { return true; }
		
		return false;
	}
	
	public static int verificaValidadeCampos(Cliente cliente) {
		int validade = 0;
		
		if(verificaCamposVazios(cliente)) { return -1; }
		
		return validade;
	}
}
