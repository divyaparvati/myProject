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
 * Class for all constants used in Railroad configuration
 */
public class RailroadPageConstants {
	/**
	 *  Constant for Invalid Statiion Zone ID
	 */
	public static String INVALID_STAIONZONE_ID = '1111111111111'

	/**
	 *  Constant for Message When no Station and Zone
	 */
	public static String RAILROAD_MESSAGE_NO_STAION_ZONE =  'No stations or zones found.'

	/**
	 *  Constant for Railroad Leftpane Summary
	 */
	public static String RAILROAD_SUMMERY =  '//*[@id="root"]/div/div/section/section/div[1]/div[2]'

	/**
	 *  Constant for Railroad Setup dropdown
	 */
	public static String RAILROAD_SETUP = 'document.querySelector(\'px-app-nav\').shadowRoot.querySelector(\'#items > px-app-nav-group:nth-child(13) > px-app-nav-subitem:nth-child(1)\').click()'

	/**
	 * Constant for Select Railroad dropdown
	 */
	public static String SELECT_RAILROAD_DROPDOWN = 'document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * Constant for search railroad 
	 */
	public static String SEARCH_RAILROAD = "document.querySelector('#railroads-select-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown > div #searchbox').value ='"

	/**
	 * Constant for getting all the railroads from the Select Railroad dropdown
	 */
	public static String RAILROAD_DROPDOWN_DATALIST = 'let railRoadList=[]; document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelectorAll(\'#selector > div.dropdown-option\').forEach(node => { railRoadList.push(node.getAttribute(\'title\')) }); return railRoadList'

	/**
	 * Constant to select a railroad from Select Railroad dropdown
	 */
	public static String SELECT_ANY_RAILRAOD_FROM_DROPDOWN = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(2) span\').innerHTML'

	/**
	 * Constant to show the dropdown value thats selected
	 */
	public static String RAILROAD_DROPDOWN_SELECTED_VALUE = 'return document.querySelector(\'#railroads-select-dropdown\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#label\').innerHTML'

	/**
	 *  Constant to get all stations under a railroad
	 */
	public static String STATION_LIST_UNDER_RAILROAD = 'return document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\').length'

	/**
	 * CONSTANTS FOR STATION SCREEN
	 */

	/**
	 * Constant to click the first station in the left navigation list
	 */
	public static final String CLICK_FIRST_STATION_IN_LIST = 'document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\')[0].click()'

	/**
	 *  Constant to get text of selected station
	 */
	public static String STATION_SELECTED = 'return document.querySelector(\'px-tree\').shadowRoot.querySelector(\'ul > px-tree-node\').shadowRoot.querySelector(\'li.tree__branch.active.selected > span\').innerHTML'

	/**
	 *   Constant to click on add station splc field
	 */
	public static String STATION_SPLC = 'document.querySelector(\'#splc\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 *   Constant to click on add station Fsac field
	 */
	public static String STATION_FSAC = 'document.querySelector(\'#fsac\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 *   Constant to click on add station country field
	 */
	public static String STATION_COUNTRY = 'document.querySelector(\'#country\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 *   Constant to click on add station state field
	 */
	public static String STATION_STATE = 'document.querySelector(\'#state\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 *   Constant to click on add station time zone field
	 */
	public static String STATION_TIME_ZONE = 'document.querySelector(\'#station-timezone-qualifier\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 *   Constant length of dropdown of station SPLC
	 */
	public static String STATION_SPLC_LENGTH = 'return document.querySelector(\'#splc\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelectorAll(\'#dropdown > div > #selector > div\').length'

	/**
	 *   Constant length of  dropdown of station FSAC
	 */
	public static String STATION_FSAC_LENGTH = 'return document.querySelector(\'#fsac\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelectorAll(\'#dropdown > div > #selector > div\').length'

	/**
	 *   Constant length of  dropdown of station-timezone-qualifier
	 */
	public static String STATION_TIME_ZONE_LENGTH = 'return document.querySelector(\'#station-timezone-qualifier\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelectorAll(\'#dropdown > div > #selector > div\').length'

	/**
	 *   Constant length of dropdown of station country
	 */
	public static String STATION_COUNTRY_LENGTH = 'return document.querySelector(\'#country\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelectorAll(\'#dropdown > div > #selector > div\').length'

