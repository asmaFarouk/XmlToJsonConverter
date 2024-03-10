package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    public static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }
        return properties;
    }

    public static List<Map<String, String>> getAttributeInputs(Properties properties) {
        List<Map<String, String>> attributeInputs = new ArrayList<>();
        int totalAttributes = Integer.parseInt(properties.getProperty("totalAttributes", "0"));

        for (int i = 1; i <= totalAttributes; i++) {
            String parentPath = properties.getProperty("attribute" + i + ".parentPath");
            String attributeName = properties.getProperty("attribute" + i + ".attributeName");
            if (parentPath != null && attributeName != null) {
                attributeInputs.add(Map.of("parentPath", parentPath, "attributeName", attributeName));
            }
        }
        return attributeInputs;
    }

    public static List<String> getAttributeValues(XmlToJsonConverter xmlToJsonConverter, JsonNode jsonNode, List<Map<String, String>> attributeInputs) {
        List<String> attributeValues = new ArrayList<>();
        for (Map<String, String> input : attributeInputs) {
            String value = xmlToJsonConverter.getNestedAttributeValue(jsonNode, input.get("parentPath"), input.get("attributeName"));
            attributeValues.add(input.get("attributeName") + ": " + value);
        }
        return attributeValues;
    }
}
