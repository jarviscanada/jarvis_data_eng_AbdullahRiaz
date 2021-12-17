package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private CrdDao dao;
  private Service service;
  private Controller controller;

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
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() throws JsonProcessingException {
    String[] postArgs = {"post", "Hello #World!!", "43.83671324:-79.2511409"};
    Tweet tweet = controller.postTweet(postArgs);
    System.out.println(JsonUtil.toPrettyJson(tweet));
  }

  @Test
  public void showTweet() throws JsonProcessingException {
    String[] showArgs = {"show", "1471840758810529794", "created_at,id,entities"};
    Tweet tweet = controller.showTweet(showArgs);
    System.out.println(JsonUtil.toPrettyJson(tweet));
  }

  @Test
  public void deleteTweet() throws JsonProcessingException {
    String[] delArgs = {"delete", "1471840758810529794"};
    List<Tweet> tweets = controller.deleteTweet(delArgs);
    for (Tweet tweet : tweets) {
      System.out.println(JsonUtil.toPrettyJson(tweet));
    }
  }
}