package DesktopAppPack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class mouse {
	mouse(String coordString)
	{
	try {
		Robot mouseRobot=new Robot();
		String[] numbers = coordString.replaceAll("[^0-9.]+", " ").trim().split(" ");
	//	System.out.println(Integer.valueOf(numbers[0])+" "+Integer.valueOf(numbers[1]));
		if(Integer.valueOf(numbers[0])==5000)
		{
			mouseRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mouseRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		else 
			mouseRobot.mouseMove(Integer.valueOf(numbers[0]),Integer.valueOf(numbers[1]));
	} catch (AWTException e) {
		e.printStackTrace();
	}
	}
}
