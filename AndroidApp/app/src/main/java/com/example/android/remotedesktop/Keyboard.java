package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class Keyboard extends AppCompatActivity {

    EditText keyCode;
    Button performOp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        keyCode=(EditText)findViewById(R.id.et_KeyCode);
        performOp=(Button)findViewById(R.id.btn_kbOperation);

        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                performOp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        netUtils.send(new KeyboardOperation(Integer.parseInt(String.valueOf(keyCode.getText()))));
                    }
                });
            }
        });
        t.start();
    }

}

class KeyboardOperation implements Serializable
{
    int keyCode;
    KeyboardOperation(int keyCode)
    {
        this.keyCode=keyCode;
    }
}