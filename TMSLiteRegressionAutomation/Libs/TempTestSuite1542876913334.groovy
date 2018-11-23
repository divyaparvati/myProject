import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/Regression/Manage Waybill Test Suite')

suiteProperties.put('name', 'Manage Waybill Test Suite')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())



RunConfiguration.setExecutionSettingFile("/Users/divya/GitWorkSpace/tms-qa-automation/TMSLiteRegressionAutomation/Reports/Regression/Manage Waybill Test Suite/20181122_142513/execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/Regression/Manage Waybill Test Suite', suiteProperties, [new TestCaseBinding('Test Cases/Waybill/F10684-View Waybill/QA-US83752/US80788-View Waybills in a List/TC61049 - Verification of fields in view waybill', 'Test Cases/Waybill/F10684-View Waybill/QA-US83752/US80788-View Waybills in a List/TC61049 - Verification of fields in view waybill',  null), new TestCaseBinding('Test Cases/Waybill/F10684-View Waybill/QA-US83754/US80803-Enable Waybill Actions dropdown/TC59595-Waybill Actions', 'Test Cases/Waybill/F10684-View Waybill/QA-US83754/US80803-Enable Waybill Actions dropdown/TC59595-Waybill Actions',  null), new TestCaseBinding('Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/TC59661 - Search Waybill', 'Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US84159/US80793 - Search Waybill/TC59661 - Search Waybill',  null), new TestCaseBinding('Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US84856/TC60009 - Negative-Scenario-Waybill-Search', 'Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US84856/TC60009 - Negative-Scenario-Waybill-Search',  null), new TestCaseBinding('Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US85913/US80796-Sort WayBills in a List/TC60446 - SortWaybill', 'Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US85913/US80796-Sort WayBills in a List/TC60446 - SortWaybill',  null), new TestCaseBinding('Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US88052/TC66897-Additional Search for waybill', 'Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US88052/TC66897-Additional Search for waybill',  null), new TestCaseBinding('Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US88052/TC67197-Verify message for no record found', 'Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US88052/TC67197-Verify message for no record found',  null), new TestCaseBinding('Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/TC65847-FilterWaybill', 'Test Cases/Waybill/F10724-Search and Filter Waybills/QA-US92238/US80797-Select and Apply Filter/TC65847-FilterWaybill',  null)])
