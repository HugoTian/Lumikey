package com.example.flashkey;



import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Camera;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends FragmentActivity {
	private ViewPager pager;
	
	public static InputMethodManager imm; 
	private ActionBar actionBar;
	
	
	//system setting
	public static Boolean torch_mode;
	public static String primary_password="1234";
	public static Boolean lockScreen=false;
	public static Boolean lockApp=false;
	public static String securityLevelString; 
	
	public static DatabaseHelper dbhelper = null;
	
	private Menu mainMenu;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//lock the screen
		if(lockScreen)
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		
		dbhelper = new DatabaseHelper(this);
		
		//set action bar
        actionBar = getActionBar();
        actionBar.setIcon(R.drawable.actionbar);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        
        
        Tab tab = actionBar.newTab();
        tab.setText("Transmission");
        tab.setTabListener(new TabListener<TorchKey>(this, "TORCH KEY", TorchKey.class) {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				pager.setCurrentItem(1);
				overridePendingTransition(R.anim.left_in, R.anim.right_out);
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
		});
        actionBar.addTab(tab);
        
        tab = actionBar.newTab();
        tab.setText("KEY LIST");
        tab.setTabListener(new TabListener<KeyListFragment>(this, "TORCH KEY", KeyListFragment.class) {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				pager.setCurrentItem(0);
				overridePendingTransition(R.anim.left_in, R.anim.right_out);
				
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
		});
        actionBar.addTab(tab);
        
        // load the system setting 
        loadPref();
        
        // page adapter 
        
        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        
        // pager transmorm effect
        
        pager.setPageTransformer(false, new ViewPager.PageTransformer() {
			
			@Override
			public void transformPage(View arg0, float arg1) {
				// TODO Auto-generated method stub
				arg0.setRotationY(arg1 * -30);
				
			}
		});
        
       
        
        //pager view on change listener
        pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if(pager.getCurrentItem()==0){
					
					actionBar.setSelectedNavigationItem(0);
				}
				else{
					actionBar.setSelectedNavigationItem(1);
					
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				if(pager.getCurrentItem()==0){
					
					actionBar.setSelectedNavigationItem(0);
				}
				else{
					
					actionBar.setSelectedNavigationItem(1);
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				if(pager.getCurrentItem()==0){
					
					
				}
				else{
					
					
				}
			}
		});
        
        // initiate setting for Button
     	
        imm  = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        
	}


	private void systemSetting() {
		 Intent intent = new Intent();
	     intent.setClass(MainActivity.this, SetPreferenceSettingActivity.class);
	     startActivityForResult(intent, 0); 
	     overridePendingTransition(R.anim.right_in, R.anim.left_out);
	}
	private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

            case 0: return TorchKey.newInstance();
            case 1: return KeyListFragment.newInstance();
            default: return TorchKey.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }       
    }
	private void loadPref(){
		  SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		  torch_mode = mySharedPreferences.getBoolean("checkbox_preference", false);
		  primary_password = mySharedPreferences.getString("edittext_preference", "");

		  lockScreen=mySharedPreferences.getBoolean("lock_screen", false);
		  lockApp = mySharedPreferences.getBoolean("lock_app", false);
    }
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			TorchKey.isSent=true;
			break;
		case  KeyEvent.KEYCODE_VOLUME_UP :
			TorchKey.isSent=false;
			break;
			
		case KeyEvent.KEYCODE_BACK:{

        	Log.d("CDA", "onBackPressed Called");
     	   //torchKey.cam.release();
     	    Intent startMain = new Intent(Intent.ACTION_MAIN);
     	    startMain.addCategory(Intent.CATEGORY_HOME);
     	    TorchKey.cam.release();
     	    TorchKey.cam=null;
     	   // startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     	    
     	    startActivity(startMain);	
     	   this.finish();
		  }
			break;
//		 case KeyEvent.KEYCODE_MENU :
//			 if (mainMenu !=null) {
//	                mainMenu.performIdentifierAction(R.id.action_setting, 0);
//	            }
//			 break;
		default:
			break;
		}
       
        return true;
    }
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		switch (keyCode){
		 case KeyEvent.KEYCODE_MENU :
			 if (mainMenu !=null) {
	                mainMenu.performIdentifierAction(R.id.action_setting, 0);
	            }
			 break;
		}
		return true;
		
	}
	
	// action bar menu
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    mainMenu=menu;
	    return true;
	  } 
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {

	    switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case R.id.action_new:
	    	Intent intent = new Intent (this,AddKeyActivity.class);	
			
			startActivity(intent);
			overridePendingTransition(R.anim.left_in,R.anim.right_out);
	      break;
	    
	    case R.id.action_setting:
	    	systemSetting();
	    	break;
	    case R.id.action_about_us:
	    {
            Intent aboutUsIntent = new Intent (this,AboutUsActivity.class);	
			startActivity(aboutUsIntent);
			overridePendingTransition(R.anim.left_in,R.anim.right_out);
	    }
	    default:
	      break;
	    }

	    return true;
	  } 
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
	    private Fragment mFragment;
	    private final Activity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;

	    /** Constructor used each time a new tab is created.
	      * @param activity  The host Activity, used to instantiate the fragment
	      * @param tag  The identifier tag for the fragment
	      * @param clz  The fragment's Class, used to instantiate the fragment
	      */
	    public TabListener(Activity activity, String tag, Class<T> clz) {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	    }

	    /* The following are each of the ActionBar.TabListener callbacks */

	    public void onTabSelected(Tab tab, FragmentTransaction ft) {
	        // Check if the fragment is already initialized
	        
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	        
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) {
	        // User selected the already selected tab. Usually do nothing.
	    }
	}
	protected void onDestory(){

		if(TorchKey.cam!=null){
		       TorchKey.cam.release();
		       TorchKey.cam = null;
       }
    }
	  @Override
	    protected void onResume() {
	        super.onResume();
	        if(TorchKey.cam==null)
	        TorchKey.cam = android.hardware.Camera.open();
	        lockApp = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("lock_app", false);
	        //KeyListFragment.mUndoBarController.hideUndoBar(true);
	    }


	    @Override
	    protected void onPause() {
	        super.onPause();
	        
	        if (TorchKey.cam != null) {
	            TorchKey.cam.release();
	            TorchKey.cam = null;
	        }
	    }
	
	
}
