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
 * This is to hold constants values related to the equipment actions
 */
public class EquipmentPageConstants {

	/**
	 * Constant to hold Type option from Sort by menu
	 */
	public static final String SORT_BY_TYPE_MENU='document.querySelector("#sort-by-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2").click()'

	/**
	 * Constant to hold Equipment ID option from Sort by menu
	 */
	public static final String SORT_BY_EQUIPMENT_ID_MENU='document.querySelector("#sort-by-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1").click()'

	/**
	 * Constant to hold kind option from Sort by menu
	 */
	public static final String SORT_BY_KIND_MENU= 'document.querySelector("#sort-by-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3").click()'

	/**
	 * Constant to hold Equipment management option from menu
	 */
	public static final String EQUIPMENT_MANAGE_MENU = 'document.querySelector("px-app-nav").shadowRoot.querySelector("#items > px-app-nav-group:nth-child(13) > px-app-nav-subitem:nth-child(3)").click()'

	/**
	 * Constant to hold Equipment sort order icon
	 */
	public static final String SORT_ORDER_ICON ='document.querySelector("#root").querySelector(".Sidebar-module_root_3mUZs.flex__item.flex.flex--col > div.flex__item.QueryController-module_root_2pvTU > div.QueryController-module_sortWrapper_30lGN > button > px-icon").click()'

	/**
	 * Constant to hold Left pane element count
	 */
	public static final String LEFT_PANE_ELEMENT_COUNT ='//*[@class="Selectable-module_title_rBAAt"]'

	/**
	 * Constant variable to hold EquipmentID
	 */
	public static final String SORT_BY_OPTION_EQUIPMENT_ID = "EquipmentID"

	/**
	 * Constant variable to hold Type
	 */
	public static final String SORT_BY_OPTION_TYPE = "Type"

	/**
	 * Constant variable to hold Kind
	 */
	public static final String SORT_BY_OPTION_KIND = "Kind"

	/**
	 * Constant for railcar
	 */
	public static final RAILCAR = "Railcar"

	/**
	 * Constant for carKinnd
	 */
	public static final CAR_KIND = "CarKind"

	/**
	 * Constant for Pool number and AAR car type
	 */
	public static final POOL_NUMBER_AND_AAR_CAR_TYPE = "AARCarTypeAndPoolnumber"
	
	/**
	 * Constant for Pool number and car kind
	 */
	public static final POOL_NUMBER_AND_CAR_KIND = "CarKindAndPoolnumber"
	
	/**
	 * Constant for AAR Car type and car kind
	 */
	public static final AAR_CAR_TYPE_AND_CAR_KIND = "CarKindAndAARCarType"
	
	/**
	 * Constant for AAR Car type. car kind and pool number
	 */
	public static final AAR_CAR_TYPE_CAR_KIND_AND_POOL_NUMBER= "CarKindAARCarTypeAndPoolNumber"

	/**
	 * Constant for Locomotive
	 */
	public static final LOCOMOTIVE = "Locomotive"

	/**
	 * constant to click on the Equipment Type
	 */
	public static final String CLICK_EQUIPMENT_TYPE = "document.querySelector('#equipment-type').shadowRoot.querySelector('#trigger').click()"

	/**
	 * Constant to get the equipment type length
	 */
	public static final String EQUIPMENT_TYPE_LENGTH = "return document.querySelector('#equipment-type').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant to get the selected Equipment Type text
	 */
	public static final String EQUIPMENT_TYPE_LABEL = "return document.querySelector('#equipment-type').shadowRoot.querySelector('#trigger').shadowRoot.querySelector('#label').innerHTML"

	/**
	 * Constant to Car Kind dropdown length
	 */
	public static final String CAR_KIND_LENGTH = " return document.querySelector('#car-kind').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for AAR Car type drop down to get the length
	 */
	public static final String AAR_CAR_KIND_LENGTH = "return document.querySelector('#aar-car-type').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for OwnerShip code length
	 */
	public static final String OWNERSHIP_CODE_LENGTH = "return document.querySelector('#ownership-code').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for reflectorization length
	 */
	public static final String REFLECTORIZATION_LENGTH = "return document.querySelector('#reflectorization').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for Grade length
	 */
	public static final String GRADE_LENGTH = "return document.querySelector('#grade').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for warning indicator
	 */
	public static final String WARNING_INDICATOR_LENGTH  = "return document.querySelector('#early-warning-indicator').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant for break and bear
	 */
	public static final String BEARING_BRAKE_LENGTH  = "return document.querySelector('#bearing-or-break').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Message when no Equipments Available
	 */
	public static final String NO_EQUIPMENT_ERROR_MESSAGE = "No equipments available"

