package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Display extends AppCompatActivity {

    ImageView displayView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        displayView=(ImageView)findViewById(R.id.iv_display);
        netUtils.send(new Double(50.0)); //send code 50
        Log.d("Display","debug1");
        while(netUtils.receivedImageArray==null){};
        Log.d("Display","debug2");
        //unboxing Byte array
   /*     byte displayImageArray[]=new byte[netUtils.receivedImageArray.length];
        int i=0;
        for(Byte b:netUtils.receivedImageArray) //commented
            displayImageArray[i++]=b; */
        Bitmap bmp = BitmapFactory.decodeByteArray(netUtils.receivedImageArray, 0, netUtils.receivedImageArray.length); //changed from disPlayImage to receivedImageArray
        displayView.setImageBitmap(bmp);

    }
    

}