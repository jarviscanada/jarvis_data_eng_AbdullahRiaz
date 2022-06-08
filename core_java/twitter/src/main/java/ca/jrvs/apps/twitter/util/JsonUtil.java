package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

/**
 * JSON utilities class to allow serialization of objects to JSON strings and deserialization of
 * JSON strings to objects.
 */
public class JsonUtil {

  /**
   * Convert a java object into JSON string.
   *
   * @param object input object
   * @return JSON string
   * @throws JsonProcessingException exception
   */
  public static String toJson(Object object, boolean prettyJson, boolean includeNullValues)
      throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();
    if (!includeNullValues) {
      m.setSerializationInclusion(Include.NON_NULL);
    }
    if (prettyJson) {
      m.enable(SerializationFeature.INDENT_OUTPUT);
    }
    return m.writeValueAsString(object);
  }

  /**
   * Parse JSON string into java object.
   *
   * @param json  JSON string
   * @param clazz object class
   * @param <T>   Type
   * @return Object
   * @throws IOException exception
   */
  public static <T> T toObjectFromJson(String json, Class clazz) throws IOException {
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json, clazz);
  }

  public static String toPrettyJson(Tweet tweet) throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();
    return m.writerWithDefaultPrettyPrinter().writeValueAsString(tweet);
  }
}
