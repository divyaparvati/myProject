package com.ge.tms.util
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.google.gson.JsonObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.rallydev.rest.RallyRestApi
import com.rallydev.rest.request.CreateRequest
import com.rallydev.rest.request.GetRequest
import com.rallydev.rest.request.QueryRequest
import com.rallydev.rest.response.CreateResponse
import com.rallydev.rest.response.QueryResponse
import com.rallydev.rest.util.Fetch
import com.rallydev.rest.util.QueryFilter
import internal.GlobalVariable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.testng.annotations.AfterTest
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.rallydev.rest.util.Ref;

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import groovy.json.JsonSlurper;
import groovy.json.internal.LazyMap

/**
 *   Class for Rally Api to get update
 */
public class RallyIntegration {

	/**
	 * Creating Object for Logger class to log the text to console
	 */
	KeywordLogger logger = new KeywordLogger()

	/**
	 *  Value for user directory
	 */
	public static final String USER_DIR = System.getProperty("user.dir")

	/**
	 *  Value for a particular file
	 */
	public static final String PROP_CONFIG_PATH = USER_DIR+"/Data Files/rallyProperties.json";

	/**
	 *  Method uses the TestCaseID and ResultStatus from @AfterTestCase to Update the
	 * corresponding Rally Testcase
	 * @param resultstatus, testcaseId
	 */
	public void addTestCaseResultToRally(String resultStatus, String testCaseId)
	throws IOException, URISyntaxException {
		String url = getRallyProperties("rallyURL")
		String key = getRallyProperties("apiKEY")
		String proxy = getRallyProperties("proxyURL")
		String buildNo = getRallyProperties("buildNo")
		RallyRestApi restApi = new RallyRestApi(new URI(url),key);
		// To set the Proxy
		restApi.setProxy(new URI(proxy));
		String testCaseRef = getTestCaseReference(restApi, testCaseId);
		JsonObject newTestCaseResult = new JsonObject();
		newTestCaseResult.addProperty("Verdict", resultStatus);
		newTestCaseResult.addProperty("Date", this.getSystemDateConvertToRallyDateFormat());
		newTestCaseResult.addProperty("Build",buildNo);
		newTestCaseResult.addProperty("Notes", "Result from Test Automation Run");
		newTestCaseResult.addProperty("TestCase", testCaseRef);
		CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
		CreateResponse createResponse = restApi.create(createRequest);
		if (createResponse.wasSuccessful()) {
			String ref = Ref.getRelativeRef(createResponse.getObject().get("_ref").getAsString());
			GetRequest getRequest = new GetRequest(ref);
			getRequest.setFetch(new Fetch("Date", "Verdict"));
			com.rallydev.rest.response.GetResponse getResponse = restApi.get(getRequest);
			JsonObject obj = getResponse.getObject();
		}
		else{
			String[] createErrors;
			createErrors = createResponse.getErrors();
		}
	}

	/**
	 * Function called from the addTestCaseResultToRally() to get the Test case reference variable
	 *
	 * @param restApi function to update rally is passed as parameter
	 * @param testCaseId test case id passed as parameter
	 * @return testCaseRef
	 * @throws IOException
	 */
	private String getTestCaseReference(RallyRestApi restApi, String testCaseId) throws IOException {
		// Create query request object for the Test Case attribute in rally
		QueryRequest testCaseRequest = new QueryRequest("TestCase");

		// Filter the query using the test case id
		testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", testCaseId));
		// Get the query response
		QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);

		// TO check if the query response was a success or failure
		logger.logInfo("testCaseQueryResponse success " + testCaseQueryResponse.wasSuccessful());
		JsonObject testCaseJsonObject = testCaseQueryResponse.getResults().get(0).getAsJsonObject();
		String testCaseRef = testCaseJsonObject.get("_ref").getAsString();
		logger.logInfo("testCaseRef for the particular test case id is : " + testCaseRef);
		return testCaseRef;
	}

	/**
	 * Function to get the system time and to covert into Rally Date time format
	 * @return date string
	 */
	public String getSystemDateConvertToRallyDateFormat() {
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
		Date date = new Date();
		String input = simpleDateFormat.format(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS X");
		OffsetDateTime offsetDateTime = OffsetDateTime.parse(input, formatter);
		logger.logInfo("Covert the system date to Rally date format: " + offsetDateTime.toInstant());
		Instant finalDate = offsetDateTime.toInstant();
		return finalDate.toString();
	}

	/**
	 * Method to read json for rally properties
	 * @param keyValue
	 * @return value
	 */
	public String getRallyProperties(String keyValue){
		Map<String, String> customerResponse;
		def jsonSlurper = new JsonSlurper()
		def dataFull = jsonSlurper.parse(new File(PROP_CONFIG_PATH))
		customerResponse = (LazyMap)dataFull.rally
		String value = customerResponse.get(keyValue)
		return value;
	}
}
