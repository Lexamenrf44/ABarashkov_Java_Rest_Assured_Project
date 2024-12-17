package io.swagger.petstore.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.swagger.petstore.dtos.pet.PetJson;
import io.swagger.petstore.dtos.pet.PetStatus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetController {

    @Step("Find Pet by {petId}")
    public PetJson findPetById(Long petId) {
        return RestService.getAsDto(Endpoint.Pet.Get.findPetById(petId), PetJson.class);
    }

    @Step("Find Pet by {status}")
    public List<PetJson> findPetByStatus2(PetStatus status) {
        return RestService.getAsList(Endpoint.Pet.Get.findPetByStatus(), Map.of("status", status), PetJson.class);
    }

    @Step("Find Pet by {status}")
    public List<PetJson> findPetByStatus(PetStatus status) {

        String endpoint = Endpoint.Pet.Get.findPetByStatus();
        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("status", status);

        return RestService.getAsList(endpoint, queryParams, PetJson.class);

    }

    @Step("Upload Pet Image by {petId}")
    public Response uploadPetImageByPetId(Long petId, File file) {
        return RestService.uploadImageAsResponse(Endpoint.Pet.Post.uploadPetImage(petId), file);
    }
}
