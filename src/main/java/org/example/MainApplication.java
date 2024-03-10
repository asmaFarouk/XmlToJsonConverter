package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MainApplication {

    public static void main(String[] args){
        String xmlFilePath = System.getProperty("user.dir") + "/resources/XMLResponseData.xml";
        String configFilePath = System.getProperty("user.dir") + "/resources/config.properties";
        XmlToJsonConverter xmlToJsonConverter = new XmlToJsonConverter();
        ConfigReader configReader = new ConfigReader();
        try {
            JsonNode jsonNode = xmlToJsonConverter.convertXmlToJson(xmlFilePath);
            Properties properties = ConfigReader.loadProperties(configFilePath);
            List<Map<String, String>> attributeInputs = configReader.getAttributeInputs(properties);
            List<String> attributeValues = configReader.getAttributeValues(xmlToJsonConverter, jsonNode, attributeInputs);

            for (String value : attributeValues) {
                System.out.println(value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
