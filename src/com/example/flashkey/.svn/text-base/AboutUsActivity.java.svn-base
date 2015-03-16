package com.example.flashkey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AboutUsActivity extends Activity{
	private ListView mListView;
	private ArrayAdapter<String> adapter;
	String[] values = new String[] {
			"Logan",
			"Hugo",
			"Bryan",
			"Micky",
			"Ricky",
			"Josh",
			
			"stephnie",
			"lexie"};
	Integer[] imageId = new Integer[]{R.drawable.logan,R.drawable.hugo,R.drawable.bryan,R.drawable.micky,R.drawable.ricky,R.drawable.josh,R.drawable.stephanie,R.drawable.lexie};
	
	
 @Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about_us);
	
	getActionBar().setDisplayHomeAsUpEnabled(true);
	
    mListView=(ListView) findViewById(R.id.listView);
    
    CustomList adapter = new
            CustomList(this, values, imageId);
        
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        
                    }
                });
   
}

public static AboutUsActivity newInstance() {

    AboutUsActivity f = new AboutUsActivity();
    return f;
}
 
@Override
public boolean onOptionsItemSelected(MenuItem item) {

  switch (item.getItemId()) {
  // action with ID action_refresh was selected
  case android.R.id.home:
  	    this.finish();
		overridePendingTransition(R.anim.right_in,R.anim.left_out);
    break;
  		}
  	return false; 
	}
}