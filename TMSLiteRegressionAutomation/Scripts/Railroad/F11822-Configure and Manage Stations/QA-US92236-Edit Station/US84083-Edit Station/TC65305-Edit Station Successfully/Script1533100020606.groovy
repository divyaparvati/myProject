import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.kms.katalon.core.annotation.SetUp as SetUp
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
import com.ge.tms.util.CommonUtilities as CommonUtilities
import com.ge.tms.constants.RailroadPageConstants as RailroadPageConstants
import com.ge.tms.railroad.RailroadVerification as RailroadVerification
import com.ge.tms.commonactions.RailroadActions as RailroadActions

/**
 * This test case is to Edit a new station

	1. Login to application Successfully
    2. Create a New Station successfully
    3. Click on Action menu of View Station , and click on Edit Station
    4. Edit the newly created station with valid values for mandatory fields
    5. Click on Save
    6. Validate that user is navigated to view of that edited station
    7. Validate that the edited information is successfully persisted in UI
    8. Delete the station successfully
 */
/**
 * Click on the Railroad Setup
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRailroadSetup'()

/**
 *  Action class for railroad
 */
RailroadActions actions = new RailroadActions()

/**
 * Click on the Select Railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnSelectRailroadDropdown'()

/**
 * Select any random railroad from the the Select railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.selectRandomRailRoad'()

WebUI.delay(4)

/**
 *  click on add new station and set text for the mandatory fields
 *  Set the station id to null for a new creation of station
 */
def setStationId

actions.addStationMandatotyFields(setStationId)

/**
 * Click on the Actions menu in View of Station
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnStationActionMenu'()

/**
 * Click on Edit Station button under the Actions menu
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnEditStationButton'()

/**
* Edit the station with required details
*/
actions.editStation()

/**
 * Click on the Edit save button
 */
actions.clickOnStationSavebutton()

/**
 * Verify if the edited details are persisted in View Station
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyEditedStation'()

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
TestObject deleteYesButtonObj = findTestObject('Object Repository/F11822-Configure and Manage Stations/deleteConfirmationYesButton')

/**
 * Click on the Yes button to confirm the delete action of station
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)

WebUI.delay(1)

/**
  * Verify that after station deletion , deleted station shall not be searched in left navigation
  */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyDeletedItemNotPresentInSearchView'(actions.stationId)
