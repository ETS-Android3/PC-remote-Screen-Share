package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Mouse extends AppCompatActivity {

    EditText xcood,ycood;
    Button msOp;
    String send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        xcood=(EditText)findViewById(R.id.ed_xcood);
        ycood=(EditText)findViewById(R.id.ed_ycood);
        msOp=(Button)findViewById(R.id.btn_msOperation);
        msOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send="x="+String.valueOf(xcood.getText())+"&"+"y="+String.valueOf(ycood.getText());
                netUtils.send(send);
            }
        });
    }
}