package ro.pub.cs.systems.eim.practicaltest01Model;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01ModelService extends Service {
	
	
	@Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	int nr1 = intent.getIntExtra("nr1", -1);
        int nr2 = intent.getIntExtra("nr2", -1);
        ProcessingThread processingThread = new ProcessingThread(this,nr1, nr2);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
