import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.ge.tms.constants.CustomerPageConstants as CustomerPageConstants
import com.ge.tms.util.CommonUtilities
import com.ge.tms.customer.CustomerActions


/**
 * This Test Case is for Edit Customer
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
* CustomerActions class instance
*/
CustomerActions action= new CustomerActions()

/**
 * Adding delay because, Clicking on Manage Customers failing some times due to element not found
 */
WebUI.delay(3)

/**
 *  Click On Manage Customer Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers'()

/**
 *  Verify Page Header
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerPageHeader'()

/**
 * Select the newly created customer
 */
CustomKeywords.'com.ge.tms.customer.CustomerActions.selectNewlyCreatedCustomer'(CustomerPageConstants.BILLING)

/**
 * To click on the Actions dropdown on the Customer screen
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnCustomerActionsDropdown'()

/**
 * Select Edit Customer from dropdown
 */
WebUI.executeJavaScript(CustomerPageConstants.EDIT_CUSTOMER_SELECTOR, null)

/**
 * Delaying for 1 sec to load the form
 */
WebUI.delay(1)

/**
 * Clearing the text fields and setting up the modified text into the fields
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AltCode'))

/**
 * Modifying the alternate code value and storing in alternateCodeToUpdate
 */
String alternateCodeToUpdate = 'Update Code'

/** 
 * Setting the text in alternatecode textbox
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AltCode'), 
    alternateCodeToUpdate)

/**
 * Clear the textbox
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/Attention'))

/**
 * Modifying the Attention value and storing in attentionToUpdate
 */
String attentionToUpdate = 'Update Attention'

/**
 * Setting the text in attentionToUpdate textbox
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/Attention'), 
    attentionToUpdate)

/**
 * Clear the textbox
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey2'))

/**
 * Modifying the Address type 2 value and storing in address2ToUpdate
 */
String address2ToUpdate = 'Update Address 2'

/**
 * Setting the text in address2ToUpdate textbox
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey2'), 
    address2ToUpdate)

/**
 * Clear the textbox
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/MilePost'))

/**
 * Modifying the Mile Post value and storing in milePostToUpdate
 */
String milePostToUpdate = action.randomMilePost()

/**
 * Setting the text in address2ToUpdate textbox
 */
WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/MilePost'), 
    milePostToUpdate)

/**
 * Clicking on save button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton'))

/**
 * Delay for 1 sec to complete save action and load the data again on page
 */
WebUI.delay(1)

/**
 * Getting the field values again after modification to verify with the original
 */
String alternateCodeModified = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/altCode'))

String attentionModified = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/attention'))

String address2Modified = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/address'))

String milePostModified = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/milepost'))

/**
 * Verify the modified values in the view page
 */
WebUI.verifyEqual(alternateCodeToUpdate, alternateCodeModified)

WebUI.verifyEqual(attentionToUpdate, attentionModified)

WebUI.verifyEqual(milePostToUpdate, milePostModified)

