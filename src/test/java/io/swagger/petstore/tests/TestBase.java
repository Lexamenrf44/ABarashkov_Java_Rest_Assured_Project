package io.swagger.petstore.tests;

import com.github.javafaker.Faker;
import io.swagger.petstore.service.PetController;
import io.swagger.petstore.spec.Specification;

public class TestBase {

    protected final PetController petController = new PetController();
    protected final Faker faker = new Faker();

    static {
        Specification.installSpecification();
    }
}
