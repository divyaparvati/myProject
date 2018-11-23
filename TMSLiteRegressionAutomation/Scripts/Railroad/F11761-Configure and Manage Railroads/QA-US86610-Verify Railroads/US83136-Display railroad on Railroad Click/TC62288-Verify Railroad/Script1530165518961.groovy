import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ge.tms.constants.RailroadPageConstants as RailroadPageConstants
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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import java.util.Map as Map
import java.util.HashMap as HashMap
import com.ge.tms.commonactions.RailroadActions

/**
 * ResponseRequest Object for Get all Railroads
 */
ResponseObject getAllRailroadDataObj = WS.sendRequest(findTestObject('Object Repository/RailRoadServices/GETAllRailRoads'))

/**
 * Click on the Railroad Setup
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRailroadSetup'()

/**
 * Click on the Select Railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnSelectRailroadDropdown'()

/**
 * Verify the list of railroads in Select Railroad dropdown and in the Api call
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyRailRoadsInSelectRailRoadDropdown'(getAllRailroadDataObj)

/**
 * Click on any railroad in the dropdown
 */

CustomKeywords.'com.ge.tms.commonactions.RailroadActions.selectRandomRailRoad'()

/**
 * Verify that the selected Railroad text is shown as dropdown value
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifySelectedRailroadAsDropdownValue'()
