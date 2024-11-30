package Authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2APITest {
	
	
	public String getaccToken;
	@BeforeMethod
	public void getAccessToken() {
     RestAssured.baseURI="https://test.api.amadeus.com";
		
		 getaccToken= given().log().all()
				.contentType("application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "I1lpA4LpZRlqHhL7G7G3xFGQOi8glo5G")
				.formParam("client_secret", "aRbRGKOMjBKg1dVN")
				.when()
		        .post("/v1/security/oauth2/token")
		        .then()
		        .extract()
		        .path("access_token");
		
	
		System.out.println("Access Token is  "  +getaccToken);
		
	}
	
	@Test
	public void getFlightdetails() {
	     RestAssured.baseURI="https://test.api.amadeus.com";
			
			 Response respdetails=given().log().all()
					.contentType("application/x-www-form-urlencoded")
					.header("Authorization","Bearer " +getaccToken)
					.when()
			        .get("/v1/shopping/flight-destinations?origin=PAR&maxPrice=200")
			        .then()
			        .extract()
			        .response();
			// respdetails.prettyPrint();
			
		
			System.out.println(respdetails);
			
		}

}
