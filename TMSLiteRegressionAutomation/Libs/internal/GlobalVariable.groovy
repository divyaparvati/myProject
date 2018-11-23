package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object G_Timeout
     
    /**
     * <p></p>
     */
    public static Object G_SiteURL
     
    /**
     * <p></p>
     */
    public static Object G_ShortTimeOut
     
    /**
     * <p></p>
     */
    public static Object URL
     
    /**
     * <p>Profile Local Environment : DEV_USERNAME
Profile QA Environment : DEV_USERNAME</p>
     */
    public static Object USERNAME
     
    /**
     * <p>Profile Local Environment : DEV_PASSWORD
Profile QA Environment : DEV_PASSWORD</p>
     */
    public static Object PASSWORD
     
    /**
     * <p></p>
     */
    public static Object EMAIL
     

    static {
        def allVariables = [:]        
        allVariables.put('default', ['G_Timeout' : 10, 'G_SiteURL' : 'http://demoaut.katalon.com', 'G_ShortTimeOut' : 5])
        allVariables.put('DEV Environment', allVariables['default'] + ['URL' : '', 'USERNAME' : '', 'PASSWORD' : ''])
        allVariables.put('Local Environment', allVariables['default'] + ['URL' : 'http://localhost:8080/home', 'USERNAME' : 'testuser1', 'PASSWORD' : 'Testuser@1', 'EMAIL' : 'abc@abc.com'])
        allVariables.put('QA Environment', allVariables['default'] + ['URL' : 'https://qa.sinharaja.io/', 'USERNAME' : 'testuser1', 'PASSWORD' : 'Testuser@1'])
        
        String profileName = RunConfiguration.getExecutionProfile()
        
        def selectedVariables = allVariables[profileName]
        G_Timeout = selectedVariables['G_Timeout']
        G_SiteURL = selectedVariables['G_SiteURL']
        G_ShortTimeOut = selectedVariables['G_ShortTimeOut']
        URL = selectedVariables['URL']
        USERNAME = selectedVariables['USERNAME']
        PASSWORD = selectedVariables['PASSWORD']
        EMAIL = selectedVariables['EMAIL']
        
    }
}
