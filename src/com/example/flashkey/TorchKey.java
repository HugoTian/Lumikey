package com.example.flashkey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.R.integer;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class TorchKey extends Fragment {
	protected static final int REFRESH = 0;
	private static final String ON = Parameters.FLASH_MODE_TORCH;
	private static final String OFF = Parameters.FLASH_MODE_OFF;
	
	// buttons in layout
	private static Button startButton;
	
	
	// TextView in layout
	
	public static TextView currentKeyTextView;
	
	public static TextView encrtptionModeTextView;
	
	private static View buttonView;
	
	
	//password and dataTransmission;
	
    public static String pwd ;
    private static String dataTransmission ;
	
     //setting for camera access
     public static Camera cam = Camera.open();
     private static Camera.Parameters pon = cam.getParameters();
     private static Camera.Parameters poff = cam.getParameters();
     // private static String mode = pon.getFlashMode();
     
     // setting PWM duty cycle and period
     
     private static int onTimeForOne = 18 ;
     private static int onTimeforZero = 1;
     private static int onTimeforStartOrEnd = 40;
     
     private static int secondOfTime;
     
     // multithread programming 
     private Thread flashThread;
     
     public static Boolean isSent = true;
     public static Boolean isSendKey = true;
	
     // variable for hash function
     private static byte[] SipHashKey = new byte[16];

 	 private static byte[] testMsg = new byte[2];//{ (byte)0x64 };
 	 
 	 private static TorchKey f = new TorchKey();
 			
 	 public static TorchKey mTorchKey = null;
 	
 	 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.torch_key, container, false);  
            
            if(mTorchKey==null){
            	mTorchKey=this;
            }
            initiate();
