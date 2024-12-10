package io.swagger.petstore.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.swagger.petstore.dtos.pet.PetJson;
import io.swagger.petstore.dtos.pet.PetStatus;
import io.swagger.petstore.service.Endpoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.swagger.petstore.dtos.pet.PetStatus.available;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTests extends TestBase {

    @Test
    public void getPetById() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        int petId = 1;

        Response response = given()
                .log().all()
                .get("/pet/" + petId)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.jsonPath().getInt("id")).isEqualTo(petId);
    }

    @Test
    public void getPetById2() {

        Long petId = 1L;

        PetJson response = given()
                .get(Endpoint.Pet.Get.findPetById(petId))
                .then()
                .extract()
                .response()
                .body().as(PetJson.class);

        assertThat(response.getId()).isEqualTo(petId);
    }

    @Test
    public void getPetById3() {

        Long petId = 1L;

        PetJson petById = petController.findPetById(petId);

        assertThat(petById.getId()).isEqualTo(petId);
    }

    @Test
    public void getPetByStatus() {

        PetStatus status = available;

        List<PetJson> pets = petController.findPetByStatus2(status);

        pets.forEach(pet -> assertThat(pet.getStatus()).isEqualTo(status.name()));
    }

    @Test
    public void findUserByUsername() {
        userController.findUserByUsername("user1");
    }

    @Test
    public void getInventory() {
        storeController.getInventory();
    }

    @Test
    public void findOrderById() {
        storeController.getOrderByOrderId(1);
    }
}
