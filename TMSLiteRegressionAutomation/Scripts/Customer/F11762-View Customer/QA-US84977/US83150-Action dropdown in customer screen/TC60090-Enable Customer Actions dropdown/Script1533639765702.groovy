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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**
 * To click on the Manage Customer under System configurations Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers'()

WebUI.delay(2)

// Click on the first customer in the list  - * To be removed when default customer is already selected *
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/firstCustomerInList'))

/**
 * To click on the Actions dropdown on the Customer screen
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnCustomerActionsDropdown'()

/**
 * Verify if the Actions dropdown has Edit and Delete options
 */
CustomKeywords.'com.ge.tms.customerverify.CustomerVerification.verifyCustomerActionsDropdownValues'()

/**
 * To click on the Edit customer and verify if its clickable
 */
WebUI.executeJavaScript('document.querySelector("#customer-actions-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1)").click()', 
    null)

/**
 * Click on the Cancel button to go back to the previous screen
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnCancelButton'()

/**
 * To click on the Actions dropdown on the Customer screen
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnCustomerActionsDropdown'()

/**
 * To click on the Delete customer and verify if its clickable
 */
WebUI.executeJavaScript('document.querySelector("#customer-actions-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2)").click()', 
    null)

/**
 * Click on the Cancel button to go back to the previous screen
 */
/**
 * Below Line Commented because, The functionality was still not developed. This can be used once the functionality developed.
 * CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnCancelButton'()
 */
