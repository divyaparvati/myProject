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
 * This test case is to Add a new station

	  1. Login to the application
	  2. Navigate to System Configuration -> Railroad SetUp
	  3. Select a railroad 
	  4. Create a station under this and verify created
	  5. Select another railroad
	  6. create the same station under this railroad and verify selected
	  7. Delete both the station under railroads
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
 *  click on add new station and set text for the mandatory fields
 *  Set the station id as null for a new creation
 */
def setStationId

actions.addStationMandatotyFields(setStationId)

/**
 * Verify if the newly created stations details are correct in the Header
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySelectedStationWithHeaderDetails'(actions.stationId)

/**
 * To select a different railroad to create station under it
 */
actions.selectARailroadNotSelectedBefore()

/**
 * Add same station to this railroad selected 
 */
actions.addStationMandatotyFields(actions.stationId)

/**
 * Verify if the newly created stations details are correct in the Header
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySelectedStationWithHeaderDetails'(actions.stationId)

/**
 * Click on the Actions menu in View station to delete the station from the railroad
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

/**
  * Verify that after station deletion , deleted station shall not be searched in left navigation
  */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyDeletedItemNotPresentInSearchView'(actions.stationId)

///**
// *  DEFECT DE74771 is raised for the dropdpwn issue in railroad because of which user is not able to change the railroad 
// *  Hence the first station created , cannout be deleted at present 
// *  This will be taken once the defect is fixed
// */

/**
* Search for the railroad in the dropdown to select the previous railroad and to delete the station
*/
//actions.searchForARailroadAndClick(actions.railRoadSelected, actions.randomNumberForRailroad) 
