/***
 * @author Sanat Raorane
 * The mouse class simulates mouse operation for coordinates sent by Android device
 */
package DesktopAppPack;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class mouse {
	mouse(String coordString)
	{
	try {
		Robot mouseRobot=new Robot();
		/**
		 * The Android device will send a string having format "x=num1&y=num2"
		 * Use regex to split the string into num1 and num2 (string array)
		 * Regex decipher:
		 * ^ Matches the beginning
		 * 0-9 set of values allowed
		 * . Matches any characer from the set
		 * + One or more times
		 */
		String[] numbers = coordString.replaceAll("[^0-9.]+", " ").trim().split(" ");
		// All patterns matching regular expression will be separated and stored in numbers array
		if(Integer.valueOf(numbers[0])==5000)     //x=5000 is for left click 
		{
			//Press and release immediately to avoid repetition
			mouseRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mouseRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
		else if(Integer.valueOf(numbers[0])==6000)  ///x=6000 is for right click
		{
			//Press and release immediately to avoid repetition
			mouseRobot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			mouseRobot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		}
		else {
			//If not 5000 or 6000, then mouse mouse to the x and y coordinates
			mouseRobot.mouseMove(Integer.valueOf(numbers[0]),Integer.valueOf(numbers[1]));
		}
			
	} catch (AWTException e) {
		e.printStackTrace();
	}
	}
}
