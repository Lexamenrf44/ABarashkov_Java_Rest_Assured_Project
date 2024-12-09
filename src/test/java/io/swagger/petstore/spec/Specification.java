package io.swagger.petstore.spec;

import io.swagger.petstore.config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class Specification {

    private static final Config config = Config.getInstance();

    private static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(config.apiBaseUri())
                .setContentType(JSON)
                .setRelaxedHTTPSValidation()
                .addHeader("User-Agent", "Autotest")
                .build()
                .filters(
                        new RequestLoggingFilter(URI),
                        new RequestLoggingFilter(METHOD),
                        new RequestLoggingFilter(BODY),
                        new ResponseLoggingFilter(BODY)
                );
    }

    private static ResponseSpecification responseSpecPositive() {
        return new ResponseSpecBuilder()
                .expectStatusCode(
                        anyOf(
                                is(200),
                                is(201)
                        ))
                .build();
    }

    private static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installSpecification() {
        installSpec(requestSpec(), responseSpecPositive());

        RestAssured.config = RestAssured.config().jsonConfig(jsonConfig()
                .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));

        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(AllureRestAppender.withCustomTemplates());
        }
    }
}
