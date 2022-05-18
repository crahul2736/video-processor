package com.walmart.video.processor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetails {
    private String productName;
    private String dominantColor;
    private double confidence;
    private String tag;
    private String brand;
    private String imgUrl;
}
