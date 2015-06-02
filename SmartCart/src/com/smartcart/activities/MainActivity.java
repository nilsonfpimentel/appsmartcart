package com.smartcart.activities;

import com.smartcart.R;
import com.smartcart.R.id;
import com.smartcart.R.layout;
import com.smartcart.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bundle extras = getIntent().getExtras();
		String accountName = extras.getString("accountName");
		
		TextView accountNameTextView = (TextView) findViewById(
				R.id.main_welcome_user_label);
		
		accountNameTextView.setText(getString(
				R.string.main_welcome_user_text, accountName));
	}
}
