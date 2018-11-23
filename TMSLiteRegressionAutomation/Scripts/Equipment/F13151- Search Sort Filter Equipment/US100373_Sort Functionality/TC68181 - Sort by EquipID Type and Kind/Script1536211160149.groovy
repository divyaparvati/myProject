/**
 * 1. login to TMS lite application
2. Click on Train operations
3. Select System Configuration
4. Select Manage Equipments
5. Select Type from Sort by Menu
6. Click on descending icon
7. Select Equipment from Sort by Menu
8. Click on ascending icon
9. Select Kind from Sort by Menu
10. Click on descending icon
 */

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
import com.ge.tms.commonactions.RailroadActions
import com.ge.tms.util.CommonUtilities
import com.ge.tms.constants.EquipmentPageConstants
import com.ge.tms.constants.RailroadPageConstants
import com.ge.tms.equipments.EquipmentActions
import com.ge.tms.commonactions.EquipmentActions

/**
  *  Action class for Equipment
  */
 EquipmentActions actions = new EquipmentActions()

/**
 * Select Equipment management from system configuration menu
 */
 actions.clickOnEquipmentManagement()
 
 /**
  * Select equipment type form sort by menu and select descending option
  */
 actions.sortByTypeDescendingAction()
 
 /**
  * Verify from Type equipments sorted correctly in a descending manner
  */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentSorted'(EquipmentPageConstants.SORT_BY_OPTION_TYPE)

/**
 * Select equipment Type, form sort by menu and select ascending option
 */
actions.sortByEquipmentIDAscendingAction()

/**
 * Verify from EquipmentID, equipments sorted correctly in a ascending manner
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentSorted'(EquipmentPageConstants.SORT_BY_OPTION_EQUIPMENT_ID)

/**
 * Select equipment kind, form sort by menu and select descending option
 */
actions.sortByKindDescendingAction()

/**
 * Verify from Kind, equipments sorted correctly in a descending manner
 */
CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentSorted'(EquipmentPageConstants.SORT_BY_OPTION_KIND)
