package DesktopAppPack;

import java.awt.AWTException;
import java.awt.Robot;

public class keyboard {
	keyboard(Integer keyCode)
	{
		try {
			Robot kbRobot=new Robot();
			kbRobot.keyPress(keyCode);
			kbRobot.keyRelease(keyCode);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
