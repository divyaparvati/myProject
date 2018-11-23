import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import groovy.lang.MissingPropertyException
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())


RunConfiguration.setExecutionSettingFile('/var/folders/nf/ztgg1f9j5db_k2zwhxz62hq80000gn/T/Katalon/Test Cases/Railroad/F5362 - Configure and manage track/QA - US92237/US39513 - Add track from zone/TC65592 - Add track from zone/20181122_150409/execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Railroad/F5362 - Configure and manage track/QA - US92237/US39513 - Add track from zone/TC65592 - Add track from zone', new TestCaseBinding('Test Cases/Railroad/F5362 - Configure and manage track/QA - US92237/US39513 - Add track from zone/TC65592 - Add track from zone', [:]), FailureHandling.STOP_ON_FAILURE , true, true)
    
