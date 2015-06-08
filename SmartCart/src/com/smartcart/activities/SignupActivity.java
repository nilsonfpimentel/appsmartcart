package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.funcionalidades.UsuarioServicos;
import com.smartcart.cliente.persistencia.ClienteDAO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity {
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		/**
		 * AlertDialog gen�rico para activity criar conta
		 */
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {}
		});
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setTitle("Falha na cria��o de nova conta");
		
//		UsuarioServicos.setDao( new ClienteDAO(this) );
		
		final EditText primeiroNomeInput = (EditText) findViewById(
				R.id.signup_first_name_input);
		
		final EditText ultimoNomeInput = (EditText) findViewById(
				R.id.signup_last_name_input);
		
		final EditText emailInput = (EditText) findViewById(
				R.id.signup_email_input);
		
		final EditText senhaInput = (EditText) findViewById(
				R.id.signup_password_input);
		
		Button signupOkButton = (Button) findViewById(
				R.id.signup_clickable_create_account_button);
		
		signupOkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String primeiroNome = primeiroNomeInput.getEditableText().toString();
				String ultimoNome = ultimoNomeInput.getEditableText().toString();
				String email = emailInput.getEditableText().toString();
				String senha = senhaInput.getEditableText().toString();
				
				Cliente cliente = preencheCampos(primeiroNome, ultimoNome, email, senha);
				
				if (UsuarioServicos.verificaValidadeCampos(cliente) == -1) {
					alertDialog.setMessage("Todos os campos devem ser preenchidos.");
					alertDialog.show();
				}
				else if(UsuarioServicos.checaEmailCadastrado(email)) {
					alertDialog.setMessage("Conta de E-mail j� cadastrada.");
					alertDialog.show();
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
