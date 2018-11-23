import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.ge.tms.constants.WayBillPageConstants as WayBillPageConstants

import com.ge.tms.commonactions.WaybillActions
import com.ge.tms.waybillVerify.WaybillVerification
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**
 * Test Steps: Waybill search sort filter combination by car ID
 * 
 * 1. Login to Application
 * 2. Select Waybill Management and View Waybills
 * 3. Select Car ID from search by and set a random car ID
 * 4. Click Filters set Origin
 * 5. Sort by Commodity
 * 6. Verify Error message
 /*
 /**
 *  WayBill Management Tab click
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

WebUI.delay(2)

/**
 *  Action class for WaybillActions
 */
WaybillActions actions = new WaybillActions()

/**
 *  WayBill Search Dropdown click
 */
actions.clickWaybillSearchDropdown()

WebUI.delay(2)

/**
 * click of CarID, child 1 in Waybill Search dropdown
 */
actions.clickWaybillSearchCarID()

/**
 * Set Random Car ID for the Search Field
 */
actions.setRandomCarIDForSearch()

WebUI.delay(2)

/**
 * Click Waybill Filter Icon
 */
actions.clickWaybillFilter()

WebUI.delay(2)

/**
 * Setting Commodity for the filter
 */
actions.setInvalidCommodityFilter()

WebUI.delay(2)

/**
 * Click Waybill Filter Apply Button
 */
actions.clickWaybillFilterApply()

WebUI.delay(2)

/**
 * Verify Error Message
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyMessageforWrongSearch'(actions.GetNoresultText())
