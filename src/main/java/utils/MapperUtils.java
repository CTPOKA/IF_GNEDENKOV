package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;


public class MapperUtils {

    @SneakyThrows
    public static <T> T readFromFile(String path, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}