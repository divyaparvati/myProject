package com.ge.tms.constants
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

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.junit.After
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

/**
 * Constant class for Waybill Objects 
 */
public class WayBillPageConstants {


	/**
	 *  Sort Dropdown Selector
	 */
	public static String sortDropDown = 'document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#trigger").click()'

	/**
	 *  Car ID  option Sort Dropdown Selector
	 */
	public static String carIdInSortDropdown = 'document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3) > span").click()'

	/**
	 *  WayBill Number option Sort Dropdown Selector
	 */
	static String wayBillNumberInSortDropdown = 'document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").click()'

	/**
	 *  Commodity option Sort Dropdown Selector
	 */
	static String commodityInSortDropdown = 'document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(4) > span").click()'

	/**
	 *  Errors option Sort Dropdown Selector
	 */
	static String errorsInSortDropdown = 'document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(7) > span").click()'

	/**
	 *   Leftpane div selector
	 */
	static String WaybillLeftPaneDivFull = 'id("root")/div[@class="flex flex--col App-module_appRoot_2otsY"]/div[@class="flex__item App-module_routesRoot_yEKqQ"]/section[@class="flex flex__item flex--col WaybillContainer-module_root_366LY view"]/section[@class="flex flex--row"]/div[@class="Sidebar-module_root_16T7c flex__item flex flex--col"]/section[@class="flex__item scroll-y"]'

	/**
	 *   Search By Dropdown button
	 */
	public static final String SEARCH_BY_DROPDOWN = 'document.querySelector("#search-criteria-dropdown").shadowRoot.querySelector("#trigger").shadowRoot.querySelector("#trigger").click()'

	/**
	 *   Search By Equipment ID
	 */
	public static final String SEARCH_BY_EQUIPMENT_ID ="document.querySelector('#search-criteria-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown > div #selector > div').click()"

	/**
	 *   Search By Waybill Number
	 */
	public static final String SEARCH_BY_WAYBILL_NUMBER ="document.querySelector('#search-criteria-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown > div #selector > div:nth-child(2)').click()"

	/**
	 *   Search By Sender
	 */
	public static final String SEARCH_BY_SENDER ="document.querySelector('#search-criteria-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown > div #selector > div:nth-child(3)').click()"

	/**
	 *   Search By Shipper
	 */
	public static final String SEARCH_BY_SHIPPER ="document.querySelector('#search-criteria-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown > div #selector > div:nth-child(4)').click()"

	/**
	 *   Search By Consignee
	 */
	public static final String SEARCH_BY_CONSIGNEE ="document.querySelector('#search-criteria-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown > div #selector > div:nth-child(5)').click()"

	/**
	 *   Leftpane subdiv selector
	 */
	static String WaybillLeftPaneSubdiv = "//*[@class ='flex__item scroll-y']/div"

	/**
	 * Ascending or Descending order Icon Button
	 */
	static String OrderingIconWaybill  = '//*button[@class ="btn btn--bare btn--icon QueryController-module_sortDirectionButton_GqyeD"]'

	/**
	 * View Waybill navigation selector on the top navigation pane
	 */
	public static final  String VIEW_WAYBILL_SELECTOR = "document.querySelector('px-app-nav').shadowRoot.querySelector('#items > px-app-nav-group:nth-child(10) > px-app-nav-subitem:nth-child(1)').shadowRoot.querySelector('p').click()"

	/**
	 * Waybill Status Selector - Active
	 */
	public static final String ID="referenceInformation.waybillStatus"

	public static final String WAYBILL_STATUS_ACTIVE="document.querySelector('#' + CSS.escape('referenceInformation.waybillStatus')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(1)').click()"
	/**
	 * Waybill Status Selector - Inbound
	 */
	public static final String WAYBILL_STATUS_INBOUND="document.querySelector('#' + CSS.escape('referenceInformation.waybillStatus')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(2)').click()"

	/**
	 * Waybill Status Selector - Completed
	 */
	public static final String WAYBILL_STATUS_COMPLETED="document.querySelector('#' + CSS.escape('referenceInformation.waybillStatus')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(3)').click()"

	/**
	 * Active Status Constant
	 */
	public static final String ACTIVE_STATUS = "Active"

	/**
	 * Inbound Status Constant
	 */
	public static final String INBOUND_STATUS = "Inbound"

	/**
	 * Completed Status Constant
	 */
	public static final String COMPLETED_STATUS = "Completed"

	/**
	 * XPATH for Waybill List Items in the left pane 
	 */
	public static final String DIV_ITEMS_LEFTPANE_XPATH = "//div[contains(@class,'flex flex--col WaybillSummary-module_root_19TdE')]"

	/**
	 * Waybill LoadEmpty Selector - LOAD
	 */
	public static final String WAYBILL_LOAD_SELECTOR="document.querySelector('#' + CSS.escape('equipmentDetails.loadStatus')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(1)').click()"

	/**
	 * Waybill LoadEmpty Selector - EMPTY
	 */
	public static final String WAYBILL_EMPTY_SELECTOR="document.querySelector('#' + CSS.escape('equipmentDetails.loadStatus')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(2)').click()"

