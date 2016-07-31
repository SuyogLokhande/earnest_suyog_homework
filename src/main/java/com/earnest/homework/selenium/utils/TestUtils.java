//
//           Suyog Lokhande
//              Copyright
//      Confidential and Proprietary
//          ALL RIGHTS RESERVED
//           405 - 200 - 7802

// Jul 30, 2016

package com.earnest.homework.selenium.utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestUtils {

    public static void write(WebElement we, String s) {
        we.clear();
        // Do not send null as this will fail for Chrome. From my experience :)
        we.sendKeys(s == null ? "": s);
    }
    

	
    public static String getText(WebDriver driver, WebElement value) {
        
        String res = "";
        //The text value to capture
        String textOfValue = value.getText().trim();

        res = textOfValue;     
        return res;
    }
    
    public static void highlightElement(WebDriver driver, WebElement element) {
		for (int i = 0; i < 2; i++) { 
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 5px solid yellow;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: green; border: 5px solid green;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, ""); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: blue; border: 5px solid blue;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");  
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 5px solid red;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 5px solid yellow;"); 
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
		
	}
 
}