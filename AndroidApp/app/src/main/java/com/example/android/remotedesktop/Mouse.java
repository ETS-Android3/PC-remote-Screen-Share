package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Mouse extends AppCompatActivity implements View.OnTouchListener {


    ImageView mousePad;
    Button Lclick,Rclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        mousePad=findViewById(R.id.mousePad);
        mousePad.setOnTouchListener(this);
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
        Lclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendClick="x="+5000+"&y="+5000;
                while(netUtils.sendStatus!=1){}
                netUtils.sendObject=sendClick;
                netUtils.sendStatus=0;
            }
        });
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        String send="x="+x+"&y="+y;
        while(netUtils.sendStatus!=1){}
        netUtils.sendObject=send;
        netUtils.sendStatus=0;
        return true;
    }
}
