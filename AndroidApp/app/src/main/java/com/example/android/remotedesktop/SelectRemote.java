/***
 * @author Sanat Raorane
 * The SelectRemote class is for selecting the remote from
 * mouse, keyboard and screen-share
 */
package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class SelectRemote extends AppCompatActivity {

    RelativeLayout mouse,keyboard,display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_remote); //Inflate view objects from layout file
        mouse=(RelativeLayout)findViewById(R.id.btn_mouse); //Button to start mouse remote
        keyboard=(RelativeLayout)findViewById(R.id.btn_keyboard); //Button to start keyboard remote
        display=(RelativeLayout)findViewById(R.id.btn_display); //Button to start display remote
        /**
         * networkMonitor thread will destroy the activity
         * if the connection gets disturbed
         */
        Thread networkMonitor=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(netUtils.status==-1) {
                        Log.d("SelectRemote: ","Destroyed");
                        finish();
                    }
                }
            }
        });
        networkMonitor.start();

        /***
         * For all three buttons (mouse, keyboard, display)
         * The remote will open only if the connection is stable
         * else the activity will be destroyed
         */
        mouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(netUtils.status==1)
                {
                    startActivity(new Intent(SelectRemote.this,Mouse.class));
                }
                else
                {
                    finish();
                }
            }
        });

        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(netUtils.status==1)
                {
                    startActivity(new Intent(SelectRemote.this,Keyboard.class));
                }
                else
                {
                    finish();
                }
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(netUtils.status==1)
                {
                    startActivity(new Intent(SelectRemote.this,Display.class));
                }
                else
                {
                    finish();
                }
            }
        });
    }

}