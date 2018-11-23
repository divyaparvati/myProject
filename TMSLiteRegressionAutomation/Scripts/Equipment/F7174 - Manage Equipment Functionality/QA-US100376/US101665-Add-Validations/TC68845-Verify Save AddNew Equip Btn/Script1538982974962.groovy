import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.ge.tms.util.CommonUtilities
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
import com.ge.tms.commonactions.EquipmentActions
import com.ge.tms.constants.EquipmentPageConstants

/**
 *Login to application 
 *Navigate to Rule 15 Offering tab
 *Click on Create New offering button
 *Provide invalid values to all mandatory fields
 *Verify that 
	1. Proper Error message is displayed for each fields
	2. Next button  is disabled
**/

/**
 * Create the instance of the common util class
 */
CommonUtilities util = new CommonUtilities()

/**
 *  Action class for railroad
 */
EquipmentActions actions = new EquipmentActions()

/**
 * Click on the Railroad Setup
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageEquipment'()

/**
 * Click Add New Equipment Button
 */
actions.clickOnAddNewEquipmentButton()

/**
 * Set Mandatory Fields for Add New Equipment Button
 */
actions.setMandatoryFieldsForAddNewEquipment()

/**
 * Verify No Equipment Error Message
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifySaveButtonEnabled'()
