package com.example.android.remotedesktop;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
            u1= new netUtils();
        } catch (IOException e) {
            Log.d("details",e.getMessage());
        }

        done=(Button)findViewById(R.id.btn_done);
        myIp=(TextView)findViewById(R.id.tv_myIp);
        myPort=(TextView)findViewById(R.id.tv_myPort);
        pcIP=(EditText)findViewById(R.id.tv_pcIp);
        pcPort=(EditText)findViewById(R.id.tv_pcPort);
        while(netUtils.receivePort==0){}; //WARNING! Blocks main thread
        myIp.setText(netUtils.myIp);
        myPort.setText(String.valueOf(netUtils.receivePort));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netUtils.pcIp=String.valueOf(pcIP.getText());
                netUtils.sendPort=Integer.parseInt(String.valueOf(pcPort.getText()));
            }
        });
    }
}