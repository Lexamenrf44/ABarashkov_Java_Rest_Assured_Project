package io.swagger.petstore.service;

import io.qameta.allure.Step;
import io.swagger.petstore.dtos.store.OrderJson;
import io.swagger.petstore.dtos.store.StoreJson;

public class StoreController {

    @Step("Get inventory")
    public StoreJson getInventory(){
        return RestService.getAsDto(Endpoint.Store.Get.getInventory(), StoreJson.class);
    }

    @Step("Get order by {orderId}")
    public OrderJson getOrderByOrderId(Integer orderId){
        return RestService.getAsDto(Endpoint.Store.Get.getOrderByOrderId(orderId), OrderJson.class);
    }
}
