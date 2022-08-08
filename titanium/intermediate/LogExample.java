package com.titanium.intermediate;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogExample {
	String baseUrl = "http://www.healthunify.com/bmicalculator";
	WebDriver myDriver;
	Logger log = Logger.getLogger(LogExample.class.getName());
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";
	
	@BeforeClass
	public void initializeComponent() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	@Test
	public void launchBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", chromePath);
			myDriver = new ChromeDriver();
			myDriver.manage().window().maximize();
			myDriver.get(baseUrl);
			log.info("Opening Website");
		} catch (Exception ex) {
			if (ex instanceof WebDriverException) {
				log.error("WebDriver failed: " + ex.getMessage());
			} else {
				log.error(ex.getMessage());
			}
		}
	}
	
	@Test(dependsOnMethods = {"BMICalculator"})
	public void tearDown() {
		myDriver.close();
		log.info("Browser closed");
	}
	
	@Test(dependsOnMethods = {"launchBrowser"})
	public void BMICalculator() {
		try {
			log.info("Entering weight");
			myDriver.findElement(By.name("wg")).sendKeys("78");
			
			log.info("Selection kilograms");
			myDriver.findElement(By.name("opt1")).sendKeys("kilograms");
			
			log.info("Selecting height in feet");
			myDriver.findElement(By.name("opt2")).sendKeys("5");

			log.info("Selecting height in inches");
			myDriver.findElement(By.name("opt3")).sendKeys("10");
			
			log.info("Clicking on calculate");
			myDriver.findElement(By.name("cc")).click();
			
			log.info("Getting SIUnit value");
			String strSIUnit = myDriver.findElement(By.name("si")).getAttribute("value");
			
			log.info("Getting USUnit value");
			String strUSUnit = myDriver.findElement(By.name("us")).getAttribute("value");
			
			log.info("Getting UKUnit value");
			String strUKUnit = myDriver.findElement(By.name("uk")).getAttribute("value");
			
			log.info("Getting overall description");
			String strDesc = myDriver.findElement(By.name("desc")).getAttribute("value");
			
			log.info("SIUnit = " + strSIUnit);
			log.info("USUnit = " + strUSUnit);
			log.info("UKUnit = " + strUKUnit);
			log.info("Description = " + strDesc);
		} catch (Exception ex) {
			if (ex instanceof NoSuchElementException) {
				log.error("WebDriver not found: " + ex.getMessage());
			} else if (ex instanceof WebDriverException) {
				log.error("WebDriver failed: " + ex.getMessage());
			} else {
				log.error(ex.getMessage());
			}
		}
	}
	
}
