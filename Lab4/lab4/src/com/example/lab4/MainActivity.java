package com.example.lab4;
import java.io.File;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
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
	public void training(){
		
		
		
	}
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
	
		Button btn1=(Button) findViewById(R.id.button1);
		Button btn2=(Button) findViewById(R.id.button2);
		Button btn3=(Button) findViewById(R.id.button3);
		Button btn4=(Button) findViewById(R.id.button4);
		Button btn5=(Button)findViewById(R.id.button5);

		btn5.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	new SendFile().execute("");
		    }
		    
		});
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
	    		testing(v);
	    	}
	    });
		btn4.setOnClickListener(new View.OnClickListener(){
	    	@Override
	    	public void onClick(View v){
	    		training(v);
	    	}

			private void training(View v) {
				// TODO Auto-generated method stub
				webView = (WebView) findViewById(R.id.webView1);
				webView.getSettings().setJavaScriptEnabled(true);
				//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
				webView.loadUrl("http://134.193.136.127:8080/HMMWS/jaxrs/generic/TrainFileOperation/-home-group2-group12-vPunch.txt/-home-group2-group12-vPunch.seq");
				
				webView.loadUrl("http://134.193.136.127:8080/HMMWS/jaxrs/generic/TrainFileOperation/-home-group2-group12-vLtoR.txt/-home-group2-group12-vLtoR.seq");
				webView.loadUrl("http://134.193.136.127:8080/HMMWS/jaxrs/generic/TrainFileOperation/-home-group2-group12-vRtoL.txt/-home-group2-group12-vRtoL.seq");
				webView.loadUrl("http://134.193.136.127:8080/HMMWS/jaxrs/generic/TrainFileOperation/-home-group2-group12-vCombo.txt/-home-group2-group12-vCombo.seq");
				
			}
	    });
		
	    
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	protected void testing(View v) {
		// TODO Auto-generated method stub
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
		webView.loadUrl("http://134.193.136.127:8080/HMMWS/jaxrs/generic/HMMTrainingTestThree/-home-group2-group12-vPunch.seq/-home-group2-group12-vLtoR.seq/-home-group2-group12-vRtoL.seq/-home-group2-group12-vCombo.seq");
		
		webView.loadUrl("http://134.193.136.127:8080/HMMWS/jaxrs/generic/HMMTrainingTest/-home-group2-group12-vPunch.seq:-home-group2-group12-vRtoL.seq:-home-group2-group12-vLtoR.seq/punch:ltor:rtol/-home-group2-group12-vCombo.seq");
		
	}
class SendFile extends AsyncTask<String, Void, String> {
		

	    private Exception exception;

	    protected String doInBackground(String... urls) {
	        try {
	           
	        	
	        	
	        	JSch ssh = new JSch();
			        JSch.setConfig("StrictHostKeyChecking", "no");
			        Session session;
					try {
						session = ssh.getSession("group2", "134.193.136.127", 22);
					
			        session.setPassword("group2");
			        session.connect();
			        Channel channel = session.openChannel("sftp");
			        channel.connect();
			        ChannelSftp sftp = (ChannelSftp) channel;
			        
			        File sdCard = Environment.getExternalStorageDirectory(); 
					File directory = new File (sdCard.getAbsolutePath() + "/Data");
				    
					Log.i(null,directory+"/vCombo.txt");
					
			        
			        sftp.put(directory+"/vCombo.txt", "/home/group2/group12");
			    	
					} catch (JSchException e) {
						// TODO Auto-generated catch block
						Log.i(null,e.toString());
						e.printStackTrace();
						 Toast.makeText(getApplicationContext(), 
				                   e.toString(), Toast.LENGTH_LONG).show();
					} catch (SftpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						 Toast.makeText(getApplicationContext(), 
				                   e.toString(), Toast.LENGTH_LONG).show();
					}

	        	
	        } catch (Exception e) {
	            this.exception = e;
	            return null;
	        }
			return null;
	    }

	    protected void onPostExecute() {
	        // TODO: check this.exception 
	        // TODO: do something with the feed
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
