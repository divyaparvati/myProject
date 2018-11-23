package com.ge.tms.railroad

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After
import org.junit.Before
import com.ge.tms.commonactions.RailroadActions
import com.ge.tms.constants.CommonPageConstants
import com.ge.tms.constants.CustomerPageConstants
import com.ge.tms.constants.RailroadPageConstants
import com.ge.tms.util.CommonUtilities
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import java.util.Map
import java.util.HashMap
import groovy.json.JsonSlurper;
import com.kms.katalon.core.testobject.ResponseObject;
import com.kms.katalon.core.logging.KeywordLogger
import org.openqa.selenium.Keys
import org.openqa.selenium.*
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ge.tms.railroad.TracksModel
import java.util.List
import java.util.ArrayList
/**
 * Class for all the verification methods in Railroads setup
 */
public class RailroadVerification {

	/**
	 * variables to hold directory path
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 *  Common utility class instance
	 */
	CommonUtilities utils = new CommonUtilities()

	/**
	 * Railroad Actions class instance
	 */
	RailroadActions railroadActions = new RailroadActions()

	/**
	 * Logger class
	 */
	KeywordLogger log = new KeywordLogger()

	/**
	 * Global variable for selected station from the tree
	 */
	static int stationSelectedNumber

	/**
	 *  Testobject for Save button of station
	 */
	TestObject stationSaveButton = findTestObject('Object Repository/F11822-Configure and Manage Stations/station_save_button')

	/**
	 *  Testobject for Save button of Track
	 */
	TestObject trackSaveButton =  findTestObject('Object Repository/F5362 - Configure and Manage Track/SaveTrackButton')

	/**
	 * Verify if the Select Railroad dropdown contains all railroads by comparing with Api call
	 * Get all the railroads from the Railroad dropdown to a Map 
	 * Get all the railroads from API call to another Map
	 * Compare the two maps to verify if data is the same
	 * @param Response Object from the api call
	 **/
	@Keyword
	def verifyRailRoadsInSelectRailRoadDropdown(ResponseObject resObj){
		String resText = resObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)

		/**
		 * Map Object that contains all railroads from API
		 */
		Map<String, String> railRoadApiDataMap = new HashMap<String, String>()

		for(int i=0;i<response.size();i++){
			railRoadApiDataMap.put(response[i].code.replace(" ", ""),response[i].name.replace(" ",""))
		}
		log.logInfo("List of Railroads from Api call: " + railRoadApiDataMap)

