package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static steps.PageInitializer.initializePages;

public class Base {
	public static WebDriver driver;
	
	public static void openBrowser() {
		PropertyReader.loadProperty(Constants.propertyFilePath);
		switch (PropertyReader.readProperty("browser")) {
			case "chrome" -> {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			case "firefox" -> {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			case "edge" -> {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			default -> System.out.println("NO SUCH BROWSER IN DIRECTORY");
		}
		initializePages();
		driver.get(PropertyReader.readProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("Test case is started");
	}
	
	public static byte[] takeScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourceFile,
					new File(Constants.SCREENSHOT_FILEPATH + fileName + " " +
							getTimeStamp("yyyy-MM-dd-HH-mm-ss,SSS") + ".png"));
			
		} catch (
				IOException e) {
			e.printStackTrace();
		}
		return picBytes;
	}
	public static String getTimeStamp(String pattern) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static void quitBrowser() {
		driver.quit();
		Log.endTestCase("Test is ended");
	}
	
	private static WebDriverWait getWait() {
		return new WebDriverWait(driver, 20);
	}
	
	public static void click(WebElement element) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Log.warning("No such element");
		}
	}
	
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void elementBeVisible(WebElement element) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Log.warning("No such element");
		}
	}
	
	private static JavascriptExecutor getJS() {
		return (JavascriptExecutor) driver;
	}
	
	/**
	 * Method scrolls window to WebElement on page and wait until element be visible.
	 *
	 * @param element
	 * @return element
	 */
	public static WebElement scrollIntoView(WebElement element) {
		try {
			elementBeVisible(element);
			getJS().executeScript("arguments[0].scrollIntoView();", element);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Log.warning("No such element ");
		}
		return element;
	}
	
	private static Select getSelect(WebElement element) {
		return new Select(element);
	}
	
	/**
	 * Method for select option from dropDown element with included visible text.
	 *
	 * @param element
	 * @param target
	 */
	public static void selectDropdownByText(WebElement element, String target) {
		try {
			getSelect(element).selectByVisibleText(target);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Log.warning("No such element ");
		}
	}
	
	public static void JsClick(WebElement element) {
		getJS().executeScript("arguments[0].click();", element);
	}
	
	
	public static void assertTrue(boolean element) {
		Assert.assertTrue(element);
	}
	
}
