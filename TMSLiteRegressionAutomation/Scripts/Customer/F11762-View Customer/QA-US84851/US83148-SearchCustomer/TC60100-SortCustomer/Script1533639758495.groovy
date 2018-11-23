import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.ge.tms.constants.CustomerPageConstants as CustomerPageConstants
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
 * This Test Case is for Sorting the Customers in the Left pane and verifying the up/down arrows for sort.
	 * Steps:
	 * 1. Login to TMS Application.
	 * 2. Click On System Configuration/Manage Customers.
	 * 3. Verify Page header.
	 * 4. Select the Customer ID from the Sort By drop down and Verify the list.List should sort by natural order by default.
	 * 5. Click on Up arrow  Icon, Now the List would be in descending order. Verify the sort list
	 * 6. Click on Down arrow  Icon, Now the List would be in Ascending order. Verify the sort list
	 * 7. Select the Customer Name from the Sort By drop down and Verify the list.List should sort by natural order by default.
	 * 8. Click on Up arrow  Icon, Now the List would be in descending order. Verify the sort list
	 * 9. Click on Down arrow  Icon, Now the List would be in Ascending order. Verify the sort list
	 * 10. Select the Address Key Type  from the Sort By drop down and Verify the list.List should sort by natural order by default.
	 * 11. Click on Up arrow  Icon, Now the List would be in descending order. Verify the sort list
	 * 12. Click on Down arrow  Icon, Now the List would be in Ascending order. Verify the sort list
	 * 13. Select the Modified Date from the Sort By drop down and Verify the list. List should sort by natural order by default.
	 * 14. Click on Up arrow  Icon, Now the List would be in descending order. Verify the sort list
	 * 15. Click on Down arrow  Icon, Now the List would be in Ascending order. Verify the sort list
	 * 16. Close the browser
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
 * Select Name from Dropdown
 */
WebUI.executeJavaScript(CustomerPageConstants.CUSTOMER_NAME_SORT_DROPDOWN, null)

/**
 * Method Call to Verify Sort of Name Default Sort
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyNameSort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.ASCENDING_ICON, null)

/**
 * Method Call to Verify Sort of Name in descending order
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyNameSort'()

/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.DESCENDING_ICON, null)

/**
 * Method Call to Verify Sort of Name in ASCENDING order
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyNameSort'()

/**
 * Select Customer ID from Dropdown
 */
WebUI.executeJavaScript(CustomerPageConstants.CUSTOMER_ID_SORT_DROPDOWN, null)

/**
 * Method Call to Verify Sort of Customer ID on default
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerIDSort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.ASCENDING_ICON, null)

/**
 * Method Call to Verify Sort of Customer ID in descending order
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerIDSort'()

/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.DESCENDING_ICON, null)

/**
 * Method Call to Verify Sort of Customer ID in Ascending order
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerIDSort'()

/**
 * Select Address Type from Dropdown
 */
WebUI.executeJavaScript(CustomerPageConstants.ADDRESS_TYPE_KEY_SORT_DROPDOWN, null)

/**
 * Method Call to Verify Sort of Address Key on default
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyAddressKeySort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.ASCENDING_ICON, null)

/**
 * Method Call to Verify Sort of Address Key in descending order
 */
/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.DESCENDING_ICON, null)

/**
 * Method Call to Verify Sort of Address Key in Ascending order
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyAddressKeySort'()

/**
 * Select Modified Date from Dropdown
 */
WebUI.executeJavaScript(CustomerPageConstants.MODIFIED_DATE_SORT_DROPDOWN, null)

/**
 * Method Call to Verify Sort by Modified Date on default
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyModifiedDateSort'()

/**
 * Clicking on Ascending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.ASCENDING_ICON, null)

/**
 * Method Call to Verify Sort by Modified Date in descending order
 */
/**
 * Clicking on Descending Order Icon
 */
WebUI.executeJavaScript(CustomerPageConstants.DESCENDING_ICON, null)

/**
 * Method Call to Verify Sort by Modified Date in Ascending order
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyModifiedDateSort'()
