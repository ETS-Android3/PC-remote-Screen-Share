package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Mouse extends AppCompatActivity implements View.OnTouchListener,View.OnLongClickListener {


    ImageView mousePad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        mousePad=findViewById(R.id.mousePad);
        mousePad.setOnTouchListener(this);
        mousePad.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        String send="x="+5000+"&y="+5000;
        netUtils.send(send);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        String send="x="+x+"&y="+y;
        netUtils.send(send);
      /*  mousePad.setOnTouchListener(null);
        long currentTime=System.currentTimeMillis();
        while(System.currentTimeMillis()-currentTime<1000){}
        mousePad.setOnTouchListener(this); */
        return true;
    }
}
/*
    int x=(int)event.getX();
    int y=(int)event.getY();
    String send="x="+x+"&y="+y;
                netUtils.send(send);
                        return true; */