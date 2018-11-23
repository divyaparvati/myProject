package com.ge.tms.constants

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.junit.After

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
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

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CustomerPageConstants {

	/**
	 * Customer Page left pane selector
	 */
	public static final String CUSTOMER_SUMMARY = '//*[@data-testid="customer-summary"]';

	/**
	 * Customer Name error message in Add Customer page
	 */
	public static final String CUSTOMER_NAME_ERROR_MSG = "CUSTOMER NAME CANNOT BE EMPTY";

	/**
	 * Customer ID error message in Add Customer page
	 */
	public static final String CUSTOMER_ID_ERROR_MSG = "CUSTOMER ID CANNOT BE EMPTY";

	/**
	 * Address Type error message in Add Customer page
	 */
	public static final String ADDRES_TYPE_ERROR_MSG = "CUSTOMER ADDRESS TYPE CANNOT BE EMPTY";

	/**
	 * Customer Type error message in Add Customer page
	 */
	public static final String CUSTOMER_TYPE_ERROR_MSG = 'CUSTOMER TYPE CANNOT BE EMPTY'

	/**
	 * ALPHANUMERIC ONLY
	 */
	public static final String CUSTOMER_TYPE_ALPHA_NUEMERIC_ERROR_MSG = 'ALPHANUMERIC ONLY'
	/**
	 * Interal Server error message in Add Customer page
	 */
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

	/**
	 * Billing Key
	 */
	public static final String BILLING = "Billing";

	/**
	 * Inactive Key
	 */
	public static final String INACTIVE = "Inactive";

	/**
	 * Any Customer Key
	 */
	public static final String ANY_CUSTOMER = "Any";

	/**
	 * Active Key
	 */
	public static final String ACTIVE = "Active";

	/**
	 * Transportation Key
	 */
	public static final String TRANSPORTATION = "Transportation";

	/**
	 * Creating Customer with Mandatory fields
	 */
	public static final String MANDATORY = "Mandatory";

	/**
	 * Customer Type Drop down Selector
	 */
	public static final String CUSTOMER_TYPE_DROPDOWN ='document.querySelector("#customer-type").shadowRoot.querySelector("#trigger").click()';

	/**
	 * Selector for Billing in customer type drop down
	 */

	public static final String BILLING_SELECTOR ='document.querySelector("#customer-type").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1)").click()'

	/**
	 * Selector for INACTIVE in customer type drop down
	 */

	public static final String INACTIVE_SELECTOR ='document.querySelector("#customer-type").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2)").click()'

	/**
	 * Selector for TRANSPORTATION in customer type drop down
	 */

	public static final String TRANSPORTATION_SELECTOR = 'document.querySelector("#customer-type").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3)").click()'

	/**
	 * Internal Server Error Message Selector
	 */
	public static final String SERVER_ERROR_SELECTOR = 'return document.querySelector("#root px-alert-message").shadowRoot.querySelector("#message>span").innerHTML';

	/**
	 * 	CSS Class for Enabled Add Customer Button
	 */
	public static final String ADD_CUSTOMER_ENABLE_CLASS = "btn btn--tertiary";

	/**
	 * CSS Class for Enabled Save Button
	 */
	public static final String SAVE_BUTTON_ENABLE_CLASS = "btn--call-to-action";

	/**
	 * Cancel Text
	 */
	public static final String CANCEL = 'Cancel';

	/**
	 * None
	 */
	public static final String NONE="NONE"

	/**
	 * Active Filter Button in Customer Page left pane
	 */
	public static final String ACTIVE_BUTTON_FILTER = 'return document.querySelector("#root px-chip").shadowRoot.querySelector("div > div > button").click()'

	/**
	 * Active Filter Button TEXT in Customer Page left pane - Should be Active
	 */
	public static final String ACTIVE_BUTTON_TEXT = 'return document.querySelectorAll("#root px-chip")[0].shadowRoot.querySelector("div > div > span").innerHTML'

	/**
	 * In-Active Filter Button TEXT in Customer Page left pane - Should be Inactive
	 */
	public static final String IN_ACTIVE_BUTTON_TEXT_SELECTOR = 'return document.querySelectorAll("#root px-chip")[1].shadowRoot.querySelector("div > div > span").innerHTML'

	/**
	 * Sample Inactive Customer ID
	 */
	public static final String IN_ACTIVE_CUSTOMER_ID = "123456789"

	/**
	 * Manage Customer Navigation Selector on the top navigation pane
	 */

	public static final String MANAGE_CUSTOMERS_SELECTOR = "document.querySelector('px-app-nav').shadowRoot.querySelector('#items > px-app-nav-group:nth-child(13) > px-app-nav-subitem:nth-child(2)').click()"

	/**
	 * Ascending Icon in Left of Customer Page
	 */

	public static final String ASCENDING_ICON = 'return document.querySelectorAll("px-icon")[2].shadowRoot.querySelector("iron-icon").click()'

	/**
	 * Descending Icon in Left of Customer Page
	 */

	public static final String DESCENDING_ICON = 'return document.querySelectorAll("px-icon")[2].shadowRoot.querySelector("iron-icon").click()'

	/**
	 * Customer ID in sort by drop down
	 */
	public static final String CUSTOMER_ID_SORT_DROPDOWN ='return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()'

	/**
	 * Customer NAME in sort by drop down
	 */
	public static final String CUSTOMER_NAME_SORT_DROPDOWN ='return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").click()'

	/**
	 * Customer ADDRESS KEY in sort by drop down
	 */
	public static final String ADDRESS_TYPE_KEY_SORT_DROPDOWN ='return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(3) > span").click()'

	/**
	 * Customer MODIFIED DATE in sort by drop down
	 */
	public static final String MODIFIED_DATE_SORT_DROPDOWN ='return document.querySelectorAll("#root px-dropdown")[0].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(4) > span").click()'

	/**
	 * Country drop down Selector
	 */
	public static final String COUNTRY_SELECTOR='return document.querySelector("#country").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div").click()'

	/**
	 * State drop down Selector
	 */
	public static final String STATE_SELECTOR='return document.querySelector("#state").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1)").click()'

	/**
	 * ONLINE STATION drop down  Selector
	 */
	public static final String ONLINE_STATION_SELECTOR ='document.querySelector("#online-station").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2)").click()'

	/**
	 * ONLINE STATION drop down  Selector
	 */
	public static final String ONLINE_STATION_SELECTED_TEXT ='return document.querySelector("#online-station").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div.dropdown-option.iron-selected > span").innerHTML'

	/**
	 * Zone drop down Selector
	 */
	public static final String ZONE_SELECTOR = 'return document.querySelector("#zone").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1)").click()'

	/**
	 * Serving Station drop down Selector
	 */
	public static final String SERVICE_STATION_SELECTOR = 'return document.querySelector("#serving-station").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(4)").click()'

	/**
	 * Default Station drop down Selector
	 */
	public static final String DEFAULT_STATION_SELECTOR ='return document.querySelector("#default-station").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2)").click()'

	/**
	 * Default Track drop down Selector
	 */
	public static final String DEFAULT_TRACK_SELECTOR = 'return document.querySelector("#default-track").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1)").click()'

	/**
	 * Close icon in the customer type - to delete a selected customer type
	 */
	public static final String CLOSE_ICON_CUSTOMER_TYPE = 'document.querySelector("#customer-type").shadowRoot.querySelector("#trigger").shadowRoot.querySelector("#trigger > px-icon:nth-child(4)").click()'

	/**
	 * Customer Type error message
	 */
	public static final String CUSTOMER_TYPE_ERROR_MSG_XPATH = "//span[@class='form-field__help u-mt-- LabelValue-module_errorMsg_k_QeZ']"

	/**
	 * Size of the Stations in Add Customer
	 */
	public static final String STATION_DROPDOWN_SIZE = 'return document.querySelector("#online-station").shadowRoot.querySelector("#content").shadowRoot.querySelectorAll("#selector > div").length'

	/**
	 * Size of the Stations in Add Customer
	 */
	public static final String ZONES_DROPDOWN_SIZE = 'return document.querySelector("#zone").shadowRoot.querySelector("#content").shadowRoot.querySelectorAll("#selector > div").length'

	/**
	 * Default Station drop down Selector
	 */
	public static final String DEFAULT_STATION_TWO_SELECTOR ='document.querySelector("#default-station").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2)").click()'

	/**
	 * Default Station drop down selected value
	 */
	public static final String DEFAULT_STATION_SELECTED_TEXT = 'return document.querySelector("#default-station").shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(2) > span").innerHTML'

	/**
	 * Size of the TRACKS in Add Customer
	 */
	public static final String DEFAULT_TRACKS_DROPDOWN_SIZE = 'return document.querySelector("#default-track").shadowRoot.querySelector("#content").shadowRoot.querySelectorAll("#selector > div").length'

	/**
	 * Station ID
	 */
	public static final String STATION_ID = "TOLED"

	/**
	 * Edit Customer Selector in Actions Dropdown
	 */

	public static final String EDIT_CUSTOMER_SELECTOR = 'return document.querySelectorAll("#root px-dropdown")[1].shadowRoot.querySelector("#content").shadowRoot.querySelector("#selector > div:nth-child(1) > span").click()'

	/**
	 * Filters Display Div
	 */
	public static final String FILETER_DIV_XPATH = "//div[@class='flex flex--wrap u-mt']//px-chip"

	/**
	 * NO CUSTOMER FOUND TEXT
	 */
	public static final String NO_CUSTOMER_FOUND="No customers available"

	/**
	 * NO CUSTOMER AVAILABLE TEXT
	 */
	public static final String NO_CUSTOMER_AVAILABLE="No customers available"

	/**
	 * String array for negative data
	 */
	public static final String[] SEARCH_TEXT_NEGATIVE = [
		'1ABC100Z',
		'$%^^&19HAa',
		'kdhk119'
	]

	/**
	 * String array of customers
	 */
	//public static final String[] CUSTOMER_ARRAY = ["113331232", "213131332", "313333312","411433422"]
	public static final String[] CUSTOMER_ARRAY = [
		"111121222",
		"123456789",
		"187909876",
		"411433422"
	]

	/**
	 * Constant to click default station
	 */
	public static final String CLICK_DEFAULT_STATION='document.querySelector("#default-station").shadowRoot.querySelector("#trigger")'

	/**
	 * Constant to get the default station length
	 */
	public static final String DEFAULT_STATION_LENGTH = "return document.querySelector('#default-station').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant to get the default track
	 */
	public static final String CLICK_DEFAULT_TRACK = 'document.querySelector("#default-track").shadowRoot.querySelector("#trigger")'

	/**
	 * Constant to get the default track length
	 */
	public static final String DEFAULT_TRACK_LENGTH = "return document.querySelector('#default-track').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant to hold full left pane element selector xpath
	 */
	public static final String FULL_LEFT_PANE_ELEMENT_SELECTOR = '//*[@class="Sidebar-module_root_3mUZs CustomerSidebar-module_items_HuDFC flex__item flex flex--col"]/div[2]/div'

	/**
	 * Constant to hold left pane customer Id
	 */
	public static final String LEFTPANE_CUSTOMER_ID = '//*[@class="Sidebar-module_root_3mUZs CustomerSidebar-module_items_HuDFC flex__item flex flex--col"]/div[2]/div['

	/**
	 * Constant to click online station
	 */
	public static final String CLICK_ONLINE_STATION='document.querySelector("#online-station").shadowRoot.querySelector("#trigger").click'

	/**
	 * Constant to get the onlione station length
	 */
	public static final String ONLINE_STATION_LENGTH = "return document.querySelector('#online-station').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"

	/**
	 * Constant to click zone
	 */
	public static final String CLICK_ZONE = 'document.querySelector("#zone").shadowRoot.querySelector("#trigger").click'

	/**
	 * Constant to get the zone length
	 */
	public static final String ZONE_LENGTH = "return document.querySelector('#zone').shadowRoot.querySelector('#content').shadowRoot.querySelectorAll('#dropdown > div > #selector > div').length"
}
