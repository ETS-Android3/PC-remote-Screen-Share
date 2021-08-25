/***
 * @author Sanat Raorane
 * The main class sends and received objects to the Android device
 */

package DesktopAppPack;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class main {
	
	/***
	 * If the display, keyboard or mouse classes want to sent an object, then 
	 * they will set the sendObject variable to that object
	 */
	volatile static Object sendObject; 
	/**
	 * If sendStatus=1, the send thread is busy 
	 * sendStatus=0, the send thread is free and another object can be sent
	 */
	volatile static int sendStatus=1; 
	private volatile static int receivePortnum=-1,remotePortnum; //receivePort num will be generated, remote Portnum must be entered
	private volatile static Inet4Address myIp;                  //To store LAN IP address of current PC
	private volatile static String myIpAdd,remoteIpAdd;         //myIP is IP of PC, remote IP is IP of android device
	static volatile ObjectInputStream oRec;                     //stream to receive objects
	static volatile ObjectOutputStream oSend;                   //stream to send objects
	
	public static void main(String[] args) throws IOException {
		Scanner scan1=new Scanner(System.in);
		try {
			//gets the LAN address of pc
			myIp=(Inet4Address)Inet4Address.getLocalHost(); //Local host is the current PC
			myIpAdd=myIp.getHostAddress();                  //Get the IPV4 address of current PC
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
	
		/***
		 * The receive thread is like a mailbox, it shall receive all objects
		 * After receiving the objects, it shall send them to the appropriate class
		 */
		Thread readThread=new Thread(new Runnable()
		{
	
			@Override
			public void run() {
				try
				{
					ServerSocket sRec=new ServerSocket(0); //If port no. is set as 0, then an available port no. is auto assigned
					receivePortnum=sRec.getLocalPort();    //get the port no. that was assigned
					Socket soRec=sRec.accept();	           //Wait for a connection, will block here till then
					oRec=new ObjectInputStream(soRec.getInputStream());
					while(true)  
					{
								try {
									Object receivedObject;
									receivedObject=oRec.readObject();  //Read next object 
									/**
									 * Get the class of received object
									 * Integer: Android device has sent a keycode for keyboard
									 * String:  Android device has sent a coordinate string of format "x= &y= " for mouse
									 * Double:  Android device has requested the next display frame for screen sharing
									 * Character: Reserved for future use
									 */
									switch(receivedObject.getClass().getName())  
									{
									case "java.lang.Integer":          
										new keyboard((Integer) receivedObject);
										break;
									case "java.lang.String":
										new mouse((String) receivedObject);
										break;
									case "java.lang.Double":
										if((Double)receivedObject==50.0)
											new display();
										break;
									case "java.lang.Character":
										System.out.println(receivedObject);
										break;
									default:
										break;
									}
								}
								catch(StreamCorruptedException e) {
									System.out.println("Connection corrupted, please restart");
									System.exit(0);
								}
								catch(EOFException e){
									System.out.println("Remote has Shut down");
									System.exit(0);
								}
								catch (Exception e) {
									System.out.println("An issue was encountered with the connection, service stopped.");
									System.exit(0);
								}
					} 
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
	
		});
	
		//Start the read thread
		readThread.start();
			
		while(receivePortnum==-1) {};  //Wait for the establishment of receiver part
		System.out.println("\nDesktop details:\nip:"+myIpAdd+"\nport:"+receivePortnum);
		//Prompt user to enter details about Android device
		System.out.println("\nEnter Remote details:\nip:");
		remoteIpAdd=scan1.next();
		System.out.println("port:");
		remotePortnum=scan1.nextInt();
		
		/***
		 * The receive thread is a single point of contact for all classes which
		 * intend on sending objects
		 */
		Thread sendThread=new Thread(new Runnable() {
	
			@Override
			public void run() {
				try {	
					Socket soSend=new Socket(remoteIpAdd,remotePortnum); //Set up a client type socket
					oSend=new ObjectOutputStream(soSend.getOutputStream());  //Set up the output stream
					System.out.println("Connection established");     //Announce establishment of connection
					while(true)
					{
						if(sendStatus==0)
						{
							oSend.writeObject(sendObject);
							oSend.flush();                     //Send data currently in stream to the destination
							sendStatus=1;
						}
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Please restart");
				} 
			}
			
		});
		
		sendThread.start();		
		
	}
}
	
	

