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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.ge.tms.constants.CustomerPageConstants as CustomerPageConstants

/**
 * TC60946: Add Customer basic validation checks
 * 
 Steps:
 1. Login to TMS Application with valid credentials
 2. Click on Manage Customers Tab
 3. Click on Add Customer Button.
 4. Verify Save Button Enable and disable.
 5. Verify Cancel button.
 6. Verify the mandatory fields error messages ( Customer ID, Customer Name, AddressKeyType and Customer Type )
 7. Verifying Station data with Service call
 8. Verifying Zones data with Service call
 9. Verifying Tracks data with Service call
 10. Add Test Data to the fields
 11. Click on Cancel button and check the page
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
 * Click on Add Customer Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddCustomerButton'))

/**
 * Giving delay of 1 sec to load the form
 */
WebUI.delay(1)

/**
 * Getting saveButtonObject to verify Save button enable/disable
 * Method call to verify the Save Button state ( Enable/Disable )
 */
TestObject saveButtonObject = findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton')

CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifySaveButtonDisabled'(saveButtonObject)

/**
 * Get the cancel button text and holding in cancelButtonText Object
 * Verify Cancel Button Text with the expected text
 */
String cancelButtonText = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/CancelButton'))

WebUI.verifyEqual(cancelButtonText, CustomerPageConstants.CANCEL)

/**
 * Verify mandatory field validation ( Customer Name, Customer ID, Address Key, Customer Type ) and check correct error messages
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyMandatoryFieldsErrorMessages'()

/**
 * Verifying Station data with Service call.
 * Selecting the Station
 * Calling the Stations API and storing the respone object
 * Get the size of the Stations dropdown and store in the expectedStationSize
 * Call verifyStationsInCustomer(stationsObject, expectedStationsSize) method.
 */
WebUI.executeJavaScript(CustomerPageConstants.ONLINE_STATION_SELECTOR, null)

ResponseObject stationsObject = WS.sendRequest(findTestObject('Object Repository/customerServices/GET-AllStations'))

String expectedStationsSize = WebUI.executeJavaScript(CustomerPageConstants.STATION_DROPDOWN_SIZE, null)

CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyStationsInCustomer'(stationsObject, expectedStationsSize)

/**
 * Verifying Station data with Service call.
 * Selecting the Station
 * Get the Selected Station Text
 * Calling the Zones API and storing the respone object
 * Get the size of the Stations dropdown and store in the expectedStationSize
 * Call verifyStationsInCustomer(stationsObject, expectedStationsSize) method.
 */
WebUI.executeJavaScript(CustomerPageConstants.ONLINE_STATION_SELECTOR, null)

def selectedStation = WebUI.executeJavaScript(CustomerPageConstants.ONLINE_STATION_SELECTED_TEXT, null)

ResponseObject zonesObject = WS.sendRequest(findTestObject('customerServices/GET-AllZonesForSelectedStation', [('zoneId') : CustomerPageConstants.STATION_ID]))

String expectedZonesSize = WebUI.executeJavaScript(CustomerPageConstants.ZONES_DROPDOWN_SIZE, null)

CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyZonesInCustomer'(zonesObject, expectedZonesSize)

/**
 * Verifying Station data with Service call.
 * Selecting the Station
 * Calling the Stations API and storing the respone object
 * Get the size of the Stations dropdown and store in the expectedStationSize
 * Call verifyStationsInCustomer(stationsObject, expectedStationsSize) method.
 */
WebUI.executeJavaScript(CustomerPageConstants.DEFAULT_STATION_TWO_SELECTOR, null)

def selectedDefaultStation = WebUI.executeJavaScript(CustomerPageConstants.DEFAULT_STATION_SELECTED_TEXT, null)

ResponseObject tracksObject = WS.sendRequest(findTestObject('customerServices/GET-AllTracksForDefaultStation', [('defaultStation') : CustomerPageConstants.STATION_ID]))

String expectedTracksSize = WebUI.executeJavaScript(CustomerPageConstants.DEFAULT_TRACKS_DROPDOWN_SIZE, null)

CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyTracksInCustomer'(tracksObject, expectedTracksSize)

/**
 * Calling addCustomer Method, Where the form will be filled with addCustomer.json data
 */
CustomKeywords.'com.ge.tms.customer.CustomerActions.addCustomer'(CustomerPageConstants.BILLING)

/**
 * Getting Save Button Object to check the button state ( Enable/Disable )
 */
TestObject saveButtonAfterFormFillMandatory = findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton')

/**
 * Calling verifySaveButtonEnabled method to verify whether Save button Enabled or not
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifySaveButtonEnabled'(saveButtonAfterFormFillMandatory)

/**
 * Getting Add Button state to verify after the Cancel. State of Add button should be Active.
 */
TestObject addCustomerBtn = findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddCustomerButton')

/**
 * Clicking on Cancel Button
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCancelButtonInAddCustomer'(addCustomerBtn)