		/**
		 * Variable list that contains all the railroads from the Select Railroad dropdown
		 */
		def railRoadList = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_DATALIST,null)

		/**
		 * Map Object to take all the data from the Select Railroad dropdown
		 */
		Map<String, String> railRoadDropdownDataMap = new HashMap<String, String>()

		for (int i=0;i<railRoadList.size();i++){
			String splitText = railRoadList[i];
			String[] railRoadText = splitText.split("-")
			railRoadDropdownDataMap.put(railRoadText[0].replace(" ", ""), railRoadText[1].replace(" ",""))
		}
		log.logInfo("List of Railroads from Select Railroad dropdown: "+railRoadDropdownDataMap)

		/**
		 * Compare if the data is same in dropdown and in api
		 */
		boolean dataStatus = railRoadApiDataMap.equals(railRoadDropdownDataMap)
		WebUI.verifyEqual(true, dataStatus)
	}

	/**
	 * Verify that selected Railroad from the dropdown is given as the value in the dropdown for Select Railroad text after selection
	 */
	@Keyword
	def verifySelectedRailroadAsDropdownValue(){

		/**
		 * Variable to railroad value that is clicked
		 */
		String selectedRailroadDropdownValue = railroadActions.railRoadSelected

		/**
		 * Variable to hold the railroad dropdown value after a selection is made
		 */
		String railroadDropdownValueAfterSelection = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_SELECTED_VALUE, null)
		WebUI.verifyEqual(selectedRailroadDropdownValue,railroadDropdownValueAfterSelection)
	}

	/**
	 * Method to verify stations displayed in UI and stations from API
	 */
	@Keyword
	def verifyAllStationsForASelectedRailRoad(ResponseObject getParticularRailRoad){
		List<String> stationListFromApi = new ArrayList<String>();
		List<String> stationListFromUi = new ArrayList<String>();
		String resText = getParticularRailRoad.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def responseForStation  = slupper.parseText(resText)
		WebUI.delay(3)
		int stationSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_LIST_UNDER_RAILROAD, null)
		for (int i=1; i<= stationSize ; i++){
			def stationFromResponse = responseForStation.stationCode[i-1]
			stationListFromApi.add(stationFromResponse)
			if (stationSize == 1 || i==1){
				def stationFromUI = WebUI.executeJavaScript(RailroadPageConstants.STATION_SELECTED, null)
				stationListFromUi.add(stationFromUI)
			}
			else{
				String stationSelected = 'return document.querySelector(\'px-tree\').shadowRoot.querySelector(\'ul > px-tree-node:nth-child('+ i +')\').shadowRoot.querySelector(\'span\').innerHTML'
				def stationFromUI = WebUI.executeJavaScript(stationSelected, null)
				stationListFromUi.add(stationFromUI)
			}
		}
		utils.equalLists(stationListFromUi,stationListFromApi)
	}

	/**
	 *  Method to verify zones displayed in UI and zones from API
	 */
	@Keyword
	def verifyAllZonesForAStation(ResponseObject getAllZonesFromAStation){
		List<String> zoneListFromApi = new ArrayList<String>();
		List<String> zoneListFromUi = new ArrayList<String>();
		String resText = getAllZonesFromAStation.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def responseForZone  = slupper.parseText(resText)
		int zoneSize = railroadActions.getsizeOfZone()
		for (int i=1; i<= zoneSize ; i++){
			def zoneFromResponse = responseForZone.zoneId[i-1]
			zoneListFromApi.add(zoneFromResponse)
			int randomNumberLessOne = railroadActions.generatedRandomNumber-1
			String zoneText = 'return document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\')[' + randomNumberLessOne +'].shadowRoot.querySelector(\'#collapse > div > px-tree-node:nth-child('+ (i) + ')\').shadowRoot.querySelector(\'li > span\').innerHTML'

			WebUI.delay(2)

			def zoneFromUi = WebUI.executeJavaScript(zoneText, null)
			zoneListFromUi.add(zoneFromUi)
		}
		WebUI.verifyEqual(zoneListFromUi, zoneListFromApi)
	}

	/**
	 * Verify Selected station in the tree , details with the header in right panel
	 * @param stationCodeValue
	 */
	@Keyword
	def verifySelectedStationWithHeaderDetails(String stationCodeValue){

		/**
		 * Select the railroad
		 * Split the text to get the roadmark key
		 */
		String selectedRailRoad = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_SELECTED_VALUE, null)
		String[] railRoad=selectedRailRoad.split('-')
		String abbrevartionForRailRoad = railRoad[0].replace(" ", "")

		def expectedRoadMarkFromApi
		def expectedStationCodeFromApi
		def expectedStationNameFromApi
		ResponseObject getAllStationsFromASelectedRailRoad = WS.sendRequest(findTestObject('RailRoadServices/GETALLStations_fsac',[('fsacId'): abbrevartionForRailRoad]))
		String resText = getAllStationsFromASelectedRailRoad.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfSations = response.size();
		for(int i=0;i<noOfSations;i++){
			String stationId = response[i].stationCode.toString()
			if(stationId.equals(stationCodeValue)){
				expectedRoadMarkFromApi = response[i].fsacRoadKey
				expectedStationCodeFromApi = response[i].stationCode
				expectedStationNameFromApi = response[i].stationName
			}
		}

		/**
		 * Variables to hold the actual data from the UI  ( for view station form)
		 */
		def actualStationCodeFromUI = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/div_StationCode'))
		def actualStationNameFromUI= WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/p_StationName'))
		def actualRoadMarkFromApi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_RailroadCode'))

		/**
		 * Verify the header details with the api data
		 */
		WebUI.verifyEqual(actualStationCodeFromUI,expectedStationCodeFromApi)
		WebUI.verifyEqual(actualStationNameFromUI,expectedStationNameFromApi)
		WebUI.verifyEqual(actualRoadMarkFromApi,expectedRoadMarkFromApi)
	}

	/**
	 * Verify the selected Station details in the right pane with that of the Api call
	 * @param stationCodeValue
	 */
	@Keyword
	def verifySelectedStationDetailsInRightPane(stationCodeValue){

		/**
		 * Select the railroad
		 * Split the text to get the roadmark key
		 */
		String selectedRailRoad = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_SELECTED_VALUE, null)
		String[] railRoad=selectedRailRoad.split('-')
		String abbrevartionForRailRoad = railRoad[0].replace(" ", "")

		def expectedSplcFromApi
		def expectedFsacFromApi
		def expectedDefaultTrackFromApi
		def expectedJunctionFromApi
		def expectedSwitchingTrackFromApi
		def expectedClassificationFromApi
		def expectedOriginalFsacFromApi
		def expectedEntryPointFromApi
		def expectedCanadaEntryFromApi
		def expectedInterchangeRoadsFromApi
		def expectedMilepostFromApi
		def expectedTimezoneFromApi
		def expectedCountryFromApi
		def expectedStateFromApi
		def expectedDaylightSavingFromApi
		def expectedLatitudeFromApi
		def expectedLongitudeFromApi
		ResponseObject getAllStationsFromASelectedRailRoad = WS.sendRequest(findTestObject('RailRoadServices/GETALLStations_fsac',[('fsacId'): abbrevartionForRailRoad]))
		String resText = getAllStationsFromASelectedRailRoad.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfSations = response.size();
		for(int i=0;i<noOfSations;i++){
			String stationId = response[i].stationCode.toString()
			if(stationId.equals(stationCodeValue)){
				expectedSplcFromApi = response[i].splcKey
				expectedFsacFromApi = response[i].fsacCodeKey
				expectedDefaultTrackFromApi = response[i].stationDefaultTrack
				expectedJunctionFromApi = response[i].junctionStation260Abbreviation
				expectedSwitchingTrackFromApi = response[i].stationSwitchingLeadTrack
				expectedClassificationFromApi = response[i].defaultClassification
				expectedOriginalFsacFromApi = response[i].originalBillFsac
				expectedEntryPointFromApi = response[i].usEntryPort
				expectedCanadaEntryFromApi = response[i].canadaEntryPort
				expectedInterchangeRoadsFromApi = response[i].interchangeRoads
				expectedMilepostFromApi = response[i].stationMilePost
				expectedTimezoneFromApi = response[i].stationTimeZoneQualifier
				expectedCountryFromApi = response[i].countryCode
				expectedStateFromApi = response[i].stateCode
				expectedDaylightSavingFromApi = response[i].stationDayLightSavingsIndicator
				expectedLatitudeFromApi = response[i].latitude
				expectedLongitudeFromApi = response[i].longitude
			}
		}

		/**
		 * Variables to hold the temp text when the UI shows as '--' and the api call data is 'null'
		 */
		def tempJunction = (expectedJunctionFromApi == null) ? "--" : expectedJunctionFromApi
		def tempDefaultTrack = (expectedDefaultTrackFromApi == null) ? "--" : expectedDefaultTrackFromApi;
		def tempSwitchTrack = (expectedSwitchingTrackFromApi == null) ? "--" : expectedSwitchingTrackFromApi;
		def tempClassification = (expectedClassificationFromApi == null) ? "--" : expectedClassificationFromApi;
		def tempOriginalFsac = (expectedOriginalFsacFromApi == null) ? "--" : expectedOriginalFsacFromApi;
		def tempUSEntryPoint = (expectedEntryPointFromApi == null) ? "--" : expectedEntryPointFromApi;
		def tempCanadaEntryPoint = (expectedCanadaEntryFromApi == null) ? "--" : expectedCanadaEntryFromApi;
		def tempInterchangeRoads = (expectedInterchangeRoadsFromApi == null || expectedInterchangeRoadsFromApi.toString()) ? "--" : expectedInterchangeRoadsFromApi;
		def tempMilepost = (expectedMilepostFromApi == null) ? "--" : expectedMilepostFromApi;
		def tempTimezone = (expectedTimezoneFromApi == null) ? "--" : expectedTimezoneFromApi;
		def tempDaylight = (expectedDaylightSavingFromApi == false) ? "No" : "Yes";
		def tempLatitude = (expectedLatitudeFromApi == null) ? "--" : expectedLatitudeFromApi;
		def tempLongitude = (expectedLongitudeFromApi == null) ? "--" : expectedLongitudeFromApi;

		/**
		 * Variables to hold the actual text from the UI ( view station form) 
		 */
		def actualSplcFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_SPLC'))
		def actualFsacFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_FSAC'))
		def actualDefaultTrackFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_DefaultTrack'))
		def actualJunctionFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Junction'))
		def actualSwitchingTrackFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_SwitchingTrack'))
		def actualClassificationFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Classification'))
		def actualOriginalFsacFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_OriginalBillFsac'))
		def actualEntryPointFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_USEntryPort'))
		def actualCanadaEntryFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_CanadaEntry'))
		def actualInterchangeRoadsFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_InterchangeRoads'))
		def actualMilepostFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Milepost'))
		def actualTimezoneFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Timezone'))
		def actualCountryFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Country'))
		def actualStateFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_State'))
		def actualDaylightSavingFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_DaylightSavings'))
		def actualLatitudeFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Lattitude'))
		def actualLongitudeFromUi = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/span_Longitude'))

		/**
		 * Verify if selected Station details on the right panel matches the response data 
		 */
		WebUI.verifyEqual(actualSplcFromUi,expectedSplcFromApi )
		WebUI.verifyEqual(actualFsacFromUi,expectedFsacFromApi )
		WebUI.verifyEqual(actualJunctionFromUi,tempJunction)
		WebUI.verifyEqual(actualSwitchingTrackFromUi,tempSwitchTrack)
		WebUI.verifyEqual(actualClassificationFromUi,tempClassification)
		WebUI.verifyEqual(actualOriginalFsacFromUi,tempOriginalFsac)
		WebUI.verifyEqual(actualEntryPointFromUi,tempUSEntryPoint)
		WebUI.verifyEqual(actualCanadaEntryFromUi,tempCanadaEntryPoint)
		WebUI.verifyEqual(actualInterchangeRoadsFromUi,tempInterchangeRoads)
		WebUI.verifyEqual(actualMilepostFromUi,tempMilepost)
		WebUI.verifyEqual(actualTimezoneFromUi,tempTimezone)
		WebUI.verifyEqual(actualCountryFromUi,expectedCountryFromApi)
		WebUI.verifyEqual(actualStateFromUi,expectedStateFromApi)
		WebUI.verifyEqual(actualDaylightSavingFromUi,tempDaylight)
		WebUI.verifyEqual(actualLatitudeFromUi,tempLatitude)
		WebUI.verifyEqual(actualLongitudeFromUi,tempLongitude)
	}

	/**
	 * To verify if Zone has been successfully created
	 */
	@Keyword
	def verifyNewZoneCreatedSuccessfully(){
		def actualZoneId = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/viewZoneId'))
		WebUI.verifyEqual(actualZoneId, railroadActions.zoneId)
		def actualZoneName = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/ViewZoneName'))
		WebUI.verifyEqual(actualZoneName, railroadActions.zoneName )
	}

	/**
	 * Verify the mandatory fields in Add New Zone
	 */
	@Keyword
	def verifyZoneMandatoryFields(Boolean isZoneIdEditable){

		/**
		 * Test Object get the Save button  
		 */
		TestObject zonesaveButton = findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneSaveButton')

		/**
		 * To check if Zone id is editable or not
		 * This condition is checked for Add Zone and Edit Zone validation
		 * For Add Zone Validation , Zone id is editable field and hence validation needs to be done
		 * For Edit Zone Validation , Zone id is not editable - Hence checked if non editable
		 */
		if(isZoneIdEditable == true){
			/**
			 * Verify the error message for empty Zone Id field
			 * Verify Save button is disabled
			 */
			railroadActions.clickOnZoneId()
			WebUI.sendKeys(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID'), CommonPageConstants.TAB_KEY)
			def actualZoneIdFieldErrorMessage = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneIdErrorMessage'))
			WebUI.verifyEqual(actualZoneIdFieldErrorMessage, RailroadPageConstants.ZONE_ID_EMPTY_FIELD_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(zonesaveButton)

			/**
			 * Verify the error message when Zone Id is given Special Characters
			 * Verify Save button is disabled
			 */
			railroadActions.clickOnZoneId()
			WebUI.sendKeys(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID'), CommonPageConstants.SPECIAL_CHARACTERS)
			def actualZoneIdErrorMessage = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneIdErrorMessage'))
			WebUI.verifyEqual(actualZoneIdErrorMessage, CommonPageConstants.FIELD_ALPHANUMERIC_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(zonesaveButton)
		}
		else{
			/**
			 * Verify zone id is non editable for Edit Zone
			 */
			TestObject zoneIdObj = findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID')
			WebUI.verifyElementHasAttribute(zoneIdObj, CommonPageConstants.FIELD_NON_EDITABLE, 3)
		}

		/**
		 * Verify the error message for empty Zone Name field
		 * Verify Save button is disabled
		 */
		railroadActions.clickOnZoneName()
		String clearText = WebUI.clearText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName'))
		WebUI.sendKeys(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName')," ")
		WebUI.sendKeys(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName'),CommonPageConstants.TAB_KEY)
		def actualZoneNameFieldErrorMessage = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneNameErrorMessage'))
		WebUI.verifyEqual(actualZoneNameFieldErrorMessage, RailroadPageConstants.ZONE_NAME_EMPTY_FIELD_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(zonesaveButton)

		/**
		 * Verify the error message when Zone Name is given Special Characters
		 * Verify Save button is disabled
		 */
		railroadActions.clickOnZoneName()
		WebUI.sendKeys(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName'), CommonPageConstants.SPECIAL_CHARACTERS)
		def actualZoneNameErrorMessage = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneNameErrorMessage'))
		WebUI.verifyEqual(actualZoneNameErrorMessage, CommonPageConstants.FIELD_ALPHANUMERIC_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(zonesaveButton)

		/**
		 * Verify the Zone Type error message when dropdown is not selected 
		 * Select some value for the Zone type and then remove that value
		 * Error message should be displayed
		 */
		railroadActions.clickOnZoneTypeDropdown()
		railroadActions.selectZoneType()
		railroadActions.clickOnZoneTypeDropdown()
		WebUI.executeJavaScript(RailroadPageConstants.ZONE_TYPE_DROPDOWN_DESELECT, null)
		def  actualZoneTypeEmptyErrorMessage = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneTypeDeselectErrorMessage'))
		WebUI.verifyEqual(actualZoneTypeEmptyErrorMessage, RailroadPageConstants.ZONE_TYPE_EMPTY_FIELD_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(zonesaveButton)
	}

	/**
	 * Method to get all the stations for a specify Railroad mark
	 * @return response of the api call 
	 */
	def toGetAllStationsFromASelectedRailroad(){

		/**
		 * Select the railroad 
		 * Split the text to get the roadmark key 
		 */
		String selectedRailRoad = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_SELECTED_VALUE, null)
		String[] railRoad=selectedRailRoad.split('-')
		String abbrevartionForRailRoad = railRoad[0].replace(" ", "")

		/**
		 * Send the request with the Roadmark key
		 * and return the response
		 */
		ResponseObject getAllStationsFromASelectedRailRoad = WS.sendRequest(findTestObject('RailRoadServices/GETALLStations_fsac',[('fsacId'): abbrevartionForRailRoad]))
		String resText = getAllStationsFromASelectedRailRoad.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		return response
	}

	/**
	 *  Method to verify newly created station
	 */
	@Keyword
	def verifyCreatedStation(){
		String expectedStationId = railroadActions.stationId;
		WebUI.delay(2)
		String actualStaionId = WebUI.executeJavaScript(RailroadPageConstants.STATION_LIST_UI, null)
		boolean temp = actualStaionId.contains(expectedStationId) ? true : false;
		WebUI.verifyEqual(temp, true)
	}

	/**
	 *  Method to verify that save button for Add / Edit station is disabled 
	 *  until all mandatory fields are filled with valid values
	 */
	@Keyword
	def  verifyErrorMessagesForMandatoryFieldsStation(Boolean isStationIdEditable){

		/**
		 * To check if Station id is editable or not
		 * This condition is checked for Add Station and Edit Station validation
		 * For Add Station Validation , Station id is editable field and hence validation needs to be done
		 * For Edit Station Validation , Station id is not editable - Hence checked if non editable
		 */
		if(isStationIdEditable == true){
			WebUI.verifyEqual(railroadActions.errorMessageForStationId(), RailroadPageConstants.STATION_ID_EMPTY_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(stationSaveButton)
			WebUI.verifyEqual(railroadActions.errorForStationIdSpecialCharacters(), CommonPageConstants.FIELD_ALPHANUMERIC_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(stationSaveButton)
		}
		else{
			/**
			 * Verify station id is non editable for Edit Station
			 */
			TestObject stationIdObj = findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id')
			WebUI.verifyElementHasAttribute(stationIdObj, CommonPageConstants.FIELD_NON_EDITABLE, 3)
		}

		/**
		 * Verifying the error message for rest of the common mandatory fields in Add and Edit Station 
		 */
		WebUI.verifyEqual(railroadActions.errorMessageForStationName(), RailroadPageConstants.STATION_NAME_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
		WebUI.verifyEqual(railroadActions.errorForStationNameSpecialCharacters(), CommonPageConstants.FIELD_ALPHANUMERIC_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
		WebUI.verifyEqual(railroadActions.splcErrorMessage(), RailroadPageConstants.STATION_SPLC_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
		WebUI.verifyEqual(railroadActions.fsacErrorMessage(), RailroadPageConstants.STATION_FSAC_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
		WebUI.verifyEqual(railroadActions.timeZoneErrorMessage(), RailroadPageConstants.STATION_TIMEZONE_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
		WebUI.verifyEqual(railroadActions.countryErrorMessage(), RailroadPageConstants.STATION_COUNTRY_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
		WebUI.delay(1)
		railroadActions.setCountryForStation()
		WebUI.verifyEqual(railroadActions.stateErrorMessage(), RailroadPageConstants.STATION_STATE_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(stationSaveButton)
	}

	/**
	 * Method to verify if the zone has been successfully edited
	 * with new zone name and Zone type
	 */
	@Keyword
	def verifyEditedZone(){
		String actualZoneName = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/ViewZoneName'))
		WebUI.verifyEqual(actualZoneName, railroadActions.editZoneName )
		String actualZoneLabel = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/ViewZoneTypeLabel'))
		WebUI.verifyEqual(actualZoneLabel, railroadActions.editZoneTypeLabel)
	}

	/**
	 * Method to verify if station edited is successful
	 * with New station name and timezone
	 */
	@Keyword
	def verifyEditedStation(){
		String actualStationName = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/ViewStationName'))
		WebUI.verifyEqual(actualStationName, railroadActions.editStationName)
		String actualStationTimezone = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_timezone_label'))
		WebUI.verifyEqual(actualStationTimezone, railroadActions.editStationTimeZoneLabel)
	}

	/**
	 * Method to verify the cancel button in Edit Station
	 */   
	@Keyword
	def verifyCancelButtonInEditStation(String expectedText){
		String actualStationName = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/ViewStationName'))
		WebUI.verifyEqual(actualStationName, expectedText)
	}

	/**
     *  Verification of Track created with the data in the row
     */
    @Keyword
    def verifyNewlyAddedTrack(){
        
        /**
         * Verify the track id is same as created in modal
         */
		String actualTrackId = driver.findElement(By.xpath(RailroadPageConstants.trackID)).getText()
		WebUI.verifyEqual(actualTrackId, railroadActions.trackId)
        
        /**
         * Verify if the track desc is same as created in modal
         */
        String actualTrackDesc =  driver.findElement(By.xpath(RailroadPageConstants.trackDescription)).getText()
        WebUI.verifyEqual(actualTrackDesc, railroadActions.trackDescription)
        
		 /**
         * Verify if the track type is same as created in modal
         */
        String actualTrackType = driver.findElement(By.xpath(RailroadPageConstants.trackType)).getText()
        WebUI.verifyEqual(actualTrackType, railroadActions.trackType)
      
        /**
         * Verify if the track length is same as created in modal
         */
        String actualTrackLength = driver.findElement(By.xpath(RailroadPageConstants.trackLength)).getText()
        WebUI.verifyEqual(actualTrackLength, railroadActions.trackLength)
        
        /**
         * Verify if the track length is same as created in modal
         */
        String actualTrackCarCapacity = driver.findElement(By.xpath(RailroadPageConstants.carCapacity)).getText()
        WebUI.verifyEqual(actualTrackCarCapacity, railroadActions.trackCarCapacity)   
    }

	/**
	 *  Verification of Error Messages and Save Button for Track
	 *  @param string for which functionality being tested
	 */
	@Keyword
	def verifyTrackErrorMessages(String functionality){
		if(functionality.equals('addTrack')){
			WebUI.verifyEqual(railroadActions.getTrackIDErrorMessage(), RailroadPageConstants.TRACK_ID_EMPTY_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(trackSaveButton)
			WebUI.verifyEqual(railroadActions.getTrackIDAlphaNumericErrorMessage(), CommonPageConstants.FIELD_ALPHANUMERIC_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(trackSaveButton)
			WebUI.verifyEqual(railroadActions.getTrackLengthErrorMessage(), RailroadPageConstants.TRACK_LENGTH_EMPTY_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(trackSaveButton)
		}
		else if(functionality.equals('editTrack')){
			WebUI.verifyEqual(WebUI.verifyElementNotClickable(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input')),true)
			utils.verifySaveButtonDisabled(trackSaveButton)
			WebUI.verifyEqual(railroadActions.getTrackDescAlphaNumericErrorMessage(), CommonPageConstants.FIELD_ALPHANUMERIC_ERROR_MESSAGE)
			utils.verifySaveButtonDisabled(trackSaveButton)
		}

		WebUI.verifyEqual(railroadActions.getTrackDescriptionErrorMessage(functionality), RailroadPageConstants.TRACK_DESCRIPTION_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(trackSaveButton)
		WebUI.verifyEqual(railroadActions.trackTypeErrorMessage(), RailroadPageConstants.TRACK_TYPE_EMPTY_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(trackSaveButton)
	}

	/**
	 * Method to verify the delete confirmation message
	 */
	@Keyword
	def verifyDeleteConfirmationMessage(String assetId , TestObject deleteMessageObj){
		String actualMessage  = WebUI.getText(deleteMessageObj)
		actualMessage.contains(assetId)
	}

	/**
	 * Method to verify the delete confirmation message
	 * When station has zones or tracks
	 */
	@Keyword
	def verifyDeleteMessageWhenStationHasZoneTracks(String setId, String deleteMessageSecondPart , Boolean isStation){
		/**
		 * Variable to get the actual delete message text from UI
		 */
		def actualText
		if(isStation == true){
			actualText= WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/deleteConfirmationMessage'))
		}
		else{
			actualText=	WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Delete Zone/deleteConfirmationMessage'))
		}
		String expectedText = railroadActions.createDeleteMessageForStationWithZoneAndTrack(setId,deleteMessageSecondPart)
		WebUI.verifyEqual(actualText, expectedText)
	}

	/**
	 * Method to verify that after the successful deletion of a zone, user is navigated back to its Station View
	 */
	@Keyword
	def verifyViewStationIdAfterZoneDeletion(){
		String stationIdFromView = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/QA-US87586-View Stations/US84086-View Station Details/View Stations/div_StationCode'))
		WebUI.verifyEqual(stationIdFromView, railroadActions.stationId)
	}

	/**
	 * Method to verify that , after successful deletion of a zone , deleted zone shall not be present
	 * in Search view in the left navigation
	 */
	@Keyword
	def verifyDeletedItemNotPresentInSearchView(String searchId){
		railroadActions.setTextForSearchInLeftNav(searchId)
		WebUI.delay(2)
		String actualSearchResultText = WebUI.getText(findTestObject('Object Repository/CommonObjects/noSearchResultMessageLeftNav'))
		WebUI.verifyEqual(actualSearchResultText, CommonPageConstants.NO_SEARCH_RESULT_MESSAGE)
	}

	/**   
	 * Method to verify that When user clicks on the No button in Delete 
	 * User is navigated to the view screen
	 * @param actualText from the UI
	 * @param expectedText generated
	 */
	@Keyword
	def verifyNoButtonInDeleteAction(String actualText , String expectedText){
		WebUI.verifyEqual(actualText,expectedText)
	}

	/**
	 * Method to verify No tracks in the table
	 * @param track id passed
	 */
	@Keyword
	def verifyNoTracksFoundInSearch(String trackId){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_search_field_row1_trackid'), trackId)
		String actualNoTrackText = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/noRowsfound'))
		WebUI.verifyEqual(actualNoTrackText, RailroadPageConstants.TRACK_NO_ROWS_FOUND_TEXT)
	}

	/**
	 *  Verify NoTracks Found
	 */
	@Keyword
	def verifyNoTracksFound(){
		WebUI.verifyEqual(railroadActions.noResultsForTrack(), RailroadPageConstants.TRACK_NO_ROWS_FOUND_TEXT)
	}

	/**
	 *  Verify the edited track length
	 */
	@Keyword
	def verifyEditedTrackLength(){
		WebUI.verifyEqual(railroadActions.getTrackLengthUi(), railroadActions.searchTrackLength())
	}

	/**
	 *  Verify the edited track length not saved
	 */
	@Keyword
	def verifyEditedTrackLengthNotSaved(){
		WebUI.verifyNotEqual(railroadActions.setTextForTrackLengthEdit(), railroadActions.searchForNotEditedTrackDetails())
	}

	/**
	 *  Verify the edited track type
	 */
	@Keyword
	def verifyEditedTrackType(){
		WebUI.verifyEqual(railroadActions.getTrackTypeUi(), railroadActions.searchTrackType())
	}

	/**
	 * Method to verify duplicate error message for track
	 */
	@Keyword
	def verifyDuplicateTrackIDErrorMessage(){
		WebUI.verifyEqual(railroadActions.duplicateTrackId(), RailroadPageConstants.TRACKID_DUPLICATE_ERROR_MESSAGE)
		utils.verifySaveButtonDisabled(trackSaveButton)
	}

	/**
	 * Method to verify the Zone Search with Zone Id
	 * @param searchString
	 */
	@Keyword
	def verifyZoneSearchWithZone(String stationString, String zonestring){
		List<WebElement> listOfElements = driver.findElements(By.xpath(RailroadPageConstants.RAILROAD_SUMMERY))
		for(WebElement ele : listOfElements){
			String[] text = ele.getText().split("\n");
			String[] station = text[0].split("-")
			String[] zone = text[1].split("-")
			for(String stn : station){
				for( String str : zone){
					boolean flag1 = (str.matches(zonestring))?true:false
					WebUI.verifyEqual(true, flag1)
					boolean flag2 = (stn.matches(stationString))?true:false
					WebUI.verifyEqual(true, flag2)
				}
			}
		}
	}

	/**
	 * Method to verify the Station Search with Station Id
	 * @param searchString
	 */
	@Keyword
	def verifyStationSearchWithStation(String stationString){
		List<WebElement> listOfElements = driver.findElements(By.xpath(RailroadPageConstants.RAILROAD_SUMMERY))
		for(WebElement ele : listOfElements){
			String[] text = ele.getText().split("\n");
			String[] station = text[1].split("-")
			String[] zone = text[1].split("-")
			for(String stn : station){
				boolean flag2 = (stn.matches(stationString))?true:false
				WebUI.verifyEqual(true, flag2)
			}
		}
	}

	/**
	 * Method to verify message when No Station and Zone available
	 * @param searchString
	 */
	@Keyword
	def verifyMessagewhennoStationandZoneAvailable(String stationString){
		boolean flag = (stationString.matches(RailroadPageConstants.RAILROAD_MESSAGE_NO_STAION_ZONE))?true:false
		WebUI.verifyEqual(true, flag)
	}

	/**
	 * Verify if a station can be recreated under same railroad
	 */
	@Keyword
	def verifyIfStationCanBeRecreatedUnderSameRailroad(String stationIdText){
		WebUI.setText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id'), stationIdText)
		WebUI.sendKeys(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id'), CommonPageConstants.TAB_KEY)
		def actualStationIdErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id_error_message'))
		WebUI.verifyEqual(actualStationIdErrorMessage, RailroadPageConstants.DUPLICATE_STATION_ERROR_MESSAGE)
	}

	/**
	 * Method to search a track by track id
	 * @param tracksList
	 */
	@Keyword
	def verifySearchByTrackID(List<TracksModel> tracksList ){
		TracksModel tracksObject = getTheTrackObject(tracksList)
		String[] trackData = tracksObject.getTrackId().split("\n")
		String trackId = trackData[0]

		/**
		 * Getting Track ID Search field Object to set the text
		 */
		TestObject trackIdElement = findTestObject('Object Repository/F5362 - Configure and Manage Track/trackIdSearchBx')
		
		/**
		 * Setting text into Track ID search box
		 */
		railroadActions.setTextForTracksSearchBox(trackIdElement,trackId)
		
		/**
		 * Verifying the search results
		 */
		verifyTracksDataAfterSearch(tracksObject,trackData)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(trackIdElement,trackId.length())

		/**
		 * Setting partial text into Track ID search box
		 */
		railroadActions.setTextForTracksSearchBox(trackIdElement,trackId.substring(trackId.length()-2))
		
		/**
		 * Verifying the search results
		 */
		verifyTracksDataAfterSearch(tracksObject,trackData)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(trackIdElement,trackId.length()-2)
		WebUI.delay(2)
	}

	/**
	 * Method to search a track by track Description
	 * @param tracksList
	 */
	@Keyword
	def verifySearchByTrackDescription(List<TracksModel> tracksList ){
		TracksModel tracksObject = getTheTrackObject(tracksList)
		String[] trackData = tracksObject.getTrackId().split("\n")
		String trackDescription = trackData[1]
		
		/**
		 * Getting Track Description Search field Object to set the text
		 */
		TestObject trackDescriptionElement = findTestObject('Object Repository/F5362 - Configure and Manage Track/trackDescriptionSearchBox')
		
		/**
		 * Setting full search text into Track Description search box
		 */
		railroadActions.setTextForTracksSearchBox(trackDescriptionElement,trackDescription)
		
		/**
		 * Verifying the search results
		 */
		verifyTracksDataAfterSearch(tracksObject,trackData)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(trackDescriptionElement,trackDescription.length())
		
		/**
		 * Setting partial text into Track Description search box
		 */
		railroadActions.setTextForTracksSearchBox(trackDescriptionElement,trackDescription.substring(trackDescription.length()-2))
		
		/**
		 * Verifying the search results
		 */
		verifyTracksDataAfterSearch(tracksObject,trackData)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(trackDescriptionElement,trackDescription.length()-2)
		WebUI.delay(2)
	}

	/**
	 * Method to search a track by track type
	 * @param tracksList
	 */
	@Keyword
	def verifySearchByTrackType(List<TracksModel> tracksList ){
		TracksModel tracksObject = getTheTrackObject(tracksList)
		String[] trackData = tracksObject.getTrackId().split("\n")
		String trackType = trackData[2]
		
		/**
		 * Getting Track Type Search field Object to set the text
		 */
		TestObject trackTypeElement = findTestObject('Object Repository/F5362 - Configure and Manage Track/trackTypeSearchBox')
		
		/**
		 * Setting  text into Track Type search box
		 */
		railroadActions.setTextForTracksSearchBox(trackTypeElement,trackType)
		
		/**
		 * Verifying the search results
		 */
		verifyTracksDataAfterSearch(tracksObject,trackData)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(trackTypeElement,trackType.length())

		/* Commenting below code because of the existing defect */
		/*
		 * partialSearchByTrackType(trackType)
		 * verifyTracksDataAfterSearch(tracksObject,trackData)
		 * railroadActions.backSpaceTrackTypeTextFields(trackType.length()-2)
		 */
		WebUI.delay(2)
	}

	/**
	 * Method to search a track by track length
	 * @param tracksList
	 */
	@Keyword
	def verifySearchByTrackLength(List<TracksModel> tracksList ){
		TracksModel tracksObject = getTheTrackObject(tracksList)
		String[] trackData = tracksObject.getTrackId().split("\n")
		String trackLength = trackData[3]
		
		/**
		 * Getting Track Length Search field Object to set the text
		 */
		TestObject trackLengthElement = findTestObject('Object Repository/F5362 - Configure and Manage Track/trackLengthSearchBox')
		
		/**
		 * Setting full search text into Track Length search box
		 */
		railroadActions.setTextForTracksSearchBox(trackLengthElement,trackLength)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(trackLengthElement,trackLength.length())
		WebUI.delay(2)
	}

	/**
	 * Method to search a track by car capacity
	 * @param tracksList
	 */
	@Keyword
	def verifySearchByCarCapacity(List<TracksModel> tracksList ){
		TracksModel tracksObject = getTheTrackObject(tracksList)
		String[] trackData = tracksObject.getTrackId().split("\n")
		String carCapacity = trackData[4]
		
		/**
		 * Getting car capacity Search field Object to set the text
		 */
		TestObject carCapacityElement = findTestObject('Object Repository/F5362 - Configure and Manage Track/carCapactitySearchBox')
		
		/**
		 * Setting full search text into Car Capacity search box
		 */
		railroadActions.setTextForTracksSearchBox(carCapacityElement,carCapacity)
		
		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(carCapacityElement,carCapacity.length())
		WebUI.delay(2)
	}

	/**
	 * Method to search a track by condition code
	 * @param tracksList
	 */
	@Keyword
	def verifySearchByConditionCode(List<TracksModel> tracksList ){
		TracksModel tracksObject = getTheTrackObject(tracksList)
		String[] trackData = tracksObject.getTrackId().split("\n")
		String conditionCode = trackData[5]
		
		/**
		 * Getting Condition Code Search field Object to set the text
		 */
		TestObject conditionCodeElement = findTestObject('Object Repository/F5362 - Configure and Manage Track/conditionCodeSearchBox')
		
		/**
		 * Setting full search text into Condition Code search box
		 */
		railroadActions.setTextForTracksSearchBox(conditionCodeElement,conditionCode)
		
		verifyTracksDataAfterSearch(tracksObject,trackData)

		railroadActions.deleteSearchTextByBackSpace(conditionCodeElement,conditionCode.length())

		/**
		 * Setting partial search text into Condition Code search box
		 */
		railroadActions.setTextForTracksSearchBox(conditionCodeElement,conditionCode.substring(conditionCode.length()-1))
		verifyTracksDataAfterSearch(tracksObject,trackData)

		/**
		 * deleting search Text
		 */
		railroadActions.deleteSearchTextByBackSpace(conditionCodeElement,conditionCode.length()-1)
		WebUI.delay(2)
	}

	/**
	 * method to Get the track object
	 * @param tracksList
	 */
	def getTheTrackObject(List<TracksModel> tracksList){
		int size = tracksList.size()
		int valueToSelect = utils.generateRandomNumber(size)
		TracksModel tracksObject = tracksList.get(valueToSelect-1)
		return tracksObject
	}

	/**
	 * Verifying the search results
	 * @param tracksObject
	 * @param trackData
	 */
	def verifyTracksDataAfterSearch(TracksModel tracksObject, String[] trackData){
		/**
		 * Getting the actual search result track values
		 */
		String trackId = driver.findElement(By.xpath(RailroadPageConstants.trackID)).getText()
		String trackDescription = driver.findElement(By.xpath(RailroadPageConstants.trackDescription)).getText()
		String trackType = driver.findElement(By.xpath(RailroadPageConstants.trackType)).getText()
		String trackLength = driver.findElement(By.xpath(RailroadPageConstants.trackLength)).getText()
		String carCapacity = driver.findElement(By.xpath(RailroadPageConstants.carCapacity)).getText()
		String conditionCode = driver.findElement(By.xpath(RailroadPageConstants.conditionCode)).getText()
		
		/**
		 * Verifying the search results with the expected values
		 */
		WebUI.verifyEqual(trackId, trackData[0])
		WebUI.verifyEqual(trackDescription, trackData[1])
		WebUI.verifyEqual(trackType, trackData[2])
		WebUI.verifyEqual(trackLength, trackData[3])
		WebUI.verifyEqual(carCapacity, trackData[4])
		WebUI.verifyEqual(conditionCode, trackData[5])
	}

	/**
	 * Verify if the zone can be recreated under the same station
	 */
	@Keyword
	def verifyIfZoneCanBeRecreatedUnderSameStation(String zoneIdText){
		WebUI.setText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID'), zoneIdText)
		WebUI.sendKeys(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID'), CommonPageConstants.TAB_KEY)
		def actualZoneIdFieldErrorMessage = WebUI.getText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneIdErrorMessage'))
		WebUI.verifyEqual(actualZoneIdFieldErrorMessage, RailroadPageConstants.DUPLICATE_ZONE_ERROR_MESSAGE)
	}

	/**
	 * Method to verify delete message when there are more than one zones and tracks
	 */
	@Keyword
	def verifyDeleteMessage(){
		String actualMessage  = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/deleteConfirmationMessage'))
		String deleteMessage = RailroadPageConstants.STATION_DELETE_MESSAGE_TEXT1+railroadActions.stationId+RailroadPageConstants.STATION_DELETE_MESSAGE_TEXT2+railroadActions.getSelectedZoneAPI()+RailroadPageConstants.STATION_DELETE_MESSAGE_TEXT3+railroadActions.getSelectedTrackAPI()+RailroadPageConstants.STATION_DELETE_MESSAGE_TEXT4
		WebUI.verifyEqual(actualMessage, deleteMessage)
	}
}
