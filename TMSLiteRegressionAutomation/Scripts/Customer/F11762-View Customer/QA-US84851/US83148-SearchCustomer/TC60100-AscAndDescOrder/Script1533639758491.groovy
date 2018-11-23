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

/**
 * This Test case is for Verifying Ascending and Descending order
 * 
	 * Steps:
	 * 1.  Login to TMS Application.
	 * 2.  CLick On System COnfiguration/Manage Customers.
	 * 3.  Verify Page header.
	 * 4.  Select the Customer ID from the Sort By drop down and Verify the list. List should sort by natural order.
	 * 5.  Click on Ascending Order Icon, Then the List should sort in Descending order
	 * 6.  Again Click on Descending order Icon, Then List should sort by Ascending order
	 * 7.  Select the Name from the Sort By drop down and Verify the list. List should sort by natural order.
	 * 8.  Click on Ascending Order Icon, Then the List should sort in Descending order
	 * 9.  Again Click on Descending order Icon, Then List should sort by Ascending order
	 * 10. Select Address Type from the Sort By drop down and Verify the list. List should sort by natural order.
	 * 11. Click on Ascending Order Icon, Then the List should sort in Descending order
	 * 12. Again Click on Descending order Icon, Then List should sort by Ascending order
	 * 13. Close the browser
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
 * Click on Sort By Dropdown
 */
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#trigger").click()', 
    null)

/**
 * Select Name from Dropdown
 */
WebUI.executeJavaScript('return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").click()', 
    null)

/**
 * Method Call to Verify Sort of Name
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyNameSort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript('return document.querySelectorAll("px-icon")[1].shadowRoot.querySelector("iron-icon").click()', 
    null)

/**
 * Method to verify the descending ordered list
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyNameSortWithDescendingOrder'()

/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript('return document.querySelectorAll("px-icon")[1].shadowRoot.querySelector("iron-icon").click()', 
    null)

/**
 * Method Call to Verify Sort of Name
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyNameSort'()

WebUI.delay(5)

/**
 * Select Customer ID from Dropdown
 */
WebUI.executeJavaScript('return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()', 
    null)

/**
 * Method Call to Verify Sort of Customer ID
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerIDSort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript('return document.querySelectorAll("px-icon")[1].shadowRoot.querySelector("iron-icon").click()', 
    null)

/**
 * Method to verify the descending ordered list
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerIDWithDescendingOrder'()

/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript('return document.querySelectorAll("px-icon")[1].shadowRoot.querySelector("iron-icon").click()', 
    null)

/**
 * Method Call to Verify Sort of Name
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerIDSort'()

/**
 * Select Address Type from Dropdown
 */
WebUI.executeJavaScript('return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3) > span").click()', 
    null)

/**
 * Method Call to Verify Sort of Address Key
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyAddressKeySort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript('return document.querySelectorAll("px-icon")[1].shadowRoot.querySelector("iron-icon").click()', 
    null)

/**
 * Method to verify the descending ordered list
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyAddressKeyWithDescendingOrder'()

/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript('return document.querySelectorAll("px-icon")[1].shadowRoot.querySelector("iron-icon").click()', 
    null)

/**
 * Method Call to Verify Sort of Address Key
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyAddressKeySort'()
