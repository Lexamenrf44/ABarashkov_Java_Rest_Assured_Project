package io.swagger.petstore.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.swagger.petstore.dtos.pet.PetJson;
import io.swagger.petstore.dtos.pet.PetStatus;
import io.swagger.petstore.dtos.user.UserJson;
import io.swagger.petstore.jupiter.CreateUser;
import io.swagger.petstore.service.Endpoint;
import io.swagger.petstore.utils.JsonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.MULTIPART;
import static io.swagger.petstore.dtos.pet.PetStatus.available;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void creteUserBodyViaMap() {
        Map<String, Object> user = new HashMap<>();
        user.put("id", 0);
        user.put("username", faker.name().username());
        user.put("firstName", faker.name().firstName());
        user.put("lastName", faker.name().lastName());
        user.put("email", "a@a.com");
        user.put("password", "password3");
        user.put("phone", faker.phoneNumber().cellPhone());
        user.put("userStatus", 0);

        Response result = userController.createUser(user);
        int responseCode = result.jsonPath().getInt("code");
        assertThat(responseCode).isEqualTo(200);

        UserJson userJson = userController.findUserByUsername(user.get("username").toString());
        assertThat(userJson.getUsername()).isEqualTo(user.get("username"));
    }

    @Test
    @DisplayName("Bad Practice of Post Method - NEVER USE THAT")
    public void createUser() {
        String body = "{\n" +
                "                    \"firstName\": \"Henry\",\n" +
                "                    \"lastName\": \"Tillman\",\n" +
                "                    \"password\": \"password3\",\n" +
                "                    \"phone\": \"850-090-0095\",\n" +
                "                    \"id\": 0,\n" +
                "                    \"email\": \"a@a.com\",\n" +
                "                    \"username\": \"zenaida.conn\",\n" +
                "                    \"status\": 0\n" +
                "                }";
        userController.createUser(body);
    }

    @Test
    public void createUserViaLombok() {
        UserJson user = UserJson.builder()
                .id(0L)
                .username(faker.name().username())
                .firstName(faker.name().username())
                .lastName(faker.name().lastName())
                .email("a@a.com")
                .password("password3")
                .phone("850-090-0095")
                .userStatus(0L)
                .build();

        userController.createUser(user);
    }

    @Test
    public void createUserViaJsonFileManual() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UserJson user = mapper.readValue(new File("src/test/resources/json/user.json"), UserJson.class);
        user.setUsername(faker.name().username());
        userController.createUser(user);
    }

    @Test
    public void createUserViaJsonFile() {
        UserJson user = JsonMapper.mapToJson("user", UserJson.class);
        user.setUsername(faker.name().username());
        userController.createUser(user);
    }

    @Test
    @CreateUser()
    public void createUserWithAnnotationViaJsonDefault(UserJson user) {
    }

    @Test
    @CreateUser(userName = "SomeUserName")
    public void createUserWithAnnotationViaJsonUsernameSet(UserJson user) {
    }

    @Test
    @CreateUser(cleanUp = false)
    public void deleteUserViaUsername() {
        userController.findUserByUsername("madeleine.denesik");
        userController.deleteUserByUsername("madeleine.denesik");
    }

    @Test
    @CreateUser(userName = "ValidUser")
    public void updateUserViaUsername() {
        UserJson existingUser = userController.findUserByUsername("ValidUser");

        UserJson updatedUser = UserJson.builder()
                .id(existingUser.getId())
                .username(existingUser.getUsername())
                .firstName("Madeleine Updated")
                .lastName("Denesik Updated")
                .email("madeleine.updated@example.com")
                .password("password3")
                .phone("1234567890")
                .userStatus(existingUser.getUserStatus())
                .build();

        userController.updateUserByUsername("ValidUser", updatedUser);

        UserJson updatedUserFromApi = userController.findUserByUsername("ValidUser");
        assertEquals(updatedUser.getFirstName(), updatedUserFromApi.getFirstName(), "First name not updated");
        assertEquals(updatedUser.getLastName(), updatedUserFromApi.getLastName(), "Last name not updated");
        assertEquals(updatedUser.getEmail(), updatedUserFromApi.getEmail(), "Email not updated");
    }

    @Test
    public void uploadImageForPetByPetId() {
        PetJson existingPet = petController.findPetById(1L);

        File image = new File("src/test/resources/images/pet.png");

        Response response = given().contentType(MULTIPART)
                .multiPart("file", image, "image/png")
                .post(Endpoint.Pet.Post.uploadPetImage(existingPet.getId()))
                .then()
                .extract().response();

    }

    @Test
    public void uploadImageForPetByPetId2() {
        PetJson existingPet = petController.findPetById(1L);

        File image = new File("src/test/resources/images/pet.png");

        petController.uploadPetImageByPetId(existingPet.getId(), image);

    }
}
