package DesktopAppPack;


public class display{
	static Double d=0.1;
	display()
	{
		d++;
		sendTest(d);
	}
	static void sendTest(Double d)
	{
		Double testd=new Double(d);
		main.send(testd);	
	}
}
                   
    
    