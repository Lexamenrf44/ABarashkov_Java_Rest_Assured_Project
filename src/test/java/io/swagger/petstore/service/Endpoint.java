package io.swagger.petstore.service;

import io.swagger.petstore.dtos.pet.PetStatus;

public class Endpoint {

    public static class Pet {
        public static class Get {
            public static String findPetById(Long petId) {return "/pet/" + petId;}
            public static String findPetByStatus() {return "/pet/findByStatus";}
        }
    }
}
