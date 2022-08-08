package com.titanium.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterUser {
	WebDriver driver;
	String Expected = null;
	String Actual = null;
	IndexPage objIndex;
	RegisterPage objRegister;
	
	
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\IntermediateLevel\\drivers\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://newtours.demoaut.com/");
	}
	
	@AfterTest
	public void tearDown(){
		driver.close();
	}
	
	@Test(priority = 0)
	public void goToRegisterPage(){
		objIndex = new IndexPage(driver);
		objIndex.clickRegisterLink();
		
		Expected = "Register: Mercury Tours";
		Actual = objIndex.getIndexPageTitle();
		
		Assert.assertEquals(Actual,  Expected);
	}
	
	@Test(priority = 1)
	public void registerAnUser(){
		objRegister = new RegisterPage(driver);
		try{
			 objRegister.setFirstName("Gilberto");
			 objRegister.selectCountry("AUSTRIA");
			 objRegister.submitUserInformation("gilberto@mail.com", "123");
			 String textSuccess = driver.findElement(By.xpath(".//*[contains(text(), 'Note: Your user name is')]")).getText();
			 System.out.println("Test passed! : " + textSuccess);
			 
		 }catch(Exception e){
			 if(e instanceof NoSuchElementException){
				 Assert.fail("Test Failed!: element was not found");
			 }else{
				 Assert.fail("Test Failed!: " + e.getMessage());
			 }
		 }
	}

}
