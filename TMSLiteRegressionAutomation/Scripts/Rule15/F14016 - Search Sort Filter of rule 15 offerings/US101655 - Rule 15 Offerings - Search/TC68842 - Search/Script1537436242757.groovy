import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.commonactions.Rule15Actions
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
import com.ge.tms.commonactions.Rule15Actions
import com.ge.tms.commonactions.CommonClickEvents
import com.ge.tms.constants.Rule15OfferingsPageConstants

/**
 * Method to click on Rule 15 offer tab from Menu
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab'()

/**
 *Create a instance from actions class
 */
Rule15Actions actions = new Rule15Actions()

/**
 * Search for wrong text in search field
 */
actions.setValueInSearchfield(Rule15OfferingsPageConstants.EQUIPMENT_ID)

/**
 * Verify the message no offers found
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.wrongSearchResultsFound'()

/**
 * Search for Train Id
 */
actions.setValueInSearchfield(Rule15OfferingsPageConstants.TRAIN_ID)

/**
 * Verify the correct train id searched correctly
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyTrainID'()
WebUI.delay(2)

/**
 * Search for station Id
 */
actions.setValueInSearchfield(Rule15OfferingsPageConstants.STATION_ID)
WebUI.delay(2)

/**
 * Verify the correct station id searched correctly
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyStationID'()
WebUI.delay(2)

/**
 * Hold the test scenario, because of the DE73090
 * Search for Equipment Id
 * 
 * actions.setValueInSearchfield(Rule15PageConstants.EQUIPMENT_ID)

/**
 * Verify the correct Equipment id searched correctly
 * 
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyEquipmenntID'()
 */
