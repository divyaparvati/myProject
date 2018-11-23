import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.ge.tms.constants.WayBillPageConstants as WayBillPageConstants
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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.ge.tms.waybillVerify.WaybillVerification

/**
 *  WayBill Management Tab click
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

WebUI.delay(2)

WaybillVerification waybillVerification = new WaybillVerification()

/**
 * Webservice call to get the waybill data
 */
ResponseObject waybillData = WS.sendRequest(findTestObject('Object Repository/WayBillServices/GETPARTICULARWAYBILL',[('waybillnumber'): waybillVerification.actualWaybillNumber]))

/**
 *  Verify the number of data from API and UI
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillSize'(waybillData)

/**
 *  Verify the overview tab details in view waybill management
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillOverViewTabDetails'(waybillData)

/**
 * Click On Customer Tab
 */
WebUI.click(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/px-tab_Customers'))
/**
 * Get the Selected Waybill number
 */
String selectedWaybill = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/customerTab/selectedWaybill'))

/**
 *  Verify the Customer tab details in view waybill management
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillCustomerTabDetails'(waybillData, selectedWaybill)

/**
 * Click On Versions Tab
 */
WebUI.executeJavaScript(WayBillPageConstants.VERSION_TAB_SELECTOR, null)

/**
 *  Verify the Version tab details in view waybill management
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillVersionTabDetails'(waybillData, selectedWaybill)