	/**
	 *   Constant length of dropdown of station state
	 */
	public static String STATION_STATE_LENGTH = 'return document.querySelector(\'#state\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelectorAll(\'#dropdown > div > #selector > div\').length'

	/**
	 *  Constant for station list in UI
	 */
	public static String STATION_LIST_UI = 'let stationList=[]; document.querySelector(\'px-tree\').shadowRoot.querySelectorAll(\'px-tree-node\').forEach(node => { stationList.push(node.shadowRoot.querySelector(\'li>span\').innerHTML)}); return stationList'

	/**
	 * Constant for Empty Station Id error message
	 */
	public static final String STATION_ID_EMPTY_ERROR_MESSAGE = 'STATION ID CANNOT BE EMPTY'

	/**
	 * Constant for Empty Station Name error message SPLC CANNOT BE EMPTY
	 */
	public static final String STATION_NAME_EMPTY_ERROR_MESSAGE = 'STATION NAME CANNOT BE EMPTY'

	/**
	 * Constant for Empty Station SPLC error message
	 */
	public static final String STATION_SPLC_EMPTY_ERROR_MESSAGE = 'SPLC CANNOT BE EMPTY'

	/**
	 * Constant for Empty Station FSAC error message
	 */
	public static final String STATION_FSAC_EMPTY_ERROR_MESSAGE = 'FSAC CANNOT BE EMPTY'

	/**
	 * Constant for Empty Station Time Zone error message 
	 */
	public static final String STATION_STATE_EMPTY_ERROR_MESSAGE = 'STATE CANNOT BE EMPTY'

	/**
	 * Constant for Empty Station Country error message
	 */
	public static final String STATION_COUNTRY_EMPTY_ERROR_MESSAGE = 'COUNTRY CANNOT BE EMPTY'

	/**
	 * Constant for Empty Station Time Zone error message STATE CANNOT BE EMPTY
	 */
	public static final String STATION_TIMEZONE_EMPTY_ERROR_MESSAGE = 'TIME ZONE CANNOT BE EMPTY'

	/**
	 * Constant to Deselect Station SPLC
	 */
	public static final String STATION_SPLC_DESELECT = 'document.querySelector(\'#splc\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()'

	/**
	 * Constant to Deselect Station TimeZone
	 */
	public static final String STATION_TIMEZONE_DESELECT = 'document.querySelector(\'#station-timezone-qualifier\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()'

	/**
	 * Constant to Deselect Station FSAC
	 */
	public static final String STATION_FSAC_DESELECT = 'document.querySelector(\'#fsac\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()'

	/**
	 * Constant to Deselect Station state
	 */
	public static final String STATE_STATE_DESELECT = 'document.querySelector(\'#state\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()'

	/**
	 * Constant to Deselect Station Country
	 */
	public static final String STATION_COUNTRY_DESELECT = 'document.querySelector(\'#country\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()'

	/**
	 * Constant for Action menu in View Station
	 */
	public static final String CLICK_STATION_ACTIONS_MENU = 'document.querySelector(\'#station-actions-dropdown\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * Constant for Clicking the Edit Station 
	 */
	public static final String CLICK_EDIT_STATION_BUTTON = 'document.querySelector(\'#station-actions-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(1)\').click()'

	/**
	 * Constant for clicking the delete station
	 */
	public static final String CLICK_DELETE_STATION_BUTTON = 'document.querySelector(\'#station-actions-dropdown\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(2)\').click()'

	/**
	 * Constant to get the label on Station Time zone
	 */
	public static final String STATION_TIMEZONE_LABEL = 'return document.querySelector(\'#station-timezone-qualifier\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#label\').innerHTML'

	/**
	 * Constant for delete confirmation message when station has zone first part
	 */
	public static final String DELETE_MESSAGE_FIRST_HALF = "You cannot delete "

	/**
	 * Constant for delete confirmation message when station has zone second part
	 */
	public static final String DELETE_MESSAGE_SECOND_HALF_ONLY_ZONE = " until you first delete all its 1 zone(s)"

	/**
	 * Constant for delete confirmation message when station has zone and track second part
	 */
	public static final String DELETE_MESSAGE_SECOND_HALF_WITH_ZONE_TRACK = " until you first delete all its 1 zone(s) and 1 track(s)."

	/**
	 * Constant for delete confirmation message when zone has tracks second part
	 */
	public static final String DELETE_MESSAGE_SECOND_HALF_ONLY_TRACK = " until you first delete all of its 1 track(s)."

