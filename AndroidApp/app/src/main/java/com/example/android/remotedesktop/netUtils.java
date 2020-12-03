package com.example.android.remotedesktop;


import android.util.Log;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class netUtils
{
    static Object objectReceived;
    public volatile static String myIp,pcIp="";
    static volatile int receivePort,sendPort=0;
    static ServerSocket phoneServ;
    static Socket rSocket,sSocket;
    static ObjectInputStream inputStream;
    static ObjectOutputStream outputStream;
    netUtils() throws IOException {


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
                }
                catch (Exception e)
                {
                    Log.d("details",e.getMessage());
                }
            }
        });



        final Thread send=new Thread(new Runnable() {
            @Override
            public void run() {
                //sender part
                while(pcIp.length()==0 && sendPort==0){};
                try {
                    Log.d("details: pcattempt",pcIp+" "+sendPort);
                    sSocket=new Socket(pcIp,sendPort);
                    outputStream= new ObjectOutputStream(sSocket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //sender part
            }
        });

        read.start();
        send.start();
    }


    static void send(Object o)
    {
        while(outputStream==null){};
        try{
            outputStream.writeObject(o);
            outputStream.flush();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    static void receive() {
        while(inputStream==null){};
        try {
            objectReceived = inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
