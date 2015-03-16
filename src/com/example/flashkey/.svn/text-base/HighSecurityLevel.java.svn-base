package com.example.flashkey;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import static com.example.flashkey.DatabaseConstants.TABLE_NAME;

public class HighSecurityLevel extends Activity{
	
	private SQLiteDatabase db = null;
	private int wrong_number = 3;
	
	private Integer image[] = new Integer[]{R.drawable.ic_0,
			R.drawable.ic_1,
			R.drawable.ic_2,
			R.drawable.ic_3,
			R.drawable.ic_4,
			R.drawable.ic_5,
			R.drawable.ic_6,
			R.drawable.ic_7,
			R.drawable.ic_8,
			R.drawable.ic_9,
			R.drawable.ic_ok,
			R.drawable.ic_backspace
			};
	private Integer effect[] = new Integer[]{
			R.drawable.ic_0_press,
			R.drawable.ic_1_press,
			R.drawable.ic_2_press,
			R.drawable.ic_3_press,
			R.drawable.ic_4_press,
			R.drawable.ic_5_press,
			R.drawable.ic_6_press,
			R.drawable.ic_7_press,
			R.drawable.ic_8_press,
			R.drawable.ic_9_press,
			R.drawable.ic_ok_press,
			R.drawable.ic_backspace_press
	};
	
	private static int count = 0;
	
	private static ImageButton button1;
	private static ImageButton button2;
	private static ImageButton button3;
	private static ImageButton button4;
	private static ImageButton button5;
	private static ImageButton button6;
	private static ImageButton button7;
	private static ImageButton button8;
	private static ImageButton button9;
	private static ImageButton button0;
	
	private static ImageButton buttonDelete;
	private static ImageButton buttonOk;
	
	private static EditText input1;
	private static EditText input2;
	private static EditText input3;
	private static EditText input4;
	
