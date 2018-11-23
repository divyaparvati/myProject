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
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.URL)

WebUI.click(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_TMSlite/button_Login'))

WebUI.delay(5)

WebUI.setText(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_Sign In with Auth0/input_username'), 
    GlobalVariable.USERNAME)

WebUI.setText(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_Sign In with Auth0/input_password'), 
    GlobalVariable.PASSWORD)

WebUI.click(findTestObject('F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_Sign In with Auth0/span_Log In'))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('Object Repository/F10740-F10745-Authentication-Authorization/QA-US83167/US80752-Require Login to view Sinharaja Page/LoginPage/Page_TMSlite/HelloText'), 
    10)

