package io.swagger.petstore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;

public class JsonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static String rootPath = "src/test/resources/json/";

    @SneakyThrows
    public static <T> T mapToJson(String fileName, Class<T> dtoClass) {
        return mapper.readValue(new File(rootPath + fileName + ".json"), dtoClass);
    }
}
