package Authentication;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SpotifyAPI {

	public String token;
	@BeforeMethod
	public void getAccessToken() {
		
		RestAssured.baseURI="https://accounts.spotify.com";
		 token=given()
		.contentType(ContentType.URLENC)
		.formParam("grant_type", "client_credentials")
		.formParam("client_id", "e5d1b780b57b4c51ad32e57378ccba41")
		.formParam("client_secret", "27d9d333003741ce9cb583c5898b2fee")
		.when()
		.post("/api/token")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("access_token");
		
		
		
		
	}
	
	@Test
public void getAritistusingID() {
		
		RestAssured.baseURI="https://api.spotify.com";
		Response getresponse=given()
		.header("Authorization","Bearer "+token )
		.when()
		.get("/v1/artists/4v0D49goEdstHHCMMlbpt1")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		
		getresponse.prettyPrint();
		System.out.println("-----------------------------------------------");
		
		String artistName=  getresponse.jsonPath().getString("name");
		Assert.assertEquals(artistName,"Chithra");
		System.out.println(artistName);
	}
}
