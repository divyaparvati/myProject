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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ByXPath
import org.openqa.selenium.remote.server.handler.FindElement

import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By

import com.ge.tms.constants.WayBillPageConstants;
import com.ge.tms.util.CommonUtilities
import com.ge.tms.waybillVerify.WaybillVerification

import java.util.Map;
import java.util.HashMap;

/**
 * Class for all actions related to Waybill management screen
 */
public class WaybillActions {

	/**
	 * Object to hold randomCarID
	 */
	public  String randomCarID

	/**
	 * Object to hold shipper
	 */
	public  String shipper

	/**
	 * Object to hold consignee
	 */
	public  String consignee

	/**
	 * Object to hold origin
	 */
	public  String origin

	/**
	 * Object to hold commodity
	 */
	public  String commodity

	/**
	 * Map With Key and value for a way bill actions
	 */
	Map<String,String> wayBillKeyValuesMap= new HashMap<String,String>()

	/**
	 * Constructor
	 */
	WaybillActions(){

		/**
		 * Adding Keys which are used in API response and map to UI
		 */
		wayBillKeyValuesMap.put("errors.count",WayBillPageConstants.ERROR_COUNT);
		wayBillKeyValuesMap.put("commodity.commodityDescription.commodityCode",WayBillPageConstants.STCC_NUMBER);
		wayBillKeyValuesMap.put("referenceInformation.waybillStatus",WayBillPageConstants.WAYBILL_STATUS)
	}

	/**
	 * Object to hold Commodity Number
	 */
	WebElement commodityNumber;

	/**
	 * Creating the driver instance using Driver Factory
	 */
	WebDriver driver = DriverFactory.getWebDriver()

	/**
	 * Creating instance of CommonUtilities
	 */
	CommonUtilities util = new CommonUtilities()

	/**
	 * method to get the commodity number from the waybill list items
	 * @return commodityNumber
	 */
	def getCommodityNumber(){
		commodityNumber = driver.findElement(By.xpath("//*[contains(@data-testid,'waybill-item-')]//following::span[8]")).getText()
		
		/** 
		 * Returning the commodity number
		 */
		return commodityNumber;
	}

	/**
	 * Method to get value for the given key
	 * @Param key
	 */
	def getValueForGivenKey(String key){
		return wayBillKeyValuesMap.get(key)
	}

	/**
	 * click of CarID, child 1 in Waybill Search dropdown
	 */
	def clickWaybillSearchDropdown(){
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#trigger").click()',
				null)
	}
	/**
	 * click of CarID, child 1 in Waybill Search dropdown
	 */
	def clickWaybillSearchCarID(){
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()',
				null)
	}

	/**
	 * Set Random Car ID
	 */
	def setRandomCarIDForSearch(){
		List<WebElement> listOfWaybillElements = driver.findElements(By.xpath(WayBillPageConstants.DIV_ITEMS_LEFTPANE_XPATH))
		int size = listOfWaybillElements.size()
		randomCarID = driver.findElement(By.xpath('//*[@id="root"]/div/div/section/section/div[1]/section/div['+util.generateRandomNumber(size)+']/div/div[1]/div/div/span')).getText()
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), randomCarID)

	}

	/**
	 * Click on filter icon
	 */
	def clickWaybillFilter(){
		driver.findElement(By.xpath(WayBillPageConstants.WAYBILLFILTERICON)).click()
	}

	/**
	 * Select Load/Empty dropdown value - Loaded
	 */
	def selectEmptyfromwaybillLE(){
		WebUI.executeJavaScript(WayBillPageConstants.WAYBILL_EMPTY_SELECTOR, null)
	}

	/**
	 * Click on Apply button
	 */
	def clickWaybillFilterApply(){
		WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
	}

	/**
	 *  Sort dropdown click
	 */
	def clickWaybillSortdropdown(){
		WebUI.executeJavaScript(WayBillPageConstants.sortDropDown, null)
	}

	/**
	 *  set Waybill number from sort dropdown
	 */
	def setWaybillnumbersortdropdown(){
		WebUI.executeJavaScript(WayBillPageConstants.wayBillNumberInSortDropdown, null)
	}

	/**
	 * Clear Search text Field
	 */
	def clearSearchText(){
		WebUI.clearText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'))
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), ' ')
	}

	/**
	 * click of Shipper, child 4 in dropdown
	 */
	def clickWaybillSearchShipper(){
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(4) > span").click()',
				null)
	}

	/**
	 * Set Random Shipper
	 */
	def setRandomShipperForSearch(){
		shipper = driver.findElement(By.xpath(WayBillPageConstants.WAYBILLSHIPPERRIGHTPANE)).getText()
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), shipper)
	}

	/**
	 * Select Hazmant dropdown value - Has Hazmat
	 */
	def selectHasHazmatfromFilter(){
		WebUI.executeJavaScript(WayBillPageConstants.HAS_HAZMAT_SELECTOR, null)
	}

	/**
	 * click on Clear Filters
	 */
	def clearFilter(){
		WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/clearFilterBtn'))
	}

	/**
	 * Sorted by Car id in descending order
	 */
	def setCarIDsortdropdown(){
		WebUI.executeJavaScript(WayBillPageConstants.carIdInSortDropdown, null)
		WebUI.delay(1)
	}

	/**
	 * click of Consignee, child 5 in dropdown
	 */
	def clickWaybillConsignee(){
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(5) > span").click()',
				null)
	}

	/**
	 * Set Random Consignee
	 */
	def setConsigneeForSearch(){
		consignee = driver.findElement(By.xpath(WayBillPageConstants.WAYBILLCONSIGNEERIGHTPANE)).getText()
		WebUI.setText(findTestObject('Object Repository/CommonObjects/searchTextBoxLeftNav'), consignee)
	}

	/**
	 * Set Origin for the Filter
	 */
	def setOriginFilter(){
		origin = driver.findElement(By.xpath(WayBillPageConstants.WAYBILLORIGINRIGHTPANE)).getText()
		driver.findElement(By.xpath(WayBillPageConstants.WAYBILLFILTERORIGIN)).sendKeys(origin)
	}

	/**
	 * Set Commodity sort
	 */
	def setCommoditysortdropdown(){
		WebUI.executeJavaScript(WayBillPageConstants.commodityInSortDropdown, null)
	}

	/**
	 * Set Origin for the Filter
	 */
	def setInvalidOriginFilter(){
		origin = util.generateRandomString(5)
		driver.findElement(By.xpath(WayBillPageConstants.WAYBILLFILTERORIGIN)).sendKeys(origin)
	}

	/**
	 * Set Origin for the Filter
	 */
	def setInvalidCommodityFilter(){
		commodity = util.generateRandomNumberLeftPane(1)
		WebUI.setText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_filter_commodity_input'),commodity)
	}

	/**
	 *  Get No Result Message Leftpane
	 */
	def GetNoresultText(){
		driver.findElement(By.xpath(WayBillPageConstants.LEFTPANEERRORMESSAGE)).getText()
	}

	/**
	 *  Remove default Active filter from waybill screen
	 */
	def removeActiveFilter(){
		WebUI.click(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_remove_active_filter'))
	}

	/**
	 *  Click on First record
	 */
	def clickOnFirstRecord(){
		WebUI.click(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_firstrecord_leftpane'))
	}

	/**
	 * Method to send text in search field
	 * @param textForSearch
	 */ 
	def setTextForSearch(String textForSearch) {
		WebUI.setText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/serachfield_waybill_id'),
				textForSearch)
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
}
