package com.ge.tms.commonactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import com.ge.tms.constants.EquipmentPageConstants
import com.ge.tms.constants.Rule15OfferingsPageConstants
import com.ge.tms.constants.CommonPageConstants
import com.ge.tms.util.CommonUtilities
import groovy.json.JsonSlurper

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.Keys

/**
 * Class for all actions related to all Rule 15 actions
 */
public class Rule15Actions {

	/**
	 *  Webdriver Instance from selenium
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities commonUtil = new CommonUtilities()

	/**
	 * Variable for hold the trainID took from API
	 */
	public static String pickATrainID

	/**
	 * Variable for hold the stationID took from API
	 */
	public static String pickAStationID

	/**
	 * Variable for hold the trainId while creating a new Rule 15
	 */
	public static String rule15TrainId

	/**
	 * Variable to hold the edited first name
	 */
	public static int rule15EditedAuthNumber

	/**
	 * Variable to hole the autho number
	 */
	public static int rule15AuthNumber

	/**
	 * Variable to hold the edited train id
	 */
	public static String rule15EditedTrainId

	/**
	 * Variable for hold the railroad selected while creating a new Rule 15
	 */
	public static String rule15RailroadSelected

	/**
	 * Variable for hold the Equipment ID took from API
	 */
	public static String pickedEquipmentID

	/**
	 * Variable to hold the Random Train ID took from API
	 */
	public static String randomConsistTrainId

	/**
	 * List to hold Equipment ID of the Add Cars and Resend
	 */
	public static List<String> addCarsAndResendEquipmentIDsList

	/**
	 * Variable for hold the selected offering type
	 */
	public static String selectedOfferingType

	/**
	 * Variable for hold the station filter
	 */
	public static String selectedStation

	/**
	 *  Keyboard actions for shift+tab 
	 */
	private String selectAll = Keys.chord(Keys.SHIFT, Keys.TAB);

	/**
	 * Send request for get all rule 15 offers
	 */
	ResponseObject getAllRule15OffersObj = WS.sendRequest(findTestObject('Object Repository/Rule15Services/GETALL-RULE15_OFFERS'))

	/**
	 * Get all rule15 offers through API
	 * @param rule15ResponseBody
	 * @return response
	 */
	def getAllRule15Offers(){
		String rule15ResponseBody = getAllRule15OffersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		ArrayList<String> response  = slupper.parseText(rule15ResponseBody)
		return response
	}

	/**
	 * Get count of rule 15 offers
	 * @return responseForRule15OffersSize
	 */
	def getNumberOfOffersFromAPI(){
		ArrayList<String> responseForRule15Offers = getAllRule15Offers()
		return (responseForRule15Offers.size)
	}

