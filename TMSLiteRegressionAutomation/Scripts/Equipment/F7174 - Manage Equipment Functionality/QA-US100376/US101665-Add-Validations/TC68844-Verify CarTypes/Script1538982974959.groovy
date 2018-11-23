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
 * Verify Car Kinds for Equipment type
 *1. Login to Application 
 *2. Select System Configurations and Manage Equipment
 *3. Select Equipment Type
 *4. Click Car Kind
 *5. Verify Car Kind For selected Equipment Type
 
 *Verify AAR Car Type for selected Car Kind
 *1. Select Equipment Type
 *2. Select Car Kind
 *3. Click AAR Car Type
 *4. Verify AAR Car Type For selected Car Kind
*/

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
 * Add a new equipment details
 */
actions.clickOnAddNewEquipmentButton()

/**
 * Select Random Equipment
 */
actions.selectRandomlyEquipmentType()

/**
 * Select Random Car Kind
 */
actions.selectRandomCarKind()

/**
 * Verify AAR Car Type For selected Car Kind
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyAarCarTypeList'(actions.selectRandomCarKind())

/**
  * Click Add New Equipment Cancel Button
  */
actions.clickCancelBtn()

/**
 * Add a new equipment details
 */
actions.clickOnAddNewEquipmentButton()

/**
 * Verify Car Kind for Equipment Type Rail Car
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyCarKindList'(actions.clickEquipTypeLocomotive())

/**
 * Verify Car Kind for Equipment Type Rail Car
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyCarKindList'(actions.clickEquipTypeRailCar())
