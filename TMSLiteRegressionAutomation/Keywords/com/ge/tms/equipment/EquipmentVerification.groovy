package com.ge.tms.equipment


import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.ge.tms.util.CommonUtilities
import com.ge.tms.constants.EquipmentPageConstants
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import groovy.json.JsonSlurper
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

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ge.tms.commonactions.EquipmentActions

/**
 * This class hold  all the verification methods related to Equipment
 */
public class EquipmentVerification {

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities commonUtil = new CommonUtilities()

	/**
	 *  Action class instance
	 */
	EquipmentActions actions = new EquipmentActions()

	/**
	 * Create a instance from web driver
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * Variable to hold xpaths of EquipmentID,Type and Kind
	 */
	public String sortByElementsPath

	/**
	 * send request for get all Equipments details
	 */
	ResponseObject getPirticularEquipmentIdObj = WS.sendRequest(findTestObject('Object Repository/EquipmentServices/GetParticularEquipment',[('equipmentId') : actions.randomEquipmentID]))

	/**
	 * Method to get count from left pane of equipment view
	 * @return leftPaneElementCount
	 */
	def int getLeftPaneCountforEquipments(){
		List<WebElement> leftPaneElementCount = driver.findElements(By.xpath(EquipmentPageConstants.LEFT_PANE_ELEMENT_COUNT))
		return leftPaneElementCount.size()
	}

	/**
	 * Method to verify Equipments are sorted by type and in descending order
	 */
	@Keyword
	def String verifyEquipmentSorted(String sortBy) {
		List<String> equipmentIDsInLeftPane = new ArrayList<String>();
		for (int i = 1; i <= getLeftPaneCountforEquipments() - 1; i++) {
			if (sortBy.equals(EquipmentPageConstants.SORT_BY_OPTION_TYPE)) {
				sortByElementsPath = '//*[@class="Selectable-module_scrollable_1EvSP"]/div[' + i + ']/div[2]/div[1]'
			} else if (sortBy.equals(EquipmentPageConstants.SORT_BY_OPTION_EQUIPMENT_ID)) {
				sortByElementsPath = '//*[@class="Selectable-module_scrollable_1EvSP"]/div[' + i + ']/div[1]/div[1]/span'
			} else if (sortBy.equals(EquipmentPageConstants.SORT_BY_OPTION_KIND)) {
				sortByElementsPath = '//*[@class="Selectable-module_scrollable_1EvSP"]/div[' + i + ']/div[2]/div[2]'
			}
			WebElement leftPaneElementsBeforeSorting = driver.findElement(By.xpath(sortByElementsPath))
			String equipmentDetails = leftPaneElementsBeforeSorting.getText()
			equipmentIDsInLeftPane.add(equipmentDetails)
		}
		commonUtil.sortOrder(equipmentIDsInLeftPane)
	}


	/**
	 * Method to get size of left pane of equipment
	 */
	def  equipmentIdToBeClicked(){
		int listsize= getLeftPaneCountforEquipments()
		if(listsize>0){
			String equipmentTobeClicked = ".//div[@data-testid='equipment-list']/div[1]"
			driver.findElement(By.xpath(equipmentTobeClicked)).click()
		}
	}

	/**
	 * Method to get all the stations for a specify Railroad mark
	 * @return response of the api call
	 */
	def getAllEquipments(){
		ResponseObject getAllEquipments = WS.sendRequest(findTestObject('Object Repository/EquipmentServices/GetAllEquipments'))
		String resText = getAllEquipments.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		ArrayList<String> response  = slupper.parseText(resText)
		return response
	}

	/**
	 * send request for get a particular Equipment detail
	 * @return response to get a particular equipment details
	 */
	def getParticularEquipment(){
		ResponseObject getAllEquipmentIdObj = WS.sendRequest(findTestObject('Object Repository/EquipmentServices/GetParticularEquipment',[('equipmentId') : (actions.equipmentId).toUpperCase()]))
		String resText = getAllEquipmentIdObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		ArrayList<String> response  = slupper.parseText(resText)
		return response
	}

