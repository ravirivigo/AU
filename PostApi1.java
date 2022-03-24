package org.example;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
public class PostApi1 extends payload {
static String id;
static String id1;
    static String token;
   static String Cnote = "" + (long) (Math.random() * 100000 + 3333300000L);
   static String Cnotebarcode= "" + (long) (Math.random() * 100000 + 3333300000L);




    @Test ()
    public void bearerToken() {
        test = extent.createTest("bearerToken");
        RestAssured op = new RestAssured();
        op.baseURI = "https://login.stg.rivigo.com";

      String tok_res =   given().header("Content-Type", "application/x-www-form-urlencoded")
              .formParam("client_id","sso")
                .formParam("username","smoke_oadelt1@rivigo.com")
                .formParam("password","Smoketest@zoom5")
                .formParam("grant_type","password")
              .formParam("session_reset","no")
            .when().post("sso/token").then().log().all().assertThat().statusCode(200).extract().asString();
      JsonPath v3 = new JsonPath(tok_res);
      token = v3.getString("response.access_token");

System.out.println(token);
    }

@Test (priority = 1)

public void addressAdd() throws IOException {
    System.out.println(token);
    FileReader reader=new FileReader("/home/rivigo/IdeaProjects/untitled11/src/test/java/org/example/utils/zoom.properties");
    Properties p = new Properties();

    p.load(reader);
    System.out.println(p.getProperty("env"));
String baseUri = p.getProperty("env");

    RestAssured ra = new RestAssured();
    ra.baseURI = baseUri;

    String AccessToken = "Bearer " + token;
System.out.println(AccessToken);


// From_Address
    String res_FromAddress = given().header("Authorization", AccessToken).header("Content-Type", "application/json;charset=UTF-8")
            .body("{\n" +
                    "   \"id\":null,\n" +
                    "   \"client\":{\n" +
                    "      \"id\":1519\n" +
                    "   },\n" +
                    "   \"addressType\":\"PICKUPANDDROP\",\n" +
                    "   \"address\":{\n" +
                    "      \"id\":null,\n" +
                    "      \"detailedAddress\":\"test test test3333395798\",\n" +
                    "      \"pincode\":\"110021\",\n" +
                    "      \"state\":\"Delhi\",\n" +
                    "      \"landmark\":null\n" +
                    "   },\n" +
                    "   \"name\":\"test\",\n" +
                    "   \"phoneNumber\":\"9971639104\",\n" +
                    "   \"isAppoinmentDelivery\":false,\n" +
                    "   \"contactPerson\":\"test\",\n" +
                    "   \"status\":\"ACTIVE\",\n" +
                    "   \"organizationId\":null\n" +
                    "}")
            .when().post("backend/master/client_address")
            .then().assertThat().statusCode(200).log().all().extract().response().asString();

    JsonPath kk = new JsonPath(res_FromAddress);
    id = kk.getString("payload.id");
    System.out.println(id);


// To_Address
    String res_to = given().header("Authorization",AccessToken).header("Content-Type", "application/json;charset=UTF-8")
            .body("{\n" +
                    "  \"id\":null,\n" +
                    "  \"client\":{\n" +
                    "    \"id\":1519\n" +
                    "  },\n" +
                    "  \"addressType\":\"PICKUPANDDROP\",\n" +
                    "  \"address\":{\n" +
                    "    \"id\":null,\n" +
                    "    \"detailedAddress\":\"test test test3333395798\",\n" +
                    "    \"pincode\":\"110021\",\n" +
                    "    \"state\":\"Delhi\",\n" +
                    "    \"landmark\":null\n" +
                    "  },\n" +
                    "  \"name\":\"test\",\n" +
                    "  \"phoneNumber\":\"9971639104\",\n" +
                    "  \"isAppoinmentDelivery\":false,\n" +
                    "  \"contactPerson\":\"test\",\n" +
                    "  \"status\":\"ACTIVE\",\n" +
                    "  \"organizationId\":null\n" +
                    "}\n")
            .when().post("backend/master/client_address")
            .then().log().all().assertThat().statusCode(200).extract().asString();
    JsonPath rs1 = new JsonPath(res_to);
    id1 = rs1.getString("payload.id");
    System.out.println(id1);

}

