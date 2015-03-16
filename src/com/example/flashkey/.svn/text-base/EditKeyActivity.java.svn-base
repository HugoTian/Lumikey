package com.example.flashkey;

import static android.provider.BaseColumns._ID;
import static com.example.flashkey.DatabaseConstants.KEY_TYPE;
import static com.example.flashkey.DatabaseConstants.TABLE_NAME;
import static com.example.flashkey.DatabaseConstants.KEY_NAME;
import static com.example.flashkey.DatabaseConstants.PASSWORD;

import java.util.ArrayList;



import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
public class EditKeyActivity extends Activity {
	private String id;
	private ClearableEditText keyName;
	private ClearableEditText KeyPassword;
	private ClearableEditText ReKeyPassword;
	
    private SQLiteDatabase db;
	
	private ActionBar actionBar;
	private Button confirmAdd, cancelAdd;
	
	private Spinner mSpinner;
	
	private String nameString, passwordString,keyTypeString;
	
	private static ArrayList<String> typeStrings = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		id = intent.getExtras().getString("ID");
		setContentView(R.layout.add_key);
		
		ActionBar actionBar = getActionBar();
		actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = inflator.inflate(R.layout.addkey_actionbar, null);
        actionBar.setCustomView(actionBarView);
        
        typeStrings.add("Home");
        typeStrings.add("Office");
        typeStrings.add("Car");
        typeStrings.add("Others");
        
		
		keyName = (ClearableEditText) findViewById(R.id.key_name);
		
		
		KeyPassword = (ClearableEditText) findViewById(R.id.key_password);
		ReKeyPassword = (ClearableEditText) findViewById(R.id.refill_password);
		
		mSpinner = (Spinner) findViewById(R.id.keyType);
		
		
		confirmAdd = (Button) findViewById(R.id.confirm_add);
		cancelAdd = (Button) findViewById(R.id.cancel_add);
        
        
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Key Details");
        
       
		
		db = MainActivity.dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
	               "SELECT " + KEY_NAME + ", " + PASSWORD  + ", " +KEY_TYPE +
	               " FROM " + TABLE_NAME + 
	               " WHERE _ID=?", new String[]{id});
		 int nameColumn = cursor.getColumnIndex(KEY_NAME);
         int passwordColumn = cursor.getColumnIndex(PASSWORD);
         int id = cursor.getColumnIndex(_ID);
         int type = cursor.getColumnIndex(KEY_TYPE);
		while (cursor.moveToNext()) {
		    nameString = cursor.getString(nameColumn);
		    passwordString = cursor.getString(passwordColumn);
		    
		    keyTypeString=cursor.getString(type);
		}
		
		keyName.setText(nameString);
		KeyPassword.setText(passwordString);
		int i = typeStrings.indexOf(keyTypeString);
		mSpinner.setSelection(i);
		
		
		confirmAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if(updateEvent()) {
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
		
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				int item = mSpinner.getSelectedItemPosition();
				keyTypeString = typeStrings.get(item);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		
	}
	private boolean updateEvent(){
        SQLiteDatabase db2 = MainActivity.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        nameString = keyName.getText().toString();
		if(nameString.equals("")) {	
			Toast.makeText(this, "Please input the key name", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		passwordString = KeyPassword.getText().toString();
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
        values.put(KEY_TYPE, keyTypeString);
       
        db.update(TABLE_NAME, values, "_ID=" +id, null);
        return true;
	}

}
