package BookingAPITest;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotNull;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookingAPIFeatureTest {
	String tokenID;

	@BeforeMethod
	public void getTokenID() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		tokenID=given().log().all()
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
	public void getAllBookingIDTest() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		Response resp=given().log().all()
				.when()
				.get("/booking")
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.extract()
				.response();

		JsonPath rspjspath=resp.jsonPath();
		List<Integer> bookingidlist=rspjspath.getList("bookingid");


		System.out.println( "Count of bookings= "  +  bookingidlist.size());


		for(Integer id:bookingidlist) {

			Assert.assertNotNull(id);
			System.out.println( bookingidlist.contains(5107));

		}
		//System.out.println();
	}



	@Test
	public void getSingleBookingIDTest() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		int newbookingid=createBooking();
		Assert.assertNotNull(newbookingid);

		given().log().all()
		.contentType("application/json")
		.pathParams("bookingid", newbookingid)
		.when()
		.get("/booking/{bookingid}")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("firstname", equalTo("Caren"))
		.and()
		.body("bookingdates.checkin",equalTo("2018-01-01"));



		//System.out.println();
	}


	@Test
	public void createABookingTest() {

		//create a booking
		Assert.assertNotNull(createBooking());

	}


	@Test
	public void updateABookingTest() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";		

		//create a booking
		int bookidforUpdate=createBooking();

		//Fetch bookid and 

		given().log().all()
		.contentType("application/json")
		.pathParams("bookingid", bookidforUpdate)
		.when()
		.get("/booking/{bookingid}")
		.then().log().all()
		.assertThat()
		.statusCode(200);

		//update this bookid

		given().log().all()
		.contentType("application/json")
		.header("Cookie","token=" +tokenID)
		.pathParam("bookingid", bookidforUpdate)
		.body("{\n"
				+ "\"firstname\": \"Deepika\",\n"
				+ "\"lastname\": \"automation\",\n"
				+ "\"totalprice\": 111,\n"
				+ "\"depositpaid\": true,\n"
				+ "\"bookingdates\": {\n"
				+ "    \"checkin\": \"2018-01-01\",\n"
				+ "    \"checkout\": \"2019-01-01\"\n"
				+ "},\n"
				+ "\"additionalneeds\": \"Dinner\"\n"
				+ "}")
		.when().log().all()
		.put("/booking/{bookingid}")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("firstname",equalTo("Deepika"))
		.and()
		.body("lastname",equalTo("automation"))
		.and()
		.body("additionalneeds",equalTo( "Dinner"));

	}

	
	
	@Test
	public void partialUpdateBookingTest() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";		

		//create a booking
		int bookidforUpdate=createBooking();

		//Fetch bookid and 

		given().log().all()
		.contentType("application/json")
		.pathParams("bookingid", bookidforUpdate)
		.when()
		.get("/booking/{bookingid}")
		.then().log().all()
		.assertThat()
		.statusCode(200);

		//partial update this bookid

		given().log().all()
		.contentType("application/json")
		.header("Cookie","token=" +tokenID)
		.pathParam("bookingid", bookidforUpdate)
		.body("{\n"
		    + "\"firstname\": \"Ishan\",\n"
			    + "\"lastname\": \"Manager\"\n"
			    + "}")
		.when().log().all()
		.patch("/booking/{bookingid}")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("firstname",equalTo("Ishan"))
		.and()
		.body("lastname",equalTo("Manager"))
		;

	}
	
	@Test
	public void deleteBookingTest() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";		

		//create a booking
		int bookidforUpdate=createBooking();

		//Fetch bookid and 

		given().log().all()
		.contentType("application/json")
		.pathParams("bookingid", bookidforUpdate)
		.when()
		.get("/booking/{bookingid}")
		.then().log().all()
		.assertThat()
		.statusCode(200);

		//delete  this bookid
		
		given().log().all()
		.contentType("application/json")
		.header("Cookie","token=" +tokenID)
		.pathParam("bookingid", bookidforUpdate)
		.when().log().all()
		.delete("/booking/{bookingid}")
		.then()
		.assertThat()
		.statusCode(201);

		

	}

	
	
	
	
	public int createBooking() {

		RestAssured.baseURI="https://restful-booker.herokuapp.com";

		int bookid=given().log().all()
				.contentType("application/json")
				.body("{\n"
						+ "\"firstname\": \"Caren\",\n"
						+ "\"lastname\": \"Brown\",\n"
						+ "\"totalprice\": 111,\n"
						+ "\"depositpaid\": true,\n"
						+ "\"bookingdates\": {\n"
						+ "    \"checkin\": \"2018-01-01\",\n"
						+ "    \"checkout\": \"2019-01-01\"\n"
						+ "},\n"
						+ "\"additionalneeds\": \"Breakfast\"\n"
						+ "}")
				.when().log().all()
				.post("/booking")
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.path("bookingid");



		System.out.println("Created bookid=" + bookid);

		return bookid;

	}


















}
