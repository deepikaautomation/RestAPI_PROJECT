package FIRSTAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.swing.border.TitledBorder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class GetAPIwithQueryparamsandPathparams {

	@Test
	public void getusUserwithQueryparam() {

		RestAssured.baseURI="https://gorest.co.in";

		given().log().all()
		.header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.queryParam("name", "Trivedi")
		.queryParam("status", "inactive")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType("application/json");

	}

	@DataProvider
	public Object[][] getUser() {
		return new Object[][] {
			{"Trivedi","inactive"},
			{"Naveen","active"}
		};
	}
	@Test(dataProvider="getUser")
	public void getusUserwithQueryparam_withHashMap(String username, String userstatus) {

		RestAssured.baseURI="https://gorest.co.in";
		Map<String,String> querymap=new HashMap<String,String>();
		querymap.put("name", username);
		querymap.put("status", userstatus);

		/*
		 * //OR MAp.of Map<String,String> qquerymap=Map.of( "name","Trivedi",
		 * "status","active");
		 */
		
		
		given().log().all()
		.header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.queryParams(querymap)
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType("application/json");

	}

	@DataProvider
	public Object[][] getUserdata() {
		return new Object[][] {
			{"7381453"}
			//{"7001901"},
			//{"6942433"}
		};
	}
	
	
@Test(dataProvider="getUserdata")
	public void getusUserwithpathParams(String usrid) {

		RestAssured.baseURI="https://gorest.co.in";


		given().log().all()
		.header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.pathParam("userid",usrid)
		.when().log().all()
		.get("/public/v2/users/{userid}/posts")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("title",hasItem("Ascit calcar virgo animi concido cultura eaque."));

	}
//Ascit calcar virgo animi concido cultura eaque. 7381453

public void getUser_withPathParam() {
	
}

}
