package com.titanium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage {
	WebDriver myDriver;
	
	By lnkRegister = By.linkText("REGISTER");
	
	public IndexPage(WebDriver driver) {
		myDriver = driver;
	}
	
	public void clickRegisterLink() {
		myDriver.findElement(lnkRegister).click();
	}
	
	public String getIndexPageTitle() {
		return myDriver.getTitle();
	}
}
