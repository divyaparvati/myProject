/**
 1. login to TMS lite application
 2. Click on Train operations
 3. Select Manage Interchanges
 4. Select Train ID from Search by Menu
 5. Enter a randomly selected Train ID
 6. Verify Searched train display on left pane
 
 1. login to TMS lite application
 2. Click on Train operations
 3. Select Manage Interchanges
 4. Select Car ID from Search by Menu
 5. Enter a randomly selected car ID
 6. Verify searched car display on right pane
 
 
 1. login to TMS lite application
 2. Click on Train operations
 3. Select Manage Interchanges
 4. Select Train ID from Search by Menu
 5. Enter a invalid Train ID
 6. Verify message “No Interchange found”
 7. Select Car ID from Search by Menu
 8. Enter a invalid carID
 9. Verify message “No Interchange found”
  */

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.ge.tms.commonactions.InterchangeActions
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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**
 * Action class for InterchangeActions
 */
InterchangeActions actions = new InterchangeActions()

/**
 * Click on the Manage Interchange Roads Tab
 */
CustomKeywords.'com.ge.tms.commonactions.InterchangeActions.clickOnManageInterchangeRoadTab'()

/**
 * Verify the Manage Interchnage Road text when the tab is clicked
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyManageInterchangeRoadText'()

WebUI.delay(3)

/**
 * Create a interchangeConsistDetailList to contain all the details from a selected consist
 * This Method is commented because of the DE72076 - The train ID does not exact match when full Train ID given, Works fine when Train ID partially given
 */
//actions.createInterchangeConsistDetailList


/**
 * Pick a car randomly from right pane
 */
actions.getRandomCarFromRightPane()

/**
 * Add a invalid car ID
 */
actions.addWrongCarID()

/**
 * Verify the no interchange found message when eneterd a Invalid car ID
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyInvalidSearchResults'()
WebUI.delay(2)

/**
 * Click on Search By drop down
 * Add the randomly selected train ID from the consist, in to Search fields
 * This Method is commented because of the DE72076 - The train ID does not exact match when full Train ID given, Works fine when Train ID partially given
 */
//actions.clickAndAddRandomTrainID()
/**
 *  Verify the Train ID search text and the Train ID search results match together
 *  This Method is commented because of the DE72076 - The train ID does not exact match when full Train ID given, Works fine when Train ID partially given
 */
//CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyTrainIDSearchResults'()

/**
 * Click on Search By drop down
 * Add the randomly selected car ID from the consist, in to Search fields
 */
actions.addRandomlyPickedCarToSearchField()

/**
 *  Verify the Car ID search text and the CarID ID search results match together
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyCarSearchResults'()
