package com.titanium.pdfemail;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(JyperionListener.class)
public class PdfEmail extends BaseClass{
	
	WebDriver myDriver = getDriver();
	
	@Test
	public void testOne() {
		myDriver.get("http://live.guru99.com/index.php/checkout/cart");
		Assert.assertTrue(false);
	}
	
	@Test
	public void testTwo() {
		myDriver.get("http://www.facebook.com/");
		Assert.assertTrue(true);
	}
	
	@Test
	public void testThree() {
		myDriver.get("http://www.google.com/");
		Assert.assertTrue(false);
	}
	
	@AfterTest
	public void closeBrowser() {
		myDriver.quit();
	}
	
	@AfterSuite
	public void tearDown() {
		sendPdfReportByMail("titaniumsoltest@gmail.com", "titaniumtest", "titaniumsoltest@gmail.com", "Victor PDF Report", "Please find Attached the pdf report");
	}
}
