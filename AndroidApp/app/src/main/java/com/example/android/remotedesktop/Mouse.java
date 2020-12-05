package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Mouse extends AppCompatActivity {


    ImageView mousePad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        mousePad=findViewById(R.id.mousePad);
        mousePad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x=(int)event.getX();
                int y=(int)event.getY();
                String send="x="+x+"&y="+y;
                netUtils.send(send);
                return true;
            }
        });
    }


}