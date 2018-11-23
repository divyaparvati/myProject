package com.ge.tms.commonactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.EquipmentPageConstants
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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import java.text.ParseException;
import java.text.SimpleDateFormat;

import internal.GlobalVariable
import groovy.json.JsonSlurper

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List
import java.util.*

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement


/**
 * This class was implemented to hold all the methods related to equipment Actions to keep
 */
public class EquipmentActions {

	/**
	 * Object to hold randomCarID
	 */
	public static String randomEquipmentID

	/**
	 * Static variable to hold the equipment id
	 */
	public static String equipmentId

	/**
	 * Static variable to hold the owner mark
	 */
	public static String ownerMark

	/**
	 * Static variable to hold the leesse owner
	 */
	public static String leeseOwner

	/**
	 * Static variable to hold the rate code
	 */
	public static String rateCode

	/**
	 * Static variable to hold the time Rate
	 */
	public static int timeRate

	/**
	 * Static variable to hold the mileage Rate
	 */
	public static int mileageRate

	/**
	 * Static variable to hold the tolerance
	 */
	public static int tolerance

	/**
	 * Static variable to hold the transportation code
	 */
	public static String transportationCode

	/**
	 * Static variable to hold the condition code
	 */
	public static String conditionCode

	/**
	 * Static variable to hold the equipment type
	 */
	public static String equipmentTypeSelected

	/**
	 * Static variable to hold the exteremWidth
	 */
	public static String exteremWidth

	/**
	 * Create a instance from web driver
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities utils= new CommonUtilities()

	/**
	 * Variable to hold selected AAR car type
	 */
	public static String selectedAARCarType

	/**
	 * Variable to hold selected AAR car type
	 */
	public static String selectedCarKind

	/**
	 * send request for get all AAR Car Type details
	 */
	ResponseObject getAllAarCarTypeObj = WS.sendRequest(findTestObject('Object Repository/EquipmentServices/GetAllAarCarType'))

	/**
	 * Method to select Equipment management from system config menu
	 */
	def clickOnEquipmentManagement(){
		WebUI.executeJavaScript(EquipmentPageConstants.EQUIPMENT_MANAGE_MENU, null)
		WebUI.delay(2)
	}

	/**
	 * Method to select Type from the sort by menu and select Descending option
	 */
	def sortByTypeDescendingAction(){

		/**
		 * Select Type from Sort by menu
		 */
		WebUI.executeJavaScript(EquipmentPageConstants.SORT_BY_TYPE_MENU, null)
		WebUI.delay(2)

		/**
		 * Click on descending icon
		 */
		WebUI.executeJavaScript(EquipmentPageConstants.SORT_ORDER_ICON, null)
		WebUI.delay(2)
	}

	/**
	 * Method to select Equipment ID from the sort by menu and select Ascending option
	 */
	def sortByEquipmentIDAscendingAction(){

		/**
		 * Select EquipmentID from Sort by menu
		 */
		WebUI.executeJavaScript(EquipmentPageConstants.SORT_BY_EQUIPMENT_ID_MENU, null)
		WebUI.delay(2)

		/**
		 * Click on Ascending icon
		 */
		WebUI.executeJavaScript(EquipmentPageConstants.SORT_ORDER_ICON, null)
		WebUI.delay(2)
	}

	/**
	 * Method to select Kind from the sort by menu and select Descending option
	 */
	def sortByKindDescendingAction(){

		/**
		 * Select Kind from Sort by menu
		 */
		WebUI.executeJavaScript(EquipmentPageConstants.SORT_BY_KIND_MENU, null)
		WebUI.delay(2)

		/**
		 * Click on Descending icon
		 */
		WebUI.executeJavaScript(EquipmentPageConstants.SORT_ORDER_ICON, null)
		WebUI.delay(2)
	}

	/**
	 * Get text of Equipment ID
	 * @return equipment id
	 */
	def String getTextEquipmentId(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/equipment_id'))
	}

	/**
	 * Get text of Equipment Type
	 * @return equipment type
	 */
	def String getTextEquipmentType(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/equipment_type'))
	}

	/**
	 * Get text of Equipment kind
	 * @return equipment kind
	 */
	def String getTextCarKind(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/equipment_carkind')).charAt(0)
	}

	/**
	 * Get text of Equipment AarCar type
	 * @return equipment aarCarKind
	 */
	def String getTextAarCarType(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/equipment_aarcar_type'))
	}

	/**
	 * Get text of Equipment Ownership code
	 * @return equipment ownership code
	 */
	def String getTextOwnerShipCode(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/ownership_code')).charAt(0)
	}

	/**
	 * Get text of Equipment Refelctorization
	 * @return equipment refelectorization
	 */
	def String getTextRefelctorization(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/reflectorization')).charAt(0)
	}

	/**
	 * Get text of Equipment Pool Number
	 * @return equipment pool number
	 */
	def String getTextPoolNumber(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/poolnumber'))
	}

	/**
	 * Get text of Equipment Builder
	 * @return equipment builder
	 */
	def String getTextBuilder(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/Builder'))
	}

	/**
	 * Get text of Equipment hoodtype
	 * @return equipment hoodtype
	 */
	def String getTextHoodType(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/hoodtype'))
	}

	/**
	 * Get text of Equipment Dimension details outside length
	 * @return equipment Outside length
	 */
	def String getTextOutsideLength(){
		String [] outsideLength = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_outside_length')).split("'")
		return outsideLength[0]
	}

	/**
	 * Get text of Equipment Dimension details outside width
	 * @return equipment outside width
	 */
	def String getTextOutsideWidth(){
		String [] outsideWidth = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_outside_width')).split("'")
		return outsideWidth[0]
	}

