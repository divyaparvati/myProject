import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ge.tms.constants.InterchangePageConstants

import org.junit.After

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
 * User to login to the application successfully
 */
CustomKeywords.'com.ge.tms.util.CommonUtilities.login'()

/**
 * Action class for InterchangeActions
 */
InterchangeActions actions = new InterchangeActions()

/**
 * Click on the Manage Interchange Roads Tab
 */
CustomKeywords.'com.ge.tms.commonactions.InterchangeActions.clickOnManageInterchangeRoadTab'()

/**
 * Click on the  InterchangeType DropDown
 */
actions.clickOnInterchangeTypeDropDown()

/**
 * Select InterchangeType DropDown select Inound Only 
 */
actions.clickOnInterchangeTypeDropDownIO()

/**
 * Verify Inbound Only from left pane
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeFilterInboundOutbound'(InterchangePageConstants.INBOUND)

/**
 * Click on the  InterchangeType DropDown
 */
actions.clickOnInterchangeTypeDropDown()

/**
 * Select InterchangeType DropDown select Outbound Only
 */
actions.clickOnInterchangeTypeDropDownOO()

/**
 * Verify Outbound Only from left pane
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeFilterInboundOutbound'(InterchangePageConstants.OUTBOUND)

/**
 * Click on the  InterchangeType DropDown
 */
actions.clickOnInterchangeTypeDropDown()

/**
 * Select InterchangeType DropDown select Outbound Only
 */
actions.clickOnInterchangeTypeDropDownAll()

/**
 * Verify Outbound Only from left pane
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeFilterInboundOutboundAll'(InterchangePageConstants.INBOUND, InterchangePageConstants.OUTBOUND)
