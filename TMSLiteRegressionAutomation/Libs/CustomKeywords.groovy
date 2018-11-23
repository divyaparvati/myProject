
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import com.kms.katalon.core.testobject.ResponseObject

import java.lang.Boolean

import com.kms.katalon.core.testobject.TestObject

import java.util.List


def static "com.ge.tms.customer.CustomerActions.selectNewlyCreatedCustomer"(
    	String customerType	) {
    (new com.ge.tms.customer.CustomerActions()).selectNewlyCreatedCustomer(
        	customerType)
}

def static "com.ge.tms.customer.CustomerActions.generateRandomCustomerID"() {
    (new com.ge.tms.customer.CustomerActions()).generateRandomCustomerID()
}

def static "com.ge.tms.customer.CustomerActions.setcustomerIDToSearchPane"() {
    (new com.ge.tms.customer.CustomerActions()).setcustomerIDToSearchPane()
}

def static "com.ge.tms.customer.CustomerActions.clearcustomerIDToSearchPane"() {
    (new com.ge.tms.customer.CustomerActions()).clearcustomerIDToSearchPane()
}

def static "com.ge.tms.customer.CustomerActions.editCustomer"() {
    (new com.ge.tms.customer.CustomerActions()).editCustomer()
}

def static "com.ge.tms.customer.CustomerActions.setAlternativeCode"() {
    (new com.ge.tms.customer.CustomerActions()).setAlternativeCode()
}

def static "com.ge.tms.customer.CustomerActions.setAttention"() {
    (new com.ge.tms.customer.CustomerActions()).setAttention()
}

def static "com.ge.tms.customer.CustomerActions.setAddressTwo"() {
    (new com.ge.tms.customer.CustomerActions()).setAddressTwo()
}

def static "com.ge.tms.customer.CustomerActions.setMilePost"() {
    (new com.ge.tms.customer.CustomerActions()).setMilePost()
}

def static "com.ge.tms.customer.CustomerActions.clickSaveButton"() {
    (new com.ge.tms.customer.CustomerActions()).clickSaveButton()
}

