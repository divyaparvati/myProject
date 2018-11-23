/**1. login to TMS lite application
2. Click on Train operations
3. Select System Configuration
4. Select Manage Equipments
5. Click on Filter button
6. Click on Cancel button
7. Select a CarKind
8. Click on Apply button
9. Verify equipments. 
10filter from Car kind
11. Click on Filter button
12. Select a AAR car type
13. Click on Apply button
14. Verify equipments, filter from AAR car type
15. Click on Filter button
16. Select a AAR car type
17. Click on Clear button
18. Verify all filters removed
*/

import java.awt.Button


import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.ge.tms.commonactions.EquipmentActions
import com.ge.tms.constants.EquipmentPageConstants

/**
  *  Action class for Equipment
  */
 EquipmentActions actions = new EquipmentActions()

/**
 * Select Equipment management from system configuration menu
 */
 actions.clickOnEquipmentManagement()
 
 /**
  * Check results according to the car kind filter
  */
 actions.selectCarKindFromFilterMenu()
  
 /**
  * Verify by car kind type filter works correctly
  */
 CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentFilterFunctionality'(EquipmentPageConstants.CAR_KIND)
 
 /**
  * Method to click on clear button
  */
 actions.clickOnClearButtonInFilter()
 
 /**
  * Method to verify clear function
  */
 CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyClearButtonClearTheFilters'()
 
 /**
  *  Check results according to the AAR car type annd pool number
  */
 actions.selectaarCarTypeFromFilterMenu()
 
 /**
  * Verify by aar car type filter works correctly
  */
 CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentFilterFunctionality'(EquipmentPageConstants.POOL_NUMBER_AND_AAR_CAR_TYPE)
 
 /**
  *  Check results according to the car kind annd pool number
  */
 actions.selectCarKindAndPoolNumber()
 
 /**
  * Verify by aar car type filter works correctly
  */
 CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentFilterFunctionality'(EquipmentPageConstants.POOL_NUMBER_AND_CAR_KIND)
 
 /**
  *  Check results according to the car kind annd pool number
  */
 actions.selectCarKindAndAARCarType()
 
 /**
  * Verify by aar car type filter works correctly
  */
 CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentFilterFunctionality'(EquipmentPageConstants.AAR_CAR_TYPE_AND_CAR_KIND)
 
 /**
  *  Check results according to the car kind  AAR car type and pool number
  */
 actions.selectCarKindAARCarTypePoolNumber()
 
 /**
  * Verify by aar car type filter works correctly
  */
 CustomKeywords.'com.ge.tms.equipment.EquipmentVerification.verifyEquipmentFilterFunctionality'(EquipmentPageConstants.AAR_CAR_TYPE_CAR_KIND_AND_POOL_NUMBER)
