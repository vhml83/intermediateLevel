package com.titanium.intermediate;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterByMethodInDataProvider {

	String baseUrl = "http://www.google.com/";
	WebDriver myDriver;
	//String expectedResult = "";
	//String actualResult = "";
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		myDriver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() {
		myDriver.quit();
	}
	
	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataProvider(Method m) {
		if(m.getName().equalsIgnoreCase("testMethoda")) {
			return new Object[][] {
				{"Ernesto", "Google"},
				{"Victor", "Yahoo"},
				{"Osiris", "Gmail"},
				{"Gabriela", "Amazon" }
			};
		} else {
			return new Object[][] {
				{"Mexico"},
				{"Canada"},
				{"Rusia"},
				{"Japon" }
			};
		}	
	}
	
	@Test(dataProvider = "SearchProvider")
	public void testMethodA(String testerName, String searchKey) throws InterruptedException {
		WebElement txtSearch = myDriver.findElement(By.name("q"));
		txtSearch.sendKeys(searchKey);
		System.out.println("Welcome -> " + testerName + " your search key is " + searchKey);
		Thread.sleep(3000);
		
		String testValue = txtSearch.getAttribute("value");
		System.out.println("Test value is -> " + testValue + " and is equal to " + searchKey);
		txtSearch.clear();
		
		Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}
	
	@Test(dataProvider = "SearchProvider")
	public void testMethodB(String searchKey) throws InterruptedException {
		WebElement txtSearch = myDriver.findElement(By.name("q"));
		txtSearch.sendKeys(searchKey);
		Thread.sleep(3000);
		
		String testValue = txtSearch.getAttribute("value");
		System.out.println("Test value is -> " + testValue + " and is equal to " + searchKey);
		txtSearch.clear();
		
		Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}
}
