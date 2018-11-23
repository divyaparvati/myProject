package com.ge.tms.util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import java.text.ParsePosition;

import com.ge.tms.constants.CommonPageConstants
import com.ge.tms.constants.CustomerPageConstants
import com.ge.tms.equipment.EquipmentVerification
import com.google.api.client.json.Json
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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import groovy.json.JsonSlurper

import org.openqa.selenium.WebElement
import org.stringtemplate.v4.compiler.CodeGenerator.region_return
import org.openqa.selenium.WebDriver
import org.junit.After
import org.openqa.selenium.By as By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory


import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import org.apache.commons.lang3.RandomStringUtils
import java.util.ArrayList
import java.text.Format
import java.text.SimpleDateFormat;
import java.text.DateFormat;


/**
 *  Utility Class for the tmslite katalon project
 */
class CommonUtilities {

	// Variable to define the path of DB.json

	def str = System.getProperty("user.dir")
	def path = str+'/Data Files/db.json'

	/**
	 * Variable to hold sender ID
	 */
	String senderID

	/**
	 * Variable to hold waybill number of the sender id
	 */
	static String wayBillNumberAttachingSenderID

	/**
	 * send request for get all waybill details
	 */
	ResponseObject getAllWayBillNumbersObj = WS.sendRequest(findTestObject('Object Repository/WayBillServices/GETALLWAYBILL'))

	/**
	 * send request for get all Equipment details
	 */
	ResponseObject getAllEquipmentIdObj = WS.sendRequest(findTestObject('Object Repository/EquipmentService/GETEQUIPMENT'))
	/**
	 * Send request for get all rule 15 offers
	 */
	ResponseObject getAllRule15OffersObj = WS.sendRequest(findTestObject('Object Repository/Rule15Services/GETALL-RULE15_OFFERS'))

	/**
	 * send request for get all AAR Car Type details
	 */
	ResponseObject getAllAarCarTypeObj = WS.sendRequest(findTestObject('Object Repository/EquipmentServices/GetAllAarCarType'))

	/**
	 * Creating Object for Logger class to log the text to console
	 */
	KeywordLogger log = new KeywordLogger()