	/**
	 * Set the randomly picked trainID Station and Equipment IDs
	 * @param randomlyPickedTrainID
	 * @param pickATrainID
	 * @param randomlyPickedStationID
	 * @param pickAStationID
	 */
	def setValueInSearchfield(String searchBy){
		if(searchBy.equals(Rule15OfferingsPageConstants.TRAIN_ID)){
			int randomlyPickedTrainID = commonUtil.generateRandomNumberLeftPane(this.getNumberOfOffersFromAPI())
			ArrayList<String> responseTrainID = getAllRule15Offers()
			pickATrainID = responseTrainID[randomlyPickedTrainID].trainId
			WebUI.setText(findTestObject('Object Repository/F12441 - Rule15/Page_Transportation Management Syst/Rule15_SearchField'), pickATrainID)
		}
		else if(searchBy.equals(Rule15OfferingsPageConstants.STATION_ID)){
			int randomlyPickedStationID = commonUtil.generateRandomNumberLeftPane(this.getNumberOfOffersFromAPI())
			ArrayList<String> responseStationID = getAllRule15Offers()
			pickAStationID = responseStationID[randomlyPickedStationID].stationName
			WebUI.setText(findTestObject('Object Repository/F12441 - Rule15/Page_Transportation Management Syst/Rule15_SearchField'), pickAStationID)
		}

		/**
		 * Search by Equipment ID feature will be done in a future release (Hold the test scenario, because of the DE73090)
		 * 
		 *	else if(searchBy.equals(Rule15PageConstants.EQUIPMENT_ID)){
		 *		int randomlyPickedEquipment = commonUtil.generateRandomNumberLeftPane(this.getNumberOfOffersFromAPI())
		 *		ArrayList<String> responseEquipment = getAllRule15Offers()
		 *		String pickAEquipmentInitial = responseEquipment[randomlyPickedEquipment].equipments.equipmentInitial
		 *		String pickAEquipmentNumber = responseEquipment[randomlyPickedEquipment].equipments.equipmentNumber
		 *		pickedEquipmentID = pickAEquipmentInitial+pickAEquipmentNumber
		 *		WebUI.setText(findTestObject('Object Repository/F12441 - Rule15/Page_Transportation Management Syst/Rule15_SearchField'), pickedEquipmentID)
		 }*/

		else {
			WebUI.setText(findTestObject('Object Repository/F12441 - Rule15/Page_Transportation Management Syst/Rule15_SearchField'), Rule15OfferingsPageConstants.EQUIPMENT_ID)
		}
	}

	/**
	 * Method to get the api data for the given train id
	 * @param trainId
	 * @return responseObject
	 */
	def getOfferingDetails(String trainId){
		ResponseObject responseObject = WS.sendRequest(findTestObject('Rule15Services/GET-Rule15OfferingByTrainID', [('trainID') : trainId]))
		return responseObject;
	}

	/**
	 * Click on the Create New offering button
	 */
	def clickOnCreateNewRule15OfferingButton(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/createNewRuleOfferButton'))
		WebUI.delay(1)
	}

	/**
	 * Method to select randomly select a value from To Railroad dropdown
	 */
	def selectValueToRailroad(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.CLICK_ON_TO_RAILROAD, null)
		int railroadLength = WebUI.executeJavaScript(Rule15OfferingsPageConstants.TO_RAILROAD_LENGTH, null)
		int valueToBeSelected = commonUtil.generateRandomNumber(railroadLength)
		WebUI.executeJavaScript('document.querySelector(\'#to-railroad\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		rule15RailroadSelected = WebUI.executeJavaScript(Rule15OfferingsPageConstants.RAILROAD_SELECTED, null)
		WebUI.delay(2)
	}

	/**
	 * Method to select randomly select a value from To Station dropdown
	 */
	def selectValueToStation(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.CLICK_ON_TO_STATION, null)
		int stationLength = WebUI.executeJavaScript(Rule15OfferingsPageConstants.TO_STATION_LENGTH, null)
		int valueToBeSelected = commonUtil.generateRandomNumber(stationLength)
		WebUI.executeJavaScript('document.querySelector(\'#to-station\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
	}

	/**
	 * Set value for the train number
	 * @param TrainId passed
	 */
	def setValueForTrainId(String trainIdNumber){
		rule15TrainId = trainIdNumber
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/trainNumberInputField'),trainIdNumber)
	}

	/**
	 * Set Value for the Authorization Field
	 * @param Authorization number passed
	 */
	def setValueForAuthorizationField(int authorizationNo){
		rule15AuthNumber = authorizationNo
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/authorizationInputField'),String.valueOf(authorizationNo))
	}

	/**
	 * To set the value for the offering Railroad first name
	 * @param offeringRoadFirstName passed
	 */
	def setValueForOfferingRoadFirstName(String offeringRoadFirstName){
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/offeringRoadFirstNameInputField'),offeringRoadFirstName)
	}

	/**
	 * To set the value for the offering Railroad last name
	 * @param offeringRoadLastName passed
	 */
	def setValueForOfferingRoadLastName(String offeringRoadLastName){
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/offeringRoadLastNameInputField'),offeringRoadLastName)
	}

