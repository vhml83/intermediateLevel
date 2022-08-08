package com.titanium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	WebDriver myDriver;
	@FindBy(linkText = "REGISTER")
	WebElement lnkRegister;
	
	public IndexPage(WebDriver driver) {
		myDriver = driver;
		PageFactory.initElements(myDriver, this);
	}
	
	public void clickRegisterLink() {
		lnkRegister.click();
	}
	
	public String getIndexPageTitle() {
		return myDriver.getTitle();
	}
}
