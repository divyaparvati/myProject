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

/**
 *  To click on the View waybill in the Waybill Management Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

WebUI.delay(5)

//Get The Selected Waybill number
String expectedWaybillNumber = WebUI.getText(findTestObject('F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillNumber_leftpane')).substring(
    1)

//Get the selected waybill data
def wayBill = CustomKeywords.'com.ge.tms.util.CommonUtilities.getSelectedWaybillAPI'(expectedWaybillNumber)

// To verify the waybill error count
def actualWaybillError = WebUI.getText(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/waybillError'))

////Get the Errors from waybill json
//List listOfWayBillErros = wayBill.errors
//
////Count of Errors
//def expectedWaybillError = listOfWayBillErros.size()
////Verify Actual errors with expected erros.
//
//CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillErrors'(actualWaybillError, expectedWaybillError)

// Check the default selected waybill in the list background
CustomKeywords.'com.ge.tms.util.CommonUtilities.verifyDefualtSelectedBackground'(findTestObject('Object Repository/F10684-View Waybill/QA-US83597/US80788- View Waybills in a List/ViewWaybill/Page_Waybill/selectedWaybill'))