def static "com.ge.tms.customer.CustomerActions.randomMilePost"() {
    (new com.ge.tms.customer.CustomerActions()).randomMilePost()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyRailRoadsInSelectRailRoadDropdown"(
    	ResponseObject resObj	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyRailRoadsInSelectRailRoadDropdown(
        	resObj)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySelectedRailroadAsDropdownValue"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifySelectedRailroadAsDropdownValue()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyAllStationsForASelectedRailRoad"(
    	ResponseObject getParticularRailRoad	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyAllStationsForASelectedRailRoad(
        	getParticularRailRoad)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyAllZonesForAStation"(
    	ResponseObject getAllZonesFromAStation	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyAllZonesForAStation(
        	getAllZonesFromAStation)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySelectedStationWithHeaderDetails"(
    	String stationCodeValue	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySelectedStationWithHeaderDetails(
        	stationCodeValue)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySelectedStationDetailsInRightPane"(
    	Object stationCodeValue	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySelectedStationDetailsInRightPane(
        	stationCodeValue)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyNewZoneCreatedSuccessfully"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyNewZoneCreatedSuccessfully()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyZoneMandatoryFields"(
    	Boolean isZoneIdEditable	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyZoneMandatoryFields(
        	isZoneIdEditable)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyCreatedStation"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyCreatedStation()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyErrorMessagesForMandatoryFieldsStation"(
    	Boolean isStationIdEditable	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyErrorMessagesForMandatoryFieldsStation(
        	isStationIdEditable)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyEditedZone"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyEditedZone()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyEditedStation"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyEditedStation()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyCancelButtonInEditStation"(
    	String expectedText	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyCancelButtonInEditStation(
        	expectedText)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyNewlyAddedTrack"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyNewlyAddedTrack()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyTrackErrorMessages"(
    	String functionality	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyTrackErrorMessages(
        	functionality)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyDeleteConfirmationMessage"(
    	String assetId	
     , 	TestObject deleteMessageObj	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyDeleteConfirmationMessage(
        	assetId
         , 	deleteMessageObj)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyDeleteMessageWhenStationHasZoneTracks"(
    	String setId	
     , 	String deleteMessageSecondPart	
     , 	Boolean isStation	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyDeleteMessageWhenStationHasZoneTracks(
        	setId
         , 	deleteMessageSecondPart
         , 	isStation)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyViewStationIdAfterZoneDeletion"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyViewStationIdAfterZoneDeletion()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyDeletedItemNotPresentInSearchView"(
    	String searchId	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyDeletedItemNotPresentInSearchView(
        	searchId)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyNoButtonInDeleteAction"(
    	String actualText	
     , 	String expectedText	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyNoButtonInDeleteAction(
        	actualText
         , 	expectedText)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyNoTracksFoundInSearch"(
    	String trackId	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyNoTracksFoundInSearch(
        	trackId)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyNoTracksFound"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyNoTracksFound()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyEditedTrackLength"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyEditedTrackLength()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyEditedTrackLengthNotSaved"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyEditedTrackLengthNotSaved()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyEditedTrackType"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyEditedTrackType()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyDuplicateTrackIDErrorMessage"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyDuplicateTrackIDErrorMessage()
}

def static "com.ge.tms.railroad.RailroadVerification.verifyZoneSearchWithZone"(
    	String stationString	
     , 	String zonestring	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyZoneSearchWithZone(
        	stationString
         , 	zonestring)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyStationSearchWithStation"(
    	String stationString	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyStationSearchWithStation(
        	stationString)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyMessagewhennoStationandZoneAvailable"(
    	String stationString	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyMessagewhennoStationandZoneAvailable(
        	stationString)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyIfStationCanBeRecreatedUnderSameRailroad"(
    	String stationIdText	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyIfStationCanBeRecreatedUnderSameRailroad(
        	stationIdText)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySearchByTrackID"(
    	java.util.List<com.ge.tms.railroad.TracksModel> tracksList	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySearchByTrackID(
        	tracksList)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySearchByTrackDescription"(
    	java.util.List<com.ge.tms.railroad.TracksModel> tracksList	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySearchByTrackDescription(
        	tracksList)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySearchByTrackType"(
    	java.util.List<com.ge.tms.railroad.TracksModel> tracksList	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySearchByTrackType(
        	tracksList)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySearchByTrackLength"(
    	java.util.List<com.ge.tms.railroad.TracksModel> tracksList	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySearchByTrackLength(
        	tracksList)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySearchByCarCapacity"(
    	java.util.List<com.ge.tms.railroad.TracksModel> tracksList	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySearchByCarCapacity(
        	tracksList)
}

def static "com.ge.tms.railroad.RailroadVerification.verifySearchByConditionCode"(
    	java.util.List<com.ge.tms.railroad.TracksModel> tracksList	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifySearchByConditionCode(
        	tracksList)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyIfZoneCanBeRecreatedUnderSameStation"(
    	String zoneIdText	) {
    (new com.ge.tms.railroad.RailroadVerification()).verifyIfZoneCanBeRecreatedUnderSameStation(
        	zoneIdText)
}

def static "com.ge.tms.railroad.RailroadVerification.verifyDeleteMessage"() {
    (new com.ge.tms.railroad.RailroadVerification()).verifyDeleteMessage()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnWaybillActionsDropdown"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnWaybillActionsDropdown()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnCustomerActionsDropdown"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnCustomerActionsDropdown()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOkButtonInPopupModal"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOkButtonInPopupModal()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickWayBillManagementTabInMainMenu"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickWayBillManagementTabInMainMenu()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnViewWayBillInManagementTab"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnViewWayBillInManagementTab()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnManageCustomers"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnManageCustomers()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnRailroadSetup"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnRailroadSetup()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnCancelButton"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnCancelButton()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnManageEquipment"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnManageEquipment()
}

def static "com.ge.tms.commonactions.CommonClickEvents.clickOnRule15OfferingsTab"() {
    (new com.ge.tms.commonactions.CommonClickEvents()).clickOnRule15OfferingsTab()
}

def static "com.ge.tms.rule15.Rule15Verification.selectOfferingInLeftPane"() {
    (new com.ge.tms.rule15.Rule15Verification()).selectOfferingInLeftPane()
}

def static "com.ge.tms.rule15.Rule15Verification.verifyRule15Offering"(
    	ResponseObject responseObject	) {
    (new com.ge.tms.rule15.Rule15Verification()).verifyRule15Offering(
        	responseObject)
}

def static "com.ge.tms.rule15.Rule15Verification.verifyFilteredResults"() {
    (new com.ge.tms.rule15.Rule15Verification()).verifyFilteredResults()
}

def static "com.ge.tms.rule15.Rule15Verification.verifyFilterChipExistence"() {
    (new com.ge.tms.rule15.Rule15Verification()).verifyFilterChipExistence()
}

def static "com.ge.tms.rule15.Rule15Verification.verifyRule15ConfirmationMessage"(
    	String trainId	
     , 	String railroadSelected	) {
    (new com.ge.tms.rule15.Rule15Verification()).verifyRule15ConfirmationMessage(
        	trainId
         , 	railroadSelected)
}

def static "com.ge.tms.rule15.Rule15Verification.verifyResendOfferOfRule15"(
    	String expectedTrainId	
     , 	String expectedAuthNumber	) {
    (new com.ge.tms.rule15.Rule15Verification()).verifyResendOfferOfRule15(
        	expectedTrainId
         , 	expectedAuthNumber)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerSize"(
    	ResponseObject resObj	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerSize(
        	resObj)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerPageHeader"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerPageHeader()
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerRightPageHeader"(
    	ResponseObject obj	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerRightPageHeader(
        	obj)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerDetailsInRightPane"(
    	String expectedCustomerId	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerDetailsInRightPane(
        	expectedCustomerId)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithCustomerId"(
    	String searchString	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerSearchWithCustomerId(
        	searchString)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithAddressType"(
    	String searchString	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerSearchWithAddressType(
        	searchString)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithPartialCustomerId"(
    	String partialSearchString	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerSearchWithPartialCustomerId(
        	partialSearchString)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerSearchWithPartialAddrKey"(
    	String partialSearchStrAddKey	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerSearchWithPartialAddrKey(
        	partialSearchStrAddKey)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerIDSort"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerIDSort()
}

def static "com.ge.tms.customer.CustomerVerification.verifyNameSort"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyNameSort()
}

def static "com.ge.tms.customer.CustomerVerification.verifyAddressKeySort"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyAddressKeySort()
}

def static "com.ge.tms.customer.CustomerVerification.verifyNameSortWithDescendingOrder"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyNameSortWithDescendingOrder()
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerIDWithDescendingOrder"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerIDWithDescendingOrder()
}

def static "com.ge.tms.customer.CustomerVerification.verifyAddressKeyWithDescendingOrder"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyAddressKeyWithDescendingOrder()
}

def static "com.ge.tms.customer.CustomerVerification.verifyModifiedDateSort"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyModifiedDateSort()
}

