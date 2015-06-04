package com.smartcart.activities;

import java.util.ArrayList;
import java.util.List;

import com.smartcart.R;
import com.smartcart.R.id;
import com.smartcart.R.layout;
import com.smartcart.R.menu;
import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.persistencia.ClienteDAO;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;	/*LogCat*/

public class SignupActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		//INICIO_TESTES//
		Log.d("ClienteDAO SQLite DB: ", "Abrindo...");
		ClienteDAO clienteDb = new ClienteDAO(this);
		Log.d("ClienteDAO SQLite DB: ", "Feito");
		
		Log.d("ClienteDAO: ", "Inserindo clientes...");
		for (int i = 0; i <= 10; i++) {
			String fstNome = "fst_Nome_" + i;
			String lstNome = "lst_Nome_" + i;
			String accNome = "acc_Nome_" + i;
			String accPass = "acc_Pass_" + i;
			
			Cliente cliente = new Cliente(fstNome, lstNome, accNome, accPass);
			clienteDb.incluirCliente(cliente);
		}
		Log.d("ClienteDAO: ", "Feito");
		
		Log.d("ClienteDAO: ", "Todos os clientes:");
		List<Cliente> listaClientes = clienteDb.recuperarTodosClientes();
		Log.d("ClienteDAO: ", "Feito");
		
		for (Cliente cl : listaClientes) {
			String log = "FST NOME: " + cl.getPrimeiroNome()
					+ ", LST NOME: " + cl.getUltimoNome()
					+ ", ACC NOME: " + cl.getContaNome()
					+ ", ACC SENHA: " + cl.getContaSenha();
			Log.d("Cliente: ", log);
		}
		
		int qtdClientes = clienteDb.recuperarNumeroDeClientes();
		Log.d("ClienteDAO: ", "QTD: " + qtdClientes);
		
		long id = 100;
		Cliente cliente = new Cliente();
		
		cliente.setContaNome("acc_Nome_1");
		cliente.setContaSenha("acc_Pass_3");
		
		Log.d("Consultando cadastro: ", cliente.getContaNome() + " : " 
				+ cliente.getContaSenha());
		
		Cliente res = clienteDb.verificarDadosLogin(cliente);
		
		if (res == null) {
			Log.d("resultado: ", "null");
		}
		else {
			Log.d("resultado: ", "true");
		}
		
		id = 555;
		Log.d("consultando: (OUT)", "ID: " + id);
		cliente = clienteDb.recuperarCliente(id);
		if (cliente == null) {
			Log.d("Resultado: ", "null");
		}
		else {
			Log.d("Resultado: ", "EXISTE");
		}
		
		id = 9;
		Log.d("consultando: (IN)", "ID: " + id);
		cliente = clienteDb.recuperarCliente(id);
		if (cliente == null) {
			Log.d("Resultado: ", "null");
		}
		else {
			Log.d("Resultado: ", "EXISTE");
		}
		
		cliente.setContaNome("HUE");
		
		Log.d("Atualizando ID: " + id, "contaNome: " + cliente.getContaNome());
		clienteDb.atualizarCliente(cliente);
		
		Log.d("consultando: (IN)", "ID: " + id);
		cliente = clienteDb.recuperarCliente(id);
		
		String log = "FST NOME: " + cliente.getPrimeiroNome()
				+ ", LST NOME: " + cliente.getUltimoNome()
				+ ", ACC NOME: " + cliente.getContaNome()
				+ ", ACC SENHA: " + cliente.getContaSenha();
		Log.d("resultado: ", log);
		
		id = 567;
		Log.d("Consultar nomes em uso:", "acc_Nome_" + id);
		boolean bool = clienteDb.verificarNomeDeConta("acc_Nome_" + id);
		
		if (bool == true) {
			Log.d("Resultado: ", "Já cadastrado");
		}
		else {
			Log.d("Resultado: ", "Disponível");
		}
		//FIM_TESTES//
	}
}
