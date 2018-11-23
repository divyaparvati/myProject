import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import java.util.List as List

/**
 *  Click On Manage Customer Tab
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers'()

/**
 *  Verify Page Header
 */
CustomKeywords.'com.ge.tms.customer.CustomerVerification.verifyCustomerPageHeader'()

/**
 * Create a list of negative Customer IDs
 */
String[] searchTextCustIdArr = CustomerPageConstants.SEARCH_TEXT_NEGATIVE

/**
 * Iterate through array and place the array values in the search string
 * Verify the no matching customer found text for empty results
 */
for (int i = 0; i < searchTextCustIdArr.length; i++) {
    WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'), 
        searchTextCustIdArr[i])

    String textDisplayed = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/NoDataFoundSection'))

    if (textDisplayed.equals(CustomerPageConstants.NO_CUSTOMER_FOUND)) {
        WebUI.verifyEqual(textDisplayed, CustomerPageConstants.NO_CUSTOMER_FOUND)
    } else {
        WebUI.verifyEqual(textDisplayed, CustomerPageConstants.NO_CUSTOMER_AVAILABLE)
    }
}

/**
 * Clear the test box
 */
WebUI.clearText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'))

WebUI.delay(3)

/**
 * Create a list of negative Address Keys
 */
String[] searchTextAddressKeyArr = ['1ABC100Z', '$%^^&19HAa', 'kdhk119']

/**
 * Iterate through array and place the array values in the search string
 * Verify the no matching customer found text for empty results
 */
for (int i = 0; i < searchTextCustIdArr.length; i++) {
    WebUI.setText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/searchBox'), 
        searchTextCustIdArr[i])

    String textDisplayed = WebUI.getText(findTestObject('Object Repository/F11762-View Customer/QA-US84851/US83148-SearchCustomer/SearchCustomer/SearchSortCustomerPageObjects/NoDataFoundSection'))

    WebUI.verifyEqual(textDisplayed, 'No customers available')
}