def static "com.ge.tms.customer.CustomerVerification.verifySaveButtonDisabled"(
    	TestObject testObject	) {
    (new com.ge.tms.customer.CustomerVerification()).verifySaveButtonDisabled(
        	testObject)
}

def static "com.ge.tms.customer.CustomerVerification.verifySaveButtonEnabled"(
    	TestObject testObject	) {
    (new com.ge.tms.customer.CustomerVerification()).verifySaveButtonEnabled(
        	testObject)
}

def static "com.ge.tms.customer.CustomerVerification.verifyCancelButtonInAddCustomer"(
    	TestObject addCustomerBtnObject	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCancelButtonInAddCustomer(
        	addCustomerBtnObject)
}

def static "com.ge.tms.customer.CustomerVerification.verifyInternalServerError"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyInternalServerError()
}

def static "com.ge.tms.customer.CustomerVerification.verifyFilterInactive"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyFilterInactive()
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerFilter"(
    	String customerType	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerFilter(
        	customerType)
}

def static "com.ge.tms.customer.CustomerVerification.verifyFilterTransportation"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyFilterTransportation()
}

def static "com.ge.tms.customer.CustomerVerification.verifyMandatoryFields"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyMandatoryFields()
}

def static "com.ge.tms.customer.CustomerVerification.verifyClearFilters"() {
    (new com.ge.tms.customer.CustomerVerification()).verifyClearFilters()
}