	private static ArrayList<String> passwordLogin = new ArrayList<String>();
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_login);
		
		button0 = (ImageButton) findViewById(R.id.ic_0);
		button1 = (ImageButton) findViewById(R.id.ic_1);
		button2 = (ImageButton) findViewById(R.id.ic_2);
		button3 = (ImageButton) findViewById(R.id.ic_3);
		button4 = (ImageButton) findViewById(R.id.ic_4);
		button5 = (ImageButton) findViewById(R.id.ic_5);
		button6 = (ImageButton) findViewById(R.id.ic_6);
		button7 = (ImageButton) findViewById(R.id.ic_7);
		button8 = (ImageButton) findViewById(R.id.ic_8);
		button9 = (ImageButton) findViewById(R.id.ic_9);
		
		buttonDelete = (ImageButton) findViewById(R.id.delete);
		buttonOk = (ImageButton) findViewById(R.id.ok);
		
		input1 = (EditText) findViewById(R.id.firstInput);
		input2 = (EditText) findViewById(R.id.secondInput);
		input3 = (EditText) findViewById(R.id.thirdInput);
		input4 = (EditText) findViewById(R.id.fourtInput);
		
		//on click listenner
		buttonOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				matchPassword();
				passwordLogin.clear();
			}
		});
		
		button0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "0");
				count++;
				displayDigit();
			}
		});
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "1");
				count++;
				displayDigit();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "2");
				count++;
				displayDigit();
			}
		});
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "3");
				count++;
				displayDigit();
			}
		});
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "4");
				count++;
				displayDigit();
			}
		});
		
		button5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "5");
				count++;
				displayDigit();
			}
		});
		button6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "6");
				count++;
				displayDigit();
			}
		});
		button7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "7");
				count++;
				displayDigit();
			}
		});
		button8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "8");
				count++;
				displayDigit();
			}
		});
		button9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordLogin.add(count, "9");
				count++;
				displayDigit();
			}
		});
		
		buttonDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(count>0){
				count--;
				passwordLogin.remove(count);
				
				displayDigit();
				}
			}
		});
		
		button0.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button0.setBackgroundResource(effect[0]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button0.setBackgroundResource(image[0]);
				return false;
			}
		});
		button1.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button1.setBackgroundResource(effect[1]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button1.setBackgroundResource(image[1]);
				return false;
			}
		});
		button2.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button2.setBackgroundResource(effect[2]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button2.setBackgroundResource(image[2]);
				return false;
			}
		});
		button3.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button3.setBackgroundResource(effect[3]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button3.setBackgroundResource(image[3]);
				return false;
			}
		});
		button4.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button4.setBackgroundResource(effect[4]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button4.setBackgroundResource(image[4]);
				return false;
			}
		});
		button5.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button5.setBackgroundResource(effect[5]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button5.setBackgroundResource(image[5]);
				return false;
			}
		});
		button6.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button6.setBackgroundResource(effect[6]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button6.setBackgroundResource(image[6]);
				return false;
			}
		});
		button7.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button7.setBackgroundResource(effect[7]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button7.setBackgroundResource(image[7]);
				return false;
			}
		});
		button8.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button8.setBackgroundResource(effect[8]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button8.setBackgroundResource(image[8]);
				return false;
			}
		});
		button9.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				button9.setBackgroundResource(effect[9]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	button9.setBackgroundResource(image[9]);
				return false;
			}
		});
		buttonDelete.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				buttonDelete.setBackgroundResource(effect[11]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	buttonDelete.setBackgroundResource(image[11]);
				return false;
			}
		});
		buttonOk.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
		        if (action == MotionEvent.ACTION_DOWN)
		        {
		        	
				buttonOk.setBackgroundResource(effect[10]);
		        }
		        else if (action == MotionEvent.ACTION_UP)
		        	buttonOk.setBackgroundResource(image[10]);
				return false;
			}
		});
		
		
		
	}
    
	
	
	private void matchPassword(){
		String input = input1.getText().toString()+ input2.getText().toString()+input3.getText().toString()+input4.getText().toString();
		if(input.equals(MainActivity.primary_password)){
			Intent start = new Intent(this, MainActivity.class);
			Toast.makeText(this, "success", 
	                Toast.LENGTH_SHORT).show();
			count = 0;
			input1.setText(" ");
			input2.setText(" ");
			input3.setText(" ");
			input4.setText(" ");
			startActivity(start);
			overridePendingTransition(R.anim.right_in, R.anim.left_out);
			this.finish();
		}
		else{
			input1.setText(" ");
			input2.setText(" ");
			input3.setText(" ");
			input4.setText(" ");
			count=0;
			wrong_number --;
			deleteAll();
		}
	}
	private void displayDigit(){
		switch (count-1) {
		case -1:
			    input1.setText(" ");
			    input2.setText(" ");
			    input3.setText(" ");
			    input4.setText(" ");
			break;
		case 0:
			   input1.setText(passwordLogin.get(0));
			   input2.setText(" ");
			   input3.setText(" ");
			   input4.setText(" ");
			break;
		case 1:
			   input1.setText(passwordLogin.get(0));
			   input2.setText(passwordLogin.get(1));
			   input3.setText(" ");
			   input4.setText(" ");
			break;
		case 2:
			   input1.setText(passwordLogin.get(0));
			   input2.setText(passwordLogin.get(1));
			   input3.setText(passwordLogin.get(2));
			   input4.setText(" ");
			break;
		case 3:
			   input1.setText(passwordLogin.get(0));
			   input2.setText(passwordLogin.get(1));
			   input3.setText(passwordLogin.get(2));
			   input4.setText(passwordLogin.get(3));
			break;

		default:
			break;
		}
	}
	private void deleteAll() {
		  if(db==null){
			  db = new DatabaseHelper(this).getWritableDatabase();
		  } 
		if(wrong_number==0){
				db.delete(TABLE_NAME, null, null);
		}
		else{
			Toast.makeText(this, Integer.toString(wrong_number)+"times left", 
	                Toast.LENGTH_SHORT).show();
		}
		
	}
}
