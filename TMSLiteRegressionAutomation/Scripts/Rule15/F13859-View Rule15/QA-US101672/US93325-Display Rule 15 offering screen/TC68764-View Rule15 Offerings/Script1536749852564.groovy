import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.ge.tms.constants.Rule15OfferingsPageConstants as Rule15OfferingsPageConstants
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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.ge.tms.commonactions.Rule15Actions

/**
 * Creating object for Action class
 */
Rule15Actions rule15Action = new Rule15Actions()

/**
 * Click on Rule 15 offering tab under Train offerations
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab'()

/**
 * Veriy Header of the page
 */
String actualHeaderText = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/pageHeader'))

WebUI.verifyEqual(actualHeaderText, Rule15OfferingsPageConstants.HEADER_TEXT_RULE_15_OFFERINGS)

/**
 * Verify the Add button clickable
 */
WebUI.verifyElementClickable(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/actionDropdown'), 
    FailureHandling.CONTINUE_ON_FAILURE)

/**
 * Select any random offering and get train id
 */
String trainID = CustomKeywords.'com.ge.tms.rule15.Rule15Verification.selectOfferingInLeftPane'()

/**
 * Calling method to get the API response to validate
 */
ResponseObject responseObject = rule15Action.getOfferingDetails(trainID)

/**
 * Verify the response with Actual UI Data
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyRule15Offering'(responseObject)
