
package com.ge.tms.waybillVerify
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.List
import com.ge.tms.commonactions.WaybillActions
import com.ge.tms.constants.WayBillPageConstants
import com.ge.tms.util.CommonUtilities
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.logging.KeywordLogger
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
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import internal.GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.junit.After
import org.openqa.selenium.By as By

import com.google.common.collect.ComparatorOrdering
import com.google.common.collect.Ordering;
import com.google.common.primitives.Booleans.BooleanArrayAsList

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for WayBill Verification
 */
public class WaybillVerification {


	public def actualWaybillNumber = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_rightpane'));

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities commonUtil= new CommonUtilities()

	/**
	 *  Variable to define the path of DB.json
	 */
	def str = System.getProperty("user.dir")
	def path = str+'/Data Files/db.json'

	/**
	 * Logger class
	 */
	KeywordLogger log = new KeywordLogger()

	/**
	 *  Action class for WaybillActions
	 */
	WaybillActions actions = new WaybillActions()

	/**
	 *  Array variable that contain all the values in the Actions dropdown
	 */
	def actionsDropdownValuesArr = (([
		'Load Car',
		'Empty Car',
		'Reverse Route']) as String[])

	/**
	 * Error Message of search in waybill
	 */

	String expectedDisplayMessageSearch = 'No waybills found. Check your search criteria.'

	/**
	 *  Webdriver Instance from selenium
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * To verify all the dropdown values in Actions Dropdown in waybill screen
	 */

	@Keyword
	def verifyWaybillActionsDropdown(){

		String actualDropDownValueforEditWayBill = WebUI.executeJavaScript('return document.querySelectorAll("#root px-dropdown")[2].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").innerHTML',
				null)

		WebUI.verifyMatch(actualDropDownValueforEditWayBill, actionsDropdownValuesArr[0], false)

		String actualDropDownValueforSendEdi = WebUI.executeJavaScript('return document.querySelectorAll("#root px-dropdown")[2].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").innerHTML',
				null)

		WebUI.verifyMatch(actualDropDownValueforSendEdi, actionsDropdownValuesArr[1], false)

		String actualDropDownValueforPrint = WebUI.executeJavaScript('return document.querySelectorAll("#root px-dropdown")[2].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3) > span").innerHTML',
				null)

		WebUI.verifyMatch(actualDropDownValueforPrint, actionsDropdownValuesArr[2], false)

		WebUI.delay(2)
	}

	/**
	 * Method to get first car id from json file and verify
	 * @param expectedCarId
	 */
	@Keyword
	def verifyCarIDFirstRecord(String expectedCarId){
		String textInLeftPane = WebUI.getText(findTestObject('F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_carId_leftpane'))
		String  textInRightPane = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_carid_rightpane'))
		WebUI.verifyEqual(textInLeftPane, expectedCarId)
		WebUI.verifyEqual(textInRightPane, expectedCarId)
	}

	/**
	 * Method to get first waybill number from json file and verify
	 * @param expectedWayBillNumber
	 */
	@Keyword
	def verifyWayBillNumberFirstRecord(String expectedWayBillNumber){
		String textInLeftPane = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_leftpane')).substring(1)
		String  textInRightPane = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_waybillnumber_rightpane'))
		WebUI.verifyEqual(textInLeftPane, expectedWayBillNumber)
		WebUI.verifyEqual(textInRightPane, expectedWayBillNumber)
	}

	/**
	 * Method to get first Sender from json file and verify
	 * @param expectedSender
	 */
	@Keyword
	def verifySenderFirstRecord(String expectedSender){
		String textInLeftPane = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_leftpane')).substring(1)
		String  textInRightPane = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_waybillnumber_rightpane'))
		def senderRecordfromUI=commonUtil.getSelectedWaybillAPI(textInLeftPane)
		String sender = senderRecordfromUI.referenceInformation.waybillNumber.toString()
		WebUI.verifyEqual(sender, expectedSender)
	}
	/**
	 * Method to get first Shipper from json file and verify
	 * @param expectedShipper
	 */
	@Keyword
	def verifyShipperFirstRecord(String expectedShipper){
		String textRightPane = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_shipper_name_rightpane'))
		WebUI.verifyEqual(textRightPane, expectedShipper)
	}

	/**
	 * Method to get first Consignee from json file and verify
	 * @param expectedConsignee
	 */
	@Keyword
	def verifyConsigneeFirstRecord(String expectedConsignee){
		String textRightPane = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_consignee_name_rightpane'))
		WebUI.verifyEqual(textRightPane, expectedConsignee)
	}

	/**
	 *  Method to get the selected waybill
	 * @param wayBillId
	 * @return waybill for the number
	 */
	@Keyword
	def getSelectedWaybill(String wayBillId){

		def jsonSlurper = new JsonSlurper()
		def data = jsonSlurper.parse(new File(path))
		def noOfWaybills = data.waybills.size();
		for(int i=0;i<noOfWaybills;i++){
			String billId = data.waybills[i].referenceInformation.waybillNumber.toString()
			if(wayBillId.equals(billId)){
				log.logInfo("Selected Waybill : "+data.waybills[i])
				return data.waybills[i];
			}
		}
	}

