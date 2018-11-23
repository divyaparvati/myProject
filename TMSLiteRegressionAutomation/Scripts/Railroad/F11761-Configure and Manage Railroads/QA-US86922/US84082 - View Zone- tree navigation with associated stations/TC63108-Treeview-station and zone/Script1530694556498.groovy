import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.RailroadPageConstants
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
import com.ge.tms.commonactions.RailroadActions


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
 * Click on any railroad in the dropdown
 */

CustomKeywords.'com.ge.tms.commonactions.RailroadActions.selectRandomRailRoadExclusiveFirstRailRoad'()

/**
 *  Get the selected railroad code and pass it to getStation webservice url
 */
String selectedRailRoad = actions.railRoadSelected
String[] railRoad=selectedRailRoad.split(' - ')
String abbrevartionForRailRoad = railRoad[0]
ResponseObject getAllStationsFromASelectedRailRoad = WS.sendRequest(findTestObject('RailRoadServices/GETALLStations_fsac',[('fsacId'): abbrevartionForRailRoad]))

/**
 * Verification of list of stations for the selected Railroad
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyAllStationsForASelectedRailRoad'(getAllStationsFromASelectedRailRoad)

/**
 *  Select a random station from the list
 */
ResponseObject getAllZonesFromASelectedStation = WS.sendRequest(findTestObject('RailRoadServices/GETALLZones',[('stationId'): CustomKeywords.'com.ge.tms.commonactions.RailroadActions.selectRandomStationByName'()]))

/**
 *  Verification of list of zones for the selected station
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyAllZonesForAStation'(getAllZonesFromASelectedStation)
