package UserDetails;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.testng.annotations.Test;

public class UserwithJsonArrayLombokTest {
	
	
	
	@Test
	public void createUserJson() {
		RestAssured.baseURI="https://postman-echo.com";
		
		
		
		User.UserData userdt1=new User.UserData(1, "abc@yopmail.com", "avcx", "sin", "https://reqres.in/img/faces/1-image. jpg");
		User.UserData userdt2=new User.UserData(2, "abcd@yopmail.com", "avcxx", "singh", "https://reqres.in/img/faces/2-image. jpg");
		User.UserData userdt3=new User.UserData(3, "abce@yopmail.com", "avmxx", "sgh", "https://reqres.in/img/faces/3-image. jpg");
		User.UserData userdt4=new User.UserData(4, "abcf@yopmail.com", "avkcxx", "sngh", "https://reqres.in/img/faces/4-image. jpg");
		User.UserData userdt5=new User.UserData(5, "abcg@yopmail.com", "avkxx", "sigh", "https://reqres.in/img/faces/5-image. jpg");
		User.UserData userdt6=new User.UserData(6, "abch@yopmail.com", "abkxx", "sih", "https://reqres.in/img/faces/6-image. jpg");
		
		User.Support support1=new User.Support("\"https://reqres.in/#support-heading\"", 
				"To keep ReqRes free,contributions towards server costs are appreciated!");
		
		User user=new User(100,10,150,1500,Arrays.asList(userdt1,userdt2,userdt3,userdt4,userdt5,userdt6),support1);
		
		
		
		
		given().log().all()
		 .body(user)
		  .when().log().all()
		     .post("/post")
		        .then().log() .all()
		        .assertThat()
		         .statusCode(200);
		
				 
		
		
		
		
	}
	
	
	@Test
	public void createUserJwithBuilder() {
		RestAssured.baseURI="https://postman-echo.com";
		
		
		User.UserData userdt1=new User.UserData.UserDataBuilder()
				.id(101)
				.email("abc1@yopmail.com")
				.firstname("MILAN")
				.last_name("abc")
				.avatar("https://reqres.in/img/faces/1-image. jpg")
				.build();
		User.UserData userdt2=new User.UserData.UserDataBuilder()
				.id(102)
				.email("abc2@yopmail.com")
				.firstname("aILAN")
				.last_name("aFc")
				.avatar("https://reqres.in/img/faces/1-image. jpg")
				.build();
		User.UserData userdt3=new User.UserData.UserDataBuilder()
				.id(103)
				.email("abc3@yopmail.com")
				.firstname("DMILAN")
				.last_name("GG")
				.avatar("https://reqres.in/img/faces/1-image. jpg")
				.build();
		User.UserData userdt4=new User.UserData.UserDataBuilder()
				.id(104)
				.email("abc4@yopmail.com")
				.firstname("MGLAN")
				.last_name("abcF")
				.avatar("https://reqres.in/img/faces/1-image. jpg")
				.build();
		User.UserData userdt5=new User.UserData.UserDataBuilder()
				.id(105)
				.email("abc5@yopmail.com")
				.firstname("MIFAN")
				.last_name("aGGbc")
				.avatar("https://reqres.in/img/faces/1-image. jpg")
				.build();
		User.UserData userdt6=new User.UserData.UserDataBuilder()
				.id(106)
				.email("abc6@yopmail.com")
				.firstname("MIFN")
				.last_name("aFFbc")
				.avatar("https://reqres.in/img/faces/1-image. jpg")
				.build();
		
		
		User.Support support1=new User.Support.SupportBuilder()
				.url("\"https://reqres.in/#support-heading\"")
				.text("To keep ReqRes free,contributions towards server costs are appreciated!")
				.build();
		
		
		User user=new User.UserBuilder()
		      .page(0)
		      .per_page(0)
		      .total(0)
		      .total_pages(0)
		      .data(Arrays.asList(userdt1,userdt2,userdt3,userdt4,userdt5,userdt6))
		      .support(support1)
		      .build();
		      
		
		
		
		
		
		
		
		given().log().all()
		 .body(user)
		  .when().log().all()
		     .post("/post")
		        .then().log() .all()
		        .assertThat()
		         .statusCode(200);
		
				 
		
		
		
		
	}
	

}
