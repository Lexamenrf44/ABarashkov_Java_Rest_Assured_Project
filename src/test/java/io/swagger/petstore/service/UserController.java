package io.swagger.petstore.service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.swagger.petstore.dtos.user.UserJson;

public class UserController {

    @Step("Find User by {username}")
    public UserJson findUserByUsername(String username) {
        return RestService.getAsDto(Endpoint.User.Get.findUserByUsername(username), UserJson.class);
    }

    @Step
    public Response createUser(Object user) {
        return RestService.postAsResponse(Endpoint.User.Post.createUser(), user);
    }
}
