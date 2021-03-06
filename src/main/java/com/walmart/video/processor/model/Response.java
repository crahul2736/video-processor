package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    @JsonProperty("Products")
    private List<ProductDetails> Products;
}
