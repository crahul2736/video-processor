package com.walmart.video.processor.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private List<ProductDetails> productDetails;
}