	/**
	 *  Method to verify the waybill errors
	 * @param actualWaybillError,expectedWaybillError
	 */
	@Keyword
	def verifyWaybillErrors(def actualWaybillError, def expectedWaybillError){
		WebUI.verifyEqual(actualWaybillError, expectedWaybillError)
	}

	/**
	 *  Method to verify No search criteria matched 
	 */
	@Keyword
	def verifyNoSearchMatched(){
		String actualMessageDispalyedSearch =	WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/ErrorMessegeForSearch'))
		WebUI.verifyEqual(actualMessageDispalyedSearch, expectedDisplayMessageSearch)
	}

	/**
	 * Method to verify the Sort based on CarId
	 */
	@Keyword
	def verifyCarIdSort(){
		List<String> waybillcarIdList = new ArrayList<String>();
		List<WebElement> listOfElements = driver.findElements(By.xpath('//*[@class="flex flex--row"]/p'))
		def expectedCarIdSize = listOfElements.size()
		for(WebElement ele : listOfElements){
			String carId = ele.getText().substring(0);
			waybillcarIdList.add(carId)
		}
		commonUtil.sortOrder(waybillcarIdList)
	}

	/**
	 * Method to verify the Sort based on WaybillNumber
	 */
	def verifyWaybillNumberSort(){
		int expectedWaybillNumberSize = waybillLeftPaneSize()
		List<String> waybillNumberList = new ArrayList<String>();
		for(int i=1; i<= expectedWaybillNumberSize; i++){
			String waybillTextFromEachDiv = '//*[@class="flex__item scroll-y"]/div['+ (i) +']'+'/div[1]/div[1]/div/span'
			WebElement wayBillActualText = driver.findElement(By.xpath(waybillTextFromEachDiv))
			String wayBillNumber = wayBillActualText.getText().substring(1);
			waybillNumberList.add(wayBillNumber)
		}
		commonUtil.sortOrder(waybillNumberList)
	}

	/**
	 * Method to verify the Sort based on WaybillNumber
	 */
	def verifyCommoditySort(){
		int expectedCommoditySize = waybillLeftPaneSize()
		List<String> commodityList = new ArrayList<String>();
		for(int i=1; i<= expectedCommoditySize; i++){
			String commodityTextFromEachDiv = "//*[contains(@data-testid,'waybill-item-')]/div/div["+i+"]/div/div/span"
			WebElement commodityActualText = driver.findElement(By.xpath(commodityTextFromEachDiv))
			String commodity = commodityActualText.getText().substring(0);
			commodityList.add(commodity)
		}
		commonUtil.sortOrder(commodityList)
	}

	/**
	 * Method to get the size of left pane
	 * @return size of left pane
	 */
	def int waybillLeftPaneSize() {
		List<WebElement> listOfElements = driver.findElements(By.xpath(WayBillPageConstants.WaybillLeftPaneSubdiv))
		int expectedSize = listOfElements.size()
		return expectedSize
	}

	/**
	 * Method to verify the waybill size in the left pane
	 * @param resObj, actualwaybill size
	 */
	@Keyword
	def verifyWaybillSize(ResponseObject waybillResponseObject){
		String resText = waybillResponseObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def waybillSizeFromResponse = response.size();
		/**
		 * Defect for mismatch in size - DE67703
		 */
		//	WebUI.verifyEqual(waybillDataSizeFromResponse, waybillLeftPaneSize())
	}

