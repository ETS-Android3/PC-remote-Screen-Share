/***
 * @author Sanat Raorane
 * The keyboard class simulates keyboard operation for keycodes sent by Android device
 */
package DesktopAppPack;

import java.awt.AWTException;
import java.awt.Robot;

public class keyboard {
	keyboard(Integer keyCode)
	{
		try {
			//Create object of Robot class
			Robot kbRobot=new Robot();
			//Press the key
			kbRobot.keyPress(keyCode);
			//Release the key immediately to prevent repetition
			kbRobot.keyRelease(keyCode);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
