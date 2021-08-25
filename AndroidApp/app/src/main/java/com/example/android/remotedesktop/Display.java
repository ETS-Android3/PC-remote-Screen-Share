/**
 * @author Sanat Raorane
 * The Display activity shall handle the screen share feature
 */
package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class Display extends AppCompatActivity {

    ImageView displayView; //The display where live footage shall be shown
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display); //Inflate objects
        displayView=(ImageView)findViewById(R.id.iv_display);
        this.getSupportActionBar().hide(); //hide action bar, to maximize displayView area
        /**
         * networkMonitor thread will destroy the activity
         * if the connection gets disturbed
         */
        Thread networkMonitor=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(netUtils.status==-1)  //connection disturbed
                        finish();  //Destroy the activity, return to the previous activity
                }
            }
        });
        networkMonitor.start();

         updateDisplay();
        /**
         * This thread will update the display after every 500 ms
         * It will keep on doing so till displayStatus=1
         */
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

    /***
     * updateDisplay(): Method responsible for updating the footage on displayView
     * Shall send a request code (50.0) to the PC
     * The PC will reply with a an image in form of byte array
     */
    void updateDisplay()
    {
        while(netUtils.sendStatus!=1){}  //Wait till stable connection is established
        /**
         * Double object of value 50: Request for the next frame
         */
        netUtils.sendObject=new Double(50.0);
        netUtils.sendStatus=0; //Drop the send status, this will prompt netUtils to send data
        if(netUtils.receivedImageArray==null)
        {
            finish();
        }
        else{
            /**
             * Use factory method to convert received byte array to bitmap image
             * a bitmap is an array of binary data representing the values of pixels in an image or display.
             * Unlike JPEG, bmp uses loss-less compressions
             */
            Bitmap bmp = BitmapFactory.decodeByteArray(netUtils.receivedImageArray, 0, netUtils.receivedImageArray.length);
            displayView.setImageBitmap(bmp);
        }
    }

    /**
     * This will turn displayStatus to 1
     * This will start the loop which was sending (50.0) frame requests to the PC
     */
    @Override
    protected void onResume() {
        super.onResume();
        netUtils.displayStatus=1;
    }

    /**
     * This will turn displayStatus=0 (sentinel value)
     * This will stop the loop which was sending (50.0) frame requests to the PC
     */
    @Override
    protected void onPause() {
        super.onPause();
        netUtils.displayStatus=0;
    }
}