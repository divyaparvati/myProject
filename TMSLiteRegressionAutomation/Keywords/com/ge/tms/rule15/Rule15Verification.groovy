package com.ge.tms.rule15

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import groovy.json.JsonSlurper

import com.ge.tms.commonactions.Rule15Actions
import com.ge.tms.constants.CommonPageConstants
import com.ge.tms.constants.Rule15OfferingsPageConstants
import com.ge.tms.util.CommonUtilities
import java.text.SimpleDateFormat;

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

/**
 * Page class for Rule 15 Offering page
 */
public class Rule15Verification {

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities commonUtil = new CommonUtilities()

	/**
	 * instance of the Actions class
	 */
	Rule15Actions actions = new Rule15Actions()

	/**
	 * Create a instance from web driver
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * Send request for get all rule 15 offers
	 */
	ResponseObject getAllRule15OffersObj = WS.sendRequest(findTestObject('Object Repository/Rule15Services/GETALL-RULE15_OFFERS'))

	/**
	 * Method to select the rule 15 offering from left pane
	 * @return trainId
	 */
	@Keyword
	def selectOfferingInLeftPane(){

		List<WebElement> listOfOfferings = driver.findElements(By.xpath(Rule15OfferingsPageConstants.LIST_OF_OFFERINGS_LEFTPANE_XPATH))
		int sizeOfOfferings = listOfOfferings.size();
		int randomNumber = commonUtil.generateRandomNumber(sizeOfOfferings)
		String itemToBeSelected = Rule15OfferingsPageConstants.LIST_OF_OFFERINGS_LEFTPANE_XPATH+"["+randomNumber+"]"
		driver.findElement(By.xpath(itemToBeSelected)).click()
		String trainId = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/selectedTrainId'))
		return trainId
	}