	/**
	 * To set the value for the offering Railroad telephone
	 * @param offeringRoadTelephone passed
	 */
	def setValueForOfferingRoadTelephone(int offeringRoadTelephone){
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'),String.valueOf(offeringRoadTelephone))
	}

	/**
	 * To set the value for the receiving Railroad first name
	 * @param receivingRoadFirstName passed
	 */
	def setValueForReceivingRoadFirstName(String receivingRoadFirstName){
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/receivingRoadFirstNameInputField'),receivingRoadFirstName)
	}

	/**
	 * To set the value for the offering Railroad last name
	 * @param receivingRoadLastName passed
	 */
	def setValueForReceivingRoadLastName(String receivingRoadLastName){
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/receivingRoadLastNameInputField'),receivingRoadLastName)
	}

	/**
	 * To set the value for the receiving Railroad telephone
	 * @param receivingRoadTelephone passed
	 */
	def setValueForReceivingRoadTelephone(int receivingRoadTelephone){
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/receivingRoadTelephoneInputField'),String.valueOf(receivingRoadTelephone))
	}

	/**
	 * Click on the Next button
	 */
	def clickOnNextButton(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/createOfferingNextButton'))
		WebUI.delay(2)
	}

	/**
	 * Select random equipment id from the Table
	 * click on the checkbox
	 */
	def selectRandomEquipmentCarFromTable(){
		List<WebElement> listOfElements = driver.findElements(By.xpath(Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE))
		int expectedSize = listOfElements.size()
		String xpathForRandomEquipmentInTable = Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE+"["+ expectedSize +"]//input[@type='checkbox']"
		driver.findElement(By.xpath(xpathForRandomEquipmentInTable)).click()
	}

	/**
	 * Click on the Continue button in the Create new Rule 15 offering
	 */
	def clickOnContinueButton(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/continueButtonInCreateNewRule15'))
	}

	/**
	 * Add mandatory values for the Create Rule 15 offer fields
	 */
	def addValuesToCreateNewRule15Offering(){

		/**
		 * set all values for the fields in the first page
		 * Click on the Next button
		 */
		selectValueToRailroad()
		selectValueToStation()
		setValueForTrainId(commonUtil.generateRandomString(9))
		setValueForAuthorizationField(commonUtil.generateRandomNumber(5))
		setValueForOfferingRoadFirstName(commonUtil.generateRandomAlphabets(6))
		setValueForOfferingRoadLastName(commonUtil.generateRandomAlphabets(4))
		setValueForOfferingRoadTelephone(commonUtil.generateRandomNumberWithLimit(Rule15OfferingsPageConstants.RANDOM_NUMBER_MIN_RANGE, Rule15OfferingsPageConstants.RANDOM_NUMBER_MAX_RANGE))
		setValueForReceivingRoadFirstName(commonUtil.generateRandomAlphabets(5))
		setValueForReceivingRoadLastName(commonUtil.generateRandomAlphabets(4))
		setValueForReceivingRoadTelephone(commonUtil.generateRandomNumberWithLimit(Rule15OfferingsPageConstants.RANDOM_NUMBER_MIN_RANGE, Rule15OfferingsPageConstants.RANDOM_NUMBER_MAX_RANGE))
		clickOnNextButton()
		selectRandomEquipmentCarFromTable()
		clickOnContinueButton()
	}

	/**
	 * Click on the Send button in the  Confirmation message
	 */
	def clickOnSendRule15Offering(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/sendButtonInConfirmationMsg'))
		WebUI.delay(2)
	}

	/**
	 * Search by train id in the Search text box
	 * @param Train id is passed
	 */
	def searchByTrainId(String searchTrainId){
		WebUI.setText(findTestObject('Object Repository/F12441 - Rule15/Page_Transportation Management Syst/Rule15_SearchField'), searchTrainId)
		WebUI.delay(3)
	}

