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
	
	volatile static Object sendObject;
	volatile static int readStatus=1,sendStatus=1; //1=safe   0=blocked
	private volatile static int receivePortnum=-1,remotePortnum;
	private volatile static Inet4Address myIp;
	private volatile static String myIpAdd,remoteIpAdd;
	static volatile ObjectInputStream oRec;
	static volatile ObjectOutputStream oSend;
	
	public static void main(String[] args) throws IOException {
	Scanner scan1=new Scanner(System.in);
	try {
			//gets the LAN address of pc
			myIp=(Inet4Address)Inet4Address.getLocalHost();
			myIpAdd=myIp.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
	
	Thread readThread=new Thread(new Runnable()
	{

		@Override
		public void run() {
			try
			{
				ServerSocket sRec=new ServerSocket(0); //Make a server socket with the generated Portnum
				receivePortnum=sRec.getLocalPort();
				Socket soRec=sRec.accept();	
				oRec=new ObjectInputStream(soRec.getInputStream());
				System.out.println("Handshake success");
				while(true)  
				{
					if(readStatus==1){
							readStatus=0;
							try {
								Object receivedObject;
								while(oRec==null) {}
								//	System.out.println("debug 1 oRec not null");
									receivedObject=oRec.readObject();
									readStatus=1;
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
							//		System.out.println("Code 50 received");
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
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	});
	
	
	
	readThread.start();
		
		while(receivePortnum==-1) {};
		System.out.println("\nDesktop details:\nip:"+myIpAdd+"\nport:"+receivePortnum);
		System.out.println("\nEnter Remote details:\nip:");
		remoteIpAdd=scan1.next();
		System.out.println("port:");
		remotePortnum=scan1.nextInt();
		
		
		Thread sendThread=new Thread(new Runnable() {

			@Override
			public void run() {
				try {	
					Socket soSend=new Socket(remoteIpAdd,remotePortnum);
					oSend=new ObjectOutputStream(soSend.getOutputStream());
					while(true)
					{
						if(sendStatus==0)
						{
							oSend.writeObject(sendObject);
							oSend.flush();
							sendStatus=1;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					run();
				} 
			}
			
		});
		
		sendThread.start();			
	}
}
	
	

