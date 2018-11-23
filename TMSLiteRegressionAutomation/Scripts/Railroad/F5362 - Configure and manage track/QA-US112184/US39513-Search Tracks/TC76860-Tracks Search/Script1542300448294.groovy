import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
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
import com.ge.tms.util.CommonUtilities as CommonUtilities
import com.ge.tms.constants.RailroadPageConstants as RailroadPageConstants
import java.util.List as List
import java.util.ArrayList as ArrayList
import com.ge.tms.railroad.TracksModel as TracksModel

/**
 * Test Steps:
 *
 * 1. Login to Application
 * 2. Select random Railroad and add station under that railroad
 * 3. Add new zone under that station
 * 4. Click on the Add New track button
 * 5. Provide all valid inputs
 * 6. Click on Save button
 * 7. Click on the Add New track button 
 * 8. Provide all valid inputs
 * 9. Click on Save button
 * 10.Click on the Add New track button
 * 11. Provide all valid inputs
 * 12. Click on Save button
 * 13.Full Text search with track id and verify the results
 * 14.partial search with track id and verify the results
 * 15.Full Text search with track description and verify the results
 * 16.partial search with track description and verify the results
 * 17.Full Text search with track type and verify the results
 * 18.partial search with track type and verify the results
 * 19.Full Text search with track length and verify the results
 * 20.Full Text search with car capacity and verify the results
 * 21.Full Text search with condition code and verify the results
 * 22.partial search with condition code and verify the results
 * 23.multiple fields search and verify
 * 24. Delete the tracks
 * 25. Delete the Zone
 * 26. Delete the station
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
def setStationId

actions.addStationMandatotyFields(setStationId)

WebUI.delay(2)

/**
 * Create a zone under that station
 * set the zone id as null for a new creation
 */
def setZoneId

actions.addZone(setZoneId)

/**
 * To click on the Zone Save button
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneSaveButton'()

/**
 * Create a new track under the zone
 * set the track id as null for new creation
 * Creating 3 tracks verify the search of tracks
 */
def setTrackId

for (int i = 0; i < 3; i++) {
    actions.saveNewTrackRecord(setTrackId)

    actions.saveTrack()

    setTrackId = null

    WebUI.delay(3)
}

/**
 * Click on the delete button for the created track
 */
List<TracksModel> tracksList = CustomKeywords.'com.ge.tms.commonactions.RailroadActions.storeTracksDataToSearch'()

/**
 * Searching Tracks by Track ID
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySearchByTrackID'(tracksList)

WebUI.delay(2)

/**
 * Searching Tracks by Track Description
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySearchByTrackDescription'(tracksList)

WebUI.delay(2)

/**
 * Searching Tracks by Track Description
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySearchByTrackType'(tracksList)

WebUI.delay(2)

/**
 * Searching Tracks by Track Length
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySearchByTrackLength'(tracksList)

WebUI.delay(2)

/**
 * Searching Tracks by Car Capacity
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySearchByCarCapacity'(tracksList)

WebUI.delay(2)

/**
 * Searching Tracks by Condition Code
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySearchByConditionCode'(tracksList)

WebUI.delay(2)

/**
 * Deleting all 3 newly created Tracks
 */
for (int i = 0; i < 3; i++) {
    /**
	 * Test object obtained for Yes button in Delete confirmation box
	 */
    deleteTrackButtonObj = findTestObject('Object Repository/F5362 - Configure and Manage Track/deleteTrackButton')

    /**
	 * Click on the Yes button to confirm the delete action of track
	 */
    CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteTrackButtonObj)

    deleteYesButtonObj = findTestObject('Object Repository/F5362 - Configure and Manage Track/deleteTrackYesButton')

    /**
	 * Click on the Yes button to confirm the delete action of track
	 */
    CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)
}

/**
 * Verify that track has been successfully deleted
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyNoTracksFoundInSearch'(actions.trackId)

WebUI.delay(2)

/**
 * Click on the Actions menu of the View Zone
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneActionMenu'()

/**
 * Click on Delete zone
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteAction'(RailroadPageConstants.CLICK_DELETE_ZONE_BUTTON)

WebUI.delay(1)

/**
 * Test object obtained for Yes button in Delete confirmation box
 */
deleteYesButtonObj = findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Delete Zone/deleteYesButton')

/**
 * Click on the Yes button to confirm the delete action of zone
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)

WebUI.delay(1)

/**
 * Verify that after successful delete of a zone user is naviagted back to the station view
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyViewStationIdAfterZoneDeletion'()

/**
 * Click on the Actions menu in View station
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnStationActionMenu'()

/**
 * Click on Delete station button
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteStationButton'()

/**
 * Test object obtained for Yes button in Delete confirmation box
 */
deleteYesButtonObj = findTestObject('Object Repository/F11822-Configure and Manage Stations/deleteConfirmationYesButton')

/**
 * Click on the Yes button to confirm the delete action of station
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)

WebUI.delay(1)

/**
 * Verify that after station deletion , deleted station shall not be searched in left navigation
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyDeletedItemNotPresentInSearchView'(actions.stationId)
