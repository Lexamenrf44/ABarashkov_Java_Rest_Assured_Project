package io.swagger.petstore.config;

public interface Config {

    static Config getInstance() {
        return LocalConfig.INSTANCE;
    }

    String apiBaseUri();
}
