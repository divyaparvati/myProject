import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
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
import com.ge.tms.constants.WayBillPageConstants
import com.ge.tms.commonactions.WaybillActions

/**
 * Steps
 * 
 * 1.Login to TMS Lite Application
 * 2.Click on View Waybills
 * 3.Verify the page header
 * 4.click on search dropdown and select CarID
 * 5.Verify the search results
 * 6.click on search dropdown and select WayBill Number
 * 7.Verify the search results
 * 8.click on search dropdown and select Sender
 * 9.Verify the search results
 * 10.click on search dropdown and select Shipper
 * 11.Verify the search results
 * 12.click on search dropdown and select Consignee
 * 13.Verify the search results
 */

/**
 *  WayBill Management Tab click
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

WebUI.delay(2)

/**
 *  WayBill Management Tab click - view Waybill
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickWayBillManagementTabInMainMenu'()

/**
 * Remove active filter and click on first record
 * These lines of code to be removed when defect DE73603 and DE67356
 */
WaybillActions actions = new WaybillActions()
actions.removeActiveFilter()
WebUI.delay(2)
actions.clickOnFirstRecord()

/**
 *  click of Search dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_DROPDOWN, null)

/**
 *  click of CarID, child 1 in dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_EQUIPMENT_ID, null)
actions.setTextForSearch(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstCarIDFromAPI'())
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCarIDFirstRecord'(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstCarIDFromAPI'())

/**
 *  click of WayBill Number, child 2 in dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_WAYBILL_NUMBER, null)
actions.setTextForSearch(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstWaybillNumberFromAPI'())
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWayBillNumberFirstRecord'(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstWaybillNumberFromAPI'())

/**
 *  click of Sender, child 3 in dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_SENDER, null)
actions.setTextForSearch(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstSenderFromAPI'())
WebUI.delay(3)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifySenderFirstRecord'(CustomKeywords.'com.ge.tms.util.CommonUtilities.getWaybillAttachedToSenderID'())

/**
 *  click of Shipper, child 4 in dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_SHIPPER, null)
actions.setTextForSearch(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstShipperFromAPI'())
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyShipperFirstRecord'(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstShipperFromAPI'())

/**
 *  click of Consignee, child 5 in dropdown
 */
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_CONSIGNEE, null)
actions.setTextForSearch(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstConsigneeFromAPI'())
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyConsigneeFirstRecord'(CustomKeywords.'com.ge.tms.util.CommonUtilities.getFirstConsigneeFromAPI'())
