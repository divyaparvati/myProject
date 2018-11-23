package com.ge.tms.commonactions

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.ge.tms.constants.CustomerPageConstants
import com.ge.tms.constants.EquipmentPageConstants
import com.ge.tms.constants.RailroadPageConstants
import com.ge.tms.constants.Rule15OfferingsPageConstants
import com.ge.tms.constants.WayBillPageConstants
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
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

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

/**
 * Class for Common click events in the TMS application
 */

public class CommonClickEvents {

	/**
	 * Method to click on the Actions dropdown in the View waybill screen
	 */
	@Keyword
	def clickOnWaybillActionsDropdown(){
		WebUI.executeJavaScript('document.querySelectorAll("#root px-dropdown")[2].shadowRoot.querySelector("#trigger").click()',
				null)
	}

	/**
	 * Method to click on the Actions dropdown in Manage customer Screen
	 */
	@Keyword
	def clickOnCustomerActionsDropdown(){
		WebUI.executeJavaScript('document.querySelector("#customer-actions-dropdown").shadowRoot.querySelector("#trigger").click()',
				null)
	}

	/**
	 * Keyword to click on the OK button in a pop up modal
	 */
	@Keyword
	def clickOkButtonInPopupModal(){
		WebUI.executeJavaScript('document.querySelector("px-modal").shadowRoot.querySelector("#accept-trigger-button").click()',
				null)
	}

	/**
	 * Method to click on the View Waybill in Waybill Management Tab on the Main Menu
	 */
	@Keyword
	def clickWayBillManagementTabInMainMenu(){

		/**
		 * New View Waybill Selector
		 */
		WebUI.executeJavaScript(WayBillPageConstants.VIEW_WAYBILL_SELECTOR,null)
		WebUI.delay(2)
	}

	/**
	 * Method to click on the View Waybill in Waybill Management Tab on the Main Menu
	 */
	@Keyword
	def clickOnViewWayBillInManagementTab(){

		/**
		 * New View Waybill Selector
		 */
		WebUI.executeJavaScript(WayBillPageConstants.VIEW_WAYBILL_SELECTOR,null)
		WebUI.delay(2)
	}

	/**
	 * Method to click on Manage Customer in System Configuration Tab in main menu
	 */
	@Keyword
	def clickOnManageCustomers(){
		WebUI.executeJavaScript(CustomerPageConstants.MANAGE_CUSTOMERS_SELECTOR,
				null)
		WebUI.delay(1)
	}

	/**
	 * Method to click on the Railroad Setup in System Configuration Tab in main menu
	 */
	@Keyword
	def clickOnRailroadSetup(){
		WebUI.executeJavaScript(RailroadPageConstants.RAILROAD_SETUP,null)
		WebUI.delay(2)
	}

	/**
	 * Method to click on the Cancel button in screen
	 */
	@Keyword
	def clickOnCancelButton(){
		WebUI.click(findTestObject('Object Repository/CommonObjects/cancelButton'))
	}

	/**
	 * Method to click on the Manage Equipment in System Configuration Tab in main menu
	 */
	@Keyword
	def clickOnManageEquipment(){
		WebUI.executeJavaScript(EquipmentPageConstants.EQUIPMENT_MANAGE_MENU,null)
		WebUI.delay(2)
	}

	/**
	 * Method to click on the Rule 15 Offerings Tab under Train Operations
	 */
	@Keyword
	def clickOnRule15OfferingsTab(){
		WebUI.executeJavaScript(Rule15OfferingsPageConstants.RULE_15_OFFERING_TAB, null)
		WebUI.delay(2)
	}
}