	/**
	 * Constant for Duplicate Station Error message
	 */
	public static final String DUPLICATE_STATION_ERROR_MESSAGE = "STATION ID ALREADY EXISTS"

	/**
	 * ZONE SCREEN CONSTANTS 
	 */

	/**
	 * Constant for duplicate zone error message
	 */
	public static final String DUPLICATE_ZONE_ERROR_MESSAGE = "ZONE ID ALREADY EXISTS"

	/**
	 * Constant to click on the first zone in the left navigation panel after search
	 */
	public static final String CLICK_FIRST_ZONE_IN_LIST = "document.querySelector(\"px-tree\").shadowRoot.querySelector(\"px-tree-node\").shadowRoot.querySelector(\"px-tree-node\").shadowRoot.querySelector(\"li span\").click()"

	/**
	 * Constant for Zone Type Classification
	 */
	public static final String CLASSIFICATION = "Classification";

	/**
	 * Constant for Zone Type Phantom
	 */
	public static final String PHANTOM = "Phantom";

	/**
	 * Constant for Zone Type Switching District
	 */
	public static final String SWITCHING_DISTRICT = "Switching District";

	/**
	 * Constant for Zone Type Yard
	 */
	public static final String YARD = "Yard";

	/**
	 * Constant for zone type drop down in Add/Edit Zone
	 */
	public static final String ZONE_TYPE = "document.querySelector(\'#zone-type\').shadowRoot.querySelector(\'#trigger\').click()";

	/**
	 * Constant for de-selecting ZONE type dropdown
	 */
	public static final String ZONE_TYPE_DROPDOWN_DESELECT = "document.querySelector(\'#zone-type\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()";

	/**
	 * Constant for selecting the Zone type as Classification
	 */
	public static final String CLASSIFICATION_SELECTOR = "document.querySelector('#zone-type').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(1)').click()";

	/**
	 * Constant for selecting the Zone type as Phantom
	 */
	public static final String PHANTOM_SELECTOR = "document.querySelector('#zone-type').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(2)').click()";

	/**
	 * Constant for selecting the Zone type as Switching District
	 */
	public static final String SWITCHING_DISTRICT_SELECTOR = "document.querySelector('#zone-type').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(3)').click()";

	/**
	 * Constant for selecting the Zone type as Yard
	 */
	public static final String YARD_SELECTOR = "document.querySelector('#zone-type').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(4)').click()";

	/**
	 * Constant for Empty Zone Id error message
	 */
	public static final String ZONE_ID_EMPTY_FIELD_ERROR_MESSAGE = "ZONE ID CANNOT BE EMPTY";

	/**
	 * Constant for Empty Zone name error message
	 */
	public static final String ZONE_NAME_EMPTY_FIELD_ERROR_MESSAGE = "ZONE NAME CANNOT BE EMPTY";

	/**
	 * Constant for Empty Zone Type error message
	 */
	public static final String ZONE_TYPE_EMPTY_FIELD_ERROR_MESSAGE = "ZONE TYPE CANNOT BE EMPTY";
	/**
	 * Constant for Action Menu in View Zone
	 */
	public static final String CLICK_ZONE_ACTION_MENU = "document.querySelector('#zone-actions-dropdown').shadowRoot.querySelector('#trigger').click()"

	/**
	 * Constant for Edit Zone Button
	 */
	public static final String CLICK_EDIT_ZONE_BUTTON = "document.querySelector('#zone-actions-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(1)').click()"

	/**
	 * Constant for getting the total size of the Zone Type
	 */
	public static final String ZONE_TYPE_LENGTH = "return document.querySelector('#zone-type').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#selector > div').length"

	/**
	 * Constant for Zone type label
	 */
	public static final String ZONE_TYPE_LABEL = "return document.querySelector(\'#zone-type\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#label\').innerHTML"

	/**
	 * Constant for Delete zone button
	 */
	public static final String CLICK_DELETE_ZONE_BUTTON = "document.querySelector('#zone-actions-dropdown').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child(2)').click()"

