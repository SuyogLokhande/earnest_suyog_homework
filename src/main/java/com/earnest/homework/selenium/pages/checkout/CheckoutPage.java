//
//            Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Jul 30, 2016

package com.earnest.homework.selenium.pages.checkout;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.earnest.homework.selenium.utils.TestUtils;
import com.earnest.homework.selenium.pages.common.Page;


public class CheckoutPage extends Page{
	private static final Logger logger = Logger.getLogger(CheckoutPage.class);   

	//Better to use CSS value than xpath. So if developer moved from one panel to other it will not break this script.
	//Finding all required elements required for CheckoutPage.
	
	@FindBy(css="[id='subtotal']")
    private WebElement subtotalField;
    
    @FindBy(css="[id='taxes']")
    private WebElement taxesField;
    
    @FindBy(css="[id='total']")
    private WebElement totalField;
    
	public CheckoutPage(WebDriver driver) {
        super(driver);
    }   
    
	
	public ArrayList<String> confirmOrder(WebDriver driver){
		ArrayList<String> confirmOrderFromGUI = new ArrayList<String>();
		
		try{
			logger.info("Grabbing SubTotal value from confirmation page.");
			TestUtils.highlightElement(driver, subtotalField);		
			String subTotalFromGUI = TestUtils.getText(driver, subtotalField);
			logger.debug("Adding SubTotal to ArrayList.");
			confirmOrderFromGUI.add(0,subTotalFromGUI);
		} catch (NoSuchElementException e) {
            logger.error("subtotalField Element cannot be found.");           
        }	
		
		try{
			logger.info("Grabbing Tax value from confirmation page.");
			TestUtils.highlightElement(driver, taxesField);
			String taxesFromGUI = TestUtils.getText(driver, taxesField);
			logger.debug("Adding Tax to ArrayList.");
			confirmOrderFromGUI.add(1, taxesFromGUI);
		} catch (NoSuchElementException e) {
	        logger.error("taxField Element cannot be found.");           
	    }
		
		try{
			logger.info("Grabbing Total value from confirmation page.");
			TestUtils.highlightElement(driver, totalField);
			String totalFromGUI = TestUtils.getText(driver, totalField);
			logger.debug("Adding Total to ArrayList.");
			confirmOrderFromGUI.add(2, totalFromGUI);
		} catch (NoSuchElementException e) {
		    logger.error("totalField Element cannot be found.");           
		}
		
		return confirmOrderFromGUI;
		
	}

}