	/**
	 * Verify Fields displayed in UI and API are equal
	 *  For Locomotive and Railcar
	 *  @param functionality variable to pick one of the responses
	 */
	@Keyword
	def verifyEquipmentDataInView(String functionality){
		ArrayList<String> responseOfEquipment ;

		if(functionality.equals('view')){
			responseOfEquipment = getAllEquipments()
		}
		else if(functionality.equals('edit')){
			responseOfEquipment  = getParticularEquipment()
		}

		/**
		 *  Common Fields verification for Locomotive or Railcar type
		 *  Fields :- equipment id, carKind, aarcar type, build date, ownership code, reflectorization, poolnumber
		 */
		String equipmentText = actions.getTextEquipmentId().replace(" ", "");
		WebUI.verifyEqual(equipmentText, responseOfEquipment[0].equipmentInitial+responseOfEquipment[0].equipmentNumber)

		WebUI.verifyEqual(actions.getTextCarKind().substring(0, 1),responseOfEquipment[0].carKindType)

		WebUI.verifyEqual(actions.getTextAarCarType(),responseOfEquipment[0].aarCarType)

		/**
		 * Conversion of date for build type
		 * this was commented due to TIME ZONE ISSUE, 
		 * def buildDate = responseOfEquipment[0].builtDate
		 * WebUI.verifyEqual(actions.getTextBuildDate(),commonUtil.convertStringToDate(buildDate))
		 */

		def tempOwnershipCode = (responseOfEquipment[0].ownershipCode == null) ?'--' : responseOfEquipment[0].ownershipCode;
		WebUI.verifyEqual(actions.getTextOwnerShipCode().substring(0, 1),tempOwnershipCode)

		def tempReflectorization = (responseOfEquipment[0].reflectorization == null) ?'--' : responseOfEquipment[0].reflectorization;
		WebUI.verifyEqual(actions.getTextRefelctorization().substring(0, 1),tempReflectorization)

		def tempPoolNumber = (responseOfEquipment[0].poolNumber == null) ?'--' : responseOfEquipment[0].poolNumber;
		WebUI.verifyEqual(actions.getTextPoolNumber(),tempPoolNumber)

		/**
		 *  Fields occur when equipment type is Locomotive
		 */
		if(actions.getTextEquipmentType().equals('Locomotive')){


			def tempOwnerMark = (responseOfEquipment[0].ownerMark == null) ?'--' : responseOfEquipment[0].ownerMark;
			WebUI.verifyEqual(actions.ownerMark,tempOwnerMark)

			def tempLesseOwner = (responseOfEquipment[0].lesseeOwner == null) ?'--' : responseOfEquipment[0].lesseeOwner;
			WebUI.verifyEqual(actions.leeseOwner,tempLesseOwner)

			def tempTransportationCode = (responseOfEquipment[0].transportationCode == null) ?'--' : responseOfEquipment[0].transportationCode;
			WebUI.verifyEqual(actions.transportationCode,tempTransportationCode)

			def tempConditionCode= (responseOfEquipment[0].transConditionCode == null) ?'--' : responseOfEquipment[0].transConditionCode;
			WebUI.verifyEqual(actions.conditionCode,tempConditionCode)

			def tempRateCode= (responseOfEquipment[0].rateIndicator == null) ?'--' : responseOfEquipment[0].rateIndicator;
			WebUI.verifyEqual(actions.rateCode,tempRateCode)

			def tempTimeRate = (responseOfEquipment[0].timeRate == null) ?'--' : responseOfEquipment[0].timeRate;
			WebUI.verifyEqual(actions.timeRate,tempTimeRate)

			def tempMilageRate = (responseOfEquipment[0].mileageRate == null) ?'--' : responseOfEquipment[0].mileageRate;
			WebUI.verifyEqual(actions.mileageRate,tempMilageRate)

			def tempOutsideLength = (responseOfEquipment[0].outsideLength == null) ?'--' : responseOfEquipment[0].outsideLength;
			WebUI.verifyEqual(actions.getTextOutsideLength(),tempOutsideLength)

			def tempOutsidewidth = (responseOfEquipment[0].outsideExtremeWidth == null) ?'--' : responseOfEquipment[0].outsideExtremeWidth;
			WebUI.verifyEqual(actions.getTextOutsideWidth(),tempOutsidewidth)

			def tempOutsideHeight = (responseOfEquipment[0].outsideHeight == null) ?'--' : responseOfEquipment[0].outsideHeight
			WebUI.verifyEqual(actions.getTextOutsideHeight(),tempOutsideHeight)

			def tempBuilderLotCode = (responseOfEquipment[0].builderLotCode == null) ? "--" : responseOfEquipment[0].builderLotCode;
			WebUI.verifyEqual(actions.getTextBuilder(),tempBuilderLotCode)

			def tempHoodType = (responseOfEquipment[0].hoodType == null) ? "--" : responseOfEquipment[0].hoodType;
			WebUI.verifyEqual(actions.getTextHoodType(),tempHoodType)

			def tempclearanceCode = (responseOfEquipment[0].clearanceCode == null) ? "--" : responseOfEquipment[0].clearanceCode;
			WebUI.verifyEqual(actions.getTextClearence(),tempclearanceCode)

			def tempWeightOnDrivers = (responseOfEquipment[0].weightOnDrivers == null) ? "--" : responseOfEquipment[0].weightOnDrivers;
			WebUI.verifyEqual(actions.getTextWeightOnDrivers(),tempWeightOnDrivers)

			def tempMinimumSpeed = (responseOfEquipment[0].minimumSpeed == null) ? "--" : responseOfEquipment[0].minimumSpeed;
			WebUI.verifyEqual(actions.getTextCapacitySpeedMinSpeed(),tempMinimumSpeed)

			def tempMaximumSpeed = (responseOfEquipment[0].maximumSpeed == null) ? "--" : responseOfEquipment[0].maximumSpeed;
			WebUI.verifyEqual(actions.getTextCapacitySpeedMaxSpeed(),tempMaximumSpeed)

			def tempAirBrake = (responseOfEquipment[0].airBrake == null) ? "--" : responseOfEquipment[0].airBrake;
			WebUI.verifyEqual(actions.getTextAirBrake(),tempAirBrake)

			def tempGearRatio = (responseOfEquipment[0].gearRatio == null) ? "--" : responseOfEquipment[0].gearRatio;
			WebUI.verifyEqual(actions.getTextGearRatio(),tempGearRatio)

			def tempFuelCapacity = (responseOfEquipment[0].fuelCapacity == null) ? "--" : responseOfEquipment[0].fuelCapacity;
			WebUI.verifyEqual(actions.getTextFuelCapacity(),tempFuelCapacity)

			def tempbearingOrBreak = (responseOfEquipment[0].bearingOrBreak == null) ? "--" : responseOfEquipment[0].bearingOrBreak;
			WebUI.verifyEqual(actions.getTextBearingBreaks().substring(0, 1),tempbearingOrBreak)

			def tempOperatingBreak = (responseOfEquipment[0].operatingBrakes == null) ? "--" : responseOfEquipment[0].operatingBrakes;
			WebUI.verifyEqual(actions.getTextOperatingBreaks(),tempOperatingBreak)
		}

		/**
		 *  Fields occur when equipment type id railcar
		 */
		else if(actions.getTextEquipmentType().equals('Railcar')){

			def tempInsideLength = (responseOfEquipment[0].insideLength == null) ? "--" : responseOfEquipment[0].insideLength;
			WebUI.verifyEqual(actions.getTextInsideLength(),tempInsideLength)

			def tempInsideWidth = (responseOfEquipment[0].insideWidth == null) ? "--" : responseOfEquipment[0].insideWidth;
			WebUI.verifyEqual(actions.getTextInsideWidth(),tempInsideWidth)

			def tempRailInsideHeight = (responseOfEquipment[0].insideHeight == null) ? "--" : responseOfEquipment[0].insideHeight;
			WebUI.verifyEqual(actions.getTextInsideHeight(),tempRailInsideHeight)

			def tempRailClearanceCode = (responseOfEquipment[0].clearanceCode == null) ? "--" : responseOfEquipment[0].clearanceCode;
			WebUI.verifyEqual(actions.getTextRailClearance(),tempRailClearanceCode)

			def tempGrade = (responseOfEquipment[0].grade == null) ? "--" : responseOfEquipment[0].grade;
			WebUI.verifyEqual(actions.getTextGrade(),tempGrade)

			def tempTotalWeightIncludingLoadLimits = (responseOfEquipment[0].totalWeightIncludingLoadLimits == null) ? "--" : responseOfEquipment[0].totalWeightIncludingLoadLimits;
			WebUI.verifyEqual(actions.getTextLoadLimit(),tempTotalWeightIncludingLoadLimits)

			def tempAirBrakeTest = (responseOfEquipment[0].airBrakeTest == null) ? "--" : responseOfEquipment[0].airBrakeTest;
			WebUI.verifyEqual(actions.getTextRailAirBrake(),tempAirBrakeTest)

			def tempEarlyWarningIndicator = (responseOfEquipment[0].earlyWarningIndicator == null) ? "--" : responseOfEquipment[0].earlyWarningIndicator;
			WebUI.verifyEqual(actions.getTextWarningIndicator(),tempEarlyWarningIndicator)

			def tempTolerance = (responseOfEquipment[0].totalWeightIncludingTolerance == null) ? "--" : responseOfEquipment[0].totalWeightIncludingTolerance;
			WebUI.verifyEqual(actions.tolerance,tempTolerance)

			def tempRailBearingOrBreak = (responseOfEquipment[0].bearingOrBreak == null) ? "--" : responseOfEquipment[0].bearingOrBreak;
			WebUI.verifyEqual(actions.getTextRailcarBearingBrake().substring(0, 1),tempRailBearingOrBreak)

			def tempRailTruckType = (responseOfEquipment[0].truckType == null) ? "--" : responseOfEquipment[0].truckType;
			WebUI.verifyEqual(actions.getTextRailcarTruckType(),tempRailTruckType)

			def tempRailTruckCentre = (responseOfEquipment[0].truckCenter == null) ? "--" : responseOfEquipment[0].truckCenter;
			WebUI.verifyEqual(actions.getTextTruckCenter(),tempRailTruckCenter)

			def tempRailSideDoorType = (responseOfEquipment[0].sideDoorType == null) ? "--" : responseOfEquipment[0].sideDoorType;
			WebUI.verifyEqual(actions.getTextRailcarSideDoor(),tempRailSideDoorType)
		}
	}