	/**
	 * Click on Filter icon
	 */
	def clickOnFilter(){
		WebUI.click(findTestObject('Rule15 Offerings/filter-button'))
	}

	/**
	 * Offering Type dropdown click and select
	 */
	def offeringDropdownClickAndSelect(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.OFFERING_TYPE_DROPDOWN, null)
		int listOfOfferingType = WebUI.executeJavaScript(Rule15OfferingsPageConstants.OFFERING_TYPE_ITEMS_LENGTH, null)
		int sizeOfOfferingType = commonUtil.generateRandomNumber(listOfOfferingType)
		String OfferingToBeSelected = "document.querySelector('#offeringType').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelector('#dropdown > div > #selector > div:nth-child("+ (sizeOfOfferingType) +")').click()"
		WebUI.executeJavaScript(OfferingToBeSelected, null)
		WebUI.delay(2)
		selectedOfferingType = WebUI.executeJavaScript(Rule15OfferingsPageConstants.OFFERING_TYPE_UI, null)
	}

	/**
	 *  station dropdown click and select
	 */
	def stationDropDownClickAndSelect(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.STATION_DROPDOWN, null)
		int listOfStation = WebUI.executeJavaScript(Rule15OfferingsPageConstants.STATION_ITEMS_LENGTH, null)
		int sizeOfStation = commonUtil.generateRandomNumber(listOfStation)
		String stationToBeSelected = "document.querySelector('#station').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelector('#dropdown > div > #selector > div:nth-child("+ sizeOfStation +")').click()"
		WebUI.executeJavaScript(stationToBeSelected, null)
		WebUI.delay(2)
		selectedStation = WebUI.executeJavaScript(Rule15OfferingsPageConstants.STATION_UI, null)
	}

	/**
	 * Apply button for filter
	 */
	def applyButtonClick(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/filter_apply_button'))
		WebUI.delay(2)
	}

	/**
	 *  Select the filter options for Rule15
	 */
	def selectFilterOptions(){
		clickOnFilter()
		offeringDropdownClickAndSelect()
		applyButtonClick()
	}

	/**
	 *  Cancel button for filter
	 */
	def cancelButtonFilter(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/filter_cancel_button'))
	}

	/**
	 *  Select the filter options for Rule15 and cancel
	 */
	def selectFilterOptionsAndCancel(){
		clickOnFilter()
		offeringDropdownClickAndSelect()
		cancelButtonFilter()
	}

	/**
	 *  Apply Filter and clear
	 */
	def clearTheAppliedFilter(){
		selectFilterOptions()
		clickOnFilter()
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/filter_clear_button'))
	}

	/**
	 *  check error messages of Railroad
	 */
	def navigateAndViewErrorMessageOfRailRoad(){
		clickOnCreateNewRule15OfferingButton()
		selectValueToRailroad()
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.DESELECT_TO_RAILROAD, null)
	}

	/**
	 *  check error messages of Station
	 */
	def viewErrorMessageOfStation(){
		selectValueToRailroad()
		selectValueToStation()
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.CLICK_ON_TO_STATION, null)
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.DESELECT_TO_STATION, null)
	}
	/**
	 *  Train Id empty error message
	 */
	def trainIdEmptyError(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/trainNumberInputField'))
		WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/trainNumberInputField'), CommonPageConstants.TAB_KEY)
	}

	/**
	 *  Offering road first name
	 *  @param string to check if numerical is passed
	 */
	def offeringRoadFirstNameError(String numeric){
		if (numeric.equals('number')){
			WebUI.click(findTestObject('Object Repository/Rule15 Offerings/offeringRoadFirstNameInputField'))
			setValueForOfferingRoadFirstName((commonUtil.generateRandomNumber(1)).toString())
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadFirstNameInputField'), CommonPageConstants.TAB_KEY)
		}
		else if(numeric.equals('alphabets')){
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadLastNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadFirstNameInputField'), Keys.chord(Keys.DELETE))
			WebUI.delay(2)
		}
	}

	/**
	 *  Offering road last name
	 *  @param string to check if numerical is passed
	 */
	def offeringRoadLastNameError(String numeric){
		if (numeric.equals('number')){
			WebUI.click(findTestObject('Object Repository/Rule15 Offerings/offeringRoadLastNameInputField'))
			setValueForOfferingRoadLastName((commonUtil.generateRandomNumber(1)).toString())
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'), CommonPageConstants.TAB_KEY)
		}
		else if(numeric.equals('alphabets')){
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadLastNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadLastNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadLastNameInputField'), Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(2)
		}
	}

	/**
	 *  Offering road phone number
	 *  @param string to check if numerical is passed
	 */
	def offeringRoadPhoneNumberError(String numeric){
		if (numeric.equals('alphabets')){
			WebUI.click(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'))
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'),commonUtil.generateRandomString(1))
		}
		else if(numeric.equals('number')){
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/offeringRoadTelephoneInputField'), Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(2)
		}
	}

	/**
	 *  Receiving road first name
	 *  @param string to check if numerical is passed
	 */
	def receivingRoadFirstNameError(String numeric){
		if (numeric.equals('number')){
			WebUI.click(findTestObject('Object Repository/Rule15 Offerings/receivingRoadFirstNameInputField'))
			setValueForReceivingRoadFirstName((commonUtil.generateRandomNumber(1)).toString())
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadLastNameInputField'), CommonPageConstants.TAB_KEY)
		}
		else if(numeric.equals('alphabets')){
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadFirstNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadFirstNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadFirstNameInputField'), Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(2)
		}
	}

	/**
	 *  Receiving road last name
	 *  @param string to check if numerical is passed
	 */
	def receivingRoadLastNameError(String numeric){
		if (numeric.equals('number')){
			WebUI.click(findTestObject('Object Repository/Rule15 Offerings/receivingRoadLastNameInputField'))
			setValueForReceivingRoadLastName((commonUtil.generateRandomNumber(1)).toString())
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadTelephoneInputField'), CommonPageConstants.TAB_KEY)
		}
		else if(numeric.equals('alphabets')){
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadLastNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadLastNameInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadLastNameInputField'), Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(2)
		}
	}

	/**
	 *  Receiving road phone number
	 *  @param string to check if numerical is passed
	 */
	def receivingRoadPhoneNumberError(String numeric){
		if (numeric.equals('alphabets')){
			WebUI.click(findTestObject('Object Repository/Rule15 Offerings/receivingRoadTelephoneInputField'))
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadTelephoneInputField'),commonUtil.generateRandomString(1))
		}
		else if(numeric.equals('number')){
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadTelephoneInputField'), selectAll)
			WebUI.delay(1)
			WebUI.sendKeys(findTestObject('Object Repository/Rule15 Offerings/receivingRoadTelephoneInputField'), Keys.chord(Keys.BACK_SPACE))
			WebUI.delay(2)
		}
	}

	/**
	 * Method to get the selected Rule15 Consist which have rule15Status as REJECTED/OFFERED and offeringType
	 * @return randomConsistTrainId
	 */
	@Keyword
	def getSelectedConsist(){
		List<String> consistList = new ArrayList()
		String resText = getAllRule15OffersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfConsists = response.size();
		for(int i=0;i<noOfConsists;i++){
			String rule15Status = response[i].rule15Status.toString()
			String offeringType = response[i].offeringType.toString()
			if(offeringType.equals(Rule15OfferingsPageConstants.OFFERING_TYPE_OUTBOUND)&&rule15Status.equals(Rule15OfferingsPageConstants.RULE15_STATUS_REJECTED)||rule15Status.equals(Rule15OfferingsPageConstants.RULE15_STATUS_OFFERED)) {
				consistList.add(response[i].trainId.toString())
			}
		}
		int listSize = consistList.size()
		int randomNo = commonUtil.generateRandomNumberExlusiveZero(listSize)
		randomConsistTrainId=consistList[randomNo].toString()
		return randomConsistTrainId
	}

	/**
	 *  Method to click on Add Cars And Resend
	 */
	def clickOnAddCarsAndResend(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.CLICK_ADD_CARS_AND_RESEND, null)
		WebUI.delay(2)
	}

	/**
	 *  Method to click on select All equipment check box
	 */
	def clickOnSelectAllEquipmentCheckBox(){
		WebUI.click(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/selectAllEquipment",))
		WebUI.delay(2)
	}

	/**
	 * Method to get No of Equipments of the Table
	 * @param locator
	 * @return expectedSize
	 */
	def int equipmentTableSize(String locator) {
		List<WebElement> listOfElements = driver.findElements(By.xpath(locator))
		int expectedSize = listOfElements.size()
		return expectedSize
	}

	/**
	 *   Method to click random check box
	 *   @return addCarsAndResendEquipmentIDsList
	 */
	@Keyword
	def clickOnRandomCheckBox(){
		addCarsAndResendEquipmentIDsList = new ArrayList()
		int randomNumber= commonUtil.generateRandomNumberExlusiveZero(equipmentTableSize(Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE))
		for(int i=1;i<=randomNumber;i++) {
			driver.findElement(By.xpath(Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE+"["+ i +"]//input[@type='checkbox']")).click()
			String string = driver.findElement(By.xpath(Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE+'['+i+']/div/div[2]')).getText().trim()
			if(string.isEmpty())
				break
			else
				addCarsAndResendEquipmentIDsList.add(string)
		}
		return addCarsAndResendEquipmentIDsList
	}

	/**
	 * Method to Add Cars and Resend
	 */
	@Keyword
	def addCarsAndResend(){
		getSelectedConsist()
		searchByTrainId(randomConsistTrainId)
		clickOnActions()
		clickOnAddCarsAndResend()
		clickOnSelectAllEquipmentCheckBox()
		clickOnSelectAllEquipmentCheckBox()
		clickOnRandomCheckBox()
		WebUI.click(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/addCarsContinueBtn",))
		WebUI.delay(2)
		WebUI.click(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/confirmationSendButton",))
		WebUI.delay(2)
	}


	/**
	 *  Method to click on Actions Button
	 */
	def clickOnActions(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.CLICK_ACTION, null)
	}

	/**
	 * To Select the Resend Offer dropdown
	 */
	def selectResendOfferInActionsDropdown(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.SELECT_RESEND_OFFER,null)
		WebUI.delay(1)
	}

	/**
	 * Method to resend the rule 15 offer by
	 * Editing the train id and firstname
	 */
	def resendRule15Offer(){
		/**
		 * Edit the train id
		 */
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/trainNumberInputField'))
		WebUI.clearText(findTestObject('Object Repository/Rule15 Offerings/trainNumberInputField'))
		rule15EditedTrainId = commonUtil.generateRandomString(9)
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/trainNumberInputField'),rule15EditedTrainId)

		/**
		 * Edit the authorization number
		 */
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/authorizationInputField'))
		WebUI.clearText(findTestObject('Object Repository/Rule15 Offerings/authorizationInputField'))
		rule15EditedAuthNumber = commonUtil.generateRandomNumber(9)
		WebUI.setText(findTestObject('Object Repository/Rule15 Offerings/authorizationInputField'),String.valueOf(rule15EditedAuthNumber))
	}

	/**
	 * Method to click on the cancel button in the Rule 15 offer
	 */
	def clickOnCancelButtonInRule15(){
		WebUI.click(findTestObject('Object Repository/Rule15 Offerings/cancelButtonInRule15Offer'))
		WebUI.delay(1)
	}
}
