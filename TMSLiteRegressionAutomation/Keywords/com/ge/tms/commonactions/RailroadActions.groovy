package com.ge.tms.commonactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.RailroadPageConstants
import com.ge.tms.railroad.RailroadVerification
import com.ge.tms.railroad.TracksModel
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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import com.ge.tms.constants.CommonPageConstants

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.Keys
import groovy.json.JsonSlurper;
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.junit.After
import org.openqa.selenium.By as By
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.lang.Integer
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject

import java.util.List
import java.util.ArrayList
/**
 * Class for all actions related to all Railroads
 */
public class RailroadActions {

	/**
	 *  Variable to hold track length once created
	 */
	public static String editedTrackLength;

	/**
	 *  Variable to hold track type once created
	 */
	public  static String editedTrackType;

	/**
	 *  Variable which has number of stations displayed
	 */
	public int stationSize;

	/**
	 *   Variable for Random number generated
	 */
	public static int  generatedRandomNumber;

	/**
	 * Variable to store the random number generated while selecting railroad first time
	 */
	public static int randomNumberForRailroad

	/**
	 *  Common utility class instance
	 */
	CommonUtilities utils = new CommonUtilities()

	/**
	 *  Variable to hold station ID once created
	 */
	public  static String  stationId

	/**
	 * variables to hold directory path
	 */
	def userDirPath = System.getProperty("user.dir")
	def path = userDirPath+'/Data Files/addZone.json'

	/**
	 * Global static variable for the zone id
	 */
	public static String zoneId;

	/**
	 * Variable to store the newly created zone name
	 */
	public static String zoneName;

	/**
	 * Variable to store the edited zone name
	 */
	public static String editZoneName;

	/**
	 * Variable to store the updated station name
	 */
	public static String editStationName;

	/**
	 * Variable to store the updated timezone
	 */
	public static String editStationTimeZoneLabel;

	/**
	 * Variable to store the updated Zone type
	 */
	public static String editZoneTypeLabel;

	/**
	 * Variable to store RailRoad Dropdown selected Value
	 */
	public static String railRoadSelected

	/**
	 * Variable to store RailRoad Dropdown value that was selected again
	 */
	public static String railRoadSelectedAgain

	/**
	 *  Variable to store track id
	 */
	public static String trackId;

	/**
	 * variables to hold directory path
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 *  Variable to store no of zones
	 */
	public static String noOfZones;

	/**
	 *  Variable to store no of tracks
	 */
	public static String noOfTracks;
	
	/**
	 *  Variable to store trackLength
	 */
	public static String trackLength;
	
	/**
	 *  Variable to store track Type
	 */
	public static String trackType;
	
	/**
	 * Variable to store the track description
	 */
	public static String trackDescription
	
	/**
	 * Variable to hold the track car capacity
	 */
	public static String trackCarCapacity

	/**
	 * RAILROAD DROPDOWN ACTIONS
	 * Following actions are performed on Railroad dropdown
	 */

	/**
	 * Method to click on the Select Railroad dropdown in the Railroad Setup
	 */
	@Keyword
	def clickOnSelectRailroadDropdown(){
		WebUI.executeJavaScript(RailroadPageConstants.SELECT_RAILROAD_DROPDOWN,null)
		WebUI.delay(3)
	}

