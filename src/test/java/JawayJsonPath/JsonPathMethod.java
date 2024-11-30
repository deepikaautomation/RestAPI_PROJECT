package JawayJsonPath;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathMethod {

	@Test
	public void getProductAPITest_Conditionalexamples() {
		RestAssured.baseURI="https://fakestoreapi.com";

		Response getresp=given().log().all()
				.when()
				.get("/products")
				.then()
				.extract().response();

		String jsonResponse=getresp.asString();
		//minimum price
		Double min=JsonPath.read(jsonResponse, "min($[*].price)");
		System.out.println("Minimum Price of product is: "   + min  );

		System.out.println("-------------------------------------"  );

		//maximum price
		Double max=JsonPath.read(jsonResponse, "max($[*].price)");
		System.out.println("Maximum Price of product is: "   + max  );
		System.out.println("-------------------------------------"  );
		//Average price
		Double avg=JsonPath.read(jsonResponse, "avg($[*].price)");
		System.out.println("Average Price of product is: "   + avg  );
		System.out.println("-------------------------------------"  );


		//Standard deviation price
		Double std=JsonPath.read(jsonResponse, "stddev($[*].price)");
		System.out.println("Standard deviation Price of product is: "   + std  );
		System.out.println("-------------------------------------"  );

		//Sum price
		Double sum=JsonPath.read(jsonResponse, "sum($[*].price)");
		System.out.println("Sum Price of product is: "   + sum  );
		System.out.println("-------------------------------------"  );

		//length of the array
		int lengthoftharray=JsonPath.read(jsonResponse, "length($)");
		System.out.println("total length is: "   + lengthoftharray  );
		System.out.println("-------------------------------------"  );


		//first of the array
		int first=JsonPath.read(jsonResponse, "first($[*].price)");
		System.out.println("first: "   + first  );
		System.out.println("-------------------------------------"  );

		//last of the array
		int last=JsonPath.read(jsonResponse, "last($[*].price)");
		System.out.println("last: "   + last  );
		System.out.println("-------------------------------------"  );
		
		
		//last of the array
				int last=JsonPath.read(jsonResponse, "index($[*].price,2)");
				System.out.println("last: "   + last  );
				System.out.println("-------------------------------------"  );

	}
}

