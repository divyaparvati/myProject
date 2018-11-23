package com.ge.tms.commonactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After
import org.junit.Before
import com.ge.tms.constants.InterchangePageConstants
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import java.util.Map
import java.util.HashMap
import groovy.json.JsonSlurper;
import com.kms.katalon.core.testobject.ResponseObject;
import com.kms.katalon.core.logging.KeywordLogger
import org.openqa.selenium.Keys
import org.openqa.selenium.*
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List

/**
 * Class for all actions related to all Interchange roads
 */
public class InterchangeActions {

	/**
	 *  Webdriver Instance from selenium
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * Variable to hold consistTrainIdSpan
	 */
	public String consistTrainIdSpan

	/**
	 * Static random number for the selected Consist
	 */
	static int randomNumberForConsistSelection

	/**
	 * Static random number for selection of a car
	 */
	static int randomNumberForCarSelection

	/**
	 * Static variable to hold the train id
	 */
	static String trainId

	/**
	 * Static variable to hold the equipment id
	 */
	static String equipmentId

	/**
	 * CommonUtilities class instance
	 */
	CommonUtilities utils= new CommonUtilities()

	/**
	 * Variable to hold randomly picked Train ID
	 */
	String randomTrainID;

	/**
	 * List to hold Car IDs
	 */
	public static List<String> listOfCarIds

	/**
	 * Variable to hold randomly picked Car ID
	 */
	public static String randomPickedCarIDFromFirstConsist

	/**
	 * Creating Object for Logger class to log the text to console
	 */
	KeywordLogger log = new KeywordLogger()

	/**
	 * Method to click on the Manage Interchange Roads Tab under Train Operations
	 */
	@Keyword
	def clickOnManageInterchangeRoadTab(){
		WebUI.executeJavaScript(InterchangePageConstants.MANAGE_INTERCHANGE_ROAD_TAB, null)
		WebUI.delay(2)
	}

	/**
	 * Method to randomly select a Interchangeroad consist from the left panel
	 * and get all the details of the selected consit
	 * @return selected consist's detail list
	 */
	def getRandomlySelectedConsistDetailsFromLeftPanel(){

		/**
		 * Randomly click on a consist
		 */
		clickOnARandomConsistInLeftPanel()

		/**
		 * Create a list to contain all the details of a selected consist
		 */
		List<String> interchangeConsistDetailList = new ArrayList<String>()

		/**
		 * Get each detail of a consist like TrainId, Direction , Type , SenderId, Splc , NoOfCars
		 * and store it in a array list
		 */ 
		consistTrainIdSpan = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//span/span'
		interchangeConsistDetailList.add(driver.findElement(By.xpath(consistTrainIdSpan)).getText())
		trainId = interchangeConsistDetailList[0]
		def consistDirectionSpan = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//span/span[2]'
		interchangeConsistDetailList.add(driver.findElement(By.xpath(consistDirectionSpan)).getText())
		def  consistTypeSpan = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//div[2]/div[2]/span'
		interchangeConsistDetailList.add(driver.findElement(By.xpath(consistTypeSpan)).getText())
		def consistSenderIdSpan = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//div[2]/div[3]/span'
		interchangeConsistDetailList.add(driver.findElement(By.xpath(consistSenderIdSpan)).getText())
		def consistSplcSpan = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//div[2]/div[4]/span'
		interchangeConsistDetailList.add(driver.findElement(By.xpath(consistSplcSpan)).getText())
		def consistCarNumber = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//div[2]/div[5]/span'
		interchangeConsistDetailList.add(driver.findElement(By.xpath(consistCarNumber)).getText())

		/**
		 * To get the number of cars in the right panel for a consist
		 * and store it in list
		 */
		int noOfCarsInSelectedConsist = getNumberOfCarsFromAConsist()
		interchangeConsistDetailList.add(noOfCarsInSelectedConsist)

		/**
		 * Return the selected consist details list
		 * including the number of car in the right panel 
		 */
		return interchangeConsistDetailList
	}

