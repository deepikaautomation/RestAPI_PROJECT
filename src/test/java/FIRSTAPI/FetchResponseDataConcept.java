package FIRSTAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchResponseDataConcept {

	@Test
	public void getContactAPI_withinvalidToken() {
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		
		
		given().log().all()
		  .header("Authorization", "Bearer eyJhbGciOi6IkpXVCJ9")
		    .when().log().all()
		      .get("/contacts")
		         .then().log().all()
		            .assertThat()
		              .statusCode(401)
		              .and()
                      .body("error",equalTo("Please authenticate."));
                      
		///contacts
		
	}
	
	@Test
	public void getContactAPI_withinvalidToken_withExtract() {
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		
		
		String errmsg=given().log().all()
		  .header("Authorization", "Bearer eyJhbGciOi6IkpXVCJ9")
		    .when().log().all()
		      .get("/contacts")
		         .then().log().all()
		            .assertThat()
		              .statusCode(401)
		              .and()
                      .extract()
                       .path("error");
		
		System.out.println(errmsg);
		Assert.assertEquals(errmsg, "Please authenticate.");
                      
		///contacts
		
	}
	
	@Test
public void getContactAPI_SinglUser() {
	RestAssured.baseURI="https://gorest.co.in";
	
	Response response=given()
	.header("Authorization","Bearer f5d4adcc2296fc61fae28fcd3acc1857a943370fa2a17e5a2e2372fca4053285")
	.when()
	.get("/public/v2/users/6940805");
	
	System.out.println("StatusCode =" + response.getStatusCode());
	System.out.println("StatusLine =" + response.getStatusLine());
	
	response.prettyPrint();
	JsonPath js=response.jsonPath();
	
	String username=js.getString("name");
	System.out.println(username);
	
	
	String userid=js.getString("id");
	System.out.println(userid);
	
		
	}
	
	@Test
	public void getContactAPI_MultiUser() {
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given()
		.header("Authorization","Bearer f5d4adcc2296fc61fae28fcd3acc1857a943370fa2a17e5a2e2372fca4053285")
		.when()
		.get("/public/v2/users");
		
		System.out.println("StatusCode =" + response.getStatusCode());
		System.out.println("StatusLine =" + response.getStatusLine());
		
		response.prettyPrint();
		JsonPath js=response.jsonPath();
		
		List<Integer> userids=js.getList("id");
		System.out.println(userids);
		
		Assert.assertTrue(userids.contains(7413153));
		
		
		List<String> usernames=js.getList("name");
		System.out.println(usernames);
		
		for(String e:usernames) {
			System.out.println(e);
			
		}
		
		
		

}
	@Test
	public void getProduct_FetchNestedData() {
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response rsp=given()
		.when()
		 .get("/products");
		
		System.out.println("StatusCode =" +rsp.statusCode());
		System.out.println("StatusLine =" +rsp.statusLine());
		
		rsp.prettyPrint();
		
		JsonPath jsp=rsp.jsonPath();
		List<String> lstoftitle=jsp.getList("title");
		System.out.println(lstoftitle);
		
		List<Integer> lstofids=jsp.getList("id");
		System.out.println(lstofids);
		
		
		List<Float> lstofprice=jsp.getList("price");
		System.out.println(lstofprice);
		
		List<Float> lstofratingrate=jsp.getList("rating.rate");
		System.out.println("rating Rate " +lstofratingrate);
		
		
		List<Integer> lstofratingcount=jsp.getList("rating.count");
		System.out.println("Rating Count " +lstofratingcount);
		
		
		for(int i=0;i<lstoftitle.size();i++) {
			
			String Title =lstoftitle.get(i);
			Object Price=lstofprice.get(i);
			
			System.out.println("Product Title: " +Title+ "Price is" + Price);
		}
		Assert.assertTrue(lstofratingcount.contains(120));
		 
	}
	

	
}
