package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Display extends AppCompatActivity {

    ImageView displayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        displayView=(ImageView)findViewById(R.id.iv_display);

     updateDisplay();
     new Thread(new Runnable() {
         @Override
         public void run() {
             Long startTime=System.currentTimeMillis();
             while(true)
             {
                 if(System.currentTimeMillis()-startTime>500 && netUtils.displayStatus==1)
                 {
                     startTime=System.currentTimeMillis();
                     updateDisplay();
                 }
             }
         }
     }).start();
    }

    void updateDisplay()
    {
        while(netUtils.sendStatus!=1){}
        netUtils.sendObject=new Double(50.0);
    //    Log.d("Sendingrequest","code 50");
        netUtils.sendStatus=0;
        if(netUtils.receivedImageArray==null)
        {
            finish();
        }
        else{
            Bitmap bmp = BitmapFactory.decodeByteArray(netUtils.receivedImageArray, 0, netUtils.receivedImageArray.length); //changed from disPlayImage to receivedImageArray
            displayView.setImageBitmap(bmp);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        netUtils.displayStatus=1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        netUtils.displayStatus=0;
    }
}