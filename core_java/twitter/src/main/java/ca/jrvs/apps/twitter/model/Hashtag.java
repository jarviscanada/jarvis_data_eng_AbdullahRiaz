package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 * Hashtag Object - Part of Entities Object (DTO/POJO).
 */
@JsonInclude(Include.NON_NULL)
public class Hashtag {

  private String text;
  private List<Integer> indices;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }
}
