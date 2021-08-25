/***
 * @author Sanat Raorane
 * The MainActivity takes pc IP and pc Port input from the user
 * This is the home activity
 */
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
    TextView myIp,myPort;   //Port number, IP address of Android device
    EditText pcIP,pcPort;   //Port number, IP address of PC
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity: ","onCreate");
        /**
         * If connection was never made/ was disconnected due to error
         * Then setUp a new connection (Make an object of netUtils class)
         */
        if(netUtils.status==0 || netUtils.status==-1)
            setUp();
    }

    void setUp(){
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE); //WifiManager object to access Wifi service
        netUtils.myIp = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress()); //extract LAN ip of the android device

        try {
            u1= new netUtils();
        } catch (IOException e) {
            Log.d("MainActivity: ",e.getMessage());
        }

        done=(Button)findViewById(R.id.btn_done);
        myIp=(TextView)findViewById(R.id.tv_myIp);
        myPort=(TextView)findViewById(R.id.tv_myPort);
        pcIP=(EditText)findViewById(R.id.tv_pcIp);
        pcPort=(EditText)findViewById(R.id.tv_pcPort);
        while(netUtils.receivePort==0){};
        if(netUtils.myIp.startsWith("0"))  //This will happen if hotspot is being used
            netUtils.myIp="192.168.43.1";  //The default IP address of WIFI hotspot is 192.168.43.1 (followed by most manufacturers)
        myIp.setText(netUtils.myIp);
        myPort.setText(String.valueOf(netUtils.receivePort));

        /***
         * If the user presses the done button
         * Check if the pc IP address length and pc Port length entered is more than 1
         * If yes then start the next activity
         * Else pop a warning toast
         */
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcIP.length()>1)
                    netUtils.pcIp=String.valueOf(pcIP.getText());
                if(pcPort.length()>1)
                    netUtils.sendPort=Integer.parseInt(String.valueOf(pcPort.getText()));
                Intent i=new Intent(MainActivity.this,SelectRemote.class);
                if(pcIP.length()<=1 || pcPort.length()<=1)
                    Toast.makeText(MainActivity.this,"Enter PC details",Toast.LENGTH_SHORT).show();
                else
                    startActivity(i);
            }
        });
    }

    /***
     * In case of interruption in connection, the user will return to main activity
     * If this happens then update IP and port of the Android device
     */
    protected void onResume(){
        super.onResume();
        myIp.setText(netUtils.myIp);
        myPort.setText(String.valueOf(netUtils.receivePort));
        Log.d("MainActivity: ","onResume");
    }

}