	/**
	 * Get text of Equipment Dimension details outside Height
	 * @return equipment Outside Height
	 */
	def String getTextOutsideHeight(){
		String [] outsideHeight = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_outside_height')).split("'")
		return outsideHeight[0]
	}

	/**
	 * Get text of Equipment Dimension details clearance
	 * @return equipment clearance
	 */
	def String getTextClearence(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_clearence'))
	}

	/**
	 * Get text of Equipment capacity-speed weight
	 * @return equipment weight
	 */
	def String getTextCapacitySpeedWeight(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_capacity_speed_weigth'))
	}

	/**
	 * Get text of Equipment capacity-speed Minimum speed
	 * @return equipment min speed
	 */
	def String getTextCapacitySpeedMinSpeed(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_capacity_speed_minspeed'))
	}

	/**
	 * Get text of Equipment capacity-speed Maximum speed
	 * @return equipment maxspeed
	 */
	def String getTextCapacitySpeedMaxSpeed(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_capacity_speed_maxspeed'))
	}

	/**
	 * Get text of Equipment Brake Details
	 * @return equipment brake
	 */
	def String getTextAirBrake(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_airbrake'))
	}

	/**
	 * Get text of Equipment Gear Ratio
	 * @return equipment gear ratio
	 */
	def String getTextGearRatio(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_gearratio'))
	}

	/**
	 * Get text of Equipment truck center
	 * @return equipment truck center
	 */
	def String getTextTruckCenter(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/truckCenter'))
	}

	/**
	 * Get text of Equipment Fuel Capacity
	 * @return equipment fuel
	 */
	def String getTextFuelCapacity(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_fuelcapacity'))
	}

	/**
	 * Get text of Equipment Bearings Breaks
	 * @return equipment bearing
	 */
	def String getTextBearingBreaks(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/loco_bearings_breaks'))
	}

	/**
	 * Get text of Equipment Dimension Inside Length
	 * @return equipment inside length
	 */
	def String getTextInsideLength(){
		String [] insideLength = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_inside_length')).split("'")
		return insideLength[0]
	}

	/**
	 * Get text of Equipment Dimension Inside Width
	 * @return insideWidth
	 */
	def String getTextInsideWidth(){
		String [] insideWidth = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_inside_width')).split("'")
		return insideWidth [0]
	}

	/**
	 * Get text of Equipment Dimension Clearance
	 * @return equipment clearance
	 */
	def String getTextRailClearance(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_clearance'))
	}

	/**
	 * Get text of Equipment Capacity Grade
	 * @return equipment grade
	 */
	def String getTextGrade(){
		String grade = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_capacity_grade')).substring(0, 2)
		return grade.trim()
	}

	/**
	 * Get text of Equipment Capacity load limit
	 * @return equipment load limit
	 */
	def String getTextLoadLimit(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_capacity_loadlimit'))
	}

	/**
	 * Get text of Equipment Test details air brake
	 * @return equipment air brake
	 */
	def String getTextRailAirBrake(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_airbrake'))
	}

	/**
	 * Get text of Equipment Test details warning indicator
	 * @return equipment warning indicator
	 */
	def String getTextWarningIndicator(){
		String warningIndicator = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_warning_indicator')).substring(0, 2)
		return warningIndicator.trim()
	}

	/**
	 * Get text of Equipment Other details Bearing Brake
	 * @return equipment brake
	 */
	def String getTextRailcarBearingBrake(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_bearing_brake'))
	}

	/**
	 * Get text of Equipment Other details Truck Type
	 * @return equipment truck type
	 */
	def String getTextRailcarTruckType(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_trucktype'))
	}

	/**
	 * Get text of Equipment Other details side door type
	 * @return equipment side door type
	 */
	def String getTextRailcarSideDoor(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/railcar_side_doortype'))
	}

	/**
	 * Get text of Equipment Build date
	 * @return build date
	 */
	def String getTextBuildDate(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/build_date'))
	}

	/**
	 * Get text of Equipment Operating Breaks
	 * @return operating breaks
	 */
	def String getTextOperatingBreaks(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/operatingbreaks'))
	}

	/**
	 * Get text of Equipment Weight On Drivers
	 * @return Weight On Drivers
	 */
	def String getTextWeightOnDrivers(){
		return WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/weigthondrivers'))
	}

	/**
	 * Get text of Equipment Inside Height
	 * @return Inside Height
	 */
	def String getTextInsideHeight(){
		String [] insideHeight = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/insideheight')).split("'")
		return insideHeight[0]
	}

	/**
	 * Method to click on the  Add New Equipment Button
	 */
	def clickOnAddNewEquipmentButton(){
		WebUI.verifyElementClickable(findTestObject('Object Repository/F7174 - Manage Equipment/addNewEquipmentButton'))
		WebUI.click(findTestObject('Object Repository/F7174 - Manage Equipment/addNewEquipmentButton'))
		WebUI.delay(2)
	}

	/**
	 * Method to add a new Equipment with a random Id
	 * Equipment can be either Railcar / Locomotive depending random selection
	 */
	def addNewEquipment(){
		clickOnAddNewEquipmentButton()
		setUniqueValueForEquipmentId()
		selectRandomlyEquipmentType()
		selectRandomCarKind()
		selectRandomAarCarType()
		setTextForAarMechanicalDesp()
		setValuesForGeneralInformation(equipmentTypeSelected)
		setValuesForDimensionDetails(equipmentTypeSelected)
		setValuesForCapacity(equipmentTypeSelected)
		setValueForTestDetailsOrBrakeTest(equipmentTypeSelected)
		setValuesForOtherDetails(equipmentTypeSelected)
		clickOnEquipmentSaveButton()
	}

