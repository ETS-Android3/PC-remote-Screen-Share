package DesktopAppPack;

import java.awt.AWTException;
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
			Robot dispCapture=new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenBufferImage = dispCapture.createScreenCapture(screenRect);
			ByteArrayOutputStream bStream=new ByteArrayOutputStream();
			ImageIO.write(screenBufferImage,"JPG", bStream);
			byte[] tempArray=bStream.toByteArray();
			while(main.sendStatus!=1) //changed from finalArray to tempArray
			{}
			main.sendObject=tempArray;
			main.sendStatus=0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
                   
    
    