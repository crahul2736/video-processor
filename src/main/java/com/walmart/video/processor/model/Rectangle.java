
package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rectangle {

    public long w;
    public long x;
    public long h;
    public long y;
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
        result = ((result* 31)+((int)(this.x^(this.x >>> 32))));
        result = ((result* 31)+((int)(this.h^(this.h >>> 32))));
        result = ((result* 31)+((int)(this.y^(this.y >>> 32))));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((int)(this.w^(this.w >>> 32))));
        return result;
    }

    @Override
    public boolean equals(java.lang.Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Rectangle) == false) {
            return false;
        }
        Rectangle rhs = ((Rectangle) other);
        return (((((this.x == rhs.x)&&(this.h == rhs.h))&&(this.y == rhs.y))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&(this.w == rhs.w));
    }

}
