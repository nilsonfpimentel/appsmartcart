/**
 * 
 */
package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.funcionalidades.UsuarioServicos;
import com.smartcart.cliente.persistencia.ClienteDAO;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author PLACEHOLDER
 *
 */
public class UpdateActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		
		UsuarioServicos.openDao();
		
		final EditText primeiroNomeInput = (EditText) findViewById(
				R.id.update_first_name_input);
		
		final EditText ultimoNomeInput = (EditText) findViewById(
				R.id.update_last_name_input);
		
		final EditText emailInput = (EditText) findViewById(
				R.id.update_email_input);
		
		final EditText senhaInput = (EditText) findViewById(
				R.id.update_password_input);
		
		Button signupOkButton = (Button) findViewById(
				R.id.update_clickable_create_account_button);
		
		signupOkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String primeiroNome = primeiroNomeInput.getEditableText().toString();
				String ultimoNome = ultimoNomeInput.getEditableText().toString();
				String email = emailInput.getEditableText().toString();
				String senha = senhaInput.getEditableText().toString();
				
				Cliente cliente = preencheCampos(primeiroNome, ultimoNome, email, senha);
				
				UsuarioServicos.atualizarDados(cliente);
				finish();
			}
		});
	}
	
	private Cliente preencheCampos(String primeiroNome, String ultimoNome, String email,
			String senha) {
		Cliente cliente = new Cliente(primeiroNome, ultimoNome, email, senha);
		return cliente;
	}
	
	public void onResume() {
		UsuarioServicos.setDao(new ClienteDAO(this));
		UsuarioServicos.openDao();
		super.onResume();
	}
	
	public void onPause() {
		UsuarioServicos.closeDAO();
		super.onPause();
	}
}
