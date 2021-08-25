/**
 * @author Sanat Raorane
 * The Keyboard class shall be used for the keyboard remote
 */

package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Keyboard extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard); //Inflate objects from activity_keyboard.xml
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
    }

}

