/***
 * @author Sanat Raorane
 * The display class captures a screenshot every 500ms and sends it to Android device
 */

package DesktopAppPack;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

public class display{
	display()
	{
		try {
			//Create an object of Java Robot class
			Robot dispCapture=new Robot();
			//Create a Rectangle objects with dimensions of screen size
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			//Pass the Rectangle object to Robot, this will return a BufferedImage 
			BufferedImage screenBufferImage = dispCapture.createScreenCapture(screenRect);
			ByteArrayOutputStream bStream=new ByteArrayOutputStream();
			//Write the buffered image to the ByteArrayOutputStream
			ImageIO.write(screenBufferImage,"JPG", bStream);  //Warning JPG compression may be lossy
			byte[] tempArray=bStream.toByteArray();  //Get a byte array object representing the image
			while(main.sendStatus!=1) {} //Wait for connection establishment
			main.sendObject=tempArray;     
			main.sendStatus=0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
                   
    
    