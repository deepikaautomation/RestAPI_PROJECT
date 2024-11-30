package Deserilization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;



public class ProductTest {
	
	
	@Test
	public void createandgetProduct_WITHPOJODerilization() {
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response getresp=given().log().all()
		.when()
		.get("/products");
		
		
		//Deserilaize
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			Product[] pdtresponse=mapper.readValue(getresp.getBody().asString(),Product[].class);
			
			for(Product pdt : pdtresponse) {
				System.out.println(pdt.getId());
				System.out.println(pdt.getTitle());
				System.out.println(pdt.getDescription());
				System.out.println(pdt.getPrice());
				System.out.println(pdt.getImage());
				System.out.println(pdt.getCategory());
				System.out.println(pdt.getRating().getRate());
				System.out.println(pdt.getRating().getCount());
				
				System.out.println("-------------------------");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
