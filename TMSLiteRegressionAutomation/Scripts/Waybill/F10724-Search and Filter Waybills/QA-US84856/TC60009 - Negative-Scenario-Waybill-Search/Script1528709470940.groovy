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
import com.ge.tms.constants.WayBillPageConstants

/**
 *  Variable to keyin a search string
 */
def searchString = 'Search'

/**
 *  WayBill Management Tab click - view Waybill
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickWayBillManagementTabInMainMenu'()

WebUI.delay(2)

// click of Search dropdown
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_DROPDOWN,
	null)

// click of CarID, child 1 in dropdown
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_EQUIPMENT_ID,
	null)
setTextForSearch(searchString)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyNoSearchMatched'()

// click of WayBill Number, child 2 in dropdown
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_WAYBILL_NUMBER,
	null)
setTextForSearch(searchString)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyNoSearchMatched'()

// click of Sender, child 3 in dropdown
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_SENDER,
	null)
setTextForSearch(searchString)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyNoSearchMatched'()

// click of Shipper, child 4 in dropdown
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_SHIPPER,
	null)
setTextForSearch(searchString)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyNoSearchMatched'()

// click of Consignee, child 5 in dropdown
WebUI.executeJavaScript(WayBillPageConstants.SEARCH_BY_CONSIGNEE,
	null)
setTextForSearch(searchString)
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyNoSearchMatched'()

/**
 * Method to send text in search field
 * @param textForSearch
 */
def setTextForSearch(String textForSearch){
	WebUI.setText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/serachfield_waybill_id'), textForSearch)
}
