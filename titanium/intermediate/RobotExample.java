package com.titanium.intermediate;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;



public class RobotExample {

	WebDriver myDriver;
	String firefoxPath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\geckodriver.exe";
	
	@Test
	public void robotApiTest() throws AWTException, InterruptedException {
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		myDriver = new FirefoxDriver();
		myDriver.manage().window().maximize();
		myDriver.get("http://spreadsheetpage.com/index.php/file/C35/P10/");
		
		myDriver.findElement(By.linkText("animatedcolors.xlsm")).click();
		
		Robot robot = new Robot();
		
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_DOWN);
		
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}
}
