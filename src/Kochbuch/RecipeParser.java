package Kochbuch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RecipeParser {

    public List<Recipe> getRecipesFromJsonFile(String fileName) {
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(bufferedReader, new TypeReference<List<Recipe>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
