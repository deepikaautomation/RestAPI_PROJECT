package FakeStoreAPI;

import org.testng.annotations.Test;

import FakeStoreAPI.UserFakestoreLombok.Address.Geolocation;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.restassured.AllureRestAssured;

public class CreatefakeUserTest {

	 static AllureRestAssured allureFilter = new AllureRestAssured();
	public String getrandomEmailid() {
		return "apiuser"+System.currentTimeMillis()+"@opencart.com";
	}

	@Test
	public void cretefakeUSer_withLombok() {
		RestAssured.baseURI="https://fakestoreapi.com";

		UserFakestoreLombok.Address.Geolocation geolocation =new UserFakestoreLombok.Address.Geolocation("0.98.98","0.107.1875.87");

		// -this way also possible
		//Geolocation geolocation1=new Geolocation("0.98.98","0.107.1875.87")  -this way also possible

		UserFakestoreLombok.Address address=new UserFakestoreLombok.Address("City1","Strret",6599,"L5N2X3",geolocation);

		UserFakestoreLombok.Name name=new UserFakestoreLombok.Name("Firstnamedee","lastnamedr");
		UserFakestoreLombok user=new UserFakestoreLombok(getrandomEmailid(),"user1","passwor123","23265622323",name,address);


		

		Response rsp=given().filter(allureFilter).log().all()
		.body(user)
		.when().log().all()
		.post("/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		
		rsp.prettyPrint();


	}	
	
	
	@Test
	public void cretefakeUSer_withBuilderLombok() {
		RestAssured.baseURI="https://fakestoreapi.com";

	


		UserFakestoreLombok.Address.Geolocation geolocation=new UserFakestoreLombok.Address.Geolocation.GeolocationBuilder()
	             .lat("0.98.98")
	             .longtiude("0.107.1875.87")
	             .build();
		
		UserFakestoreLombok.Address address=new UserFakestoreLombok.Address.AddressBuilder()
		       .city("City1")
		       .Street("Strret")
		       .number(6599)
		       .zipcode("L5N2X3")
		       .geolocation(geolocation)
		       .build();
		
		UserFakestoreLombok.Name name=new UserFakestoreLombok.Name.NameBuilder()
		       .firstname("Fddee")
		       .lastname("lastnedr")
		       .build();
		
		UserFakestoreLombok user=new UserFakestoreLombok.UserFakestoreLombokBuilder()
		.email(getrandomEmailid())
		.username("ddddsd")
		.password("Fdsds34dee")
		.phone("34343434343")
		.address(address)
		.name(name)
		.build();
		
		

		Response rsp=given().log().all()
		.body(user)
		.when().log().all()
		.post("/users")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		
		rsp.prettyPrint();


	}		


}
