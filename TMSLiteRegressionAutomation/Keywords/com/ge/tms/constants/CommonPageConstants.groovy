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
 * Class for all common page objects
 */
public class CommonPageConstants {

	/**
	 * CSS Class for Enabled Save Button
	 */
	public static final String SAVE_BUTTON_ENABLE_CLASS = "btn--call-to-action";

	/**
	 * CSS class for Disabled Save button
	 */
	public static final String SAVE_BUTTON_DISABLED_CLASS = "btn--disabled";

	/**
	 * Constant for containing the CSS atribute for Non editable fields
	 */
	public static final String FIELD_NON_EDITABLE = "disabled";

	/**
	 * Constant for Tab key
	 */
	public static final String TAB_KEY = "\u0009";

	/**
	 * Constant for Special characters to be given to any field
	 */
	public static final String SPECIAL_CHARACTERS = "A*@T&!";

	/**
	 * Constant for Special Character Error Message for Fields
	 */
	public static final String FIELD_ALPHANUMERIC_ERROR_MESSAGE = "ALPHANUMERIC ONLY";

	/**
	 * Constant for Numeric value Error Message for Fields
	 */
	public static final String FIELD_NUMERIC_ERROR_MESSAGE = "NUMERIC VALUES ONLY";

	/**
	 * Constant for Numeric value Error Message for Fields
	 */
	public static final String FIELD_ALPHABETIC_ERROR_MESSAGE = "ALPHABETIC ONLY";

	/**
	 * Constant for No Search result in left navigation for stations and zones
	 */
	public static final NO_SEARCH_RESULT_MESSAGE= "No stations or zones found."
}