	/**
	 * Method to search created equipment id
	 * @param equipmentIdText
	 */
	def verifyNewEquipmentCreatedIsSearchable(String equipmentIdText){
		def actualEquipmentId = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/equipmentIdInView'))
		WebUI.verifyEqual(actualEquipmentId, equipmentIdText.replaceAll("(?<=\\D)(?=\\d)"," "))
	}

	/**
	 * Method to verify Equipment Id
	 * @param expectedEquipmentId
	 */
	@Keyword
	def verifyEquipmentID(String expectedEquipmentId){
		String  carIdInRightPane = WebUI.getText(findTestObject('Object Repository/Manage Equipments/carIDRightPane')).replace(" ", "")
		WebUI.verifyEqual(carIdInRightPane, expectedEquipmentId.replace(" ",""))
	}

	/**
	 * Method to verify No Equipment Message while input a invalid Equipment
	 * @param msg
	 * @param equipmentIdInRightPane
	 */
	@Keyword
	def verifyNoequipmentMessage(String noEquipmentMessage){
		String  equipmentIdInRightPane = WebUI.getText(findTestObject('Object Repository/Manage Equipments/noEquipmentMessage'))
		WebUI.verifyEqual(equipmentIdInRightPane, noEquipmentMessage)
	}

	/**
	 * Method to Verify Equipment from API
	 * @param carIdInRightPane
	 * @param carKindRightPane
	 * @param aarCarTypeRightPane
	 * @param aarMerchDesignation
	 * @param eId
	 * @param carType
	 * @param carKind
	 * @param aarMerchDesig
	 */
	@Keyword
	def verifyEquipmentRecord(String equipID){
		String  carIdInRightPane = WebUI.getText(findTestObject('Object Repository/Manage Equipments/noEquipmentMessage'))
		String carKindRightPane = WebUI.getText(findTestObject('Object Repository/Manage Equipments/rightPaneCarKind')).charAt(0)
		String aarCarTypeRightPane = WebUI.getText(findTestObject('Object Repository/Manage Equipments/rightPaneARRCarType'))
		String aarMerchDesignation = WebUI.getText(findTestObject('Object Repository/Manage Equipments/rightPaneARRMechDesig'))
		def RecordfromAPI = getSelectedEquipmentAPI(equipID)
		String eId = RecordfromAPI.equipmentId.toString()
		String carType = RecordfromAPI.aarCarType.toString()
		String carKind = RecordfromAPI.carKindType.toString()
		def aarMerchDesig = (RecordfromAPI.aarMechanicalDesignation == null) ? "--" : RecordfromAPI.aarMechanicalDesignation;
		WebUI.verifyEqual(eId, equipID)
		WebUI.verifyEqual(carType, aarCarTypeRightPane)
		WebUI.verifyEqual(carKind, carKindRightPane)
		WebUI.verifyEqual(aarMerchDesig, aarMerchDesignation)
	}

