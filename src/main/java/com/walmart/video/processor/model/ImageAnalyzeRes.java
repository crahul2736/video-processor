package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageAnalyzeRes {

    public Metadata metadata;
    public List<Brand> brands = null;
    public Color color;
    public String requestId;
    public String modelVersion;
    public List<Object> objects = null;
    public Description description;
    public List<Category> categories = null;
    public ImageType imageType;
    public List<Tag> tags = null;
    private Map<String, java.lang.Object> additionalProperties = new HashMap<String, java.lang.Object>();

    public Map<String, java.lang.Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, java.lang.Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.metadata == null) ? 0 : this.metadata.hashCode()));
        result = ((result * 31) + ((this.brands == null) ? 0 : this.brands.hashCode()));
        result = ((result * 31) + ((this.color == null) ? 0 : this.color.hashCode()));
        result = ((result * 31) + ((this.requestId == null) ? 0 : this.requestId.hashCode()));
        result = ((result * 31) + ((this.modelVersion == null) ? 0 : this.modelVersion.hashCode()));
        result = ((result * 31) + ((this.objects == null) ? 0 : this.objects.hashCode()));
        result = ((result * 31) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((result * 31) + ((this.categories == null) ? 0 : this.categories.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.imageType == null) ? 0 : this.imageType.hashCode()));
        result = ((result * 31) + ((this.tags == null) ? 0 : this.tags.hashCode()));
        return result;
    }

    @Override
    public boolean equals(java.lang.Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ImageAnalyzeRes) == false) {
            return false;
        }
        ImageAnalyzeRes rhs = ((ImageAnalyzeRes) other);
        return ((((((((((((this.metadata == rhs.metadata) || ((this.metadata != null) && this.metadata.equals(rhs.metadata))) && ((this.brands == rhs.brands) || ((this.brands != null) && this.brands.equals(rhs.brands)))) && ((this.color == rhs.color) || ((this.color != null) && this.color.equals(rhs.color)))) && ((this.requestId == rhs.requestId) || ((this.requestId != null) && this.requestId.equals(rhs.requestId)))) && ((this.modelVersion == rhs.modelVersion) || ((this.modelVersion != null) && this.modelVersion.equals(rhs.modelVersion)))) && ((this.objects == rhs.objects) || ((this.objects != null) && this.objects.equals(rhs.objects)))) && ((this.description == rhs.description) || ((this.description != null) && this.description.equals(rhs.description)))) && ((this.categories == rhs.categories) || ((this.categories != null) && this.categories.equals(rhs.categories)))) && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null) && this.additionalProperties.equals(rhs.additionalProperties)))) && ((this.imageType == rhs.imageType) || ((this.imageType != null) && this.imageType.equals(rhs.imageType)))) && ((this.tags == rhs.tags) || ((this.tags != null) && this.tags.equals(rhs.tags))));
    }

}