	/**
	 * Constant for Track type length
	 */
	public static final String TRACK_TYPE_LENGTH = "return document.querySelector('#trackType').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for Track Condition code
	 */
	public static final String TRACK_CONDITION_CODE = "return document.querySelector('#trackConditionCode').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 *  Constant for Error Message Track Id Empty Field
	 */
	public static final String TRACK_ID_EMPTY_ERROR_MESSAGE = 'TRACK ID CANNOT BE EMPTY'

	/**
	 *  Constant for Error Message Track Description Empty Field
	 */
	public static final String TRACK_DESCRIPTION_EMPTY_ERROR_MESSAGE = 'TRACK DESCRIPTION CANNOT BE EMPTY'

	/**
	 *  Constant for Error Message Track Type Empty Field
	 */
	public static final String TRACK_TYPE_EMPTY_ERROR_MESSAGE = 'TRACK TYPE CANNOT BE EMPTY'

	/**
	 *  Constant for Error Message Track Length Empty Field 
	 */
	public static final String TRACK_LENGTH_EMPTY_ERROR_MESSAGE = 'TRACK LENGTH CANNOT BE EMPTY'

	/**
	 *  Constant for Error Message Track Type dropdown
	 */
	public static final String TRACK_TYPE_DROPDOWN = 'document.querySelector(\'#trackType\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * Constant to Deselect Track Type
	 */
	public static final String TRACK_TYPE_DESELECT = 'document.querySelector(\'#trackType\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'px-icon\').click()'

	/**
	 * Constant for no Rows found in Track table
	 */
	public static final TRACK_NO_ROWS_FOUND_TEXT = "No rows found"

	/**
	 * Constant for selected Track Type 
	 */
	public static final String TRACK_TYPE_SELECTED = 'return document.querySelector(\'#trackType\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#label\').innerHTML'

	/**
	 * Constant for message of Duplicate Track Id Record
	 */
	public static final TRACKID_DUPLICATE_ERROR_MESSAGE = 'TRACK ID ALREADY EXISTS'

	/**
	 *  Constant for click of zone of dropdown 
	 */
	public static final String ADD_TRACK_ZONEID_DROPDOWN = 'document.querySelector(\'#zoneId\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * Constant for zoneId Length 
	 */
	public static final String ADD_TRACK_ZONEID_LENGTH = 'return document.querySelector(\'#zoneId\').shadowRoot.querySelector(\'px-overlay-content > #content\').shadowRoot.querySelectorAll(\'#dropdown > div > #selector > div\').length'

	/**
	 *  Constant for click on station ID after selection  
	 */
	public static final String ADD_TRACK_STATIONID_CLICK = 'document.querySelector(\'px-tree\').shadowRoot.querySelector(\'px-tree-node\').shadowRoot.querySelector(\'li\').click()'

	/**
	 * Constant for zoneId click
	 */
	public static final String ADD_TRACK_ZONEID_CLICK = 'document.querySelector(\'px-tree\').shadowRoot.querySelector(\'px-tree-node\').shadowRoot.querySelector(\'#collapse > div > px-tree-node\').shadowRoot.querySelector(\'li\').click()'

	/**
	 * Constant Delete message text 1
	 */
	public static final String STATION_DELETE_MESSAGE_TEXT1 = 'You cannot delete '

	/**
	 * Constant Delete message text 2
	 */
	public static final String STATION_DELETE_MESSAGE_TEXT2 = ' until you first delete all its '

	/**
	 * Constant Delete message text 3
	 */
	public static final String STATION_DELETE_MESSAGE_TEXT3 = ' zone(s) and '

	/**
	 * Constant Delete message text 4
	 */
	public static final String STATION_DELETE_MESSAGE_TEXT4 = ' track(s).'

	/**
	 * Track ID in search
	 */
	public static final String trackID ="//div[@class='rt-tr -odd'][1]//div[1]"

	/**
	 * Track Description in search
	 */
	public static final String trackDescription ="//div[@class='rt-tr -odd'][1]//div[2]"

	/**
	 * Track Type in search
	 */
	public static final String trackType ="//div[@class='rt-tr -odd'][1]//div[3]"

	/**
	 * Track Length in search
	 */
	public static final String trackLength ="//div[@class='rt-tr -odd'][1]//div[4]"

	/**
	 * car Capacity in search
	 */
	public static final String carCapacity ="//div[@class='rt-tr -odd'][1]//div[5]"

	/**
	 * Condition Code in search
	 */
	public static final String conditionCode ="//div[@class='rt-tr -odd'][1]//div[6]"
}
