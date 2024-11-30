package XMLAPIs;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;

public class XMLPathTest {


	@Test
	public void CircuitXML() {

		RestAssured.baseURI="http://ergast.com";

		Response getresp=given().log().all()
				.when()
				.get("/api/f1/2017/circuits.xml")
				.then()
				.extract().response();

		String responsebody=getresp.body().asString();
		System.out.println(responsebody);

		XmlPath xmlpath=new XmlPath(responsebody);

		List<String> cirname=	xmlpath.getList("MRData.CircuitTable.Circuit.CircuitName");

		for(String cir:cirname) {
			System.out.println(cir);


		}
		System.out.println("----------------------------------------------------");
	//	String locality=xmlpath.getString("**.findAll{it.circuitId=='americas'}.Location.Locality");
		
		 String locality = xmlpath.getString("**.findAll{it.@circuitId == 'americas'}.Location.Locality");
		
		System.out.println(locality);
	    System.out.println("----------------------------------------------------");
		
        String country=xmlpath.getString("**.findAll{it.@circuitId=='americas'}.Location.Country");
		
		System.out.println(country);
	    System.out.println("----------------------------------------------------");
	    
        List<String> countrylst=xmlpath.getList("**.findAll{it.@circuitId=='americas' || it.@circuitId=='bahrain'}.Location.Locality");
		
        
		System.out.println(countrylst);
	    System.out.println("----------------------------------------------------");
	    
	}
	
	
	@Test
	public void xmlpathCircuitXMLtest() {

		RestAssured.baseURI="http://ergast.com";

		Response getresp=given().log().all()
				.when()
				.get("/api/f1/2017/circuits.xml")
				.then()
				.extract().response();

		String responsebody=getresp.body().asString();
		System.out.println(responsebody);
		XmlPath xmlpath=new XmlPath(responsebody);
		
		List<String> cirtlist=xmlpath.getList("MRData.CircuitTable.Circuit.@circuitId");
		for(String e :cirtlist) {
        	System.out.println(e);
        }
		
		//System.out.println(cirtlist);
	    System.out.println("----------------------------------------------------");
	    
	    //fetch lat and long  where circuitid=baharin
	    
	    
	     String latitude = xmlpath.getString("**.findAll{it.@circuitId=='bahrain'}.Location.@lat");
	     System.out.println(latitude);
	     String longitude = xmlpath.getString("**.findAll{it.@circuitId=='bahrain'}.Location.@long");
	     System.out.println(longitude);
	     
	     String cirurl = xmlpath.getString("**.findAll{it.@circuitId=='americas'}.@url");
	     System.out.println(cirurl);
	
	
	}
	
	
}
