package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gdata.util.common.base.PercentEscaper;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  private TwitterDao dao;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

    //Setup dependency
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);

    //Pass dependency
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() throws JsonProcessingException {
    String text = "Hello #World!!!";
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    String txtPE = percentEscaper.escape(text);
    Double lat = 43.83671324;
    Double lon = -79.2511409;
    Tweet postTweet = TweetUtil.buildTweet(txtPE, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet));

    Tweet tweet = dao.create(postTweet);

    assertEquals(text, tweet.getText());

    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lat, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lon, tweet.getCoordinates().getCoordinates().get(1));

    System.out.println(JsonUtil.toPrettyJson(tweet));

    //assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void findById() throws JsonProcessingException {
    String id = "1469335758741938176";
    Tweet tweet = dao.findById(id);
    System.out.println(JsonUtil.toPrettyJson(tweet));

    assertEquals(id, tweet.getId_str());
  }

  @Test
  public void deleteById() throws JsonProcessingException {
    String id = "1469329214105169928";
    Tweet tweet = dao.deleteById(id);
    System.out.println(JsonUtil.toPrettyJson(tweet));

    assertEquals(id, tweet.getId_str());
  }
}