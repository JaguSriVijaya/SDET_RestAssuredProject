package restassuredtestcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelfiles.InputsFromExcel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Testcase1 {

	@Test(dataProvider = "InputsFromExcel")
	public void datasourceexample(double AccountNo, String Name, String Type,double PhoneNum,String HNO,String Place,String State) {
		RestAssured.baseURI = "http://localhost:3000/";
		JSONObject rootbodyobject = new JSONObject();
		JSONObject detailsobject = new JSONObject();
		JSONObject Addressobject = new JSONObject();

		rootbodyobject.put("id", 0);
		
		detailsobject.put("AccountNo", AccountNo);
		detailsobject.put("Name", Name);
		
		rootbodyobject.put("Type", Type);
		rootbodyobject.put("PhoneNum", PhoneNum);
		
		Addressobject.put("HNO",HNO);
		Addressobject.put("Place",Place);
		Addressobject.put("State",State);
		
		rootbodyobject.put("details",detailsobject);
		rootbodyobject.put("Address", Addressobject);
		//String input = rootbodyobject.toString();
		//System.out.println(input);

		Response res =given().body(rootbodyobject.toJSONString()).headers("content-type", "Application/JSON").
				        when().
				        post("AccountDetails").
				        then().
				        extract().response();
		
		//System.out.println(actualName);
		int statuscode = res.getStatusCode();
		String response = res.asString();	  
		AssertJUnit.assertEquals(201, statuscode);
		System.out.println("Status code is matching");
		
		String Responsebody = res.asString();
	   boolean ValidateName = Responsebody.contains(Name);
	   Assert.assertEquals(true, ValidateName);
	   System.out.println("Name in Response body is matching with the given name");
	   boolean ValidateType = Responsebody.contains(Type);
	   Assert.assertEquals(true, ValidateType);
	   System.out.println("Type in Response body is matching with the given Type");
	}
	@DataProvider(name = "InputsFromExcel")
	public Object[][] exceldata() throws IOException {
		Object[][] data = InputsFromExcel.getTestData();

		return data;

	}
}
