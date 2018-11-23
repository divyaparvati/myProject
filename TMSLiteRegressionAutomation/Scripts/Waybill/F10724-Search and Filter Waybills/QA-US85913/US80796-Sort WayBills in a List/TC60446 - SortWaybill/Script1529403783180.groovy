import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.WayBillPageConstants
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**
 *  WayBill Management Tab click - view Waybill
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

/**
 *  Sort dropdown click
 */
WebUI.executeJavaScript(WayBillPageConstants.sortDropDown, null)

/**
 *  Sorted by WaybillNumber in descending order
 */
WebUI.executeJavaScript(WayBillPageConstants.wayBillNumberInSortDropdown, null)
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillNumberSort'()

/**
 *  Ordering icon and waybill number sorted in ascending order
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA- US85913/US80796 - Sort WayBills in a List/Ascending_Descending_Button_waybill'))
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillNumberSort'()

/**
 * Sorted by Commodity in descending order
 */
WebUI.executeJavaScript(WayBillPageConstants.commodityInSortDropdown, null)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCommoditySort'()

/**
 *  Ordering icon and commodity number sorted in ascending order
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA- US85913/US80796 - Sort WayBills in a List/Ascending_Descending_Button_waybill'))
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCommoditySort'()

/**
 * Sorted by Car id in descending order
 */
WebUI.executeJavaScript(WayBillPageConstants.carIdInSortDropdown, null)
WebUI.delay(1)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCarIdSort'()

/**
 *  Ordering icon and commodity number sorted in ascending order
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA- US85913/US80796 - Sort WayBills in a List/Ascending_Descending_Button_waybill'))
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCarIdSort'()
