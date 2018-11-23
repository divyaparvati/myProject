import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.ge.tms.commonactions.EquipmentActions
import com.ge.tms.commonactions.InterchangeActions
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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/**
 * Action class for Equipment
 */
EquipmentActions actions = new EquipmentActions()

/**
 * Click on the Manage Equipment Tab
 */
actions.clickOnEquipmentManagement()

/**
 * Wait till Add New Equipment button is clickable to verify the page load
 */
WebUI.verifyElementClickable(findTestObject('Object Repository/F7174 - Manage Equipment/addNewEquipmentButton'))

/**
 * Add a new equipment details
 */
actions.addNewEquipment()

/**
* Verify if the newly created equipment is added successfully by searching for the new added equipment
*/
actions.setEquipmentIdSearchPane(actions.equipmentId)

CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyNewEquipmentCreatedIsSearchable'(actions.equipmentId)
