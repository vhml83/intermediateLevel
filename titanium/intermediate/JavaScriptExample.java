package com.titanium.intermediate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*------Ejercicio con funciones de JavaScript------*/

public class JavaScriptExample {
	String baseUrl = "http://newtours.demoaut.com/";
	WebDriver myDriver;
	String expectedResult = "";
	String actualResult = "";
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";
	JavascriptExecutor js;
	String pageLoadStatus = null;
	
	private boolean highLight(WebElement element){
		js = (JavascriptExecutor)myDriver;
		
		for(int iCnt = 0; iCnt < 3; iCnt++) {
			try {
				js.executeScript("arguments[0].setAttribute('style', 'background: red')", element);
				Thread.sleep(1000);
				js.executeScript("arguments[0].setAttribute('style', 'background: ')", element);
			} catch (InterruptedException ie) {
				System.err.println("JavaScriptExample | Method highLight | Exception desc: " + ie);
				return false;
			}	
		}
		return true;
	}
	
	private boolean waitForPageToLoad() {
		try {
			do {
				js = (JavascriptExecutor)myDriver;
				pageLoadStatus = (String)js.executeScript("return document.readyState", "");
			} while (!pageLoadStatus.equals("complete")); 
		} catch(Exception ie) {
			System.err.println("JavaScriptExample | Method waitForPageToLoad | Exception desc: " + ie);
			return false;
		}
		return true;
	}
	
	private boolean scrollWindow() {
		try {
			js.executeScript("window.scrollBy(0, 250)", "");			
		} catch(Exception ie) {
			System.err.println("JavaScriptExample | Method scrollWindow | Exception desc: " + ie);
			return false;
		}
		return true;
	}
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		myDriver.get(baseUrl);
		waitForPageToLoad();
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
	public void register() throws InterruptedException {
		WebElement lnkRegister = myDriver.findElement(By.linkText("REGISTER"));
		Assert.assertTrue(highLight(lnkRegister));
		js.executeScript("arguments[0].click()", lnkRegister);
		expectedResult = "Register: Mercury Tours";
		actualResult = myDriver.getTitle();
		//Si actualResult es igual a expectedResult devuelve 'true'
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals");
		Assert.assertTrue(scrollWindow());
		Thread.sleep(2000);
	}
	
	//Piority 0 se ejecuta primero, enabled = true el método se va a ejecutar
	@Test(priority = 0, enabled = true, description = "Click on Support link")
	public void support() {
		WebElement lnkSupport = myDriver.findElement(By.linkText("SUPPORT"));
		Assert.assertTrue(highLight(lnkSupport));
		js.executeScript("arguments[0].click()", lnkSupport);
		waitForPageToLoad();
		expectedResult = "Under Construction: Mercury Tours";
		actualResult = myDriver.getTitle();
		//Si actualResult es igual a expectedResult devuelve 'true'
		Assert.assertEquals(actualResult, expectedResult, "Title is not equals"); 
	}
	
}