def static "com.ge.tms.customer.CustomerVerification.verifyCustomerEditedFields"(
    	String expectedCustomerId	) {
    (new com.ge.tms.customer.CustomerVerification()).verifyCustomerEditedFields(
        	expectedCustomerId)
}

def static "com.ge.tms.commonactions.Rule15Actions.getSelectedConsist"() {
    (new com.ge.tms.commonactions.Rule15Actions()).getSelectedConsist()
}

def static "com.ge.tms.commonactions.Rule15Actions.clickOnRandomCheckBox"() {
    (new com.ge.tms.commonactions.Rule15Actions()).clickOnRandomCheckBox()
}

def static "com.ge.tms.commonactions.Rule15Actions.addCarsAndResend"() {
    (new com.ge.tms.commonactions.Rule15Actions()).addCarsAndResend()
}

def static "com.ge.tms.commonactions.EquipmentActions.setEquipmentIdSearchPane"(
    	String equipmentIdText	) {
    (new com.ge.tms.commonactions.EquipmentActions()).setEquipmentIdSearchPane(
        	equipmentIdText)
}

def static "com.ge.tms.commonactions.EquipmentActions.generateRandomEquipmentID"() {
    (new com.ge.tms.commonactions.EquipmentActions()).generateRandomEquipmentID()
}

def static "com.ge.tms.commonactions.EquipmentActions.setequipmentIDToSearchPane"(
    	String equipmentID	) {
    (new com.ge.tms.commonactions.EquipmentActions()).setequipmentIDToSearchPane(
        	equipmentID)
}

def static "com.ge.tms.commonactions.EquipmentActions.setAddEquipmentIdField"(
    	String equipmentID	) {
    (new com.ge.tms.commonactions.EquipmentActions()).setAddEquipmentIdField(
        	equipmentID)
}

def static "com.ge.tms.commonactions.EquipmentActions.clearAddEquipmentIdField"() {
    (new com.ge.tms.commonactions.EquipmentActions()).clearAddEquipmentIdField()
}

def static "com.ge.tms.commonactions.EquipmentActions.clickAARMechTextField"() {
    (new com.ge.tms.commonactions.EquipmentActions()).clickAARMechTextField()
}

def static "com.ge.tms.commonactions.EquipmentActions.clickCancelBtn"() {
    (new com.ge.tms.commonactions.EquipmentActions()).clickCancelBtn()
}

def static "com.ge.tms.commonactions.EquipmentActions.clickEquipTypeRailCar"() {
    (new com.ge.tms.commonactions.EquipmentActions()).clickEquipTypeRailCar()
}

def static "com.ge.tms.commonactions.EquipmentActions.clickEquipTypeLocomotive"() {
    (new com.ge.tms.commonactions.EquipmentActions()).clickEquipTypeLocomotive()
}

def static "com.ge.tms.commonactions.EquipmentActions.getSelectedAarCarType"(
    	String kindKey	) {
    (new com.ge.tms.commonactions.EquipmentActions()).getSelectedAarCarType(
        	kindKey)
}

def static "com.ge.tms.commonactions.EquipmentActions.getSelectedCarKind"(
    	String equipmentType	) {
    (new com.ge.tms.commonactions.EquipmentActions()).getSelectedCarKind(
        	equipmentType)
}

def static "com.ge.tms.commonactions.EquipmentActions.setMandatoryFieldsForAddNewEquipment"() {
    (new com.ge.tms.commonactions.EquipmentActions()).setMandatoryFieldsForAddNewEquipment()
}

