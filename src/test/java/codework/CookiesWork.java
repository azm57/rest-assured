package codework;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class CookiesWork {

	@Test
	public void testCookies() {
		given()

				.when().get("https://google.com")

				.then().statusCode(200).cookie("AEC").log();
	}

	@Test
	public void getCookiesInfo() {
		Response re = given()

				.when().get("https://google.com");

		Map<String, String> hm = re.getCookies();
		System.out.println("value of cookie is " + hm.values());
	}
	
	@Test
	public void testHeaders() {
		given()
		
		.when()
			.get("https://google.com")
			
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
		.log().all();
	}
	
	@Test
	public void getTestHeaders() {
		Response res = given()
		
		.when()
			.get("https://google.com");
		
		Headers headers = res.getHeaders();
		System.out.println(headers);
	}
}
