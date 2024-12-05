package io.swagger.petstore.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SimpleTests {

    @Test
    public void getPetById() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        int petId = 1;

        Response response = given()
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200) // Verify HTTP status code is 200
                .body("id", equalTo(petId)) // Assert that the pet ID matches
                .extract()
                .response();

        System.out.println(response.asPrettyString());
    }
}
