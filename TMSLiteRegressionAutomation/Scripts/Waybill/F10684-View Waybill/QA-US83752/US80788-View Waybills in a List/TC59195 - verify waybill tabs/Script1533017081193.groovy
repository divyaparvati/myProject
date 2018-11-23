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

import org.junit.After
import org.openqa.selenium.Keys as Keys

/**
 *  To click on the View waybill in the Waybill Management Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

/**
 * Verify the view way bill landing screen
 * Verify if the waybill number in the selected left panel is same as the right panel
 * Verify if all the tabs for a selected waybill is clickable
 */
String expectedWaybillNumber = WebUI.getText(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_leftpane')).substring(1)

String actualWaybillNumber = WebUI.getText(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_rightpane'))

WebUI.verifyMatch(actualWaybillNumber, expectedWaybillNumber, false)

WebUI.click(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/px-tab_Customers'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_CustomerTab_text'), 2)

WebUI.click(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/px-tab_Version'))

WebUI.verifyElementPresent(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_VersionsTab_text'), 2)

WebUI.click(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/px-tab_EDI'))

WebUI.verifyElementPresent(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybill_EDITab_text'), 2)
