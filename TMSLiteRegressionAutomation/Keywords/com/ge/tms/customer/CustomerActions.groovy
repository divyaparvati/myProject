package com.ge.tms.customer

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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.ge.tms.util.CommonUtilities
import com.ge.tms.commonactions.CommonClickEvents

import internal.GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By

/**
 * JsonSlurper to parse the response
 */
import groovy.json.JsonSlurper;
import com.ge.tms.constants.CustomerPageConstants
public class CustomerActions {



	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities utils= new CommonUtilities()

	/**
	 * CommonUtilities class instance
	 */
	CommonClickEvents commonClick= new CommonClickEvents()

	/**
	 * variables to hold directory path
	 */
	def userDirPath = System.getProperty("user.dir")
	def path = userDirPath+'/Data Files/addCustomer.json'
	String customerId = "";

	/**
	 * Static variable to hold the customer id
	 */
	public static String customerID

	/**
	 * Static variable to hold the alternateCode
	 */
	public static String alternativeCode

	/**
	 * Static variable to hold the attention
	 */
	public static String attentionText

	/**
	 * Static variable to hold the address 2
	 */
	public static String addressTwoText

	/**
	 * Static variable to hold the milePost
	 */
	public static String milePostText

	/**
	 *  WebDriver instance
	 */
	WebDriver driver = DriverFactory.getWebDriver()
	/**
	 * Add Customer form. Fill the form values with the static json data.
	 * @Param customerType
	 */
	def addCustomer(String customerType){

		/**
		 * Loading the data from the static json file into data object
		 * If customer type None means not selecting any customer type. This will be used for negative scenario
		 */
		def jsonSlurper = new JsonSlurper()
		def dataFull = jsonSlurper.parse(new File(path))
		def data;
		if(customerType.equals(CustomerPageConstants.BILLING)){
			data = dataFull.Billing
		}
		else if(customerType.equals(CustomerPageConstants.INACTIVE)){
			data = dataFull.inactive
		}
		else if(customerType.equals(CustomerPageConstants.TRANSPORTATION)){
			data = dataFull.Transportaion
		}
		else if(customerType.equals(CustomerPageConstants.MANDATORY)){
			data = dataFull.Mandatory
		}
		else{
			data = dataFull.Billing
		}

		/**
		 *
		 * Enter Test data in customer form. Setting text fields
		 *
		 */
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerName'), data.name)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'),setUniqueValueForCustomerId())
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey'), data.addressTypeKey)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AltCode'), data.alternateCode)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/Address1'), data.address.streetAddressLine1)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey2'), data.address.streetAddressLine2)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/city'), data.address.city)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/ZipCode'), data.address.zipCode)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/Attention'), data.attention)
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/MilePost'), data.milepost)

		/**
		 *
		 * Setting Customer Type value from drop down based on customer type.
		 *
		 */
		if(customerType.equals(CustomerPageConstants.BILLING)){
			WebUI.executeJavaScript(CustomerPageConstants.BILLING_SELECTOR,null)
		}
		else if(customerType.equals(CustomerPageConstants.INACTIVE)){
			WebUI.executeJavaScript(CustomerPageConstants.INACTIVE_SELECTOR,null)
		}
		else if(customerType.equals(CustomerPageConstants.TRANSPORTATION)){
			WebUI.executeJavaScript(CustomerPageConstants.TRANSPORTATION_SELECTOR,null)
		}
		else if(customerType.equals(CustomerPageConstants.MANDATORY)){
			WebUI.executeJavaScript(CustomerPageConstants.BILLING_SELECTOR,null)
		}

		/**
		 * Setting Up Other drop down values
		 */
		WebUI.executeJavaScript(CustomerPageConstants.COUNTRY_SELECTOR, null)
		WebUI.executeJavaScript(CustomerPageConstants.STATE_SELECTOR, null)
		selectStationWithZone()
		selectStationWithTrack()
		/**
		 * Setting Customer ID for further use
		 */
		customerId = data.customerId;
	}

	/**
	 * Method to randomly select station which have a track
	 */
	def selectStationWithTrack(){
		WebUI.executeJavaScript(CustomerPageConstants.CLICK_DEFAULT_STATION, null)
		int stationLength = WebUI.executeJavaScript(CustomerPageConstants.DEFAULT_STATION_LENGTH, null)
		for(int i=1;i<stationLength;i++){
			WebUI.executeJavaScript('document.querySelector(\'#default-station\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ i +')\').click()', null)
			int trackLength = WebUI.executeJavaScript(CustomerPageConstants.DEFAULT_TRACK_LENGTH, null)
			if (trackLength!=0){
				int valueToBeSelected = utils.generateRandomNumberExlusiveZero(trackLength)
				WebUI.executeJavaScript('document.querySelector(\'#default-track\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
				break
			}
		}
	}

	/**
	 * Method to randomly select station which have a zone
	 */
	def selectStationWithZone(){
		WebUI.executeJavaScript(CustomerPageConstants.CLICK_ONLINE_STATION, null)
		int stationLength = WebUI.executeJavaScript(CustomerPageConstants.ONLINE_STATION_LENGTH, null)
		for(int i=1;i<stationLength;i++){
			WebUI.executeJavaScript('document.querySelector(\'#online-station\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ i +')\').click()', null)
			int zoneLength = WebUI.executeJavaScript(CustomerPageConstants.ZONE_LENGTH, null)
			if (zoneLength!=0){
				int valueToBeSelected = utils.generateRandomNumberExlusiveZero(zoneLength)
				WebUI.executeJavaScript('document.querySelector(\'#zone\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child('+ valueToBeSelected +')\').click()', null)
				break
			}
		}
	}

	@Keyword
	def selectNewlyCreatedCustomer(String customerType){
		/**
		 * Loading the data from the static json file into data object
		 * If customer type None means not selecting any customer type. This will be used for negative scenario
		 */
		def jsonSlurper = new JsonSlurper()
		def dataFull = jsonSlurper.parse(new File(path))
		def data;
		String expectedCustomerId;
		if(customerType.equals(CustomerPageConstants.BILLING)){
			data = dataFull.Billing
		}
		else if(customerType.equals(CustomerPageConstants.INACTIVE)){
			data = dataFull.inactive
		}
		else if(customerType.equals(CustomerPageConstants.TRANSPORTATION)){
			data = dataFull.Transportaion
		}
		else if(customerType.equals(CustomerPageConstants.MANDATORY)){
			data = dataFull.Mandatory
		}
		else{
			data = dataFull.Billing
		}
		boolean flag = false
		expectedCustomerId = data.customerId
		String actualCustomerNumber="";
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@data-testid='customer-summary']"))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerAddressKeyList = new ArrayList<String>();
		for(int i=0;i<expectedCustomersSize;i++){
			WebElement ele = listOfElements.get(i);
			String[] custId = ele.getText().split("-");
			String[] customerNumberArray = custId[0].split("\n")
			actualCustomerNumber = customerNumberArray[1].toString()
			if(actualCustomerNumber.equals(expectedCustomerId)){
				ele.click()
				flag=true;
			}
		}
	}

	/**
	 * Method to set Customer id
	 * @return customerID
	 */
	def setUniqueValueForCustomerId(){
		int	numberTobeGenerated = utils.generateRandomNumber(4)
		customerID = utils.generateRandomAlphabets(3)+ numberTobeGenerated
		return customerID
	}

	/**
	 * Method to get No of Equipments of the leftpane
	 * @return expectedSize
	 */
	def int equipmentFullLeftPaneSize() {
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.FULL_LEFT_PANE_ELEMENT_SELECTOR))
		int expectedSize = listOfElements.size()
		return expectedSize
	}

	/**
	 *   Method to randomly select a CustomerID
	 *   @return customerID
	 */
	@Keyword
	def generateRandomCustomerID(){
		int noOfCustomers = equipmentFullLeftPaneSize()
		String customerIDAddrsType = driver.findElement(By.xpath(CustomerPageConstants.LEFTPANE_CUSTOMER_ID+utils.generateRandomNumber(noOfCustomers)+']/div[1]/p')).getText()
		String[] splitText=customerIDAddrsType.split('-')
		customerID = splitText[0]
		return customerID
	}

	/**
	 *   Method to set customerID to Search Pane
	 */
	@Keyword
	def setcustomerIDToSearchPane(){
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/customerSearchField'), customerID)
		WebUI.delay(2)
	}

	/**
	 *   Method to clear search pane
	 */
	@Keyword
	def clearcustomerIDToSearchPane(){
		WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/customerSearchField'))
		WebUI.delay(2)
	}

	/**
	 *   edit customer steps
	 */
	@Keyword
	def editCustomer(){
		generateRandomCustomerID()
		setcustomerIDToSearchPane()
		commonClick.clickOnCustomerActionsDropdown()
		WebUI.executeJavaScript(CustomerPageConstants.EDIT_CUSTOMER_SELECTOR, null)
		WebUI.delay(2)
		setAlternativeCode()
		setAttention()
		setAddressTwo()
		setMilePost()
		clickSaveButton()
		clearcustomerIDToSearchPane()
		setcustomerIDToSearchPane()
	}

	/**
	 *   Method to set random text to Alternative Code
	 */
	@Keyword
	def setAlternativeCode(){
		alternativeCode = utils.generateRandomString(5)
		WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AltCode'))
		String alternateCode = WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AltCode'),alternativeCode)
	}

	/**
	 *   Method to set random text to Attention
	 */
	@Keyword
	def setAttention(){
		attentionText = utils.generateRandomString(5)
		WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/Attention'))
		String attention = WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/Attention'),attentionText)
	}

	/**
	 *   Method to set random text to Address 2
	 */
	@Keyword
	def setAddressTwo(){
		addressTwoText = utils.generateRandomString(5)
		WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey2'))
		String addressTwo = WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey2'),addressTwoText)
	}

	/**
	 *   Method to set random text to Address 2
	 */
	@Keyword
	def setMilePost(){
		milePostText = utils.generateRandomNumber(4)
		WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/MilePost'))
		String milePost = WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/MilePost'),milePostText)
	}

	/**
	 *   Method to click save button
	 */
	@Keyword
	def clickSaveButton(){
		WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/SaveButton'))
	}

	/**
	 *   generate random milepost
	 *   @return randomMilePost
	 */
	@Keyword
	def randomMilePost(){
		String randomMilePost = utils.generateRandomNumberWithLimit(1000,2000).toString()
		return 	randomMilePost
	}
}
