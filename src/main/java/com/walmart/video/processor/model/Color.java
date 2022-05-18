
package com.walmart.video.processor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Color {

    public String dominantColorForeground;
    public boolean isBwImg;
    public boolean isBWImg;
    public String accentColor;
    public String dominantColorBackground;
    public List<String> dominantColors = null;
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
        result = ((result* 31)+((this.dominantColorForeground == null)? 0 :this.dominantColorForeground.hashCode()));
        result = ((result* 31)+(this.isBwImg? 1 : 0));
        result = ((result* 31)+(this.isBWImg? 1 : 0));
        result = ((result* 31)+((this.accentColor == null)? 0 :this.accentColor.hashCode()));
        result = ((result* 31)+((this.dominantColorBackground == null)? 0 :this.dominantColorBackground.hashCode()));
        result = ((result* 31)+((this.dominantColors == null)? 0 :this.dominantColors.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(java.lang.Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Color) == false) {
            return false;
        }
        Color rhs = ((Color) other);
        return ((((((((this.dominantColorForeground == rhs.dominantColorForeground)||((this.dominantColorForeground!= null)&&this.dominantColorForeground.equals(rhs.dominantColorForeground)))&&(this.isBwImg == rhs.isBwImg))&&(this.isBWImg == rhs.isBWImg))&&((this.accentColor == rhs.accentColor)||((this.accentColor!= null)&&this.accentColor.equals(rhs.accentColor))))&&((this.dominantColorBackground == rhs.dominantColorBackground)||((this.dominantColorBackground!= null)&&this.dominantColorBackground.equals(rhs.dominantColorBackground))))&&((this.dominantColors == rhs.dominantColors)||((this.dominantColors!= null)&&this.dominantColors.equals(rhs.dominantColors))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