def static "com.ge.tms.customerverify.CustomerVerification.verifyCustomerActionsDropdownValues"() {
    (new com.ge.tms.customerverify.CustomerVerification()).verifyCustomerActionsDropdownValues()
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillActionsDropdown"() {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillActionsDropdown()
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyCarIDFirstRecord"(
    	String expectedCarId	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyCarIDFirstRecord(
        	expectedCarId)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWayBillNumberFirstRecord"(
    	String expectedWayBillNumber	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWayBillNumberFirstRecord(
        	expectedWayBillNumber)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifySenderFirstRecord"(
    	String expectedSender	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifySenderFirstRecord(
        	expectedSender)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyShipperFirstRecord"(
    	String expectedShipper	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyShipperFirstRecord(
        	expectedShipper)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyConsigneeFirstRecord"(
    	String expectedConsignee	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyConsigneeFirstRecord(
        	expectedConsignee)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.getSelectedWaybill"(
    	String wayBillId	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).getSelectedWaybill(
        	wayBillId)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillErrors"(
    	Object actualWaybillError	
     , 	Object expectedWaybillError	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillErrors(
        	actualWaybillError
         , 	expectedWaybillError)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyNoSearchMatched"() {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyNoSearchMatched()
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyCarIdSort"() {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyCarIdSort()
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillSize"(
    	ResponseObject waybillResponseObject	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillSize(
        	waybillResponseObject)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillOverViewTabDetails"(
    	ResponseObject waybillResponseObject	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillOverViewTabDetails(
        	waybillResponseObject)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillCustomerTabDetails"(
    	ResponseObject waybillResponseObject	
     , 	String selectedWaybillNumber	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillCustomerTabDetails(
        	waybillResponseObject
         , 	selectedWaybillNumber)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillVersionTabDetails"(
    	ResponseObject waybillResponseObject	
     , 	String selectedWaybillNumber	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillVersionTabDetails(
        	waybillResponseObject
         , 	selectedWaybillNumber)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterStatus"(
    	String waybillstatus	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillFilterStatus(
        	waybillstatus)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterLoadEmpty"(
    	String loadStatus	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillFilterLoadEmpty(
        	loadStatus)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterHazmat"(
    	String hazmatStatus	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillFilterHazmat(
        	hazmatStatus)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyWaybillFilterCommodity"(
    	String commodity	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyWaybillFilterCommodity(
        	commodity)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.getRandomWaybillId"() {
    (new com.ge.tms.waybillVerify.WaybillVerification()).getRandomWaybillId()
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyOriginFrmrightpane"(
    	String expectedOrigin	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyOriginFrmrightpane(
        	expectedOrigin)
}

def static "com.ge.tms.waybillVerify.WaybillVerification.verifyMessageforWrongSearch"(
    	String emsg	) {
    (new com.ge.tms.waybillVerify.WaybillVerification()).verifyMessageforWrongSearch(
        	emsg)
}

def static "com.ge.tms.util.CommonUtilities.login"() {
    (new com.ge.tms.util.CommonUtilities()).login()
}

def static "com.ge.tms.util.CommonUtilities.logout"() {
    (new com.ge.tms.util.CommonUtilities()).logout()
}

def static "com.ge.tms.util.CommonUtilities.getWaybillSize"() {
    (new com.ge.tms.util.CommonUtilities()).getWaybillSize()
}

def static "com.ge.tms.util.CommonUtilities.getSelectedWaybillAPI"(
    	String wayBillId	) {
    (new com.ge.tms.util.CommonUtilities()).getSelectedWaybillAPI(
        	wayBillId)
}

def static "com.ge.tms.util.CommonUtilities.getFirstCarIDFromAPI"() {
    (new com.ge.tms.util.CommonUtilities()).getFirstCarIDFromAPI()
}

def static "com.ge.tms.util.CommonUtilities.getFirstWaybillNumberFromAPI"() {
    (new com.ge.tms.util.CommonUtilities()).getFirstWaybillNumberFromAPI()
}

def static "com.ge.tms.util.CommonUtilities.getFirstSenderFromAPI"() {
    (new com.ge.tms.util.CommonUtilities()).getFirstSenderFromAPI()
}

