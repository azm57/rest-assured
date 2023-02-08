package codework;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponse {

	@Test
	public void testJSONResponseBodyData() {
		Response res = given().contentType(ContentType.JSON)

				.when().get("https://reqres.in/api/unknown");

		boolean status = false;
		int foundIndex = -1;
		JSONObject jo = new JSONObject(res.asString());
		for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
			String actName = jo.getJSONArray("data").getJSONObject(i).get("name").toString();
			if (actName.equals("true red")) {
				status = true;
				foundIndex = i;
				break;
			}
		}
		System.out.println("Book Found at "+foundIndex);
		Assert.assertEquals(status, true);
	}

}
