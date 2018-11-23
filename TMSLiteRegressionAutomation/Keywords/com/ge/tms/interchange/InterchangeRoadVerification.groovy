package com.ge.tms.interchange

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.InterchangePageConstants
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

import internal.GlobalVariable
import groovy.json.JsonSlurper

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import java.lang.Integer

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.junit.After
import org.openqa.selenium.By as By
import org.testng.Assert
import com.ge.tms.commonactions.InterchangeActions
import com.ge.tms.util.CommonUtilities



/**
 * Class for all the verification methods in Manage InterchangeRoad
 */
public class InterchangeRoadVerification {

	/**
	 *  Webdriver Instance from selenium
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * Action class for InterchangeActions
	 */
	InterchangeActions actions = new InterchangeActions()

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities utils= new CommonUtilities()

	/**
	 * Method to verify the manage interchange road text on page load
	 */
	@Keyword
	def verifyManageInterchangeRoadText(){
		WebUI.delay(4)
		String actualText = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/manageInterchangeText'))
		WebUI.verifyEqual(actualText,InterchangePageConstants.MANAGE_INTERCHANGE_TEXT)
	}

	/**
	 * Method to verify the interchange road view of a consist
	 * @param Response Object to get all interchange consist
	 * @param Selected consist Train id passed
	 */
	@Keyword
	def verifyInterchangeRoadConsistView(ResponseObject interchangeResponseObj ,List<String> interchangeConsistDetailList ){

		/**
		 * Json object for the selected consist from the Api
		 */
		def interchangeConsistSpecificData
		interchangeConsistSpecificData = getJsonBodyForSelectedConsist(interchangeResponseObj)
		/**
		 * To get all data from the API for a selected consist
		 * Verify if the details match the one from the UI
		 */

		/**
		 * To verify if the consist direction match with Api and UI 
		 */
		def expectedConsistDirection = interchangeConsistSpecificData.referenceInformation.direction.toString()
		def tempConsistDirection = (expectedConsistDirection == null || expectedConsistDirection == "") ? "--" : expectedConsistDirection;
		WebUI.verifyEqual(interchangeConsistDetailList[1],tempConsistDirection)

		/**
		 * To verify if the consist type match with Api and UI 
		 */
		def expectedConsistType = interchangeConsistSpecificData.referenceInformation.consistType.toString()
		def tempConsistType = (expectedConsistType == null || expectedConsistType == "") ? "--" : expectedConsistType;
		WebUI.verifyEqual(interchangeConsistDetailList[2],tempConsistType)

		/**
		 * To verify if the consist Sender Id match with Api and UI
		 */
		def expectedConsistSenderId = interchangeConsistSpecificData.referenceInformation.senderId.toString()
		def tempConsistSenderId = (expectedConsistSenderId == null || expectedConsistSenderId == "") ? "--" : expectedConsistSenderId;
		WebUI.verifyEqual(interchangeConsistDetailList[3],tempConsistSenderId)

		/**
		 * To verify if the consist splc match with Api and UI
		 */
		def expectedConsistSplc = interchangeConsistSpecificData.destination.splc.toString()
		def tempConsistSplc = (expectedConsistSplc == null || expectedConsistSplc == "") ? "--" : expectedConsistSplc;
		WebUI.verifyEqual(interchangeConsistDetailList[4],tempConsistSplc)

		/**
		 * Verify the car number shown in the UI with Api car number data 
		 * Also verify the car number shown in the UI equals the number of car in the right panel
		 */
		List<String> carList = new ArrayList<String>()
		carList= interchangeConsistSpecificData.consists.cars[0]
		def temp = carList.size()
		WebUI.verifyEqual(interchangeConsistDetailList[5],temp)
		WebUI.verifyEqual(interchangeConsistDetailList[5],interchangeConsistDetailList[6])
	}

