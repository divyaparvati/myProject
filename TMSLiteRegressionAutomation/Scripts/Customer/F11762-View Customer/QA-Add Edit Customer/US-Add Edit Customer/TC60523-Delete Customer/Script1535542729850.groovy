import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.ge.tms.constants.CustomerPageConstants
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
import groovy.json.JsonSlurper;

String[] customerArray = CustomerPageConstants.CUSTOMER_ARRAY;
String objectIdOfCustomer;
for(int i=0;i<customerArray.length;i++){
	String customerId = customerArray[i];
	ResponseObject customerResponse = WS.sendRequest(findTestObject('Object Repository/customerServices/GET-SelectedCustomer',[('custId'): customerId]))
	System.out.println("Response Object:"+customerResponse.toString())
	
	String resText = customerResponse.getResponseBodyContent()
	JsonSlurper slupper = new JsonSlurper()
	def response  = slupper.parseText(resText)
	def customerResponseSize = response.size();
	
	objectIdOfCustomer = response[0].id;
	ResponseObject deleteCustomerResponse = WS.sendRequest(findTestObject('Object Repository/customerServices/deleteCustomer',[('objid'): objectIdOfCustomer]))
	String deletedFlag = deleteCustomerResponse.deleted
	System.out.println(deletedFlag)
	//ResponseObject customerResponse = WS.sendRequest(findTestObject('Object Repository/customerServices/GET-SelectedCustomer'))
}