	/**
	 *	Method to Login TMS Application
	 */
	@Keyword
	def login(){
		log.logInfo("Launching Browser Start")
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.URL)
		WebUI.click(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_TMSlite/button_Login'))
		WebUI.delay(10)
		WebUI.setText(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_Sign In with Auth0/input_username'),
				findTestData('LoginData').getValue(1, 1))
		WebUI.setText(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_Sign In with Auth0/input_password'),
				findTestData('LoginData').getValue(2, 1))
		WebUI.click(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_Sign In with Auth0/span_Log In'))
		WebUI.delay(5)
		WebUI.verifyElementPresent(findTestObject('Object Repository/F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_TMSlite/HelloText'),
				10)
		WebUI.maximizeWindow()
		log.logInfo("Launching Browser Ends")
	}

	/**
	 * Method to Logout TMS Application
	 *
	 */
	@Keyword
	def logout(){
		WebUI.executeJavaScript("document.querySelector('px-app-nav').shadowRoot.querySelector('#items > px-app-nav-item:nth-child(2)').click()')", null)
		WebUI.click(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_TMSlite/button_Logout'))
		log.logInfo("Logged out")
	}

	/**
	 * Method to get the waybill size
	 * @return noOfWaybills
	 */
	@Keyword
	def getWaybillSize(){
		def jsonSlurper = new JsonSlurper()
		def data = jsonSlurper.parse(new File(path))
		log.logInfo("Waybills Size: "+data.waybills.size())
		return data.waybills.size();
	}

	/**
	 * Method to get the selected waybill from API
	 * @param wayBillId
	 * @return selected Waybill Data
	 */
	@Keyword
	def getSelectedWaybillAPI(String wayBillId){
		String resText = getAllWayBillNumbersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfWaybills = response.size();
		for(int i=0;i<noOfWaybills;i++){
			String billId = response[i].referenceInformation.waybillNumber.toString()
			if(wayBillId.equals(billId)){
				return response[i];
			}
		}
	}

	/**
	 * Method to get the first car ID from API
	 * @return firstCarIDInAPI
	 */
	@Keyword
	def getFirstCarIDFromAPI(){
		String resText = getAllWayBillNumbersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		String firstCarIDInAPI = response[0].equipmentDetails.carId.toString()
		return firstCarIDInAPI;
	}

	/**
	 * Method to get the first waybill number from API
	 * @return firstWaybillNumberInAPI
	 */
	@Keyword
	def getFirstWaybillNumberFromAPI(){
		String resText = getAllWayBillNumbersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		String firstWaybillNumberInAPI = response[0].referenceInformation.waybillNumber.toString()
		return firstWaybillNumberInAPI;
	}

	/**
	 * Method to get the first waybill with a sender ID from API
	 * @return senderID
	 */
	@Keyword
	def getFirstSenderFromAPI(){
		String resText = getAllWayBillNumbersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfWaybills= response.size()
		for(int i=0;i<noOfWaybills;i++){
			if (response[i].referenceInformation.senderId != null && !response[i].referenceInformation.senderId.equals("") ){
				senderID = response[i].referenceInformation.senderId.toString();
				wayBillNumberAttachingSenderID = response[i].referenceInformation.waybillNumber.toString();
				break;
			}
		}
		return senderID;
	}

	/**
	 * Method to return waybill number attached with sender ID
	 * @return wayBillNumberAttachingSenderID
	 */
	@Keyword
	def getWaybillAttachedToSenderID(){
		return wayBillNumberAttachingSenderID
	}

	/**
	 * Method to get the Shipper from the first waybill, from API
	 * @return firstShipperInAPI
	 */
	@Keyword
	def getFirstShipperFromAPI(){
		String resText = getAllWayBillNumbersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)

		String firstShipperInAPI =response[0].customers.shipper.companyName.toString()
		return firstShipperInAPI;
	}

	/**
	 * Method to get the consignee from the first waybill, from API
	 * @return firstConsigneeInAPI
	 */
	@Keyword
	def getFirstConsigneeFromAPI(){
		String resText = getAllWayBillNumbersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		String firstConsigneeInAPI =response[0].customers.consignee.companyName.toString()
		return firstConsigneeInAPI;
	}

	/**
	 * Method to get the customers size
	 * @return noOfCustomers
	 */
	@Keyword
	def getCustomerSize(){
		def jsonSlurper = new JsonSlurper()
		def data = jsonSlurper.parse(new File(path))
		log.logInfo("Customer Size: "+data.customers.size())
		return data.customers.size();
	}

	/**
	 * Method to get the selected customer
	 * @param customer
	 * @return Selected Customer Data
	 */
	@Keyword
	def getSelectedCustomer(def customerName){
		def jsonSlurper = new JsonSlurper()
		def data = jsonSlurper.parse(new File(path))
		def noOfCustomers = data.customers.size();
		for(int i=0;i<noOfCustomers;i++){
			def patName = data.customers[i].name.split("\n")
			def value = patName[0];
			if(customerName.equals(patName[0])){
				log.logInfo("Selected Customer Data: "+data.customers[i])
				return data.customers[i];
			}
		}
	}

	/**
	 * Method to verify the default selected Item background
	 * TestObject of the Component
	 */
	@Keyword
	def verifyDefualtSelectedBackground(TestObject testObject){
		String actualBgColor = WebUI.getCSSValue(testObject, 'background-color')
		String expectedColor="rgba(9, 129, 156, 1)";
		WebUI.verifyEqual(actualBgColor, expectedColor)
	}

	/**
	 * Method to Generate Random number between O to Size of List
	 * size of the List in the left pane
	 */
	def generateRandomNumber(def size){
		Random rand = new Random();
		return rand.nextInt(size)+1;
	}

	/**
	 * Method to Generate Random number with a Min and Max value
	 *@param minimum range passed
	 *@param maximum range passed
	 */
	def generateRandomNumberWithLimit(def min , def max){
		Random rand = new Random();
		return rand.nextInt(max)+min;
	}

	/**
	 * Method to generate random number between 0 to the size list for Railroad configurations
	 */
	def generateRandomNumberForRailroad(def size){
		Random rand = new Random();
		return rand.nextInt(size);
	}

	/**
	 * Method to generate random number between 0 to the size list for Rule15 configurations
	 */
	def generateRandomNumberLeftPane(def size){
		Random rand = new Random();
		return rand.nextInt(size);
	}

	/**
	 * Method to generate random number between 1 to the size list
	 */
	def generateRandomNumberExlusiveZero(def size){
		Random rand = new Random();
		int ran = rand.nextInt(size);
		if (ran==0){
			ran= ran+1;
		}
		return ran
	}

	/**
	 * Method to verify sorted
	 * @param listOfStrings
	 */
	def sortOrder(List<String> listOfStrings){
		boolean isSorted = listOfStrings.sort(false)
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the whether Save button disabled or not
	 * @param TestObject of the Component
	 */
	def verifySaveButtonDisabled(TestObject testObject){
		String actualBtnState = WebUI.getAttribute(testObject, "class")
		boolean flag = (actualBtnState.contains(CommonPageConstants.SAVE_BUTTON_DISABLED_CLASS))?true:false
		WebUI.verifyEqual(flag, true)
	}

	/**
	 * Method to verify the whether Save button Enabled or not
	 * @param TestObject of the Component
	 */
	def verifySaveButtonEnabled(TestObject testObject){
		String actualBtnState = WebUI.getAttribute(testObject, "class")
		boolean flag = (actualBtnState.contains(CommonPageConstants.SAVE_BUTTON_ENABLE_CLASS))?true:false
		WebUI.verifyEqual(flag, true)
	}

	/**
	 *  Method to generate random string
	 *  return string generated
	 */
	def String generateRandomString(def size){
		return RandomStringUtils.randomAlphanumeric(size).toUpperCase();
	}

	/**
	 * Method to generate random strings only 
	 * not Alphanumberic
	 */
	def String generateRandomAlphabets(def size){
		return RandomStringUtils.randomAlphabetic(size)
	}

	/**
	 * Method to convert EPOC time to SimpleDateFormat
	 * @param epocDay
	 */
	def convertEpocFromSimpleDate(Long epocDay){
		Date date = new Date(epocDay);
		String dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm").format(date);
		return dateFormat;
	}

	/**
	 * Method to convert date and Time to date only
	 * @param dateAndTime
	 * @return Date only
	 */
	def convertDate(String dateAndTime){
		Date date = new Date();
		Format formatter = new SimpleDateFormat("MM/dd/yyyy");
		String dateOnly = formatter.format(date);
		return dateOnly;
	}

	/**
	 * Method to convert String date to date only
	 * @param dateAndTime
	 * @return dateStringInNewFormat
	 */
	def convertStringToDate(String dateAndTime){
		if(dateAndTime.contains("-")){
			def convertTime = dateAndTime.substring(10)
			SimpleDateFormat originalFormatter = new SimpleDateFormat ("yyyy-MM-dd");
			SimpleDateFormat newFormatter = new SimpleDateFormat ("MM/dd/yyyy");
			ParsePosition pos = new ParsePosition(0);
			Date dateFromString = originalFormatter.parse(dateAndTime, pos);
			String dateStringInNewFormat = newFormatter.format(dateFromString);
			return dateStringInNewFormat;
		}
		SimpleDateFormat originalFormatter = new SimpleDateFormat ("yyyyMMdd");
		SimpleDateFormat newFormatter = new SimpleDateFormat ("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		Date dateFromString = originalFormatter.parse(dateAndTime, pos);
		String dateStringInNewFormat = newFormatter.format(dateFromString);
		return dateStringInNewFormat;
	}

	/**
	 * Method to get Date Prefix like 1st,2nd,3rd,4th etc..
	 * @param day
	 */
	def getDayPrefix(int day){
		if (day >= 11 && day <= 13) {
			return "th";
		}
		switch (Integer.valueOf(day) % 10) {
			case 1:
				return day+"st";
			case 2:
				return day+"nd";
			case 3:
				return day+"rd";
			default:
				return day+"th";
		}
	}

	/**
	 * Method to get the full name of the month
	 * @param shortNameOfMonth
	 */
	def getMonthFullName(String shortNameOfMonth){
		switch (shortNameOfMonth) {
			case "Jan":
				return "January";
			case "Feb":
				return "February";
			case "Mar":
				return "March";
			case "Apr":
				return "April";
			case "May":
				return "May";
			case "Jun":
				return "June";
			case "Jul":
				return "July";
			case "Aug":
				return "August";
			case "Sep":
				return "September";
			case "Oct":
				return "October";
			case "Nov":
				return "November";
			case "Dec":
				return "December";
			default:
				return "January";
		}
	}

	/**
	 * Method to Match Array List
	 * @return Boolean
	 */
	def boolean equalLists(List<String> a, List<String> b){
		if (a == null && b == null) return true;
		if ((a == null && b!= null) || (a != null && b== null) || (a.size() != b.size()))
		{
			return false;
		}
		Collections.sort(a);
		Collections.sort(b);
		return a.equals(b);
	}
}
