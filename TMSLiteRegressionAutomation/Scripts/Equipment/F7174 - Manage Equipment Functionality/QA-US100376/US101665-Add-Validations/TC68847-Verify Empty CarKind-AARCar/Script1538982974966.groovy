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
 * Verify Equipment cannot be empty error message 
 *1. Login to Application 
 *2. Select System Configurations and Manage Equipment
 *3. Select Add New Equipment
 *4. CLick on Equipment ID and leave it blank
 *5. Verify Equipment cannot be empty error message 

Verify Equipment ID format validation message
 *1. Select Add New Equipment
 *2. Set Invalid Equipment ID
 *3. Verify validation 

Verify Car Kind validation message
 *1. Select and remove Car Kind
 *2. Verify validation message

Verify AAR Car Type validation message]
 *1. Select and remove AAR Car Type
 *2. Verify validation message

Verify Equipment Type validation message
 *1. Select and remove Equipment Type
 *2. Verify validation message
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
 * Verify Empty Car Kind Validation message
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEmptyCarKindMsg'(actions.clickOnRemoveCarKind())

/**
 * Click Add New Equipment Cancel button 
 */
actions.clickCancelBtn()

/**
 * Add a new equipment details
 */
actions.clickOnAddNewEquipmentButton()

/**
 * Verify Empty AAR Car Type Validation message
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEmptyCarKindMsg'(actions.clickOnRemoveAarCarType())

/**
 * Click Add New Equipment Cancel button
 */
actions.clickCancelBtn()

/**
 * Add a new equipment details
 */
actions.clickOnAddNewEquipmentButton()

/**
 * Verify Empty Equipment Type Validation message
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEmptyCarKindMsg'(actions.clickOnRemoveEquipmentType())

/**
 * Click Add New Equipment Cancel button
 */
actions.clickCancelBtn()

/**
 * Add a new equipment details
 */
actions.clickOnAddNewEquipmentButton()

/**
 * Set empty value for Equipment ID
 */
actions.setAddEquipmentIdField(EquipmentPageConstants.INVALID_EQUIPMENTID)

/**
 * Verify Invalid Equipment Error Message
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentIdEmptyAndInvalidMsg'()

/**
 * Clear Equipment ID Field
 */
actions.clearAddEquipmentIdField()

/**
 * Set Invalid Equipment ID
 */
actions.setAddEquipmentIdField(EquipmentPageConstants.EMPTY_EQUIPMENTID)

/**
 * Verify No Equipment Error Message
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentIdEmptyAndInvalidMsg'()
