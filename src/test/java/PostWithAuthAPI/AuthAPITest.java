package PostWithAuthAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import POJO.Credentials;;

public class AuthAPITest {

	
	@Test
	public void gettokenusingAuth() {


		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		String tokenID=given().log().all()
		.contentType("application/json")
		.body("{\n"
				+   "\"username\" : \"admin\", \n "
				+   "\"password\" : \"password123\"\n"
				+"}")
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.extract()
		.path("token");
		
		System.out.println(tokenID);
		Assert.assertNotNull("Token ID is "  +   tokenID);


	}
	
	
	@Test
	public void gettokenusingAuth_WithJSONFile() {


		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		String tokenID=given().log().all()
		.contentType("application/json")
		.body(new File("./src/test/resources/jsons/auth.json"))
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.extract()
		.path("token");
		
		System.out.println("Token ID is "  +   tokenID);
		Assert.assertNotNull(tokenID);


	}
	
	@Test
	public void gettokenusingAuth_WithPOJOClass() {


		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		Credentials cred=new Credentials("admin","password123");

		String tokenID=given().log().all()
		.contentType("application/json")
		.body(cred)
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.extract()
		.path("token");
		
		System.out.println("Token ID is "  +   tokenID);
		Assert.assertNotNull(tokenID);


	}

}