	/**
	 * Waybill Load
	 */
	public static final String LOAD="LOADED"

	/**
	 * Waybill Empty
	 */
	public static final String EMPTY="EMPTY"

	/**
	 * Waybill HAZMAT Selector - Has Hazmat
	 */
	public static final String HAS_HAZMAT_SELECTOR="document.querySelector('#' + CSS.escape('commodity.isHazmat')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(1)').click()"
	/**
	 * Waybill HAZMAT Selector - No Hazmat
	 */
	public static final String NO_HAZMAT_SELECTOR="document.querySelector('#' + CSS.escape('commodity.isHazmat')).shadowRoot.querySelectorAll('#content')[0].shadowRoot.querySelector('#selector > div:nth-child(2)').click()"

	/**
	 * Has Hazmat
	 */
	public static final String HAS_HAZMAT_TEXT = "Hazmat"

	/**
	 * No Hazmat
	 */
	public static final String NO_HAZMAT_TEXT = "No Hazmat"

	/**
	 * Selector for Commodity Text box
	 */
	public static final String COMMODITY_TEXTBOX_SELECTOR= "document.querySelector('#'+CSS.escape('commodity.commodityDescription.commodityCode')).value="

	/**
	 * Customer Tab in Waybill view - Selector
	 */
	public static final String CUSTOMER_TAB_SELECTOR = "document.querySelector('#root > div > div > section > section > div:nth-child(2) > form > section > px-tabs > px-tab:nth-child(2)').click()"

	/**
	 * Customer Tab Header Text
	 */
	public static final String CUSTOMER_TAB_HEADER_TEXT="Customers"

	/**
	 * Customer Table Headers
	 */
	public static final String COMPANY_TEXT="COMPANY"
	public static final String CUSTOMER_ROLE="CUSTOMER ROLE"
	public static final String ADDRESS="ADDRESS"
	public static final String CITY="CITY"
	public static final String STATE="STATE"
	public static final String ZIPCODE="ZIP CODE"
	public static final String COUNTRY="COUNTRY"

	/**
	 * XPATH of table rows
	 */
	public static final String CUSTOMER_ROWS_XPATH="//div[@role='row']"

	/**
	 * Version Tab in Waybill view - Selector
	 */
	public static final String VERSION_TAB_SELECTOR = "document.querySelector('#root > div > div > section > section > div:nth-child(2) > form > section > px-tabs > px-tab:nth-child(3)').click()"

	/**
	 * Selector to get the length of the version nodes
	 */
	public static final String VERSION_LENGTH_SELECTOR = "return document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node').length"

	/**
	 * Version Node Selector
	 */
	public static final String VERSION_TAB_SELECT0R = "document.querySelector('#root px-timeline').shadowRoot.querySelectorAll('#node')"

	/**
	 * No Version Data xpath
	 */
	public static final String NO_VERSION_DATA_XPATH = "//div[@class='box scroll-y']//div"

	/**
	 * No Version Data Text
	 */
	public static final String NO_VERSION_DATA_TEXT ="No version history found."

	/**
	 * Error Count Text
	 */
	public static final String ERROR_COUNT="Error Count "

	/**
	 * STCC NUmber Text
	 */
	public static final String STCC_NUMBER="STCC Number"

	/**
	 * Waybill Status Text
	 */
	public static final String WAYBILL_STATUS="Waybill Status"

	/**
	 *   Leftpane Full selector
	 */
	static String WAYBILLFULLLEFTPANE = "//*[@class='flex__item scroll-y']/div"

	/**
	 *   Leftpane waybill filter icon
	 */
	static String WAYBILLFILTERICON = '//*[@id="root"]/div/div/section/section/div[1]/div/div[2]/div/button'

	/**
	 *   Right pane waybill Shipper
	 */
	static String WAYBILLSHIPPERRIGHTPANE = "//*[contains(@data-testid,'waybill-customer-details')]//following::span[2]"

	/**
	 *   Right pane waybill Consignee
	 */
	static String WAYBILLCONSIGNEERIGHTPANE ="//*[contains(@data-testid,'waybill-consignee-details')]//following::span[2]"

	/**
	 *   Right pane waybill Filter Origin
	 */
	static String WAYBILLFILTERORIGIN ='//*[@id="stations.originStation.address.city"]'

	/**
	 *   Right pane waybill Filter Origin
	 */
	public static final String WAYBILL_FILTER_COMMODITY = "//input[@id='commodity.commodityDescription.commodityCode']"

	/**
	 *   Right pane waybill Origin
	 */
	static String WAYBILLORIGINRIGHTPANE ='//*[@id="root"]/div/div/section/section/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div[1]/div/span'

	/**
	 *   Left pane Error Message
	 */
	static String LEFTPANEERRORMESSAGE ='//*[@id="root"]/div/div/section/section/div[1]/section/div'

	/**
	 *  Constant for Message When wrong search criteria
	 */
	static String WAYBILL_MESSAGE_NO_SEARCH_CRITERIA =  'No waybills found. Check your search criteria.'
}
