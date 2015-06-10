package com.smartcart.cliente.funcionalidades;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.persistencia.ClienteDAO;

/**
 * @author PLACEHOLDER
 *
 */
public abstract class UsuarioServicos {
	
	/**
	 * Objeto responsável pela manipulação do banco de dados.
	 */
	private static ClienteDAO clienteDao;
	
	/**
	 * Instância do cliente atualmente logado.
	 */
	private static Cliente clienteLogado;
	
	/**
	 * Método interno da classe de serviços de usuário para setar o objeto referente ao cliente
	 * para ser logado.
	 * 
	 * @param cliente
	 */
	private static void setClienteLogado(Cliente cliente) { clienteLogado = cliente; }
	
	/**
	 * Ajusta o contexto do banco de dados para o da atual activity em primeiro plano.
	 * 
	 * @param context
	 */
	public static void setDatabaseContext(Context context) {
		clienteDao = new ClienteDAO(context);
	}
	
	/**
	 * Recupera do banco de dados o cliente registrado com o e-mail e senha fornecidos, armaze-
	 * na tal cliente no atributo clienteLogado e retorna um booleano como resposta caso o log-
	 * in foi bem-sucedido ou não.
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
	 * Seta o cliente logado como null, utilizado como funcionalidade de logoff.
	 */
	public static void resetLogin() {
		setClienteLogado(null);
	}
	
	/**
	 * Recupera o atributo primeiroNome do cliente logado para funcionalidades como mensagem de
	 * boas-vindas.
	 * 
	 * @return
	 */
	public static String recuperarPrimeiroNome() {
		return clienteLogado.getPrimeiroNome();
	}
	
	/**
	 * @param email
	 * @return
	 */
	public static boolean checaEmailCadastrado(String email) {
		return clienteDao.verificarEmail(email); 
	}
	
	/**
	 * Cadastra o cliente recebido como argumento no banco de dados.
	 * 
	 * @param cliente
	 */
	public static void cadastrarCliente(Cliente cliente) {
		clienteDao.incluirCliente(cliente);
	}
	
	/**
	 * Atualiza os atributos do cliente atuamente logado com base nos atributos não-vazios do
	 * cliente passado como argumento.
	 * 
	 * @param cliente
	 */
	private static void atualizaAtributos(Cliente cliente) {
		
		if (cliente.getPrimeiroNome().length() > 0) {
			clienteLogado.setPrimeiroNome(cliente.getPrimeiroNome());
			Log.d("UsrServ: insereAtt", "primeiroNome >> Alterado");
		}
		
		if (cliente.getUltimoNome().length() > 0) {
			clienteLogado.setUltimoNome(cliente.getUltimoNome());
			Log.d("UsrServ: insereAtt", "sobreNome >> Alterado");
		}
		
		if (cliente.getEmail().length() > 0) {
			clienteLogado.setEmail(cliente.getEmail());
			Log.d("UsrServ: insereAtt", "email >> Alterado");
		}
		
		if (cliente.getSenha().length() > 0) {
			clienteLogado.setSenha(cliente.getSenha());
			Log.d("UsrServ: insereAtt", "senha >> Alterado");
		}
	}
	
	/**
	 * Atualiza os atributos do cliente logado por meio do método atualizarAtributos(arg) e em
	 * seguida atualiza o cliente no banco de dados.
	 * 
	 * @param cliente
	 * @return
	 */
	public static boolean atualizarDados(Cliente cliente) {
		atualizaAtributos(cliente);
		
		clienteDao.atualizarCliente(clienteLogado);
		
		Log.d("UsrServ: cliente:", "priNome: " + cliente.getPrimeiroNome());
		Log.d("UsrServ: cliente:", "ultNome: " + cliente.getUltimoNome());
		Log.d("UsrServ: cliente:", "email: " + cliente.getEmail());
		Log.d("UsrServ: cliente:", "senha: " + cliente.getSenha());

		return true;
	}
	
	/**
	 * APENAS PARA FINALIDADES DE DEPURAÇÃO, RESULTADOS APARECEM IMPRESSOS VIA LOGCAT.
	 */
	public static void imprimeTodos() {
		Log.d("Reading: ", "Reading all contacts.."); 
        List<Cliente> contacts = clienteDao.recuperarTodosClientes();       
         
        for (Cliente cn : contacts) {
            String log = "fN: "+cn.getPrimeiroNome() + " ,lN: " + cn.getUltimoNome() + " ,E: " + cn.getEmail() + ", S: " + cn.getSenha();
            
            Log.d("Cliente: ", log);
        }
	}
}
