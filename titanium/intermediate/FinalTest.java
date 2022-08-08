package com.titanium.intermediate;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FinalTest {
	String baseUrl = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&hl=en";
	WebDriver myDriver;
	Logger log = Logger.getLogger(LogExample.class.getName());
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";
	
	@BeforeClass
	public void initializeComponent() {
		DOMConfigurator.configure("log4j.xml");
	}
	
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		myDriver.get(baseUrl);
		log.info("Opening Website");
	}
	
	@AfterTest
	public void tearDown() {
		log.info("Browser closed");
		myDriver.quit();
	}
	
	@Test(priority = 0, description = "Failed attempt to login to the email account")
	public void failLoginPage() throws InterruptedException {
		log.info("Entering wrong email");
		myDriver.findElement(By.id("identifierId")).sendKeys("loginFail");
		myDriver.findElement(By.id("identifierNext")).click();
		WebDriverWait waitVar = new WebDriverWait(myDriver, 10);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		log.info("Entering wrong password");
		myDriver.findElement(By.name("password")).sendKeys("abc123");
		myDriver.findElement(By.id("passwordNext")).click();
		Thread.sleep(3000);
		myDriver.navigate().to(baseUrl);
	}
	
	@Test(priority = 1, description = "Successfull login to the email account")
	public void loginPage() throws InterruptedException {
		myDriver.findElement(By.id("identifierId")).clear();
		log.info("Entering correct email");
		myDriver.findElement(By.id("identifierId")).sendKeys("vhml76@gmail.com");
		myDriver.findElement(By.id("identifierNext")).click();
		WebDriverWait waitVar = new WebDriverWait(myDriver, 10);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		log.info("Entering correct password");
		myDriver.findElement(By.name("password")).sendKeys("oTixe1102");
		myDriver.findElement(By.id("passwordNext")).click();
		Thread.sleep(3000);
	}
	
	@Test(priority = 2, description = "Create a new email category called Test")
	public void createCategory() throws InterruptedException  {
		log.info("Creating new email category 'Test'");
		myDriver.findElement(By.className("aos")).click();
		myDriver.findElement(By.id("ms")).click();
		WebDriverWait waitVar = new WebDriverWait(myDriver, 10);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Labels")));
		myDriver.findElement(By.linkText("Labels")).click();
		myDriver.findElement(By.className("alZ")).click();
		myDriver.findElement(By.className("xx")).sendKeys("Test");
		myDriver.findElement(By.name("ok")).click();
		myDriver.findElement(By.partialLinkText("Inbox")).click();
		Thread.sleep(3000);
	}
	
	@Test(priority = 3, description = "Send an Email")
	public void sendEmail() throws InterruptedException {
		WebDriverWait waitVar = new WebDriverWait(myDriver, 10);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("z0")));
		log.info("Sending new email");
		myDriver.findElement(By.className("z0")).click();
		myDriver.findElement(By.name("to")).sendKeys("vhml76@gmail.com");
		myDriver.findElement(By.name("subjectbox")).sendKeys("Email test with Selenium WebDriver");
		myDriver.findElement(By.className("editable")).sendKeys("Hello this is a test.");
		myDriver.findElement(By.className("btA")).click();
		Thread.sleep(3000);
	}
}
