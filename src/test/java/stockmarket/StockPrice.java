package stockmarket;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class StockPrice extends BaseClass {
	
	 @Parameters({ "url", "browser" })
	 @Test
	 public void launchURl(String url, String browser) {
		 try {
			System.out.println("Launching browser : " + browser);
			BaseClass.setupBrowser(browser, url);
			
			if(driver.getTitle().contains("Yahoo Finance")) {
				 BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				 assertEquals(true, true);
			 }
			 else {
				 BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				 assertEquals(false, true);
			 }
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error while launching url");
			e.printStackTrace();
			assertEquals(false, true);
		}
	 }
	 
	 @Parameters({ "ticker" })
	 @Test
	 public void searchForTicker(String ticker) {
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, 10);
			 WebElement inputElement = wait.until(
			         ExpectedConditions.visibilityOfElementLocated(By.id("yfin-usr-qry")));
			
			 inputElement.sendKeys(ticker);
			 
			 WebElement searchButton = driver.findElement(By.id("header-desktop-search-button"));
			 searchButton.click();
			 
			
			 WebElement ticketElement = wait.until(
				         ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]")));
				 
			 
			 if(ticketElement.isDisplayed() && driver.getTitle().contains("AAPL")) {
				 BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				 assertEquals(true, true);
			 }
			 else {
				 BaseClass.test.log(LogStatus.FAIL, "Test Failed");
				 assertEquals(false, true);
			 }
			 
		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(false, true);
		}
	 }
	 
	//*[@id="quote-header-info"]/div[3]/div[1]/div/span[1]
	 
	 @Parameters({ "expectedTickerPrice" })
	 @Test
	 public void validateTickerPrice(String expectedTickerPrice) {
		 try {
			 
			WebElement tickerPrice = driver.findElement(By.xpath("//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]"));
			System.out.println("Ticket Price : " + tickerPrice.getText());
			double dblTickerPrice = Double.parseDouble(tickerPrice.getText());
			double expTickerPrice = Double.parseDouble(expectedTickerPrice);
			
			BaseClass.test.log(LogStatus.INFO, "Expected Ticker Price : " + expectedTickerPrice);
			BaseClass.test.log(LogStatus.INFO, "Current  Ticker Price : " + dblTickerPrice);
			
			
			if(dblTickerPrice >= expTickerPrice ) {
				BaseClass.test.log(LogStatus.PASS, "Navigated to the specified URL");
				assertEquals(true, true);
			} else {
				BaseClass.test.log(LogStatus.FAIL, "Ticker Price not matching with expected Price");
				assertEquals(false, true);
			}
			
		} catch(NumberFormatException e) {
			System.err.println("\n\nNot able to convert to number.");
			e.printStackTrace();
			assertEquals(false, true);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("\n\nNot able to get ticker price");
			e.printStackTrace();
			assertEquals(false, true);
		}
	 }
	 
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
