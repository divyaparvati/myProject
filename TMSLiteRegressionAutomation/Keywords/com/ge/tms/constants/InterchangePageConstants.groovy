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

/**
 * Class for all constants used in Manage Interchange Roads
 */
public class InterchangePageConstants {

	/**
	 * Constant for clicking the Manage Customer tab
	 */
	public static final String MANAGE_INTERCHANGE_ROAD_TAB = "document.querySelector('px-app-nav').shadowRoot.querySelector('#items > px-app-nav-group:nth-child(7) > px-app-nav-subitem:nth-child(1)').click()"

	/**
	 * Constant for Manage Interchange Text
	 */
	public static final String MANAGE_INTERCHANGE_TEXT = "Manage Interchanges"

	/**
	 * Constant for Interchange roads list class
	 */
	public static final String INTERCHANGE_ROAD_CONSIST_LIST_DIV = "//div[@class='flex flex--col Group-module_root_hKwAm']/div/div"

	/**
	 * Constant for No Interchange found message TEXT
	 */
	public static final String NO_INTERCHAGE_FOUND_MESSAGE_TEXT = "No Interchanges found."

	/**
	 * Constant for consist car list div in the right panel
	 */
	public static final String INTERCHANGE_ROAD_CONSIST_CAR_LIST_DIV = "//*[@class='flex__item flex flex--middle RailPath-module_path_1gTHH']/div"

	/**
	 * Constant for consist car list span to click on the car
	 */
	public static final String LIST_SPAN = "//span"

	/**
	 * Constant for Interchange Type Dropdown
	 */
	public static final String INTERCHANGE_TYPE_DROPDOWN = '//*[@class="btn btn--bare btn--icon Selectors-module_chevronBtn_1n2C9"]//*'

	/**
	 * Constant for Interchange Type Dropdown value Inbound
	 */
	public static final String INTERCHANGE_TYPE_DROPDOWN_INBOUDONLY = '//*[@id="root"]/div/div/section/header/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/span'

	/**
	 * Constant for Interchange Type Dropdown value Outbound
	 */
	public static final String INTERCHANGE_TYPE_DROPDOWN_OUTBOUDONLY = '//*[@id="root"]/div/div/section/header/div[2]/div[1]/div[2]/div[2]/div/div/div[3]'

	/**
	 * Constant for Interchange Type Dropdown Value invound/outbound
	 */
	public static final String INTERCHANGE_LEFTPANE = "//div[@class='flex flex--col Group-module_root_hKwAm']//div[@class='Group-module_scrollContainer_2YWn_']"

	/**
	 * Constant for Interchange Type Dropdown Value invound/outbound
	 */
	public static final String INTERCHANGE_INBOUNDOUTBOUNDLABLE = "//div[@class='flex flex--col Group-module_root_hKwAm']//span[@class='u-ml- Interchanges-module_directionColor_6-eSs']"

	/**
	 * Constant for Interchange Type Dropdown Value invound/outbound
	 */
	public static final String INTERCHANGE_TYPE_DROPDOWN_INBOUNDOUTBOUDND = '//*[@id="root"]/div/div/section/header/div[2]/div[1]/div[2]/div[2]/div/div/div[1]'

	/**
	 * Interchange type Inbound
	 */
	public static final String INBOUND = "Inbound"

	/**
	 * Interchange type Outbound
	 */
	public static final String OUTBOUND = "Outbound"

	/**
	 * Car list containing in first consist
	 */
	public static final String INTERCHANGE_ROAD_CONSIST_CAR_LIST_PER_CONSIST_PATH = '//*[@id="root"]/div/div/section/section/div[1]/div[2]/div/div[2]/div[1]/div[1]/div'

	/**
	 * Interchange Sortby Time
	 */
	public static final String TIME = "Time"

	/**
	 * Interchange Sortby Station
	 */
	public static final String STATION = "Station"

	/**
	 * Interchange Sortby Type
	 */
	public static final String TYPE = "Type"

	/**
	 * Interchange Sortby Railroad
	 */
	public static final String RAILROAD = "Railroad"

	/**
	 * Interchange Sortby TrainID
	 */
	public static final String TRAINID = "TrainID"

	/**
	 * Selected Cars xpath
	 */
	public static final String SELECTED_CARS_XPATH="//div[@aria-roledescription='Draggable item. Press space bar to lift']"

	/**
	 * Unselected cars xpath
	 */
	public static final String UNSELECTED_CARS_XPATH="//div[@aria-roledescription='Draggable item. Press space bar to lift']//div[@class='u-p-- Car-module_root_1BPIb']"

