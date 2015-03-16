package com.example.flashkey;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class SetPreferenceSettingActivity extends Activity {

	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);
	  
	  getFragmentManager().beginTransaction().replace(android.R.id.content,
	                new PrefSetting()).commit();
	  
	  getActionBar().setDisplayHomeAsUpEnabled(true);
	 }
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	     // Respond to the action bar's Up/Home button
	     case android.R.id.home:
	         NavUtils.navigateUpFromSameTask(this);
	         overridePendingTransition(R.anim.left_in, R.anim.right_out);
	         return true;
	     }
	     return super.onOptionsItemSelected(item);
	 }
	}
