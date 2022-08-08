package com.titanium.pdfemail;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class BaseClass {
	static WebDriver myDriver;
	static String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";
	
	public static WebDriver getDriver() {
		if(myDriver == null){
			System.setProperty("webdriver.chrome.driver", chromePath);
			myDriver = new ChromeDriver();
			myDriver.manage().window().maximize();
		}
		return myDriver;
	}
	
	public static void takeSnapShot(WebDriver myDriver, String fileWithPath) throws IOException {
		TakesScreenshot scrShot = (TakesScreenshot)myDriver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		FileUtils.copyFile(srcFile, destFile);
	}
	
	public static void sendPdfReportByMail(String from, String pass, String to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587"); //25, 465, 587
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			message.setText(body);
			BodyPart objMessageBodyPart = new MimeBodyPart();
			objMessageBodyPart.setText(body);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(objMessageBodyPart);
			objMessageBodyPart = new MimeBodyPart();
			
			String fileName = System.getProperty("user.dir")+"\\Default test.pdf";
			
			DataSource source = new FileDataSource(fileName);
			objMessageBodyPart.setDataHandler(new DataHandler(source));
			objMessageBodyPart.setFileName(fileName);
			multipart.addBodyPart(objMessageBodyPart);
			
			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			if (ex instanceof AddressException) {
				System.err.println("Problems with Email Address: " + ex.getMessage());
			} else {
				System.err.println(ex.getMessage());
			}
		}
	
	}
}