	/**
	 * All Cars in consist
	 */
	public static final String ALL_CARS_IN_CONSIST="//div[@class='Group-module_scrollContainer_2YWn_']/div[1]//*[@class='flex__item flex scroll-y']//*[@class='flex__item flex flex--middle RailPath-module_path_1gTHH']//div[@aria-roledescription='Draggable item. Press space bar to lift']"

	/**
	 * First Car in one consist - xpath
	 */
	public static final String FIRST_CONSIST_XPATH="//div[@class='Group-module_scrollContainer_2YWn_']/div[1]//*[@class='flex__item flex scroll-y']//*[@class='flex__item flex flex--middle RailPath-module_path_1gTHH']"

	/**
	 * Select All Cars Selector - Actions
	 */
	public static final String SELECT_ALL_ACTION = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(1)').click()"

	/**
	 * Deselect All Selector - Actions
	 */
	public static final String DESELECT_ALL_ACTION = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(2)').click()"

	/**
	 * Inbound Cars Selector - Actions
	 */
	public static final String ACTIONS_INBOUND_CARS_SELECTOR = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(4)').click()"

	/**
	 * Remove Cars Selector - Actions
	 */
	public static final String ACTIONS_REMOVE_CARS_SELECTOR = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(5)').click()"

	/**
	 * Add Cars Selector - Actions
	 */
	public static final String ACTIONS_ADD_CARS_SELECTOR = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(6)').click()"

	/**
	 * Flip Cars Selector - Actions
	 */
	public static final String ACTIONS_FLIP_CONSIST_SELECTOR = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(8)').click()"

	/**
	 * Select Track in Inbound cars popup
	 */
	public static final String SELECT_TRACK_INBOUND_POPUP_SELECTOR="document.querySelector('body > div.ReactModalPortal > div > div > div').lastChild.shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(1)').click()"

	/**
	 * Station ID Selector
	 */
	public static final String STATION_ID_SELECTOR="return document.querySelector('#root > div > div > section > section > div:nth-child(1) > div.Group-module_scrollContainer_2YWn_ > div:nth-child(1) > div.u-p.Interchanges-module_detailsWidth_3Ck7u.Interchanges-module_enabled_FXXoX > div:nth-child(2) > div:nth-child(4) > span').innerHTML"

	/**
	 * Car Id - Add Car Action
	 */
	public static final String CAR_ID = "ZAZB20455"

	/**
	 * Car Type - Add Car Action
	 */
	public static final String CAR_TYPE = "C614"

	/**
	 * Commodity Code text
	 */
	public static final String COMMODITY_CODE = "STCC001"

	/**
	 * DESTINATION STATION text
	 */
	public static final int DESTINATION_STATION= 100

	/**
	 * BLOCK TO text
	 */
	public static final String BLOCK_TO = "ABC"

	/**
	 * Notes text
	 */
	public static final String NOTES = "This is Bad Ordered Car"

	/**
	 * Quick Car View Hazmat type value No
	 */
	public static final String QUICK_CAR_VIEW_UI_HAZMAT_TYPE_NO = "No"

	/**
	 * Quick Car View Hazmat type value from API false 
	 */
	public static final String QUICK_CAR_VIEW_API_HAZMAT_TYPE_FALSE = "false"

	/**
	 * Quick Car View Hazmat type value from API true
	 */
	public static final String QUICK_CAR_VIEW_API_HAZMAT_TYPE_TRUE = "true"

	/**
	 * Select Range Selector - Actions
	 */
	public static final String ACTIONS_SELECT_RANGE_SELECTOR = "document.querySelector('#root #interchange-actions-menu').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(3)').click()"

	/**
	 * Left Pane Consist select button
	 */
	public static final String CONSIST_SELECT_BUTTON = '//*[@class="flex flex--col Group-module_root_hKwAm"]/div[2]/div'

	/**
	 * Left Pane Consist select button
	 */
	public static final String CONSIST_FIRST_EQUIPMENT = "]//div[@aria-roledescription='Draggable item. Press space bar to lift'][1]//*[@class='flex flex--middle flex--center Car-module_button_3OkLC']"

	/**
	 * Left Pane Consist select button
	 */
	public static final String CONSIST_FIFTH_EQUIPMENT = "]//div[@aria-roledescription='Draggable item. Press space bar to lift'][5]//*[@class='flex flex--middle flex--center Car-module_button_3OkLC']"

	/**
	 * Selected cars of the consist
	 */
	public static final String SELECTED_CARS = "*//div[@class='u-p-- Car-module_root_1BPIb Car-module_selected_24bVV']"
	}
