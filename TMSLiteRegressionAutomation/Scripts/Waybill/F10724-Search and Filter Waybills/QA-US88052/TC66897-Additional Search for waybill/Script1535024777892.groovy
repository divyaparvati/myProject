import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.ge.tms.constants.WayBillPageConstants as WayBillPageConstants

import com.ge.tms.commonactions.WaybillActions
import com.ge.tms.waybillVerify.WaybillVerification
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
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
/**
 * Test Steps: Waybill search sort filter combination by car ID
 * 
 * 1. Login to Application
 * 2. Select Waybill Management and View Waybills
 * 3. Select Car ID from search by and set a random car ID
 * 4. Click Filters set Load empty as Empty and Apply
 * 5. Sort by Waybill Number
 * 6. In the Left pane only Empty waybill should be displayed
 * 7. Left pane result should be sorted according to waybill number
 /*
 /**
 * Test Steps: Waybill search sort filter combination by Shipper
 * 
 * 1. Login to Application
 * 2. Select Waybill Management and View Waybills
 * 3. Select Shipper from search by and set a random Shipper
 * 4. Click Filters set Hazmat as Has Hazmat
 * 5. Sort by Car ID
 * 6. In the Left pane only Hazmat enabled waybills should displayed
 * 7. Left pane result should be sorted according to Car ID
 /*
 /**
 * Test Steps: Waybill search sort filter combination by Consignee
 * 
 * 1. Login to Application
 * 2. Select Waybill Management and View Waybills
 * 3. Select Consignee from search by and set a random Consignee
 * 4. Click Filters set Random origin
 * 5. Sort by Comodity
 * 6. Result only Shown under selected origin
 * 7. Left pane result should be sorted according to Comodity
 /*
 /**
 *  WayBill Management Tab click
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab'()

/**
 *  Action class for WaybillActions
 *  These lines of code to be removed when defect DE73603 and DE67356 from all places in the script
 */
WaybillActions actions = new WaybillActions()
WebUI.delay(2)
actions.removeActiveFilter()
WebUI.delay(2)
actions.clickOnFirstRecord()

/**
 *  WayBill Search Dropdown click
 */
actions.clickWaybillSearchDropdown()
WebUI.delay(2)

/**
 * click of CarID, child 1 in Waybill Search dropdown
 */
actions.clickWaybillSearchCarID()

/**
 * Set Random Car ID for the Search Field
 */
actions.setRandomCarIDForSearch()
WebUI.delay(2)

/**
 * Click Waybill Filter Icon
 */
actions.clickWaybillFilter()
WebUI.delay(2)

/**
 * Select Empty from Waybill Load/Empty
 * If condition to be removed when Defect DE67356 is closed
 */
actions.selectEmptyfromwaybillLE()
actions.clickWaybillFilterApply()
WebUI.delay(2)
/**
 * Click Waybill Filter Apply Button
 */
if(actions.GetNoresultText()!=(WayBillPageConstants.WAYBILL_MESSAGE_NO_SEARCH_CRITERIA)){
	actions.clickOnFirstRecord()
	WebUI.delay(2)

	/**
	 * Call verifyWaybillFilterLoadEmpty method to verify the filter results
	 */
	CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterLoadEmpty'(WayBillPageConstants.EMPTY)
}

/**
 *  Sort dropdown click
 */
actions.clickWaybillSortdropdown()
WebUI.delay(2)

/**
 *  Set Waybill number from sort dropdown
 */
actions.setWaybillnumbersortdropdown()
WebUI.delay(2)

/**
 *  Verify sort
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillNumberSort'()

/**
 *  Verify Car ID from search
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCarIDFirstRecord'(actions.randomCarID)

/**
 *  WayBill Search Dropdown click
 */
actions.clickWaybillSearchDropdown()
WebUI.delay(2)

/**
 * Clear Search text Field
 */
actions.clearSearchText()
WebUI.delay(2)

/**
 *  Click Waybill search shipper
 */
actions.clickWaybillSearchShipper()
WebUI.delay(2)

/**
 *  Set Shipper for Search Field
 */
actions.setRandomShipperForSearch()
WebUI.delay(2)

/**
 * Clear Search text Field
 */
actions.clearSearchText()

/**
 * Click Waybill Filter Icon
 */
actions.clickWaybillFilter()
WebUI.delay(2)

/**
 * Clear Filter
 */
actions.clearFilter()

/**
 * Click Waybill Filter Icon
 */
actions.clickWaybillFilter()
WebUI.delay(2)

/**
 * Select Hazmant dropdown value - Has Hazmat
 */
actions.selectHasHazmatfromFilter()
WebUI.delay(2)

/**
 * Click Waybill Filter Apply Button
 * If condition to be removed when Defect DE67356 is closed
 */
actions.clickWaybillFilterApply()
WebUI.delay(2)
if(actions.GetNoresultText()!=(WayBillPageConstants.WAYBILL_MESSAGE_NO_SEARCH_CRITERIA)){
	actions.clickOnFirstRecord()
	WebUI.delay(2)

	/**
	 * Call verifyWaybillFilterLoadEmpty method to verify the filter results
	 */
	CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterLoadEmpty'(WayBillPageConstants.EMPTY)


	/**
	 *  Sort dropdown click
	 */
	actions.clickWaybillSortdropdown()
	WebUI.delay(2)

	/**
	 * Sorted by Car id in descending order
	 */
	actions.setCarIDsortdropdown()

	/**
	 * Verify sort by Car ID
	 */
	CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCarIdSort'()

	/**
	 * Verify Shipper from search
	 */
	CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyShipperFirstRecord'(actions.shipper)
	WebUI.delay(5)
}

/**
 *  WayBill Search Dropdown click
 */
actions.clickWaybillSearchDropdown()
WebUI.delay(2)

/**
 * Clear Search text Field
 */
actions.clearSearchText()
WebUI.delay(2)

/**
 *  Click Waybill search Consignee
 *  If condition to be removed when Defect DE67356 is closed
 */
actions.clickWaybillConsignee()
WebUI.delay(2)
if(actions.GetNoresultText()!=(WayBillPageConstants.WAYBILL_MESSAGE_NO_SEARCH_CRITERIA)){
	actions.clickOnFirstRecord()
	WebUI.delay(2)

	/**
	 *  Set Consignee for Search Field
	 */
	actions.setConsigneeForSearch()
	WebUI.delay(2)
}

/**
 * Click Waybill Filter Icon
 */
actions.clickWaybillFilter()
WebUI.delay(2)

/**
 * Clear Filter
 */
actions.clearFilter()

/**
 * Click Waybill Filter Icon
 */
actions.clickWaybillFilter()
WebUI.delay(2)

/**
 *  Sort dropdown click
 */
actions.clickWaybillSortdropdown()
WebUI.delay(2)

/**
 * Set Commodity sort
 */
actions.setCommoditysortdropdown()

/**
 * Verify Commodity sort
 */
CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyCommoditySort'()

/**
 * Verify Consignee from search
 * If condition to be removed when Defect DE67356 is closed
 */
if(actions.GetNoresultText()!=(WayBillPageConstants.WAYBILL_MESSAGE_NO_SEARCH_CRITERIA)){
	actions.clickOnFirstRecord()
	WebUI.delay(2)
	CustomKeywords.'com.ge.tms.waybillVerify.WaybillVerification.verifyConsigneeFirstRecord'(actions.consignee)
	WebUI.delay(5)
}
