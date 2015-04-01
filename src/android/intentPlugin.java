
package com.cordovaprogramming.appintent;  

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface; 
import org.apache.cordova.CordovaWebView; 

import org.json.JSONArray; 
import org.json.JSONException; 
import android.app.Activity;
import android.content.Intent;


class MyIntent extends Activity{
	
	  Intent intent = getIntent();
	 public final  String usName = intent.getStringExtra("username");
	 //public final  String usName = "username";
}

/**
 * js����java����
 * 
 * ����̳�CordovaPlugin CordovaPlugin������ʵ��cordovaActivity�ķ���
 * �ṩstartActivityForResult();
 * 
 * ��ʹ�õ� cordova 3.3.0�汾
 * 
 * @author XueQi
 * 
 */
public class intentPlugin extends CordovaPlugin {

	
	@Override
	public boolean execute(String action, org.json.JSONArray args,
			CallbackContext callbackContext) throws org.json.JSONException {
		
		MyIntent myintent= new MyIntent();
		try { 
			if (action.equals("getIntent")) {

				if (myintent.usName != null && !myintent.usName.equals("")) {
					  callbackContext.success(myintent.usName);
					  return true;
				}
			
	      }
			 callbackContext.error("Invalid Action");   
			 return false; 
		}
		catch(Exception e){ 
			
			System.err.println("Exception: " + e.getMessage());    
			callbackContext.error(e.getMessage());    
			return false; 
			}
		 


	}

	
}
