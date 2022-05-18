package com.walmart.video.processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.video.processor.model.ImageAnalyzeRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class AnalyzeLocalImage {
    private static String subscriptionKey = "0a3bc19ae7de40dc8aa81aafd4b250ed";
    private static String endpoint = "https://rc-image-processor.cognitiveservices.azure.com/";
    private static final String uriBase = endpoint + "vision/v3.2/analyze";
    private static String imgPath = "src/main/resources/IMG20220517202832.jpg";

    public ImageAnalyzeRes analyzeImage(Path imgPath) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String responseStr = "";
        ImageAnalyzeRes imageAnalyzeResObj = new ImageAnalyzeRes();
        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Brands,Categories,Description,Color,Objects,Tags,ImageType");
            builder.setParameter("model-version", "latest");

            //Convert image to bytes.
//            File localImage = new File(imgPath);
            byte[] imgBytes = Files.readAllBytes(imgPath);
            ByteArrayEntity requestEntity =
                    new ByteArrayEntity(imgBytes);

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                JSONObject json = new JSONObject(jsonString);
                log.info("CV Analyze API Response: {}", json.toString(2));
                responseStr = json.toString(2);
                ObjectMapper om = new ObjectMapper();
                imageAnalyzeResObj = om.readValue(responseStr, ImageAnalyzeRes.class);
            }
        } catch (Exception e) {
            // Display error message.
            log.error(e.getMessage());
        }
        return imageAnalyzeResObj;
    }
}