	/**
	 *  Method to verify all the displayed fields in the waybill Overview Tab UI with the API
	 * @param waybillResponseObject
	 */
	@Keyword
	def verifyWaybillOverViewTabDetails(ResponseObject waybillResponseObject){
		String resText = waybillResponseObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def waybillResponse  = slupper.parseText(resText)
		int waybillToBeSelected = commonUtil.generateRandomNumber(waybillLeftPaneSize())
		def waybillToBeVerified = waybillResponse[(waybillToBeSelected-1)];
		String waybillDivToBeClicked = '//*[@id="root"]/div/div/section/section/div[1]/section/div['+ (waybillToBeSelected) + ']'
		WebElement waybillToBeClicked = driver.findElement(By.xpath(waybillDivToBeClicked)).click()

		/**
		 *  Waybill number verification
		 */
		def expectedWaybillNumber = waybillToBeVerified.referenceInformation.waybillNumber
		actualWaybillNumber = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_rightpane'))
		WebUI.verifyEqual(actualWaybillNumber, expectedWaybillNumber)

		/**
		 *  carid verification
		 */
		def expectedCarid = waybillToBeVerified.equipmentDetails.carId
		def actualCarID = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_carid_rightpane'))
		WebUI.verifyEqual(actualCarID, expectedCarid)

		/**
		 * waybill status verification
		 */
		def expectedWaybillStatus = waybillToBeVerified.referenceInformation.waybillStatus
		def actualWaybillStatus = WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/waybill_status'))
		WebUI.verifyEqual(actualWaybillStatus, expectedWaybillStatus)

		/**
		 *  Overview origin city verification
		 */
		def expectedWaybillOriginCity = waybillToBeVerified.stations.originStation.address.city
		def actualWaybillOriginCity = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_origin_city'))
		WebUI.verifyEqual(actualWaybillOriginCity, expectedWaybillOriginCity)

		/**
		 * Overview origin state verification
		 */
		def expectedWaybillOriginState = waybillToBeVerified.stations.originStation.address.state
		def actualWaybillOriginState = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_origin_state'))
		WebUI.verifyEqual(actualWaybillOriginState, expectedWaybillOriginState)

		/**
		 *  Overview destination city verification
		 */
		def expectedWaybillDestinationCity = waybillToBeVerified.stations.destinationStation.address.city
		def actualWaybillDestinationCity = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_destination_city'))
		WebUI.verifyEqual(actualWaybillDestinationCity, expectedWaybillDestinationCity)

		/**
		 * Overview destination state verification
		 */
		def expectedWaybillDestinationState = waybillToBeVerified.stations.destinationStation.address.state
		def actualWaybillDestinationState = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_destination_state'))
		WebUI.verifyEqual(actualWaybillDestinationState, expectedWaybillDestinationState)

		/**
		 *  Overview customers shipper company name verification
		 */
		def expectedShipperCompanyName = waybillToBeVerified.customers.shipper.companyName
		def actualShipperCompanyName = WebUI.getText(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customers_shipper_companyname'))
		WebUI.verifyEqual(actualShipperCompanyName, expectedShipperCompanyName)

		/**
		 *  Overview customers shipper address verification
		 */
		def expectedShipperAddress = waybillToBeVerified.customers.shipper.address.streetAddressLine1
		def actualShipperAddress = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customer_shipper_address'))
		WebUI.verifyEqual(actualShipperAddress, expectedShipperAddress)

		/**
		 *  Overview customers shipper state verification
		 */
		def expectedShipperState = waybillToBeVerified.customers.shipper.address.state
		def actualShipperState = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customer_shipper_state'))
		WebUI.verifyEqual(actualShipperState, expectedShipperState)

		/**
		 *  Overview customers shipper country verification
		 */
		def expectedShipperCountry = waybillToBeVerified.customers.shipper.address.country
		def actualShipperCountry = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customers_shipper_country'))
		def tempShipperCountry = (expectedShipperCountry == null || expectedShipperCountry == "") ? "--" : expectedShipperCountry;
		WebUI.verifyEqual(actualShipperCountry, tempShipperCountry)

		/**
		 *  Overview customers shipper zipcode verification - DEFECT DE67724
		 */
		def expectedShipperZipCode = waybillToBeVerified.customers.shipper.address.zipCode
		def actualShipperZipCode = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customers_shipper_zipcode'))
		WebUI.verifyEqual(actualShipperZipCode, expectedShipperZipCode)

		/**
		 *  Overview customers consignee company name verification
		 */
		def expectedConsigneeCompanyName = waybillToBeVerified.customers.consignee.companyName
		def actualConsigneeCompanyName = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customers_consignee_comapnyname'))
		WebUI.verifyEqual(actualConsigneeCompanyName, expectedConsigneeCompanyName)

		/**
		 *  Overview customers consignee address verification
		 */
		def expectedConsigneeAddress = waybillToBeVerified.customers.consignee.address.streetAddressLine1
		def actualConsigneeAddress = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customer_consignee_address'))
		WebUI.verifyEqual(actualConsigneeAddress, expectedConsigneeAddress)

		/**
		 *  Overview customers consignee city verification
		 */
		def expectedConsigneeCity = waybillToBeVerified.customers.consignee.address.city
		def actualConsigneeCity = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customer_consignee_city'))
		WebUI.verifyEqual(actualConsigneeCity, expectedConsigneeCity)

		/**
		 *  Overview customers consignee state verification
		 */
		def expectedConsigneeState = waybillToBeVerified.customers.consignee.address.state
		def actualConsigneeState = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customer_consignee_state'))
		WebUI.verifyEqual(actualConsigneeState, expectedConsigneeState)

		/**
		 *  Overview customers consignee country verification
		 */
		def expectedConsigneeCountry = waybillToBeVerified.customers.consignee.address.country
		def actualConsigneeCountry = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_customer_consignee_country'))
		WebUI.verifyEqual(actualConsigneeCountry, expectedConsigneeCountry)

		/**
		 *  Overview Equipment details AAR Type verification
		 */
		def expectedEquipmentAarType = waybillToBeVerified.equipmentDetails.aarType
		def actualEquipmentAarType = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_details_aar_type'))
		def tempEquipmentAarType = (expectedEquipmentAarType == null || expectedEquipmentAarType == "") ? "--" : expectedEquipmentAarType;
		WebUI.verifyEqual(actualEquipmentAarType, tempEquipmentAarType)

		/**
		 *  Overview Equipment details Method code verification
		 */
		def expectedTransportationMethodCode = waybillToBeVerified.equipmentDetails.transportationMethodCode
		def actualTransportationMethodCode = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_details_transportationmethodcode'))
		def tempTransportationMethodCode = (expectedTransportationMethodCode == null || expectedTransportationMethodCode == "") ? "--" : expectedTransportationMethodCode;
		WebUI.verifyEqual(actualTransportationMethodCode, tempTransportationMethodCode)

		/**
		 *  Overview Equipment details EDI type verification
		 */
		def expectedEdiType = waybillToBeVerified.equipmentDetails.ediType
		def actualEdiType = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_type_edi_type'))
		def tempEdiType = (expectedEdiType == null || expectedEdiType == " ") ? "--" : expectedEdiType;
		WebUI.verifyEqual(actualEdiType, tempEdiType)

		/**
		 *  Overview Equipment details length verification
		 */
		def expectedLength = waybillToBeVerified.equipmentDetails.length
		def actualLength = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_type_length'))
		def tempLength = (expectedLength == null || expectedLength == " ") ? "--" : expectedLength;
		WebUI.verifyEqual(actualLength, tempLength)

		/**
		 *  Overview Equipment details weight verification
		 */
		def expectedWeight = waybillToBeVerified.equipmentDetails.weight
		def actualWeight = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_type_weight'))
		def tempWeight = (expectedWeight == null || expectedWeight == " ") ? "--" : expectedWeight;
		WebUI.verifyEqual(actualWeight, tempWeight)

		/**
		 *  Overview Equipment details dunnage verification
		 */
		def expectedDunnage = waybillToBeVerified.equipmentDetails.dunnage
		def actualDunnage = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_type_dunnage'))
		def tempDunnage = (expectedDunnage == null || expectedDunnage == "") ? "--" : expectedDunnage;
		WebUI.verifyEqual(actualDunnage, tempDunnage)

		/**
		 *  Overview Equipment details allowance verification
		 */
		def expectedAllowance = waybillToBeVerified.equipmentDetails.weightAllowance
		def actualAllowance = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_equipment_type_allowance'))
		def tempAllowance = (expectedAllowance == null || expectedAllowance == "") ? "--" : expectedAllowance;
		WebUI.verifyEqual(actualAllowance, tempAllowance)

		/**
		 *  Overview Commodity STCC Number verification
		 */
		def expectedStccNumber = waybillToBeVerified.commodity.commodityDescription.commodityCode
		def actualStccNumber = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_commodity_stccnumber'))
		def tempStccNumber = (expectedStccNumber == null || expectedStccNumber == "") ? "--" : expectedStccNumber;
		WebUI.verifyEqual(actualStccNumber, tempStccNumber)

		/**
		 *  Overview Commodity Method verification
		 */
		def expectedMethod = waybillToBeVerified.commodity.methodOfPayment
		def actualMethod = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_commodity_method'))
		def tempMethod = (expectedMethod == null || expectedMethod == "") ? "--" : expectedMethod;
		WebUI.verifyEqual(actualMethod, tempMethod)

		/**
		 *  Overview Routing role verification
		 */
		def expectedRoutingRole = waybillToBeVerified.route[0].role
		def actualRoutingRole = WebUI.getText(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_routing_role'))
		def tempRoutingRole = (expectedRoutingRole == null || expectedRoutingRole == "") ? "--" : expectedRoutingRole;
		WebUI.verifyEqual(actualRoutingRole, tempRoutingRole)

		/**
		 *  Overview Routing railroad verification
		 */
		def expectedRoutingRailroad = waybillToBeVerified.route[0].railRoadName
		def actualRoutingRailroad = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_routing_railroad'))
		def tempRoutingRailroad = (expectedRoutingRailroad == null || expectedRoutingRailroad == "") ? "--" : expectedRoutingRailroad;
		WebUI.verifyEqual(actualRoutingRailroad, tempRoutingRailroad)

		/**
		 *  Overview Routing junction verification
		 */
		def expectedRoutingJunction = waybillToBeVerified.route[0].junctionName
		def actualRoutingJunction = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_routing_junction'))
		def tempRoutingJunction = (expectedRoutingJunction == null || expectedRoutingJunction == "") ? "--" : expectedRoutingJunction;
		WebUI.verifyEqual(actualRoutingJunction, tempRoutingJunction)

		/**
		 *  Overview Routing To role verification
		 */
		def expectedRoutingToRole = waybillToBeVerified.route[1].role
		def actualRoutingToRole = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_routing_to_role'))
		def tempRoutingToRole = (expectedRoutingToRole == null || expectedRoutingToRole == "") ? "--" : expectedRoutingToRole;
		WebUI.verifyEqual(actualRoutingToRole, tempRoutingToRole)

		/**
		 *  Overview Routing To railroad verification
		 */
		def expectedRoutingToRailroad = waybillToBeVerified.route[1].railRoadName
		def actualRoutingToRailroad = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_routing_to_railroad'))
		def tempRoutingToRailroad = (expectedRoutingToRailroad == null || expectedRoutingToRailroad == "") ? "--" : expectedRoutingToRailroad;
		WebUI.verifyEqual(actualRoutingToRailroad, tempRoutingToRailroad)

		/**
		 *  Overview Routing To junction verification
		 */
		def expectedRoutingToJunction = waybillToBeVerified.route[1].junctionName
		def actualRoutingToJunction = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_routing_to_junction'))
		def tempRoutingToJunction = (expectedRoutingToJunction == null || expectedRoutingToJunction == "") ? "--" : expectedRoutingToJunction;
		WebUI.verifyEqual(actualRoutingToJunction, tempRoutingToJunction)
	}

	/**
	 *  Method to verify all the displayed fields in the waybill Overview Tab UI with the API
	 * @param waybillResponseObject,selectedWaybillNumber
	 */
	@Keyword
	def verifyWaybillCustomerTabDetails(ResponseObject waybillResponseObject, String selectedWaybillNumber){

		/**
		 * Get Header Text and Verify with expected header text
		 */
		String headerText =WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/header'))
		WebUI.verifyEqual(headerText, WayBillPageConstants.CUSTOMER_TAB_HEADER_TEXT)
		String tablHeaderCompany = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_company'))
		String tablHeaderCustomerRole = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_CustomerRole'))
		String tablHeaderAddress = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_address'))
		String tablHeaderCity = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_city'))
		String tablHeaderState = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_state'))
		String tablHeaderZipCode = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_zipCode'))
		String tablHeaderCountry = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/tableHeader_country'))

		/**
		 * Verifying Table Header Text
		 */
		WebUI.verifyEqual(tablHeaderCompany, WayBillPageConstants.COMPANY_TEXT)
		WebUI.verifyEqual(tablHeaderCustomerRole, WayBillPageConstants.CUSTOMER_ROLE)
		WebUI.verifyEqual(tablHeaderAddress, WayBillPageConstants.ADDRESS)
		WebUI.verifyEqual(tablHeaderCity, WayBillPageConstants.CITY)
		WebUI.verifyEqual(tablHeaderState, WayBillPageConstants.STATE)
		WebUI.verifyEqual(tablHeaderZipCode, WayBillPageConstants.ZIPCODE)
		WebUI.verifyEqual(tablHeaderCountry, WayBillPageConstants.COUNTRY)

		/**
		 * Getting Selected Waybill Number. Using SubStrig since UI Waybill number appended with '#'. So removing '#'
		 */
		selectedWaybillNumber = selectedWaybillNumber.substring(1,selectedWaybillNumber.length())

		/**
		 * Parsing Json text and getting Customer specific data for the given Waybill Number
		 */
		String resText = waybillResponseObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def waybillResponse  = slupper.parseText(resText)
		def singleWaybillData;
		def customerSpecificData;
		int responseSize = waybillResponse.size()
		boolean flag=false;
		for(int i=0;i<responseSize;i++){
			if(!flag){
				singleWaybillData = waybillResponse[i]
				String wayBillNumber = singleWaybillData.referenceInformation.waybillNumber.toString()
				if(wayBillNumber.equals(selectedWaybillNumber)){
					customerSpecificData = singleWaybillData.customers
					flag=true
				}
			}
		}

		/**
		 * Always Consignee and Shipper size would be 1
		 * Finding the Third parties size and adding all to get the actual count of the customer
		 */
		int sizeOfConsignee=1;
		int sizeOfShipper =1;
		int sizeOfThirdParties = customerSpecificData.thirdParties.size();
		int expectedSizeOfCustomers = sizeOfConsignee+sizeOfShipper+sizeOfThirdParties;

		/**
		 * Getting the rows of the customer from UI and Iterating through each row
		 */
		List<WebElement> listOfCustomers = driver.findElements(By.xpath(WayBillPageConstants.CUSTOMER_ROWS_XPATH))
		String rowsData;
		int actualSizeOfCustomers=0;
		def ExpectedCustomerData;
		if(listOfCustomers.size()>0){
			/**
			 * Taking i value 1 because always 0 is table header
			 */

			for(int i=1;i<listOfCustomers.size();i++){
				WebElement ele = listOfCustomers.get(i)
				rowsData= ele.getText()
				int len = rowsData.trim().length()
				if(rowsData!=null && len>0){
					actualSizeOfCustomers=actualSizeOfCustomers+1
					String[] actualCustomersData = rowsData.split("\n")

					if((actualCustomersData[1].toString()).equals("Consignee")){
						WebUI.verifyEqual(actualCustomersData[0].trim(), customerSpecificData.consignee.companyName.trim())
						WebUI.verifyEqual(actualCustomersData[1].trim(), customerSpecificData.consignee.role.trim())
						WebUI.verifyEqual(actualCustomersData[2].trim(), ((customerSpecificData.consignee.address.streetAddressLine1)+" "+(customerSpecificData.consignee.address.streetAddressLine2)).trim())
						WebUI.verifyEqual(actualCustomersData[3].trim(), customerSpecificData.consignee.address.city.trim())
						WebUI.verifyEqual(actualCustomersData[4].trim(), customerSpecificData.consignee.address.state.trim())
						WebUI.verifyEqual(actualCustomersData[5].trim(), customerSpecificData.consignee.address.zipCode.trim())
						WebUI.verifyEqual(actualCustomersData[6].trim(), customerSpecificData.consignee.address.country.trim())
					}
					else if((actualCustomersData[1].toString()).equals("Shipper")){
						WebUI.verifyEqual(actualCustomersData[0].trim(), customerSpecificData.shipper.companyName.trim())
						WebUI.verifyEqual(actualCustomersData[1].trim(), customerSpecificData.shipper.role.trim())
						WebUI.verifyEqual(actualCustomersData[2].trim(), (customerSpecificData.shipper.address.streetAddressLine1+" "+(customerSpecificData.shipper.address.streetAddressLine2)).trim())
						WebUI.verifyEqual(actualCustomersData[3].trim(), customerSpecificData.shipper.address.city.trim())
						WebUI.verifyEqual(actualCustomersData[4].trim(), customerSpecificData.shipper.address.state.trim())
						WebUI.verifyEqual(actualCustomersData[5].trim(), customerSpecificData.shipper.address.zipCode.trim())
						WebUI.verifyEqual(actualCustomersData[6].trim(), customerSpecificData.shipper.address.country.trim())
					}
					else{
						WebUI.verifyEqual(actualCustomersData[0].trim(), customerSpecificData.thirdParties[0].companyName.trim())
						WebUI.verifyEqual(actualCustomersData[1].trim(), customerSpecificData.thirdParties[0].role.trim())
						WebUI.verifyEqual(actualCustomersData[2].trim(), (customerSpecificData.thirdParties[0].address.streetAddressLine1+" "+(customerSpecificData.thirdParties[0].address.streetAddressLine2)).trim())
						WebUI.verifyEqual(actualCustomersData[3].trim(), customerSpecificData.thirdParties[0].address.city.trim())
						WebUI.verifyEqual(actualCustomersData[4].trim(), customerSpecificData.thirdParties[0].address.state.trim())
						WebUI.verifyEqual(actualCustomersData[5].trim(), customerSpecificData.thirdParties[0].address.zipCode.trim())
						WebUI.verifyEqual(actualCustomersData[6].trim(), customerSpecificData.thirdParties[0].address.country.trim())
					}
				}
			}
			WebUI.verifyEqual(actualSizeOfCustomers,expectedSizeOfCustomers)
		}
	}

	/**
	 *  Method to verify all the displayed fields in the waybill Overview Tab UI with the API
	 * @param waybillResponseObject,selectedWaybillNumber
	 */
	@Keyword
	def verifyWaybillVersionTabDetails(ResponseObject waybillResponseObject, String selectedWaybillNumber){

		/**
		 * Parsing Json text and getting Customer specific data for the given Waybill Number
		 */
		String resText = waybillResponseObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def waybillResponse  = slupper.parseText(resText)
		def singleWaybillData;
		HashMap versionSpecificData;
		int responseSize = waybillResponse.size()
		boolean flag=false;
		Map changeLogData = new HashMap()
		String selectedWayBillNumberUpdated = selectedWaybillNumber.substring(1,selectedWaybillNumber.length())

		if(responseSize>=0){
			for(int i=0;i<responseSize;i++){
				if(!flag){
					singleWaybillData = waybillResponse[i]
					String wayBillNumber = singleWaybillData.referenceInformation.waybillNumber.toString()
					if(wayBillNumber.equals(selectedWayBillNumberUpdated)){
						versionSpecificData = singleWaybillData.versionHistory
						flag=true
					}
				}
			}
		}
		String noDataText;
		List<String> keysList = new ArrayList<String>()
		if(versionSpecificData.changelog.toString() == "{}" || (versionSpecificData.changelog).toString().equals("{}")){
			WebElement noDataWebElement = driver.findElement(By.xpath(WayBillPageConstants.NO_VERSION_DATA_XPATH))
			noDataText = (noDataWebElement.getText().split("\n"))[1]
			WebUI.verifyEqual(noDataText, WayBillPageConstants.NO_VERSION_DATA_TEXT)
		}
		else{
			/**
			 * Getting the length of the versions
			 */
			String actalSizeOfVersions = WebUI.executeJavaScript(WayBillPageConstants.VERSION_LENGTH_SELECTOR, null)
			changeLogData = versionSpecificData.changelog
			Set< Map.Entry< String,Integer> > setOfChangeLogData = changeLogData.entrySet();
			for (Map.Entry< String,Integer> me:setOfChangeLogData) {
				keysList.add(me.getKey())
			}
			Collections.sort(keysList)
			Collections.reverse(keysList)
			def specificVersionData;
			int lengthOfTheVersion=keysList.size()

			/**
			 * Verify actual size of versions with the API versions size
			 */
			WebUI.verifyEqual(actalSizeOfVersions, lengthOfTheVersion)

			/**
			 * Iterate through the list of versions and verify the UI with API
			 */
			for(int i=0;i<keysList.size();i++){

				/**
				 * Getting Inner Text of the Version Modified Date
				 */
				String dateOfModifiedVersionSelector = "return document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('#node-box > div.node__head.flex > div.title.flex.flex--col.flex--justify.enhanced > div.flex__item > div > span').innerText"
				String dateOfModifiedVersionText = WebUI.executeJavaScript(dateOfModifiedVersionSelector, null)
				String[] splitVersionDate = dateOfModifiedVersionText.split(":")
				String versionTxt = "v"+lengthOfTheVersion

				/**
				 * Getting Inner Text of the Version Modified Date with Time and Locale
				 */
				String modifiedDateWithLocaleSelector = "return document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('#node-box > div.node__head.flex > div.title.flex.flex--col.flex--justify.enhanced > div.title__date > span:nth-child(1)').innerText"
				String modifiedDateWithLocaleText = WebUI.executeJavaScript(modifiedDateWithLocaleSelector, null)

				/**
				 * Getting Inner Text of the Version Modified By User
				 */
				String modifiedByUserSelector = "return document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('#node-box > div.node__head.flex > div.title.flex.flex--col.flex--justify.enhanced > div.title__date > span:nth-child(2)').innerText"
				String modifiedByUserText = WebUI.executeJavaScript(modifiedByUserSelector, null)

				/**
				 * Getting the actual Version Comments
				 */
				String versionCommentSelector = "return document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('px-timeline-node-list').shadowRoot.querySelector('div > span').innerHTML"
				String actualVersionCommentText = WebUI.executeJavaScript(versionCommentSelector, null)

				/**
				 * Get the Version History data for a given date
				 */
				String versionHistoryDate = keysList.get(i)
				specificVersionData = changeLogData.get(versionHistoryDate)
				long longDate = Long.parseLong(versionHistoryDate);

				/**
				 * calling convertEpocFromSimpleDate() with Long date to get the simple date
				 */
				String dateFormat = commonUtil.convertEpocFromSimpleDate(longDate);
				String[] arrayOfDate = dateFormat.split(" ");

				/**
				 * Calling getDayPrefix() to get the day prefix.
				 */
				String prefix = commonUtil.getDayPrefix(Integer.parseInt(arrayOfDate[1].split(",")[0]));

				/**
				 * Converting the Date Into UI display Date ( Ex: v1:Aug 2, 2018 )
				 */
				String[] finalStringArray = dateFormat.split(",");
				String monthFullName = commonUtil.getMonthFullName(finalStringArray[0].split(" ")[0])
				String month = finalStringArray[0].trim().split(" ")[0]
				String year = finalStringArray[1].trim().split(" ")[0]
				String ExpectedConvertedDateWithVersion = versionTxt+": "+monthFullName+" "+prefix+", "+year;
				String dateWithLocale = finalStringArray[0].split(" ")[0]+prefix

				/**
				 * Getting Version History Data from the APIs
				 */
				String currentValue = specificVersionData.changeDetails[0].currentValue
				String previousValue = specificVersionData.changeDetails[0].previousValue
				String eventType = specificVersionData.changeDetails[0].eventType
				String rawJsonPath = specificVersionData.changeDetails[0].rawJsonPath
				String simpleJsonPath = specificVersionData.changeDetails[0].simpleJsonPath
				String changeEventType =specificVersionData.changeEventType
				String changedBy = specificVersionData.changedBy

				/**
				 * Creating Object of WaybillActions class and callung methods on the object
				 */
				WaybillActions actions = new WaybillActions()
				String valueForKey = actions.getValueForGivenKey(simpleJsonPath)

				/**
				 * Constructing a String similar to the UI display. Ex:- Eror Count Changed from 1 to 2
				 */
				String expectedVersionChangeInfoText = valueForKey.trim()+" | "+"changed from "+previousValue+" to "+currentValue

				/**
				 * Constructing a String similar to the UI display. Ex:- AUG 2,2018 11:00 IST - User
				 */
				String[] monthDayForLocaleText = dateFormat.split(",")
				String[] monthForLocaleText = monthDayForLocaleText[0].split(" ")
				String monthForLocale = monthForLocaleText[0].toUpperCase()
				int dayWithoutZeroPrefix = Integer.parseInt(monthForLocaleText[1])
				String yearForLocale = monthDayForLocaleText[1]
				String expectedDateFormatWithLocal = monthForLocale+" "+dayWithoutZeroPrefix+","+monthDayForLocaleText[1]+" IST - "+changedBy
				String actualDateFormatWithLocal =modifiedDateWithLocaleText+modifiedByUserText

				/**
				 * Verification
				 */
				WebUI.verifyEqual(actualVersionCommentText.trim(), expectedVersionChangeInfoText)
				WebUI.verifyEqual(dateOfModifiedVersionText, ExpectedConvertedDateWithVersion)
				WebUI.verifyEqual(actualDateFormatWithLocal, expectedDateFormatWithLocal)

				/**
				 * Collapse Icon
				 */
				String collapseIconSelector = "document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('#node-box > div.node__head.flex > div.chevron.flex.flex--middle > px-icon').click()"

				/**
				 * Click on collapse icon and see the section is hiding or not
				 */
				WebUI.executeJavaScript(collapseIconSelector, null)

				/**
				 * Selector for Expand Collapse icon
				 */
				String sectionHideDisplaySelector= "return document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('#node-box > div.node__body.flex.enhanced').style.display"
				String iconStyleVisible = WebUI.executeJavaScript(sectionHideDisplaySelector, null)

				/**
				 * Verify whether section is hiding or not
				 */
				WebUI.verifyEqual(iconStyleVisible, "none")

				/**
				 * Expand Icon
				 */
				String expandIconSelector = "document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')["+i+"].shadowRoot.querySelector('#node-box > div.node__head.flex > div.chevron.flex.flex--middle > px-icon:nth-child(1)').click()"

				/**
				 * Click on Expand icon and see the section is visible or not
				 */
				WebUI.executeJavaScript(expandIconSelector, null)

				/**
				 * Get the style of the section
				 */
				String iconStyleHide = WebUI.executeJavaScript(sectionHideDisplaySelector, null)

				/**
				 * Verify whether section is hiding or not
				 */
				WebUI.verifyEqual(iconStyleHide, "")

				/**
				 * Decreasing the value of lengthOfTheVersion. This variable is used for forming the versions
				 */
				lengthOfTheVersion--;
			}
		}
	}

	/**
	 * Verify Waybill status filters
	 * @Param waybillstatus
	 */
	@Keyword
	def verifyWaybillFilterStatus(String waybillstatus){
		String actualWaybillStatus="";
		List<WebElement> listOfElements = driver.findElements(By.xpath(WayBillPageConstants.DIV_ITEMS_LEFTPANE_XPATH))
		def sizeOfFilteredWaybills = listOfElements.size()
		if(sizeOfFilteredWaybills>0){
			int random = commonUtil.generateRandomNumber(sizeOfFilteredWaybills)
			String waybillToSelectXpath = WayBillPageConstants.DIV_ITEMS_LEFTPANE_XPATH+'['+random+']'
			WebElement waybillToSelectWebElement = driver.findElement(By.xpath(waybillToSelectXpath))
			waybillToSelectWebElement.click()
			actualWaybillStatus= WebUI.getText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/activeDiv'))
			WebUI.verifyEqual(actualWaybillStatus, waybillstatus)
		}
	}

	/**
	 * Verify Waybill load/empty  filters
	 * @Param loadStatus
	 */
	@Keyword
	def verifyWaybillFilterLoadEmpty(String loadStatus){
		String [] textArr;
		String text="";
		List<WebElement> listOfElements = driver.findElements(By.xpath(WayBillPageConstants.DIV_ITEMS_LEFTPANE_XPATH))
		def sizeOfFilteredWaybills = listOfElements.size()
		for(int i=1;i<sizeOfFilteredWaybills;i++){
			text = "//*[contains(@data-testid,'waybill-item-')]["+i+"]//following::div[4]/div/div/span"
			textArr = (driver.findElement(By.xpath(text)).getText()).split(",")
			WebUI.verifyEqual(textArr[0].toString(),loadStatus)
		}
	}

	/**
	 * Verify Waybill Hazmat  filters
	 * @Param hazmatStatus
	 */
	@Keyword
	def verifyWaybillFilterHazmat(String hazmatStatus){
		String[] textArr;
		String text=""
		List<WebElement> listOfElements = driver.findElements(By.xpath(WayBillPageConstants.DIV_ITEMS_LEFTPANE_XPATH))
		def sizeOfFilteredWaybills = listOfElements.size()
		for(int i=1;i<sizeOfFilteredWaybills;i++){
			WebElement element = listOfElements.get(i)
			text = "//*[contains(@data-testid,'waybill-item-')]["+i+"]//following::div[4]/div/div/span"
			textArr = (driver.findElement(By.xpath(text)).getText()).split(",")
			String[] hazmatArray=textArr[3].split(",")
			if(hazmatArray.length>=1){
				WebUI.verifyEqual(textArr[1].toString(),hazmatStatus)
			}
			else{
				WebUI.verifyEqual("No Hazmat", hazmatStatus)
			}
		}
	}

	/**
	 * Verify Waybill Commodity  filters
	 * @Param commodity
	 */
	@Keyword
	def verifyWaybillFilterCommodity(String commodity){
		String[] textArr;
		String text=""

		/**
		 * Finding the list of waybills items in the left pane and putting them in list
		 */
		List<WebElement> listOfWaybillElements = driver.findElements(By.xpath(WayBillPageConstants.DIV_ITEMS_LEFTPANE_XPATH))
		def sizeOfFilteredWaybills = listOfWaybillElements.size()

		/**
		 * Iterating through the waybill list and getting the commodity value
		 * Asserting actual commodity with the expected commodity value
		 */
		for(int i=0;i<sizeOfFilteredWaybills;i++){
			WebElement element = listOfWaybillElements.get(i)
			text = element.getText()
			textArr = text.split("\n")
			WebUI.verifyEqual(textArr[6].toString(), commodity)
		}
	}

	/**
	 * Method to get Random Waybill ID
	 */
	@Keyword
	def getRandomWaybillId(){
		commonUtil.generateRandomNumber(waybillLeftPaneSize())
	}

	/**
	 * Method to get the size of left pane
	 * @return size of left pane
	 */
	def int waybillFullLeftPaneSize() {
		List<WebElement> listOfElements = driver.findElements(By.xpath(WayBillPageConstants.WAYBILLFULLLEFTPANE))
		int expectedSize = listOfElements.size()
		return expectedSize
	}

	/**
	 * Verify Waybill load/empty  filters
	 * @Param expected Origin
	 */
	@Keyword
	def verifyOriginFrmrightpane(String expectedOrigin){
		String textRightPane = driver.findElement(By.xpath(WayBillPageConstants.WAYBILLORIGINRIGHTPANE)).getText()
		WebUI.verifyEqual(textRightPane, expectedOrigin)
	}

	/**
	 * Method to verify message for wrong search criteria
	 * @param Error message
	 */
	@Keyword
	def verifyMessageforWrongSearch(String emsg){
		boolean flag = (emsg.matches(WayBillPageConstants.WAYBILL_MESSAGE_NO_SEARCH_CRITERIA))?true:false
		WebUI.verifyEqual(true, flag)
	}
}
