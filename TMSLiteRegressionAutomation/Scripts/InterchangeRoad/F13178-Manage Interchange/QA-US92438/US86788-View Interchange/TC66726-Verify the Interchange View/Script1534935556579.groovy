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

/**
 * Create a interchangeConsistDetailList to contain all the details from a selected consist
 */
List<String> interchangeConsistDetailList = new ArrayList<String>()

/**
 * Randomly select a consist from the left panel 
 */
interchangeConsistDetailList = actions.getRandomlySelectedConsistDetailsFromLeftPanel()

WebUI.delay(1)

/**
* Response object for the Interchange Roads - to get all roads 
*/
ResponseObject interchangeRoadGetAllData = WS.sendRequest(findTestObject('Object Repository/InterchangeRoadServices/GET_ALL_INTERCHANGEROADS'))

/**
 * Verify the details of a consist randomly selected.
 * Verify the details like TrainID, Direction , Sender ID , Type, SPLC,  Number of cars in Left and Right Panel
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeRoadConsistView'(interchangeRoadGetAllData, interchangeConsistDetailList)