def static "com.ge.tms.util.CommonUtilities.getWaybillAttachedToSenderID"() {
    (new com.ge.tms.util.CommonUtilities()).getWaybillAttachedToSenderID()
}

def static "com.ge.tms.util.CommonUtilities.getFirstShipperFromAPI"() {
    (new com.ge.tms.util.CommonUtilities()).getFirstShipperFromAPI()
}

def static "com.ge.tms.util.CommonUtilities.getFirstConsigneeFromAPI"() {
    (new com.ge.tms.util.CommonUtilities()).getFirstConsigneeFromAPI()
}

def static "com.ge.tms.util.CommonUtilities.getCustomerSize"() {
    (new com.ge.tms.util.CommonUtilities()).getCustomerSize()
}

def static "com.ge.tms.util.CommonUtilities.getSelectedCustomer"(
    	Object customerName	) {
    (new com.ge.tms.util.CommonUtilities()).getSelectedCustomer(
        	customerName)
}

def static "com.ge.tms.util.CommonUtilities.verifyDefualtSelectedBackground"(
    	TestObject testObject	) {
    (new com.ge.tms.util.CommonUtilities()).verifyDefualtSelectedBackground(
        	testObject)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEquipmentSorted"(
    	String sortBy	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEquipmentSorted(
        	sortBy)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEquipmentDataInView"(
    	String functionality	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEquipmentDataInView(
        	functionality)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEquipmentID"(
    	String expectedEquipmentId	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEquipmentID(
        	expectedEquipmentId)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyNoequipmentMessage"(
    	String noEquipmentMessage	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyNoequipmentMessage(
        	noEquipmentMessage)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEquipmentRecord"(
    	String equipID	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEquipmentRecord(
        	equipID)
}

def static "com.ge.tms.equipment.EquipmentVerification.getSelectedEquipmentAPI"(
    	String equipmentID	) {
    (new com.ge.tms.equipment.EquipmentVerification()).getSelectedEquipmentAPI(
        	equipmentID)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyClearButtonClearTheFilters"() {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyClearButtonClearTheFilters()
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEquipmentFilterFunctionality"(
    	String numberOfFilters	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEquipmentFilterFunctionality(
        	numberOfFilters)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEquipmentIdEmptyAndInvalidMsg"() {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEquipmentIdEmptyAndInvalidMsg()
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyEmptyCarKindMsg"(
    	String validationMessage	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyEmptyCarKindMsg(
        	validationMessage)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyCarKindList"(
    	String equipmentType	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyCarKindList(
        	equipmentType)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifyAarCarTypeList"(
    	String carKind	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifyAarCarTypeList(
        	carKind)
}

