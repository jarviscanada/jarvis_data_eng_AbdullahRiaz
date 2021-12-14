package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    //Business logic:
    //ex: text length, lat/long range, id format
    if (!validatePostTweet(tweet)) {
      throw new IllegalArgumentException(
          "Number of characters exceed 280 and/or latitude/longitude value exceeds range");
    }

    //create tweet via dao
    return (Tweet) dao.create(tweet);
  }

  private static boolean validatePostTweet(Tweet tweet) {
    if (tweet.getText().length() <= 280) {
      if (tweet.getCoordinates().getCoordinates().get(0) >= -90
          && tweet.getCoordinates().getCoordinates().get(0) <= 90) {
        if (tweet.getCoordinates().getCoordinates().get(1) >= -180
            && tweet.getCoordinates().getCoordinates().get(1) <= 180) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean validateShowTweet(String id) {
    return id.length() == 19;
  }

  private static boolean validateDelTweets(String[] ids) {
    for (String id : ids) {
      if (id.length() != 19) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    if (!validateShowTweet(id)) {
      throw new IllegalArgumentException("Incorrect id");
    }

    //show tweet via dao
    Tweet tweet = (Tweet) dao.findById(id);
    //Fields
    Field field = tweet.getClass().getDeclaredField(Arrays.toString(fields));
    return
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    if (!validateDelTweets(ids)) {
      throw new IllegalArgumentException("Invalid id/ids");
    }
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }
}