	/**
	 * Method to set equipment id
	 * EQUIPMENT ID MUST START WITH 1 TO 4 CHARACTERS AND ENDS WITH 1 TO 11 NUMBERS
	 */
	def setUniqueValueForEquipmentId(){
		int	numberTobeGenerated = utils.generateRandomNumber(4)
		equipmentId = utils.generateRandomAlphabets(4).toUpperCase()+ numberTobeGenerated
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/equipmentIdInputField'), equipmentId)
	}

	/**
	 * Method to randomly select the Equipment Type from the dropdown
	 */
	def selectRandomlyEquipmentType(){
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_EQUIPMENT_TYPE, null)
		int equipmentTypeLength = WebUI.executeJavaScript(EquipmentPageConstants.EQUIPMENT_TYPE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(equipmentTypeLength)
		WebUI.executeJavaScript('document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(2)
		equipmentTypeSelected = WebUI.executeJavaScript(EquipmentPageConstants.EQUIPMENT_TYPE_LABEL, null)
	}

	/**
	 * Method to randomly select the CAr kind from the drop down
	 */
	def selectRandomCarKind(){
		int carKindLength = WebUI.executeJavaScript(EquipmentPageConstants.CAR_KIND_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(carKindLength)
		WebUI.executeJavaScript('document.querySelector(\'#car-kind\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(2)
		String carKind = WebUI.executeJavaScript('return document.querySelector(\'#car-kind\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').innerText', null)
		return carKind.trim().substring(0, 1)
	}

	/**
	 * Method to randomly select the AAR Car Type from the drop down
	 */
	def selectRandomAarCarType(){
		int aarCarTypeLength = WebUI.executeJavaScript(EquipmentPageConstants.AAR_CAR_KIND_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(aarCarTypeLength)
		WebUI.executeJavaScript('document.querySelector(\'#aar-car-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(1)
	}

	/**
	 * Method to set text for AAR Mechanical Description
	 */
	def setTextForAarMechanicalDesp(){
		def aarMechanicalText = utils.generateRandomString(8)
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/aarMechnanicalTextField'), aarMechanicalText)
	}

	/**
	 * Method to select random value from the Owner ship code in General Information section
	 */
	def selectRandomValueForOwnershipCode(){
		int ownerShipCodeLength = WebUI.executeJavaScript(EquipmentPageConstants.OWNERSHIP_CODE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(ownerShipCodeLength)
		WebUI.executeJavaScript('document.querySelector(\'#ownership-code\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(1)
	}

	/**
	 * Method to set value for the Pool number
	 */
	def setValueForPoolNumber(){
		int poolNo = utils.generateRandomNumber(5)
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/poolNumberInputField'))
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/poolNumberInputField'), String.valueOf(poolNo))
	}

	/**
	 * Method to set value on owner mark
	 */
	def setValueForOwnerMark(){
		ownerMark = utils.generateRandomString(10)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/ownerMarkText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/ownerMarkText'), ownerMark)
	}

	/**
	 * Method to set value on Leesee owner
	 */
	def setValueForLeeseOwner(){
		leeseOwner = utils.generateRandomString(10)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/lesseeOwnerText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/lesseeOwnerText'), leeseOwner)
	}

	/**
	 * Method to set value on Transportation Code
	 */
	def setValueForTransportationCode(){
		transportationCode = utils.generateRandomString(10)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/transportationCodeText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/transportationCodeText'), transportationCode)
	}

	/**
	 * Method to set value on condition Code
	 */
	def setValueForConditionCode(){
		conditionCode = utils.generateRandomString(10)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/transConditionCodeText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/transConditionCodeText'), conditionCode)
	}

	/**
	 * Method to set value on service code
	 */
	def setValueForServiceCode(){
		String serviceCodeMenuOptionsPath = EquipmentPageConstants.SERVICE_CODE_LENGTH
		int serviceCodeDropDownLength = WebUI.executeJavaScript(serviceCodeMenuOptionsPath, null)
		int randomServiceCoeNumber = utils.generateRandomNumber(serviceCodeDropDownLength)
		WebUI.executeJavaScript(EquipmentPageConstants.SERVICE_CODE+randomServiceCoeNumber+")> span').click()", null)
		String getSelectedServiceCode = WebUI.executeJavaScript(EquipmentPageConstants.SERVICE_CODE +randomServiceCoeNumber+")> span').innerText", null)
		selectedAARCarType = getSelectedServiceCode.trim()
		WebUI.delay(2)
	}

	/**
	 * Method to set the rates field
	 */
	def setValueForRates(){
		rateCode = utils.generateRandomString(10)
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/rateCodeText'), rateCode)
	}

	/**
	 * Method to set the time rates field
	 */
	def setTimeRate(){
		timeRate = utils.generateRandomNumber(2)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/timeRateText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/timeRateText'), timeRate.toString())
	}

	/**
	 * Method to set the time mileagerates
	 */
	def setMileageRate(){
		mileageRate  = utils.generateRandomNumber(2)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/milageRateText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/milageRateText'), mileageRate.toString())
	}

	/**
	 * Method to set value for Reflectorization
	 */
	def selectRandomValueForReflectorization(){
		int reflectLength = WebUI.executeJavaScript(EquipmentPageConstants.REFLECTORIZATION_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(reflectLength)
		WebUI.executeJavaScript('document.querySelector(\'#reflectorization\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(1)
	}

	/**
	 * Method to set value for the Inside Length and height field
	 */
	def setInputForDimensionInsideLength(){
		int dimension = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/insideLengthDimensionInput'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/insideLengthDimensionInput'), String.valueOf(dimension))
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/insideheightText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/insideheightText'), String.valueOf(dimension))
	}

	/**
	 * Method to set value for the Inside Width field
	 */
	def setInputForDimensionInsideWidth(){
		int width = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/insideWidthDimensionInput'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/insideWidthDimensionInput'), String.valueOf(width))
	}

	/**
	 * Method to set value for inside height field
	 */
	def setInputForDimensionInsideHeight(){
		int dimension = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/insideheightText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/insideheightText'), String.valueOf(dimension))
	}

	/**
	 * Method to set value for the Outside Length field
	 */
	def setInputForDimensionOutsideLength(){
		int length = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/outsideLengthDimensionInput'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/outsideLengthDimensionInput'), String.valueOf(length))
	}

	/**
	 * Method to set value for the Outside Width field
	 */
	def setInputForDimensionOutsideWidth(){
		int width = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/outsideWidthDimensionInput'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/outsideWidthDimensionInput'), String.valueOf(width))
	}

	/**
	 * method to set value for outside height field
	 */
	def setInputForDimensionOutsideHeight(){
		int height = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/outsideHeightText'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/outsideHeightText'), String.valueOf(height))
	}

	/**
	 * Method to set value for the clearance code input
	 */
	def setInputForClearanceCode(){
		String clearanceCode = utils.generateRandomString(5)
		WebUI.click(findTestObject('Object Repository/F7174 - Manage Equipment/clearanceCodeInput'))
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/clearanceCodeInput'))
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/clearanceCodeInput'), clearanceCode)
	}

	/**
	 * Method to set value for the Height extreme width 
	 */
	def setInputForExtremeWidth (){
		exteremWidth = utils.generateRandomString(5)
		WebUI.click(findTestObject('Object Repository/Manage Equipments/extremeWidth'))
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/extremeWidth'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/extremeWidth'), exteremWidth)
	}

	/**
	 * Method to set value for Builder Code
	 */
	def setValuesForBuilderCode(){
		def builderCodeText = utils.generateRandomString(8)
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/builderCodeInputField'))
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/builderCodeInputField'), builderCodeText)
	}

	/**
	 * Method to set value for HoodType
	 */
	def setValueforHoodType(){
		def hoodtypeText = utils.generateRandomString(8)
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/hoodTypeInputField'))
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/hoodTypeInputField'), hoodtypeText)
	}

	/**
	 * Method to set value for cubic capacity
	 */
	def setValueForCubicCapacity(){
		int cubic = utils.generateRandomNumber(3)
		WebUI.click(findTestObject('F7174 - Manage Equipment/insideLengthDimensionInput'))
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/cubicCapacityInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/cubicCapacityInputField'), String.valueOf(cubic))
	}

	/**
	 * Method to set value for cubic Load Limit
	 */
	def setValueForLoadLimit(){
		int LoadLimit = utils.generateRandomNumber(3)
		WebUI.click(findTestObject('Object Repository/Manage Equipments/cubicLoadlimit'))
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/cubicLoadlimit'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/cubicLoadlimit'), String.valueOf(LoadLimit))
	}

	/**
	 * Method to set value for Weight On Rail
	 */
	def setValueForWeightOnRail(){
		int LoadLimit = utils.generateRandomNumber(3)
		WebUI.click(findTestObject('Object Repository/Manage Equipments/weightOnRail'))
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/weightOnRail'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/weightOnRail'), String.valueOf(LoadLimit))
	}

	/**
	 * Method to set value for Tare weight
	 */
	def setValueForTareWeight(){
		int LoadLimit = utils.generateRandomNumber(3)
		WebUI.click(findTestObject('Object Repository/Manage Equipments/tareWeight'))
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/tareWeight'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/tareWeight'), String.valueOf(LoadLimit))
	}

	/**
	 * Method to set value for over weight calc
	 */
	def setValueForOverWeight(){
		int overWeight = utils.generateRandomNumber(3)
		WebUI.click(findTestObject('Object Repository/Manage Equipments/overWeightCalc'))
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/overWeightCalc'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/overWeightCalc'), String.valueOf(overWeight))
	}

	/**
	 *  Method to set value for Gallons
	 */
	def setValueForGallonCapacity(){
		int gallon = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/gallonCapacityInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/gallonCapacityInputField'), String.valueOf(gallon))
	}

	/**
	 *  Method to set value for Over weight Load limit
	 */
	def setValueForOverWeightLoadLimit(){
		int overLoadLimit = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/LoadLimitsOverWeight'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/LoadLimitsOverWeight'), String.valueOf(overLoadLimit))
	}

	/**
	 *  Method to set value for Over weight Tolernace
	 */
	def setValueForOverWeightTolerance(){
		tolerance = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/tolerenceWeight'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/tolerenceWeight'), String.valueOf(tolerance))
	}

	/**
	 *  Method to set value for using Load limit
	 */
	def setValueForUsingLoadlimit(){
		int usingLoadlimit = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('Object Repository/Manage Equipments/usingLoadLimit'))
		WebUI.setText(findTestObject('Object Repository/Manage Equipments/usingLoadLimit'), String.valueOf(usingLoadlimit))
	}

	/**
	 * Method to set value for Grade
	 */
	def selectRandomValueForGrade(){
		int gradeLength = WebUI.executeJavaScript(EquipmentPageConstants.GRADE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(gradeLength)
		WebUI.executeJavaScript('document.querySelector(\'#grade\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(1)
	}

	/**
	 * Method to set value for the weight Capacity
	 */
	def setValueForWeightCapacity(){
		int weight = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/weightCapacityInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/weightCapacityInputField'), String.valueOf(weight))
	}

	/**
	 * Method to set min speed for capacity
	 */
	def setValueForMinSpeedCapacity(){
		int minSpeed = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/minSpeedCapacityInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/minSpeedCapacityInputField'), String.valueOf(minSpeed))
	}

	/**
	 * Method to set max speed for capacity
	 */
	def setValueForMaxSpeedCapacity(){
		int maxSpeed = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/maxSpeedCapacityInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/maxSpeedCapacityInputField'), String.valueOf(maxSpeed))
	}

	/**
	 * Method to set the air brake test in Test details for railcar
	 */
	def setValueForAirBrakeTest(){
		def airBrake = utils.generateRandomString(8)
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/airBrakeTestInputField'))
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/airBrakeTestInputField'), airBrake)
	}

	/**
	 * Method to set value for Warning Indicator
	 */
	def selectRandomValueWarningIndicator(){
		int indicatorLength = WebUI.executeJavaScript(EquipmentPageConstants.WARNING_INDICATOR_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(indicatorLength)
		WebUI.executeJavaScript('document.querySelector(\'#early-warning-indicator\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(1)
	}

	/**
	 * Method to set value for airbrake for Locomotive
	 */
	def setValueForAirBrake(){
		def airBrakeTest = utils.generateRandomString(8)
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/airBrakeInputField'))
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/airBrakeInputField'), airBrakeTest)
	}

	/**
	 * Method to set value for gear Ratio
	 */
	def setValueForGearRatio(){
		int gearRatio = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/gearRatioInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/gearRatioInputField'), String.valueOf(gearRatio))
	}

	/**
	 * Method to randomly select bearing and brake in Locomotove
	 */
	def setRandomValueForBearingBrake(){
		int brakeLength = WebUI.executeJavaScript(EquipmentPageConstants.BEARING_BRAKE_LENGTH, null)
		int valueToBeSelected = utils.generateRandomNumber(brakeLength)
		WebUI.executeJavaScript('document.querySelector(\'#bearing-or-break\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
		WebUI.delay(1)
	}

	/**
	 * Method to set value for fuel capacity
	 */
	def setValueForFuelCapacity(){
		int fuelCapacity = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/fuelCapacityInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/fuelCapacityInputField'), String.valueOf(fuelCapacity))
	}

	/**
	 * Method to set value for truck center length
	 */
	def setValueForTruckCenter(){
		int truckLength = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/truckCenterInputField'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/truckCenterInputField'), String.valueOf(truckLength))
	}

	/**
	 * Method to set value for Side Door Type
	 */
	def setValueForSideDoorType(){
		def sideDoorType = utils.generateRandomString(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/input_sidedoortype'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/input_sidedoortype'), sideDoorType)
	}

	/**
	 * Method to set value for Dimention inside height
	 */
	def setValueForInsideHeight(){
		def insideHeight = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/input_insideheight'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/input_insideheight'), String.valueOf(insideHeight))
	}

	/**
	 * Method to set value for weight on drivers loco
	 */
	def setValueForWeigthOnDrivers(){
		def weigthOnDrivers = utils.generateRandomNumber(3)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/input_weightondrivers'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/input_weightondrivers'), String.valueOf(weigthOnDrivers))
	}

	/**
	 * Method to set value for Operating Brakes loco
	 */
	def setValueForOperatingBrakes(){
		def operatingBrakes = utils.generateRandomString(4)
		WebUI.clearText(findTestObject('F7174 - Manage Equipment/input_operatingbrake'))
		WebUI.setText(findTestObject('F7174 - Manage Equipment/input_operatingbrake'), operatingBrakes)
	}

	/**
	 * Method to set values for the General Information Section for Add Equipment
	 */
	def setValuesForGeneralInformation(String equipmentType){

		/**
		 * Values set when equipment type is Railcar or locomotive
		 */
		selectRandomValueForOwnershipCode()
		setValueForPoolNumber()
		selectRandomValueForReflectorization()
		setValueForOwnerMark()
		setValueForLeeseOwner()
		setValueForTransportationCode()
		setValueForConditionCode()
		setValueForServiceCode()
		setValueForRates()
		setTimeRate()
		setMileageRate()

		/**
		 * Provide field values if the equipment type selected is Locomotive
		 */
		if(equipmentType == EquipmentPageConstants.LOCOMOTIVE){
			setValuesForBuilderCode()
			setValueforHoodType()
		}
	}

	/**
	 * Method to set the values for the dimension details
	 */
	def setValuesForDimensionDetails(String equipmentType){

		/**
		 * Values set when equipment type is Railcar or locomotive
		 */
		setInputForDimensionOutsideLength()
		setInputForDimensionOutsideWidth()
		setInputForClearanceCode()


		/**
		 * Provide field values if the equipment type selected is Railcar
		 */
		if(equipmentType == EquipmentPageConstants.RAILCAR){
			setInputForDimensionInsideLength()
			setInputForDimensionInsideWidth()
			setInputForDimensionInsideHeight()
			setInputForExtremeWidth()
		}
	}

	/**
	 * Method to set values for the capacity details
	 */
	def setValuesForCapacity(String equipmentType){

		/**
		 * Provide field values if the equipment type selected is Railcar
		 */
		if(equipmentType == EquipmentPageConstants.RAILCAR){
			setValueForCubicCapacity()
			setValueForLoadLimit()
			setValueForWeightOnRail()
			setValueForTareWeight()
			setValueForOverWeight()
			setValueForOverWeightLoadLimit()
			setValueForOverWeightTolerance()
			selectRandomValueForGrade()
			setValueForGallonCapacity()
			setValueForUsingLoadlimit()
		}

		/**
		 * Provide field values if the equipment type selected is Locomotive
		 */
		else{

			setValueForWeightCapacity()
			setValueForMinSpeedCapacity()
			setValueForMaxSpeedCapacity()
		}
	}

	/**
	 * Method to provide values for Test details / Brake details
	 * depending on equipment type selected
	 */
	def setValueForTestDetailsOrBrakeTest(String equipmentType){

		/**
		 * Provide field values if the equipment type selected is Railcar
		 */
		if(equipmentType == EquipmentPageConstants.RAILCAR){
			setValueForAirBrakeTest()
			selectRandomValueWarningIndicator()
		}

		/**
		 * Provide field values if the equipment type selected is Locomotive
		 */
		else{
			setValueForAirBrake()
			setValueForGearRatio()
			setValueForTruckCenter()
		}
	}

	/**
	 * Method to set values for the Other details
	 */
	def setValuesForOtherDetails(String equipmentType){

		setRandomValueForBearingBrake()

		/**
		 * Provide field values if the equipment type selected is Railcar
		 */
		if(equipmentType == EquipmentPageConstants.RAILCAR){
			setValueForTruckCenter()
		}

		/**
		 * Provide field values if the equipment type selected is Locomotive
		 */
		else{
			setValueForFuelCapacity()
		}
	}

	/**
	 * Method to click on the Save button in Equipment
	 */
	def clickOnEquipmentSaveButton(){
		WebUI.click(findTestObject('Object Repository/F7174 - Manage Equipment/saveEquipmentButton'))
		WebUI.delay(2)
	}

	/**
	 *   Method to set equipment ID to Search Pane
	 */
	@Keyword
	def setEquipmentIdSearchPane(String equipmentIdText){
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), equipmentIdText)
		WebUI.delay(30)
		WebUI.waitForElementClickable(findTestObject('Object Repository/F7174 - Manage Equipment/addNewEquipmentButton'), 15)
	}

	/**
	 * Method to get No of Equipments of the left pane
	 * @return size of left pane
	 */
	def int equipmentFullLeftPaneSize() {
		List<WebElement> listOfElements = driver.findElements(By.xpath(EquipmentPageConstants.FULL_LEFT_PANE_ELEMENT_SELECTOR))
		int expectedSize = listOfElements.size()
		return expectedSize
	}

	/**
	 *   Method to randomly select a CarID
	 *   @return Random carID
	 */
	@Keyword
	def generateRandomEquipmentID(){
		int noOfEquipments = equipmentFullLeftPaneSize()
		randomEquipmentID = driver.findElement(By.xpath('//*[@data-testid="equipment-list"]/div['+utils.generateRandomNumberLeftPane(noOfEquipments)+']/div/div/span')).getText().replaceAll("\\s", "")
		return randomEquipmentID
	}

	/**
	 *   Method to set equipmentID to Search Pane
	 *   @param equipmentID
	 */
	@Keyword
	def setequipmentIDToSearchPane(String equipmentID){
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), equipmentID.replaceAll("\\s", ""))
		WebUI.delay(55)
	}

	/**
	 * Method to verify car kind filter
	 */
	def selectCarKindFromFilterMenu(){
		this.clickOnFilterIcon()
		this.selectCarKindOnFilterMenu()
		this.clickOnApplyButtonInFilterScreen()
	}

	/**
	 * Method to verify clear functionality
	 */
	def clickOnClearButtonInFilter(){
		this.clickOnFilterIcon()
		this.clickOnClearButton()
	}

	/**
	 * Method to check on pool number and AAR car kind
	 */
	def selectaarCarTypeFromFilterMenu(){
		this.clickOnFilterIcon()
		this.clickOnCancelButtonOnFilterScreen()
		this.clickOnFilterIcon()
		this.selectAARCarTypeInFilterMenu()
		this.setValuesToPoolNumberField()
		this.clickOnApplyButtonInFilterScreen()
	}

	/**
	 * Method to check on pool number and car kind
	 */
	def selectCarKindAndPoolNumber(){
		this.clickOnFilterIcon()
		this.clickOnClearButton()
		this.clickOnFilterIcon()
		this.selectCarKindOnFilterMenu()
		this.setValuesToPoolNumberField()
		this.clickOnApplyButtonInFilterScreen()
		WebUI.delay(30)
	}

	/**
	 * Method to check on AAR Car Type and car kind
	 */
	def selectCarKindAndAARCarType(){
		this.clickOnFilterIcon()
		this.clickOnClearButton()
		this.clickOnFilterIcon()
		this.selectCarKindOnFilterMenu()
		this.selectAARCarTypeInFilterMenu()
		this.clickOnApplyButtonInFilterScreen()
		WebUI.delay(30)
	}

	/**
	 * Method to check on pool number, car kind and AAR Car Type
	 */
	def selectCarKindAARCarTypePoolNumber(){
		this.clickOnFilterIcon()
		this.clickOnClearButton()
		this.clickOnFilterIcon()
		this.selectCarKindOnFilterMenu()
		this.selectAARCarTypeInFilterMenu()
		this.clickOnApplyButtonInFilterScreen()
		WebUI.delay(30)
	}

	/**
	 * Method to select filter icon
	 */
	def clickOnFilterIcon(){
		driver.findElement(By.xpath(EquipmentPageConstants.FILTER_ICON_XPATH)).click()
		WebUI.delay(3)
	}

	/**
	 * Method to select car kind from car kind menu
	 */
	def selectCarKindOnFilterMenu(){
		String carKindDropDownPath = EquipmentPageConstants.CAR_KIND_MENU_OPTION_LENGTH
		int carKindDropDownLength=WebUI.executeJavaScript(carKindDropDownPath, null)
		int randomCarKindNumber = utils.generateRandomNumber(carKindDropDownLength)
		WebUI.executeJavaScript(EquipmentPageConstants.CAR_KIND_DROP_DOWN_SELECTION+randomCarKindNumber+")').click()",null)
		String getSelectedCarKind = WebUI.executeJavaScript(EquipmentPageConstants.CAR_KIND_DROP_DOWN_SELECTION+randomCarKindNumber+")').innerText",null)
		selectedCarKind = getSelectedCarKind.trim()
		WebUI.delay(2)
	}

	/**
	 * Method to click on AAR car type from the filter screen
	 */
	def selectAARCarTypeInFilterMenu(){
		String aarCarTypedMenuOptionsPath = EquipmentPageConstants.AAR_CAR_TYPE_MENU_OPTION_LENGTH
		int aarCarKindDropDownLength = WebUI.executeJavaScript(aarCarTypedMenuOptionsPath, null)
		int randomAARCarTypeNumber = utils.generateRandomNumber(aarCarKindDropDownLength)
		WebUI.executeJavaScript(EquipmentPageConstants.AAR_CAR_TYPE_DROP_DOWN_SELECTION+randomAARCarTypeNumber+")').click()", null)
		String getSelectedAARCarType = WebUI.executeJavaScript(EquipmentPageConstants.AAR_CAR_TYPE_DROP_DOWN_SELECTION+randomAARCarTypeNumber+")').innerText", null)
		selectedAARCarType = getSelectedAARCarType.trim()
		WebUI.delay(2)
	}

	/**
	 * Method to set value to pool number field
	 */
	def setValuesToPoolNumberField(){
		WebUI.setText(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/input_poolNumber'), EquipmentPageConstants.POOL_NUMBER)
		WebUI.delay(2)
	}

	/**
	 * Method to click on Apply button on filter screen
	 */
	def clickOnApplyButtonInFilterScreen(){
		WebUI.click(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/button_Apply'))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Clear button on filter screen
	 */
	def clickOnClearButton(){
		WebUI.click(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/button_Clear'))
		WebUI.delay(2)
	}

	/**
	 * Method to click on cancel button
	 */
	def clickOnCancelButtonOnFilterScreen(){
		WebUI.click(findTestObject('Object Repository/F13151 - Search sort filter Equipment/Page_Transportation Management Syst/button_Cancel'))
		WebUI.delay(1)
	}

	/**
	 *  Method to click on Actions Button
	 */
	def clickOnActions(){
		WebUI.executeJavaScript(EquipmentPageConstants.ACTION_BUTTON, null)
	}

	/**
	 *  Method to click on Edit Equipment option
	 */
	def clickOnEditEquipment(){
		WebUI.executeJavaScript(EquipmentPageConstants.EDIT_EQUIPMENT, null)
	}

	/**
	 *  Method to click on Calender button in Build date field
	 */
	def clickOnCalenderBuildDate(){
		WebUI.executeJavaScript(EquipmentPageConstants.EDIT_CALENDER_BUILD_DATE, null)
	}

	/**
	 *  Method to click on Calender of Build date Today field
	 */
	def clickOnBuildDateCalenderToday(){
		WebUI.executeJavaScript(EquipmentPageConstants.EDIT_CALENDER_BUILDDATE_TODAY, null)
	}

	/**
	 *  Method to edit the added equipment
	 */
	def editEquipment(){
		setEquipmentIdSearchPane(equipmentId)
		WebUI.delay(20)
		clickOnActions()
		clickOnEditEquipment()
		setValueForOwnerMark()
		selectRandomValueForOwnershipCode()
		setValueForLeeseOwner()
		setValueForTransportationCode()
		setValueForConditionCode()
		if(equipmentTypeSelected.equals(EquipmentPageConstants.RAILCAR)){
			setInputForDimensionInsideLength()
			setInputForDimensionInsideWidth()
			setValueForOverWeightTolerance()
			setValueForInsideHeight()
			setValueForGallonCapacity()
			selectRandomValueWarningIndicator()
			setValueForSideDoorType()
		}
		else if(equipmentTypeSelected.equals(EquipmentPageConstants.LOCOMOTIVE)){
			setValueForTruckCenter()
			setValueForTruckCenter()
			setValueForGearRatio()
			setValueForFuelCapacity()
			setRandomValueForBearingBrake()
			setValueForWeigthOnDrivers()
			setValueForOperatingBrakes()
		}
		clickOnCalenderBuildDate()
		clickOnBuildDateCalenderToday()
		clickOnEquipmentSaveButton()
		setEquipmentIdSearchPane(equipmentId)
	}

	/**
	 *   Method to set equipmentID to Add New Equipment Text Field
	 *   @param equipmentID
	 */
	@Keyword
	def setAddEquipmentIdField(String equipmentID){
		WebUI.setText(findTestObject('Object Repository/F7174 - Manage Equipment/equipmentIdInputField'), equipmentID)
		WebUI.delay(2)
	}

	/**
	 *   Method to clear equipmentID to Search Pane
	 */
	@Keyword
	def clearAddEquipmentIdField(){
		WebUI.clearText(findTestObject('Object Repository/F7174 - Manage Equipment/equipmentIdInputField'))
		WebUI.delay(2)
	}
	/**
	 *   Method to click AAR Mechanical Text Field
	 */
	@Keyword
	def clickAARMechTextField(){
		WebUI.click(findTestObject('Object Repository/F7174 - Manage Equipment/aarMechnanicalTextField'))
		WebUI.delay(2)
	}

	/**
	 * Method to Click and remove Car Kind From the Car Kind DropDown and return Car Kind Validation Message 
	 * @param carKindValidationMessage
	 * @return carKindValidationMessage
	 */
	def String clickOnRemoveCarKind(){
		selectRandomCarKind()
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_CAR_KIND_DROPDOWN,null)
		WebUI.delay(2)
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_CAR_KIND_DROPDOWN_ICON,null)
		WebUI.delay(2)
		String carKindValidationMessage = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/emptyCarKindMsg'))
		return carKindValidationMessage
	}

	/**
	 * Method to Click Add new Equipment Cancel button
	 */
	@Keyword
	def clickCancelBtn(){
		WebUI.click(findTestObject('Object Repository/F7174 - Manage Equipment/addNewEquipCancelBtn'))
		WebUI.delay(2)
	}

	/**
	 * Method to Click and remove AAR Car Type from the AAR Car Type DropDown and return AAR Car Type Validation Message
	 * @param aarCarTypeValidationMessage
	 * @return aarCarTypeValidationMessage
	 */
	def String clickOnRemoveAarCarType(){
		selectRandomCarKind()
		selectRandomAarCarType()
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_AARCARTYPE_DROPDOWN,null)
		WebUI.delay(2)
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_AARCARTYPE_DROPDOWN_ICON,null)
		WebUI.delay(2)
		String aarCarTypeValidationMessage = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/emptyAARCarTypemsg'))
		return aarCarTypeValidationMessage
	}

	/**
	 * Method to Click and remove Equipment Type from the Equipment Type Dropdown and return Equipment Type Validation Message 
	 * @param equipmentTypeValidationMessage
	 * @return equipmentTypeValidationMessage
	 */
	def String clickOnRemoveEquipmentType(){
		selectRandomlyEquipmentType()
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_EQUIPMENT_DROPDOWN,null)
		WebUI.delay(2)
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_EQUIPMENT_DROPDOWN_ICON,null)
		WebUI.delay(2)
		String equipmentTypeValidationMessage = WebUI.getText(findTestObject('Object Repository/F7174 - Manage Equipment/emptyAARCarTypemsg'))
		return equipmentTypeValidationMessage
	}

	/**
	 * Method to set RailCar to Equipment type drop down
	 * @Param textOfEquipmentTypeRailcar
	 * @return textOfEquipmentTypeRailcar
	 */
	@Keyword
	def String clickEquipTypeRailCar(){
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_EQUIPMENT_TYPE_RAILCAR, null)
		WebUI.delay(2)
		String textOfEquipmentTypeRailcar = WebUI.executeJavaScript(EquipmentPageConstants.TEXT_EQUIPMENT_TYPE_RAILCAR, null)
		return textOfEquipmentTypeRailcar.trim()
	}

	/**
	 * Method to set Locomotive to Equipment type drop down
	 * @Param textOfEquipmentTypeLocomotive
	 * @return textOfEquipmentTypeLocomotive
	 */
	@Keyword
	def String clickEquipTypeLocomotive(){
		WebUI.executeJavaScript(EquipmentPageConstants.CLICK_EQUIPMENT_TYPE_LOCOMOTIVE, null)
		WebUI.delay(2)
		String textOfEquipmentTypeLocomotive = WebUI.executeJavaScript(EquipmentPageConstants.TEXT_EQUIPMENT_TYPE_LOCOMOTIVE, null)
		return textOfEquipmentTypeLocomotive.trim()
	}

	/**
	 * Method to get the selected AAR Car Type from API
	 * @param carKind
	 * @return aarCarTypeList
	 */
	@Keyword
	def getSelectedAarCarType(String kindKey){
		List<String> aarCarTypeList = new ArrayList()
		String resText = getAllAarCarTypeObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noAarCarType = response.size();
		for(int i=0;i<noAarCarType;i++){
			String carKind = response[i].kindType.kindKey.toString()
			if(kindKey.equals(carKind)){
				aarCarTypeList.add(response[i].aarCarType.toString())
			}
		}
		return aarCarTypeList
	}

	/**
	 * Method to get the selected Car Kind from API
	 * @param resText
	 * @return carKindList
	 */
	@Keyword
	def getSelectedCarKind(String equipmentType){
		Set<String> carKinds = new HashSet()
		String resText = getAllAarCarTypeObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noAarCarType = response.size();
		for(int i=0;i<noAarCarType;i++){
			String equipType = response[i].kindType.description.toString()
			if(equipmentType.equals(EquipmentPageConstants.EQUIPMENT_TYPE_LOCOMOTIVE)){
				if(EquipmentPageConstants.EQUIPMENT_TYPE_LOCOMOTIVE.equals(equipType)) {
					carKinds.add(response[i].kindType.kindKey.toString())
				}
			}
			else {
				if(!EquipmentPageConstants.EQUIPMENT_TYPE_LOCOMOTIVE.equals(equipType)) {
					carKinds.add(response[i].kindType.kindKey.toString())
				}
			}
		}
		List<String> carKindList = new ArrayList(carKinds)
		return carKindList
	}

	/**
	 * Method to set Mandatory Fields For Add New Equipment 
	 */
	@Keyword
	def String setMandatoryFieldsForAddNewEquipment(){
		setUniqueValueForEquipmentId()
		selectRandomlyEquipmentType()
		selectRandomCarKind()
		selectRandomAarCarType()
	}
}
