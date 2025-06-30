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

    public static List<User> readUsersFromCSV(String fileName) {
        List<User> users = new ArrayList<>();

        try (InputStream inputStream = TestDataReader.class.getResourceAsStream(TEST_DATA_PATH + fileName)) {
            CSVFormat format = CSVFormat.Builder.create()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();

            CSVParser csvParser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, format);

            for (CSVRecord record : csvParser) {
                User user = new User();
                user.setUsername(record.get("username"));
                user.setPassword(record.get("password"));
                users.add(user);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + fileName, e);
        }

        return users;
    }

    public static User getUserByUsername(String fileName, String username) {
        return readUsersFromCSV(fileName).stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);
    }

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