	/**
	 * Method to get the selected EquipmentId from API
	 * @param EquipmentId
	 * @return selected EquipmentId Data
	 */
	@Keyword
	def getSelectedEquipmentAPI(String equipmentID){
		String resText = getPirticularEquipmentIdObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfEquipments = response.size();
		for(int i=0;i<noOfEquipments;i++){
			String eId = response[0].equipmentId.toString()
			if(equipmentID.equals(eId)){
				return response[i];
			}
		}
	}

	/**
	 * Method to verify clear button functions correctly
	 */
	@Keyword
	def verifyClearButtonClearTheFilters(){
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/selected filter box'), 3)
	}

	/**
	 * Method to verify The filter works correctly in equipment search
	 * @param emptyListMessageInLeftPane
	 * @param carkindtypeInLeftPane
	 * @param poolNumberInRightPane
	 * @param aarCarTypeInRightPane
	 */
	@Keyword
	def String verifyEquipmentFilterFunctionality(String numberOfFilters){

		/**
		 * 		 If the selected scenario in filter is not in the data list, this method uses to verify empty equipment message
		 */
		if(getLeftPaneCountforEquipments() == 0){
			String emptyListMessageInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/span_No equipments available'))
			WebUI.verifyEqual(emptyListMessageInLeftPane, EquipmentPageConstants.NO_EQUIPMENT_FOUND)
		}

		/**
		 * In this test case, tester test the filter by selecting only the car kind option in the menu. verifying results is done in this method
		 */
		else if(numberOfFilters.equals(EquipmentPageConstants.CAR_KIND)){
			String carKindtypeInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/div_C'))
			WebUI.verifyEqual(carKindtypeInLeftPane, actions.selectedCarKind)
		}

		/**
		 * 		in this test case tester uses both AAR car type and pool number as filter criteria. The results verify when both of the fields returned their results
		 */
		else if(numberOfFilters.equals(EquipmentPageConstants.POOL_NUMBER_AND_AAR_CAR_TYPE)){
			if(getLeftPaneCountforEquipments() == 0){
				String emptyListMessageInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/span_No equipments available'))
				WebUI.verifyEqual(emptyListMessageInLeftPane, EquipmentPageConstants.NO_EQUIPMENT_FOUND)
			}
			else{
				String  poolNumberInRightPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/Pool_Number'))
				String  aarCarTypeInRightPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/AARCarType'))
				WebUI.verifyEqual(aarCarTypeInRightPane, actions.selectedAARCarType)
				WebUI.verifyEqual(poolNumberInRightPane, EquipmentPageConstants.POOL_NUMBER)
			}
		}

		/**
		 * 		in this test case tester uses both car kind and pool number as filter criteria. The results verify when both of the fields returned their results
		 */
		else if(numberOfFilters.equals(EquipmentPageConstants.POOL_NUMBER_AND_CAR_KIND)){
			if(getLeftPaneCountforEquipments() == 0){
				String emptyListMessageInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/span_No equipments available'))
				WebUI.verifyEqual(emptyListMessageInLeftPane, EquipmentPageConstants.NO_EQUIPMENT_FOUND)
			}
			else{
				String  poolNumberInRightPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/Pool_Number'))
				String carKindtypeInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/div_C'))
				WebUI.verifyEqual(carKindtypeInLeftPane, actions.selectedCarKind)
				WebUI.verifyEqual(poolNumberInRightPane, EquipmentPageConstants.POOL_NUMBER)
			}
		}

		/**
		 * 		in this test case tester uses both car kind and AAR CAr type as filter criteria. The results verify when both of the fields returned their results
		 */
		else if(numberOfFilters.equals(EquipmentPageConstants.AAR_CAR_TYPE_AND_CAR_KIND)){
			if(getLeftPaneCountforEquipments() == 0){
				String emptyListMessageInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/span_No equipments available'))
				WebUI.verifyEqual(emptyListMessageInLeftPane, EquipmentPageConstants.NO_EQUIPMENT_FOUND)
			}
			else{
				String  aarCarTypeInRightPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/AARCarType'))
				String carKindtypeInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/div_C'))
				WebUI.verifyEqual(carKindtypeInLeftPane, actions.selectedCarKind)
				WebUI.verifyEqual(aarCarTypeInRightPane, actions.selectedAARCarType)
			}
		}
		/**
		 * 		in this test case tester uses all pool number, car kind and AAR CAr type as filter criteria. The results verify when both of the fields returned their results
		 */
		else if(numberOfFilters.equals(EquipmentPageConstants.AAR_CAR_TYPE_AND_CAR_KIND)){
			if(getLeftPaneCountforEquipments() == 0){
				String emptyListMessageInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/span_No equipments available'))
				WebUI.verifyEqual(emptyListMessageInLeftPane, EquipmentPageConstants.NO_EQUIPMENT_FOUND)
			}
			else{
				String  poolNumberInRightPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/Pool_Number'))
				String  aarCarTypeInRightPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/AARCarType'))
				String carKindtypeInLeftPane = WebUI.getText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/div_C'))
				WebUI.verifyEqual(carKindtypeInLeftPane, actions.selectedCarKind)
				WebUI.verifyEqual(aarCarTypeInRightPane, actions.selectedAARCarType)
				WebUI.verifyEqual(poolNumberInRightPane, EquipmentPageConstants.POOL_NUMBER)
			}
		}
	}

