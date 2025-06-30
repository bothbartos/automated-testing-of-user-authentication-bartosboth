package utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestDataReader {

    private static final String TEST_DATA_PATH = "/test-data/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<User> readUsersFromJSON(String fileName) {
        try (InputStream inputStream = TestDataReader.class.getResourceAsStream(TEST_DATA_PATH + fileName)) {
            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, User.class);
            return objectMapper.readValue(inputStream, listType);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }
}
