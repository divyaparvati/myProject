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
 * Constant file for Rule 15 Offerings
 */
public class Rule15OfferingsPageConstants {

	/**
	 * Rule 15 offerings tab selector
	 */
	public static final String RULE_15_OFFERING_TAB = "document.querySelector('px-app-nav').shadowRoot.querySelector('#items > px-app-nav-group:nth-child(7) > px-app-nav-subitem:nth-child(2)').click()"

	/**
	 * Expected Header Text
	 */
	public static final String HEADER_TEXT_RULE_15_OFFERINGS="Rule 15 Offerings"

	/**
	 * Xpath for List of offerings
	 */
	public static final String LIST_OF_OFFERINGS_LEFTPANE_XPATH = "//div[@data-testid='rule15-summary']"

	/**
	 * Xpath for the equipment(Cars) rows in CARS table
	 */
	public static final String EQUPMENTS_ROWS_XPATH="//div[@role='row']"

	/**
	 * Constant to hold Rule15 left pane count
	 */
	public static final String LEFT_PANE_ALL_ITEM_PATH = "//*[@class='Sidebar-module_Rule15Summary_yaCSd']/div"

	/**
	 * Constant to hold Train Id parameter
	 */
	public static final String TRAIN_ID = "TrainId"

	/**
	 * Constant to hold Station Id parameter
	 */
	public static final String STATION_ID = "StationId"

	/**
	 * Constant to hold Equipment Id parameter
	 */
	public static final String EQUIPMENT_ID = "EquipmentID"

	/**
	 * Constant to hold No offers found message
	 */
	public static final String NO_OFFERS_FOUND_MESSAGE = "No Rule15 Offerings found"

	/**
	 * Constant to click on the To Railroad dropdown
	 */
	public static final String CLICK_ON_TO_RAILROAD = "document.querySelector('#to-railroad').shadowRoot.querySelector('#trigger').click()"

	/**
	 * Constant to get the length of the To Railroad
	 */
	public static final String TO_RAILROAD_LENGTH = "return document.querySelector('#to-railroad').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant to get the inner text of the railroad selected
	 */
	public static final String RAILROAD_SELECTED = "return document.querySelector('#to-railroad').shadowRoot.querySelector('#trigger').shadowRoot.querySelector('#label').innerHTML"

	/**
	 * Constant to click on the To Railroad dropdown
	 */
	public static final String CLICK_ON_TO_STATION = "document.querySelector('#to-station').shadowRoot.querySelector('#trigger').click()"

	/**
	 * Constant to get the length of the To Station
	 */
	public static final String TO_STATION_LENGTH = "return document.querySelector('#to-station').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Xpath for the  equipment id table content
	 */
	public static final String EQUIPMENT_ID_LIST_TABLE = "*//div[@class='rt-tr-group']"

	/**
	 *  Offering Type dropdown in filter
	 */
	public static final String OFFERING_TYPE_DROPDOWN = "document.querySelector('#offeringType').shadowRoot.querySelector('#target > #triggerSlot > #trigger').shadowRoot.querySelector('#trigger > #label').click()"

	/**
	 *  Offering Type dropdown in filter ITEMS
	 */
	public static final String OFFERING_TYPE_ITEMS_LENGTH = "return document.querySelector('#offeringType').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 *  Offering type displayed in UI
	 */
	public static final String OFFERING_TYPE_UI = "return document.querySelector('#offeringType').shadowRoot.querySelector('#target > #triggerSlot > #trigger').shadowRoot.querySelector('#trigger > #label').innerHTML"

	/**
	 *  Station Dropdown in filter
	 */
	public static final String STATION_DROPDOWN = "document.querySelector('#station').shadowRoot.querySelector('#target > #triggerSlot > #trigger').shadowRoot.querySelector('#trigger > #label').click()"

	/**
	 *  Station displayed in UI
	 */
	public static final String STATION_UI = "return document.querySelector('#station').shadowRoot.querySelector('#target > #triggerSlot > #trigger').shadowRoot.querySelector('#trigger > #label').innerHTML"

	/**
	 *  Station dropdown in filter ITEMS
	 */
	public static final String STATION_ITEMS_LENGTH = "return document.querySelector('#station').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 *  Tiles visible after filter is applied
	 */
	public static final String FILTER_CHIP = "document.querySelector('px-chip').shadowRoot.querySelector('#content')"

	/**
	 * Constant to set the minimum range for random number
	 */
	public static final int RANDOM_NUMBER_MIN_RANGE = 100000000

	/**
	 * Constant to set the minimum range for random number
	 */
	public static final int RANDOM_NUMBER_MAX_RANGE = 900000000

	/**
	 * Click Action
	 */
	public static final String CLICK_ACTION = "document.querySelector('#rule15-actions-dropdown').shadowRoot.querySelector('#trigger').shadowRoot.querySelector('#trigger > #label').click()"

	/**
	 * Click Add cars and resend
	 */
	public static final String CLICK_ADD_CARS_AND_RESEND = "document.querySelector('#rule15-actions-dropdown').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelector('#dropdown > div > #selector > div:nth-child(2)').click()"

	/**
	 * Constant to hold Offering Type Outbound
	 */
	public static final String OFFERING_TYPE_OUTBOUND = 'OUTBOUND'

	/**
	 * Constant to hold rule 15 status Rejected
	 */
	public static final String RULE15_STATUS_REJECTED = 'REJECTED'

	/**
	 * Constant to hold rule 15 status Offered
	 */
	public static final String RULE15_STATUS_OFFERED = 'OFFERED'

	/**
	 * Constant to select the Resend Offer in Actions dropdown
	 */
	public static final String SELECT_RESEND_OFFER = "document.querySelector('#rule15-actions-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(1)').click()"

	/**
	 * Constant to deselect the TO railroad dropdown
	 */
	public static final String DESELECT_TO_RAILROAD = "document.querySelector('#to-railroad').shadowRoot.querySelector('#trigger').shadowRoot.querySelector('px-icon').click()"

	/**
	 * Constant to deselect the TO Station dropdown
	 */
	public static final String DESELECT_TO_STATION = "document.querySelector('#to-station').shadowRoot.querySelector('#trigger').shadowRoot.querySelector('px-icon').click()"

	/**
	 * Constant for Railroad error message
	 */
	public static final String ERROR_MESSAGE_RAILROAD = "RESPONSIBLE ROAD CANNOT BE EMPTY"

	/**
	 * Constant for Railroad error message
	 */
	public static final String ERROR_MESSAGE_TO_STATION = "DESTINATION STATION CANNOT BE EMPTY"

	/**
	 * Constant for Train Id empty error message
	 */
	public static final TRAIN_ID_EMPTY_ERROR_MESSAGE = "TRAIN ID CANNOT BE EMPTY"

	/**
	 * Constant for First name Empty Message
	 */
	public static final FIRST_NAME_EMPTY_ERROR_MESSAGE = "FIRST NAME CANNOT BE EMPTY"

	/**
	 * Constant for Last name empty Error message
	 */
	public static final LAST_NAME_EMPTY_ERROR_MESSAGE = "LAST NAME CANNOT BE EMPTY"

	/**
	 * Constant for Phone Number empty Error message
	 */
	public static final PHONE_NUMBER_EMPTY_ERROR_MESSAGE = "PHONE NUMBER CANNOT BE EMPTY"

	/**
	 * Constant hold locator of Offering type
	 */
	public static final LEFTPANE_OFFERING_TYPE = '//*[@data-testid="summary-offering-type"]//span[@class="value Field-module_truncate_N4hFD"]'
}
