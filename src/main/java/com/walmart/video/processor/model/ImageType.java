
package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageType {

    public long lineDrawingType;
    public long clipArtType;
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((int)(this.lineDrawingType^(this.lineDrawingType >>> 32))));
        result = ((result* 31)+((int)(this.clipArtType^(this.clipArtType >>> 32))));
        return result;
    }

    @Override
    public boolean equals(java.lang.Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ImageType) == false) {
            return false;
        }
        ImageType rhs = ((ImageType) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&(this.lineDrawingType == rhs.lineDrawingType))&&(this.clipArtType == rhs.clipArtType));
    }

}
