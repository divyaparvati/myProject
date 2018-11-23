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
import com.ge.tms.customer.CustomerActions

import internal.GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.openqa.selenium.Keys as Keys
import org.junit.After as After
import org.openqa.selenium.By as By

import java.lang.Long

/**
 * Adding below import from Guava to get the pre defined Sort ability of the List
 */
import com.google.common.collect.Ordering;

/**
 * Holds the Response from Web Service
 */
import com.kms.katalon.core.testobject.ResponseObject;

/**
 * JsonSlurper to parse the response
 */
import groovy.json.JsonSlurper;
import com.ge.tms.constants.CustomerPageConstants;
import com.ge.tms.util.CommonUtilities

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.Long;
/**
 *
 * Customer Verification Class
 */
public class CustomerVerification {
	/**
	 * variables to hold directory path
	 */
	def userDirPath = System.getProperty("user.dir")
	def path = userDirPath+'/Data Files/addCustomer.json'

	def actualHeader = "Customer Management";
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities commonUtil= new CommonUtilities()

	/**
	 * CustomerActions class instance
	 */
	CustomerActions action= new CustomerActions()

	/**
	 * send request for get all Customers
	 */
	ResponseObject getAllCustomersObj = WS.sendRequest(findTestObject('Object Repository/customerServices/GET-AllCustomersData'))

	/**
	 * Method to verify the customer size in the left pane
	 * @param resObj, actualCustomersSize
	 */
	@Keyword
	def verifyCustomerSize(ResponseObject resObj){
		String resText = resObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def customerSizeFromServiceResponse = response.size();
		List<WebElement> listOfElements = driver.findElements(By.xpath('//*[@data-testid="customer-summary"]'))
		def expectedCustomersSize = listOfElements.size()
		WebUI.verifyEqual(customerSizeFromServiceResponse, expectedCustomersSize)
	}

	/**
	 * Method to verify the header text in the customer page
	 */
	@Keyword
	def verifyCustomerPageHeader(){
		def pageHeader = WebUI.getText(findTestObject('F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/h1_Manage Customers'))
		WebUI.verifyEqual(actualHeader, pageHeader)
	}

	/**
	 * Method to verify the header text in the right pane
	 */
	@Keyword
	def verifyCustomerRightPageHeader(ResponseObject obj){
		/**
		 * Verify Name
		 */
		String resText = obj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def noOfCustomers = response.size();
		def patName = response.name
		String[] headerText = WebUI.getText(findTestObject('F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/div_Customer1')).split("\n")
		WebUI.verifyEqual(headerText[0], patName[0])
	}

