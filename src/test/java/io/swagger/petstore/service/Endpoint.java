package io.swagger.petstore.service;

public class Endpoint {

    public static class Pet {
        public static class Get {
            public static String findPetById(Long petId) {return "/pet/" + petId;}
            public static String findPetByStatus() {return "/pet/findByStatus";}
        }
        public static class Post {
            public static String uploadPetImage(Long petId) {return "/pet/" + petId + "/uploadImage";}
        }
    }

    public static class User {
        public static class Get {
            public static String findUserByUsername(String username) {return "/user/" + username;}
        }
        public static class Post {
            public static String createUser() {return "/user";}
        }
        public static class Put {
            public static String updateUserByUsername(String username) {return "/user/" + username;}
        }
        public static class Delete {
            public static String deleteUserByUsername(String username) {return "/user/" + username;}
        }
    }

    public static class Store {
        public static class Get {
            public static String getInventory() {return "/store/inventory";}
            public static String getOrderByOrderId(Integer orderId) {return "/store/order/" + orderId;}
        }
    }
}
