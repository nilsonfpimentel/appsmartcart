package com.smartcart.activities;

import java.util.List;

import com.smartcart.R;
//import com.smartcart.R.id;
//import com.smartcart.R.layout;
//import com.smartcart.R.menu;
import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.persistencia.ClienteDAO;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	ClienteDAO clienteDB;
	
	Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		clienteDB = new ClienteDAO(context);
		clienteDB.open();
		
		Bundle extras = getIntent().getExtras();
		String accountName = extras.getString("email");
		
		TextView accountNameTextView = (TextView) findViewById(
				R.id.main_welcome_user_label);
		
		accountNameTextView.setText(getString(
				R.string.main_welcome_user_text, accountName));
		
		//INICIO_TESTES//
		Log.d("Qdt de clientes:", Long.toString(
				clienteDB.recuperarNumeroDeClientes() ));
		
		Log.d("clienteDB:", "Cadastrando...");
		
		Cliente cliente;
		for (int i = 1; i <= 10; i++) {
			String pNome = "p" + i;
			String uNome = "u" + i;
			String email = "e" + i;
			String senha = "s" + i;
			
			Log.d("Usuario: ",
					pNome + " : " + uNome + " : " + email + " : " + senha);
			
			cliente = new Cliente(pNome, uNome, email, senha);
			clienteDB.incluirCliente(cliente);
		}
		
		Log.d("Clientes: ", "Listando...");
		
		List<Cliente> listaClientes = clienteDB.recuperarTodosClientes();
		String log;
		for (Cliente cl : listaClientes) {
			log = cl.getPrimeiroNome() + ":" + cl.getUltimoNome() + ":"
					+ cl.getEmail() + ":" + cl.getSenha();
			
			Log.d("Cliente:", log);
		}
		//FIM_TESTES//
	}
}
