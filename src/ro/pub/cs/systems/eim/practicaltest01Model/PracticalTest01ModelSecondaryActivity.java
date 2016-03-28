package ro.pub.cs.systems.eim.practicaltest01Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ro.pub.cs.systems.eim.practicaltest01Model.PracticalTest01ModelMainActivity.ApasaButon;

public class PracticalTest01ModelSecondaryActivity extends Activity {

	Button ok = null, cancel = null;
	EditText total = null;
	
	class ApasaButon implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			
			// TODO Auto-generated method stub
			if (v.getId() == R.id.ok) {
				setResult(RESULT_OK, null);
				finish();
			}
			
			if (v.getId() == R.id.cancel) {
				setResult(RESULT_CANCELED, null);
				finish();
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_model_secondary);
		
		
		ApasaButon ap = new ApasaButon();
		
		ok = (Button)findViewById(R.id.ok);
		ok.setOnClickListener(ap);
		
		cancel = (Button)findViewById(R.id.cancel);
		cancel.setOnClickListener(ap);
		
		total = (EditText)findViewById(R.id.total);
		
		Intent intent = getIntent();
		if (intent != null) {
		  String nr = intent.getStringExtra("numarTotalClickuri");
		  if (nr != null) {
		    total.setText(nr);
		  }
		} 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_model_secondary, menu);
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
}