	/**
	 * Method to verify Invalid Equipment ID in Add New Equipment
	 * @param emptyEquipmentValidationIdMessage
	 */
	@Keyword
	def verifyEquipmentIdEmptyAndInvalidMsg(){
		WebUI.click(findTestObject('Object Repository/F7174 - Manage Equipment/aarMechnanicalTextField'))
		WebUI.delay(2)
		String emptyEquipmentValidationIdMessage = WebUI.getText(findTestObject('Object Repository/Manage Equipments/equipmentErrorMsgAddEqp'))
		if(emptyEquipmentValidationIdMessage == EquipmentPageConstants.EQUIPMENT_EMPTY_MSG){
			WebUI.verifyEqual(EquipmentPageConstants.EQUIPMENT_EMPTY_MSG, emptyEquipmentValidationIdMessage)
		}
		else{
			WebUI.verifyEqual(EquipmentPageConstants.VALID_EQUIPMENT_MSG, emptyEquipmentValidationIdMessage)
		}
	}

	/**
	 * Method to verify Empty Car Kind and Empty AAR Car Type validation message
	 * @param validationMessage
	 */
	@Keyword
	def verifyEmptyCarKindMsg(String validationMessage){
		if (validationMessage.equals(EquipmentPageConstants.EMPTY_CAR_KIND_MSG)) {
			WebUI.verifyEqual(EquipmentPageConstants.EMPTY_CAR_KIND_MSG, validationMessage)
		}
		else if (validationMessage.equals(EquipmentPageConstants.EMPTY_AAR_TYPE_MSG)) {
			WebUI.verifyEqual(EquipmentPageConstants.EMPTY_AAR_TYPE_MSG, validationMessage)
		}
		else if(validationMessage.equals(EquipmentPageConstants.EMPTY_EQUIOPMENT_TYPE_MSG)) {
			WebUI.verifyEqual(EquipmentPageConstants.EMPTY_EQUIOPMENT_TYPE_MSG, validationMessage)
		}
	}