	/**
	 * Verify Response data for the selected train with the actual UI data
	 * @param responseObject
	 */
	@Keyword
	def verifyRule15Offering(ResponseObject responseObject){
		String resText = responseObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def responseList  = slupper.parseText(resText)
		def response = responseList[0]
		def sizeOfResponse = response.size();

		/**
		 * Get UI Data for the selected offerings and validate with expected API data
		 */

		/**
		 * Train ID validation
		 */
		String actualTrainId = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/selectedTrainId'))
		String expectedTrainId = response.trainId
		WebUI.verifyEqual(actualTrainId, expectedTrainId)

		/**
		 * Date validation
		 */
		String dateAndTime = response.offerDateTime
		String actualDate=WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/date'))
		String[] dateAndTimeArray = dateAndTime.split("T")
		String date = dateAndTimeArray[0].toString().replace('-', '/')
		String expectedDate = convertDateMMDDYYYY(date)
		WebUI.verifyEqual(actualDate, expectedDate)

		/**
		 * Time validation
		 */
		String actualTime = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/time'))
		String expectedTime = dateAndTimeArray[1].toString().substring(0,5)
		WebUI.verifyEqual(actualTime, expectedTime)

		/**
		 * Status Validation
		 */

		String actualStatus = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/status'))
		String expectedStatus = response.rule15Status
		WebUI.verifyEqual(actualStatus.toUpperCase(), expectedStatus.toUpperCase())

		/**
		 * Offering Officer
		 */

		String actualOfferingOfficer = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/offeringOfficer'))
		String expectedOfferingOfficer = response.offeringRailroadOfficer
		WebUI.verifyEqual(actualOfferingOfficer, expectedOfferingOfficer)

		/**
		 * Offering Type Validation
		 */
		String actualOfferingType = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/offeringType'))
		String expectedOfferingType = response.offeringType
		WebUI.verifyEqual(actualOfferingType.toUpperCase(), expectedOfferingType.toUpperCase())

		/**
		 * Station validation
		 */
		String actualStation = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/station'))
		String expectedStation = response.stationName
		WebUI.verifyEqual(actualStation, expectedStation)

		/**
		 * Train ID validation
		 */
		String actualTrainIdRightPane = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/trainIDRightPane'))
		String exptectTrainIdRightPane = response.trainId
		WebUI.verifyEqual(actualTrainIdRightPane, exptectTrainIdRightPane)

		/**
		 * Road Validations
		 */
		String actualRoad = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/road'))
		String expectedRoad = response.holdingRoadSwitchCarrier
		WebUI.verifyEqual(actualRoad, expectedRoad)

		/**
		 * Date Validation right Pane
		 */
		String actualDateRightPane = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/offerDateRightPane'))
		String expectedDateRightPane = convertDateDDMMYY(date)
		WebUI.verifyEqual(actualDateRightPane, expectedDateRightPane)

		/**
		 * Time Validation Right Pane
		 */
		String actualTimeRightPane = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/timeRightPane'))
		WebUI.verifyEqual(actualTimeRightPane, expectedTime)

		String actualStatusRightPane = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/rule15StatusRightPane'))
		String expectedStatusRightPane = response.rule15Status
		WebUI.verifyEqual(actualStatusRightPane.toUpperCase(), expectedStatusRightPane.toUpperCase())

		/**
		 * Actual Lead Car
		 */
		String actualLeadCar = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/leadCar'))

		/**
		 * Verifying Loads
		 */
		String actualLoads = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/loadRightPane'))
		String expectedLoads = response.action

		/**
		 * Need clarification on this element, So commented below statement
		 * WebUI.verifyEqual(actualLoads, expectedLoads)
		 */

		/**
		 * MTS Validations
		 */
		String actualMTS = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/mtsRightPane'))

		/**
		 * TTS Validations
		 */
		String actualTTS = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/ttsRightPane'))

		/**
		 * Contact Information Validation
		 */
		String actualOfferingRoad = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/offeringRoadRightPane'))
		String offeringRoad = response.holdingRoadSwitchCarrier
		String expectedOfferingRoad = (offeringRoad == null) ? "--" : offeringRoad;
		WebUI.verifyEqual(actualOfferingRoad, expectedOfferingRoad)

		String actualAuthNumber = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/authNumberRightPane'))
		String authNumber = response.authorizationNumber
		String expectedAuthNumber = (authNumber == "") ? "--" : authNumber;
		WebUI.verifyEqual(actualAuthNumber, expectedAuthNumber)

		String actualOffericerRightPane = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/officerRightPane'))
		String offericerRightPane = response.offeringRailroadOfficer
		String expectedOffericerRightPane = (offericerRightPane == "") ? "--" : offericerRightPane;
		WebUI.verifyEqual(actualOffericerRightPane, expectedOffericerRightPane)

		String actualOfferingPhoneNumber = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/offeringRRPhoneNoRightPane'))
		String offeringPhoneNumber = response.offeringRailroadTelephone
		String expectedOfferingPhoneNumber = (offeringPhoneNumber == null) ? "--" : offeringPhoneNumber;
		WebUI.verifyEqual(actualOfferingPhoneNumber, expectedOfferingPhoneNumber)

		String actualResponsibleRoad = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/responsibleRoadRightPane'))
		String responsibleRoad = response.responsibleRoad
		String expectedResponsibleRoad = (responsibleRoad == null) ? "--" : responsibleRoad;
		WebUI.verifyEqual(actualResponsibleRoad, expectedResponsibleRoad)

		def expectedEquipmentsList = response.equipments
		int sizeOfEquipments = expectedEquipmentsList.size()

		/**
		 * Getting the rows of the Equipments from UI and Iterating through each row
		 */
		List<WebElement> listOfEquipments = driver.findElements(By.xpath(Rule15OfferingsPageConstants.EQUPMENTS_ROWS_XPATH))
		String rowsData;
		int actualSizeOfEquipments=0;
		def ExpectedEquipmentsData;
		ArrayList<String> actualEquipmentsData=new ArrayList<String>()
		if(listOfEquipments.size()>0){

			/**
			 * Taking i value 1 because always 0 is table header
			 */
			for(int i=1;i<listOfEquipments.size();i++){
				WebElement ele = listOfEquipments.get(i)
				rowsData= ele.getText()
				int len = rowsData.trim().length()
				if(rowsData!=null && len>0){
					actualSizeOfEquipments=actualSizeOfEquipments+1
					actualEquipmentsData.add(rowsData.split("\n"))
				}
			}

			/**
			 * Getting the pagination text and taking the count of cars
			 */
			String paginationText = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/paginationText'))
			String[] arrPagination = paginationText.split("of")
			int actualCount = Integer.parseInt(arrPagination[1].trim())

			/**
			 * Verifying the size of equipments with actual and expected
			 */
			WebUI.verifyEqual(actualCount, sizeOfEquipments)

			/**
			 * Since pagination is there for equipmenents more than 5, only 5 cars we are verifying
			 */
			if(actualCount>5){
				actualCount=5
			}

			/**
			 * Iterates through equipment data from API and UI data and verifies both
			 */
			for(int i=0;i<actualCount;i++){
				String equipmentText = expectedEquipmentsList[i]
				String[] arrEquipment = equipmentText.split(",")
				String expectedCar = arrEquipment[0].split("=")[1]+" "+arrEquipment[1].split("=")[1]
				String[] actualEquipmentArr = actualEquipmentsData[i]
				String actualCar = actualEquipmentArr[0]
				WebUI.verifyEqual(actualCar, expectedCar)

				String actualLE = actualEquipmentArr[1]
				String expectedLE = arrEquipment[2].split("=")[1]
				WebUI.verifyEqual(actualLE, expectedLE.charAt(0))
			}
		}
	}

