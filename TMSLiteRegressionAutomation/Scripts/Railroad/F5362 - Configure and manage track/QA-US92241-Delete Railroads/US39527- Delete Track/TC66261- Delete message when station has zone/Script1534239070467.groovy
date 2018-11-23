import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.ge.tms.constants.RailroadPageConstants as RailroadPageConstants
import com.ge.tms.util.CommonUtilities as CommonUtilities
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
import com.ge.tms.commonactions.RailroadActions as RailroadActions

/**
 * Test Steps:
1. Login to application 
2. Add a new station
3. Create a new zone under that station
4. Click on Delete Icon of the Station
5. Validate the delete confirmation message 
 */

/**
 * Create the instance of the common util class
 */
CommonUtilities util = new CommonUtilities()

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
 * Create a new Station 
 * Set the station id as null for a new creation
 */
def setStationId;

actions.addStationMandatotyFields(setStationId)

WebUI.delay(2)

/**
 * Create a zone under that station
 * set the zone id as null for a new creation
 */
def setZoneId;

actions.addZone(setZoneId)

/**
 * To click on the Zone Save button
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneSaveButton'()

/**
 * Search the station in left panel
 */ 
actions.setTextForSearchInLeftNav(actions.stationId)

WebUI.delay(2)

/**
 * Click the station in the left panel
 */
WebUI.executeJavaScript(RailroadPageConstants.CLICK_FIRST_STATION_IN_LIST, null)

WebUI.delay(2)

/**
 * Click on the Actions menu in View station
 */
 CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnStationActionMenu'()
 
/**
 * Click on Delete station button
 */
 CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteStationButton'()
 
/**
 * Test Object created to get the delete confirmation message
 */
TestObject deleteMessageObj = findTestObject('Object Repository/F11822-Configure and Manage Stations/deleteConfirmationMessage')
 
/**
 * Verify the delete confirmation message when station has zones
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyDeleteMessageWhenStationHasZoneTracks'(actions.stationId, RailroadPageConstants.DELETE_MESSAGE_SECOND_HALF_ONLY_ZONE,true)
