package com.example.flashkey;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.example.flashkey.DatabaseConstants.TABLE_NAME;
import static com.example.flashkey.DatabaseConstants.KEY_NAME;
import static com.example.flashkey.DatabaseConstants.PASSWORD;




public class KeyDetail extends Activity {
	private static String TAG = "KeyDetail";
	private String id;
	private TextView nameView, passwordView ;
	private String name, passwordString;
	private SQLiteDatabase db;
	
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		id = intent.getExtras().getString("ID");
		setContentView(R.layout.key_detail);
		
		ActionBar actionBar = getActionBar();
        
        
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Key Details");
        
        nameView = (TextView) findViewById(R.id.detail_name);
		passwordView = (TextView) findViewById(R.id.detail_password);
		
		db = MainActivity.dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
	               "SELECT " + KEY_NAME + ", " + PASSWORD  +
	               " FROM " + TABLE_NAME + 
	               " WHERE _ID=?", new String[]{id});
		while (cursor.moveToNext()) {
		    name = cursor.getString(0);
		    passwordString = cursor.getString(1);
		}
		
		nameView.setText(name);
		passwordView.setText(passwordString);
		
	}
	
	
	static void deleteEvent(String id) {
		SQLiteDatabase db = MainActivity.dbhelper.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + "=" + id, null);
	}
  // menu for delete and edit key
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.key_option, menu);
	    return true;
	  } 
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {

	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case android.R.id.home :{
	    	     this.finish();
	    	     overridePendingTransition(R.anim.right_in, R.anim.left_out);
	         }
	         break;
	    
	    case R.id.delete_key:
	         deleteEvent(id);
	         KeyListFragment.mKeyListFragment.refresh();
	         this.finish();
	         overridePendingTransition(R.anim.right_in, R.anim.left_out);
	      break;
	      
	    case R.id.edit_key:{
	    	Intent intent = new Intent (this, EditKeyActivity.class);	
			intent.putExtra("ID", id);
			
			startActivity(intent);
			overridePendingTransition(R.anim.right_in, R.anim.left_out);
	    }
	    	break;
	    
	    default:
	      break;
	    }

	    return true;
	  } 

}
