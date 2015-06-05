package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.cliente.estruturas.Cliente;
import com.smartcart.cliente.funcionalidades.UsuarioServicos;
import com.smartcart.cliente.persistencia.ClienteDAO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SigninActivity extends Activity {
	//private ClienteDAO clienteDB;
	
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		UsuarioServicos.setDao( new ClienteDAO(this) );
		UsuarioServicos.openDao();
		
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
				
				UsuarioServicos.openDao();
				Cliente cliente = UsuarioServicos.logarConta(email, senha);
				
				if (cliente == null){
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
					alertDialog.setTitle("E-mail ou senha incorretos");
					alertDialog.setMessage("Verifique os dados de sua conta");
					alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// here you can add functions
						}
					});
					
					alertDialog.setIcon(R.drawable.ic_launcher);
					alertDialog.show();
				}
				else{
					Intent intentMain = new Intent(SigninActivity.this,
							MainActivity.class);
					
					intentMain.putExtra("email", cliente.getPrimeiroNome());
					intentMain.putExtra("senha", senha);
					
					startActivity(intentMain);
					finish();
				}
			}
		});
		
		//SignUP activity -> clicar texto "criar conta"
		TextView signinCreateAccount = (TextView) findViewById(
				R.id.signin_clickable_signup);
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
		UsuarioServicos.closeDAO();
		super.onResume();
	}
	
	public void onPause() {
		UsuarioServicos.closeDAO();
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
}
