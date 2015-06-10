package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.cliente.funcionalidades.UsuarioServicos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button mainAtualizarButton = (Button) findViewById(R.id.main_update_client_button);
		
		mainAtualizarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentUpdate = new Intent(MainActivity.this, UpdateActivity.class);
				startActivity(intentUpdate);
			}
		});
		
		Button mainLogoutButton = (Button) findViewById(R.id.main_logout_button);
		
		mainLogoutButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentUpdate = new Intent(MainActivity.this, SigninActivity.class);
				startActivity(intentUpdate);
				UsuarioServicos.resetLogin();
				finish();
			}
		});
		
	}
	
	public void onResume() {
		UsuarioServicos.setDatabaseContext(context);
		
		String accountName = UsuarioServicos.recuperarPrimeiroNome();
		TextView accountNameTextView = (TextView) findViewById(R.id.main_welcome_user_label);
		accountNameTextView.setText(getString(R.string.main_welcome_user_text, accountName));
		
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}
}
