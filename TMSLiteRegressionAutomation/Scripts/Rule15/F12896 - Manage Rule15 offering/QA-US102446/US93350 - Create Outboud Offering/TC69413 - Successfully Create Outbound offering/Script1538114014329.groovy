import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.commonactions.Rule15Actions
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
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
import com.ge.tms.commonactions.Rule15Actions
import com.ge.tms.commonactions.CommonClickEvents
import com.ge.tms.constants.Rule15OfferingsPageConstants
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

/**
 * Test Steps :
 * 
 * Login to application 
 * Navigate to Rule 15 Offering tab
 * Click on Create New offering button
 * Fill all the mandatory fields in the page
 * Click on Next button
 * Select the Equipment cars
 * Click on Continue
 * Verify that User get a Continue confirmation message
 * Click on the Submit button
 * Verify that rule 15 offering is successfully created by verifying the View
 */

/**
 * User to login to the application successfully
 */
CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()

/**
 * Method to click on Rule 15 offer tab from Menu
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab'()

/**
 *Create a instance from actions class
 */
Rule15Actions actions = new Rule15Actions()

/**
* Click on the Create New Rule offering button
*/
actions.clickOnCreateNewRule15OfferingButton()

/**
*  Add values to create a new offering
*/
actions.addValuesToCreateNewRule15Offering()

/**
 * Verify that the confirmatiom message for sending the Rule 15 offer 
 * contains the TrainId and Railroad selected in page 1
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyRule15ConfirmationMessage'(actions.rule15TrainId, actions.rule15RailroadSelected)

/**
 * Click on the senb button in the confirmation box
 */
actions.clickOnSendRule15Offering()

/**
 * Search for the newly created train id in left panel
 */
actions.searchByTrainId(actions.rule15TrainId)

/**
 * Calling method to get the API response to validate
 */
ResponseObject responseObject = actions.getOfferingDetails(actions.rule15TrainId)

/**
 * Verify the response with Actual UI Data
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyRule15Offering'(responseObject)