	/**
	 *   Method to randomly select a railroad
	 */
	@Keyword
	def selectRandomRailRoad(){
		def listOfRailroads = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_DATALIST,null)
		def generatedNumber = utils.generateRandomNumber(listOfRailroads.size())
		String railRoadToBeSelected = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(' + generatedNumber + ') span\')'
		railRoadSelected = WebUI.executeJavaScript(railRoadToBeSelected+'.innerHTML', null)
		WebUI.executeJavaScript(railRoadToBeSelected+'.click()', null)
		randomNumberForRailroad = generatedNumber
	}

	/**
	 *   Method to randomly select a railroad exclusive First railroad
	 */
	@Keyword
	def selectRandomRailRoadExclusiveFirstRailRoad(){
		String selectedRailRoad
		def listOfRailroads = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_DATALIST,null)
		int generatedNumber = utils.generateRandomNumberExlusiveZero(listOfRailroads.size())
		if (generatedNumber==1) {
			int number = generatedNumber+1
			selectedRailRoad = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(' + number + ') span\')'
		}
		else {
			selectedRailRoad = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(' + generatedNumber + ') span\')'
		}
		railRoadSelected = WebUI.executeJavaScript(selectedRailRoad+'.innerHTML', null)
		WebUI.executeJavaScript(selectedRailRoad+'.click()', null)
		WebUI.delay(3)
	}

	/**
	 * To randomly select an railroad that was not selected before
	 */
	@Keyword
	def selectARailroadNotSelectedBefore(){
		def listOfRailroads = WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_DROPDOWN_DATALIST,null)
		int generatedNumber = utils.generateRandomNumberExlusiveZero(listOfRailroads.size())
		String railRoadToBeSelected = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(' + generatedNumber + ') span\')'
		railRoadSelectedAgain = WebUI.executeJavaScript(railRoadToBeSelected+'.innerHTML', null)

		while(railRoadSelectedAgain.equals(railRoadSelected)) {
			generatedNumber = utils.generateRandomNumberExlusiveZero(listOfRailroads.size())
			railRoadToBeSelected = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(' + generatedNumber + ') span\')'
			railRoadSelectedAgain = WebUI.executeJavaScript(railRoadToBeSelected+'.innerHTML', null)
		}
		WebUI.executeJavaScript(railRoadToBeSelected+'.click()', null)
		WebUI.delay(2)
	}

	/**
	 * Method to search for railroad in dropdown and click on the selected railroad
	 * @param Railroad name
	 * @param randomNumberToClick
	 */
	def searchForARailroadAndClick(String railroadName , int randomNumberToClick){
		clickOnSelectRailroadDropdown()
		WebUI.delay(2)
		String railRoadToBeSelected = 'document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(' + randomNumberToClick + ') span\').click()'
		WebUI.executeJavaScript(railRoadToBeSelected, null)
		WebUI.delay(2)
	}

	/**
	 * Method to randomly select a station under a railroad
	 * return station name
	 */
	@Keyword
	def String selectRandomStationByName(){
		stationSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_LIST_UNDER_RAILROAD, null)
		generatedRandomNumber = utils.generateRandomNumberExlusiveZero(stationSize)
		if (stationSize !=1 && generatedRandomNumber > 1){
			String stationToBeSelected = 'document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\')[' + (generatedRandomNumber-1) + '].click()'
			WebUI.executeJavaScript(stationToBeSelected, null)
			String textOfSelectedStation = 'return document.querySelector(\'px-tree\').shadowRoot.querySelector(\'ul > px-tree-node:nth-child('+ (generatedRandomNumber)+ ')\').shadowRoot.querySelector(\'li.tree__branch.active.selected > span\').innerHTML'
			WebUI.delay(2)
			String station = WebUI.executeJavaScript(textOfSelectedStation, null)
			return station
		}
		else if (generatedRandomNumber == 1 || stationSize ==1 ){
			String textOfSelectedStation = 'return document.querySelector(\'px-tree\').shadowRoot.querySelector(\'ul > px-tree-node\').shadowRoot.querySelector(\'li.tree__branch.active.selected > span\').innerHTML'
			String station = WebUI.executeJavaScript(textOfSelectedStation, null)
			return station
		}
	}

	/**
	 * Method to randomly select a station under a railroad
	 * @return generateRandomNumber
	 */
	@Keyword
	def String selectRandomStation(){
		int stationSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_LIST_UNDER_RAILROAD, null)
		def generateRandomNumber = utils.generateRandomNumberForRailroad(stationSize)
		String stationToBeSelected = 'document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\')[' + (generateRandomNumber) + '].click()'
		WebUI.executeJavaScript(stationToBeSelected, null)
		WebUI.delay(1)
		return generateRandomNumber
	}

	/**
	 * To set text for search text box in left navigation
	 */
	def setTextForSearchInLeftNav(String searchText){
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), searchText)
	}

	/**
	 * Method to clear the text in the search box
	 */
	def  clearSearchTextInLeftNav() {
		Boolean cleared = WebUI.clearText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'))
		WebUI.delay(1)
	}

	/**
	 * STATION ACTIONS
	 * Following actions are performed for Station screen
	 */

	/**
	 *  Method to set Text
	 */
	def setTextForStationName(String stationNameText){
		WebUI.setText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name'),stationNameText)
	}

	/**
	 *  Method to set splc  value for station
	 */
	def setSplcForStation(){
		WebUI.executeJavaScript(RailroadPageConstants.STATION_SPLC, null)
		int splcSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_SPLC_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(splcSize)
		WebUI.executeJavaScript('document.querySelector(\'#splc\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to set fsac value for station
	 */
	def setFsacForStation(){
		int fsacSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_FSAC_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(fsacSize)
		WebUI.executeJavaScript('document.querySelector(\'#fsac\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to set timezone for station
	 */
	def setTimeZoneForStation(){
		int timezoneSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_TIME_ZONE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(timezoneSize)
		WebUI.executeJavaScript('document.querySelector(\'#station-timezone-qualifier\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to set Country for station
	 */
	def setCountryForStation(){
		int countrySize = WebUI.executeJavaScript(RailroadPageConstants.STATION_COUNTRY_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(countrySize)
		WebUI.executeJavaScript('document.querySelector(\'#country\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to set State for station
	 */
	def setStateForStation(){
		int stateSize = WebUI.executeJavaScript(RailroadPageConstants.STATION_STATE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(stateSize)
		WebUI.executeJavaScript('document.querySelector(\'#state\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to click on Save for station
	 */
	def clickOnStationSavebutton(){
		WebUI.click(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_save_button'))
		WebUI.delay(3)
	}

	/**
	 *  Method to send text to staionID
	 *  @param station id
	 */
	def setTextForStationId(String stationIdText){
		WebUI.setText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id'), stationIdText)
		stationId=stationIdText
	}

	/**
	 * Method to click on add new station Button
	 */
	def addNewStationButton(){
		WebUI.click(findTestObject('Object Repository/F11822-Configure and Manage Stations/Add_New_Station_Button'))
	}

	/**
	 *  Method to fill mandatory fields for creating station
	 *  @param set the station id
	 */
	def addStationMandatotyFields(String varStationId){

		if(varStationId == null){
			varStationId = utils.generateRandomString(7)
		}

		/**
		 * Set all values for the mandatory fields
		 */
		addNewStationButton()
		setTextForStationId(varStationId)
		setTextForStationName(utils.generateRandomString(7))
		setSplcForStation()
		setFsacForStation()
		setTimeZoneForStation()
		setCountryForStation()
		setStateForStation()
		clickOnStationSavebutton()
	}

	/**
	 * Method to get error message for station id
	 * @return error message
	 */
	def String errorMessageForStationId(){
		WebUI.click(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id'))
		WebUI.sendKeys(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id'), CommonPageConstants.TAB_KEY)
		def actualStationIdErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id_error_message'))
		return actualStationIdErrorMessage
	}

	/**
	 * Method to get error message for station id
	 * @return error message for special characters
	 */
	def String errorForStationIdSpecialCharacters(){
		WebUI.setText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id'), CommonPageConstants.SPECIAL_CHARACTERS)
		def actualStationIdErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_id_error_message'))
		return actualStationIdErrorMessage
	}

	/**
	 * Method to get error message for station Name
	 * @return error message for Empty Field
	 */
	def String errorMessageForStationName(){
		clickOnStationName()
		String isCleared = WebUI.clearText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name'))
		WebUI.sendKeys(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name')," ")
		WebUI.sendKeys(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name'),CommonPageConstants.TAB_KEY)
		def actualStationNameErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name_error_message'))
		return actualStationNameErrorMessage
	}
	/**
	 * Method to get error message for station NAme
	 * @return error message for special characters
	 */
	def String errorForStationNameSpecialCharacters(){
		clickOnStationName()
		WebUI.setText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name'), CommonPageConstants.SPECIAL_CHARACTERS)
		def actualStationNameErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name_error_message'))
		return actualStationNameErrorMessage
	}

	/**
	 * Method to get error message for splc
	 * @return error message for deselecting value
	 */
	def String splcErrorMessage(){
		setSplcForStation()
		WebUI.executeJavaScript(RailroadPageConstants.STATION_SPLC, null)
		WebUI.executeJavaScript(RailroadPageConstants.STATION_SPLC_DESELECT, null)
		def actualSplcErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_splc_error_message'))
		return actualSplcErrorMessage
	}

	/**
	 * Method to get error message for fsac
	 * @return error message for deselecting value
	 */
	def String fsacErrorMessage(){
		setFsacForStation()
		WebUI.executeJavaScript(RailroadPageConstants.STATION_FSAC, null)
		WebUI.executeJavaScript(RailroadPageConstants.STATION_FSAC_DESELECT, null)
		def actualSplcErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_fsac_error_message'))
		return actualSplcErrorMessage
	}

	/**
	 * Method to get error message for timeZone
	 * @return error message for deselecting value
	 */
	def String timeZoneErrorMessage(){
		setTimeZoneForStation()
		WebUI.executeJavaScript(RailroadPageConstants.STATION_TIME_ZONE, null)
		WebUI.executeJavaScript(RailroadPageConstants.STATION_TIMEZONE_DESELECT, null)
		def actualTimeZoneErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_timezone_error_message'))
		return actualTimeZoneErrorMessage
	}

	/**
	 * Method to get error message for country
	 * @return error message for deselecting value
	 */
	def String countryErrorMessage(){
		setCountryForStation()
		WebUI.executeJavaScript(RailroadPageConstants.STATION_COUNTRY, null)
		WebUI.executeJavaScript(RailroadPageConstants.STATION_COUNTRY_DESELECT, null)
		def actualCountryErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_country_error_message'))
		return actualCountryErrorMessage
	}

	/**
	 * Method to create the delete message when station has zone and tracks
	 * @param String id to set the assetId
	 * @param String deleteMessageSecond part to set the delete message
	 * @return expectedDeleteMessage
	 */
	def String createDeleteMessageForStationWithZoneAndTrack(String id , String deleteMsgSecondPart){
		def  expectedDeleteMessage = RailroadPageConstants.DELETE_MESSAGE_FIRST_HALF+id+deleteMsgSecondPart
		return expectedDeleteMessage
	}

	/**
	 * Method to get error message for state
	 * @return error message for deselecting value
	 */
	def String stateErrorMessage(){
		setStateForStation()
		WebUI.executeJavaScript(RailroadPageConstants.STATION_STATE, null)
		WebUI.executeJavaScript(RailroadPageConstants.STATE_STATE_DESELECT, null)
		def actualCountryErrorMessage = WebUI.getText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_state_error_message'))
		return actualCountryErrorMessage
	}

	/**
	 * Method to click on the Actions menu in View Station
	 */
	@Keyword
	def clickOnStationActionMenu(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_STATION_ACTIONS_MENU, null)
		WebUI.delay(1)
	}

	/**
	 * Method to click on the Edit Station button
	 */
	@Keyword
	def clickOnEditStationButton(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_EDIT_STATION_BUTTON, null)
		WebUI.delay(1)
	}

	/**
	 * Method to click the delete station 
	 */
	@Keyword
	def clickOnDeleteStationButton(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_DELETE_STATION_BUTTON, null)
	}

	/**
	 * Method to click on the station name
	 */
	def clickOnStationName(){
		WebUI.click(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name'))
	}

	/**
	 * Method to edit the Station
	 * Edit station with an updated Station Name
	 * and Updated Time Zone
	 */
	def editStation(){

		/**
		 * Update the station name
		 */
		clickOnStationName()
		String cleared = WebUI.clearText(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_name'))
		editStationName = utils.generateRandomString(5)
		setTextForStationName(editStationName)

		/**
		 * update the timezone
		 */
		setTimeZoneForStation()
		String tempTimezone = WebUI.executeJavaScript(RailroadPageConstants.STATION_TIMEZONE_LABEL, null)
		String[] tempText = tempTimezone.split("-")
		editStationTimeZoneLabel = tempText[0].trim()
	}

	/**
	 * Method to click on the Station Cancel button
	 */
	def clickOnStationCancelButton(){
		WebUI.click(findTestObject('Object Repository/F11822-Configure and Manage Stations/station_cancel_button'))
		WebUI.delay(1)
	}

	/**
	 * ZONE ACTIONS
	 * Following actions are performed for Zone screen
	 */

	/**
	 *  Method to add a zone successfully.
	 *  User is setting all values for the mandatory fields
	 *  @param set Zone id
	 */
	def addZone(String varZoneId){

		if(varZoneId == null){
			varZoneId= utils.generateRandomString(5)
		}

		/**
		 * CLick on the Add zone button 
		 * Set values for mandatory fields
		 */
		clickOnAddNewZoneButton()
		setTextForZoneId(varZoneId)
		setTextForZoneName(utils.generateRandomString(7))
		clickOnZoneTypeDropdown()
		selectZoneType()
	}

	/**
	 * Click on the Add New Zone button
	 */
	def clickOnAddNewZoneButton(){
		WebUI.click(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/addNewZoneButton'))
		WebUI.delay(3)
	}
	/**
	 * To click on the Save button for Zone
	 */
	@Keyword
	def clickOnZoneSaveButton(){
		WebUI.click(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneSaveButton'))
		WebUI.delay(3)
	}

	/**
	 * To click on Cancel button in Zone
	 */
	@Keyword
	def clickOnZoneCancelButton(){
		WebUI.click(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneCancelButton'))
		WebUI.delay(1)
	}

	/**
	 * To click on Actions menu in View Zone
	 */
	@Keyword
	def clickOnZoneActionMenu(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_ZONE_ACTION_MENU, null)
		WebUI.delay(1)
	}

	/**
	 * To click on Edit Zone button
	 */
	@Keyword
	def clickOnEditZoneButton(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_EDIT_ZONE_BUTTON, null)
		WebUI.delay(1)
	}

	/**
	 * MEthod to click the first zone after search is done in the left navigation
	 */
	def clickFirstZoneInLeftNavListAfterSearch(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_FIRST_ZONE_IN_LIST, null)
	}

	/**
	 * Click on the zone id
	 */
	def clickOnZoneId(){
		WebUI.click(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID'))
	}

	/**
	 * Method to set the text for zone id
	 * @param String zone id
	 */
	def setTextForZoneId(String setZoneId){
		zoneId = setZoneId
		WebUI.setText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneID'), zoneId)
	}

	/**
	 * Click on the zone name
	 */
	def clickOnZoneName(){
		WebUI.click(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName'))
	}

	/**
	 * Method to set text for zone name
	 * @param String text
	 */
	def setTextForZoneName(String textName){
		zoneName = textName
		WebUI.setText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName'), textName)
	}

	/**
	 * Click on Zone Type
	 */
	def clickOnZoneTypeDropdown(){
		WebUI.executeJavaScript(RailroadPageConstants.ZONE_TYPE, null)
	}

	/**
	 * Method to select a value from the zone type dropdown
	 */
	def selectZoneType(){
		int zoneTypeSize = WebUI.executeJavaScript(RailroadPageConstants.ZONE_TYPE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(zoneTypeSize)
		WebUI.executeJavaScript('document.querySelector(\'#zone-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 * Method to successfully provide values for Edit Zone
	 * Click on Save when all values are provided
	 */
	def editZone(){

		/**
		 * Click on Zone name to edit
		 */
		clickOnZoneName()
		String clearText = WebUI.clearText(findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Add Edit Zone/zoneName'))
		WebUI.delay(1)
		editZoneName = utils.generateRandomString(5)
		setTextForZoneName(editZoneName)

		/**
		 * Click on the zone type to edit a new value
		 */
		selectZoneType()
		String tempZoneType = WebUI.executeJavaScript(RailroadPageConstants.ZONE_TYPE_LABEL, null)
		editZoneTypeLabel = tempZoneType.substring(0, 1)

		/**
		 * Click on save button
		 */
		clickOnZoneSaveButton()
	}

	/**
	 *  Method to get size of zone in UI
	 *  return number of zones displayed
	 */
	def int getsizeOfZone(){
		int randomNumberLessOne = generatedRandomNumber
		String getNumberOfZones = 'return document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\')['+ randomNumberLessOne +'].shadowRoot.querySelectorAll(\'#collapse > div > px-tree-node\').length'
		WebUI.delay(2)
		int zoneSizeUi = WebUI.executeJavaScript(getNumberOfZones, null)
		return zoneSizeUi
	}

	/**
	 * Method to click on the Delete Zone
	 * @param delete button
	 */
	@Keyword
	def clickOnDeleteAction(String deleteButton){
		WebUI.executeJavaScript(deleteButton, null)
	}

	/**
	 * Method to click on any test object
	 * @param Test Object passed
	 */
	@Keyword
	def clickOnTestObject(TestObject testObj){
		WebUI.click(testObj)
	}

	/**
	 * TRACK ACTIONS
	 * Following actions are performed for Track screen
	 */

	/**
	 * Click on the Add New Track button
	 */
	def clickOnAddNewTrackButton(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/AddTrackButton'))
		WebUI.delay(1)
	}

	/**
	 *  Method to set Text for track id  findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_search_field_row1')
	 *  @param set Track id text
	 *  @return trackId
	 */
	def setTextForTrackId(String setTrackId){
		trackId = setTrackId
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input'), trackId)
		return trackId
	}

	/**
	 *  Method to set Text track description
	 *  @param trackdescription
	 */
	def setTextForTrackDescription(String trackDescText){
		WebUI.setText( findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'), trackDescText)
		trackDescription = trackDescText
		}

	/**
     *  Method to set track type
     */
    def setTrackType(){
        int trackTypeLength = WebUI.executeJavaScript(RailroadPageConstants.TRACK_TYPE_LENGTH, null)
        int valueToBeSelected = utils.generateRandomNumber(trackTypeLength)
        WebUI.executeJavaScript('document.querySelector(\'#trackType\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
        trackType = WebUI.executeJavaScript(RailroadPageConstants.TRACK_TYPE_SELECTED, null)
        }

	/**
	 *  Method to set Text track Length
	 *  @param track length
	 */
	def setTextForTrackLength(String trackLengthText){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Length_input'), trackLengthText)
	}

	/**
	 *  Method to set Text car capacity
	 *  @param car capacity
	 */
	def setTextForCarCapacity(String carCapacityText){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Car_Capacity_input'), carCapacityText)
	}

	/**
	 *  Method to set track type
	 */
	def setTrackConditionCode(){
		int conditionCode = WebUI.executeJavaScript(RailroadPageConstants.TRACK_CONDITION_CODE, null)
		int valueToBeSelected = utils.generateRandomNumber(conditionCode)
		WebUI.executeJavaScript('document.querySelector(\'#trackConditionCode\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to save Track
	 */
	def saveTrack(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/SaveTrackButton'))
		WebUI.delay(3)
	}

	/**
	 *  Method to Search Track id
	 *  @return string
	 */
	def SearchTrackIdText(){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_search_field_row1_trackid'), trackId)
		return trackId;
	}



	/**
	 *  Method to fill mandatory fields of track and save
	 *  @param track id passed
	 */
	def saveNewTrackRecord(String varTrackId){

		if(varTrackId == null) {
			varTrackId = utils.generateRandomString(6)
		}
		clickOnAddNewTrackButton()
		setTextForTrackId(varTrackId)
		setTextForTrackDescription(utils.generateRandomString(10))
		setTrackType()
		trackLength = utils.generateRandomNumber(3).toString()
		setTextForTrackLength(trackLength)
		trackCarCapacity = utils.generateRandomNumber(3).toString()
		setTextForCarCapacity(trackCarCapacity)
		setTrackConditionCode()
	}

	/**
	 *   Values to add new track from station
	 */
	def setValuesAddTrackFromStation(String empty){
		WebUI.executeJavaScript(RailroadPageConstants.ADD_TRACK_STATIONID_CLICK, null)
		saveNewTrackRecord(empty)
		selectZone()
	}

	/**
	 *  Selecting zone for a track from dropdown
	 */
	def selectZone(){
		WebUI.executeJavaScript(RailroadPageConstants.ADD_TRACK_ZONEID_DROPDOWN,null)
		int numberOfZones = WebUI.executeJavaScript(RailroadPageConstants.ADD_TRACK_ZONEID_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(numberOfZones)
		WebUI.executeJavaScript('document.querySelector(\'#zoneId\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelector(\'#dropdown > div > #selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 *  Method to click first zone under a station
	 */
	def clickZoneUnderStation(){
		WebUI.executeJavaScript(RailroadPageConstants.ADD_TRACK_ZONEID_CLICK, null)
	}


	/**
	 *  Track id form search
	 *  @return string
	 */
	def trackIdFromSearch(){
		def actualTrackId = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_First_row_column_trackid'))
		return actualTrackId;
	}

	/**
	 *  Method to get text for Track ID for Empty input
	 *  @ return string
	 */
	def getTrackIDErrorMessage(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input'))
		WebUI.sendKeys(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input'), CommonPageConstants.TAB_KEY)
		def actualTrackIdErrorMessage = WebUI.getText( findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Id_Error_Message'))
		return actualTrackIdErrorMessage
	}

	/**
	 *  Method to get text for Track ID for Alphanumeric input
	 *  @return string
	 */
	def getTrackIDAlphaNumericErrorMessage(){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input'),CommonPageConstants.SPECIAL_CHARACTERS)
		def actualTrackIdErrorMessage = WebUI.getText( findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Id_Error_Message'))
		return actualTrackIdErrorMessage
	}

	/**
	 *  Method to get text for Track Description for Empty input
	 *  @return string
	 */
	def getTrackDescriptionErrorMessage(String errorText){
		if(errorText.equals('addTrack')){
			WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'))
		}
		else if(errorText.equals('editTrack')){
			WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'))
			WebUI.sendKeys(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'), " ")
			WebUI.delay(2)
		}
		WebUI.sendKeys(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'), CommonPageConstants.TAB_KEY)
		def actualTrackDescErrorMessage = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Desc_Error_Message'))
		return actualTrackDescErrorMessage
	}

	/**
	 *  Method to get text for Track DEscription for Alphanumeric input
	 *  @return string
	 */
	def getTrackDescAlphaNumericErrorMessage(){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'),CommonPageConstants.SPECIAL_CHARACTERS)
		WebUI.sendKeys(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Description_input'), CommonPageConstants.TAB_KEY)
		def actualTrackDescErrorMessage = WebUI.getText( findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Desc_Error_Message'))
		return actualTrackDescErrorMessage
	}

	/**
	 * Method to get error message for splc
	 * @return error message for de-selecting value
	 */
	def String trackTypeErrorMessage(){
		setTrackType()
		WebUI.executeJavaScript(RailroadPageConstants.TRACK_TYPE_DROPDOWN, null)
		WebUI.executeJavaScript(RailroadPageConstants.TRACK_TYPE_DESELECT, null)
		def actualtrackTypeErrorMessage = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Type_Error_Message'))
		return actualtrackTypeErrorMessage
	}

	/**
	 *  Method to get text for Track Length for empty input
	 *  @return string
	 */
	def getTrackLengthErrorMessage(){
		WebUI.clearText( findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Length_input'))
		WebUI.sendKeys(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Length_input'), CommonPageConstants.TAB_KEY)
		def actualTrackLengthErrorMessage = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Length_Error_message'))
		return actualTrackLengthErrorMessage
	}

	/**
	 * Method to click on the delete track button of first row of tracks
	 */
	@Keyword
	def clickOnDeleteTrack(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/deleteTrackButton'))
	}

	/**
	 *  Edit track with Track Length
	 */
	def editTrackLength(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/edit_track_icon'))
		editedTrackLength = utils.generateRandomNumber(5).toString()
		setTextForTrackLength(editedTrackLength)
		WebUI.delay(1)
	}

	/**
	 *  Edit track with Track Type
	 */
	def editTrackType(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/edit_track_icon'))
		setTrackType()
		WebUI.delay(2)
		editedTrackType = WebUI.executeJavaScript(RailroadPageConstants.TRACK_TYPE_SELECTED, null)
	}

	/**
	 *  Search Track Length
	 *  @return tracklength
	 */
	def searchTrackLength(){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_search_row_length'),editedTrackLength)
		return Integer.parseInt(editedTrackLength)
	}

	/**
	 *  Get Track Length from UI
	 *  @return track length from UI
	 */
	def getTrackLengthUi(){
		String actualTrackLength = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_First_row_column_length'))
		return actualTrackLength
	}

	/**
	 *  Search Track Type
	 *  @return edited track type
	 */
	def searchTrackType(){
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_search_field_row1_trackid'), trackId)
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_search_row_track_type'),editedTrackType)
		return (editedTrackType)
	}

	/**
	 *  Get Track type from UI
	 *  @return track type displayed in UI
	 */
	def getTrackTypeUi(){
		String actualTrackType = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_First_row_column_tracktype'))
		return actualTrackType
	}

	/**
	 Cancel button Track
	 */
	def clickCancelTrack(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/CancelTrackButton'))
	}

	/**
	 *  Method to set Text track Length
	 *  @param track length
	 */
	def setTextForTrackLengthEdit(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/edit_track_icon'))
		WebUI.delay(2)
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Length_input'))
		WebUI.delay(2)
		String length = utils.generateRandomNumber(5).toString()
		if(length.equals(trackLength)){
			length = utils.generateRandomNumber(5).toString()
		}
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Length_input'), length)
		clickCancelTrack()
		return length
	}

	/**
	 *  Search for non edited record of track
	 *  @return tracklength from Ui
	 */
	def searchForNotEditedTrackDetails(){
		SearchTrackIdText()
		return getTrackLengthUi()
	}

	/**
	 *  Edit track icon
	 */
	def clickEditIconTrack(){
		WebUI.click(findTestObject('Object Repository/F5362 - Configure and Manage Track/edit_track_icon'))
	}

	/**
	 *  No Results found for track
	 *  @return trackid error text
	 */
	def noResultsForTrack(){
		SearchTrackIdText()
		String actualNoTrackText = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/noRowsfound'))
		return actualNoTrackText
	}

	/**
	 *  Edit Clear text Station/zone Search Field
	 */
	def clearStationzoneSearchField(){
		WebUI.clearText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'))
	}

	/**
	 *  Method to set Station Text for StationzoneSearchField  findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav')
	 *  @return stationId Auto generated ID
	 */
	def setStaionForStationzoneSearchField(){
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), stationId)
		return stationId
	}

	/**
	 *  Method to set Zone Text for StationzoneSearchField  findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav')
	 *  @return ZoneID Auto generated ID
	 */
	def setStaionForZonezoneSearchField(){
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), zoneId)
		return zoneId
	}

	/**
	 *  Method to set Invalid Station/Zone Text for StationzoneSearchField  findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav')
	 *  @return Invalid Station/Zone Id from Constants
	 */
	def setInvalidtxtForStationzoneSearchField(){
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), RailroadPageConstants.INVALID_STAIONZONE_ID)
		return RailroadPageConstants.INVALID_STAIONZONE_ID
	}

	/**
	 *  Get No Result Message Leftpane  findTestObject('Object Repository/CommonObjects/noSearchResultMessageLeftNav')
	 */
	def GetNoresultText(){
		WebUI.getText(findTestObject('Object Repository/CommonObjects/noSearchResultMessageLeftNav'))
	}

	/**
	 *  Method to get duplicate error message for track id
	 * @return duplicate error message
	 */
	def duplicateTrackId(){
		clickOnAddNewTrackButton()
		WebUI.setText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input'), trackId)
		WebUI.sendKeys(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_id_input'), CommonPageConstants.TAB_KEY)
		String duplicateTrackError = WebUI.getText(findTestObject('Object Repository/F5362 - Configure and Manage Track/Track_Id_Error_Message'))
	}

	/**
	 * This method will get all tracks data and stores in TracksModel Object
	 * return tracksList
	 */
	@Keyword
	def storeTracksDataToSearch(){
		List<TracksModel> tracksList = new ArrayList<TracksModel>()

		for(int i=1;i<=3;i++){
			String trackId = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[1]")).getText()
			String trackDescription = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[2]")).getText()
			String trackType = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[3]")).getText()
			String trackLength = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[4]")).getText()
			String carCapacity = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[5]")).getText()
			String conditionCode = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[6]")).getText()
			TracksModel model = new TracksModel()

			/**
			 * Setting the tracks data into model object
			 */
			model.setTrackId(trackId)
			model.setTrackDesc(trackDescription)
			model.setTrackType(trackType)
			model.setTrackLength(trackLength)
			model.setCarCapacity(carCapacity)
			model.setConditionCode(conditionCode)
			tracksList.add(model)
		}
		return tracksList;
	}

	/**
	 *  Method to set Text into respective test box in search box
	 *  @param testObject,textToSearch
	 */
	def setTextForTracksSearchBox(TestObject testObject,String textToSearch){
		clearTextFields()
		WebUI.delay(2)
		WebUI.setText(testObject, textToSearch)
	}

	/**
	 * Clearing all text fields after search
	 */
	def clearTextFields(){
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/trackIdSearchBx'))
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/trackDescriptionSearchBox'))
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/trackTypeSearchBox'))
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/trackLengthSearchBox'))
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/carCapactitySearchBox'))
		WebUI.clearText(findTestObject('Object Repository/F5362 - Configure and Manage Track/conditionCodeSearchBox'))
	}

	/**
	 * Clearing all text fields after search
	 */
	def deleteSearchTextByBackSpace(TestObject objectToRemoveText ,int size){
		for(int i=0;i<size;i++){
			WebUI.sendKeys(objectToRemoveText, "\u0008")
		}
	}

	/**
	 * Method to randomly select a station under a railroad
	 */
	@Keyword
	def String selectFirstStation(){
		WebUI.executeJavaScript(RailroadPageConstants.CLICK_FIRST_STATION_IN_LIST,null)
		WebUI.delay(2)
	}

	/**
	 * Method to get the selected Zone from API
	 * @return noOfZones
	 */
	@Keyword
	def getSelectedZoneAPI(){
		ResponseObject getZonesForStationObj = WS.sendRequest(findTestObject('Object Repository/RailRoadServices/GETZoneByStation',[('stationId') : stationId]))

		List<String> zoneList = new ArrayList()
		String resText = getZonesForStationObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfZonesAPI = response.size();
		for(int i=0;i<noOfZonesAPI;i++){
			zoneList.add(response[i].zoneId.toString())
		}
		noOfZones = zoneList.size()
		return noOfZones
	}

	/**
	 * Method to get the selected Tracks from API
	 * @return noOfTracks
	 */
	@Keyword
	def getSelectedTrackAPI(){
		ResponseObject getZonesForStationObj = WS.sendRequest(findTestObject('Object Repository/RailRoadServices/GETTrackByStation',[('stationId') : stationId]))

		List<String> trackList = new ArrayList()
		String resText = getZonesForStationObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfTrackAPI = response.size();
		for(int i=0;i<noOfTrackAPI;i++){
			trackList.add(response[i].zoneId.toString())
		}
		noOfTracks = trackList.size()
		return noOfTracks
	}
}
