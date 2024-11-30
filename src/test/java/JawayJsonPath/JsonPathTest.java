package JawayJsonPath;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class JsonPathTest {


	@Test
	public void getProductAPITest_JsonPath() {

		RestAssured.baseURI="https://fakestoreapi.com";

		Response getresp=given().log().all()
				.when()
				.get("/products");

		System.out.println(getresp.asString());

		String jsonresponse=getresp.asString();


		ReadContext cxt=JsonPath.parse(jsonresponse);
		List<Number> prices=cxt.read("$[?(@.price>50)].price");
		System.out.println(prices);

		List<Integer> ids=cxt.read("$[?(@.price>50)].id");
		System.out.println(ids);


		List<String> titles=cxt.read("$[?(@.price>50)].title");
		System.out.println(titles);

		List<Number> rates=cxt.read("$[?(@.price>50)].rating.rate");
		System.out.println(rates);

		List<Number> counts=cxt.read("$[?(@.price>50)].rating.count");
		System.out.println(counts);


		for(int i=0;i<ids.size();i++) {
			System.out.println(ids.get(i) + "Prices-> " + prices.get(i) + "Title-> " +  titles.get(i) );
		}



	}


	@Test
	public void getProductAPITest_Conditionalexamples() {

		RestAssured.baseURI="https://fakestoreapi.com";

		Response getresp=given().log().all()
				.when()
				.get("/products");

		//System.out.println(getresp.asString());

		String jsonresponse=getresp.asString();


		ReadContext cxt=JsonPath.parse(jsonresponse);
		
		List<Number> ratelessthan3=cxt.read("$[?(@.rating.rate<3)].id");

		System.out.println(ratelessthan3);
		
		System.out.println("---------------------------------------");
		//Fetch title and price category -jewellery
		
		
		List<Map<String,Object>> jewelery=cxt.read("[?(@.category =='jewelery')].['title','price']");
		
		System.out.println(jewelery);
		
		for(Map<String,Object> pdt:jewelery) {
			//System.out.println(pdt.get("title"));
			//System.out.println(pdt.get("price"));
		  String titlename=(String)pdt.get("title");
		  Number price=(Number)pdt.get("price");
		  System.out.println(titlename);
		  System.out.println(price);
		  
		  System.out.println("---------------------------------------");
		  
		}
		
		
		
		@Test
		public void getProductAPITest_Conditionalexamples_with3attributes() {

			RestAssured.baseURI="https://fakestoreapi.com";

			Response getresp=given().log().all()
					.when()
					.get("/products");

			//System.out.println(getresp.asString());

			String jsonresponse=getresp.asString();


			ReadContext cxt=JsonPath.parse(jsonresponse);
			
			List<Number> ratelessthan3=cxt.read("$[?(@.rating.rate<3)].id");

			System.out.println(ratelessthan3);
			
			System.out.println("---------------------------------------");
			//Fetch title and price category -jewellery
			
			
			List<Map<String,Object>> jewelery=cxt.read("[?(@.category =='jewelery')].['title','price','id','category']");
			
			System.out.println(jewelery);
			
			for(Map<String,Object> pdt:jewelery) {
				//System.out.println(pdt.get("title"));
				//System.out.println(pdt.get("price"));
			  String titlename=(String)pdt.get("title");
			  Number price=(Number)pdt.get("price");
			  int id=(Integer) pdt.get("id");
			  String category=(String)pdt.get("category");
			  System.out.println(titlename);
			  System.out.println(price);
			  System.out.println(id);
			  System.out.println(category);
			  
			  System.out.println("---------------------------------------");
			  
			}
	}

		@Test
		public void getProductAPITest_2Conditionalexamples_with3attributes() {

			RestAssured.baseURI="https://fakestoreapi.com";

			Response getresp=given().log().all()
					.when()
					.get("/products");

			String jsonresponse=getresp.asString();


			ReadContext cxt=JsonPath.parse(jsonresponse);
			
			System.out.println("---------------------------------------");
			//Fetch title and price category -jewellery
			
			
			List<String> jewelery=cxt.read("[?((@.category =='jewelery')&&(@.price>20))].title");
			
			System.out.println(jewelery);
			
  

	}
		
		//$[?((@.id== 3) && (@.username=='kevinryan'))].address.city



}