//            
          
         
       
            startButton = (Button) v.findViewById(R.id.start_key);	
            
            currentKeyTextView = (TextView) v.findViewById(R.id.current_key);
       	    buttonView = (View) v.findViewById(R.id.my_frame);

           // set onclick listener for each button
    		
             startButton.setBackgroundColor(Color.TRANSPARENT);
  
    		
    		startButton.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					 int action = event.getAction();
				        if (action == MotionEvent.ACTION_DOWN)
				        {
				        	sendMessage();
	    				buttonView.setBackgroundResource(R.drawable.ic_unlock_onclick);
				        }
				        else if (action == MotionEvent.ACTION_UP)
				        	buttonView.setBackgroundResource(R.drawable.ic_unlock);
				        return false; 
				}
			});
    	
    	   
    		
     	    return v;
    }
   

    public static TorchKey newInstance() {

       
        return f;
    }
    // initiate the setting of camera flashlight
   	private void initiate(){
   		 pon.setFlashMode(ON);
   		 poff.setFlashMode(OFF);
   	}
   	
  
       
       // send one with PWM
     private static void sendOneWithPWM() throws InterruptedException{
    	 cam.setParameters(pon);
    	 cam.setParameters(poff);
         Thread.sleep(onTimeForOne);
    }
       
       // send zero with PWM
     private static void sendZeroWithPWM() throws InterruptedException {
    	
    	 cam.setParameters(pon);
    	 cam.setParameters(poff);
         Thread.sleep(onTimeforZero);
       	
       }
       
       // end of transmission
     private static void  endTransmission() {
   	  isSent = false;
   	}
       
       // get the data length of data
     private static int dataLength(String input){
       	int length = input.length();
       	return length;
       }
       
       //check whether data transmitted is valid
     private static boolean checkValidInput(String input){
       	int length = dataLength(input);
       	
       	while(length>0){
       		char i = input.charAt(length -1);
       		length--;
       		if(i == '1' || i == '0'){
       			continue;
       		}
       		return false;
       	}
       	return true;
       }
       
     private  void sendData(String input) throws InterruptedException{
       	if(!checkValidInput(input)){
       		Toast.makeText(getActivity(), "Invalid data transmision", 
       				   Toast.LENGTH_LONG).show();
       		}
       	
       	int length = dataLength(input);
       	
       	sendStartBit();
       	
       	
       	for(int j = 0 ; j<length;j++){
       		char i = input.charAt(j);
       		if(i=='1'){
       			sendOneWithPWM();
       		}
       		else if(i=='0'){
       			sendZeroWithPWM();
       		}
       		else{
       			Toast.makeText(getActivity(), "Invalid data transmision", 
        				   Toast.LENGTH_LONG).show();
       		}
       	}
       	sendEndBit();
       
       }
       
     private void encodePassword(String input){
       	int length = input.length();
       	if(length<16){
       		for(int i=length;i<16;i++){
       			input=input+'0';
       		}
       	}
       	for(int j=0;j<16;j++){
       	   char k = input.charAt(j);
       	   byte tmp = (byte) k;
       	   SipHashKey[j]=tmp;
       	}
   		
       }
     private void startNewThread(){
    	   
           flashThread = new Thread(new Runnable() {
       		
       		@Override
       		public void run() {
       			// TODO Auto-generated method stub
       			//while(isSent){
       			try {
       				sendData(dataTransmission);
       			} catch (InterruptedException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
       		 // }
       		}
       		
       	});
           
           if((1<secondOfTime) && (secondOfTime<58)){
        	     flashThread.start();
           	
      	 }
      	 else {
      		 Toast.makeText(getActivity(), "Wait 3 second", 
      				   Toast.LENGTH_LONG).show();
  		}
      
     }
     public boolean isCameraInUse() {
    	    Camera c = null;
    	    try {
    	        c = Camera.open();
    	    } catch (RuntimeException e) {
    	        return true;
    	    } finally {
    	        if (c != null) c.release();
    	    }
    	    return false;
    	}
     private void torchModeKey(){
    	 cam.setParameters(pon);
    	 
    	 isSent=false;
         flashThread = new Thread(new Runnable() {
     		
     		@Override
     		public void run() {
     			// TODO Auto-generated method stub
     		while(isSendKey)
     			while(isSent){
     			try {
     				sendData(dataTransmission);
     			} catch (InterruptedException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
     		  }
     			cam.setParameters(pon);
     		}
     	});
        
    	 flashThread.start();
    	
    	
    	 
         
    	 
     }
     private void sendStartBit() throws InterruptedException{
    	 for(int i=0;i<3;i++){
    	 cam.setParameters(pon);
    	 cam.setParameters(poff);
         Thread.sleep(onTimeforStartOrEnd);
    	 }	
     }
     private void sendEndBit() throws InterruptedException{
    	 
    	 cam.setParameters(pon);
    	 cam.setParameters(poff);
         Thread.sleep(onTimeforStartOrEnd);
    	 	
     }
     
     private String byteArrayToString(byte[] input){
		 
		 String s2 = String.format("%8s", Integer.toBinaryString(input[0] & 0xFF)).replace(' ', '0');
		 String output = s2;
		 for(int i=1;i<8;i++){
			 output=output+String.format("%8s", Integer.toBinaryString(input[i] & 0xFF)).replace(' ', '0');
		 }
		 return output;
     }
     private String encryptionWithHash(){
		
    	SipHash_2_4 sipHash = new SipHash_2_4();
 		// note in the pdf https://131002.net/siphash/siphash.pdf the longs, v0,v1,v2,v3 are shown in BigEndian format
 		long hashResult = sipHash.hash(SipHashKey, testMsg);
 		
 			
 		byte[] result = SipHash_2_4.longToBytesLE(hashResult); // in little format
 		
 	    Log.d("haha", SipHash_2_4.toHex(result));
 		//end of test
 	  // cipherTextView.setText(SipHash_2_4.toHex(result));
 		String output = byteArrayToString(result);
 		
 		
 	    
 		
 		return output.substring(0,32);
 		
 		
    	 
    	 
     }
     private void encode(){
    	 if(cam==null)
		 cam=Camera.open();
		
		if(pwd!=null)
		//passwprd to ASCII
	    encodePassword(pwd);
	    
		//data encryption
	    getCurrentTime();
		dataTransmission = encryptionWithHash();
		// add start and end bit
		//dataTransmission=addStartandEndBit(dataTransmission);
		//set text to debug
		Log.d("data transmission", dataTransmission);
		
     }
     private void getCurrentTime(){
    	 Calendar calendar = Calendar.getInstance();
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	 String timeString = dateFormat.format(calendar.getTime());
    	 Log.d("kkk", timeString);
    	 int year = Integer.parseInt(timeString.substring(0, 4));
    	 int month = Integer.parseInt(timeString.substring(5, 7));
    	 int day = Integer.parseInt(timeString.substring(8, 10));
    	 int hour = Integer.parseInt(timeString.substring(11, 13));
    	 int minute = Integer.parseInt(timeString.substring(14, 16));
    	 int second = Integer.parseInt(timeString.substring(17, 19));
    	 secondOfTime = second;
    	 
    	 int tmp1 = year/100;
    	 int tmp2 = year%100;
    	 int byte1 = tmp1+tmp2+minute;
    	 int byte2 =month+day+hour;

         Log.d("time", Integer.toString(second));
    	 
    	 testMsg[0]=(byte) byte1;
    	 testMsg[1]=(byte) byte2;
    	  
    	
    	 
     }
     public void sendMessage(){
    	    encode();
			if(MainActivity.torch_mode)
				torchModeKey();
			else {
			startNewThread();
			isSent=false;
			}
			
     } 
    
}