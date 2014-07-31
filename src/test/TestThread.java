package test;

import java.io.File;
import java.text.ParseException;

import com.kmbridge.itdoc.connect.ConnectionBridge;

import android.content.Context;
import android.os.Message;

public class TestThread extends Thread{

	private CustomHandler handler;
	private String methodUrl;
	private File uploadFile;
	private Context context;
	private String id;
	
	
	
	public TestThread(String methodUrl, File uploadFile, Context context, String id )  {
		this.handler = new CustomHandler(context);	
		this.methodUrl = methodUrl;
		this.uploadFile = uploadFile;
		this.context = context;
		this.id = id;
	}


	@Override
	public void run() {
		Message msg = handler.obtainMessage();
		msg.what = CustomHandler.SHOW_LOADING_LAYOUT;
		handler.sendMessage(msg);
		
		ConnectionBridge bridge = new ConnectionBridge();
		bridge.insertImage(methodUrl, uploadFile, context, id);
		
		msg = handler.obtainMessage();
		msg.what = CustomHandler.END_LOADING_LAYOUT;
		handler.sendMessage(msg);
	}

	
}
