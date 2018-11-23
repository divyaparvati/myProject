import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.ge.tms.constants.WayBillPageConstants as WayBillPageConstants
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import com.ge.tms.commonactions.WaybillActions

/**
 *  WayBill Management Tab click - view Waybill
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

/**
 * Remove active filter and click on first record
 * These lines of code to be removed when defect DE73603 and DE67356
 */
WaybillActions actions = new WaybillActions()
actions.removeActiveFilter()
WebUI.delay(2)
actions.clickOnFirstRecord()

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Active Status from Status Dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.WAYBILL_STATUS_ACTIVE, null)

/**
 * Click on Apply button
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
actions.clickOnFirstRecord()
WebUI.delay(2)

/**
 * Verify waybill status
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterStatus'(WayBillPageConstants.ACTIVE_STATUS)


/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Inbound Status from Status Dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.WAYBILL_STATUS_INBOUND, null)

/**
 * Click on Apply button
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))


/**
 * Verify waybill status
 */
/**
 * Commenting below line since there is no data for Inbound waybills-Functionality yet to develop for creating inbound waybills.
 */
/**
 * CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterStatus'(WayBillPageConstants.INBOUND_STATUS)
 */
/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Completed Status from Status Dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.WAYBILL_STATUS_COMPLETED, null)

/**
 * Click on Apply button
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
actions.clickOnFirstRecord()
WebUI.delay(2)

/**
 * Verify waybill status
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterStatus'(WayBillPageConstants.COMPLETED_STATUS)

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Clear Filters
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/clearFilterBtn'))

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Load/Empty dropdown value - Loaded
 */
WebUI.executeJavaScript(WayBillPageConstants.WAYBILL_LOAD_SELECTOR, null)

/**
 * Click on Apply button
 * clickOnFirstRecord to be removed when Defect DE67356 is closed
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
actions.clickOnFirstRecord()
WebUI.delay(2)

/** 
 * Call verifyWaybillFilterLoadEmpty method to verify the filter results
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterLoadEmpty'(WayBillPageConstants.LOAD)

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Load/Empty dropdown value - Loaded
 */
WebUI.executeJavaScript(WayBillPageConstants.WAYBILL_EMPTY_SELECTOR, null)

/**
 * Click on Apply button
 * clickOnFirstRecord to be removed when Defect DE67356 is closed
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
actions.clickOnFirstRecord()

/**
 * Call verifyWaybillFilterLoadEmpty method to verify the filter results
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterLoadEmpty'(WayBillPageConstants.EMPTY)

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * click on Clear Filters
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/clearFilterBtn'))

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Hazmant dropdown value - Has Hazmat
 */
WebUI.executeJavaScript(WayBillPageConstants.HAS_HAZMAT_SELECTOR, null)

/**
 * Click on Apply button
 * clickOnFirstRecord to be removed when Defect DE67356 is closed
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
if(actions.GetNoresultText()!=(WayBillPageConstants.WAYBILL_MESSAGE_NO_SEARCH_CRITERIA)){
actions.clickOnFirstRecord()
WebUI.delay(2)

/**
 * Call verifyWaybillFilterLoadEmpty method to verify the filter results
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterHazmat'(WayBillPageConstants.HAS_HAZMAT_TEXT)
}
/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Select Hazmant dropdown value - Has Hazmat
 */
WebUI.executeJavaScript(WayBillPageConstants.NO_HAZMAT_SELECTOR, null)

/**
 * Click on Apply button
 * clickOnFirstRecord to be removed when Defect DE67356 is closed
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
actions.clickOnFirstRecord()
WebUI.delay(2)

/**
 * Call verifyWaybillFilterLoadEmpty method to verify the filter results
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterHazmat'(WayBillPageConstants.NO_HAZMAT_TEXT)

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

/**
 * Clear Filters
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/clearFilterBtn'))

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/filterIcon'))

String commodityNumber = CustomKeywords.'com.ge.tms.commonactions.WaybillActions.getCommodityNumber'()

/**
 * Set Text in Commodity Text Box
 */
String commoditySelector = WayBillPageConstants.COMMODITY_TEXTBOX_SELECTOR + commodityNumber
System.out.println('jsdhfjdgjhgvcnbvnbjfhbjh' + commoditySelector)

WebUI.executeJavaScript(commoditySelector, null)

/**
 * Click on Apply button
 * clickOnFirstRecord to be removed when Defect DE67356 is closed
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/applyButton'))
actions.clickOnFirstRecord()
WebUI.delay(2)

/**
 * Verify Commodity Code in the list after filter
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterCommodity'(commodityNumber)

/**
 * Clear Filters
 */
WebUI.click(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/clearFilterBtn'))

