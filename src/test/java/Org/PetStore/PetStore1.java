package Org.PetStore;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
public class PetStore1 {
	public static void main(String[] args) {
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
	JsonPath js =new JsonPath(response);
	int id = js.get("id");
	System.out.println(id);
	int id1 = js.get("category.id");
	System.out.println(id1);
	int id2 =js.get("tags[0].id");
	System.out.println(id2);
	String getResponse = given().log().all().pathParam("id",id).headers("Content-Type","application/json")
	.when().get("/pet/{id}")
	.then().assertThat().statusCode(200).extract().response().asString();
	JsonPath js1 =new JsonPath(getResponse);
	int Newid = js.get("id");
	System.out.println(Newid);
	Assert.assertEquals(id, Newid);
	}}


