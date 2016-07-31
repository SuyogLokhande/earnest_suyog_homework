# earnest_suyog_homework
Suyog homework for QA position

Problem URL: https://jungle-socks.herokuapp.com/

Problem definition: Webshop mentioned in above URL consists of 2 pages. The first page lists our product catalog with prices and contains a
form to select the quantity of each item and enter the customer's state. The confirmation page shows
a list of selected products along with the subtotal, tax and total purchase price.

we've recently learned that we need to apply state tax based on our customers’ addresses. We'd like
to ensure that state tax is always correctly applied.

How to run tests:
1. Run StateTaxCalculationTest.java as 'testNG test'
	Path: src/test/com.earnest.homework.selenium.tests.stateTaxCalculator has StateTaxCalculationTest.java.
   Currently all test cases realted with different state delivery are kept there.

Test cases covered:
1. specialTaxRateForCATest (CA has 8% tax rate)
2. specialTaxRateForNYTest (NY has 6% tax rate)   
3. zeroTaxRateStateTest (MN has 0% tax rate)
4. negativeQuantityTest (Socks quantity as a negative integer)
5. allZeroQuantityTest (All quantities are zero)
6. outOfStockQuantityTest (Quantity entered is greater than available stock)
7. maxStockCheckoutTest (Quantity entered as maximum available stock. 5% state tax)
8. invalidStockQuantityTest (Quantity entered as a string)
   
Information about project:
1. src/main/java/com.earnest.homework.selenium.pages.catalog/CatalogPage.java will gather all fields required to provide input (socks quantity).
  CatalogPage also has 'orderItems' methos which will order as per DataProvider's input, select state from drop doen menu and then click on 'Checkout' button.
2. src/main/java/com.earnest.homework.selenium.pages.checkout/CheckoutPage.java will gather SubTotal, tax and total amout from the confirmation page and put them in an ArrayList.
3. src/main/java/com.earnest.homework.selenium.utils/TestUtils.java has all commonly utilized methods like getText, waitForPageLoad, highlightElement, write.
4. All pages related class are extended from src/main/java/ com.earnest.homework.selenium.pages.common/Page.java

Future development:
1. Read input from src/test/resources/csv_input.csv for regression.
2. Read environment settings (like URL, login credentials etc.) from environment.properties file.
3. Convert java project to 'maven' project and then use Jenkins to build the project and execute tests.

Suggestions for more testability:
1. Provide unique ID field for all HTML elements. So all elements canbe found using that.
2. Provide default state string (like 'select a state') for State drop down menu. So user has to enter it.
3. Checkout button shall be by default disabled and need to be enabled only after doing some mandatory checks.
4. Provide error/warnings if there is anything wrong while providing input.




  
  
