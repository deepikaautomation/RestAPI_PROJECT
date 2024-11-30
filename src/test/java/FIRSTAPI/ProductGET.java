package FIRSTAPI;

import static org.testng.Assert.assertNotEqualsDeep;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class ProductGET {
	
	@Test
	public void getproduct_1() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		given().log().all()
		  .get("/products")
		     .then().log().all()
		        .assertThat()
		            .statusCode(200);
		
		}
	

	
	@Test
	public void getproduct_2() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		given().log().all()
		   .when().log().all()
		      .get("/products")
		         .then().log().all()
		             .assertThat()
		                 .statusCode(200)
		                    .and()
		                      .body("$.size()",equalTo(50));
		                      
		                   
		
		}
	
	
	
	@Test
	public void getContactAPI() {
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		
		
		given().log().all()
		  .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk3ZmY5MWQ1MmFlNDAwMTM5N2RiMTgiLCJpYXQiOjE3MjEyMzg2NTF9.8palmDGtwCvt3TiKotKMyJukeQeJK3wkZKmjlxT9f0g")
		    .when().log().all()
		      .get("/contacts")
		         .then().log().all()
		            .assertThat()
		              .statusCode(200)
		              .and()
                      .body("$.size()",equalTo(34));
                      
		///contacts
		
	}
	
	
	}

