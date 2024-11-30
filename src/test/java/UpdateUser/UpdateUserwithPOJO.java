package UpdateUser;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
public class UpdateUserwithPOJO {
	
	
	public String getrandomEmailid() {
		return "apiuser"+System.currentTimeMillis()+"@opencart.com";
	}
	
	
	
	public void getUserwithID(int usrid) {
		Response respo=given().log().all()
		             .contentType("application/json")
		             .header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		   //.pathParam("userid", usrid)
		                  .when().log().all()
		                             .get("/public/v2/users/" +usrid);
		                             
		
		
		JsonPath jsp=respo.jsonPath();
		jsp.prettyPrint();
		                             }
	
	
		         
		           
	@Test
	public void createuserwithPOJO() {
		
		RestAssured.baseURI="https://gorest.co.in";
		User us=new User("Manju",getrandomEmailid(),"female","active");
		
		int userid=given().log().all()
				.contentType("application/json")
				   .header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				      .body(us)
				       .when().log().all()
				          .post("/public/v2/users")
				             .then().log().all()
				                 .assertThat()
				                   .statusCode(201)
				                    .extract()
				                      .path("id");
				                     
		
		System.out.println("User id is->" + userid  );
		
		//getUserwithID(userid);
		
		//update user
		us.setName("Chinju");
		us.setStatus("inactive");
		
		given().log().all()
		.contentType("application/json")
		   .header("Authorization","Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		      .body(us)
		       .when().log().all()
		          .put("/public/v2/users/" +userid)
		             .then().log().all()
		                 .assertThat()
		                  .statusCode(200)
		                   .and()
		                   .body("id",equalTo(userid));
		                    
		
		
		//get user
		 getUserwithID(userid);
				       
	}
	
	

}