	/**
	 * Convert date into MM/dd/yyyy format
	 * @param dateToConvert
	 * @return date
	 */
	def convertDateMMDDYYYY(String dateToConvert){
		try{
			SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy/MM/dd");
			Date date = sdfSource.parse(dateToConvert);
			SimpleDateFormat sdfDestination = new SimpleDateFormat("MM/dd/yyyy");
			return sdfDestination.format(date);
		}
		catch(ParseException){
			return ""
		}
	}

	/**
	 * date into DD/MM/yy format
	 * @param dateToConvert
	 * @return date
	 */
	def convertDateDDMMYY(String dateToConvert){
		try{
			SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy/MM/dd");
			Date date = sdfSource.parse(dateToConvert);
			SimpleDateFormat sdfDestination = new SimpleDateFormat("yy/MM/dd");
			return sdfDestination.format(date);
		}
		catch(ParseException){
			return ""
		}
	}

	/**
	 * Method to verify search results for Train ID
	 * @param trainIDRightPane
	 */
	def verifyTrainID(){
		String trainIDRightPane = WebUI.getText(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/TrainID_rightPane"))
		WebUI.verifyEqual(trainIDRightPane, actions.pickATrainID)
	}

	/**
	 * Method to verify search results for station ID
	 * @param stationIDRightPane
	 */
	def verifyStationID(){
		String stationIDRightPane = WebUI.getText(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/sattion_ID"))
		WebUI.verifyEqual(stationIDRightPane, actions.pickAStationID)
	}

	/**
	 * Search by Equipment ID feature will be done in a future release(Hold the test scenario, because of the DE73090)
	 * Method to verify search results for equipment ID
	 *
	 def verifyEquipmentID(){
	 String EquipmentIDRightPane = WebUI.getText(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/sattion_ID"))
	 WebUI.verifyEqual(EquipmentIDRightPane, actions.pickedEquipmentID)
	 }*/

	/**
	 * Method to verify no offers found message for wrong search criteria
	 * @param noOffersFounndMessage
	 */
	def wrongSearchResultsFound(){
		/**
		 * This delay is needed, otherwise Katalon won't catch below object
		 */
		WebUI.delay(5)
		String noOffersFounndMessage = WebUI.getText(findTestObject("Object Repository/F12441 - Rule15/Page_Transportation Management Syst/div_No Rule15 Offerings found"))
		WebUI.verifyEqual(noOffersFounndMessage, Rule15OfferingsPageConstants.NO_OFFERS_FOUND_MESSAGE)
	}


	/**
	 *  Verify if the filtered record is displayed
	 */
	@Keyword
	def verifyFilteredResults(){
		String filteredOfferingType = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringtype_UI'))
		boolean verifyOfferingType = actions.selectedOfferingType.equalsIgnoreCase(filteredOfferingType)
		WebUI.verifyEqual(verifyOfferingType, true)
		List<WebElement> listOfElements = driver.findElements(By.xpath(Rule15OfferingsPageConstants.LEFTPANE_OFFERING_TYPE))
		def sizeOfFilteredOfferinge = listOfElements.size()
		for(int i=0;i<sizeOfFilteredOfferinge;i++){
			WebElement element = listOfElements.get(i)
			String offreingTypeText = element.getText()
			boolean verifyOfferingTypeLeftPane = actions.selectedOfferingType.equalsIgnoreCase(offreingTypeText)
			WebUI.verifyEqual(verifyOfferingTypeLeftPane, true)
		}
	}

	/**
	 *  Verify no filter is applied after cancel is clicked
	 */
	@Keyword
	def verifyFilterChipExistence(){
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/Rule15 Offerings/filter_chip'), 5)
	}

	/**
	 * Verify the the rule 15 confirmation message has the Train id and Railroad
	 * before Sending it
	 * @param trainId passed
	 * @param railroad selected Passed
	 */
	@Keyword
	def verifyRule15ConfirmationMessage(String trainId , String railroadSelected){
		String actualTrainIdInMsg = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/rule15ConfirmationMessage'))
		actualTrainIdInMsg.contains(trainId)
		actualTrainIdInMsg.contains(railroadSelected)
	}

	/**
	 * Verify Error messages for railroad
	 */
	def verifyErrorMessagesForRailroad(){
		String railroadError = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/railroadDeselectErrorMessageSpan'))
		WebUI.verifyEqual(railroadError, Rule15OfferingsPageConstants.ERROR_MESSAGE_RAILROAD)
	}

	/**
	 * Verify Error messages for all the mandatory fields
	 */
	def verifyErrorMessagesForMandatoryFields(){

		/**
		 *  Verify Station error message
		 */
		actions.viewErrorMessageOfStation()
		String stationError = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/stationDeselectErrorMessageSpan'))
		WebUI.verifyEqual(stationError, Rule15OfferingsPageConstants.ERROR_MESSAGE_TO_STATION)
		WebUI.delay(2)

		//Verify train id error message
		actions.trainIdEmptyError()
		String trainIdError = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/train_error_message'))
		WebUI.verifyEqual(trainIdError, Rule15OfferingsPageConstants.TRAIN_ID_EMPTY_ERROR_MESSAGE)

		/**
		 *  Verify error First name offering road
		 */
		actions.offeringRoadFirstNameError('number')
		String offeringFirstNameErrorNumeric= WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringroad_firstname_error_message'))
		WebUI.verifyEqual(offeringFirstNameErrorNumeric, CommonPageConstants.FIELD_ALPHABETIC_ERROR_MESSAGE)
		actions.offeringRoadFirstNameError('alphabets')
		String offerFirstNameErrorEmpty = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringroad_firstname_error_message'))
		WebUI.verifyEqual(offerFirstNameErrorEmpty, Rule15OfferingsPageConstants.FIRST_NAME_EMPTY_ERROR_MESSAGE)

		/**
		 *  Verify error Last name offering road
		 */
		actions.offeringRoadLastNameError('number')
		String offeringLastNameErrorNumeric= WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringroad_lastname_error_message'))
		WebUI.verifyEqual(offeringLastNameErrorNumeric, CommonPageConstants.FIELD_ALPHABETIC_ERROR_MESSAGE)
		actions.offeringRoadLastNameError('alphabets')
		String offerLastNameErrorEmpty = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringroad_lastname_error_message'))
		WebUI.verifyEqual(offerLastNameErrorEmpty, Rule15OfferingsPageConstants.LAST_NAME_EMPTY_ERROR_MESSAGE)

		/**
		 *  Verify error Phone number offering road
		 */
		actions.offeringRoadPhoneNumberError('number')
		String offeringPhoneErrorEmpty= WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringroad_phone_error_message'))
		WebUI.verifyEqual(offeringPhoneErrorEmpty, Rule15OfferingsPageConstants.PHONE_NUMBER_EMPTY_ERROR_MESSAGE)
		actions.offeringRoadPhoneNumberError('alphabets')
		String offerPhoneNumberErrorAlphabetic = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/offeringroad_phone_error_message'))
		WebUI.verifyEqual(offerPhoneNumberErrorAlphabetic, CommonPageConstants.FIELD_NUMERIC_ERROR_MESSAGE)

		/**
		 *  Verify error First name receiving road
		 */
		actions.receivingRoadFirstNameError('number')
		String receivingFirstNameErrorNumeric= WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/receivingroad_firstname_error_message'))
		WebUI.verifyEqual(receivingFirstNameErrorNumeric, CommonPageConstants.FIELD_ALPHABETIC_ERROR_MESSAGE)
		actions.receivingRoadFirstNameError('alphabets')
		String receivingFirstNameErrorEmpty = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/receivingroad_firstname_error_message'))
		WebUI.verifyEqual(receivingFirstNameErrorEmpty, Rule15OfferingsPageConstants.FIRST_NAME_EMPTY_ERROR_MESSAGE)

		/**
		 *  Verify error Last name receiving road
		 */
		actions.receivingRoadLastNameError('number')
		String receivingLastNameErrorNumeric= WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/receivingroad_lastname_error_message'))
		WebUI.verifyEqual(receivingLastNameErrorNumeric, CommonPageConstants.FIELD_ALPHABETIC_ERROR_MESSAGE)
		actions.receivingRoadLastNameError('alphabets')
		String receivingLastNameErrorEmpty = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/receivingroad_lastname_error_message'))
		WebUI.verifyEqual(receivingLastNameErrorEmpty, Rule15OfferingsPageConstants.LAST_NAME_EMPTY_ERROR_MESSAGE)

		/**
		 *  Verify error Phone number receiving road
		 */
		actions.receivingRoadPhoneNumberError('number')
		String receivingPhoneErrorEmpty= WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/receivingroad_phone_error_message'))
		WebUI.verifyEqual(receivingPhoneErrorEmpty, Rule15OfferingsPageConstants.PHONE_NUMBER_EMPTY_ERROR_MESSAGE)
		actions.receivingRoadPhoneNumberError('alphabets')
		String receivingPhoneNumberErrorAlphabetic = WebUI.getText(findTestObject('Object Repository/Rule15 Offerings/receivingroad_phone_error_message'))
		WebUI.verifyEqual(receivingPhoneNumberErrorAlphabetic, CommonPageConstants.FIELD_NUMERIC_ERROR_MESSAGE)

		/**
		 *  Verify Next button
		 */
		WebUI.verifyElementClickable(findTestObject('Object Repository/Rule15 Offerings/createOfferingNextButton'))
	}

	/**
	 * Method to verify Equipment Ids of the add cars and resend
	 */
	def verifyAddCarsAndResendEquipmentIDs(){
		List<String> equipmentIDsOfRule15View = new ArrayList()
		int numberOfRows = actions.equipmentTableSize(Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE)
		for(int i=1;i<=numberOfRows;i++) {
			String equipmentID = driver.findElement(By.xpath(Rule15OfferingsPageConstants.EQUIPMENT_ID_LIST_TABLE+'['+i+']/div/div[1]')).getText().trim()
			if(equipmentID.isEmpty())
				break
			else
				equipmentIDsOfRule15View.add(equipmentID)
		}
		commonUtil.equalLists(equipmentIDsOfRule15View,actions.addCarsAndResendEquipmentIDsList)
	}
	/**
	 * Method to verify the resend has updated the values
	 * @param expectedTrainId
	 * @param expectedAuthNumber
	 */
	@Keyword
	def verifyResendOfferOfRule15(String expectedTrainId , String expectedAuthNumber){
		String actualTrainId = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/selectedTrainId'))
		WebUI.verifyEqual(actualTrainId, expectedTrainId)

		String actualAuthNumber = WebUI.getText(findTestObject('Object Repository/F13859-View Rule15/QA-US101672/US93325-Display Rule 15 offering screen/view rule 15/authNumberRightPane'))
		WebUI.verifyEqual(actualAuthNumber, expectedAuthNumber)
	}
}

