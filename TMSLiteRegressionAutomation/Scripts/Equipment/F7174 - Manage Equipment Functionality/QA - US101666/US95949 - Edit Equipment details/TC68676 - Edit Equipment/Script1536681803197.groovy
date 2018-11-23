import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.ge.tms.commonactions.EquipmentActions
import com.ge.tms.constants.EquipmentPageConstants

/**
 * Test Steps: Edit Equipment Details
 * 1. Login to Application
 * 2. Add an equipment
 * 3. Edit few of the optional fields
 * 4. Click save
 * 5.Search for the added equipment
 * 6. Verify the edited fields
/*

/**
 * Action class for Equipment
 */
EquipmentActions actions = new EquipmentActions()

/**
 * Click on the Manage Equipment Tab
 */
actions.clickOnEquipmentManagement()

/**
 * Add a new equipment details
 */
actions.addNewEquipment()

/**
 * Edit Equipment details
 */
actions.editEquipment()

/**
 *  Verify Edited Equipment details
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentDataInView'(EquipmentPageConstants.FUNCTIONALITY_EDIT)
