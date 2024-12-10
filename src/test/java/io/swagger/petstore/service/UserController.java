package io.swagger.petstore.service;

import io.qameta.allure.Step;
import io.swagger.petstore.dtos.user.UserJson;

public class UserController {

    @Step("Find User by {username}")
    public UserJson findUserByUsername(String username) {
        return RestService.getAsDto(Endpoint.User.Get.findUserByUsername(username), UserJson.class);
    }
}
