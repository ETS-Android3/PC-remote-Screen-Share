/**
 * @author Sanat Raorane
 * The netUtils class shall be used for every send and receive operation
 */
package com.example.android.remotedesktop;

import android.util.Log;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class netUtils
{
    /**
     * If a class wants to send an object, then set that object to sendObject variable
     */
    static volatile Object sendObject;
    /**
     * If the receive thread received an image (in form of a byte array),
     * then if will set it to receivedImageArray variable
     */
    static volatile byte receivedImageArray[];
    /**
     * status: 1=connected, 0=NotConnected, -1 Disconnected due to error
     * sendStatus: 1=available, 0=blocked
     */
    static volatile int status=0,displayStatus=1,sendStatus=1;
    /***
     * If the PC has sent some special code to Phone, then it will be set to netCodes
     */
    static volatile  double netCodes;
    /**
     *  myIp: ip address of the Android phone
     *  pcIP: ip address of the pc
     */
    public volatile static String myIp,pcIp="";
    /**
     * receivePort: port number at which the server is listening (on Android device)
     * sendPort: port number at which the server is listening (on PC)
     */
    static volatile int receivePort,sendPort=0;
    /**
     * phoneServ: server socket reference variable
     */
    static ServerSocket phoneServ;
    /**
     * rSocket: This socket will receive data, will work with the ServerSocket
     * sSocket: This socket will send data (will act as client socket)
     */
    static Socket rSocket,sSocket;
    /**
     * inputStream: To receive data from PC
     */
    static ObjectInputStream inputStream;
    /**
     * outputStream: To send data to the PC
     */
    static ObjectOutputStream outputStream;


    /**
     * The constructor contains the read thread and send thread
     * The read thread reads data, send thread sends data
     * Both the threads remain operational till the app shuts down
     * @throws IOException
     */
    netUtils() throws IOException {

        /**
         * This thread will read data sent from the PC
         * It contains a Server Socket
         */
        Thread read=new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    while(true){
                        phoneServ=new ServerSocket(0); //setting port=0 automatically assigns an available port
                        receivePort=phoneServ.getLocalPort(); //get the assigned port
                        Log.d("netUtils","Android IP:"+myIp+" Port:"+receivePort);
                        rSocket=phoneServ.accept(); //accept incoming request, establish connection
                        inputStream= new ObjectInputStream(rSocket.getInputStream()); //Object input stream to receive objects
                        while(true) {
                            Object receivedObject;
                            try {
                                receivedObject = inputStream.readObject();          //Read object from input stream
                                switch (receivedObject.getClass().getName()) {       //Check class type of object
                                    case "java.lang.Double":                        //If double, then it is a special code
                                        netCodes = (double) receivedObject;
                                        break;
                                    case "[B":                                     //"[B" represent a byte array i.e. an image
                                        receivedImageArray = (byte[]) receivedObject;
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                /**
                                 * First set status to -1, this will call finalize() on all pending remotes
                                 * Then set status back to 0 (not connected)
                                 */
                                status = -1;
                                Log.d("netUtils", "readThread " + e.getMessage());
                                status=0;
                                break;
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    Log.d("netUtils","ServerSocket Side "+ e.getMessage());
                }
            }
        });


        /**
         *This thread will send data to the PC
         *It contains a Socket
         */
         Thread send=new Thread(new Runnable() {
            @Override
            public void run() {
               while(true){
                   while(pcIp.length()==0 || sendPort==0){}; //Wait till the pcIP and port number are entered by user
                       try {
                           Log.d("netUtils","PC IP:"+myIp+" Port:"+receivePort);
                           sSocket=new Socket(pcIp,sendPort);  //Make a socket, with server having IP as pcIP and port as sendPort
                           outputStream= new ObjectOutputStream(sSocket.getOutputStream());
                           status=1; //set status as connected
                           while (status==1)
                           {
                               /**
                                * sendStatus=0 implied some class has requested data to be sent
                                * The class has also set the data onto sendObject variable
                                * write this data to the outputStream and then raise sendStatus back to 1
                                */
                               if(sendStatus==0)
                               {
                                   outputStream.writeObject(sendObject);
                                   outputStream.flush();
                                   sendStatus=1;
                               }
                           }
                       } catch (Exception e) {
                           /**
                            * First set status to -1, this will call finalize() on all pending remotes
                            * Then set status back to 0 (not connected)
                            */
                           status=-1;
                           Log.d("netUtils","sendThread "+e.getMessage());
                           status=0;
                       }
                        pcIp="";
                        sendPort=0;
                   }
               }
         });
         //Start both the threads
        read.start();
        send.start();
    }
}


