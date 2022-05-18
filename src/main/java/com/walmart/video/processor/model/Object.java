
package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Object {

    public double confidence;
    public Rectangle__1 rectangle;
    public String object;
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
        result = ((result* 31)+((this.rectangle == null)? 0 :this.rectangle.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.confidence)^(Double.doubleToLongBits(this.confidence)>>> 32))));
        result = ((result* 31)+((this.object == null)? 0 :this.object.hashCode()));
        return result;
    }

    @Override
    public boolean equals(java.lang.Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Object) == false) {
            return false;
        }
        Object rhs = ((Object) other);
        return (((((this.rectangle == rhs.rectangle)||((this.rectangle!= null)&&this.rectangle.equals(rhs.rectangle)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&(Double.doubleToLongBits(this.confidence) == Double.doubleToLongBits(rhs.confidence)))&&((this.object == rhs.object)||((this.object!= null)&&this.object.equals(rhs.object))));
    }

}
