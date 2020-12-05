package com.example.android.remotedesktop;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class netUtils
{
    static volatile byte receivedImageArray[];  //type changed from Byte to byte
    static volatile int status=0,displayStatus=1;      //1=Connected 0=Not connected -1=Disconnected due to error, readStatus=1 available readStatus=0 blocked
    static volatile  double netCodes;
    public volatile static String myIp,pcIp="";
    static volatile int receivePort,sendPort=0;
    static ServerSocket phoneServ;
    static Socket rSocket,sSocket;
    static ObjectInputStream inputStream;
    static ObjectOutputStream outputStream;
    Context mainContext;
    netUtils(Context context) throws IOException {

        mainContext=context;
        Thread read=new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    //listener part
                    phoneServ=new ServerSocket(0); //set server socket
                    receivePort=phoneServ.getLocalPort();
                    Log.d("details",myIp+" "+receivePort);
                    rSocket=phoneServ.accept(); //accept incoming request, establish connection
                    inputStream= new ObjectInputStream(rSocket.getInputStream());
                    //listener part
                    int readStatus = 1;
                    while(true)
                    {
                        if(readStatus==1) {
                            readStatus = 0;
                            Object receivedObject;
                            try {
                                while(inputStream==null){}
                                Log.d("readStatus", String.valueOf(readStatus));
                                receivedObject=inputStream.readObject();
                                readStatus=1;
                                switch (receivedObject.getClass().getName())
                                {
                                    case "java.lang.Double":
                                        netCodes= (double) receivedObject;
                                        Log.d("received double", String.valueOf(netCodes));
                                        break;
                                    case "[B":
                                        Log.d("Display","array received");
                                        receivedImageArray= (byte[]) receivedObject;
                                        break;
                                    default:
                                        break;
                                }
                            } catch (EOFException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    Log.d("details",e.getMessage());
                }
            }
        });



         Thread send=new Thread(new Runnable() {
            @Override
            public void run() {
                //sender part
                while(pcIp.length()==0 && sendPort==0){};
                try {
                    Log.d("sendThread",pcIp+" "+sendPort);
                    sSocket=new Socket(pcIp,sendPort);
                    outputStream= new ObjectOutputStream(sSocket.getOutputStream());
                    status=1;
                } catch (Exception e) {
                    Log.d("sendThread",e.getMessage());
                    pcIp="";
                    sendPort=0;
                    status=-1;
                    run();
                }
                //sender part
            }
         });

        read.start();
        send.start();

    }



    static void send(final Object o) {
      new Thread(new Runnable() {
          @Override
          public void run() {
              while(outputStream==null){};
              try{
                      outputStream.writeObject(o);
                      outputStream.flush();
                  }
              catch(Exception e) {
                  e.printStackTrace();
              }
          }
      }).start();
    }


   /* static void receive() {
        new Thread(new Runnable() {
            @Override

            public void run() {
                Object receivedObject;
                try {
                    while(inputStream==null){}
                    Log.d("readStatus", String.valueOf(readStatus));
                    receivedObject=inputStream.readObject();
                    readStatus=1;
                    switch (receivedObject.getClass().getName())
                    {
                        case "java.lang.Double":
                            netCodes= (double) receivedObject;
                            Log.d("received double", String.valueOf(netCodes));
                            break;
                        case "[B":
                            Log.d("Display","array received");
                            receivedImageArray= (byte[]) receivedObject;
                            break;
                        default:
                            break;
                    }
                } catch (EOFException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                  //  readStatus=1;
                }
            }
        }).start();

    } */
}


