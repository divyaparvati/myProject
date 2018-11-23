import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.ge.tms.constants.Rule15OfferingsPageConstants as Rule15OfferingsPageConstants
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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.ge.tms.commonactions.Rule15Actions

/**
Add Cars And Resend
1. Login to Application
2. Select System Configurations and Rule15 Offering
3. Search and Select Random TrainID which has Status OFFERED OR REJECT
4. Click action and Add Cars And Resend
5. Select a Car or cars which is already not selected and click continue
6. Verify newly added cars and the consist with the API data
*/

/**
 * Creating object for Action class
 */
Rule15Actions action = new Rule15Actions()

/**
 * Click on Rule 15 offering tab under Train offerations
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab'()

/**
 * Add Cars and resend
 */
action.addCarsAndResend()

/**
 * Verify Added Cars with the UI
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyAddCarsAndResendEquipmentIDs'()

/**
 * Search for the newly created train id in left panel
 */
action.searchByTrainId(action.randomConsistTrainId)

/**
 * Calling method to get the API response to validate
 */
ResponseObject responseObject = action.getOfferingDetails(action.randomConsistTrainId)

/**
 * Verify the response with Actual UI Data
 */
CustomKeywords.'com.ge.tms.rule15.Rule15Verification.verifyRule15Offering'(responseObject)
