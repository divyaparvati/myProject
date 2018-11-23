import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.ge.tms.constants.RailroadPageConstants
import com.ge.tms.railroad.RailroadVerification
import com.ge.tms.util.CommonUtilities
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
import com.ge.tms.commonactions.RailroadActions

/**
 * Test Steps:
 * 
 * 1. Login to Application Successfully
 * 2. Select random Railroad and a random station under that railroad
 * 3. Click on the Add New Zone Button.
 * 4. Validate that the Save button in Add New Zone is disabled by default
 * 5. Validate zone id , Zone Name and Zone Type 
 * 6. Verify Save button is disabled for any validation error message
 * 7. Click on Cancel to go back
 * 8. Click on the Add New Zone button
 * 9. Provide all valid inputs
 * 10. Validate that Save button is enabled
 * 11. Click on Save button
 * 12. Verify that new zone is successfully created
 */

/**
 * Create the instance of the common util class
 */
CommonUtilities util = new CommonUtilities()

/**
 *  Action class for railroad
 */
RailroadVerification rverify = new RailroadVerification()

/**
 *  Action class for railroad
 */
RailroadActions actions = new RailroadActions()

/**
 * Click on the Railroad Setup
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRailroadSetup'()

/**
 * Click on the Select Railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnSelectRailroadDropdown'()

/**
 * Select any random railroad from the the Select railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.selectRandomRailRoad'()
WebUI.delay(2)

/**
 * Set Invalid Station/Zone ID to search Station and Zone serch field
 */
actions.setInvalidtxtForStationzoneSearchField()

/**
 *  Call Zone ID Search method and verify the search results
 */
 CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyMessagewhennoStationandZoneAvailable'(actions.GetNoresultText())
 WebUI.delay(2)
 