	/**
	 * Invalid Equipment ID
	 */
	public static final String INVALID_EQUIPMENTID = "@ijdj8"

	/**
	 *  Actions Button
	 */
	public static final String ACTION_BUTTON = "document.querySelector('#equipment-actions-dropdown').shadowRoot.querySelector('#target > #triggerSlot > #trigger').shadowRoot.querySelector('#trigger > #label').click()"

	/**
	 *  Edit Equipment option
	 */
	public static final String EDIT_EQUIPMENT = "document.querySelector('#equipment-actions-dropdown').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelector('#dropdown > div > #selector > div:nth-child(1)').click()"

	/**
	 *  Edit Build Date Calender Icon
	 */
	public static final String EDIT_CALENDER_BUILD_DATE = "document.querySelector('px-datetime-picker').shadowRoot.querySelector('#field').shadowRoot.querySelector('div > div > #fieldWrapper > #date').shadowRoot.querySelector('#wrapper > div > button > #icon').click()"

	/**
	 *  Today Button in Calender
	 */
	public static final String EDIT_CALENDER_BUILDDATE_TODAY = "document.querySelector('px-datetime-picker').shadowRoot.querySelector('px-overlay-content > #content').shadowRoot.querySelector('#dropdown > div > div > button').click()"

	/**
	 *  Constant for View
	 */
	public static final String FUNCTIONALITY_VIEW = "view"

	/**
	 *  Constant for Edit
	 */
	public static final String FUNCTIONALITY_EDIT = "edit"
	
	/**
	 *  Constant for Service Code
	 */
	public static final String SERVICE_CODE_LENGTH = "return document.querySelector('#extended-service-code').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#selector > div').length"
	public static final String SERVICE_CODE = "return document.querySelector('#extended-service-code').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child("

	/**
	 * Constant to hold xpath of Filter Icon
	 */
	public static final String FILTER_ICON_XPATH ='//*[@class="btn btn--bare btn--icon QueryController-module_filterButton_uGs1p"]'

	/**
	 * Constant to hold car kind type option menu selection
	 */
	public static final String CAR_KIND_MENU_OPTION_SELECTOR = 'let carKindList=[]; document.querySelector(\'#root\').querySelector(\'#carKindType\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#dropdown\').querySelector(\'#selector\').querySelectorAll(\'.dropdown-option\').forEach(node =>{ carKindList.push(node.title)}); return carKindList'

	/**
	 * Constant to hold car kind drop down length
	 */
	public static final String CAR_KIND_MENU_OPTION_LENGTH = "return document.querySelector('#root #carKindType').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector').querySelectorAll('.dropdown-option').length"

	/**
	 * Constant to hold AAR Car Type option menu selection
	 */
	public static final String AAR_CAR_TYPE_MENU_OPTION_SELECTOR = 'let aarCarTypeList=[]; document.querySelector(\'#root\').querySelector(\'#aarCarType\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#dropdown\').querySelector(\'#selector\').querySelectorAll(\'.dropdown-option\').forEach(node =>{ aarCarTypeList.push(node.title)}); return aarCarTypeList'

	/**
	 * Constant to hold AAR car type Menu option length
	 */
	public static final String AAR_CAR_TYPE_MENU_OPTION_LENGTH = "return document.querySelector('#root').querySelector('#aarCarType').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown').querySelector('#selector').querySelectorAll('.dropdown-option').length"

	/**
	 * Constant to hold Attribute title of web element
	 */
	public static final String TITLE_FORM_WEB_ELEMENT = 'title'

	/**
	 * Constant to hold pool number
	 */
	public static final String POOL_NUMBER = "0"

	/**
	 * Constant to hold empty left pane message
	 */
	public static final String NO_EQUIPMENT_FOUND = "No equipments available"

	/**
	 * Constant to hold full left pane element selector xpath
	 */
	public static final String FULL_LEFT_PANE_ELEMENT_SELECTOR = '//*[@class="Selectable-module_scrollable_1EvSP"]/div'

	/**
	 * Constant to hold xpath of selection drop down in car kind menu
	 */
	public static final String CAR_KIND_DROP_DOWN_SELECTION =  "return document.querySelector('#root #carKindType').shadowRoot.querySelector('#content').shadowRoot.querySelector('#selector > div:nth-child("

