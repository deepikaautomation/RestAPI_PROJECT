package Authentication;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class Authlearn {
	

	
	@Test
	public void basicAuth() {
		
	RestAssured.baseURI="https://postman-echo.com";
		
		given().log().all()
				.auth().basic("postman", "password")
		.when()
		.get("/basic-auth")
		.then()
		.assertThat()
		
		.body("authenticated", equalTo(true));
		
		
	}
	
	@Test
	public void preemptiveAuth() {
		
	RestAssured.baseURI="https://postman-echo.com";
		
		given().log().all()
				.auth().preemptive()
				.basic("postman", "password")
		.when()
		.get("/basic-auth")
		.then()
		.assertThat()
		
		.body("authenticated", equalTo(true));
		
		
	}
	
	@Test
	public void digestiveAuth() {
		
	RestAssured.baseURI="https://postman-echo.com";
		
		given().log().all()
				.auth().digest("postman", "password")
				
		.when()
		.get("/basic-auth")
		.then()
		.assertThat()
		
		.body("authenticated", equalTo(true));
		
		
	}


}
