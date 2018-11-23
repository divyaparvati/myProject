import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

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
import com.ge.tms.equipment.EquipmentVerification
import com.ge.tms.constants.EquipmentPageConstants

/**
 1. Login to the application
 2. Navigate to Manage Equipments
 3. View the values of the selected record
 4.verify mandatory field values
 a. Equipment Id value
 b. Equipment Type
 c. Car Kind
 d. AAr Car Type
 5.  verify optional field values in (for locomotive or railcar)
 1. General Information Section
 2. Dimention Details
 3. Capacity Details
 4. Test Details
 5. Other Details
 */

/**
 *  Instance for Equipment actions class
 */
EquipmentActions actions = new EquipmentActions()

/**
 *  Click on Manage Equipment tab
 */
actions.clickOnEquipmentManagement()

WebUI.delay(2)

/**
 *  Verification of Equiment details in each section with Api
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentDataInView'(EquipmentPageConstants.FUNCTIONALITY_VIEW)
