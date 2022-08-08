package com.titanium.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
	WebDriver myDriver;
	
	By txtFirstName = By.name("firstName");
	By ddlCountry = By.name("country");
	By txtUserName = By.id("email");
	By txtPassword = By.name("password");
	By txtConfirmPass = By.name("confirmPassword");
	By btnSubmit = By.name("register");
	
	public RegisterPage(WebDriver driver) {
		myDriver = driver;
	}
	
	public void setFirstName(String strFirstName) {
		myDriver.findElement(txtFirstName).sendKeys(strFirstName);
	}
	
	public void selectCountry(String strCountry) {
		new Select(myDriver.findElement(ddlCountry)).selectByVisibleText(strCountry);
	}
	
	public void setUserName(String strUserName) {
		myDriver.findElement(txtUserName).sendKeys(strUserName);
	}
	
	
	public void setPassword(String strPassword) {
		myDriver.findElement(txtPassword).sendKeys(strPassword);
	}
	
	public void setConfirmPassword(String strConfirmPass) {
		myDriver.findElement(txtConfirmPass).sendKeys(strConfirmPass);
	}
	
	public void clickSubmitButton() {
		myDriver.findElement(btnSubmit).click();
	}
	
	public void submitUserInformation(String strUserName, String strPass) {
		this.setUserName(strUserName);
		this.setPassword(strPass);
		this.setConfirmPassword(strPass);
		this.clickSubmitButton();
	}
}
