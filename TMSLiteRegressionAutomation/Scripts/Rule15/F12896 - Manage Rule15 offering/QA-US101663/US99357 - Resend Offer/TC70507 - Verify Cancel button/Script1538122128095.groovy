import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

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
Login to application 
Navigate to Rule 15 Offering tab
Click on Create New offering button
Fill all the mandatory fields in the page
Click on Next button
Select the Equipment cars
Click on Continue
Click on the Submit button
Successfully create an offer
Click on the Actions button
Select Resend Offer
Edit one or two values 
Click on the Next button
Confirm the message
Verify that resend offer has the new edited values in view
 */

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
 * Click on the send button in the confirmation box
 */
actions.clickOnSendRule15Offering()

/**
 * Search for the newly created train id in left panel
 */
actions.searchByTrainId(actions.rule15TrainId)

/**
 * Click on the Actions dropdown of the created offer
 */
actions.clickOnActions()

/**
 * Select the Resend Offer 
 */
actions.selectResendOfferInActionsDropdown()

/**
 * Resend the offer by editing Train Id and Authorization number 
 */
actions.resendRule15Offer()

/**
 * CLick on the cancel button after the edits are made
 */
actions.clickOnCancelButtonInRule15()

/**
 * Search for the train id in left panel
 */
actions.searchByTrainId(actions.rule15TrainId)

/**
 * Verify that no edits are made on the Rule 15 offer after cancel
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyResendOfferOfRule15'(actions.rule15TrainId , actions.rule15AuthNumber.toString())

