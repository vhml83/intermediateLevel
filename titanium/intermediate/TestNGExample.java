package com.titanium.intermediate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGExample {
	String baseUrl = "http://newtours.demoaut.com/";
	WebDriver myDriver;
	String expectedResult = "";
	String actualResult = "";
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		myDriver.get(baseUrl);
	}
	
	@BeforeMethod
	public void verifyHomePageTitle() {
		expectedResult = "Welcome: Mercury Tours";
		actualResult = myDriver.getTitle();
		//Si actualResult es igual a expectedResult devuelve 'true'
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals"); 
	}
	
	@AfterMethod
	public void goBackToHomePage() {
		myDriver.findElement(By.linkText("Home")).click();
	}
	
	@AfterTest
	public void tearDown() {
		myDriver.quit();
	}
	
	//Piority 1 se ejecuta después del 0, enabled = true el método se va a ejecutar
	@Test(priority = 1, enabled = true)
	public void register() {
		myDriver.findElement(By.linkText("REGISTER")).click();
		expectedResult = "Register: Mercury Tours";
		actualResult = myDriver.getTitle();
		//Si actualResult es igual a expectedResult devuelve 'true'
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals"); 
	}
	
	//Piority 0 se ejecuta primero, enabled = true el método se va a ejecutar
	@Test(priority = 0, enabled = true, description = "Click on Support link")
	public void support() {
		myDriver.findElement(By.linkText("SUPPORT")).click();
		expectedResult = "Under Construction: Mercury Tours";
		actualResult = myDriver.getTitle();
		//Si actualResult es igual a expectedResult devuelve 'true'
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals"); 
	}
	
}