	/**
	 * Method to verify the populated Customer Values in the right pane
	 * @param expectedCustomerId
	 */
	@Keyword
	def verifyCustomerDetailsInRightPane(String expectedCustomerId){
		def actualCustmerName
		def actualPdId
		def actualPatType
		def actualAlternateCode
		def actualShortName
		def actualParent
		def actualAddress
		def actualCity
		def actualState
		def actualCountry
		def actualZip
		def actualAttention
		def actualPhone
		def actualFax
		def actualOnlineStationZone
		def actualMilePost
		def actualServingStation
		def actualDefaultStationTrack
		/**
		 *  Variable for Customer datails page
		 */
		String resText = getAllCustomersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def customerResponseValue  = slupper.parseText(resText)
		def noOfCustomers = customerResponseValue.size()
		for(int i=0;i<noOfCustomers;i++){
			String customerId = customerResponseValue[i].customerId.toString()
			if(customerId.equals(expectedCustomerId)){
				actualCustmerName = customerResponseValue[i].name
				actualPdId = customerResponseValue[i].customerId;
				actualPatType = customerResponseValue[i].customerType;
				actualAlternateCode = customerResponseValue[i].alternateCode;
				actualShortName= customerResponseValue[i].abbrevName
				actualParent = customerResponseValue[i].parentCustomerId
				actualAddress = customerResponseValue[i].address.streetAddressLine1+" - "+customerResponseValue[i].address.streetAddressLine2
				actualCity = customerResponseValue[i].address.city
				actualState = customerResponseValue[i].address.state
				actualCountry = customerResponseValue[i].address.country
				actualZip = customerResponseValue[i].address.zipCode
				actualAttention = customerResponseValue[i].attention
				actualPhone = customerResponseValue[i].phone.number
				actualFax = customerResponseValue[i].fax.number
				actualOnlineStationZone = customerResponseValue[i].onlineStationId+" - "+customerResponseValue[i].zoneId
				actualMilePost = customerResponseValue[i].milepost
				actualServingStation = customerResponseValue[i].servingStationId
				actualDefaultStationTrack = customerResponseValue[i].defaultStationId+" - "+customerResponseValue[i].defaultTrackId
			}
		}
		/**
		 *  If no data then Display as NONE, So converting null value to NONE
		 */
		def tempPatType = (actualPatType == null) ? "--" : actualPatType;
		if(tempPatType.equals("billing")){
			tempPatType="Billing"
		}
		else if(tempPatType.equals("transportation")){
			tempPatType = "Transportation"
		}
		else if(tempPatType.equals("inactive")){
			tempPatType = "Inactive"
		}
		def tempAlternateCode = (actualAlternateCode == null) ? "--" : actualAlternateCode;
		def tempShortName = (actualShortName == null) ? "--" : actualShortName;
		def tempParent = (actualParent == null) ? "--" : actualParent;
		def tempAddress = (actualAddress == " - ") ? "--" : actualAddress;
		def tempCity = (actualCity == null) ? "--" : actualCity;
		def tempState = (actualState == null) ? "--" : actualState;
		def tempCountry = (actualCountry == null) ? "--" : actualCountry;
		def tempZip = (actualZip == "") ? "--" : actualZip;
		def tempAttention = (actualAttention == null) ? "--" : actualAttention;
		def tempPhone = (actualPhone == null) ? "--" : actualPhone;
		def tempFax = (actualFax == null) ? "--" : actualFax;
		def tempOnlineStationZone = (actualOnlineStationZone == " - ") ? "--" : actualOnlineStationZone;
		def tempMilePost = (actualMilePost == null) ? "--" : actualMilePost;
		def tempServingStation = (actualServingStation == null) ? "--" : actualServingStation;
		def tempDefaultStationTrack = (actualDefaultStationTrack == "null - null") ? "--" : actualDefaultStationTrack;

		/**
		 *  Expected Type from the UI
		 */
		def expectedCustType = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/type'))
		def expectedAltCode = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/altCode'))
		def expectedShortName = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/shortName'))
		def expectedParent = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/parent'))
		def expectedAddress = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/address'))
		def expectedCity = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/city'))
		def expectedState = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/state'))
		def expectedCountry = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/country'))
		def expectedZip = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/zip'))
		def expectedAttention = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/attention'))
		def expectedPhone = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/phone'))
		def expectedFax = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/fax'))
		def expectedOnlineStationZone = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/stationZone'))
		def expectedMilePost = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/milepost'))
		def expectedServingStation = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/servingStation'))
		def expectedDefaultStationTrack = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/defaultStationTrack'))

		/**
		 * Verifying customer data with the response data.
		 */
		WebUI.verifyEqual(tempPatType,expectedCustType)
		WebUI.verifyEqual(tempAlternateCode,expectedAltCode)
		WebUI.verifyEqual(tempShortName,expectedShortName)
		WebUI.verifyEqual(tempParent,expectedParent)
		WebUI.verifyEqual(tempAddress,expectedAddress)
		WebUI.verifyEqual(tempCity,expectedCity)
		WebUI.verifyEqual(tempState,expectedState)
		WebUI.verifyEqual(tempCountry,expectedCountry)
		WebUI.verifyEqual(tempZip,expectedZip)
		WebUI.verifyEqual(tempAttention,expectedAttention)
		WebUI.verifyEqual(tempPhone,expectedPhone)
		WebUI.verifyEqual(tempFax,expectedFax)
		WebUI.verifyEqual(tempOnlineStationZone,expectedOnlineStationZone)
		WebUI.verifyEqual(tempMilePost,expectedMilePost)
		WebUI.verifyEqual(tempServingStation,expectedServingStation)
		WebUI.verifyEqual(tempDefaultStationTrack,expectedDefaultStationTrack)
	}

