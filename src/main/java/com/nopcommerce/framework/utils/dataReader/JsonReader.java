package com.nopcommerce.framework.utils.dataReader;

import com.nopcommerce.framework.utils.file.FileManager;
import com.nopcommerce.framework.utils.logs.LogsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.jayway.jsonpath.DocumentContext;

import java.io.FileReader;

public class JsonReader {
    private final String TEST_DATA_PATH = "src/test/resources/test-data/";
    String jsonReader;
    String jsonFileName;

    public JsonReader(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsManager.error("Error reading json file:", jsonFileName, e.getMessage());
            jsonReader = "{}"; // Initialize to an empty JSON object to avoid null pointer exceptions
        }
    }

    //valid.username
    public String getJsonData(String jsonPath) {
        try {
            return JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsManager.error("Error reading json file for path:", jsonPath, e.getMessage());
            return "";
        }
    }

    public void setJsonData(String jsonPath, String value) {
        try {
            LogsManager.info("Setting json data for path:", jsonPath, "to value:", value);

            DocumentContext document = JsonPath.parse(jsonReader);

            try {
                document.set(jsonPath, value);
            } catch (Exception e) {
                String key = jsonPath.replace("$.", "");
                document.put("$", key, value);
            }

            jsonReader = document.jsonString();
            FileManager.writeToFile(TEST_DATA_PATH + jsonFileName + ".json", jsonReader);
            LogsManager.info("Successfully updated json file:", jsonFileName);

        } catch (Exception e) {
            LogsManager.error("Error setting json file for path:", jsonPath, e.getMessage());
        }
    }
}
