package io.swagger.petstore.config;

enum LocalConfig implements Config {
    INSTANCE;

    @Override
    public String apiBaseUri() {
        return "https://petstore.swagger.io/v2";
    }

}
