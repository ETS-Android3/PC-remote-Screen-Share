/**
 * @author Sanat Raorane
 * The Mouse class shall be used for the mouse remote
 */
package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Mouse extends AppCompatActivity implements View.OnTouchListener {

    ImageView mousePad; //The mouse touch pas
    Button Lclick,Rclick; //Left click, Right click buttons
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse); //Inflate view objects
        mousePad=findViewById(R.id.mousePad);
        mousePad.setOnTouchListener(this);
        /**
         * networkMonitor thread will destroy the activity
         * if the connection gets disturbed
         */
        Thread networkMonitor=new Thread(new Runnable() {
            @Override
            public void run() {
            while(true){
                if(netUtils.status==-1)
                    finish();
            }
            }
        });
        networkMonitor.start();

        Lclick=findViewById(R.id.Lclick);

        /**
         * For left click
         * A string "x=5000 y=5000" will be send to the PC
         * The PC shall react appropriately
         */
        Lclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendClick="x="+5000+"&y="+5000;
                while(netUtils.sendStatus!=1){}
                netUtils.sendObject=sendClick;
                netUtils.sendStatus=0;
            }
        });

        /**
         * For right click
         * A string "x=6000 y=6000" will be send to the PC
         * The PC shall react appropriately
         */
        Rclick=findViewById(R.id.Rclick);
        Rclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendClick="x="+6000+"&y="+6000;
                while(netUtils.sendStatus!=1){}
                netUtils.sendObject=sendClick;
                netUtils.sendStatus=0;
            }
        });
    }


    /***
     * This method is for handling the cursor
     * It will send coordinates of users touch on touch pad to the PC
     * The PC will translate this appropriately
     * @param v    This is the mouse pad
     * @param event  This is a touch event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x=(int)event.getX();   //Get x coordinate of touch
        int y=(int)event.getY();   //Get y coordinate of touch
        String send="x="+x+"&y="+y;  //Prepare a string "x=x-cood&y=y-cood" and send to PC
        while(netUtils.sendStatus!=1){}
        netUtils.sendObject=send;
        netUtils.sendStatus=0;
        return true;
    }
}
