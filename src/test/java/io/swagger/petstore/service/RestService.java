package io.swagger.petstore.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestService {

    @Step("POST {endpoint}")
    public static <T> T postAsDto(String endpoint, Object body, Class<T> dtoClass) {
        return given()
                .body(body)
                .post(endpoint)
                .then()
                .extract().body().as(dtoClass);
    }

    @Step("GET {endpoint}")
    public static <T> T getAsDto(String endpoint, Class<T> dtoClass) {
        return given()
                .get(endpoint)
                .then()
                .extract().body().as(dtoClass);
    }

    @Step("GET {endpoint}")
    public static <T> List<T> getAsList(String endpoint, Map<String, Object> queryParams, Class<T> dtoClass) {
        return given()
                .queryParams(queryParams)
                .get(endpoint)
                .then()
                .extract().body().jsonPath().getList(".", dtoClass);
    }

    @Step("POST {endpoint}")
    public static Response postAsResponse(String endpoint, Object body) {
        return given()
                .body(body)
                .post(endpoint)
                .then()
                .extract().response();
    }

    @Step("DELETE {endpoint}")
    public static Response deleteAsResponse(String endpoint, Object body) {
        return given()
                .body(body)
                .delete(endpoint)
                .then()
                .extract().response();
    }

    @Step("GET {endpoint}")
    public static <T> List<T> postAsListItems(String endpoint,
                                              Object body,
                                              Class<T> dtoClass) {
        return given()
                .body(body)
                .post(endpoint)
                .then()
                .extract().body().jsonPath().getList("items", dtoClass);
    }

    @Step("GET {endpoint}")
    public static <T> List<T> postAsList(String endpoint,
                                              Object body,
                                              Class<T> dtoClass) {
        return given()
                .body(body)
                .post(endpoint)
                .then()
                .extract().body().jsonPath().getList(".", dtoClass);
    }
}