	/**
	 * Method to verify the details of the quick view of the selected car
	 * @param Response object
	 * @param randomNumberOfCarSelected
	 */
	@Keyword
	def verifyDetailsOfSelectedCarQuickView(ResponseObject interchangeResponseObj , int randomNumberOfCarSelected){

		/**
		 * Json object for the selected consist from the Api
		 */
		def interchangeConsistSpecificData
		interchangeConsistSpecificData = getJsonBodyForSelectedConsist(interchangeResponseObj)

		List<String> carList = new ArrayList<String>()
		carList= interchangeConsistSpecificData.consists.cars[0]

		/**
		 * verify the ARR type in view
		 */
		def actualArrTypeFromUI =  WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewARRTypeSpan'))
		def expectedArrTypeFromApi = carList[randomNumberOfCarSelected - 1].aarType
		def tempArrTypeFromApi = (expectedArrTypeFromApi == null || expectedArrTypeFromApi == "") ? "--" : expectedArrTypeFromApi;
		WebUI.verifyEqual(actualArrTypeFromUI, tempArrTypeFromApi)

		/**
		 * Verify the Car id in the view
		 */
		def actualCarIdFromUI =   WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewCarIdHeader'))
		def expectedCarIdFromApi = carList[randomNumberOfCarSelected - 1].equipmentId
		def tempCarIdFromApi = (expectedCarIdFromApi == null || expectedCarIdFromApi == "") ? "--" : expectedCarIdFromApi;
		WebUI.verifyEqual(actualCarIdFromUI, tempCarIdFromApi)

		/**
		 * Verify the waybill number in the view
		 */
		def actualWaybillFromUI =    WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewWaybillNoSpan'))
		def expectedWaybillFromApi = carList[randomNumberOfCarSelected - 1].waybillNumber
		def tempWaybillFromApi = (expectedWaybillFromApi == null || expectedWaybillFromApi == "") ? "--" : expectedWaybillFromApi;
		WebUI.verifyEqual(actualWaybillFromUI, tempWaybillFromApi)

		/**
		 * Verify the hazmat status in the view
		 */
		def actualHazmatStatusFromUI
		if(WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewHazmatSpan')).contentEquals(InterchangePageConstants.QUICK_CAR_VIEW_UI_HAZMAT_TYPE_NO)) {
			actualHazmatStatusFromUI = InterchangePageConstants.QUICK_CAR_VIEW_API_HAZMAT_TYPE_FALSE
		}
		else {
			actualHazmatStatusFromUI = InterchangePageConstants.QUICK_CAR_VIEW_API_HAZMAT_TYPE_TRUE
		}
		def expectedHazmatStatusFromApi = carList[randomNumberOfCarSelected - 1].isHazmat.toString()
		def tempHazmatStatusFromApi = (expectedHazmatStatusFromApi == null || expectedHazmatStatusFromApi == "") ? "--" : expectedHazmatStatusFromApi;
		WebUI.verifyEqual(actualHazmatStatusFromUI, tempHazmatStatusFromApi)

		/**
		 * Verify the Commodity Code in the view
		 */
		def actualStccFromUI =     WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewSTCCNumberSpan'))
		def expectedStccFromApi = carList[randomNumberOfCarSelected - 1].commodityCode
		def tempStccFromApi = (expectedStccFromApi == null || expectedStccFromApi == "") ? "--" : expectedStccFromApi;
		WebUI.verifyEqual(actualStccFromUI, tempStccFromApi)

		/**
		 * Verify Load status in view
		 */
		def actualLoadStatusFromUI =  WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewLoadStatusSpan'))
		def expectedLoadStatusFromApi = carList[randomNumberOfCarSelected - 1].loadStatus
		def tempLoadStatusFromApi = (expectedLoadStatusFromApi == null || expectedLoadStatusFromApi == "") ? "--" : expectedLoadStatusFromApi;
		WebUI.verifyEqual(actualLoadStatusFromUI, tempLoadStatusFromApi)

		/**
		 * Verify Destination station in view
		 */
		def actualDestinationFsacFromUI =   WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewFsacSpan'))
		def expectedDestinationFsacFromApi = carList[randomNumberOfCarSelected - 1].destinationStation
		def tempDestinationFsacFromApi = (expectedDestinationFsacFromApi == null || expectedDestinationFsacFromApi == "") ? "--" : expectedDestinationFsacFromApi;
		WebUI.verifyEqual(actualDestinationFsacFromUI, tempDestinationFsacFromApi)

		/**
		 * Verify Block to in view
		 */
		def actualBlockToFromUI =   WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/quickCarViewBlockToSpan'))
		def expectedBlockToFromApi = carList[randomNumberOfCarSelected - 1].blockTo
		def tempBlockToFromApi = (expectedBlockToFromApi == null || expectedBlockToFromApi == "") ? "--" : expectedBlockToFromApi;
		WebUI.verifyEqual(actualBlockToFromUI, tempBlockToFromApi)
	}

