package com.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.testng.annotations.Test;

public class TestDemo {

	@Test(description = "Get feed from nasa", priority = 1)
	public void verifyGetAPI() {

		given()
				.baseUri("https://api.nasa.gov")
				.header("Content-Type", "application/json")
				.param("start_date", "2015-09-07")
				.param("end_date", "2015-09-08")
				.param("api_key", "0tYXar4wy7l7RCIQ0w76QCYDqRqUbDwLdscRiGiq")
				.when()
				.get("/neo/rest/v1/feed")
				.then()
				.statusCode(200)
				.and()
				.body("element_count", equalTo(26))
				.body("$", hasKey("near_earth_objects"));
	}

	@Test(description = "Create a new post", priority = 2)
	public void verifyPostAPI() {
		given()

				.baseUri("https://jsonplaceholder.typicode.com")
				.header("Content-Type", "application/json")
				.when()
				.body("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1\n}")
				.post("/posts")
				.then()
				.statusCode(201)
				.body("userId", equalTo(1))
				.body("id", equalTo(101))
				.body("title", equalTo("foo"))
				.body("body", equalTo("bar"));
	}

	@Test(description = "Update post created", priority = 2)
	public void verifyPatchAPI() {
		given()
				.baseUri("https://jsonplaceholder.typicode.com")
				.header("Content-Type", "application/json")
				.when()
				.body("{\"id\": \"1\",\"title\": \"foo\", \"body\": \"update\", \"userId\": 1\n}")
				.patch("/posts/1")
				.then()
				.statusCode(200)
				.body("userId", equalTo(1))
				.body("title", equalTo("foo"))
				.body("body", equalTo("update"));
	}

}