import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ge.tms.constants.CustomerPageConstants as CustomerPageConstants
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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.junit.After as After
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

/**
 * TC60342-Verify Filters
 * Steps:
 *  1. Login to TMS Lite Application
 *  2. Click on Filter Icon
 *  3. Select Billing filter
 *  4. Verify Custmer List View Filtered with Billing Customers
 *  4. Click on Filter Icon
 *  5. Select Inactive filter
 *  6. Verify Custmer List View Filtered with Inactive Customers
 *  7. Click on Filter Icon
 *  8. Select Transportation filter
 *  9. Verify Custmer List View Filtered with Transportation Customers
 *  10. Click on clear filter
 *  11. Verift all filters cleared
 *  12. Close Browser
 */

/**
 * Click On Manage Customer Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers'()

/**
 *  Verify Page Header
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerPageHeader'()

/**
 * Click on Filter Icon
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/filterIcon'))

/**
 * Click on Billing Check Box
 */
WebUI.click(findTestObject('F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/billingCheckBox'))

/**
 * Click on Apply Filter Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/applyFilter'))

/**
 * Verify newly created Inactive Customer in the List
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerFilter'(CustomerPageConstants.BILLING)

/**
 * Click on Filter Icon
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/filterIcon'))

/**
 * Click on Billing Check Box
 */
WebUI.click(findTestObject('F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/billingCheckBox'))

/**
 * Click on InActive Check Box
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/inactive'))

/**
 * Click on Apply Filter Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/applyFilter'))

/**
 * Verify Inactive Customer in the List
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerFilter'(CustomerPageConstants.INACTIVE)

/**
 * Click on Filter Icon
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/filterIcon'))

/**
 * Click on Inactive Check Box
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/inactive'))

/**
 * Click on Transporation Check Box
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/transportationCheckbox'))

/**
 * Click on Apply Filter Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/applyFilter'))

/**
 * Verify Transportation Customer in the List
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerFilter'(CustomerPageConstants.TRANSPORTATION)

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/filterIcon'))

/**
 * Click on Billing Check Box
 */
WebUI.click(findTestObject('F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/billingCheckBox'))

/**
 * Click on Inactive Check Box
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/inactive'))

/**
 * Click on Transporation Check Box
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/transportationCheckbox'))

/**
 * Click on Apply Filter Button
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/applyFilter'))

/**
 * Verify Billing or Inactive or Transportation Customer in the List
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerFilter'(CustomerPageConstants.ANY_CUSTOMER)

/**
 * Click on filter icon
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/filterIcon'))

/**
 * Clear Filter
 */
WebUI.click(findTestObject('Object Repository/F11762-View Customer/QA-US83146/US84104-View Customer/View Customer/CustomerPageObject/filters/clearBtn'))

/**
 * Verify Filters should be cleared in the customer home page
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyClearFilters'()

