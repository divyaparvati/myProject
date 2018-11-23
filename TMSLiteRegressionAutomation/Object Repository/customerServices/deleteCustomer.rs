<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>deleteCustomer</name>
   <tag></tag>
   <elementGuidId>8a8cc861-e55d-4b27-ab84-74996256b1d3</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n        \&quot;id\&quot;: ${objid},\n        \&quot;customerId\&quot;: \&quot;111121222\&quot;,\n        \&quot;addressTypeKey\&quot;: \&quot;LWF\&quot;,\n        \&quot;customerType\&quot;: \&quot;billing\&quot;,\n        \&quot;name\&quot;: \&quot;Billing Customer\&quot;,\n        \&quot;abbrevName\&quot;: \&quot;111121222LWF\&quot;,\n        \&quot;address\&quot;: {\n            \&quot;streetAddressLine1\&quot;: \&quot;#101-5-87\&quot;,\n            \&quot;streetAddressLine2\&quot;: \&quot;downtown\&quot;,\n            \&quot;city\&quot;: \&quot;chicago\&quot;,\n            \&quot;state\&quot;: \&quot;AK\&quot;,\n            \&quot;zipCode\&quot;: \&quot;61001\&quot;,\n            \&quot;country\&quot;: \&quot;USA\&quot;\n        },\n        \&quot;billingAddress\&quot;: {\n            \&quot;streetAddressLine1\&quot;: \&quot;\&quot;,\n            \&quot;streetAddressLine2\&quot;: \&quot;\&quot;,\n            \&quot;city\&quot;: \&quot;\&quot;,\n            \&quot;state\&quot;: \&quot;\&quot;,\n            \&quot;zipCode\&quot;: \&quot;\&quot;,\n            \&quot;country\&quot;: \&quot;USA\&quot;\n        },\n        \&quot;attention\&quot;: \&quot;Attention\&quot;,\n        \&quot;phone\&quot;: {},\n        \&quot;fax\&quot;: {},\n        \&quot;defaultTrackId\&quot;: \&quot;GTR1\&quot;,\n        \&quot;milepost\&quot;: \&quot;100\&quot;,\n        \&quot;alternateCode\&quot;: \&quot;alt10\&quot;,\n        \&quot;onlineStationId\&quot;: \&quot;TOLED\&quot;,\n        \&quot;servingStationId\&quot;: \&quot;LITRO\&quot;,\n        \&quot;defaultStationId\&quot;: \&quot;GALLO\&quot;,\n        \&quot;zoneId\&quot;: \&quot;TY\&quot;,\n        \&quot;deleted\&quot;: true,\n        \&quot;audit\&quot;: {\n            \&quot;createdDate\&quot;: \&quot;2018-08-28T15:38:02.412\&quot;,\n            \&quot;modifiedDate\&quot;: \&quot;2018-08-28T15:38:02.412\&quot;\n        }\n   &quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>http://localhost:8091/api/v1/customer/${objId}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
