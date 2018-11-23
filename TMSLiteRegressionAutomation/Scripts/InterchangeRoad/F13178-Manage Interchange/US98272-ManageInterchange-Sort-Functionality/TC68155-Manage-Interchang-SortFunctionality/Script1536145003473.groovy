import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ge.tms.constants.InterchangePageConstants

import org.junit.After

import com.ge.tms.commonactions.InterchangeActions
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.ge.tms.constants.InterchangePageConstants


/**
 * Action class for InterchangeActions
 */
InterchangeActions actions = new InterchangeActions()

/**
 * Click on the Manage Interchange Roads Tab
 */
CustomKeywords.'com.ge.tms.commonactions.InterchangeActions.clickOnManageInterchangeRoadTab'()

/**
 * Click on the  Sortby DropDown
 */
actions.clickOnsortByDropdown()

/**
 * Click on the Sort By Time Ascending
 */
actions.clickOnsortByTimeAscending()

/**
 * Verify Time Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.TIME)

/**
 * Click On Sort Order Button
 */
actions.clickOnSortBySortOrderButton()

/**
 * Verify Time Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.TIME)

/**
 * Click on the  Sortby DropDown
 */
actions.clickOnsortByDropdown()

/**
 * Click on the Sort By Station
 */
actions.clickOnsortByStation()

/**
 * Verify Station Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.STATION)

/**
 * Click On Sort Order Button
 */
actions.clickOnSortBySortOrderButton()

/**
 * Verify Station Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.STATION)

/**
 * Click on the  Sortby DropDown
 */
actions.clickOnsortByDropdown()

/**
 * Click on the Sort By Station
 */
actions.clickOnsortByInterchangeType()

/**
 * Verify Type Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.TYPE)

/**
 * Click On Sort Order Button
 */
actions.clickOnSortBySortOrderButton()

/**
 * Verify Type Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.TYPE)

/**
 * Click on the  Sortby DropDown
 */
actions.clickOnsortByDropdown()

/**
 * Click on the Sort By From RailRoad
 */
actions.clickOnsortByFromRailRoad()

/**
 * Verify Railroad Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.RAILROAD)

/**
 * Click On Sort Order Button
 */
actions.clickOnSortBySortOrderButton()

/**
 * Verify Railroad Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.RAILROAD)

/**
 * Click on the  Sortby DropDown
 */
actions.clickOnsortByDropdown()

/**
 * Click on the Sort By Train ID
 */
actions.clickOnsortByTrainID()

/**
 * Verify Train ID Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.TRAINID)

/**
 * Click On Sort Order Button
 */
actions.clickOnSortBySortOrderButton()

/**
 * Verify Train ID Sort
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort'(InterchangePageConstants.TRAINID)
