package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  private CrdDao dao;
  private Service service;

  @Before
  public void setUp() throws Exception {
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
    this.service = new TwitterService(dao);
  }

  @Test
  public void postTweet() throws JsonProcessingException {
    String text = "Testing #Twitter REST API's!!! @skyz786";
    Double lat = 43.83671324;
    Double lon = -79.2511409;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toPrettyJson(postTweet));

    Tweet tweet = service.postTweet(postTweet);
  }

  @Test
  public void showTweet() throws JsonProcessingException {
    String id = "1470488103735500805";
    String[] fields = {"created_at","id","entities"};
    Tweet tweet = service.showTweet(id, fields);
    System.out.println(JsonUtil.toPrettyJson(tweet));
  }

  @Test
  public void deleteTweets() throws JsonProcessingException {
    String[] ids = {"1470813317157597184"};
    Tweet tweet = (Tweet) service.deleteTweets(ids);
    System.out.println(JsonUtil.toPrettyJson(tweet));
  }
}