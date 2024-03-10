package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class XmlToJsonConverter {

    public static JsonNode convertXmlToJson(String xmlFilePath) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readTree(new File(xmlFilePath)).get("Body").get("ProcessRequestResponse").get("Response").get("responseData");
    }

    public static String getNestedAttributeValue(JsonNode jsonNode, String parentPath, String attributeName) {
        String[] parentPathArray = parentPath.split("/");
        JsonNode currentNode = jsonNode;

        for (String path : parentPathArray) {
            if (path.matches("\\d+")) {
                int index = Integer.parseInt(path);
                if (currentNode.isArray() && currentNode.size() > index) {
                    currentNode = currentNode.path(index);
                }
            } else {
                currentNode = currentNode.path(path);
            }
        }

        JsonNode valueNode = currentNode.path(attributeName);
        if (valueNode.isMissingNode()) {
            System.out.println("Missing node for attribute: " + attributeName);
            return null;
        }
        return valueNode.asText();
    }
}