	/**
	 * Method to verify Car Kinds for the selected Equipment
	 * @param equipmentType
	 */
	@Keyword
	def verifyCarKindList(String equipmentType){
		if(equipmentType.equals(EquipmentPageConstants.EQUIPMENT_TYPE_RAILCAR)) {
			def listofCarKind = WebUI.executeJavaScript(EquipmentPageConstants.CARKIND_LIST,null)
			commonUtil.equalLists(listofCarKind, actions.getSelectedCarKind(equipmentType))
		}
		else if(equipmentType.equals(EquipmentPageConstants.EQUIPMENT_TYPE_LOCOMOTIVE)) {
			def listofCarKind = WebUI.executeJavaScript(EquipmentPageConstants.CARKIND_LIST,null)
			commonUtil.equalLists(listofCarKind, actions.getSelectedCarKind(equipmentType))
		}
	}

	/**
	 * Method to verify AAR Car Type for the selected Car Kind
	 */
	@Keyword
	def verifyAarCarTypeList(String carKind){
		List<String> aarCarTypeListAPI = new ArrayList()
		List<String> aarCarTypeList = new ArrayList()
		aarCarTypeList= WebUI.executeJavaScript(EquipmentPageConstants.AARCAR_TYPE_LIST,null)
		aarCarTypeListAPI=actions.getSelectedAarCarType(carKind)
		commonUtil.equalLists(aarCarTypeListAPI, aarCarTypeList)
	}

	/**
	 * Method to verify Save Button is enabled
	 */
	@Keyword
	def verifySaveButtonEnabled(String text){
		WebUI.verifyElementClickable(findTestObject('Object Repository/F7174 - Manage Equipment/saveEquipmentButton'))
	}
}
