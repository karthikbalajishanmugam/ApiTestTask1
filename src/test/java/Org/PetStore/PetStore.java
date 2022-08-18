package Org.PetStore;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
public class PetStore {
	
	@DataProvider(name ="status")
	public Object[][]getStatus(){
		return new Object[][] {{"sold"},{"available"},{"pending"}};
	}
@Test(priority = 1,dataProvider="status")
public void findpetstatus(String status){
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response = given().log().all().queryParam("status", status).header("Content-Type","application/json")
	.when().get("/pet/findByStatus")
	.then().assertThat().statusCode(200).extract().response().asString();
JsonPath js = new JsonPath(response);
String s = js.get("status[0]");
System.out.println(response);
System.out.println(s);}
@BeforeClass
public void Post(){
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response =given().log().all().header("Content-Type","application/json")
	.body("{\r\n"
			+ "  \"id\": 165,\r\n"
			+ "  \"category\": {\r\n"
			+ "    \"id\": 15,\r\n"
			+ "    \"name\": \"dog\"\r\n"
			+ "  },\r\n"
			+ "  \"name\": \"bolt\",\r\n"
			+ "  \"photoUrls\": [\r\n"
			+ "    \"string\"\r\n"
			+ "  ],\r\n"
			+ "  \"tags\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 18,\r\n"
			+ "      \"name\": \"india\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"status\": \"available\"\r\n"
			+ "}").when().post("/pet").then().log().all().assertThat().statusCode(200).body("id",equalTo(165)).extract().response().asString();
 System.out.println(response);
}
@AfterClass
public void get(){
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response=given().header("Content-Type","application/json")
	.when().get("/pet/165")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js =new JsonPath(response);
	int id = js.get("id");
	System.out.println(id);

System.out.println();

}
@Test(priority = 2)
public void put() {
	RestAssured.baseURI ="https://petstore.swagger.io/v2";
	String response =given().log().all().header("Content-Type","application/json")
	.body("{\r\n"
			+ "  \"id\": 165,\r\n"
			+ "  \"category\": {\r\n"
			+ "    \"id\": 15,\r\n"
			+ "    \"name\": \"dog\"\r\n"
			+ "  },\r\n"
			+ "  \"name\": \"bolt\",\r\n"
			+ "  \"photoUrls\": [\r\n"
			+ "    \"string\"\r\n"
			+ "  ],\r\n"
			+ "  \"tags\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 18,\r\n"
			+ "      \"name\": \"india\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"status\": \"sold\"\r\n"
			+ "}").when().post("/pet").then().log().all().assertThat().statusCode(200).body("id",equalTo(165)).extract().response().asString();
 System.out.println(response);

}



}
