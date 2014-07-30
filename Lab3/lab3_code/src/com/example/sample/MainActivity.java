package com.example.sample;



import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {
		
public void create(){
		startService(new Intent(this,ConnectionService.class));
	}
	private WebView webView;
	public void startsensors(View v){
		startService(new Intent(this,ConnectionService.class));
	}
	public void stopsensors(View v){
		stopService(new Intent(this, ConnectionService.class));
		System.exit(0);
	}
	public void createtable(View v){
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
		webView.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseCreate/sensorgrp12v/x:y:z");
	}
	public void inserttable(View v){
		
		 webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);
			//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
			webView.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseInsert/sensorgrp12v/-home-group9-sensortag.txt/x:y:z");
	}
	public void retrievetable(View v){
		 webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);
			//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
			webView.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseRetrieveAll/sensorgrp12v");
	}
	public void deletetable(View v)
	{
		 webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);
			//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
			webView.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbasedeletel/sensorgrp12v");
	}	
	public void savedata( View v){
		// Initialize a SSHExec instance without referring any object. 
				SSHExec ssh = null;
				// Wrap the whole execution jobs into try-catch block   
				try {
				    // Initialize a ConnBean object, parameter list is ip, username, password
				    ConnBean cb = new ConnBean("134.193.136.147", "group12","group12");
				    // Put the ConnBean instance as parameter for SSHExec static method getInstance(ConnBean) to retrieve a real SSHExec instance
				    ssh = SSHExec.getInstance(cb);              
				
				    // Connect to server
				    ssh.connect();
				    // Upload sshxcute_test.sh to /home/tsadmin on remote system
				    ssh.uploadSingleDataToServer("E://sensorv.txt", "/home/group12/");
				    
			
				} 
				catch (TaskExecFailException e) 
				{
				    System.out.println(e.getMessage());
				    e.printStackTrace();
				} 
				catch (Exception e) 
				{
				    System.out.println(e.getMessage());
				    e.printStackTrace();
				} 
				finally 
				{
				    ssh.disconnect();   
				}
		
	}
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		Button btn1=(Button) findViewById(R.id.button1);
		Button btn2=(Button) findViewById(R.id.button2);
		Button btn3=(Button) findViewById(R.id.button3);
		Button btn4=(Button) findViewById(R.id.button4);
		Button btn5=(Button) findViewById(R.id.button5);
		Button btn6=(Button) findViewById(R.id.button6);
		Button btn7=(Button) findViewById(R.id.button7);
		
		btn1.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    startsensors(v);
		    }
		    
		});
		btn2.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		stopsensors(v);
	    	}
	    });
		btn3.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		savedata(v);
	    	}
	    });
		btn4.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		createtable(v);
	    	}
	    });
		btn5.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		inserttable(v);
	    	}
	    });
		btn6.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		retrievetable(v);
	    	}
	    });
		btn7.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		deletetable(v);
	    	}
	    });
		
	    
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
