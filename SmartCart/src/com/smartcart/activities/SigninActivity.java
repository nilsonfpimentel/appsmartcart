package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.cliente.funcionalidades.UsuarioServicos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends Activity {
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		UsuarioServicos.resetLogin();
		
		final EditText emailEditText = (EditText) findViewById(
				R.id.signin_email_input);
		
		final EditText senhaEditText = (EditText) findViewById(
				R.id.signin_password_input);
		
		//Logar conta -> clicar botão "Entrar"
		Button signinOkButton = (Button) findViewById(R.id.signin_ok_button);
		signinOkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String email = emailEditText.getEditableText().toString();
				String senha = senhaEditText.getEditableText().toString();

				if (!verificaCamposPreenchidos( new String[]{email, senha} )) {
					Toast.makeText(context, getString(R.string.error_empty_fields),
							Toast.LENGTH_SHORT).show();
				}
				else if (!UsuarioServicos.checaEmailCadastrado(email)) {
					Toast.makeText(context, getString(R.string.error_email_not_registered),
							Toast.LENGTH_LONG).show();
				}
				else if (!UsuarioServicos.login(email, senha)) {
					Toast.makeText(context, getString(R.string.error_wrong_email_or_password),
							Toast.LENGTH_SHORT).show();
				}
				else{
					Intent intentMain = new Intent(SigninActivity.this,
							MainActivity.class);
					
					startActivity(intentMain);
					finish();
				}
			}
		});
		
		//SignUP activity -> clicar texto "criar conta"
		TextView signinCreateAccount = (TextView) findViewById(R.id.signin_clickable_signup);
		signinCreateAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentSignup = new Intent(SigninActivity.this,
						SignupActivity.class);
				
				startActivity(intentSignup);
			}
		});
	}
	
	public void onResume() {
		UsuarioServicos.setDatabaseContext(context);
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean verificaCamposPreenchidos(String[] parametros) {
		for (String param : parametros) {
			if (param.length() <= 0) {
				return false;
			}
		}
		return true;
	}
}
