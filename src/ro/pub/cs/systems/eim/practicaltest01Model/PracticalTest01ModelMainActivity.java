package ro.pub.cs.systems.eim.practicaltest01Model;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01ModelMainActivity extends Activity {

	Button buton1 = null, buton2 = null, activ2;
	EditText text1 = null, text2 = null;
	boolean serviciuPornit = false;
	
	int numar1 = 0, numar2 = 0;
	String nr1 = new String(), nr2 = new String();
	
	private IntentFilter intentFilter = new IntentFilter();
	
	class ApasaButon implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			
			
			
			// TODO Auto-generated method stub
			if (v.getId() == R.id.buton1) {
				numar1++;
				nr1 = numar1 + "";
				text1.setText(nr1);
			}
			
			if (v.getId() == R.id.buton2) {
				numar2++;
				nr2 = numar2 + "";
				text2.setText(nr2);
			}
			
			if (v.getId() == R.id.activ2) {
				int numarTotal = numar1 + numar2;
				String total = numarTotal + "";
				Intent intent = new Intent(getApplicationContext(), PracticalTest01ModelSecondaryActivity.class);
				intent.putExtra("numarTotalClickuri", total);
				startActivityForResult(intent, 100);
			}
			
			if (numar1 + numar2 > 5 &&
					serviciuPornit == false) {
				Intent intent = new Intent(getApplicationContext(), PracticalTest01ModelService.class);
				intent.putExtra("nr1", nr1);
				intent.putExtra("nr2", nr2);
				startService(intent);
				serviciuPornit = true;
			}
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_model_main);
		
		ApasaButon ap = new ApasaButon();
		
		buton1 = (Button)findViewById(R.id.buton1);
		buton1.setOnClickListener(ap);
		
		buton2 = (Button)findViewById(R.id.buton2);
		buton2.setOnClickListener(ap);
		
		text1 = (EditText)findViewById(R.id.text1);
		
		text2 = (EditText)findViewById(R.id.text2);
		
		activ2 = (Button)findViewById(R.id.activ2);
		activ2.setOnClickListener(ap);
		
		for (int index = 0; index < Constants.actionTypes.length; index++) {
			intentFilter.addAction(Constants.actionTypes[index]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_model_main, menu);
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
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  savedInstanceState.putString(Constants.TEXT1, text1.getText().toString());
	  savedInstanceState.putString(Constants.TEXT2, text2.getText().toString());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  if (savedInstanceState.getString(Constants.TEXT1) != null) {
	      text1.setText(savedInstanceState.getString(Constants.TEXT1));
	  }
	  if (savedInstanceState.getString(Constants.TEXT2) != null) {
	      text2.setText(savedInstanceState.getString(Constants.TEXT2));
	  }
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	switch(requestCode) {
    	  case 100:
    	    Toast.makeText(getApplication(), "Secondary Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
    	    break;
    	 }
    }
	
	private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
	private class MessageBroadcastReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.d("[Message Irina]", intent.getStringExtra("message"));
		}
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
    	unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
	
	@Override
	protected void onDestroy() {
	  Intent intent = new Intent(this, PracticalTest01ModelService.class);
	  stopService(intent);
	  super.onDestroy();
	}
	
	
	
}