	/**
	 * Method to verify the Customer Search with Customer Id
	 * @param searchString
	 */
	@Keyword
	def verifyCustomerSearchWithCustomerId(String searchString){
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		for(WebElement ele : listOfElements){
			String[] text = ele.getText().split("\n");
			String[] custId = text[1].split("-")
			boolean flag = (custId[0].contains(searchString))?true:false
			WebUI.verifyEqual(true, flag)
		}
	}

	/**
	 * Method to verify the Customer Search with Address Type Key
	 * @param searchString
	 */
	@Keyword
	def verifyCustomerSearchWithAddressType(String searchString){
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		for(WebElement ele : listOfElements){
			String[] text = ele.getText().split("\n");
			String[] custId = text[1].split("-")
			WebUI.verifyEqual(custId[1], searchString)
		}
	}

	/**
	 * Method to verify the Customer Search with partial Customer Id
	 * @param partialSearchString
	 */
	@Keyword
	def verifyCustomerSearchWithPartialCustomerId(String partialSearchString){
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		for(WebElement ele : listOfElements){
			String[] text = ele.getText().split("\n");
			String[] custId = text[1].split("-")
			boolean actualFlag = true;
			boolean searchFlag = custId[0].contains(partialSearchString)
			WebUI.verifyEqual(actualFlag, searchFlag)
		}
	}

	/**
	 * Method to verify the Customer Search with partial Address Key
	 * @param partialSearchString
	 */
	@Keyword
	def verifyCustomerSearchWithPartialAddrKey(String partialSearchStrAddKey){
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		for(WebElement ele : listOfElements){
			String[] text = ele.getText().split("\n");
			String[] custId = text[1].split("-")
			boolean actualFlag = true;
			boolean searchFlag = custId[1].contains(partialSearchStrAddKey)
			WebUI.verifyEqual(actualFlag, searchFlag)
		}
	}

