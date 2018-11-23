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

/**
 *  User Login
 */
WebUI.callTestCase(findTestCase('Authentication/F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/TC59083 - Authentication'), 
    [:], FailureHandling.STOP_ON_FAILURE)

/**
 *  WayBill Management Tab click
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickWayBillManagementTabInMainMenu'()

WebUI.delay(2)

// click of Search dropdown
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#trigger").click()', 
    null)

// click of CarID, child 1 in dropdown
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()', 
    null)
setTextForSearch('UTLX 250034')
WebUI.delay(5)

CustomKeywords.'com.ge.tms.waybillverify.WayBillVerification.verifyCarIDFirstRecord'('UTLX 250034')

// click of WayBill Number, child 2 in dropdown
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").click()', 
    null)
setTextForSearch('808404')
CustomKeywords.'com.ge.tms.waybillverify.WayBillVerification.verifyWayBillNumberFirstRecord'('808404')

// click of Sender, child 3 in dropdown
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3) > span").click()', 
    null)
setTextForSearch('RMXXIRR')
CustomKeywords.'com.ge.tms.waybillverify.WayBillVerification.verifySenderFirstRecord'('RMXXIRR')


// click of Shipper, child 4 in dropdown
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(4) > span").click()', 
    null)
setTextForSearch('MUREX LLC')
WebUI.delay(2)
CustomKeywords.'com.ge.tms.waybillverify.WayBillVerification.verifyShipperFirstRecord'('MUREX LLC')

// click of Consignee, child 5 in dropdown
WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(5) > span").click()', 
    null)
setTextForSearch('PINE LAKE CORN PROCESSORS LLC')
CustomKeywords.'com.ge.tms.waybillverify.WayBillVerification.verifyConsigneeFirstRecord'('PINE LAKE CORN PROCESSORS LLC')

/**
 * Method to send text in search field
 * @param text
 */
def setTextForSearch(String textForSearch){
	WebUI.setText(findTestObject('Object Repository/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/serachfield_waybill_id'), textForSearch)
}

