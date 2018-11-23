import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import java.util.List as List

/**
 * This Test Case is for search Customer
 * 
	 * Steps:
	 * 1. Login to TMS Application.
	 * 2. CLick On System COnfiguration/Manage Customers.
	 * 3. Verify Page header.
	 * 4. Enter valid customer ID in the Search Text Box and verify the results.
	 * 5. Enter the partial text in the Search Text Box and verify the results 
	 * 6. Enter the valid address type and Verify the results.
	 * 7. Close the browser.
 * 
 */

/**
 *  Click On Manage Customer Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers'()

/**
 *  Verify Page Header
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerPageHeader'()

/**
 * Get the customer ID and Address Key from selected  Customer
 */
String[] customerToSearch = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/firstCustomerInList')).split(
    '-')

/**
 *  Variable to hold Customer ID
 */
String customerId = customerToSearch[0]

/**
 *  Variable to hold Address Key
 */
String addressKey = customerToSearch[1]

/**
 * Enter Text into Search Box
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'), 
    customerId)

/**
 *  Call Customer ID Search method and verify the search results
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithCustomerId'(customerId)

/**
 * To Clear the test box value
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'))

/**
 * Set the value into the test box
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'), 
    addressKey)

/**
 *  Call Address Key Search method and verify the search results
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithAddressType'(addressKey)

/**
 * Clear the test box
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'))

/**
 * Holds the Partial Text to search
 */
String partialText = customerId.substring(0, 3)

/**
 * Set the partial text for search
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'), 
    partialText)

/**
 * Call Partial Search for Customer ID method
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithPartialCustomerId'(partialText)

/**
 * Clear the test box
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'))

/**
 * Holds the Partial Text to search
 */
int lengthid =customerId.length() - 1
String partialTextSecond = customerId.substring(3, customerId.length() - 1)

/**
 * Call Partial Search for Customer ID method
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithPartialCustomerId'(partialText)

/**
 * Clear the text
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'))

/**
 *  Holds Partial Address Key
 */
String partialTextAddrKey = addressKey.substring(0, 2)

/**
 * Set the partial text for search
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'),
	partialTextAddrKey)

/**
 * Call Partial Search for Address Key method
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithPartialAddrKey'(partialTextAddrKey)