	/**
	 * Method to verify the Sort based on Customer ID - Ascending Order.
	 */
	@Keyword
	def verifyCustomerIDSort(){

		WebElement customerListElement = driver.findElement(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		List<WebElement> listOfElements = customerListElement.findElements(By.xpath('.//p'))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerIdList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String[] custId = ele.getText().split("-");
			customerIdList.add(custId[0])
		}
		/**
		 * Using Groovy Ordering Class isOrdered Method to verify sorted list.
		 * This will return boolean true if given list is sorted, else returns false
		 */
		boolean isSorted = Ordering.natural().isOrdered(customerIdList);
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the Sort based on Name - Ascending Order.
	 */
	@Keyword
	def verifyNameSort(){
		WebElement customerListElement = driver.findElement(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		List<WebElement> listOfElements = customerListElement.findElements(By.xpath('.//span'))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerNameList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String customerName = ele.getText();
			customerNameList.add(customerName)
		}
		/**
		 * Using Groovy Ordering Class isOrdered Method to verify sorted list.
		 * This will return boolean true if given list is sorted, else returns false
		 */
		boolean isSorted = Ordering.natural().isOrdered(customerNameList);
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the Sort based on Address Key - Ascending Order.
	 */
	@Keyword
	def verifyAddressKeySort(){
		WebElement customerListElement = driver.findElement(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		List<WebElement> listOfElements = customerListElement.findElements(By.xpath('.//p'))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerAddressKeyList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String[] custId = ele.getText().split("-");
			customerAddressKeyList.add(custId[1])
		}
		/**
		 * Using Groovy Ordering Class isOrdered Method to verify sorted list.
		 * This will return boolean true if given list is sorted, else returns false
		 */
		boolean isSorted = Ordering.natural().isOrdered(customerAddressKeyList);
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the Sort based on Name - Descending order
	 */
	@Keyword
	def verifyNameSortWithDescendingOrder(){
		WebElement customerListElement = driver.findElement(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		List<WebElement> listOfElements = customerListElement.findElements(By.xpath('.//span'))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerNameList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String customerName = ele.getText();
			customerNameList.add(customerName)
		}

		/**
		 * Reversing the List since we need to check the descending order
		 */

		Collections.reverse(customerNameList);

		/**
		 * Using Groovy Ordering Class isOrdered Method to verify sorted list.
		 * This will return boolean true if given list is sorted, else returns false
		 */
		boolean isSorted = Ordering.natural().isOrdered(customerNameList);
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the Sort based on Customer ID - Descending order
	 */
	@Keyword
	def verifyCustomerIDWithDescendingOrder(){
		WebElement customerListElement = driver.findElement(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		List<WebElement> listOfElements = customerListElement.findElements(By.xpath('.//p'))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerIdList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String[] custId = ele.getText().split("-");
			customerIdList.add(custId[0])
		}

		/**
		 * Reversing the List since we need to check the descending order
		 */

		Collections.reverse(customerIdList);
		/**
		 * Using Groovy Ordering Class isOrdered Method to verify sorted list.
		 * This will return boolean true if given list is sorted, else returns false
		 */

		boolean isSorted = Ordering.natural().isOrdered(customerIdList);
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the Sort based on Address Key - Descending order
	 */
	@Keyword
	def verifyAddressKeyWithDescendingOrder(){
		WebElement customerListElement = driver.findElement(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		List<WebElement> listOfElements = customerListElement.findElements(By.xpath('.//p'))
		def expectedCustomersSize = listOfElements.size()
		List<String> customerAddressKeyList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String[] custId = ele.getText().split("-");
			customerAddressKeyList.add(custId[1])
		}

		/**
		 * Reversing the List since we need to check the descending order
		 */
		Collections.reverse(customerAddressKeyList);
		/**
		 * Using Groovy Ordering Class isOrdered Method to verify sorted list.
		 * This will return boolean true if given list is sorted, else returns false
		 */
		boolean isSorted = Ordering.natural().isOrdered(customerAddressKeyList);
		WebUI.verifyEqual(true, isSorted)
	}

	/**
	 * Method to verify the Sort based on Modified Date
	 */
	@Keyword
	def verifyModifiedDateSort(){
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@data-testid='customer-summary']//div[2]"))
		def expectedCustomersSize = listOfElements.size()
		List<String> modifiedDateList = new ArrayList<String>();
		for(WebElement ele : listOfElements){
			String modifiedDate = ele.getText()
			def epocTime = localDateTimeToEpocTime(modifiedDate)
			modifiedDateList.add(epocTime)
		}
		commonUtil.sortOrder(modifiedDateList)
	}

	/**
	 * Method to verify the whether Save button disabled or not
	 * @param TestObject of the Component
	 */
	@Keyword
	def verifySaveButtonDisabled(TestObject testObject){
		String actualBtnState = WebUI.getAttribute(testObject, "class")
		boolean flag = (actualBtnState.contains("btn--disabled"))?true:false
		WebUI.verifyEqual(flag, true)
	}

	/**
	 * Method to verify the whether Save button Enabled or not
	 * @param TestObject of the Component
	 */
	@Keyword
	def verifySaveButtonEnabled(TestObject testObject){
		String actualBtnState = WebUI.getAttribute(testObject, "class")
		boolean flag = (actualBtnState.contains(CustomerPageConstants.SAVE_BUTTON_ENABLE_CLASS))?true:false
		WebUI.verifyEqual(flag, true)
	}

	/**
	 * Verify mandatory field validation ( Customer Name, Customer ID, Address Key )
	 */
	def verifyMandatoryFieldsErrorMessages(){

		/**
		 * Setting some sample text and deleting the text.
		 */
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerName'), "a")
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerName'), "\u0008")
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'),"1")
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'), "\u0008")
		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey'), "a")
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey'), "\u0008")
		WebUI.executeJavaScript(CustomerPageConstants.BILLING_SELECTOR,null)
		WebUI.executeJavaScript(CustomerPageConstants.CUSTOMER_TYPE_DROPDOWN, null)
		WebUI.executeJavaScript(CustomerPageConstants.CLOSE_ICON_CUSTOMER_TYPE, null)

		/**
		 * Sending Tab key to move focus from the text box. Error displays only after focus change
		 */
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey'), "\u0009")

		/**
		 * Delaying 2 second to wait for the error message to display
		 */
		WebUI.delay(2)

		/**
		 * Getting the error text
		 */
		String customerNameErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/custNameErrorMsg'))
		String customerIdErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerIdErrorMsg'))
		String addressKeyTypeErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/addressKeyErrorMsg'))
		String customerTypeErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerTypeErrorMsg'))

		/**
		 * Verifying actual error text with expected
		 */
		WebUI.verifyEqual(customerNameErrorMsg,CustomerPageConstants.CUSTOMER_NAME_ERROR_MSG)
		WebUI.verifyEqual(customerIdErrorMsg,CustomerPageConstants.CUSTOMER_ID_ERROR_MSG)
		WebUI.verifyEqual(addressKeyTypeErrorMsg,CustomerPageConstants.ADDRES_TYPE_ERROR_MSG)
		WebUI.verifyEqual(customerTypeErrorMsg,CustomerPageConstants.CUSTOMER_TYPE_ERROR_MSG)

		/**
		 * Verifying for alphanuemeric error message
		 */

		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'),"\u0040")
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'), "\u0009")
		String customerIdAlphaNuemericErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerIdErrorMsg'))
		WebUI.verifyEqual(customerIdAlphaNuemericErrorMsg,CustomerPageConstants.CUSTOMER_TYPE_ALPHA_NUEMERIC_ERROR_MSG)
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'), "\u0008")
	}

	/**
	 * Method to verify Cancel Action in the Add Customer Form
	 * @param addCustomerBtnObject
	 */
	@Keyword
	def verifyCancelButtonInAddCustomer(TestObject addCustomerBtnObject){

		/**
		 * Click on cancel button
		 */
		WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/CancelButton'))

		/**
		 * verifying the default page after cancel add customer
		 * Verify the Add Customer button . It should be Enabled after cancel the page.
		 * Getting the AddCustomer Button Css class and verifying
		 */
		String actualBtnState = WebUI.getAttribute(addCustomerBtnObject, "class")
		boolean flag = (actualBtnState.equals(CustomerPageConstants.ADD_CUSTOMER_ENABLE_CLASS))?true:false
		WebUI.verifyEqual(flag, true)
	}

	/**
	 * Verify Internal Server Error message for Add Customer failure due to non selecting the mandatory field
	 */
	@Keyword
	def verifyInternalServerError(){
		/**
		 * Validate the Error message for Add Customer Failures
		 */
		String errorMessage = WebUI.executeJavaScript(CustomerPageConstants.SERVER_ERROR_SELECTOR,null)
		WebUI.verifyEqual(errorMessage, CustomerPageConstants.INTERNAL_SERVER_ERROR)
	}

	/**
	 * Verify Inactive Customer in the customer List
	 */
	@Keyword
	def verifyFilterInactive(){
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/div_Customer1'))
		String expectedCustType = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/CustTypeValue'))
		WebUI.verifyEqual(expectedCustType.toString(), "Inactive")
	}

	/**
	 * Verify Customer ( Billing, Transportation, Inactive ) in the Customer List
	 * Common method for different types of customer
	 * @Param customerType
	 */
	@Keyword
	def verifyCustomerFilter(String customerType){
		boolean flag = false;
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/div_Customer1'))
		String expectedCustType = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/CustTypeValue'))

		/**
		 * Creating a list with different types of Customers,
		 * this List will be validated against the selected customers in the filters dialog
		 */
		List<String> customerTypeList = new ArrayList();
		customerTypeList.add(CustomerPageConstants.BILLING);
		customerTypeList.add(CustomerPageConstants.TRANSPORTATION);
		customerTypeList.add(CustomerPageConstants.INACTIVE);

		/**
		 * Below code verifies the filtered customers in the list
		 */
		if(customerType.equals(CustomerPageConstants.BILLING)){
			WebUI.verifyEqual(expectedCustType.toString(), CustomerPageConstants.BILLING)
		}
		else if(customerType.equals(CustomerPageConstants.TRANSPORTATION)){
			WebUI.verifyEqual(expectedCustType.toString(), CustomerPageConstants.TRANSPORTATION)
		}
		else if(customerType.equals(CustomerPageConstants.INACTIVE)){
			WebUI.verifyEqual(expectedCustType.toString(), CustomerPageConstants.INACTIVE)
		}
		else if(customerType.equals(CustomerPageConstants.ANY_CUSTOMER)){
			if(customerTypeList.contains(expectedCustType)){
				flag = true;
			}
			WebUI.verifyEqual(flag,true)
		}
	}

	/**
	 * Verify Inactive Customer in the customer List
	 */
	@Keyword
	def verifyFilterTransportation(){
		List<WebElement> listOfElements = driver.findElements(By.xpath(CustomerPageConstants.CUSTOMER_SUMMARY))
		WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/div_Customer1'))
		String expectedCustType = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/CustTypeValue'))
		WebUI.verifyEqual(expectedCustType.toString(), "Transportation")
	}

	/**
	 * Method to convert LocalDateTime to Epoc Time to sort
	 * @Param newDate
	 */
	def localDateTimeToEpocTime(String newDate){
		def epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm a").parse(newDate).getTime() / 1000;
		return epoch
	}

	/**
	 * Verify Stations drop down data
	 * @Param stationsObject, expectedStationsSize
	 */
	def verifyStationsInCustomer(ResponseObject stationsObject, String expectedStationsSize){
		String resText = stationsObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def stationsSizeFromServiceResponse = response.size();
		WebUI.verifyEqual(stationsSizeFromServiceResponse, expectedStationsSize)
	}

	/**
	 * Verify Zones drop down data
	 * @Param zonesObject, expectedZonesSize
	 */
	def verifyZonesInCustomer(ResponseObject zonesObject, String expectedZonesSize){
		String resText = zonesObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def zonesSizeFromServiceResponse = response.size();
		WebUI.verifyEqual(zonesSizeFromServiceResponse, expectedZonesSize)
	}

	/**
	 * Verify TRACKS drop down data
	 * @Param tracksObject, expectedTracksSize
	 */
	def verifyTracksInCustomer(ResponseObject tracksObject, String expectedTracksSize){
		String resText = tracksObject.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def response  = slupper.parseText(resText)
		def tracksSizeFromServiceResponse = response.size();
		WebUI.verifyEqual(tracksSizeFromServiceResponse, expectedTracksSize)
	}

	/**
	 * Verify Mandatory fields in Edit Customer
	 */
	@Keyword
	def verifyMandatoryFields(){

		WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerName'))
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerName'), "\u0008")
		WebUI.executeJavaScript(CustomerPageConstants.BILLING_SELECTOR,null)
		WebUI.executeJavaScript(CustomerPageConstants.CUSTOMER_TYPE_DROPDOWN, null)
		WebUI.executeJavaScript(CustomerPageConstants.CLOSE_ICON_CUSTOMER_TYPE, null)

		/**
		 * Sending Tab key to move focus from the text box. Error displays only after focus change
		 */
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/AddressKey'), "\u0009")

		/**
		 * Delaying 2 second to wait for the error message to display
		 */
		WebUI.delay(2)

		/**
		 * Getting the error text
		 */
		String customerNameErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/custNameErrorMsg'))
		String customerTypeErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerTypeErrorMsg'))

		/**
		 * Verifying actual error text with expected
		 */
		WebUI.verifyEqual(customerNameErrorMsg,CustomerPageConstants.CUSTOMER_NAME_ERROR_MSG)
		WebUI.verifyEqual(customerTypeErrorMsg,CustomerPageConstants.CUSTOMER_TYPE_ERROR_MSG)

		/**
		 * Verifying for alphanuemeric error message
		 */

		WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'),"\u0040")
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'), "\u0009")
		String customerIdAlphaNuemericErrorMsg = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerIdErrorMsg'))
		WebUI.verifyEqual(customerIdAlphaNuemericErrorMsg,CustomerPageConstants.CUSTOMER_TYPE_ALPHA_NUEMERIC_ERROR_MSG)
		WebUI.sendKeys(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/AddCustomer/customerId'), "\u0008")
	}

	/**
	 * Method to verify the Filter div is cleared after clearing the filters
	 */
	@Keyword
	def verifyClearFilters(){
		List<WebElement> listOfFilters = driver.findElements(By.xpath(CustomerPageConstants.FILETER_DIV_XPATH))
		int size = listOfFilters.size();
		WebUI.verifyEqual(new Integer(size),new Integer(0))
	}

	/**
	 * Method to verify the populated Customer Values in the right pane
	 * @param expectedCustomerId
	 */
	@Keyword
	def verifyCustomerEditedFields(String expectedCustomerId){
		def actualAlternateCode
		def actualAddress
		def actualAttention
		def actualMilePost
		String resText = getAllCustomersObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def customerResponseValue  = slupper.parseText(resText)
		def noOfCustomers = customerResponseValue.size()
		for(int i=0;i<noOfCustomers;i++){
			String customerId = customerResponseValue[i].customerId.toString()
			if(customerId.equals(expectedCustomerId)){
				actualAlternateCode = customerResponseValue[i].alternateCode;
				actualAddress = customerResponseValue[i].address.streetAddressLine1+" - "+customerResponseValue[i].address.streetAddressLine2
				actualAttention = customerResponseValue[i].attention
				actualMilePost = customerResponseValue[i].milepost
			}
		}
		def tempAlternateCode = (actualAlternateCode == null) ? "--" : actualAlternateCode;
		def tempAddress = (actualAddress == " - ") ? "--" : actualAddress;
		def tempAttention = (actualAttention == null) ? "--" : actualAttention;
		def tempMilePost = (actualMilePost == null) ? "--" : actualMilePost;

		/**
		 *  Expected Type from the UI
		 */
		def expectedAltCode = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/altCode'))
		def expectedAddress = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/address'))
		def expectedAttention = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/attention'))
		def expectedMilePost = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/rightPane/milepost'))

		WebUI.verifyEqual(expectedAltCode, action.alternativeCode)
		WebUI.verifyEqual(tempAttention,action.attentionText)
		WebUI.verifyEqual(tempMilePost,action.milePostText)

		/**
		 * Verifying customer data with the response data.
		 */
		WebUI.verifyEqual(tempAlternateCode,expectedAltCode)
		WebUI.verifyEqual(tempAddress,expectedAddress)
		WebUI.verifyEqual(tempAttention,expectedAttention)
		WebUI.verifyEqual(tempMilePost,expectedMilePost)
	}
}
