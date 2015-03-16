package com.example.flashkey;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import static com.example.flashkey.DatabaseConstants.TABLE_NAME;
import static com.example.flashkey.DatabaseConstants.KEY_NAME;

import static com.example.flashkey.DatabaseConstants.PASSWORD;
import static com.example.flashkey.DatabaseConstants.KEY_TYPE;




public class AddKeyActivity extends Activity {
	
	private ClearableEditText keyName;
	private ClearableEditText KeyPassword;
	private ClearableEditText ReKeyPassword;
	
	private Spinner typeSpinner;

	
	private ActionBar actionBar;
	private Button confirmAdd, cancelAdd;
	
	private String nameString, passwordString;
	private String keyType = "Others" ;
	
	private static String[] typeStrings = {"Home","Office","Car","Others"};
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_key);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		actionBar = getActionBar();
		
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflator.inflate(R.layout.addkey_actionbar, null);
        actionBar.setCustomView(actionBarView);
        
       
        
		
		keyName = (ClearableEditText) findViewById(R.id.key_name);
		
		
		KeyPassword = (ClearableEditText) findViewById(R.id.key_password);
		ReKeyPassword = (ClearableEditText) findViewById(R.id.refill_password);
		
		typeSpinner = (Spinner) findViewById(R.id.keyType);
		
		confirmAdd = (Button) findViewById(R.id.confirm_add);
		cancelAdd = (Button) findViewById(R.id.cancel_add);
		confirmAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if(addEvent()) {
					KeyListFragment.mKeyListFragment.refresh();
					finish();
					overridePendingTransition(R.anim.right_in, R.anim.left_out);
				}
			}

			
		});
		cancelAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
				overridePendingTransition(R.anim.right_in, R.anim.left_out);
				
			}
		});
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int item = typeSpinner.getSelectedItemPosition();
				keyType = typeStrings[item];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	private boolean addEvent(){
		
        SQLiteDatabase db = MainActivity.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        nameString = keyName.getText().toString();
		if(nameString.equals("")) {	
			Toast.makeText(this, "Please input the key name", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		passwordString = KeyPassword.getText().toString();
		if(passwordString.length()>16){
			Toast.makeText(this, "Password should be less than 16 letter", Toast.LENGTH_SHORT).show();
			return false;
		}
			if(passwordString.equals("")) {	
				Toast.makeText(this, "Please input the key password", Toast.LENGTH_SHORT).show();
				return false;
			}
	   
		if(!passwordString.equals(ReKeyPassword.getText().toString())){
			Toast.makeText(this, "key password not match", Toast.LENGTH_SHORT).show();
			return false;
		}
		
        values.put(KEY_NAME, nameString);
        values.put(PASSWORD, passwordString);
        values.put(KEY_TYPE, keyType);
       
        db.insert(TABLE_NAME, null, values);
        return true;
    }
	
}

