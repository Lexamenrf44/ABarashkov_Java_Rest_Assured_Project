package io.swagger.petstore.tests;

import com.github.javafaker.Faker;
import io.swagger.petstore.service.PetController;
import io.swagger.petstore.service.StoreController;
import io.swagger.petstore.service.UserController;
import io.swagger.petstore.spec.Specification;

public class TestBase {

    protected final PetController petController = new PetController();
    protected final UserController userController = new UserController();
    protected final StoreController storeController = new StoreController();
    protected final Faker faker = new Faker();

    static {
        Specification.installSpecification();
    }
}
