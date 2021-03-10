package stockmarket;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	
	public static WebDriver driver;
	public String url;
	public static ExtentReports reports;
	public static ExtentTest test;

	
	@BeforeSuite
	public void beforeSuite() {
	  
		String currentPath = System.getProperty("user.dir");
		System.out.println(currentPath);
		reports = new ExtentReports(System.getProperty("user.dir") + File.separator + 
				"target" + File.separator + 
				"HTMLReports" + File.separator + "ExtentReport.html");
		test = reports.startTest("Stock Price");
	  
	}
  

	public static void setupBrowser(String browserName, String url) {
		  
		if(browserName.equalsIgnoreCase("chrome")) {
			chromeSetup();
			driver.get(url);
		}
	}
	
	public static void chromeSetup() {
		try {
				System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
				
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("\nError while setting up chrome browser");
			e.printStackTrace();
		}
	}
  
  @AfterSuite
  public void afterSuite() {
	  try {
		Thread.sleep(2000);
		driver.quit();
		reports.endTest(test);
		reports.flush();
	  } catch (Exception e) {
		  // TODO: handle exception
		  System.err.println("Error while closing browser");
		  e.printStackTrace();
	  }
  }

}
