package com.example.flashkey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class HelperActivity extends Activity{

	
	@Override
	public void onCreate(Bundle b){
	    super.onCreate(b);
//	    //determine what activity you want
//	    Log.d("hehe","hehe");
//	    if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("lock_app", false)){
//	    
//	    	startActivity(new Intent(this, HighSecurityLevel.class));
//	    }
//	    else {
//	    	startActivity(new Intent(this, MainActivity.class));
//		}
//	    finish();
	}
	 @Override
	    protected void onResume() {
	        super.onResume();
	        Log.d("jaha","jaja");
	        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("lock_app", false)){
	    	    
		    	startActivity(new Intent(this, HighSecurityLevel.class));
		    }
		    else {
		    	startActivity(new Intent(this, MainActivity.class));
			}
		    finish();

	        
	    }
	 @Override
	    protected void onRestart() {
	        super.onRestart();
	        Log.d("pppa","ppppp");
	        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("lock_app", false)){
	    	    
		    	startActivity(new Intent(this, HighSecurityLevel.class));
		    }
		    else {
		    	startActivity(new Intent(this, MainActivity.class));
			}
		    finish();

	        
	    }
	 
	
}
