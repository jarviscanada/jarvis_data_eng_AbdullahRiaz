package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for Twitter.
 */
@Repository
public class TwitterDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Get the URI of twitter endpoint.
   *
   * @param tweet tweet object to obtain the URI from
   * @return URI of POST tweet endpoint
   * @throws URISyntaxException exception
   */
  public URI getPostUri(Tweet tweet) throws URISyntaxException {
    return new URI(
        API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + tweet.getText() + AMPERSAND
            + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(0) + AMPERSAND + "long"
            + EQUAL + tweet.getCoordinates().getCoordinates().get(1));
  }

  /**
   * Get the URI of twitter endpoint.
   *
   * @param id Tweet ID to obtain the URI from
   * @return URI of GET tweet endpoint
   * @throws URISyntaxException exception
   */
  public URI getShowUri(String id) throws URISyntaxException {
    return new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
  }

  /**
   * Get the URI of twitter endpoint.
   *
   * @param id Tweet ID to obtain the URI from
   * @return URI of POST tweet endpoint (note: Twitter API uses POST request to delete a tweet)
   * @throws URISyntaxException exception
   */
  public URI getDelUri(String id) throws URISyntaxException {
    return new URI(API_BASE_URI + DELETE_PATH + id + ".json");
  }

  /**
   * Check response status code Convert Response Entity to Tweet.
   *
   * @param response           Response Entity in String
   * @param expectedStatusCode Expected Status Code
   * @return Tweet object
   */
  private Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status:" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Empty response body");
    }

    //Convert Response Entity to String
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    //Deserialize JSON string to Tweet object
    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON str to Object", e);
    }
    return tweet;
  }

  @Override
  public Tweet create(Tweet entity) {
    //Construct URI
    URI uri;
    try {
      uri = getPostUri(entity);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }
    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deserialize response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet findById(String s) {
    //Construct URI
    URI uri;
    try {
      uri = getShowUri(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet id", e);
    }
    //Execute HTTP Request
    HttpResponse response = httpHelper.httpGet(uri);

    //Validate response and deserialize response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String s) {
    //Construct URI
    URI uri;
    try {
      uri = getDelUri(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet id", e);
    }
    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deserialize response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }
}
