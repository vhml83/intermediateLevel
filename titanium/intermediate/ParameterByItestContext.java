package com.titanium.intermediate;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*-----Ejercicio de Groups-----*/

public class ParameterByItestContext {

	String baseUrl = "http://www.google.com/";
	WebDriver myDriver;
	//String expectedResult = "";
	//String actualResult = "";
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";

	//groups indicamos los grupos para los cuáles queremos ejecutar este método
	@BeforeTest(groups = {"A", "B"})
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", chromePath);
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		myDriver.get(baseUrl);
	}

	//groups = A indicamos que únicamente para éste grupo se va a ejecutar este método
	@AfterTest(groups = {"A"})
	public void tearDown() {
		myDriver.quit();
	}
	
	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataProvider(ITestContext c) {
		Object[][] groupArray = null;
		
		for(String group: c.getIncludedGroups()){

			if(group.equalsIgnoreCase("A")) {
				groupArray = new Object[][] {
					{"Ernesto", "Google"},
					{"Victor", "Yahoo"},
					{"Osiris", "Gmail"},
					{"Gabriela", "Amazon" }
				};
			break; //Si ya encontró el grupo, se sale del ciclo for
			
			} else if (group.equalsIgnoreCase("B")){
				groupArray = new Object[][] {
					{"Mexico"},
					{"Canada"},
					{"Rusia"},
					{"Japon" }
				};
			}
			break;
		}	
		return groupArray;		
	}
	
	@Test(dataProvider = "SearchProvider", groups ="A")
	public void testMethodA(String testerName, String searchKey) throws InterruptedException {
		WebElement txtSearch = myDriver.findElement(By.name("q"));
		txtSearch.sendKeys(searchKey);
		System.out.println("Welcome -> " + testerName + " your search key is " + searchKey);
		Thread.sleep(3000);
		
		String testValue = txtSearch.getAttribute("value");
		System.out.println("Test value is -> " + testValue + " and is equal to " + searchKey);
		txtSearch.clear();
		
		Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}
	
	@Test(dataProvider = "SearchProvider", groups ="B")
	public void testMethodB(String searchKey) throws InterruptedException {
		WebElement txtSearch = myDriver.findElement(By.name("q"));
		txtSearch.sendKeys(searchKey);
		Thread.sleep(3000);
		
		String testValue = txtSearch.getAttribute("value");
		System.out.println("Test value is -> " + testValue + " and is equal to " + searchKey);
		txtSearch.clear();
		
		Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
	}
}