	/**
	 * Constant to hold xpath of selection drop down in aar car type menu
	 */
	public static final String  AAR_CAR_TYPE_DROP_DOWN_SELECTION= "return document.querySelector('#root #aarCarType').shadowRoot.querySelector('#content').shadowRoot.querySelector('#dropdown').querySelector('#selector > div:nth-child("

	/**
	 * Empty Equipment ID
	 */
	public static final String EMPTY_EQUIPMENTID = " "

	/**
	 * Equipment Empty Message
	 */
	public static final String EQUIPMENT_EMPTY_MSG = "EQUIPMENT ID CANNOT BE EMPTY"

	/**
	 * Equipment Empty Message
	 */
	public static final String VALID_EQUIPMENT_MSG = "EQUIPMENT ID MUST START WITH 1 TO 4 CHARACTERS AND ENDS WITH 1 TO 11 NUMBERS"

	/**
	 * Car Kind List
	 */
	public static final String CARKIND_LIST = "let carKindList=[];document.querySelector('#car-kind').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').forEach(node => { carKindList.push(node.getAttribute(\'title\')) }); return carKindList"

	/**
	 * AAR Car Type List
	 */
	public static final String AARCAR_TYPE_LIST = "let aarCarTypeList=[];document.querySelector('#aar-car-type').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').forEach(node => { aarCarTypeList.push(node.getAttribute(\'title\')) }); return aarCarTypeList"

	/**
	 * Empty Car Kind message
	 */
	public static final String EMPTY_CAR_KIND_MSG = "EQUIPMENT KIND CANNOT BE EMPTY"

	/**
	 * Empty AAR Car Type message
	 */
	public static final String EMPTY_AAR_TYPE_MSG = "AAR CAR TYPE CANNOT BE EMPTY"

	/**
	 * Empty Equipment Type message
	 */
	public static final String EMPTY_EQUIOPMENT_TYPE_MSG = "EQUIPMENT TYPE CANNOT BE EMPTY"

	/**
	 * car kind drop down
	 */
	public static final String CLICK_CAR_KIND_DROPDOWN = 'document.querySelector(\'#car-kind\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * car kind drop down icon
	 */
	public static final String CLICK_CAR_KIND_DROPDOWN_ICON ="document.querySelector('#car-kind').shadowRoot.querySelector('#trigger').shadowRoot.querySelector('.dropdown-icon').click()"

	/**
	 * aar car type drop down
	 */
	public static final String CLICK_AARCARTYPE_DROPDOWN ='document.querySelector(\'#aar-car-type\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * aar car type drop down icon
	 */
	public static final String CLICK_AARCARTYPE_DROPDOWN_ICON ='document.querySelector(\'#aar-car-type\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'.dropdown-icon\').click()'

	/**
	 * Equipment drop down
	 */
	public static final String CLICK_EQUIPMENT_DROPDOWN ='document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#trigger\').click()'

	/**
	 * Equipment drop down icon
	 */
	public static final String CLICK_EQUIPMENT_DROPDOWN_ICON ='document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#trigger\').shadowRoot.querySelector(\'.dropdown-icon\').click()'

	/**
	 * Equipment Type RailCar select
	 */
	public static final String CLICK_EQUIPMENT_TYPE_RAILCAR ='document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(1)\').click()'

	/**
	 * Get Equipment Type RailCar text
	 */
	public static final String TEXT_EQUIPMENT_TYPE_RAILCAR ='return document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(1)\').innerText'

	/**
	 * Equipment Type Locomotive select
	 */
	public static final String CLICK_EQUIPMENT_TYPE_LOCOMOTIVE ='document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(2)\').click()'

	/**
	 * Get Equipment Type Locomotive text
	 */
	public static final String TEXT_EQUIPMENT_TYPE_LOCOMOTIVE ='return document.querySelector(\'#equipment-type\').shadowRoot.querySelector(\'#content\').shadowRoot.querySelector(\'#selector > div:nth-child(2)\').innerText'

	/**
	 * Equipment Type Locomotive
	 */
	public static final String EQUIPMENT_TYPE_LOCOMOTIVE ='Locomotive'

	/**
	 * Equipment Type RailCar
	 */
	public static final String EQUIPMENT_TYPE_RAILCAR ='Railcar'
}
