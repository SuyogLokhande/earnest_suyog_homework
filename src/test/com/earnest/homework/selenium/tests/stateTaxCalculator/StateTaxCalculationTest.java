//
//            Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Jul 30, 2016

package com.earnest.homework.selenium.tests.stateTaxCalculator;


import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;


import com.earnest.homework.selenium.pages.catalog.CatalogPage;
import com.earnest.homework.selenium.pages.checkout.CheckoutPage;
import com.earnest.homework.selenium.utils.TestUtils;



public class StateTaxCalculationTest {
	private WebDriver driver;
	private static String baseUrl;
   
    final static Logger logger = Logger.getLogger(StateTaxCalculationTest.class);
    
    private CatalogPage catalogPage;
    private CheckoutPage checkoutPage;
    //private TestingResourceBundle resourceBundle = TestingResourceBundle.getInstance();
    //In future if suits is large, need to use CSV file for data injection.
    //private static File csvFile = new File("src/test/resources/csv_input.csv");
    

    
    @DataProvider( name = "regressionSocksQty" ) 
	public Object[][] regressionSocksQty(){
		return new Object[][]{
			{"4", "3", "2", "1", "CA", "$199.00", "$15.92", "$214.92"},
			{"1", "0", "3", "4", "NY", "$186.00", "$11.16", "$197.16"},
			{"-5", "6", "0", "1", "MN", "$137.00", "$0.00", "$137.00"},
			{"23", "12", "3", "15", "OK", "$899.00", "$44.95", "$943.95"},
			{"0", "0", "3", "0", "AL", "$105.00", "$5.25", "$110.25"},
			{"-1", "5", "3", "2", "AK", "$239.00", "$11.95", "$250.95"},
			{"2", "3", "5", "1", "CO", "$278.00", "$13.90", "$291.90"},
			{"0", "0", "0", "0", "FL", "$0.00", "$0.00", "$0.00"}
		};		
	}	
    
	@DataProvider( name = "positiveSocksQtyForSpecialCA" ) 
	public Object[][] positiveSocksQtyForSpecialCA(){
		return new Object[][]{
			{"4", "3", "2", "1", "CA", "$199.00", "$15.92", "$214.92"}
		};		
	}
	
	@DataProvider( name = "sixPercentTaxForNY" ) 
	public Object[][] sixPercentTaxForNY(){
		return new Object[][]{
			{"1", "0", "3", "4", "NY", "$186.00", "$11.16", "$197.16"}
		};		
	}
	
	
	@DataProvider( name = "maxStockFivePercentTax" ) 
	public Object[][] maxStockFivePercentTax(){
		return new Object[][]{
			{"23", "12", "3", "15", "OK", "$899.00", "$44.95", "$943.95"}
		};		
	}
	
	
	@DataProvider( name = "negativeSocksQty" ) 
	public Object[][] negativeSocksQty(){
		return new Object[][]{
			{"-1", "5", "3", "2", "AK", "$239.00", "$11.95", "$250.95"}
		};		
	}
	
	@DataProvider( name = "zeroTaxRate" ) 
	public Object[][] zeroTaxRate(){
		return new Object[][]{
			{"-5", "6", "0", "1", "MN", "$137.00", "$0.00", "$137.00"}
		};		
	}
	
	@DataProvider( name = "allZeroQty" ) 
	public Object[][] allZeroQty(){
		return new Object[][]{
			{"0", "0", "0", "0", "FL", "$0.00", "$0.00", "$0.00"}
		};		
	}
	
	@DataProvider( name = "outOfStockQty" ) 
	public Object[][] outOfStockQty(){
		return new Object[][]{
			{"2", "3", "5", "1", "CO", "$278.00", "$13.90", "$291.90"}
		};		
	}
	
	@DataProvider( name = "invalidStockQty" ) 
	public Object[][] invalidStockQty(){
		return new Object[][]{
			{"A", "3", "$", "1", "CO", "$77.00", "$3.85", "$80.85"}
		};		
	}
	
	
    @BeforeMethod
	public void beforeTest() throws InterruptedException {
    	
		this.driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		baseUrl = "https://jungle-socks.herokuapp.com/";
		
		if(logger.isDebugEnabled()){
			logger.debug("Current URL: " +baseUrl);
		}
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);		
		TestUtils.waitForPageLoad(driver);
		catalogPage = PageFactory.initElements(driver, CatalogPage.class);
		
    }
    
    @AfterMethod
    public void teardown(){
    	this.driver.close();
    }
    
    @Test(priority=1, dataProvider="positiveSocksQtyForSpecialCA")
    public void specialTaxRateForCATest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Running special 8% tax for CA test case.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
    
    @Test(priority=1, dataProvider="sixPercentTaxForNY")
    public void specialTaxRateForNYTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Running special 6% tax for NY test case.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
      	Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
    @Test(priority=1, dataProvider="zeroTaxRate")
    public void zeroTaxRateStateTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Running special 0% tax for MN test case.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
    @Test(priority=2, dataProvider="negativeSocksQty")
    public void negativeQuantityTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Providing negative socks quantity. Web page will take negative values as zero for price calculations.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }    
   
    @Test(priority=2, dataProvider="allZeroQty")
    public void allZeroQuantityTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Providing all zero quantity for checkout. Ideally check out button shall not enabled but current web page is not doing that.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
    @Test(priority=2, dataProvider="outOfStockQty")
    public void outOfStockQuantityTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Providing out of stock quantity for checkout. Ideally check out button shall not enabled but current web page is not doing that.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
    @Test(priority=2, dataProvider="maxStockFivePercentTax")
    public void maxStockCheckoutTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Providing maximum stock quantity for checkout.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
    @Test(priority=2, dataProvider="invalidStockQty")
    public void invalidStockQuantityTest(String zebraQty, String lionQty, String elephantQty, String graffeQty, String delivState, String subTotal, String tax, String total) throws InterruptedException{		
    	logger.info("Providing invalid stock quantity for checkout. Ideally check out button shall not enabled but current web page is not doing that.");
    	//Providing input in catalog page
    	catalogPage.orderItems(driver, zebraQty, lionQty, elephantQty, graffeQty, delivState);
    	
    	TestUtils.waitForPageLoad(driver);
    	checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
    	
      	ArrayList<String> confirmFromGUI = checkoutPage.confirmOrder(driver);
      	logger.info("Confirm page summary: " +(Arrays.toString(confirmFromGUI.toArray())));
		Assert.assertEquals(confirmFromGUI.get(0), subTotal);
		Assert.assertEquals(confirmFromGUI.get(1), tax);
		Assert.assertEquals(confirmFromGUI.get(2), total);
    }
    
}
