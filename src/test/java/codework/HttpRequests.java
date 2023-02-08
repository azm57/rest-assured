package codework;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class HttpRequests {
	int id;

	@Test
	public void getUser() {
		given().pathParam("path1", "users").queryParam("page", 2)

				.when().get("https://reqres.in/api/{path1}")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(enabled = false)
	public void createUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("Name", "Azeem");
		data.put("Job", "Software Enginner");

		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" }, enabled = false)
	public void updateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("Name", "Azeem");
		data.put("Job", "Software Engineer");

		given().contentType("application/json").body(data)

				.when().put("https://reqres.in/api/users/" + id)

				.then().statusCode(200).log().all();

	}

	@Test(priority = 4, dependsOnMethods = { "createUser" }, enabled = false)
	public void deleteUser() {

		when().delete("https://reqres.in/api/users/" + id)

				.then().statusCode(204).log().all();

	}

	@Test(enabled = false)
	public void createUserUsingJSONObject() {
		JSONObject data = new JSONObject();
		data.put("name", "Azeem");
		data.put("job", "Software Engineer");

		given()
		.contentType("application/json").body(data.toString()).when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("Azeem")).body("job", equalTo("Software Engineer")).log().all();

	}

	@Test(enabled = false)
	public void createUserUsingPOJOClass() {
		POJO_PostRequest pojop = new POJO_PostRequest();
		pojop.setName("Azeem");
		pojop.setJob("Software Engineer");

		given().contentType("application/json").body(pojop)

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).body("name", equalTo("Azeem")).body("job", equalTo("Software Engineer")).log()
				.all();
	}
}
