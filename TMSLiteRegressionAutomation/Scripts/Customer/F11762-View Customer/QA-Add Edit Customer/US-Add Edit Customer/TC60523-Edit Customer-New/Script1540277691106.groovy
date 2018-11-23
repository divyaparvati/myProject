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
 * Method call to Login to TMS Lite Application
 */
CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()

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
 * Steps of edit customer
 */
action.editCustomer()

/**
 * verify Edited CustomerDetails
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerEditedFields'(action.customerID)