	/**
	 * Method to get the json data for a specific consist selected
	 * @param Response Object passed
	 * @return json object for selected consist
	 */
	def getJsonBodyForSelectedConsist(ResponseObject interchangeResponseObj){

		/**
		 * Parsing Json text and getting a  interchange consist specific data for the given Train id
		 */
		String resText = interchangeResponseObj.getResponseBodyContent()
		JsonSlurper slupper = new JsonSlurper()
		def interchangeResponse  = slupper.parseText(resText)
		def singleInterchangeRoadData;
		def interchangeConsistSpecificData;
		int responseSize = interchangeResponse.size()
		boolean flag=false;
		for(int i=0;i<responseSize;i++){
			if(!flag){
				singleInterchangeRoadData = interchangeResponse[i]
				String trainIdNumber = singleInterchangeRoadData.referenceInformation.trainId.toString()
				if(trainIdNumber.equals(actions.trainId)){
					interchangeConsistSpecificData = singleInterchangeRoadData
					flag=true
				}
			}
		}
		return interchangeConsistSpecificData
	}

	/**
	 * Method to get No of consists of the leftpane
	 * @return size of left pane
	 */
	def int InterchangeFullLeftPaneSize() {
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_LEFTPANE))
		int expectedSize = listOfElements.size()
		return expectedSize
	}

	/**
	 * Verify Interchange Filter Inbound Outbound
	 * @Param labletext
	 */
	@Keyword
	def verifyInterchangeFilterInboundOutbound(String labletext){
		String[] textArr;
		String text="";
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_INBOUNDOUTBOUNDLABLE))
		def sizeOfFilteredWaybills = listOfElements.size()
		for(int i=0;i<sizeOfFilteredWaybills;i++){
			WebElement element = listOfElements.get(i)
			text = element.getText()
			WebUI.verifyEqual(text, labletext)
		}
	}

	/**
	 * Verify Interchange Filter Inbound Outbound All
	 * @Param text
	 */
	@Keyword
	def verifyInterchangeFilterInboundOutboundAll(String inbound, String outbound){
		String[] textArr;
		String text="";
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_INBOUNDOUTBOUNDLABLE))
		def sizeOfFilteredWaybills = listOfElements.size()
		for(int i=0;i<sizeOfFilteredWaybills;i++){
			WebElement element = listOfElements.get(i)
			text = element.getText()
			Assert.assertTrue(inbound.equals(text) || outbound.equals(text));
		}
	}

	/**
	 * Verify no interchange found message
	 */
	@Keyword
	def verifyInvalidSearchResults(){
		WebUI.verifyEqual(WebUI.getText(findTestObject('Object Repository/F13179 - Search among interchanges/QA - US92439/Page_Transportation Management Syst/h3_No Interchanges found.')), InterchangePageConstants.NO_INTERCHAGE_FOUND_MESSAGE_TEXT )
	}

	/**
	 * Verify Car ID search results equals to searched Car ID
	 */
	@Keyword
	def verifyCarSearchResults(){
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_CAR_LIST_PER_CONSIST_PATH))
		WebUI.delay(2)
		if(listOfElements.size()!= 0){
			for (int i=1; i <= listOfElements.size() ; i++){
				if(listOfElements[i].getText() == actions.getRandomCarFromRightPane()){
					WebUI.verifyEqual(listOfElements[i].getText(), actions.getRandomCarFromRightPane())
					break
				}
			}
		}
	}

	/**
	 * Verify Train ID search results equals to searched Train ID
	 */
	@Keyword
	def verifyTrainIDSearchResults(){
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV))
		for(int i=0; i<=listOfElements.size(); i++){
			if (listOfElements[i].getText()== actions.randomTrainID){
				WebUI.verifyEqual(actions.randomTrainID, listOfElements[i].getText())
				break
			}
		}
	}

	/**
	 * Method to Sort Interchange Sort By Time
	 */
	@Keyword
	def InterchangeSortByItemSort(String type) {
		for(int i=1;i<=InterchangeFullLeftPaneSize();i++){
			List<String> itemList = new ArrayList<String>()
			List<WebElement> listofelementday = driver.findElements(By.xpath('//*[@id="root"]/div/div/section/section/div['+i+']/div[2]/div'))
			int consistsperday = listofelementday.size()
			for(int row=1;row<=consistsperday;row++){
				if(type == "Time"){
					String consistTime = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div['+i+']/div[2]/div['+row+']/div[1]/div[2]/div[1]/span')).getText()
					itemList.add(consistTime)
				}
				if(type == "Station"){
					String consisStation = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div['+i+']/div[2]/div['+row+']/div[1]/div[2]/div[4]/span')).getText()
					itemList.add(consisStation)
				}
				if(type == "Type"){
					String consisType = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div['+i+']/div[2]/div['+row+']/div[1]/div[2]/div[2]/span')).getText()
					itemList.add(consisType)
				}
				if(type == "Railroad"){
					String consisRailRoad = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div['+i+']/div[2]/div['+row+']/div[1]/div[2]/div[3]/span')).getText()
					itemList.add(consisRailRoad)
				}
				if(type == "TrainID"){
					String consisTrainID = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div['+i+']/div[2]/div['+row+']/div[1]/div[1]/span/span[1]')).getText()
					itemList.add(consisTrainID)
				}
			}
			utils.sortOrder(itemList)
		}
	}

	/**
	 * Verify Interchange Select All and De-Select Action
	 */
	@Keyword
	def verifySelectAndDeSelectAllAction(){

		/**
		 * Selecting the consist and finding the cars in the consist
		 */
		List<WebElement> listOfConsists = driver.findElements(By.xpath(InterchangePageConstants.FIRST_CONSIST_XPATH))
		List<WebElement> listOfCars ;
		WebElement firstConsist = null;
		if(listOfConsists.size()>0){
			listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		}

		/**
		 * Size of cars in consist
		 */
		int noOfCars = listOfCars.size()

		/**
		 * Select Action item - Click on Select All
		 */
		WebUI.executeJavaScript(InterchangePageConstants.SELECT_ALL_ACTION, null)
		WebUI.delay(1)

		/**
		 * Finding the list of cars selected after clicking on select all cars actions
		 */
		List<WebElement> listOfSelectedCars = driver.findElements(By.xpath(InterchangePageConstants.SELECTED_CARS_XPATH))

		/**
		 * No Of selected cars
		 */
		int noOfCarsSelected = listOfSelectedCars.size()

		/**
		 * Verify No Of Cars Selected
		 */
		WebUI.verifyEqual(noOfCars, noOfCarsSelected)

		WebUI.delay(1)

		/**
		 * Select Action item - Click on De Select All
		 */
		WebUI.executeJavaScript(InterchangePageConstants.DESELECT_ALL_ACTION, null)
		List<WebElement> listOfDeSelectedCars = driver.findElements(By.xpath(InterchangePageConstants.UNSELECTED_CARS_XPATH))

		/**
		 * Verify No Of Cars DeSelected
		 */
		int noOfCarsDeSelected = listOfDeSelectedCars.size()

		/**
		 * Verify No Of Cars Selected
		 */
		WebUI.verifyEqual(noOfCars, noOfCarsDeSelected)
		WebUI.delay(1)
	}

	/**
	 * Verify Interchange Action - In-bound Cars
	 */
	@Keyword
	def verifyInboundCarsAction(){
		/**
		 * Getting all consists and iterating through it. Selecting a consist
		 */
		List<WebElement> listOfConsists = driver.findElements(By.xpath(InterchangePageConstants.FIRST_CONSIST_XPATH))
		List<WebElement> listOfCars ;
		WebElement firstConsist = null;
		if(listOfConsists.size()>0){
			listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		}

		/**
		 * finding no of cars in the consist
		 */
		int noOfCars = listOfCars.size()

		/**
		 * Getting station id, car id, train id
		 */
		String actualCarId = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/carId'))
		String trainId = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/trainId'))
		String stationID = WebUI.executeJavaScript(InterchangePageConstants.STATION_ID_SELECTOR, null)

		/**
		 * Click on Inbound Action
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/inboundCarsAction'))

		WebUI.delay(2)

		WebUI.executeJavaScript(InterchangePageConstants.ACTIONS_INBOUND_CARS_SELECTOR, null)

		/**
		 * Getting the car id in the modal
		 */
		String carId = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/carIdValue'))

		/**
		 * Constructing the expected header message in modal dialog
		 */
		String expectedMsg = "Inbound cars from train "+trainId.trim()+" at station "+stationID

		/**
		 * Getting actual header message in modal dialog
		 */
		String actualMsg = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/inboudCarHeaderMessage'))

		/**
		 * Verifying the header message in modal dialog
		 */
		WebUI.verifyEqual(actualMsg.toString(), expectedMsg.toString())

		/**
		 * Selecting a track to inbound
		 */
		WebUI.executeJavaScript(InterchangePageConstants.SELECT_TRACK_INBOUND_POPUP_SELECTOR, null)
		WebUI.delay(1)

		/**
		 * Clicking on continue
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/continueBtnInboundCar'))

		/**
		 * Verifying the modal dialog header in confirmation dialog box
		 */
		String confirmMessage = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/inboundCarConfirmMessage'))
		String actualDeleteConfMsg = "You are about to inbound 1 / "+noOfCars+" from train "+trainId+"."

		/**
		 * Verifying the Modal Dialog confirmation message with the actual message
		 */
		WebUI.verifyEqual(actualDeleteConfMsg, confirmMessage)

		/**
		 * Clicking on confirm button
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/inboundConfirmButton'))

		WebUI.delay(5)

		/**
		 * Getting the no of cars after inbound of a single car and verifying
		 */
		listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))

		/**
		 * verifying the size of the cars with the original size after inbounding the car
		 */
		WebUI.verifyEqual(noOfCars-1, listOfCars.size())

		/**
		 * Verifying the inbound car in the consist car list. This should not present
		 */
		List<String> listOfCarNames = new ArrayList<String>()
		for(WebElement ele:listOfCars){
			String carText = ele.getText()
			listOfCarNames.add(carText)
		}

		boolean flag = (!listOfCarNames.contains(carId))

		/**
		 * Verifying the car inbounded, That should not be displayed in the cars list
		 */
		WebUI.verifyEqual(true,flag)
	}

	/**
	 * Verify Interchange Action - Add Car
	 */
	@Keyword
	def verifyAddCarsAction(){
		WebUI.delay(2)
		WebUI.executeJavaScript(InterchangePageConstants.ACTIONS_ADD_CARS_SELECTOR, null)

		/**
		 * Setting up data into the form
		 */
		WebUI.setText(findTestObject('Object Repository/F13178-Manage Interchange/CarId_AddCar'), actions.setUniqueValueForEquipmentId())

		WebUI.setText(findTestObject('Object Repository/F13178-Manage Interchange/aarType'), InterchangePageConstants.CAR_TYPE)
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/loadEmptyButton'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/loadedOption'))
		WebUI.setText(findTestObject('Object Repository/F13178-Manage Interchange/commodityCode'), InterchangePageConstants.COMMODITY_CODE)
		WebUI.setText(findTestObject('Object Repository/F13178-Manage Interchange/blockTo'), InterchangePageConstants.BLOCK_TO)

		/**
		 * Click on Continue button after form fill
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/addCarContinue'))
		WebUI.delay(2)

		/**
		 * Verify the confirmation message
		 */
		String confirmMessge = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/addCarConfirmationMessage'))

		/**
		 * Click on Accept button - Add Car
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/addCarAcceptButton'))

		WebUI.delay(5)

		String carId = actions.equipmentId

		/**
		 * Find all cars after adding the cars and verify the car inthe list
		 */
		List<WebElement> listOfConsists = driver.findElements(By.xpath(InterchangePageConstants.FIRST_CONSIST_XPATH))
		List<WebElement> listOfCars ;
		WebElement firstConsist = null;
		if(listOfConsists.size()>0){
			listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		}

		/**
		 * finding no of cars in the consist and iterating through to verify the newly added car exist in the list
		 */
		int noOfCars = listOfCars.size()
		List<String> listOfCarNames = new ArrayList<String>()
		for(WebElement ele:listOfCars){
			String carText = ele.getText()
			listOfCarNames.add(carText)
		}

		/**
		 * Verifying the newly added car present in the cars list
		 */
		boolean flag = (listOfCarNames.contains(carId))
		WebUI.verifyEqual(true,flag)
		WebUI.delay(3)
	}

	/**
	 * Remove Car Action
	 */
	@Keyword
	def verifyRemoveCarAction(){
		/**
		 * Getting all consists and iterating through it. Selecting a consist
		 */
		List<WebElement> listOfConsists = driver.findElements(By.xpath(InterchangePageConstants.FIRST_CONSIST_XPATH))
		List<WebElement> listOfCars ;
		WebElement firstConsist = null;
		if(listOfConsists.size()>0){
			listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		}

		/**
		 * finding no of cars in the consist
		 */
		int noOfCars = listOfCars.size()

		/**
		 * Getting station id, car id, train id
		 */
		String trainId = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/trainId'))
		String stationID = WebUI.executeJavaScript(InterchangePageConstants.STATION_ID_SELECTOR, null)
		String carId = InterchangePageConstants.CAR_ID

		/**
		 * Xpath for the car to select, Selecting the last car which is newly created.
		 */
		String selectButtonXpath = "//div[@class='flex__item flex flex--middle RailPath-module_path_1gTHH'][1]//div[@aria-roledescription='Draggable item. Press space bar to lift']["+noOfCars+"]//button"
		driver.findElement(By.xpath(selectButtonXpath)).click()
		WebUI.delay(2)

		/**
		 * Clicking on Remove Cars option from Actions dropdown
		 */
		WebUI.executeJavaScript(InterchangePageConstants.ACTIONS_REMOVE_CARS_SELECTOR, null)
		WebUI.delay(1)

		/**
		 * filling data into remove form
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/removeReasons'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/badOrder'))
		WebUI.setText(findTestObject('Object Repository/F13178-Manage Interchange/notes'), InterchangePageConstants.NOTES)
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/applyButton'))
		WebUI.delay(1)

		/**
		 * Click on remove car button
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/apply_removeCar'))
		WebUI.delay(2)

		/**
		 * Verifying remove confirmation message from actual to expected
		 */
		String actualConfirmMessage = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/removeCarConfirmationMessage'))
		String expectedConfirmMessage ="You are about to remove 1 / "+noOfCars+" from train "+trainId+".";
		WebUI.verifyEqual(actualConfirmMessage, expectedConfirmMessage)
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/removeCarContinue'))
		WebUI.delay(7)

		/**
		 * Getting all cars and verifying the cars size with the original and after remove car
		 */
		List<WebElement> actualListOfCars = new ArrayList<WebElement>()
		actualListOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		WebUI.verifyEqual(actualListOfCars.size(), noOfCars-1)
		WebUI.delay(3)
	}

	/**
	 * Flip Consist Action
	 */
	@Keyword
	def verifyFlipConsistAction(){
		/**
		 * Getting all consists and iterating through it. Selecting a consist
		 */
		List<WebElement> listOfConsists = driver.findElements(By.xpath(InterchangePageConstants.FIRST_CONSIST_XPATH))
		List<WebElement> listOfCars ;
		WebElement firstConsist = null;
		if(listOfConsists.size()>0){
			listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		}

		/**
		 * finding no of cars in the consist
		 */
		int noOfCars = listOfCars.size()

		/**
		 * Get All Car Ids into ArrayList
		 */
		List<String> listOfCarIds=new ArrayList<String>()
		for(WebElement ele:listOfCars){
			String carText = ele.getText()
			listOfCarIds.add(carText)
		}

		/**
		 * Selecting Flip Cars Option from Action dropdown
		 */
		WebUI.executeJavaScript(InterchangePageConstants.ACTIONS_FLIP_CONSIST_SELECTOR, null)

		/**
		 * Getting station train id
		 */
		String trainId = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/trainId'))

		/**
		 * Verifying the confirmation message
		 */
		String expectedConfirmMessage = "Are you sure you want to flip consist: "+trainId+"?";
		String actualConfirmMessage = WebUI.getText(findTestObject('Object Repository/F13178-Manage Interchange/flipConsistConfirmMsg'))
		WebUI.verifyEqual(actualConfirmMessage, expectedConfirmMessage)

		/**
		 * Click on Accept button
		 */
		WebUI.click(findTestObject('Object Repository/F13178-Manage Interchange/acceptBtnFlipConsist'))
		WebUI.delay(5)

		/**
		 * Waiting for page to load
		 */
		WebUI.delay(5)

		/**
		 * Finding all cars after flip consist
		 */
		List<WebElement> listOfConsistsAfter = driver.findElements(By.xpath(InterchangePageConstants.FIRST_CONSIST_XPATH))
		List<WebElement> listOfCarsAfter ;
		WebElement firstConsistAfter = null;
		if(listOfConsistsAfter.size()>0){
			listOfCarsAfter = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
		}

		/**
		 * finding no of cars in the consist
		 */
		int noOfCarsAfter = listOfCarsAfter.size()

		/**
		 * Get All Car Ids into ArrayList
		 */
		List<String> listOfCarIdsAfter=new ArrayList<String>()
		for(WebElement ele:listOfCarsAfter){
			String carText = ele.getText()
			listOfCarIdsAfter.add(carText)
		}
		boolean flag =true;

		/**
		 * Finding whether all cars are reversed after flip consist or not. Comparing two lists origina list and after flip.
		 * If these are not in order making false
		 */
		int sizeOfCarListBefore = listOfCarIds.size();
		int sizeOfCarListAfter = listOfCarIdsAfter.size();
		Collections.reverse(listOfCarIds)
		if(sizeOfCarListBefore==sizeOfCarListAfter){
			for(int i=0;i<sizeOfCarListBefore;i++){
				if(!(listOfCarIds.get(i).equals(listOfCarIdsAfter.get(i)))){
					flag=false
				}
			}
		}
		else{
			flag=false
		}
		WebUI.verifyEqual(true, flag)
		WebUI.delay(3)
	}

	/**
	 * Select Range verification
	 */
	@Keyword
	def verifySelectRange(){
		List<WebElement> listOfCarsSelected
		List<String> listOfCarIdsSelected=new ArrayList<String>()
		listOfCarsSelected = driver.findElements(By.xpath(InterchangePageConstants.SELECTED_CARS))
		for(int i=0;i<listOfCarsSelected.size();i++) {
			String equipmentText = listOfCarsSelected[i].getText()
			listOfCarIdsSelected.add(equipmentText)
		}
		utils.equalLists(actions.listOfCarIds, listOfCarIdsSelected)
	}
}
