import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.junit.After as After
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.ResponseObject;
import com.kms.katalon.core.testobject.RequestObject;

/**
 * TC59502-View Customer
 
	 Steps:
	 	1. Login to TMS Application with valid credentials
		2. Click on Manage Customers Tab
		3. Verify Customes Size
		4. Verify Customers  loaded in the left pane
		4. Select any customer
		5. Customer details should be populated in the right pane.
		6. Verify the Customer details with api response.
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
 * Webservice call to get the customers data
 */

ResponseObject customersData = WS.sendRequest(findTestObject('customerServices/GET-AllCustomersData'))

/**
 * Calling verifyCustomerSize method to verify the actual customer size with the expected customer size
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerSize'(customersData)

/**
 * To verify the background color of the default selected customer
 */
CustomKeywords.'com.ge.tms.util.CommonUtilities.verifyDefualtSelectedBackground'(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/ToCheckBgColor'))

/** 
 * Getting customerId text from the list
 * hold the customer if partronId variable
 */
def partronId = WebUI.getText(findTestObject('F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/button_Customer 4ABC'))

/**
 * Webservice call to get the selected customer data
 * Hold the service response in selectedCustomerData object
 */

ResponseObject selectedCustomerData = WS.sendRequest(findTestObject('customerServices/GET-SelectedCustomer', [('custId') : partronId.split("-")[0]]))
/**
 *  Call method to Verify Right Pane header
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerRightPageHeader'(selectedCustomerData)

/**
 * Call method to verify CustomerDetails with customer api response
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerDetailsInRightPane'(partronId.split("-")[0])
