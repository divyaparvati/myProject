import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.ge.tms.commonactions.InterchangeActions as InterchangeActions
import com.ge.tms.constants.InterchangePageConstants as InterchangePageConstants
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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable


/**
 * Action class for InterchangeActions
 */
InterchangeActions actions = new InterchangeActions()

/**
 * Click on the Manage Interchange Roads Tab
 */
CustomKeywords.'com.ge.tms.commonactions.InterchangeActions.clickOnManageInterchangeRoadTab'()

/**
// * Verify the Manage Interchnage Road text when the tab is clicked
// */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyManageInterchangeRoadText'()

/**
 * Verify All Selected and Deselected Cars
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifySelectAndDeSelectAllAction'()

/**
 * Verify Inbound Cars
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyInboundCarsAction'()

WebUI.delay(2)

/**
 * Add Car Action
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyAddCarsAction'()

/** DE74393 Manage Interchange Remove Car From Consist Leads to a Blank Page. Can be remove the comment once defect is fixed.
/**
 * Remove Car Action
 *
 *CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyRemoveCarAction'()
*/

/**
 * Flip Consist
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifyFlipConsistAction'()

/**
 * Click on 2 cars and click selct range
 */
actions.clickSelectRange()

/**
 * Verify Select range
 */
CustomKeywords.'com.ge.tms.interchange.InterchangeRoadVerification.verifySelectRange'()
