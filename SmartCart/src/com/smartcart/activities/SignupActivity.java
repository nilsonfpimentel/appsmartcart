package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.funcionalidades.UsuarioServicos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends Activity {
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		final EditText primeiroNomeInput = (EditText) findViewById(
				R.id.signup_first_name_input);
		
		final EditText ultimoNomeInput = (EditText) findViewById(
				R.id.signup_last_name_input);
		
		final EditText emailInput = (EditText) findViewById(
				R.id.signup_email_input);
		
		final EditText senhaInput = (EditText) findViewById(
				R.id.signup_password_input);
		
		final EditText senhaInputConf = (EditText) findViewById(
				R.id.signup_password_conf_input);
		
		Button signupOkButton = (Button) findViewById(
				R.id.signup_clickable_create_account_button);
		
		signupOkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String primeiroNome = primeiroNomeInput.getEditableText().toString();
				String ultimoNome = ultimoNomeInput.getEditableText().toString();
				String email = emailInput.getEditableText().toString();
				String senha = senhaInput.getEditableText().toString();
				String senhaConfirmacao = senhaInputConf.getEditableText().toString();

				String listaParametros[] = new String[] {primeiroNome, ultimoNome, email,
						senha, senhaConfirmacao};

				Cliente cliente = preencheCampos(primeiroNome, ultimoNome, email, senha);

				if (!verificaCamposPreenchidos(listaParametros)) {
					Toast.makeText(context, getString(R.string.error_empty_fields),
							Toast.LENGTH_SHORT).show();
				}
				else if(UsuarioServicos.checaEmailCadastrado(email)) {
					Toast.makeText(context, getString(R.string.error_email_already_registered),
							Toast.LENGTH_SHORT).show();
				}
				else if(!senha.equals(senhaConfirmacao)) {
					Toast.makeText(context, getString(R.string.error_confirm_email_field),
							Toast.LENGTH_LONG).show();
				}
				else {
					UsuarioServicos.cadastrarCliente(cliente);
					finish();
				}
			}
		});
	}
	
	private Cliente preencheCampos(String primeiroNome, String ultimoNome, String email,
			String senha) {
		Cliente cliente = new Cliente(primeiroNome, ultimoNome, email, senha);
		return cliente;
	}
	
	private boolean verificaCamposPreenchidos(String[] parametros) {
		for (String param : parametros) {
			if (param.length() <= 0) {
				return false;
			}
		}
		return true;
	}
	
	public void onResume() {
		UsuarioServicos.setDatabaseContext(context);
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}
}
