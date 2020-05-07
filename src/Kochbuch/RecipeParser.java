package Kochbuch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecipeParser {

    public List<Recipe> getRecipesFromJsonFile(String filePath) {
        File src = new File(filePath);
        if (src.exists()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(src, new TypeReference<List<Recipe>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
