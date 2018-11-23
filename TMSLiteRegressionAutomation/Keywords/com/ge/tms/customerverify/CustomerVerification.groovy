package com.ge.tms.customerverify

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.CustomerPageConstants
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
/**
 * Class for Customer Screen Verification
 */
public class CustomerVerification {

	/**
	 * Logger class
	 */
	KeywordLogger log = new KeywordLogger()

	/**
	 *  Array variable that contain all the values in the Actions dropdown
	 */
	def actionsDropdownValuesArr = (([
		'Edit Customer',
		'Delete Customer']) as String[])

	/**
	 * Verify all the values in Customer Actions dropdown
	 */
	@Keyword
	def verifyCustomerActionsDropdownValues(){
		String actualDropDownValueforEditCustomer = WebUI.executeJavaScript('return document.querySelector("#customer-actions-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").innerHTML',
				null)

		WebUI.verifyMatch(actualDropDownValueforEditCustomer, actionsDropdownValuesArr[0], false)

		String actualDropDownValueforDeleteCustomer = WebUI.executeJavaScript('return document.querySelector("#customer-actions-dropdown").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").innerHTML',
				null)

		WebUI.verifyMatch(actualDropDownValueforDeleteCustomer, actionsDropdownValuesArr[1], false)
	}
}
