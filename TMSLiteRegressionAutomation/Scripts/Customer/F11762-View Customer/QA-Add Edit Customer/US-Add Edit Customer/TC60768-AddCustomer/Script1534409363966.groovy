import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ge.tms.constants.CustomerPageConstants as CustomerPageConstants
import com.ge.tms.customer.CustomerActions
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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/**
 * TC59502-Add Customer (Create Billing, Inactive, Transportation Customers and customer with only mandatory fields )
 Steps:
 1. Login to TMS Application with valid credentials
 2. Click on Manage Customers Tab
 3. Click on Add Customer Button
 4. Fill the customer form and click on save button - Create Billing, Inactive, Transportation Customers.
 5. Verify the newly created data on UI
 */

/**
 * CustomerActions class instance
 */
CustomerActions action= new CustomerActions()

/**
 *  Click On Manage Customer Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers'()

/**
 *  Verify Page Header
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerPageHeader'()

/**
 * Click on Add Customer Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddCustomerButton'))

/**
 * Giving delay of 2 sec to load the form
 */
WebUI.delay(2)

/**
 * Calling addCustomer Method, Where the form will be filled with addCustomer.json data - Creating Billing Customer
 */
CustomKeywords.'com.ge.tms.customer.CustomerActions.addCustomer'(CustomerPageConstants.BILLING)

/**
 * Clicking on Save Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton'))

/**
 * verify CustomerDetails
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerDetailsInRightPane'(action.customerID)

WebUI.delay(3)

/**
 * Click on Add Customer Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddCustomerButton'))

/**
 * Calling addCustomer Method, Where the form will be filled with addCustomer.json data - Creating Inactive Customer
 */
CustomKeywords.'com.ge.tms.customer.CustomerActions.addCustomer'(CustomerPageConstants.INACTIVE)

/**
 * Clicking on Save Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton'))

/**
 * verify CustomerDetails
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerDetailsInRightPane'(action.customerID)

WebUI.delay(3)

/**
 * Click on Add Customer Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddCustomerButton'))

/**
 * Calling addCustomer Method, Where the form will be filled with addCustomer.json data - Creating Transportation Customer
 */
CustomKeywords.'com.ge.tms.customer.CustomerActions.addCustomer'(CustomerPageConstants.TRANSPORTATION)

/**
 * Clicking on Save Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton'))

/**
 * verify CustomerDetails
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerDetailsInRightPane'(action.customerID)
