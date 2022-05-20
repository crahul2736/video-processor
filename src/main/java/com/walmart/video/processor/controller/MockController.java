package com.walmart.video.processor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.video.processor.model.ProductDetails;
import com.walmart.video.processor.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@Slf4j
@RestController
public class MockController {


    @GetMapping("mock")
    public ResponseEntity mockResponse() throws Exception {
        ObjectMapper om = new ObjectMapper();
        List<ProductDetails> productDetails = om.readValue(new File("src/main/resources/mockresponse.json"), List.class);
        Response response = new Response();
        response.setProducts(productDetails);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
