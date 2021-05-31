package restassuredtestcases;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;

public class Testcase2 {
@Test
	public void soapexample() throws IOException { 
	      RestAssured.baseURI ="http://www.dneonline.com";
		  
		  FileInputStream fis = new FileInputStream(".\\Payload\\MultiplyReq.xml");
		  
		  Response res =given() .headers("content-type","text/xml") .body(IOUtils.toString(fis,
		  "UTF-8")). when() .post("/calculator.asmx"). then() .extract().response();
		  //System.out.println();
		  Assert.assertEquals(200, res.statusCode());
		  System.out.println("request created successfully");
		  }
		
}
