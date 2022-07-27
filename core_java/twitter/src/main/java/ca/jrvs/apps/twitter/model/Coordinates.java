package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

/**
 * Coordinates Object - Tweet Child Object (DTO/POJO).
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
    "coordinates",
    "type"
})
public class Coordinates {

  @JsonProperty("coordinates")
  private List<Double> coordinates = null;
  @JsonProperty("type")
  private String type;

  @JsonProperty("coordinates")
  public List<Double> getCoordinates() {
    return coordinates;
  }

  @JsonProperty("coordinates")
  public void setCoordinates(List<Double> coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }
}
