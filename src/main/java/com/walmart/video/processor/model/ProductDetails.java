package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@Builder
public class ProductDetails  implements Serializable {
    @JsonProperty("Product")
    private String Product;
    @JsonProperty("Color")
    private String Color;
    @JsonProperty("Confidence")
    private double Confidence;
    @JsonProperty("Tag")
    private String Tag;
    @JsonProperty("Brand")
    private String Brand;
    @JsonProperty("Image")
    private String Image;
}
