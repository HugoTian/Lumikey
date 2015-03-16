package com.example.flashkey;

import static android.provider.BaseColumns._ID;
import static com.example.flashkey.DatabaseConstants.KEY_NAME;
import static com.example.flashkey.DatabaseConstants.KEY_TYPE;
import static com.example.flashkey.DatabaseConstants.PASSWORD;
import static com.example.flashkey.DatabaseConstants.TABLE_NAME;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class KeyListFragment extends Fragment  implements UndoBarController.UndoListener  {
	
	
	// databse 
	private static SQLiteDatabase db;
	 // listView for list of keys
	 private static ListView keyListView;
	 public  static ArrayList<String> idArrayList = new ArrayList<String>();
	 public  static ArrayList<String> keyNameArrayList = new ArrayList<String>();
	 public  static ArrayList<String> keyPasswordArrayList = new ArrayList<String>();
	 public  static ArrayList<String> keyTypeArrayList = new ArrayList<String>();
	 
	 public static KeyListFragment mKeyListFragment = null;
	 
	 private static Integer ImageList[] = new Integer[]{R.drawable.ic_home,R.drawable.ic_office,R.drawable.ic_car,R.drawable.ic_others};
	
	private static KeyListFragment f = new KeyListFragment();
	
	
	// swipe to delete and 
	private SwipeDismissListViewTouchListener mOnTouchListener;
	public UndoBarController mUndoBarController;
	
	private static   CustomListKeyContact  adapter;
	
	private static ListFragmentItemClickListener iItemClickListener;
	
	private static String undoName,undoType,undoPassword,undoId;
	
	
	//call back function 
	/**
	 * SwipeDismiss callback
	 * 
	 * Remove items, and show undobar
	 */
	public SwipeDismissListViewTouchListener.DismissCallbacks mCallback = new SwipeDismissListViewTouchListener.DismissCallbacks() {
		@Override
		public void onDismiss(ListView listView, int[] reverseSortedPositions) {

			String[] itemStrings=new String[reverseSortedPositions.length];
			int[] itemPositions=new int[reverseSortedPositions.length];
			int i=0;

			for (int position : reverseSortedPositions) {
				String itemString=adapter.getItem(position);
				int j = keyNameArrayList.indexOf(itemString);
				deleteEvent(idArrayList.get(j));
				
				undoName=keyNameArrayList.get(j);
				undoPassword=keyPasswordArrayList.get(j);
				undoType=keyTypeArrayList.get(j);
				
				adapter.remove(itemString);
				
				refresh();
				
			    

				itemStrings[i]=itemString;
				itemPositions[i]=position;
				i++;
			}
			adapter.notifyDataSetChanged();


			//Show UndoBar
			UndoItem itemUndo=new UndoItem(itemStrings,itemPositions);

			Resources res = getResources();
			String messageUndoBar = res.getQuantityString(R.plurals.items, reverseSortedPositions.length,reverseSortedPositions.length);

			mUndoBarController.showUndoBar(
                     false,
                     messageUndoBar,
                     itemUndo);
		}

		@Override
		public boolean canDismiss(int position) {
			return position <= adapter.getCount() - 1;
		}
	};
	
	/** An interface for defining the callback method */
	public interface ListFragmentItemClickListener {
		/**
		 * This method will be invoked when an item in the ListFragment is
		 * clicked
		 */
		void onListFragmentItemClick(View view, int position);
	}
	
	
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.key_contact, container, false);
         
         if(mKeyListFragment==null){
        	 mKeyListFragment=this;
         }
         if (mUndoBarController!=null)
 			mUndoBarController.onRestoreInstanceState(savedInstanceState);
         
         keyListView = (ListView) v.findViewById(R.id.listKey);
      	
      	
         //set the database 
         db = MainActivity.dbhelper.getReadableDatabase();
    	    Cursor c =db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
    	 

         int nameColumn = c.getColumnIndex(KEY_NAME);
         int passwordColumn = c.getColumnIndex(PASSWORD);
         int id = c.getColumnIndex(_ID);
         int type = c.getColumnIndex(KEY_TYPE);
         int i = 0;
         
         keyNameArrayList.clear();
         
         keyPasswordArrayList.clear();
         
         idArrayList.clear();
         keyTypeArrayList.clear();
         // Check if our result was valid.
         
         if (c.getCount() > 0 && c!=null) {
          // Loop through all Results
        	 c.moveToFirst();
          do {
           String Name = c.getString(nameColumn);
           String password = c.getString(passwordColumn);
           String key_idString =c.getString(id);
           String key_typeString = c.getString(type);
           Log.d("type", key_typeString);
           keyNameArrayList.add(i, Name);
           keyPasswordArrayList.add(i,password);
           idArrayList.add(i,key_idString);
           keyTypeArrayList.add(i, key_typeString);
           i++;
          }while(c.moveToNext());
          
          Log.d("fuck", Integer.toString(keyTypeArrayList.size()));
          
          
          ArrayList<Integer> imageStrings = new ArrayList<Integer>();
          for(int j =0;j<keyTypeArrayList.size();j++){
        	 
        	  if(keyTypeArrayList.get(j).equals("Home"))
        	    imageStrings.add(j,ImageList[0]);
        	  if(keyTypeArrayList.get(j).equals("Office"))
        		  imageStrings.add(j,ImageList[1]);
        	  if(keyTypeArrayList.get(j).equals("Car"))
        		  imageStrings.add(j,ImageList[2]);
        	  if(keyTypeArrayList.get(j).equals("Others"))
        		  imageStrings.add(j,ImageList[3]);
        	  
          }
          
          adapter = new
                  CustomListKeyContact(getActivity(), keyNameArrayList , imageStrings);
        	adapter.notifyDataSetChanged();
  			
        		  
           // Assign adapter to ListView
      	    keyListView.setAdapter(adapter);
         }
         
         if (keyListView != null) {
             mOnTouchListener = new SwipeDismissListViewTouchListener(keyListView,mCallback);
             keyListView.setOnTouchListener(mOnTouchListener);

             // Setting this scroll listener is required to ensure that during
             // ListView scrolling,
             // we don't look for swipes.
             keyListView.setOnScrollListener(mOnTouchListener.makeScrollListener());
             
           //UndoController
 		//	if (mUndoBarController==null)
 				mUndoBarController = new UndoBarController(v.findViewById(R.id.undobar), this);
    
       }  
         if(keyNameArrayList.size()>0){
        	keyListView.setItemChecked(0, true);
       //  keyListView.getChildAt(0).setBackgroundColor(Color.LTGRAY);
         TorchKey.pwd = keyPasswordArrayList.get(0);
	       TorchKey.currentKeyTextView.setText(keyNameArrayList.get(0));
	       Log.d("aa", "aa");
         }
         
         
         keyListView.setOnItemClickListener(new OnItemClickListener() {
         	   public void onItemClick(AdapterView<?> parent, View view,
         	     int position, long id) {
         	    
         		   view.setSelected(true);
         	       String keySelect =  (String) parent.getItemAtPosition(position);
         	       int i = keyNameArrayList.indexOf(keySelect);
         	       TorchKey.pwd = keyPasswordArrayList.get(i);
         	       TorchKey.currentKeyTextView.setText(keySelect);
         	       for (int j = 0; j < parent.getChildCount(); j++)
                   parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);

                   // change the background color of the selected element
                   view.setBackgroundColor(Color.LTGRAY);
         	   
  			   
         	   }
         	  });
         	    
         	    keyListView.setOnItemLongClickListener(new OnItemLongClickListener() {

  				@Override
  				public boolean onItemLongClick(AdapterView<?> parent, View view,
  						int position, long id) {
  					// TODO Auto-generated method stub
  					 String keySelect =  (String) parent.getItemAtPosition(position);
  			       	  int i = keyNameArrayList.indexOf(keySelect);
  			       	  gotoEditKey(idArrayList.get(i));
  					  return false;
  				}
         	    	
         	    	
         	    	
         	    	
  			});
         	 
         
         
		 return v;  
	 }
	 /**
		 * A callback function, executed when this fragment is attached to an
		 * activity
		 */
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			try {
				/**
				 * This statement ensures that the hosting activity implements
				 * ListFragmentItemClickListener
				 */
				iItemClickListener = (ListFragmentItemClickListener) activity;
			} catch (Exception e) {
				Log.d("haha", "Exception");
			}
		}
		public void onListItemClick(ListView list, View view, int position, long id) {

			/**
			 * Invokes the implementation of the method onListFragmentItemClick in
			 * the hosting activity
			 */
			iItemClickListener.onListFragmentItemClick(view, position);

		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			if (mUndoBarController!=null)
				mUndoBarController.onSaveInstanceState(outState);
	    }

	
	
	
	
	public static KeyListFragment newInstance() {

       
        return f;
    }
	
	  public void refresh(){
	    	 keyListView.setAdapter(null);
	    	 db = MainActivity.dbhelper.getReadableDatabase();
	    	 Cursor c =db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
	    	 
	    	 int nameColumn = c.getColumnIndex(KEY_NAME);
	         int passwordColumn = c.getColumnIndex(PASSWORD);
	         int id = c.getColumnIndex(_ID);
	         int type = c.getColumnIndex(KEY_TYPE);
	         int i = 0;
	         
	         keyNameArrayList.clear();
	         
	         keyPasswordArrayList.clear();
	         
	         idArrayList.clear();
	         
	         keyTypeArrayList.clear();
	         // Check if our result was valid.
	         
	         if (c.getCount() > 0 && c!=null) {
	          // Loop through all Results
	        	 c.moveToFirst();
	          do {
	           String Name = c.getString(nameColumn);
	           String password = c.getString(passwordColumn);
	           String key_idString =c.getString(id);
	           String key_typeString = c.getString(type);
	           Log.d("type", key_typeString);
	           keyNameArrayList.add(i, Name);
	           keyPasswordArrayList.add(i,password);
	           idArrayList.add(i,key_idString);
	           keyTypeArrayList.add(i, key_typeString);
	           i++;
	          }while(c.moveToNext());
	          
	          Log.d("fuck", Integer.toString(keyTypeArrayList.size()));
	          
	          
	          ArrayList<Integer> imageStrings = new ArrayList<Integer>();
	          for(int j =0;j<keyTypeArrayList.size();j++){
	        	  if(keyTypeArrayList.get(j).equals("Home"))
	        	    imageStrings.add(j,ImageList[0]);
	        	  if(keyTypeArrayList.get(j).equals("Office"))
	        		  imageStrings.add(j,ImageList[1]);
	        	  if(keyTypeArrayList.get(j).equals("Car"))
	        		  imageStrings.add(j,ImageList[2]);
	        	  if(keyTypeArrayList.get(j).equals("Others"))
	        		  imageStrings.add(j,ImageList[3]);
	        	  
	          }
	          
	          adapter = new
	                  CustomListKeyContact(getActivity(), keyNameArrayList , imageStrings);
	        	adapter.notifyDataSetChanged();
	  			
	        		  
	           // Assign adapter to ListView
	      	    keyListView.setAdapter(adapter);
	      	    
	         }
	         
	        
	        
	     }
	  private void gotoEditKey(String id) {
		  Intent intent = new Intent (getActivity(), EditKeyActivity.class);	
			intent.putExtra("ID", id);
			
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
		  
	}
	  static void deleteEvent(String id) {
			db = MainActivity.dbhelper.getWritableDatabase();
	        db.delete(TABLE_NAME, _ID + "=" + id, null);
		}






	  /**
		 * Undo remove Action
		 */
		@Override
		public void onUndo(Parcelable token) {

			//Restore items in lists (use reverseSortedOrder)
			if (token!=null){

				UndoItem item=(UndoItem)token;
				String[] itemStrings=item.itemString;
				int[] itemPositions=item.itemPosition;

				if (itemStrings!=null && itemPositions!=null){
					int end=itemStrings.length;

					for(int i=end-1;i>=0;i--){

						String itemString=itemStrings[i];
						int itemPosition=itemPositions[i];

						adapter.insert(itemString,itemPosition);
						
						

				        if(addKey()){
				            Log.d("in", "in");
				        	refresh();
				        }
						
						adapter.notifyDataSetChanged();
						
						
						
					}
				}
			}

		}
		private Boolean addKey() {
		    db = MainActivity.dbhelper.getWritableDatabase();
	        ContentValues values = new ContentValues();
	        
	        values.put(KEY_NAME, undoName);
	        values.put(PASSWORD, undoPassword);
	        values.put(KEY_TYPE, undoType);
	        Log.d("fuck you", undoType);
	        db.insert(TABLE_NAME, null, values);
	        return true;
		}
	  @Override
	public void onResume() {
		  Log.e("DEBUG", "onResume of HomeFragment");
		     super.onResume();
		     
          mUndoBarController.hideUndoBar(true);
		
	}

}
