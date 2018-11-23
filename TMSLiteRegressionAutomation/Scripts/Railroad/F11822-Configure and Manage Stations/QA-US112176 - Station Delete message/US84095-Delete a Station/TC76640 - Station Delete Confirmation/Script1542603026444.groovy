import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

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
import com.ge.tms.util.CommonUtilities
import com.ge.tms.constants.RailroadPageConstants

/**
 * This test case is to Add a new station 2 Zones and 2 Tracks delete and confirm message

	  1. Login to the application
	  2. Navigate to System Configuration -> Railroad SetUp
	  3. Create a new station 2 Zones and 2 Tracks for each zone
	  4. Delete the station
	  5. Verify Delete Confirmation message
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
 * Create a new track under the zone
 * set the track id as null for new creation
 */
def setTrackId;

actions.saveNewTrackRecord(setTrackId)

/**
 *  Save Track
 */
actions.saveTrack()

/**
 * Create a new track under the zone
 * set the track id as null for new creation
 */
def trackNo2;

actions.saveNewTrackRecord(trackNo2)

/**
 *  Save Track
 */
actions.saveTrack()

/**
 * Set station id to search field
 */
actions.setStaionForStationzoneSearchField()

/**
 * Select 1st record of the left pane
 */
actions.selectFirstStation()

/**
 * Create a zone under that station
 * set the zone id as null for a new creation
 */
def ZoneId;

actions.addZone(ZoneId)

/**
 * To click on the Zone Save button
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneSaveButton'()

/**
 * Create a new track under the zone
 * set the track id as null for new creation
 */
def TrackId;

actions.saveNewTrackRecord(TrackId)

/**
 *  Save Track
 */
actions.saveTrack()

/**
 * Create a new track under the zone
 * set the track id as null for new creation
 */
def TrackId2;

actions.saveNewTrackRecord(TrackId2)

/**
 *  Save Track
 */
actions.saveTrack()

/**
 *  Verify Created Track
 */

/**
 * Set station id to search field
 */
actions.setStaionForStationzoneSearchField()

/**
 * Select 1st record of the left pane
 */
actions.selectFirstStation()

/**
 * Click on the Actions menu in View station
 */
 CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnStationActionMenu'()
 
/**
 * Click on Delete station button
 */
 CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteStationButton'()
 
/**
 *  Verify Delete Message
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyDeleteMessage'()
