import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.util.RallyIntegration
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.KeywordLogger

/**
 *  Test Listner class which executes after or before actions
 */
public class TmsTestListner {
	/**
	 *  Instance of Rally Class
	 */
	RallyIntegration rally = new RallyIntegration()
	
	/**
	 * Variable to hold login status of current test suit
	 */
	public static Boolean isTestSuitEnable
	
	/**
	 * Runs before every test suit begins
	 * Make a variable set to test Suit started
	 */
	@BeforeTestSuite
	def beforeTestSuiteExecute(){
		/**
		 * User to login to the application successfully
		 */
		CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()
		
		/**
		 * Set a variable to keep login status
		 */
		isTestSuitEnable = true
	}
	
	/**
	 * runs before every test case start
	 */
	@BeforeTestCase
	def beforeTestCaseExecute(){
		if(isTestSuitEnable == true){
		WebUI.navigateToUrl(GlobalVariable.URL+ '/home')
		}
		else{
			CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()
		}
	}

	/**
	 * Executes after every test case ends if the property updateRally is mentioned during execution
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		if (System.getProperties().containsKey("updateRally")){
			String[]  fullTestPath = testCaseContext.getTestCaseId().split("/")
			String tcName = fullTestPath[fullTestPath.length-1]
			String[] actualTCname = tcName.split("-")
			String testCaseId = actualTCname[0].trim()
			String tcStatus=testCaseContext.getTestCaseStatus()
			if(tcStatus.equals("PASSED")){
				tcStatus = "Pass"
			}
			else{
				tcStatus="Fail"
			}
			rally.addTestCaseResultToRally(tcStatus, testCaseId)
		}
	}
	
	/**
	 * Runs After Every test case finish executing
	 */
	@AfterTestCase
	def closeBrowserForTestCasse(){
		if (isTestSuitEnable==true){
			beforeTestCaseExecute()
		}
		else{
		WebUI.closeBrowser()
		}
	}
	
	/**
	 * Runs After Every test suit finish executing
	 */
	@AfterTestSuite
	def closeBrowserForTestSuite(){
		WebUI.closeBrowser()
	}
}
