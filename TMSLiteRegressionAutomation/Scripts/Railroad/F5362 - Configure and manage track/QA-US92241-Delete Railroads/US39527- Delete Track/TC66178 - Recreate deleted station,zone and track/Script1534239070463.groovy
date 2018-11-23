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
4. Create a track under that zone
5. Delete the track and verify delete of track is successfull
6. Delete the zone and verify delete of zone is successfull
7. Delete the station and verify station can be successfully deleted
8. Create a station again Successfully with same deleted station id
9. Create a zone again successfully with same deleted zone id
10. Create a track again successfully with same track id
 */

/**
 * To call the test case TC66168 to create station , zone , track and delete them successfully
 */
WebUI.callTestCase(findTestCase('Railroad/F5362 - Configure and manage track/QA-US92241-Delete Railroads/US39527- Delete Track/TC66168 - Complete Delete Feature'), 
    [:], FailureHandling.CONTINUE_ON_FAILURE)

/**
 * Create the instance of the common util class
 */
CommonUtilities util = new CommonUtilities()

/**
 *  Action class for railroad
 */
RailroadActions actions =new RailroadActions()

/**
 * Variable for setting the station id
 */
def setStationId = actions.stationId

/**
 * Create a new Station 
 */
actions.addStationMandatotyFields(setStationId)

WebUI.delay(2)

/**
 *  Verify the newly created station
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyCreatedStation'()

/**
 * Variable for setting the zone id
 */
def setZoneId = actions.zoneId

/**
 * Create a zone under that station
 */
actions.addZone(setZoneId)

/**
 * To click on the Zone Save button
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneSaveButton'()

/**
 * Verify that the newly created zone has been successfully re-created
 */
 CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyNewZoneCreatedSuccessfully'()
 
 WebUI.delay(1)
 
/**
 * Variable for setting the track id
 */
 def setTrackId = actions.trackId
 
 /**
 * Create a new track under the zone with the same id
 */
actions.saveNewTrackRecord(setTrackId)

/**
 * Save Track
 */
actions.saveTrack()
/**
 *  Verify Created Track
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyNewlyAddedTrack'()
 