def static "com.ge.tms.equipment.EquipmentVerification.verifySaveButtonEnabled"(
    	String text	) {
    (new com.ge.tms.equipment.EquipmentVerification()).verifySaveButtonEnabled(
        	text)
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyManageInterchangeRoadText"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyManageInterchangeRoadText()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeRoadConsistView"(
    	ResponseObject interchangeResponseObj	
     , 	java.util.List<String> interchangeConsistDetailList	) {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyInterchangeRoadConsistView(
        	interchangeResponseObj
         , 	interchangeConsistDetailList)
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyDetailsOfSelectedCarQuickView"(
    	ResponseObject interchangeResponseObj	
     , 	int randomNumberOfCarSelected	) {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyDetailsOfSelectedCarQuickView(
        	interchangeResponseObj
         , 	randomNumberOfCarSelected)
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeFilterInboundOutbound"(
    	String labletext	) {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyInterchangeFilterInboundOutbound(
        	labletext)
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyInterchangeFilterInboundOutboundAll"(
    	String inbound	
     , 	String outbound	) {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyInterchangeFilterInboundOutboundAll(
        	inbound
         , 	outbound)
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyInvalidSearchResults"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyInvalidSearchResults()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyCarSearchResults"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyCarSearchResults()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyTrainIDSearchResults"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyTrainIDSearchResults()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.InterchangeSortByItemSort"(
    	String type	) {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).InterchangeSortByItemSort(
        	type)
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifySelectAndDeSelectAllAction"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifySelectAndDeSelectAllAction()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyInboundCarsAction"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyInboundCarsAction()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyAddCarsAction"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyAddCarsAction()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyRemoveCarAction"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyRemoveCarAction()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifyFlipConsistAction"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifyFlipConsistAction()
}

def static "com.ge.tms.interchange.InterchangeRoadVerification.verifySelectRange"() {
    (new com.ge.tms.interchange.InterchangeRoadVerification()).verifySelectRange()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnManageInterchangeRoadTab"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnManageInterchangeRoadTab()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnInterchangeTypeDropDown"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnInterchangeTypeDropDown()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnInterchangeTypeDropDownIO"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnInterchangeTypeDropDownIO()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnInterchangeTypeDropDownOO"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnInterchangeTypeDropDownOO()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnInterchangeTypeDropDownAll"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnInterchangeTypeDropDownAll()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByDropdown"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByDropdown()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByTimeAscending"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByTimeAscending()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByTimeDescending"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByTimeDescending()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByStation"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByStation()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByInterchangeType"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByInterchangeType()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByFromRailRoad"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByFromRailRoad()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnsortByTrainID"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnsortByTrainID()
}

def static "com.ge.tms.commonactions.InterchangeActions.clickOnSortBySortOrderButton"() {
    (new com.ge.tms.commonactions.InterchangeActions()).clickOnSortBySortOrderButton()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnSelectRailroadDropdown"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnSelectRailroadDropdown()
}

def static "com.ge.tms.commonactions.RailroadActions.selectRandomRailRoad"() {
    (new com.ge.tms.commonactions.RailroadActions()).selectRandomRailRoad()
}

def static "com.ge.tms.commonactions.RailroadActions.selectRandomRailRoadExclusiveFirstRailRoad"() {
    (new com.ge.tms.commonactions.RailroadActions()).selectRandomRailRoadExclusiveFirstRailRoad()
}

def static "com.ge.tms.commonactions.RailroadActions.selectARailroadNotSelectedBefore"() {
    (new com.ge.tms.commonactions.RailroadActions()).selectARailroadNotSelectedBefore()
}

def static "com.ge.tms.commonactions.RailroadActions.selectRandomStationByName"() {
    (new com.ge.tms.commonactions.RailroadActions()).selectRandomStationByName()
}

def static "com.ge.tms.commonactions.RailroadActions.selectRandomStation"() {
    (new com.ge.tms.commonactions.RailroadActions()).selectRandomStation()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnStationActionMenu"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnStationActionMenu()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnEditStationButton"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnEditStationButton()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnDeleteStationButton"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnDeleteStationButton()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnZoneSaveButton"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnZoneSaveButton()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnZoneCancelButton"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnZoneCancelButton()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnZoneActionMenu"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnZoneActionMenu()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnEditZoneButton"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnEditZoneButton()
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnDeleteAction"(
    	String deleteButton	) {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnDeleteAction(
        	deleteButton)
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnTestObject"(
    	TestObject testObj	) {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnTestObject(
        	testObj)
}

def static "com.ge.tms.commonactions.RailroadActions.clickOnDeleteTrack"() {
    (new com.ge.tms.commonactions.RailroadActions()).clickOnDeleteTrack()
}

def static "com.ge.tms.commonactions.RailroadActions.storeTracksDataToSearch"() {
    (new com.ge.tms.commonactions.RailroadActions()).storeTracksDataToSearch()
}

def static "com.ge.tms.commonactions.RailroadActions.selectFirstStation"() {
    (new com.ge.tms.commonactions.RailroadActions()).selectFirstStation()
}

def static "com.ge.tms.commonactions.RailroadActions.getSelectedZoneAPI"() {
    (new com.ge.tms.commonactions.RailroadActions()).getSelectedZoneAPI()
}

def static "com.ge.tms.commonactions.RailroadActions.getSelectedTrackAPI"() {
    (new com.ge.tms.commonactions.RailroadActions()).getSelectedTrackAPI()
}
