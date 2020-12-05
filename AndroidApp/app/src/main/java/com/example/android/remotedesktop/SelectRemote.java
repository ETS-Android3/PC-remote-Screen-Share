package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SelectRemote extends AppCompatActivity {

    RelativeLayout mouse,keyboard,display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_remote);
        mouse=(RelativeLayout)findViewById(R.id.btn_mouse);
        keyboard=(RelativeLayout)findViewById(R.id.btn_keyboard);
        display=(RelativeLayout)findViewById(R.id.btn_display);

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