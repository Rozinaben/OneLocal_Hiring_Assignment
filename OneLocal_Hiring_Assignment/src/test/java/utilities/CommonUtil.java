package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtil {

	public static String getPropertyValue(String filename, String key) throws FileNotFoundException {

		String fileLocation = "src\\test\\resources"; // 1 which folder
		FileInputStream fis = new FileInputStream(fileLocation + "\\" + filename); // go to that folder and get the file

		Properties p = new Properties(); // this is already written class in java to read properties file
		try {
			p.load(fis); // load the file
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return p.getProperty(key); // return the value to use in our scripts.
	}

	public static String getCurrentDate() {
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd-hh_mm_ss"));

		return date;
	}

	public static void takeScreenShotscreenshots(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("\\" + getCurrentDate() + ".png");
		try {
			System.out.println(1);
			FileUtils.copyFile(src, dest);
			System.out.println(2);
		} catch (IOException e) {
			System.out.println(3);
			throw new RuntimeException(e);
		}
	}

	public static void takeScreenShot(WebElement element) {
		TakesScreenshot ts = (TakesScreenshot) element;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshots\\" + getCurrentDate() + ".png");
		try {
			System.out.println(1);
			FileUtils.copyFile(src, dest);
			System.out.println(2);
		} catch (IOException e) {
			System.out.println(3);
			throw new RuntimeException(e);
		}
	}
	
	public static void pauseExecution(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
