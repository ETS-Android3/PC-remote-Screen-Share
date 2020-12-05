package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    netUtils u1;
    TextView myIp,myPort;
    EditText pcIP,pcPort;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE); //WifiManager object to access Wifi service
        netUtils.myIp = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress()); //extract LAN ip of the android device

        try {
            u1= new netUtils(MainActivity.this);
        } catch (IOException e) {
            Log.d("details",e.getMessage());
        }

        done=(Button)findViewById(R.id.btn_done);
        myIp=(TextView)findViewById(R.id.tv_myIp);
        myPort=(TextView)findViewById(R.id.tv_myPort);
        pcIP=(EditText)findViewById(R.id.tv_pcIp);
        pcPort=(EditText)findViewById(R.id.tv_pcPort);
        while(netUtils.receivePort==0){}; //WARNING! Blocks main thread
        if(netUtils.myIp.startsWith("0"))
            netUtils.myIp="192.168.43.1";
        myIp.setText(netUtils.myIp);
        myPort.setText(String.valueOf(netUtils.receivePort));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcIP.length()!=0)
                    netUtils.pcIp=String.valueOf(pcIP.getText());
                if(pcPort.length()!=0)
                    netUtils.sendPort=Integer.parseInt(String.valueOf(pcPort.getText()));
                Intent i=new Intent(MainActivity.this,SelectRemote.class);
                if(pcIP.length()==0 || pcPort.length()==0)
                    Toast.makeText(MainActivity.this,"Enter PC details",Toast.LENGTH_SHORT).show();
                else
                    startActivity(i);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(netUtils.status==-1)
            Toast.makeText(MainActivity.this,"Host Unreachable",Toast.LENGTH_SHORT).show();
    }
}