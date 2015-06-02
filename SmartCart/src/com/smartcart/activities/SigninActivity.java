package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.R.id;
import com.smartcart.R.layout;
import com.smartcart.R.menu;

import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		final EditText accountNameEditText = (EditText) findViewById(
				R.id.signin_account_name_input);
		
		final EditText passwordEditText = (EditText) findViewById(
				R.id.signin_password_input);
		
		//Logar conta -> clicar botão "Entrar"
		Button signinOkButton = (Button) findViewById(R.id.signin_ok_button);
		signinOkButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String accountName = accountNameEditText.getEditableText()
						.toString();
				
				String password = passwordEditText.getEditableText().toString();
				
				Intent intentSignin = new Intent(SigninActivity.this,
						MainActivity.class);
				
				intentSignin.putExtra("accountName", accountName);
				intentSignin.putExtra("password", password);
				
				startActivity(intentSignin);
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

