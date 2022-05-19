package com.walmart.video.processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.video.processor.model.ImageAnalyzeRes;
import com.walmart.video.processor.model.Tag;
import com.walmart.video.processor.utils.Constants;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnalyzeLocalImage {


    @Value("${azure.subscription.Key}")
    String subscriptionKey;

    @Value("${azure.analyze.api.endpoint}")
    String analyzeApiEndpoint;
    @Autowired
    Environment environment;

    /**
     * Call Azure CV Analyze API to identify objects and metadata in image.
     *
     * @param imgPath
     * @return
     */
    public ImageAnalyzeRes analyzeImage(Path imgPath) {
        final String uriBase = analyzeApiEndpoint + "vision/v3.2/analyze";
        log.info("image path: {} {}", imgPath, imgPath.getFileName());
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String responseStr = "";
        ImageAnalyzeRes imageAnalyzeResObj = new ImageAnalyzeRes();
        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("visualFeatures", "Brands,Categories,Description,Color,Objects,Tags,ImageType");
            builder.setParameter("model-version", "latest");

            //Convert image to bytes.
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
                imageAnalyzeResObj.setImgPath(imgPath.toString());
                imageAnalyzeResObj.setProductName(Arrays.asList(imgPath.getFileName().toString().split("-")).get(0));
                imageAnalyzeResObj.setImgUrl("http://"+ InetAddress.getLocalHost().getHostAddress()+":"+ environment.getProperty("server.port") + "/images/cropped/"+ imgPath.getFileName());
            }

            // Validating and sorting tag based on inventory and confidence.
            List<Tag> tagList = imageAnalyzeResObj.getTags().stream()
                    .filter(x -> Constants.INVENTORY.contains(x.getName().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
            tagList.sort(Comparator.comparing(a -> a.getConfidence()));
            imageAnalyzeResObj.setTags(tagList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return imageAnalyzeResObj;
    }
}
