//
//            Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Jul 29, 2016

package com.earnest.homework.selenium.pages.catalog;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.earnest.homework.selenium.utils.TestUtils;
import com.earnest.homework.selenium.pages.common.Page;

public class CatalogPage extends Page{
    private static final Logger logger = Logger.getLogger(CatalogPage.class);       
   
   //Better to use CSS value than xpath. So if developer moved from one panel to other it will not break this script.
   //Finding all required elements required for CatalogPage
    @FindBy(css="[id='line_item_quantity_zebra']")
    private WebElement zebraQuantityInputField;
    
    @FindBy(css="[id='line_item_quantity_lion']")
    private WebElement lionQuantityInputField;
    
    @FindBy(css="[id='line_item_quantity_elephant']")
    private WebElement elephantQuantityInputField;
    
    @FindBy(css="[id='line_item_quantity_giraffe']")
    private WebElement giraffeQuantityInputField;    
   
    @FindBy(css="[name='commit']")
    private WebElement checkoutButton;
    
    public CatalogPage(WebDriver driver) {
        super(driver);
    }
    
    
    public void orderItems(WebDriver driver, String zebraQty, String lionQty, String elephantQty, String giraffeQty, String state) throws InterruptedException{
    	//Obtain the Welcome message from landing page 
        //final String welcomeMessage = TestUtils.getText(driver, driver.findElement(By.cssSelector("#h1")));
    	
    	//Enter Quantity for Zebra socks as per test case.
        try {        	
            TestUtils.write(zebraQuantityInputField, zebraQty);
            logger.info("Adding Zebra stock quantity as : " +zebraQty );
        } catch (NoSuchElementException e) {
            logger.error("zebraQuantityInputField Element cannot be found.");           
        }

    	//Enter Quantity for Lion socks as per test case.
        try {        	
            TestUtils.write(lionQuantityInputField, lionQty);
            logger.info("Adding Lion stock quantity as : " +lionQty );
        } catch (NoSuchElementException e) {
        	logger.error("lionQuantityInputField Element cannot be found.");
        }
        
    	//Enter Quantity for Elephant socks as per test case.
        try {        	
            TestUtils.write(elephantQuantityInputField, elephantQty);
            logger.info("Adding Elephant stock quantity as : " +elephantQty );
        } catch (NoSuchElementException e) {
            logger.error("elephantQuantityInputField Element cannot be found.");
        }
        
    	//Enter Quantity for Giraffe socks as per test case.
        try {        	
            TestUtils.write(giraffeQuantityInputField, giraffeQty);
            logger.info("Adding Giraffe stock quantity as : " +giraffeQty );
        } catch (NoSuchElementException e) {
            logger.error("giraffeQuantityInputField Element cannot be found.");
        } 
        
        try{
        	logger.debug("Select state drop down box first.");
	        Select droplist = new Select(driver.findElement(By.name("state")));
	        logger.debug("Select input state value provided in the test from state drop down box.");
	        droplist.selectByValue(state);
	        logger.info("Select state from drop down as : " +state );
        } catch (NoSuchElementException e) {
            logger.error("State cannot be selected.");
        } 
        
        TimeUnit.SECONDS.sleep(2);
    	
        try{
	        //Click on 'checkout' Button
	    	TestUtils.highlightElement(driver, checkoutButton);
	    	checkoutButton.click();
	    	logger.info("Clicked on checkout button.");
        } catch (NoSuchElementException e) {
            logger.error("Checkout button cannot be clicked.");
        } 
    }
}
