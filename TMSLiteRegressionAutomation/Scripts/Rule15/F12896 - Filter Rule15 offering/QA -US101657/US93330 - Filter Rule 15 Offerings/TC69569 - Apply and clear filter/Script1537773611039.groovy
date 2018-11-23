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

import com.ge.tms.commonactions.Rule15Actions

/**
 * Test Steps: Filter - Rule 15 offering - Apply filter and Clear filter
 * 1. Login to Application
 * 2. Navigate to Train Operations -> Rule 15 Offerings
 * 3. Click on the filter icon 
 * 4. Fill in the fields to be filtered from respective dropdown
 * 5.  Click on Apply
 * 6. Verify the displayed record matches the filtered fields
 *  7. Click on the filter icon
 *  8. Click on clear filter button
 * 9. All the filters applied shall be cleared
 /*

 /**
 * User to login to the application successfully
 */
CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()

/**
 * Creating object for Action class
 */
Rule15Actions rule15Action = new Rule15Actions()

/**
 * Click on Rule 15 offering tab under Train offerations
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab'()

/**
 *  Actions to apply and clear the filter
 */
rule15Action.clearTheAppliedFilter()

/**
 * Verification no filter applied
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyFilterChipExistence'()
