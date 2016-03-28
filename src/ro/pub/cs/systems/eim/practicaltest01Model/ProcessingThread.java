package ro.pub.cs.systems.eim.practicaltest01Model;

import java.sql.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;

public class ProcessingThread extends Thread {
	private Context context;
	private double ma;
	private double mg;
	private Random random = new Random();
	 
	  public ProcessingThread(Context context, int nr1, int nr2) {
	    this.context = context;
	    ma = (nr1 + nr2) / 2;
		mg = Math.sqrt(nr1 * nr2);
	  }
	  
	  @Override
	  public void run() {
	    while(true){ 
	      sendMessage();
	      sleep();
	    }
	  }
	  
	  private void sleep() {
		    try {
		      Thread.sleep(10000);
		    } catch (InterruptedException interruptedException) {
		      interruptedException.printStackTrace();
		    }
	  }
	  
	  private void sendMessage() {
		    Intent intent = new Intent();
		    intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
			intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + ma + " " + mg);
		    context.sendBroadcast(intent);
	  }
}