        // CN_Creation
    @Test (priority = 2)
    public void cnCreate() {
        RestAssured ra = new RestAssured();
        ra.baseURI = "https://zoom-kubernetes.stg.rivigo.com/throne";
        String AccessToken = "Bearer " + token;


        String res_CnCreation = given().log().all().header("Authorization",AccessToken).header("Content-Type", "application/json;charset=UTF-8")
                .body("{\n" +
                        "    \"id\": null,\n" +
                        "    \"cnoteType\": \"NORMAL\",\n" +
                        "    \"cnote\": \""+Cnote+"\",\n" +
                        "    \"clientCode\": \"UNIBC\",\n" +
                        "    \"serviceType\": \"ZOOM\",\n" +
                        "    \"gstNumber\": null,\n" +
                        "    \"fromPinCode\": \"110021\",\n" +
                        "    \"toPinCode\": \"110021\",\n" +
                        "    \"totalBoxes\": \"1\",\n" +
                        "    \"weight\": \"1\",\n" +
                        "    \"volume\": null,\n" +
                        "    \"value\": null,\n" +
                        "    \"bookingDateTime\": 1632464620321,\n" +
                        "    \"consignorAddress\": \"test\",\n" +
                        "    \"consignorAddressId\": "+id+",\n" +
                        "    \"consignorEmail\": \"testunibic@gmail.com\",\n" +
                        "    \"consignorPhone\": \"9971639104\",\n" +
                        "    \"consigneeAddress\": \"sdfv dvervev sc\",\n" +
                        "    \"consigneeAddressId\": "+id1+",\n" +
                        "    \"consigneeEmail\": null,\n" +
                        "    \"consigneePhone\": \"12313131313\",\n" +
                        "    \"contents\": \"Chemicals\",\n" +
                        "    \"consignorName\": \"test\",\n" +
                        "    \"consigneeName\": \"Rivigo costumer\",\n" +
                        "    \"consignmentDocumentDtoList\": [\n" +
                        "        {\n" +
                        "            \"document\": \"GST Invoice / Delivery Challan\",\n" +
                        "            \"status\": \"NOT_PICKED_UP\",\n" +
                        "            \"minInvoiceValue\": null,\n" +
                        "            \"documentType\": \"GSTIN number\",\n" +
                        "            \"order\": 99\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"document\": \"CN consignee copy\",\n" +
                        "            \"status\": \"NOT_PICKED_UP\",\n" +
                        "            \"minInvoiceValue\": null,\n" +
                        "            \"order\": 2\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"document\": \"CN POD copy\",\n" +
                        "            \"status\": \"NOT_PICKED_UP\",\n" +
                        "            \"minInvoiceValue\": null,\n" +
                        "            \"order\": 1\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"packing\": \"Bundle\",\n" +
                        "    \"barcodeType\": \"PRE_PRINTED\",\n" +
                        "    \"barcodes\": [\n" +
                        "        "+Cnotebarcode+"\n" +
                        "    ],\n" +
                        "    \"isDacc\": null,\n" +
                        "    \"invoices\": [\n" +
                        "        {\n" +
                        "            \"invoiceId\": null,\n" +
                        "            \"invoiceNo\": \"1\",\n" +
                        "            \"invoiceValue\": \"1\",\n" +
                        "            \"eWaybillNumber\": \"\",\n" +
                        "            \"hsnCodes\": null\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"volumeDetails\": [\n" +
                        "        {\n" +
                        "            \"unit\": \"IN\",\n" +
                        "            \"breadth\": \"1\",\n" +
                        "            \"height\": \"1\",\n" +
                        "            \"numberOfBoxes\": \"1\",\n" +
                        "            \"length\": \"1\",\n" +
                        "            \"volume\": 0.0006\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"valueAddedServicesDTO\": null,\n" +
                        "    \"paymentDetailsDTO\": null,\n" +
                        "    \"gstDetailsDTO\": null,\n" +
                        "    \"taxId\": null,\n" +
                        "    \"taxIdType\": null,\n" +
                        "    \"trackerDTO\": {\n" +
                        "        \"deviceIdType\": \"IP\",\n" +
                        "        \"deviceType\": \"ZOOM_OPS\"\n" +
                        "    },\n" +
                        "    \"openPopupTime\": 1573037386291,\n" +
                        "    \"allIssuesResolved\": true,\n" +
                        "    \"billingEntity\": \"UNIBIC FOODS INDIA PVT LTD.\",\n" +
                        "    \"vehicleNumber\": \"HR55V5525\",\n" +
                        "    \"consignmentCodDodDTO\": null,\n" +
                        "    \"deliveryType\": \"NORMAL\",\n" +
                        "    \"completionStatus\": \"COMPLETE\"\n" +
                        "}")
                .when().post("backend/operations/consignments")
                .then().log().all().assertThat().statusCode(200).log().all().extract().asString();
    }

    }


