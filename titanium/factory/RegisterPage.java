package com.titanium.factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
	WebDriver myDriver;
	
	@FindBy(name="firstName")
	WebElement txtFirstName;
	@FindBy(name="country")
	WebElement ddlCountry;
	@FindBy(name="email")
	WebElement txtUserName;
	@FindBy(name="password")
	WebElement txtPassword;
	@FindBy(name="confirmPassword")
	WebElement txtConfirmPass;
	@FindBy(name="register")
	WebElement btnSubmit;
	
	public RegisterPage(WebDriver driver) {
		myDriver = driver;
		PageFactory.initElements(myDriver, this);
	}
	
	public void setFirstName(String strFirstName) {
		txtFirstName.sendKeys(strFirstName);
	}
	
	public void selectCountry(String strCountry) {
		new Select(ddlCountry).selectByVisibleText(strCountry);
	}
	
	public void setUserName(String strUserName) {
		txtUserName.sendKeys(strUserName);
	}
	
	
	public void setPassword(String strPassword) {
		txtPassword.sendKeys(strPassword);
	}
	
	public void setConfirmPassword(String strConfirmPass) {
		txtConfirmPass.sendKeys(strConfirmPass);
	}
	
	public void clickSubmitButton() {
		btnSubmit.click();
	}
	
	public void submitUserInformation(String strUserName, String strPass) {
		this.setUserName(strUserName);
		this.setPassword(strPass);
		this.setConfirmPassword(strPass);
		this.clickSubmitButton();
	}
}
