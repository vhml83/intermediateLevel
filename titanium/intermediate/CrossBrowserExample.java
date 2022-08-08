package com.titanium.intermediate;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserExample {
	WebDriver driver;
	String baseUrl = "http://newtours.demoaut.com/index.php";
	String expected = null;
	String actual = null;
	WebDriverWait wait;

	@BeforeTest
	@Parameters("browser")
	public void launchBrowser(String browser) throws Exception{
		switch(browser.toLowerCase()) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new Exception("Incorrect browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@BeforeMethod
	public void verifyHomePageTitle() throws InterruptedException{
		expected = "Welcome: Mercury Tours";
		Thread.sleep(2000);
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}

	@AfterMethod
	public void goBackToHomePage(){
		driver.findElement(By.linkText("Home")).click();
	}

	@AfterTest
	public void tearDown(){
		driver.quit();
	}

	@Test(priority = 1, enabled = true)
	public void register() throws InterruptedException{
		driver.findElement(By.linkText("REGISTER")).click();
		expected = "Register: Mercury Tours";
		wait.until(ExpectedConditions.titleIs("Register: Mercury Tours"));
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 0, enabled = true)
	public void support() throws InterruptedException{
		driver.findElement(By.linkText("SUPPORT")).click();
		wait = new WebDriverWait(driver, 15);
		expected = "Under Construction: Mercury Tours";
		wait.until(ExpectedConditions.titleIs("Under Construction: Mercury Tours"));
		actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}
}
