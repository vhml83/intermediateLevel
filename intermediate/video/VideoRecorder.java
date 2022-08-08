package com.intermediate.video;


import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class VideoRecorder {
	private ScreenRecorder screenRecorder;
	String baseUrl = "http://www.google.com";
	String chromePath = System.getProperty("user.dir") + "\\IntermediateLevel\\drivers\\chromedriver.exe";
	WebDriver myDriver;
	
	@Test
	public void videoTest() throws IOException {
		VideoRecorder videoRec = new VideoRecorder();
		System.setProperty("webdriver.chrome.driver", chromePath);
		myDriver = new ChromeDriver();
		myDriver.manage().window().maximize();
		videoRec.startRecording(System.getProperty("user.dir")+"\\video\\");
		myDriver.get(baseUrl);
		WebElement element = myDriver.findElement(By.name("q"));
		element.sendKeys("testing");
		element.submit();
		System.out.println("Page tile is: " + myDriver.getTitle());
		myDriver.quit();
		
		videoRec.stopRecording();
	}

	private void startRecording(String videoPath) {
		try{
			File file = new File(videoPath);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;
			
			Rectangle captureSize = new Rectangle(0,0, width, height);
			
			GraphicsConfiguration gc = GraphicsEnvironment
					.getLocalGraphicsEnvironment()
					.getDefaultScreenDevice()
					.getDefaultConfiguration();
			
			this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
	                 new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	                 new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	                      CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	                      DepthKey, 24, FrameRateKey, Rational.valueOf(15),
	                      QualityKey, 1.0f,
	                      KeyFrameIntervalKey, 15 * 60),
	                 new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
	                      FrameRateKey, Rational.valueOf(30)),
	                 null, file, "ScreenRecorded");
	         
	       this.screenRecorder.start();

				
		}catch(Exception ex){
			
		}
		
	}
	private void stopRecording() throws IOException {
		this.screenRecorder.stop();
		
	}
}
