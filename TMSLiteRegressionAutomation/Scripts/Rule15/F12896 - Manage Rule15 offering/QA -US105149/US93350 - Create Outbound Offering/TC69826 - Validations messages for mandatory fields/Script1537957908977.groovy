import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.ge.tms.commonactions.Rule15Actions
import com.ge.tms.rule15.Rule15Verification

/**
 * Test Steps: Create Rule15 - Manadatory field validations
 *1. Login to application 
 *2.Navigate to Rule 15 Offering tab
 *3.Click on Create New offering button
 *4.Provide invalid values to all mandatory fields
 *5.Verify that 
 	1. Proper Error message is displayed for each fields
 	2. Next button is disabled
 */

/**
 * User to login to the application successfully
 */
CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()

WebUI.delay(2)
/**
 * Creating object for Action class
 */
Rule15Actions rule15Action = new Rule15Actions()

/**
 * Method to click on Rule 15 offer tab from Menu
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab'()

/**
 *  Generate error message for a railroad in rule 15 page
 */
rule15Action.navigateAndViewErrorMessageOfRailRoad()

/**
 * Verification of error message displayed for railroad
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyErrorMessagesForRailroad'()

/**
 *  Verification of error messages for other mandatory fields
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyErrorMessagesForMandatoryFields'()
