package com.kmbridge.itdoc.hardcoding;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kmbridge.itdoc.R;
import com.kmbridge.itdoc.activity.KmClinicDetailActivity;
import com.kmbridge.itdoc.activity.MainDrawerActivity;




public class IntroActivity extends Activity //��Ʈ�� �κ� ��Ƽ��Ƽ
{ 
	ImageView intro; 
	ImageView intro2; 

	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        
        new Thread(new Runnable() 
        {
        	public void run() 
        	{
        		try 
        		{
        			
        			intro = (ImageView)findViewById(R.id.intro_Img);  // ��Ʈ�� �̹��� ����
        		//	Animation alphaAnim= AnimationUtils.loadAnimation(IntroActivity.this, R.anim.alpha);  // �ִ� ���� ����
        		//	intro.startAnimation(alphaAnim);  
        			Thread.sleep(1000);      // 
        			isIntro();
        			
        		
        		}
        		
        		catch (Exception e) 
        		{}
        		
        	}

        }).start();

	}
	

	private void isIntro() 
	{ 
		Intent intent = new Intent(this, MainDrawerActivity.class);  //Mainȭ������ �̵��Ѵ�.
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent); 
		finish();
	}

}