	/**
	 * Method to randomly click on a consist
	 */
	def clickOnARandomConsistInLeftPanel(){

		/**
		 * To get number of consists from the Interchange road screen
		 */
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV))
		int noOfInterchangeConsist = listOfElements.size()

		/**
		 * Create a random number
		 */
		randomNumberForConsistSelection = utils.generateRandomNumber(noOfInterchangeConsist)

		/**
		 * Click the random consist from the list
		 */
		def interchangeRoadListDiv = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']//button'
		driver.findElement(By.xpath(interchangeRoadListDiv)).click()
	}

	/**
	 * Method to click on a random car of the selected consist
	 */
	def clickOnRandomCarFromAConsist(){

		/**
		 * To get the number of cars in the right panel for a consist
		 */
		int noOfCarsInSelectedConsist = getNumberOfCarsFromAConsist()

		/**
		 * Create a random number to click on the car
		 */
		randomNumberForCarSelection = utils.generateRandomNumber(noOfCarsInSelectedConsist)

		/**
		 * click on the random car from the list
		 */
		def interchangeRoadCarListSpan = InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']'+InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_CAR_LIST_DIV+'['+randomNumberForCarSelection+']'+InterchangePageConstants.LIST_SPAN
		driver.findElement(By.xpath(interchangeRoadCarListSpan)).click()
	}

	/**
	 * Method to get the number of cars from a selected consist
	 * @return number of cars from the list
	 */
	def getNumberOfCarsFromAConsist(){
		List<WebElement> listOfCarElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV+'['+randomNumberForConsistSelection+']'+InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_CAR_LIST_DIV))
		int noOfCarsInSelectedConsistFromRightPanel = listOfCarElements.size()
		return noOfCarsInSelectedConsistFromRightPanel
	}

	/**
	 * Method to click on Interchange Type DropDown
	 */
	@Keyword
	def clickOnInterchangeTypeDropDown(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/interchangeTypeDropDown"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Type DropDown Inbound Only
	 */
	@Keyword
	def clickOnInterchangeTypeDropDownIO(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/InterchangeTypeDropdownInboundOnly"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Type DropDown Outbound Only
	 */
	@Keyword
	def clickOnInterchangeTypeDropDownOO(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/InterchangeDropdownOutboundOnly"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Type DropDown Inbound/Outbound
	 */
	@Keyword
	def clickOnInterchangeTypeDropDownAll(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/interhangeTypeDropdownInboundOutbound"))
		WebUI.delay(2)
	}

	/**
	 * Method to get the train ID from the left panel
	 * @return randomTrainID - selected consist's detail list
	 */
	def returnRandomlySelectedConsistTrainID(){
		randomTrainID = driver.findElement(By.xpath(consistTrainIdSpan)).getText()
		return randomTrainID;
	}

	/**
	 * Method to fetch the car from right pane for a particular consist
	 * @return randomPickedCarIDFromFirstConsist
	 * This Method is not integrate because of the DE72076 - The train ID does not exact match when full Train ID given, Works fine when Train ID partially given
	 */
	def getRandomCarFromRightPane(){
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_CAR_LIST_PER_CONSIST_PATH))
		int randomCarIDNumber =utils.generateRandomNumber(listOfElements.size())
		randomPickedCarIDFromFirstConsist = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div[1]/div[2]/div/div[2]/div[1]/div[1]/div['+randomCarIDNumber+']')).getText()
		WebUI.delay(2)
		return randomPickedCarIDFromFirstConsist
	}
	/**
	 * Method to add a wrong carID
	 */
	def addWrongCarID(){
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()',
				null)
		WebUI.setText(findTestObject('Object Repository/F13179 - Search among interchanges/QA - US92439/Page_Transportation Management Syst/input_search-input'), "Wrong Car")
		WebUI.delay(3)
	}

	/**
	 * Click on Search By drop down
	 * Add the randomly selected car ID from the consist, in to Search field
	 */
	def addRandomlyPickedCarToSearchField(){
		WebUI.clearText(findTestObject('Object Repository/F13179 - Search among interchanges/QA - US92439/Page_Transportation Management Syst/input_search-input'))
		WebUI.delay(1)
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").click()',null)
		WebUI.delay(1)
		WebUI.clearText(findTestObject("Object Repository/F13179 - Search among interchanges/QA - US92439/Page_Transportation Management Syst/input_search-input"))
		WebUI.setText(findTestObject('Object Repository/F13179 - Search among interchanges/QA - US92439/Page_Transportation Management Syst/input_search-input'), randomPickedCarIDFromFirstConsist)
	}

	/**
	 * Create a interchangeConsistDetailList to contain all the details from a selected consist
	 * This Method is commented because of the DE72076 - The train ID does not exact match when full Train ID given, Works fine when Train ID partially given
	 */
	def  createInterchangeConsistDetailList(){
		//List<String> interchangeConsistDetailList = new ArrayList<String>()
		//
		//interchangeConsistDetailList = actions.randomlySelectedConsistDetailsFromLeftPanel()
	}

	/**
	 * Click on Search By drop down
	 * This Method is commented because of the DE72076 - The train ID does not exact match when full Train ID given, Works fine when Train ID partially given
	 */
	def clickAndAddRandomTrainID(){
		//WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()',
		//	null)
		//WebUI.setText(findTestObject("Object Repository/F13179 - Search among interchanges/QA - US92439/Page_Transportation Management Syst/input_search-input"), actions.returnRandomlySelectedConsistTrainID())
	}

	/**
	 * Method to click on Interchange Sort By
	 */
	@Keyword
	def clickOnsortByDropdown(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByDropdown"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Sort By Time Ascending
	 */
	@Keyword
	def clickOnsortByTimeAscending(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByTimeAscending"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Sort By Time Descending
	 */
	@Keyword
	def clickOnsortByTimeDescending(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByTimeDescending"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Sort By Station
	 */
	@Keyword
	def clickOnsortByStation(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByInterchangeStation"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Sort By Interchange Type
	 */
	@Keyword
	def clickOnsortByInterchangeType(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByInterchangeType"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Sort By From RailRoad
	 */
	@Keyword
	def clickOnsortByFromRailRoad(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByFromRailRoad"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Interchange Sort By Train ID
	 */
	@Keyword
	def clickOnsortByTrainID(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortByTrainId"))
		WebUI.delay(2)
	}

	/**
	 * Method to click on Sort Order button
	 */
	@Keyword
	def clickOnSortBySortOrderButton(){
		WebUI.click(findTestObject("Object Repository/F13178-Manage Interchange/sortBySortOrderButton"))
		WebUI.delay(2)
	}

	/**
	 * Method to set equipment id
	 * EQUIPMENT ID MUST START WITH 1 TO 4 CHARACTERS AND ENDS WITH 1 TO 4 NUMBERS
	 * @return equipmentId
	 */
	def setUniqueValueForEquipmentId(){
		int	numberTobeGenerated = utils.generateRandomNumberWithLimit(1000,2000)
		equipmentId = utils.generateRandomAlphabets(4)+ numberTobeGenerated
		return equipmentId
	}

	/**
	 * Method to click on a consist which have more than Five Equipment
	 */
	def clickSelectRange(){
		listOfCarIds=new ArrayList<String>()
		List<WebElement> listOfElements = driver.findElements(By.xpath(InterchangePageConstants.INTERCHANGE_ROAD_CONSIST_LIST_DIV))
		int noOfInterchangeConsist = listOfElements.size()
		List<WebElement> listOfCars
		for(int i=1;i<noOfInterchangeConsist;i++) {
			listOfCars = driver.findElements(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST))
			if(listOfCars.size()>5){
				def interchangeRoadListDiv = InterchangePageConstants.CONSIST_SELECT_BUTTON+'['+i+']/div[1]/div[1]/button'
				try {
					WebElement selectButton = driver.findElement(By.xpath(interchangeRoadListDiv))
					selectButton.click()
				}
				catch (NoSuchElementException) {
					log.logInfo("Button Not Available for the 1St Record")
				}
				for(int n=1;n<=5;n++) {
					String equipmentText=driver.findElement(By.xpath(InterchangePageConstants.ALL_CARS_IN_CONSIST+"["+n+"]//*[@class='flex flex--middle flex--justify']")).getText()
					listOfCarIds.add(equipmentText)
				}
				driver.findElement(By.xpath("//div[@class='Group-module_scrollContainer_2YWn_']/div["+i+InterchangePageConstants.CONSIST_FIRST_EQUIPMENT)).click()
				driver.findElement(By.xpath("//div[@class='Group-module_scrollContainer_2YWn_']/div["+i+InterchangePageConstants.CONSIST_FIFTH_EQUIPMENT)).click()
				WebUI.executeJavaScript(InterchangePageConstants.ACTIONS_SELECT_RANGE_SELECTOR, null)
				WebUI.delay(2)
			}
		}
	}
}
