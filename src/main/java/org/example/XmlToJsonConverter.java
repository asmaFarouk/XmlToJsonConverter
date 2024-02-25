package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlToJsonConverter {

    public static String convertXmlToJson(String xmlString) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(xmlString);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jsonNode);
    }

    public static String getDecisionFlowId(String jsonString) {
        try {
            return JsonPath.read(jsonString, "$.Body.ProcessRequestResponse.Response.responseData.Results.ExecuteDecisionSmartResponse.ExecuteDecisionSmartResult.DecisionFlowId");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getScoreValue(String jsonString) {
        try {
            JSONArray scoreArray = JsonPath.read(jsonString, "$.Body.ProcessRequestResponse.Response.responseData.Results.ExecuteDecisionSmartResponse.ExecuteDecisionSmartResult.DecisionSmartResults[?(@.ResultOutput.Name == 'SCORE')].ResultOutput.Value");

            if (!scoreArray.isEmpty()) {
                return scoreArray.get(0).toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            String xmlFilePath = System.getProperty("user.dir") + "/resources/XMLResponseData.xml";
            String xmlContent = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
            String jsonString = convertXmlToJson(xmlContent);
            String decisionFlowId = getDecisionFlowId(jsonString);
            System.out.println("DecisionFlowId= " + decisionFlowId);
            String score = getScoreValue(jsonString);
            System.out.println("SCORE= " + score);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}